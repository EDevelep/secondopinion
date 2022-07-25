package com.dodaso.codegen;

import java.io.Serializable;

import com.dodaso.codegen.util.CodeGenUtil;
import com.dodaso.util.StringUtil;

@SuppressWarnings("serial")
public class FieldObject implements Serializable {
	private String fieldName;
	private String javaFieldName;
	
	private int sqlDbDataType;
	private SqlType dbDataType;
	private int javaDataType;
	
	private boolean isPrimaryKey;
	
	private boolean hasDefaultValue;
	private String defaultValue;
	
	private boolean nullable;
	private int dataLength; // This should be used only for VARCHAR2 fields.
	private int precesion;
	private int scale;
	private boolean signed;
	
	private boolean auditField;
	private boolean auditFieldForCreate;
	private boolean versionField;
	
	/**
	 * @return the auditFieldForCreate
	 */
	public boolean isAuditFieldForCreate() {
		return auditFieldForCreate;
	}

	/**
	 * @param auditFieldForCreate the auditFieldForCreate to set
	 */
	public void setAuditFieldForCreate(boolean auditFieldForCreate) {
		this.auditFieldForCreate = auditFieldForCreate;
	}

	/**
	 * @return the auditField
	 */
	public boolean isAuditField() {
		return auditField;
	}

	/**
	 * @param auditField the auditField to set
	 */
	public void setAuditField(boolean auditField) {
		this.auditField = auditField;
	}

	/**
	 * @return the versionField
	 */
	public boolean isVersionField() {
		return versionField;
	}

	/**
	 * @param versionField the versionField to set
	 */
	public void setVersionField(boolean versionField) {
		this.versionField = versionField;
	}

	/**
	 * @return the signed
	 */
	public boolean isSigned() {
		return signed;
	}

	/**
	 * @param signed the signed to set
	 */
	public void setSigned(boolean signed) {
		this.signed = signed;
	}

	/**
	 * @return the precesion
	 */
	public int getPrecesion() {
		return precesion;
	}

	/**
	 * @param precesion the precesion to set
	 */
	public void setPrecesion(int precesion) {
		this.precesion = precesion;
	}

	/**
	 * @return the scale
	 */
	public int getScale() {
		return scale;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}

	/**
	 * @return the sqlDbDataType
	 */
	public int getSqlDbDataType() {
		return sqlDbDataType;
	}

	/**
	 * @param sqlDbDataType the sqlDbDataType to set
	 */
	public void setSqlDbDataType(int sqlDbDataType) {
		this.sqlDbDataType = sqlDbDataType;
	}

	/**
	 * @return the dataLength
	 */
	public int getDataLength() {
		return dataLength;
	}

	/**
	 * @param dataLength
	 *            the dataLength to set
	 */
	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	/**
	 * @return the nullable
	 */
	public boolean isNullable() {
		return nullable;
	}

	/**
	 * @param nullable
	 *            the nullable to set
	 */
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	/**
	 * @param nullable
	 *            the nullable to set
	 */
	public void setNullable(String nullable) {
		this.nullable = StringUtil.isYes(nullable);
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldConstantName() {
		return "FIELD_" + fieldName;
	}
	
	/**
	 * @param fieldName
	 *            the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
		
		if(fieldName.contains("_")){
			this.javaFieldName = StringUtil.getCamelCaseRemovingUnderScore(fieldName);
		}else{
			this.javaFieldName = fieldName;// CodeGenUtil.getJavaFieldName(fieldName);
		}
	}

	/**
	 * @return the dbDataType
	 */
	public SqlType getDbDataType() {
		return dbDataType;
	}

	/**
	 * @param dbDataType
	 *            the dbDataType to set
	 */
	public void setDbDataType(SqlType dbDataType) {
		this.dbDataType = dbDataType;
		this.javaDataType = SqlType.getJavaType(dbDataType);
	}

	/**
	 * @return the javaDataType
	 */
	public int getJavaDataType() {
		return javaDataType;
	}

	/**
	 * @param javaDataType
	 *            the javaDataType to set
	 */
	public void setJavaDataType(int javaDataType) {
		this.javaDataType = javaDataType;
	}

	/**
	 * @return the isPrimaryKey
	 */
	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	/**
	 * @param isPrimaryKey
	 *            the isPrimaryKey to set
	 */
	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	/**
	 * @return the hasDefaultValue
	 */
	public boolean isHasDefaultValue() {
		return hasDefaultValue;
	}

	/**
	 * @param hasDefaultValue
	 *            the hasDefaultValue to set
	 */
	public void setHasDefaultValue(boolean hasDefaultValue) {
		this.hasDefaultValue = hasDefaultValue;
	}

	/**
	 * @return the defaultValue
	 */
	public Object getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue
	 *            the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	/**
	 * @return the javaFieldName
	 */
	public String getJavaFieldName(){
		return this.javaFieldName; 
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 17;
		int result = 1;
		result = prime * result
				+ ((fieldName == null) ? 0 : fieldName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FieldObject other = (FieldObject) obj;
		if (fieldName == null) {
			if (other.fieldName != null)
				return false;
		} else if (!fieldName.equals(other.fieldName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return fieldName + " - " + javaFieldName + " - "+ dbDataType + " - " + javaDataType + " - "
				+ isPrimaryKey + " - " + hasDefaultValue + " - " + defaultValue
				+ " - " + nullable + "  -  " + dataLength + "\n";
	}
	
	public boolean isFieldTypeUnsigned(){
		return dbDataType.isLong();
	}
	
	public String getSetterMethod() {
		StringBuilder setterStr = new StringBuilder("\tpublic void set" + StringUtil.initCap(this.getJavaFieldName()));
		setterStr.append("( " + CodeGenUtil.mapJavaType(this) + "  _" + this.getJavaFieldName() +  "){\n");
		
		setterStr.append("\t\tthis." +  this.getJavaFieldName() + " = _" +  this.getJavaFieldName() + ";\n");
		setterStr.append("\t}");
		
		return setterStr.toString();
	}
	
	public String getGetterMethodCall(){
		String filedName = getJavaFieldName();
		if(fieldName.contains("_")){
			String camelCaseStr = StringUtil.getCamelCaseRemovingUnderScore(filedName);
			return "get" + StringUtil.initCap(camelCaseStr) + "()";
		}else{
			return "get" + StringUtil.initCap(this.getJavaFieldName()) + "()";
		}
	}
	
	public String getRestrictionForEquals(){
		return "Restrictions.eq(" + getFieldConstantName() + ", " + getGetterMethodCall()+")";
	}
	
	public String getMethodAnnotation(){
		StringBuilder builder = new StringBuilder();
		if(isAnLOB()){
			builder.append("\t@Lob \n");
		}
		
		if(!this.isNullable()){
			builder.append("\t@NotNull \n");	
		}
		
		if(this.isString()){
			builder.append("\t@Length(max=" + this.getDataLength()+")\n");
		}
		
		//TODO: un-comment this.
		/*if(this.isVersionField()){
			builder.append("\t@Version\n");
		}*/
		
		builder.append("\t@Column (name=\"" + fieldName + "\")\n");
		return builder.toString();
	}
	
	public String getGetterMethod() {
		StringBuilder setterStr = new StringBuilder("\tpublic " + CodeGenUtil.mapJavaType(this) + " " + getGetterMethodCall());
		setterStr.append("{\n");
		setterStr.append("\t\t return this." +  this.getJavaFieldName() + ";\n");
		setterStr.append("\t}");
		
		return setterStr.toString();
	}
	
	public String getValidateString(boolean nonCompositeKey){
		StringBuilder setterStr = new StringBuilder();
		String tab = "";
		
		if(!this.isNullable() || nonCompositeKey){
			
			if(this.isAuditField()){
				tab = "\t";
				setterStr.append(tab+ "\tif(this.isFromDB()){\n");
			}
			if(this.isString()){
				setterStr.append(tab+"\t\tif( StringUtil.isNullOrEmpty(this."+this.getJavaFieldName()+")){\n");
			}else{
				setterStr.append(tab+"\t\tif(this."+this.getJavaFieldName()+" == null){\n");
			}
			setterStr.append(tab+"\t\t\t list.add(new ValidationMessage(\"Field \" + " + this.getFieldConstantName() + "+  \" cannot be null\"));\n");
			setterStr.append(tab+"\t\t}\n");
			if(this.isAuditField())
				setterStr.append(tab+"\t}");
			setterStr.append("\n");
		}
		
		if(this.isString()){
			if(this.isAuditField()){
				setterStr.append(tab+"\tif(this.isFromDB()){\n");
			}
			setterStr.append(tab+"\t\tif((this."+this.getJavaFieldName()+" != null) && (this."+this.getJavaFieldName()+".length()>"+ this.getDataLength()+")){\n");
			setterStr.append(tab+"\t\t\t list.add(new ValidationMessage(\"Field \" + " + this.getFieldConstantName() + "+  \" cannot be longer than: \" + " + this.getDataLength() + "));\n");
			setterStr.append(tab+"\t\t}\n");
		
			if(this.isAuditField())
				setterStr.append(tab+"\t}");
			setterStr.append("\n");
		}
		
		return setterStr.toString();
	}
	
	public String getValidateString(){
		return getValidateString(false);
		/*StringBuilder setterStr = new StringBuilder();
		if(!this.isNullable()){
			if(this.isString()){
				setterStr.append("\t\tif( StringUtil.isNullOrEmpty(this."+this.getJavaFieldName()+")){\n");
			}else{
				setterStr.append("\t\tif(this."+this.getJavaFieldName()+" == null){\n");
			}
			setterStr.append("\t\t\t list.add(new ValidationMessage(\"Field \" + " + this.getJavaFieldName() + "+  \" cannot be null\"));\n");
			setterStr.append("\t\t}\n");
			setterStr.append("\n");
		}
		
		if(this.isString()){
			setterStr.append("\t\tif((this."+this.getJavaFieldName()+" != null) && (this."+this.getJavaFieldName()+".length()>"+ this.getDataLength()+")){\n");
			setterStr.append("\t\t\t list.add(new ValidationMessage(\"Field \" + " + this.getJavaFieldName() + "+  \" cannot be longer than: \" + " + this.getDataLength() + "));\n");
			setterStr.append("\t\t}\n");
			setterStr.append("\n");
		}
		
		return setterStr.toString();*/
	}
	
	public String getStringToPrintValue(){
		return "\"" + getJavaFieldName() + " = \" + " + getJavaFieldName() ; 
	}
	
	public boolean isAnLOB(){
		if(javaDataType == java.sql.Types.BLOB ||javaDataType == java.sql.Types.CLOB){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isString(){
		boolean isString = false;
		
		switch (javaDataType) {
		case java.sql.Types.CHAR:
		case java.sql.Types.VARCHAR:
		case java.sql.Types.LONGVARCHAR:
		case java.sql.Types.NCHAR:
		case java.sql.Types.NVARCHAR:
        case java.sql.Types.LONGNVARCHAR:
        	if(this.getDataLength() != 1)
        		isString = true;
        	break;
		default:
			break;
		}
		
		return isString;
	}
	
	public boolean isCharacter(){
		boolean isChar = false;
		
		switch (javaDataType) {
		case java.sql.Types.CHAR:
		case java.sql.Types.VARCHAR:
		case java.sql.Types.LONGVARCHAR:
		case java.sql.Types.NCHAR:
		case java.sql.Types.NVARCHAR:
        case java.sql.Types.LONGNVARCHAR:
        	if(this.getDataLength() == 1)
        		isChar = true;
        	break;
		default:
			break;
		}
		
		return isChar;
	}
	
	public boolean isDate(){
		boolean isDate = false;
		
		switch (javaDataType) {
		case java.sql.Types.DATE:
        case java.sql.Types.TIME:
        case java.sql.Types.TIMESTAMP:
        	isDate = true;
        	break;
		default:
			break;
		}
		
		return isDate;
	}
}
