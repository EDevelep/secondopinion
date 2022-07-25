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

public class BaseKeyObject extends BaseDomainObject {

	public String getFileName(CodeGenProperties codeGenProperties, TableObject object) {
		return codeGenProperties.getBaseDomainObjectDirectory() + File.separator + "Base" + object.getTableClassName() + "Key.java";		
	}

	public void openClassNameDef(TableObject tableObject, Writer writer) {
		try {
			writer.write("@SuppressWarnings({ \"serial\"})\n");
			if(isUsingAnnotations()){
				writer.write("@Embeddable\n");
				writer.write("@MappedSuperclass\n");
			}
			writer.write("public abstract class Base" + tableObject.getTableClassName() + "Key implements Serializable{\n");
		} catch (IOException e) {
			throw new CodeGenException("Error openning class def");
		}
	}
	
	@Override
	public void writeImports(CodeGenProperties codeGenProperties,TableObject tableObject, Writer writer) {
		try {
			writer.write("import java.io.Serializable; \n");
			writer.write("import java.util.ArrayList; \n");
			writer.write("import java.util.List; \n");
			if(tableObject.containsDataField()){
				writer.write("import java.util.Date; \n");
			}
			
			if(isUsingAnnotations()){
				writer.write("import javax.persistence.Column; \n");
				writer.write("import javax.persistence.Embeddable; \n");
				writer.write("import javax.persistence.MappedSuperclass; \n");
				writer.write("import javax.persistence.Transient; \n");
			}
			writer.write("\n");
			writer.write("import javax.validation.constraints.NotNull;");
//			writer.write("import org.hibernate.validator.Length; \n");
//			writer.write("import org.hibernate.validator.NotNull; \n");
			writer.write("import org.hibernate.criterion.Criterion; \n");
			writer.write("import org.hibernate.criterion.Restrictions; \n\n");
			
			writer.write("import com.dodaso.domain.KeyField; \n");
			writer.write("import com.dodaso.dto." + tableObject.getKeyField().getKeyClass() +" ;\n");
			writer.write("import com.dodaso.domain.validation.ValidationMessage; \n");
			writer.write("import com.dodaso.utils.StringUtil; \n");
			writer.write("import com.dodaso.utils.ObjectUtil; \n\n");
		} catch (IOException e) {
			throw new CodeGenException("Error writing  imports information");
		}
	}

	public void writeBody(TableObject tableObject, Writer writer) {
		try {
			writer.write("\n");
			writer.write(tableObject.getKeyField().getFieldName(false));
			writer.write("\n");
			writer.write(tableObject.getKeyField().getFieldDef());
			writer.write("\n");
			writeSetterAndGetter(tableObject.getKeyField().getKeyField(), writer);
			writer.write("\n");
			writeGetCriterionMethod(tableObject.getKeyField(), writer);
			writer.write("\n");
			writer.write(getGetKeyFieldMethod(tableObject.getKeyField()));
			writer.write("\n\n");
			writer.write(tableObject.getKeyField().getEqualsMethod(tableObject.getTableClassName()));
			writer.write("\n");
			writer.write(tableObject.getKeyField().getHashCodeMethod());
			writer.write("\n");
			writer.write(CodeGenUtil.getValidateMethod(tableObject.getKeyField().getKeyField()));
			writer.write("\n");
			writer.write(CodeGenUtil.getToStringMethod(tableObject.getKeyField().getKeyField()));
		} catch (IOException e) {
			throw new CodeGenException("Error openning class def");
		}
		
	}
	
	private void writeGetCriterionMethod(KeyField keyField, Writer writer){
		try {
			if(isUsingAnnotations()){
				writer.write("\t@Transient \n");
			}
			writer.write("\tpublic Criterion getCriterion(){\n");
			//writer.write("\t\treturn Restrictions.and( \n");
			if(keyField.getKeyField().size() > 2){
				StringBuilder builder = new StringBuilder();
				writer.write("\t\tCriterion criterion = Restrictions.and( \n");
				builder.append("\t\t\t" + keyField.getKeyField().get(0).getRestrictionForEquals() + ",\n");
				builder.append("\t\t\t" + keyField.getKeyField().get(1).getRestrictionForEquals() + ");\n");
				for(int i=2; i<keyField.getKeyField().size(); i++){;
					builder.append("\t\t\tcriterion = Restrictions.and(criterion, \n");
					builder.append("\t\t\t\t" +  keyField.getKeyField().get(i).getRestrictionForEquals() + ");\n");
				}
				builder.append("\t\t\n");
				builder.append("\t\treturn criterion;\n");
				writer.write(builder.toString());
				
			}else{
				writer.write("\t\treturn Restrictions.and( \n");
				writer.write("\t\t\t" + keyField.getKeyField().get(0).getRestrictionForEquals() + ",\n");
				writer.write("\t\t\t" + keyField.getKeyField().get(1).getRestrictionForEquals() + ");\n");
			}
			
			writer.write("\t}\n");
		} catch (IOException e) {
			throw new CodeGenException("Error openning class def");
		}
	}
	
	private String getGetKeyFieldMethod(KeyField keyField) {
		StringBuilder builder = new StringBuilder();
		if(isUsingAnnotations()){
			builder.append("\t@Transient \n");
		}
		builder.append("\tpublic List<KeyField> getKeyField() {\n");
		builder.append("\t\tList<KeyField> keyFields = new ArrayList<KeyField>();\n\n");
		for(FieldObject fieldObject : keyField.getKeyField()){
			builder.append("\t\tkeyFields.add(new KeyField(" +  fieldObject.getFieldConstantName() +","+ fieldObject.getGetterMethodCall() +"));\n");
		}
		builder.append("\t\treturn keyFields; \n");
		builder.append("\t}\n");
		
		return builder.toString();
	}
	
	protected void writeSetterAndGetter(List<FieldObject> list, Writer writer) {
		try {
			for (FieldObject fieldObject : list) {
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

}
