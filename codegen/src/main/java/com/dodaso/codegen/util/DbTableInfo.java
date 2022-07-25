package com.dodaso.codegen.util;

import java.sql.Connection;

import com.dodaso.codegen.AuditFileldDefinition;
import com.dodaso.codegen.TableObject;
import com.dodaso.exception.CodeGenException;
import com.dodaso.util.StringUtil;

public class DbTableInfo implements DbObjectInfo{

	public TableObject getMetaDataInfo(String schemaName, String objectName, Connection connection, AuditFileldDefinition auditFileldDefinition) {
		
		if(StringUtil.isNullOrEmpty(objectName))
			throw new CodeGenException("Table name cannot be empty: ");
		
		try {
			
			TableObject tableObject = new TableObject(schemaName, objectName);
			DbUtil.getSqlDataType(tableObject, connection, auditFileldDefinition);
			DbUtil.setPrimaryKeyField(tableObject, connection, false, null);
			
			return tableObject;
			
		} catch (Exception ex) {
			throw new CodeGenException("Error reading table information: " + ex.getMessage(), ex);
		} 
	}
}
