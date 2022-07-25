package com.dodaso.codegen.util;

import java.util.List;

import com.dodaso.codegen.CodeGenProperties;
import com.dodaso.codegen.FieldObject;
import com.dodaso.util.FileUtil;
import com.dodaso.util.StringUtil;

public class CodeGenUtil {
	
	/**
     * Map the types from the DB world to the Java environment. We upscale some types since we do not have unsigned types in java :-(
     * 
     * @param fieldObj
     * @return Java type
     */
    public static String mapJavaType(FieldObject fieldObj) {
        String result = "";
        switch (fieldObj.getJavaDataType()) {
            case java.sql.Types.BOOLEAN:
            case java.sql.Types.BIT:
                return "Boolean";
            case java.sql.Types.TINYINT:
                return "Byte";
            case java.sql.Types.SMALLINT:
                return "Integer";
            case java.sql.Types.INTEGER:
                result = "Integer";
                break;
            case java.sql.Types.BIGINT:
                result = "Long";
                break;
            case java.sql.Types.NUMERIC:
            	 if (fieldObj.getScale() > 0){
            		 result = "Double";
            	 }else{
            		 result = "Integer";
            	 }
                break;
            case java.sql.Types.FLOAT:
            case java.sql.Types.REAL:
            case java.sql.Types.DOUBLE:
                result = "Double";
                break;
            case java.sql.Types.DECIMAL:
                result = "java.math.BigDecimal";
                break;
            case java.sql.Types.CHAR:
            case java.sql.Types.VARCHAR:
            case java.sql.Types.LONGVARCHAR:
            case java.sql.Types.NCHAR:
            case java.sql.Types.NVARCHAR:
            case java.sql.Types.LONGNVARCHAR:
            	if(fieldObj.getDataLength() == 1){
            		result = "Character";
            	}else{
            		result = "String";
            	}
                break;
            case java.sql.Types.BINARY:
            case java.sql.Types.VARBINARY:
            case java.sql.Types.LONGVARBINARY:
                result = "Byte[]";
                break;
            case java.sql.Types.DATE:
            case java.sql.Types.TIME:
            case java.sql.Types.TIMESTAMP:
                result = "Date";
                break;
            case java.sql.Types.ROWID:
            case java.sql.Types.NCLOB:
            case java.sql.Types.SQLXML:
            case java.sql.Types.NULL:
            case java.sql.Types.OTHER:
            case java.sql.Types.JAVA_OBJECT:
            case java.sql.Types.DISTINCT:
            case java.sql.Types.STRUCT:
            case java.sql.Types.ARRAY:
            case java.sql.Types.REF:
            case java.sql.Types.DATALINK:
                result = "Object";
                break;
            case java.sql.Types.BLOB:
            	result = "Byte[]";
            	break;
            case java.sql.Types.CLOB:
            	result = "String";
            	break;
            default:
                result = "Object";
                break;
        }
        return result;
    }
    
    public static String getJavaFieldName(String dbColumnName){
    	String str = dbColumnName.toLowerCase();
		while(str.contains("_")){
			int index = str.indexOf("_");
			String str2= "";
			if((index < str.length()-1)){
				 str2= StringUtil.initCap(str.substring(index + 1));
			}
			str = str.substring(0,index) + str2; 
		}
		
		return str;
    }
    
    public static void checkForRequiredDirectories(CodeGenProperties properties){
    	FileUtil.createDirectories(properties.getBaseDirectory());
    	FileUtil.createDirectories(properties.getBaseDomainObjectDirectory());
    	FileUtil.createDirectories(properties.getBaseDaoDirectory());
    	
    	if(!properties.isRegenerating()){
    		FileUtil.createDirectories(properties.getDomainObjectDirectory());
	    	FileUtil.createDirectories(properties.getDaoImplDirectory());
	       // FileUtil.createDirectories(properties.getActionsDirectory());
    	}
		if(!properties.isUsingAnnotations()){
    		FileUtil.createDirectories(properties.getMappingDirectory());
		}
	}
    
    public static String getConditinalStringForCloumns(List<FieldObject> fieldObjects){
		StringBuilder builder = new StringBuilder();
		for(FieldObject fieldObject : fieldObjects){
			String methodName = "get" + StringUtil.initCap(fieldObject.getJavaFieldName()) + "()";
			builder.append("\t\t\tObjectUtil.isEqual(" + methodName + ", other." + methodName + ") && \n");
		}
		
		String conditionalStr = builder.toString();
		return conditionalStr.substring(0, conditionalStr.lastIndexOf("&&")-1);
	}
    
    public static String getValidateMethod(List<FieldObject> fieldObjects){
		StringBuilder builder = new StringBuilder("\tpublic List<ValidationMessage> validate(){\n");
		builder.append("\t\tList<ValidationMessage> list = new ArrayList<ValidationMessage>();\n");
		for(FieldObject fieldObject : fieldObjects){
			builder.append(fieldObject.getValidateString());
		}
		builder.append("\t\treturn list;\n");
		builder.append("\t}\n");
		return builder.toString();
	}
    
    public static String getToStringMethod(List<FieldObject> fieldObjects){
		StringBuilder builder = new StringBuilder("\t@Override\n\tpublic String toString(){\n");
		builder.append("\t\tStringBuilder str = new StringBuilder();\n");
		for(FieldObject fieldObject : fieldObjects){
			builder.append("\t\tstr.append(" + fieldObject.getStringToPrintValue() + " + \"\\n\");\n");
		}
		builder.append("\t\treturn str.toString();\n");
		builder.append("\t}\n");
		return builder.toString();
	}
}
