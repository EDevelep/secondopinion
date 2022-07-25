package com.dodaso.codegen.writers;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import com.dodaso.codegen.CodeGenProperties;
import com.dodaso.codegen.TableObject;
import com.dodaso.exception.CodeGenException;

public class DAOInterfaceObject extends BaseObjectWriter {

	public String getFileName(CodeGenProperties codeGenProperties, TableObject object) {
		return codeGenProperties.getDaoIntefaceDirectory() + File.separator + object.getTableClassName() + "DAO.java";
	}
	
	public void writeBody(TableObject tableObject, Writer writer) {
	}

	public void openClassNameDef(TableObject tableObject, Writer writer) {
		try {
			writer.write("public interface " + tableObject.getTableClassName() + "DAO extends IDAO<" + tableObject.getTableClassName() + "," + tableObject.getKeyField().getKeyClass()+ " >{\n");
		} catch (IOException e) {
			throw new CodeGenException("Error openning class def");
		}
	}

	public void writeImports(CodeGenProperties codeGenProperties, TableObject tableObject, Writer writer) {
		try {
//			writer.write("import com.dodaso.dao.IDAO;");
			writer.write("import com.dodaso.dto." + tableObject.getTableClassName() + ";\n");
			if(tableObject.getKeyField().isCompositeKey()){
				writer.write("import com.dodaso.dto." + tableObject.getKeyField().getKeyClass() + ";\n");
			}
			writer.write("\n");
		} catch (IOException e) {
			throw new CodeGenException("Error creating package information");
		}
	}

	public void writePackageName(CodeGenProperties codeGenProperties,TableObject tableObject, Writer writer) {
		try {
			writer.write("package "+ codeGenProperties.getDaoInterfacePackage() + "; \n");
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
