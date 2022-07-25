package com.dodaso.codegen;

import java.io.Writer;

public interface ObjectWriter {
	String getFileName(CodeGenProperties codeGenProperties, TableObject object);
	void writePackageName(CodeGenProperties codeGenProperties, TableObject tableObject, Writer writer);
	void writeImports(CodeGenProperties codeGenProperties,TableObject tableObject, Writer writer);
	void openClassNameDef(TableObject tableObject, Writer writer);
	void writeBody(TableObject tableObject, Writer writer);
	void closeClassNameDef(TableObject tableObject, Writer writer);
	boolean isUsingAnnotations();
	void setUsingAnnotations(boolean useAnnotations);
}
