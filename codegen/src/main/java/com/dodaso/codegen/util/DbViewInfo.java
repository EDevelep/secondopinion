package com.dodaso.codegen.util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dodaso.codegen.AuditFileldDefinition;
import com.dodaso.codegen.TableObject;
import com.dodaso.exception.CodeGenException;
import com.dodaso.util.StringUtil;

public class DbViewInfo implements DbObjectInfo {

	private List<String> keyFields = new ArrayList<String>();

	/**
	 * @return the keyFields
	 */
	public List<String> getKeyFields() {
		return keyFields;
	}

	/**
	 * @param keyFields
	 *            the keyFields to set
	 */
	public void setKeyFields(List<String> keyFields) {
		this.keyFields = keyFields;
	}
	
	/**
	 * @param keyFields
	 *            the keyFields to set
	 */
	public void setKeyFields(String[] keyFields) {
		this.keyFields = Arrays.asList(keyFields);
	}

	public TableObject getMetaDataInfo(String schemaName, String objectName, Connection connection, AuditFileldDefinition auditFileldDefinition) {
		if (StringUtil.isNullOrEmpty(objectName)) 
			throw new CodeGenException("View name cannot be empty: ");
		
		if(keyFields.size() == 0)
			throw new CodeGenException("Key field names for View should be provided: ");
		
		try {
			
			TableObject tableObject = new TableObject(objectName, schemaName);
			DbUtil.getSqlDataType(tableObject, connection, auditFileldDefinition);
			
			DbUtil.setPrimaryKeyField(tableObject, connection, keyFields);
			return tableObject;
			
		} catch (Exception ex) {
			throw new CodeGenException("Error reading table information: " + ex.getMessage(), ex);
		} 
	}
}
