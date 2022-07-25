package com.dodaso.codegen.writers;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import com.dodaso.codegen.CodeGenProperties;
import com.dodaso.codegen.TableObject;
import com.dodaso.exception.CodeGenException;
import com.dodaso.util.FileUtil;
import com.dodaso.util.StringUtil;

public class BaseDAOObject extends BaseObjectWriter {
	
	private String fileContents =null;
	
	public BaseDAOObject() {
		fileContents = FileUtil.getFileContentsAsString(BaseDAOObject.class.getResource("BaseHibernateDao.txt").getFile());
	}

	public String getFileName(CodeGenProperties codeGenProperties, TableObject object) {
		return codeGenProperties.getBaseDaoDirectory() + File.separator  + "Base" +object.getTableClassName() + "DAOHibernate.java";		
	}
	
	public void writeBody(TableObject tableObject, Writer writer) {
		fileContents = StringUtil.replace(fileContents, "$$TABLE$$", tableObject.getTableClassName());
		fileContents = StringUtil.replace(fileContents, "$$KEYCLASS$$", tableObject.getKeyField().getKeyClass());
	}

	public void openClassNameDef(TableObject tableObject, Writer writer) {
	}

	public void writeImports(CodeGenProperties codeGenProperties, TableObject tableObject, Writer writer) {
		String domainObject = codeGenProperties.getDataObjectPackage() + ".*";
		fileContents = StringUtil.replace(fileContents, "$$DOMAIN_OBJECT$$", domainObject);
	}

	public void writePackageName(CodeGenProperties codeGenProperties, TableObject tableObject, Writer writer) {
		fileContents = StringUtil.replace(fileContents, "$$PACKAGE$$", codeGenProperties.getBaseDaoPackage());
		//fileContents = fileContents.replaceAll("(?:$$PACKAGE$$)+", codeGenProperties.getBaseDaoPackage());
	}

	public void closeClassNameDef(TableObject tableObject, Writer writer) {
		try {
			writer.write(fileContents);
		} catch (IOException e) {
			throw new CodeGenException("Error closing class def");
		}
	}
}
