package com.dodaso.codegen.util;

import java.sql.Connection;

import com.dodaso.codegen.AuditFileldDefinition;
import com.dodaso.codegen.TableObject;

public interface DbObjectInfo {

	public TableObject getMetaDataInfo(String schemaName, String objectName, Connection connection, AuditFileldDefinition auditFileldDefinition);
	
}
