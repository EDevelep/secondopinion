package com.dodaso.codeGenerators;

import java.sql.Connection;

import com.dodaso.codegen.TableObject;
import com.dodaso.codegen.util.DbViewInfo;

public class ViewCodeGenerator extends CodeGenerator{
	
	private String[] keyFields;

	/**
	 * @return the keyFields
	 */
	public String[] getKeyFields() {
		return keyFields;
	}

	/**
	 * @param keyFields the keyFields to set
	 */
	public void setKeyFields(String[] keyFields) {
		this.keyFields = keyFields;
	}

	public ViewCodeGenerator(String sourceDir, boolean useAnnotations, boolean regenerate) {
		super(sourceDir, useAnnotations, regenerate);
	}

	public ViewCodeGenerator(String sourceDir, boolean regenerate) {
		super(sourceDir, regenerate);
	}

	public ViewCodeGenerator(String sourceDir) {
		super(sourceDir);
	}
	
	public ViewCodeGenerator() {
		super();
	}

	@Override
	public TableObject getDbObjectInfo(String schemaName, String objectName, Connection connection) {
		DbViewInfo viewInfo = new DbViewInfo();
		viewInfo.setKeyFields(keyFields);
		
		TableObject object = viewInfo.getMetaDataInfo(schemaName, objectName, connection, getAuditFieldDefinitions());
		object.setView(true);
		
		return object;
	}
}
