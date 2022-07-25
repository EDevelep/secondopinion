package com.dodaso.codegen.writers;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import com.dodaso.codegen.CodeGenProperties;
import com.dodaso.codegen.TableObject;
import com.dodaso.exception.CodeGenException;
import com.dodaso.util.FileUtil;

public class DomainObject  extends BaseObjectWriter{

	public void createDataObject(CodeGenProperties codeGenProperties, TableObject object) {
		String fileName = codeGenProperties.getDomainObjectDirectory()+ File.separator  + object.getTableClassName() + ".java";
		System.out.println("Creating file: " + fileName);
		Writer writer = FileUtil.getPrintWriter(fileName);
		writePackageName(codeGenProperties,object, writer);
		writeImports(codeGenProperties,object, writer);
		openClassNameDef(object, writer);
		writeBody(object, writer);
		closeClassNameDef(object, writer);
		
		FileUtil.closeWriter(writer);
	}
	
	public String getFileName(CodeGenProperties codeGenProperties, TableObject object) {
		return codeGenProperties.getDomainObjectDirectory()+ File.separator  + object.getTableClassName() + ".java";
	}
	
	public void writeBody(TableObject tableObject, Writer writer) {
	}

	public void openClassNameDef(TableObject tableObject, Writer writer) {
		try {
			writer.write("@SuppressWarnings({ \"serial\"})\n");
			if(isUsingAnnotations()){
				writer.write(tableObject.getTableAnnotation());
			}
			writer.write("public class " + tableObject.getTableClassName() + " extends Base"+ tableObject.getTableClassName() + "{\n");
		} catch (IOException e) {
			throw new CodeGenException("Error openning class def");
		}
	}

	public void writeImports(CodeGenProperties codeGenProperties,TableObject tableObject, Writer writer) {
		try {
			writer.write("import javax.persistence.Entity; \n");
			if(!tableObject.getKeyField().isCompositeKey())
				writer.write("import javax.persistence.SequenceGenerator; \n");
			writer.write("import javax.persistence.Table; \n");
			writer.write("\n");
			writer.write("import " + codeGenProperties.getBaseDataObjectPackage() + ".Base" + tableObject.getTableClassName()+"; \n");
			if(isUsingAnnotations()){
				writer.write("\n");
				//writer.write("import org.hibernate.validator.Length; \n");
				//writer.write("import org.hibernate.validator.NotNull; \n");
				//writer.write("import org.jboss.seam.annotations.Name; \n");
			}
			writer.write("\n\n");
		} catch (IOException e) {
			throw new CodeGenException("Error creating package information");
		}
	}

	public void writePackageName(CodeGenProperties codeGenProperties,TableObject tableObject, Writer writer) {
		try {
			writer.write("package " + codeGenProperties.getDataObjectPackage()+"; \n");
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
