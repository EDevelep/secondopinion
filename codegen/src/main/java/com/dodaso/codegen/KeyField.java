package com.dodaso.codegen;

import java.util.ArrayList;
import java.util.List;

import com.dodaso.codegen.util.CodeGenUtil;
import com.dodaso.util.StringUtil;

public class KeyField {
	private List<FieldObject> keyField = new ArrayList<FieldObject>();
	private String tableName;
	private String keyClass;
	
	public KeyField(String tableClassName) {
		this.tableName = StringUtil.initlower(tableClassName);
		this.keyClass = tableClassName + "Key";
	}
	
	public void addField(FieldObject fieldObject){
		keyField.add(fieldObject); 
	}
	
	/**
	 * @return the keyFiled
	 */
	public List<FieldObject> getKeyField() {
		return keyField;
	}
	
	/**
	 * @param keyFiled the keyFiled to set
	 */
	public void setKeyFiled(List<FieldObject> keyFiled) {
		this.keyField = keyFiled;
	}
	
	public boolean isCompositeKey(){
		return (keyField.size() > 1);
	}
	
	public String getHashCodeString(){
		StringBuilder sb = new StringBuilder();
		if(isCompositeKey()){
			sb.append("\t\tresult = result + (" + StringUtil.initlower(keyClass) + ".hashCode());\n");
		}else{
			sb.append("\t\tresult = result + (" + keyField.get(0).getJavaFieldName() + "!= null ? " + keyField.get(0).getJavaFieldName() + ".hashCode() : 0);\n");
		}
		return sb.toString();
	}

	public String getHashCodeMethod(){
		StringBuilder sb = new StringBuilder("\t@Override\n");
		sb.append("\tpublic int hashCode(){\n");
		sb.append("\t\tint result = 0;\n");
		
		for(FieldObject fieldObject : this.keyField){
			sb.append("\t\tresult = result + (" + fieldObject.getJavaFieldName() + "!= null ? " + fieldObject.getJavaFieldName() + ".hashCode() : 0);\n");
		}
		
		sb.append("\t\treturn result;\n");
		sb.append("\t}\n");
		
		return sb.toString();
	}
	
	
	public String getEqualsMethod(String tableClassName) {
		StringBuilder sb = new StringBuilder("\t@Override\n");
		
		sb.append("\tpublic boolean equals(Object o){\n");
		sb.append("\t\tif (this == o) return true;\n");
		sb.append("\t\tif (o == null || getClass() != o.getClass()) return false;\n");
		sb.append("\t\t" + tableClassName + "Key other = (" + tableClassName + "Key)o;\n");
		sb.append("\t\tif(\n");
		sb.append(CodeGenUtil.getConditinalStringForCloumns(this.keyField));
		sb.append("){\n");
		sb.append("\t\t\treturn true;\n");
		sb.append("\t\t}\n");
		sb.append("\t\treturn false;\n");
		sb.append("\t}\n");
		
		return sb.toString();
	}
	
	public String getIdInfoForHibernateMapping(CodeGenProperties codeGenProperties){
		if(!isCompositeKey()){
			return getHibernateMapping(keyField.get(0));
			
		}else{
			return getHibernateMapping(codeGenProperties);
		}
	}

	private String getHibernateMapping(FieldObject object){
		StringBuilder builder = new StringBuilder("\t\t<id name=\""+ object.getJavaFieldName() + "\" type=\"java.lang.Integer\" column=\"" +  object.getFieldName() + "\">\n");
		builder.append("\t\t\t<generator class=\"sequence\">\n");
		builder.append("\t\t\t\t<param name=\"sequence\">" + tableName.toUpperCase() + "_SEQ</param></generator>\n");
		builder.append("\t\t</id>\n");
		return builder.toString();
	}
	
	private String getHibernateMapping(CodeGenProperties codeGenProperties){
		StringBuilder builder = new StringBuilder();
		builder.append("\t\t<composite-id  name=\"" + StringUtil.initlower(keyClass) + "\" class=\"" + codeGenProperties.getBaseDataObjectPackage() + "." + keyClass + "\" >\n");
		
		for(FieldObject fieldObject : keyField){
			builder.append("\t\t\t<key-property name=\"" + fieldObject.getJavaFieldName() +"\" column=\"" + fieldObject.getFieldName() +"\"/>\n"); 
		}
		builder.append("\t\t</composite-id >\n");
		
		return builder.toString();
	}

	public String getFieldName(boolean printPk) {
		String fieldStr = "\tpublic static final String FIELD_";
		StringBuilder builder = new StringBuilder();
		
		if(printPk && this.isCompositeKey()){
			builder.append(fieldStr + (keyClass).toUpperCase() + "= \""+ StringUtil.initlower(keyClass)+ "\";\n" );
		}
		
		if(this.isCompositeKey()){
			for(FieldObject fieldObject : keyField){
				builder.append(fieldStr + fieldObject.getFieldName() + " = \"" + StringUtil.initlower(keyClass) + "." + fieldObject.getJavaFieldName() + "\";\n");
			}
		}else{
			for(FieldObject fieldObject : keyField){
				builder.append(fieldStr + fieldObject.getFieldName() + " = \"" +  fieldObject.getJavaFieldName() + "\";\n");
			}
		}
		
		return builder.toString();
	}
	
	public String getFieldDef() {
		StringBuilder builder = new StringBuilder();
		
		for(FieldObject fieldObject : keyField){
			builder.append("\tprivate " + CodeGenUtil.mapJavaType(fieldObject) + " " + fieldObject.getJavaFieldName() + ";\n");
		}
		
		return builder.toString();
	}
	
	public String getFieldName() {
		return getFieldName(true);
	}
	
	public String getSetterMethod() {
		if(isCompositeKey()){
			StringBuilder setterStr = new StringBuilder("\tpublic void set" + keyClass);
			setterStr.append("( " + keyClass + "  _" + StringUtil.initlower(keyClass) +  "){\n");
			setterStr.append("\t\tthis." + StringUtil.initlower(keyClass) + " = _" +  StringUtil.initlower(keyClass) + ";\n");
			setterStr.append("\t}");
			
			return setterStr.toString();
		}else{
			return keyField.get(0).getSetterMethod();
		}
			
	}
	
	public String getGetterMethod() {
		if(isCompositeKey()){
			StringBuilder setterStr = new StringBuilder("\tpublic " + keyClass + " get" + keyClass);
			setterStr.append("(){\n");
			setterStr.append("\t\t return this." +  StringUtil.initlower(keyClass) + ";\n");
			setterStr.append("\t}");
			
			return setterStr.toString();
		}else{
			return keyField.get(0).getGetterMethod();
		}
	}
	
	public String getGetterMethodCall(){
		if(isCompositeKey()){
			return "get" + keyClass + "()";
		}else{
			return keyField.get(0).getGetterMethodCall();
		}
	}

	public String getKeyFieldDef() {
		if(isCompositeKey()){
			return "\tprivate " + keyClass + " " +  StringUtil.initlower(keyClass) + ";\n";
		}else{
			return "\tprivate " + CodeGenUtil.mapJavaType(keyField.get(0)) + " " + keyField.get(0).getJavaFieldName() + ";\n";
		}
	}

	public String getValidateString() {
		if(isCompositeKey()){
			return "\n\t\t\tlist = " +  StringUtil.initlower(keyClass) + ".validate();\n\n";
		}else{
			return keyField.get(0).getValidateString(true);
		}
	}

	public String getToStringPrintValue() {

		if(isCompositeKey()){
			StringBuilder builder = new StringBuilder();
			builder.append("\t\tif(" + StringUtil.initlower(keyClass) + " != null){\n");
			builder.append("\t\t\tstr.append(" + StringUtil.initlower(keyClass) +".toString());\n");
			builder.append("\t\t}\n");
			return builder.toString();
			//"\t\tstr.append(" + StringUtil.initlower(keyClass) +".toString())";
		}else{
			return "\t\tstr.append(" + keyField.get(0).getStringToPrintValue() + " + \"\\n\");\n";
		}
	}
	
	public String getKeyClass(){
		if(isCompositeKey()){
			return keyClass;
		}else{
			return CodeGenUtil.mapJavaType(keyField.get(0));
		}
	}
	
	public String getMethodAnnotation(){
		String annotation = "";
		if(isCompositeKey()){
			annotation = "\t@Id\n";
		}else{
			annotation="\t@Id\n " +
					//"\t@NotNull\n"+
					(this.keyField.get(0).isString()?"\t@Length(max="+ this.keyField.get(0).getDataLength()+")\n"  :"")+
					"\t@Column(name = \"" + keyField.get(0).getFieldName() +"\") \n" +
					(this.keyField.get(0).isString()? "" : "\t@GeneratedValue(strategy = GenerationType.IDENTITY)\n");
		}
		return annotation;
	}

	public String getKeyNullCheckString() {
		String nullCheckStr = "";
		
		if(isCompositeKey()){
			nullCheckStr = "\t\treturn (" + StringUtil.initlower(keyClass) + " == null); \n";
		}else{
			String keyFieldName = keyField.get(0).getJavaFieldName();
			if(keyField.get(0).isString()){
				nullCheckStr = "\t\treturn StringUtil.isNullOrEmpty(" + keyFieldName + ");\n"; 
			}else{
				nullCheckStr = "\t\treturn (" + keyFieldName + " == null); \n";
			}
		}
		return nullCheckStr;
	}
}