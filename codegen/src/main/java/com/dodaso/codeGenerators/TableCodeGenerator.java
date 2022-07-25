package com.dodaso.codeGenerators;

import java.sql.Connection;

import com.dodaso.codegen.TableObject;
import com.dodaso.codegen.util.DbTableInfo;

public class TableCodeGenerator extends CodeGenerator{

	public TableCodeGenerator(String sourceDir, boolean useAnnotations,
			boolean regenerate) {
		super(sourceDir, useAnnotations, regenerate);
	}

	public TableCodeGenerator(String sourceDir, boolean regenerate) {
		super(sourceDir, regenerate);
	}

	public TableCodeGenerator(String sourceDir) {
		super(sourceDir);
	}

	public TableCodeGenerator() {
		super();
	}

	@Override
	public TableObject getDbObjectInfo(String schemaName, String objectName, Connection connection) {
		DbTableInfo dbTableInfo = new DbTableInfo();
		
		TableObject object = dbTableInfo.getMetaDataInfo(schemaName, objectName, connection, getAuditFieldDefinitions());
		return object;
	}
}
