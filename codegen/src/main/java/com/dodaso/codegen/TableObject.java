package com.dodaso.codegen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.dodaso.codegen.util.CodeGenUtil;
import com.dodaso.exception.CodeGenException;
import com.dodaso.util.StringUtil;

@SuppressWarnings("serial")
public class TableObject implements Serializable{
	private String tableNameToQuery;
	private String sequence;

	private String schemaName;
	private String tableName;
	
	private String tableClassName;
	private KeyField keyField;
	private boolean view;
	private boolean hasUnderScores;
	/**
	 * @return the view
	 */
	public boolean isView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(boolean view) {
		this.view = view;
	}

	private List<FieldObject> auditFields = new ArrayList<FieldObject>();
	private List<FieldObject> fieldObjects = new ArrayList<FieldObject>();
	
	/**
	 * @return the keyField
	 */
	public KeyField getKeyField() {
		return keyField;
	}

	/**
	 * @param keyField the keyField to set
	 */
	public void setKeyField(KeyField keyField) {
		this.keyField = keyField;
	}

	/**
	 * @return the tableClassName
	 */
	public String getTableClassName() {
		return tableClassName;
	}

	/**
	 * @param tableName2
	 */
	public TableObject(String schemaName, String tableName2) {
		if(StringUtil.isNullOrEmpty(tableName2)){
			throw new CodeGenException("Table cannot be null or empty.");
		}
		tableNameToQuery = tableName2;
		
		if(tableName2.contains("_")){
			String camelCaseName = StringUtil.getCamelCaseRemovingUnderScore(tableName2);
			camelCaseName = StringUtil.initCap(camelCaseName);
			this.tableName = camelCaseName;
			this.tableClassName = camelCaseName;
		}else{
		
			this.tableName = tableName2.toUpperCase();
			
			this.tableClassName =  StringUtil.initCap(tableName2);//StringUtil.initCap(CodeGenUtil.getJavaFieldName(tableName));
		}
		
		this.schemaName = schemaName;
	}
	
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		if(StringUtil.isNullOrEmpty(tableName)){
			throw new CodeGenException("Table cannot be null or empty.");
		}
		this.tableName = tableName.toUpperCase();
		this.tableClassName = StringUtil.initCap(CodeGenUtil.getJavaFieldName(tableName));
	}
	/**
	 * @return the fieldObjects
	 */
	public List<FieldObject> getFieldObjects() {
		return fieldObjects;
	}
	/**
	 * @param fieldObjects the fieldObjects to set
	 */
	public void setFieldObjects(List<FieldObject> fieldObjects) {
		this.fieldObjects = fieldObjects;
	}
	
	
	public void addField(FieldObject fieldObject){
		fieldObjects.add(fieldObject);
		if(fieldObject.isAuditField()){
			auditFields.add(fieldObject);
		}
	}
	
	@Override
	public String toString() {
		return "Table Name: " + tableName + " - Table Class Name: " + tableClassName + "\n" + fieldObjects.toString();
	}
	
	public boolean containsDataField(){
		boolean contains = false;
		
		for(FieldObject fieldObject : fieldObjects){
			if(fieldObject.isDate()){
				contains = true;
				break;
			}
		}
		
		return contains;
	}
	
	public boolean containsAnLOB(){
		boolean contains = false;
		
		for(FieldObject fieldObject : fieldObjects){
			if(fieldObject.isAnLOB()){
				contains = true;
				break;
			}
		}
		
		return contains;
	}
	
	public String getCloneMethod(){
		StringBuilder sb = new StringBuilder("\t@Override\n");
		
		sb.append("\tpublic Object clone() throws CloneNotSupportedException {\n");
		sb.append("\t\t" + this.tableClassName + " " + StringUtil.initlower(tableClassName) + " = new " +  this.tableClassName+"();\n");
		sb.append("\t\t" +StringUtil.initlower(tableClassName)+ ".setFromDB(false);\n");
		
		if(this.keyField.isCompositeKey()){
			sb.append("\t\t"+StringUtil.initlower(tableClassName)+ ".set"+ StringUtil.initCap(keyField.getKeyClass()));
			sb.append("(new " + StringUtil.initCap(keyField.getKeyClass())+ "());\n");
		}
		
		for(FieldObject fieldObject : fieldObjects){
			if(!fieldObject.isAuditField()){
				sb.append("\t\t"+ StringUtil.initlower(tableClassName)+ ".set"+ StringUtil.initCap(fieldObject.getJavaFieldName())+"(");
				sb.append(fieldObject.getGetterMethodCall() + ");\n");
			}
		}
		sb.append("\t\t"+"//afterClone("+ StringUtil.initlower(tableClassName) +");\n");
		sb.append("\t\treturn " + StringUtil.initlower(tableClassName) + ";\n");
		sb.append("\t}\n");
		return sb.toString();
		
	}
	
	public String getEqualsMethodForDomainObject(){
		StringBuilder sb = new StringBuilder("\t@Override\n");
		
		sb.append("\tpublic boolean equals(Object o){\n");
		sb.append("\t\tif (this == o) return true;\n");
		sb.append("\t\tif (o == null || getClass() != o.getClass()) return false;\n");
		sb.append("\t\tBase" + tableClassName + " other = (Base" + tableClassName + ")o;\n");
		sb.append("\t\tif(\n");
		sb.append(getConditinalStringForCloumns());
		sb.append("){\n");
		sb.append("\t\t\treturn true;\n");
		sb.append("\t\t}\n");
		sb.append("\t\treturn false;\n");
		sb.append("\t}\n");
		
		return sb.toString();
	}
	
	public String getHashCodeMethod(){
		StringBuilder sb = new StringBuilder("\t@Override\n");
		sb.append("\tpublic int hashCode(){\n");
		sb.append("\t\tint result = 0;\n");
		sb.append(keyField.getHashCodeString() + "\n");
		sb.append("\t\treturn result;\n");
		sb.append("\t}\n");
		
		return sb.toString();
	}
	
	private String getConditinalStringForCloumns(){
		StringBuilder builder = new StringBuilder();
		for(FieldObject fieldObject : fieldObjects){
			String methodName = "get" + StringUtil.initCap(fieldObject.getJavaFieldName()) + "()";
			builder.append("\t\t\tObjectUtil.isEqual(" + methodName + ", other." + methodName + ") && \n");
		}
		
		String conditionalStr = builder.toString();
		if(StringUtil.isNullOrEmpty(conditionalStr)){
			return "";
		}
		try{
			return conditionalStr.substring(0, conditionalStr.lastIndexOf("&&")-1);
		}catch(Exception ex){
			System.out.println("Table Name:" + this.tableName);
			System.out.println("Conditional Str:" + conditionalStr);
			throw new RuntimeException(ex);
		}
	}
	
	public String getSetAuditFieldsMethod(){
		StringBuilder builder = new StringBuilder("\t@Override\n");
		builder.append("\tpublic final void setAuditFields() {\n");
		if(auditFields.size() != 0){
			builder.append("\t\tif(!isFromDB()){\n");
			for(FieldObject fieldObject :  auditFields){
				if(fieldObject.isAuditFieldForCreate()){
					if(fieldObject.isDate())
						builder.append("\t\t\tthis." + fieldObject.getJavaFieldName()  +" = AppThreadLocal.getRequestStartDate();\n");
					else
						builder.append("\t\t\tthis." + fieldObject.getJavaFieldName()  +" = AppThreadLocal.getUserName();\n");
				}
			}
			builder.append("\t\t}\n");
			
			for(FieldObject fieldObject :  auditFields){
				if(!fieldObject.isAuditFieldForCreate()){
					if(fieldObject.isDate())
						builder.append("\t\tthis." + fieldObject.getJavaFieldName()  +" = AppThreadLocal.getRequestStartDate();\n");
					else
						builder.append("\t\tthis." + fieldObject.getJavaFieldName()  +" = AppThreadLocal.getUserName();\n");
				}
			}
		}
		
		builder.append("\t}\n");
		
		return builder.toString();
	}
	
	/**
	 * @return the sequence
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(String sequence) {
		if(StringUtil.isNullOrEmpty(sequence)){
			this.sequence = tableName + "_SEQ";
		}else
			this.sequence = sequence;
	}
	
	public String getTableAnnotation(){
		StringBuilder builder = new StringBuilder();
		builder.append("@Entity \n");
//		builder.append("@Name(\"" + StringUtil.initlower(tableClassName) +"\")\n");
		builder.append("@Table (name=\"" + tableName +"\")\n");
	/*	if(!this.keyField.isCompositeKey()){
			builder.append("@SequenceGenerator(name=\"" + StringUtil.initlower(tableClassName)+ "Seq\", sequenceName=\"" + sequence + "\")\n");
		}*/
		return builder.toString();
	}
	
	public String getMappingStr(String packageForDomainObject){
		return "\t<mapping class=\"" + packageForDomainObject + "." + tableClassName +"\"/>\n"; 
	}
	
	public String getDaoConfig(String daoImplPackage){
		StringBuilder builder = new StringBuilder();
		
		builder.append("\t<dao>\n");
		builder.append("\t\t<interfaceName>" + tableClassName + "DAO</interfaceName>\n");
		builder.append("\t\t<className>\n");
		builder.append("\t\t\t" + daoImplPackage + "." + tableClassName + "DAOHibernateImpl\n"); 
		builder.append("\t\t</className>\n");
		builder.append("\t\t<implType>hibernate</implType>\n");
		builder.append("\t\t<springBeanId>"+ tableClassName + "DAOHibernateImpl</springBeanId>\n");
		builder.append("\t</dao>\n");
		
		return builder.toString();
	}
	
	public String getSpringBeanDefinition(String daoImplPackage){
		return "\t<bean id=\"" + tableClassName + "DAOHibernateImpl\" class=\"" + daoImplPackage + "." + tableClassName + "DAOHibernateImpl\" /> \n";
	}

	public boolean isHasUnderScores() {
		return hasUnderScores;
	}

	public void setHasUnderScores(boolean hasUnderScores) {
		this.hasUnderScores = hasUnderScores;
	}

	public String getTableNameToQuery() {
		return tableNameToQuery;
	}

	public void setTableNameToQuery(String tableNameToQuery) {
		this.tableNameToQuery = tableNameToQuery;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
}
