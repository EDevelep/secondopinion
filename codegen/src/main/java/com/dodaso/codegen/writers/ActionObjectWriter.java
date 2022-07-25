package com.dodaso.codegen.writers;

import java.io.File;
import java.io.Writer;

import com.dodaso.codegen.CodeGenProperties;
import com.dodaso.codegen.TableObject;
import com.dodaso.util.FileUtil;

public class ActionObjectWriter extends BaseObjectWriter {

	@SuppressWarnings("unused")
	private String fileContents = null;

	public ActionObjectWriter() {
		fileContents = FileUtil.getFileContentsAsString(BaseDAOObject.class
				.getResource("Action.txt").getFile());
	}

	public void closeClassNameDef(TableObject tableObject, Writer writer) {
	}

	public String getFileName(CodeGenProperties codeGenProperties,
			TableObject object) {
		return codeGenProperties.getBaseDaoDirectory() + File.separator
				+ object.getTableClassName() + "Action.java";
	}

	public void openClassNameDef(TableObject tableObject, Writer writer) {

	}

	public void writeBody(TableObject tableObject, Writer writer) {
	}

	public void writeImports(CodeGenProperties codeGenProperties,
			TableObject tableObject, Writer writer) {
	}

	public void writePackageName(CodeGenProperties codeGenProperties,
			TableObject tableObject, Writer writer) {
	}

}
