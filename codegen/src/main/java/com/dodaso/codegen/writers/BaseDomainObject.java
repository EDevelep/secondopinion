package com.dodaso.codegen.writers;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.dodaso.codegen.CodeGenProperties;
import com.dodaso.codegen.FieldObject;
import com.dodaso.codegen.KeyField;
import com.dodaso.codegen.TableObject;
import com.dodaso.codegen.util.CodeGenUtil;
import com.dodaso.exception.CodeGenException;
import com.dodaso.util.FileUtil;
import com.dodaso.util.StringUtil;

public class BaseDomainObject extends BaseObjectWriter {

	public void createDataObject(CodeGenProperties codeGenProperties, TableObject object) {
		String fileName = codeGenProperties.getBaseDomainObjectDirectory() + File.separator + "Base" + object.getTableClassName() + ".java";
		//System.out.println("Creating file: " + fileName);
		Writer writer = FileUtil.getPrintWriter(fileName);
		writePackageName(codeGenProperties,object, writer);
		writeImports(codeGenProperties,object, writer);
		openClassNameDef(object, writer);
		writeBody(object, writer);
		closeClassNameDef(object, writer);
		FileUtil.closeWriter(writer);
	}

	public String getFileName(CodeGenProperties codeGenProperties, TableObject object) {
		return codeGenProperties.getBaseDomainObjectDirectory() + File.separator + "Base"+ object.getTableClassName() + ".java";		
	}
	
	protected void writeFieldName(TableObject tableObject, Writer writer) {
		String fieldStr = "\tpublic static final String ";

		List<FieldObject> list = tableObject.getFieldObjects();
		try {
			writer.write(tableObject.getKeyField().getFieldName());
			for (FieldObject fieldObject : list)
				writer.write(fieldStr + fieldObject.getFieldConstantName() + " = \"" + fieldObject.getJavaFieldName() + "\";\n");

			writer.write("\n");
			writer.write(tableObject.getKeyField().getKeyFieldDef());
			for (FieldObject fieldObject : list){
				System.out.println(" Field Obj: " + fieldObject.getFieldName());
				
				if(StringUtil.equalsIgnoreCase(fieldObject.getFieldName(), "lastUpdatedBy")
					||StringUtil.equalsIgnoreCase(fieldObject.getFieldName(), "lastUpdatedTs")){
					continue;
				}
				writer.write("\tprivate "
						+ CodeGenUtil.mapJavaType(fieldObject) + " "
						+ fieldObject.getJavaFieldName() + ";\n");
			}

		} catch (IOException e) {
			throw new CodeGenException("Error creating FieldName constants");
		}
	}

	protected void writeSetterAndGetter(TableObject tableObject, Writer writer) {
		try {
			writer.write(tableObject.getKeyField().getSetterMethod());
			writer.write("\n");
			
			if(isUsingAnnotations())
				writer.write(tableObject.getKeyField().getMethodAnnotation());
			
			writer.write(tableObject.getKeyField().getGetterMethod());
			writer.write("\n");
			
			List<FieldObject> list = tableObject.getFieldObjects();
			for (FieldObject fieldObject : list) {
				
				if(StringUtil.equalsIgnoreCase(fieldObject.getFieldName(), "lastUpdatedBy")
					||StringUtil.equalsIgnoreCase(fieldObject.getFieldName(), "lastUpdatedTs")){
					continue;
				}
				
				writer.write(fieldObject.getSetterMethod());
				writer.write("\n");
				
				if(isUsingAnnotations())
					writer.write(fieldObject.getMethodAnnotation());
				writer.write(fieldObject.getGetterMethod());
				writer.write("\n");
			}
		} catch (IOException e) {
			throw new CodeGenException("Error creating FieldName constants");
		}
	}

	public void writeBody(TableObject tableObject, Writer writer) {
		try {
			writer.write("\n");
			writeFieldName(tableObject, writer);
			writer.write("\n");
			writeSetterAndGetter(tableObject, writer);
			writer.write("\n");
			writer.write(tableObject.getEqualsMethodForDomainObject());
			writer.write("\n");
			writer.write(tableObject.getHashCodeMethod());
			writer.write("\n");
			writer.write(getValidateMethod(tableObject));
			writer.write("\n");
			writer.write(tableObject.getSetAuditFieldsMethod());
			writer.write("\n");
			writer.write(getToStringMethod(tableObject));
			writer.write("\n");
			writer.write(getIsKeyNullMethod(tableObject));
			writer.write("\n");
			writer.write(getKeyFieldMethod(tableObject));
			writer.write("\n");
			writer.write(getIdMethod(tableObject));
			writer.write("\n");
			writer.write(tableObject.getCloneMethod());
		} catch (IOException e) {
			throw new CodeGenException("Error openning class def");
		}
	}
	
	private String getIdMethod(TableObject tableObject) {
		StringBuilder builder = new StringBuilder();
		builder.append("\t@Transient\n\t@Override\n");
		builder.append("\tpublic " + tableObject.getKeyField().getKeyClass() + " getId(){\n");
		builder.append("\t\treturn " + tableObject.getKeyField().getGetterMethodCall()+ ";\n");
		builder.append("\t}\n");
		return builder.toString();
	}

	private String getKeyFieldMethod(TableObject tableObject) {
		StringBuilder builder = new StringBuilder("\t@Transient\n\t@Override\n\tpublic final List<KeyField> getKeyField(){\n");
		KeyField field = tableObject.getKeyField();
		if(field.isCompositeKey()){
			builder.append("\t\treturn " + StringUtil.initlower(field.getKeyClass()) + ".getKeyField();\n");
		}else{
			builder.append("\t\tList<KeyField> list = new ArrayList<KeyField>();\n");
			builder.append("\t\tlist.add(new KeyField(" + field.getKeyField().get(0).getFieldConstantName() + ", " + field.getKeyField().get(0).getGetterMethodCall() + "));\n");
			builder.append("\t\treturn list;\n");  
		}
		builder.append("\t}\n");
		return builder.toString();
	}

	private String getIsKeyNullMethod(TableObject tableObject) {
		StringBuilder builder = new StringBuilder("\t@Transient\n\t@Override\n\tpublic final boolean isKeyNull(){\n");
		KeyField field = tableObject.getKeyField();
		builder.append(field.getKeyNullCheckString());
		builder.append("\t}\n");
		return builder.toString();
	}

	private String getToStringMethod(TableObject tableObject){
		StringBuilder builder = new StringBuilder("\t@Override\n\tpublic String toString(){\n");
		builder.append("\t\tStringBuilder str = new StringBuilder();\n");
		
		builder.append(tableObject.getKeyField().getToStringPrintValue() + ";\n" );
		
		List<FieldObject> fieldObjects = tableObject.getFieldObjects();
		for(FieldObject fieldObject : fieldObjects){
			builder.append("\t\tstr.append(" + fieldObject.getStringToPrintValue() + " + \"\\n\");\n");
		}
		builder.append("\t\treturn str.toString();\n");
		builder.append("\t}\n");
		return builder.toString();
	}

	  private String getValidateMethod(TableObject tableObject){
		StringBuilder builder = new StringBuilder("\tpublic void validate(boolean validatePk){\n");
		builder.append("\t\tresetValidationMessages();\n\n");
		builder.append("\t\tList<ValidationMessage> list = new ArrayList<ValidationMessage>();\n");
		
		builder.append("\t\tif(validatePk){\n");
		builder.append(tableObject.getKeyField().getValidateString());
		builder.append("\t\t}\n");
		
		//builder.append(tableObject.getKeyField().getValidateString());
		List<FieldObject> fieldObjects = tableObject.getFieldObjects();
		for(FieldObject fieldObject : fieldObjects){
			builder.append(fieldObject.getValidateString());
		}
		builder.append("\t\tif(list.size()>0)setHasValidationErrors(true);\n\n");
		builder.append("\t\tsetValidationMessages(list);\n");
		builder.append("\t\t\n");
		builder.append("\t}\n");
		return builder.toString();
	}
	  
	public void openClassNameDef(TableObject tableObject, Writer writer) {
		try {
			writer.write("@SuppressWarnings({ \"serial\"})\n");
			if(isUsingAnnotations())
				writer.write("@MappedSuperclass \n");
			
			StringBuilder builder = new StringBuilder("public abstract class Base");
			builder.append(tableObject.getTableClassName());
			builder.append(" extends BaseDomainObject<" );
			builder.append(tableObject.getKeyField().getKeyClass());
//			builder.append(tableObject.getKeyField().getKeyClass() + ", " + tableObject.getTableClassName());
			builder.append("> {\n");
			writer.write(builder.toString());
			//writer.write("public abstract class Base" + tableObject.getTableClassName() + " extends DomainObject<" + tableObject.getKeyField().getKeyClass() + "> implements Serializable, Cloneable{\n");
			if(tableObject.isView()){
				writeColumnMap(tableObject, writer);
			}
		} catch (IOException e) {
			throw new CodeGenException("Error openning class def");
		}
	}
	
	private void writeColumnMap(TableObject tableObject, Writer writer) throws IOException{
		StringBuilder builder = new StringBuilder("\tpublic static final Map<String, String> fieldToDbColMap = new HashMap<String, String>();\n\n");
		builder.append("\t static{ \n");
		//for(FieldObject fieldObject : tableObject.getKeyField().get)
		for(FieldObject fieldObject : tableObject.getFieldObjects()){
			builder.append("\t\tfieldToDbColMap.put(\"" + fieldObject.getJavaFieldName() + "\", \"" + fieldObject.getFieldName() + "\");\n");
		}
		builder.append("\t} \n");
		writer.write(builder.toString());
	}

	public void writeImports(CodeGenProperties codeGenProperties,TableObject tableObject, Writer writer) {
		try {
			writer.write("import java.io.Serializable; \n");
			writer.write("import java.util.ArrayList; \n");
			writer.write("import java.util.List; \n");
			
			if(tableObject.isView()){
				writer.write("import java.util.Map; \n");
				writer.write("import java.util.HashMap; \n");
			}
			
			if(tableObject.containsDataField()){
				writer.write("import java.util.Date; \n");
			}
			
			writer.write("import org.secondopinion." + tableObject.getSchemaName() + ".domain."+ tableObject.getTableClassName()+"; \n");
			if(tableObject.getKeyField().isCompositeKey()){
				writer.write("import org.secondopinion.dto."+ tableObject.getKeyField().getKeyClass()+"; \n");
			}
			writer.write("import org.secondopinion.utils.threadlocal.AppThreadLocal; \n");
			writer.write("import org.secondopinion.domain.KeyField; \n");
			writer.write("import org.secondopinion.domain.validation.ValidationMessage; \n");
			writer.write("import org.secondopinion.utils.StringUtil; \n");
			writer.write("import org.secondopinion.utils.ObjectUtil; \n\n");
						
			if(isUsingAnnotations()){
				writer.write("import javax.persistence.Column; \n");
				writer.write("import javax.persistence.MappedSuperclass; \n");
				writer.write("import javax.validation.constraints.NotNull; \n");
				if(!tableObject.getKeyField().isCompositeKey()){
					if(!tableObject.getKeyField().getKeyField().get(0).isString())
						writer.write("import javax.persistence.GeneratedValue; \n");
						writer.write("import javax.persistence.GenerationType; \n");
				}
				writer.write("import javax.persistence.Id; \n");
				if(tableObject.containsAnLOB()){
					writer.write("import javax.persistence.Lob; \n");
				}
				writer.write("import javax.persistence.Transient;\n");
				//writer.write("import javax.persistence.Version;\n");
				
				writer.write("import org.hibernate.validator.constraints.Length; \n");
			}
		} catch (IOException e) {
			throw new CodeGenException("Error writing  imports information");
		}
	}

	public void writePackageName(CodeGenProperties codeGenProperties,TableObject tableObject, Writer writer) {
		try {
			writer.write("package " + codeGenProperties.getBaseDataObjectPackage() + "; \n");
			writer.write("\n");
		} catch (IOException e) {
			throw new CodeGenException("Error creating package information");
		}
	}

	public void closeClassNameDef(TableObject tableObject, Writer writer) {
		try {
			writer.write("}");
		} catch (IOException e) {
			throw new CodeGenException("Error closing class def");
		}
	}
}
