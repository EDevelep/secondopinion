package com.dodaso.codegen.writers;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import com.dodaso.codegen.CodeGenProperties;
import com.dodaso.codegen.TableObject;
import com.dodaso.exception.CodeGenException;
import com.dodaso.util.FileUtil;

public class DAOObject  extends BaseObjectWriter {

	public void createDataObject(CodeGenProperties codeGenProperties, TableObject object) {
		String fileName = codeGenProperties.getDaoImplDirectory() + File.separator + object.getTableClassName() + "DAOHibernateImpl.java";
		//System.out.println("Creating file: " + fileName);
		Writer writer = FileUtil.getPrintWriter(fileName);
		writePackageName(codeGenProperties, object, writer);
		writeImports(codeGenProperties, object, writer);
		openClassNameDef(object, writer);
		writeBody(object, writer);
		closeClassNameDef(object, writer);
		
		FileUtil.closeWriter(writer);
	}

	public String getFileName(CodeGenProperties codeGenProperties, TableObject object) {
		return codeGenProperties.getDaoImplDirectory() + File.separator + object.getTableClassName() + "DAOHibernateImpl.java";
	}
	
	public void writeBody(TableObject tableObject, Writer writer) {
	}

	public void openClassNameDef(TableObject tableObject, Writer writer) {
		try {
			writer.write("public class " + tableObject.getTableClassName() + "DAOHibernateImpl extends Base"+tableObject.getTableClassName()+"DAOHibernate{\n");
		} catch (IOException e) {
			throw new CodeGenException("Error openning class def");
		}
	}

	public void writeImports(CodeGenProperties codeGenProperties, TableObject tableObject, Writer writer) {
		try {
			writer.write("import " + codeGenProperties.getBaseDaoPackage() + ".Base"+tableObject.getTableClassName()+"DAOHibernate;");
			writer.write("\n\n");
		} catch (IOException e) {
			throw new CodeGenException("Error creating package information");
		}
	}

	public void writePackageName(CodeGenProperties codeGenProperties, TableObject tableObject, Writer writer) {
		try {
			writer.write("package " + codeGenProperties.getDaoImplPackage() +"; \n");
			writer.write("\n\n");
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
