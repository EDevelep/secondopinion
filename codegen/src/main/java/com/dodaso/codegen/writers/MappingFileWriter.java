package com.dodaso.codegen.writers;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import com.dodaso.codegen.CodeGenProperties;
import com.dodaso.codegen.FieldObject;
import com.dodaso.codegen.KeyField;
import com.dodaso.codegen.TableObject;
import com.dodaso.exception.CodeGenException;
import com.dodaso.util.FileUtil;

/**
 * @author rswarna
 *
 */
public class MappingFileWriter  extends BaseObjectWriter{
	private static final String MAPPING_XML_HEADER_DOC_TYPE = "<?xml version=\"1.0\"?>\n" +
			"<!DOCTYPE hibernate-mapping PUBLIC \n" +
			" \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\" \n" +
			" \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\"> \n";
	CodeGenProperties codeGenProperties;

	public void createMappingFile(CodeGenProperties codeGenProperties, TableObject object) {
		String fileName = codeGenProperties.getMappingDirectory() + File.separator + object.getTableClassName()+ ".hbm.xml";
		Writer writer = FileUtil.getPrintWriter(fileName);
		writePackageName(codeGenProperties,object, writer);
		writeImports(codeGenProperties,object, writer);
		openClassNameDef(object, writer);
		writeBody(object, writer);
		closeClassNameDef(object, writer);
		FileUtil.closeWriter(writer);
	} 
	
	public String getFileName(CodeGenProperties codeGenProperties, TableObject object) {
		this.codeGenProperties = codeGenProperties;
		return codeGenProperties.getMappingDirectory() + File.separator + object.getTableClassName()+ ".hbm.xml";
	}
	
	public void closeClassNameDef(TableObject tableObject, Writer writer) {
		try{
			writer.write("\t</class>\n");
		    writer.write("</hibernate-mapping>");
		} catch (IOException e) {
			throw new CodeGenException("Error creating hibernate mapping file");
		}
	}

	public void openClassNameDef(TableObject tableObject, Writer writer) {
		try{
			
		    writer.write("\t<class name=\"" + tableObject.getTableClassName() + "\" table=\"" + tableObject.getTableName() +"\">\n");
		} catch (IOException e) {
			throw new CodeGenException("Error creating hibernate mapping file");
		}
	}

	public void writeBody(TableObject tableObject, Writer writer) {
		writeIdInfo(codeGenProperties, tableObject.getKeyField(), writer);
		
		for(FieldObject fieldObject : tableObject.getFieldObjects()){
			try {
				writer.write("\t\t<property name=\"" + fieldObject.getJavaFieldName()+ "\" column=\"" + fieldObject.getFieldName()+ "\"/>\n");
			} catch (IOException e) {
				throw new CodeGenException("Error creating hibernate mapping file");
			}
		}
	}
	
	private void writeIdInfo(CodeGenProperties codeGenProperties, KeyField keyField, Writer writer){
		try{	
			writer.write(keyField.getIdInfoForHibernateMapping(codeGenProperties));
		} catch (IOException e) {
			throw new CodeGenException("Error creating hibernate mapping file");
		}
	}
	
	public void writeImports(CodeGenProperties codeGenProperties, TableObject tableObject, Writer writer) {
		try {
			writer.write("<hibernate-mapping package=\""+codeGenProperties.getDataObjectPackage()+"\">\n");
		} catch (IOException e) {
			throw new CodeGenException("Error creating hibernate mapping file");
		}
	}

	public void writePackageName(CodeGenProperties codeGenProperties,  TableObject tableObject, Writer writer) {
		try {
			writer.write(MAPPING_XML_HEADER_DOC_TYPE);
			writer.write("\n");
		} catch (IOException e) {
			throw new CodeGenException("Error creating hibernate mapping file");
		}
	}
}
