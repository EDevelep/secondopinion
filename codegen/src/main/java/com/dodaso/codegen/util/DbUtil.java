package com.dodaso.codegen.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.RowSet;

import com.dodaso.codegen.AuditFileldDefinition;
import com.dodaso.codegen.FieldObject;
import com.dodaso.codegen.KeyField;
import com.dodaso.codegen.TableObject;
import com.dodaso.exception.CodeGenException;
import com.dodaso.util.StringUtil;

public class DbUtil extends com.dodaso.util.DbUtil {

	static void getSqlDataType(TableObject object, Connection connection, AuditFileldDefinition auditFileldDefinition){
		String sqlString = "select * from " + object.getTableNameToQuery()+" where 1 = 2";
		ResultSet rowSet = null; 
		try {
			rowSet = runQuery(connection, sqlString, null);
			ResultSetMetaData metaData = rowSet.getMetaData();
			int colCount = metaData.getColumnCount();
			
			for(int i = 1; i<=colCount; i++){
				FieldObject fieldObject = new FieldObject();
				
				fieldObject.setFieldName(metaData.getColumnName(i));
				fieldObject.setSqlDbDataType(metaData.getColumnType(i));
				fieldObject.setJavaDataType(metaData.getColumnType(i));
				fieldObject.setNullable(metaData.isNullable(i)==ResultSetMetaData.columnNullable );
				fieldObject.setSigned(metaData.isSigned(i));
				fieldObject.setPrecesion(metaData.getPrecision(i));
				fieldObject.setScale(metaData.getScale(i));
				fieldObject.setDataLength(metaData.getColumnDisplaySize(i));
				
				fieldObject.setAuditField(auditFileldDefinition.isAuditField(fieldObject.getJavaFieldName()));
				fieldObject.setVersionField(auditFileldDefinition.isVersionField(fieldObject.getJavaFieldName()));
				fieldObject.setAuditFieldForCreate(auditFileldDefinition.isAuditFieldForCreate(fieldObject.getJavaFieldName()));
				
				object.addField(fieldObject);
			}
		}catch(SQLException ex) {
			throw new CodeGenException("Error reading table information: " + ex.getMessage(), ex);
		} finally {
			closeResultSet(rowSet);
		}
	}
	
	public static TableObject getMetaDataInfo(String schemaName, String tableName, Connection connection, AuditFileldDefinition auditFileldDefinition, 
			boolean view, String[] viewKeyFields) {
		RowSet rowSet = null;
		if(StringUtil.isNullOrEmpty(tableName)){
			throw new CodeGenException("Table name cannot be empty: ");
		}
		
		try {
			TableObject tableObject = new TableObject(schemaName, tableName);
			getSqlDataType(tableObject, connection, auditFileldDefinition);
			setPrimaryKeyField(tableObject, connection, view, viewKeyFields);
			
			return tableObject;
		} catch (Exception ex) {
			throw new CodeGenException("Error reading table information: " + ex.getMessage(), ex);
		} finally {
			closeRowSet(rowSet);
		}
	}

	static void setPrimaryKeyField(TableObject table, Connection connection, boolean view, String[] viewKeyFields) {
		List<String> keys = null;
		if(view){
			keys = Arrays.asList(viewKeyFields);
		}else{
			keys = getKey(table.getSchemaName(), table.getTableNameToQuery(), connection);
		}

		if (keys == null  || keys.size()==0) {
			throw new CodeGenException("No Primary key defined for table: " + table.getTableNameToQuery());
		}
		
		setPrimaryKeyField(table, connection, keys);
	}
	
	protected static void setPrimaryKeyField(TableObject table, Connection connection, List<String> keys ) {
		KeyField field = new KeyField(table.getTableClassName());
		for(String key : keys){
			for (FieldObject object : table.getFieldObjects()) {
				if (StringUtil.equalsIgnoreCase(key, object.getFieldName())) {
					field.addField(object);
					break;
				}
			}
		}
		table.setKeyField(field);
		
		List<FieldObject> list = table.getFieldObjects();
		for (FieldObject object : field.getKeyField()){
			if(list.contains(object)){
				list.remove(object);
			}
		}
		
		table.setFieldObjects(list);
	}

//		MS_SQL - 
//			SELECT [name]
//		  	FROM syscolumns 
//		  	WHERE [id] IN (SELECT [id] 
//		                   FROM sysobjects 
//		                  WHERE [name] = @table_name)
//		    AND colid IN (SELECT SIK.colid 
//		                    FROM sysindexkeys SIK 
//		                    JOIN sysobjects SO ON SIK.[id] = SO.[id]  
//		                   WHERE SIK.indid = 1
//		                     AND SO.[name] = @table_name)

	static List<String> getKey(String schemaName, String tableName, Connection connection) {
//		String sqlString = "select a.column_name as COLUMN_NAME  from user_cons_columns a, user_constraints b "
//				+ " where a.table_name = ? and a.constraint_name = b.constraint_name and b.constraint_type = 'P'";
		String sqlString = "SELECT column_name, column_type, column_key, extra FROM INFORMATION_SCHEMA.columns where table_schema='" + schemaName + "' and table_name=? and column_key = 'PRI'";
		List<String> list = getColumnNames(sqlString, tableName, connection);
		
		if(list == null || list.size() == 0){
			 sqlString = "select a.column_name as COLUMN_NAME  from user_cons_columns a, user_constraints b "
				+ " where a.table_name = ? and a.constraint_name = b.constraint_name and b.constraint_type = 'U'";
			 list = getColumnNames(sqlString, tableName, connection);
		}
		return list;
	}

	static List<String> getColumnNames(String sqlString, String tableName, Connection connection){
		List<String> list = new ArrayList<String>();
		ResultSet rowSet = null;
		try {
			rowSet = runQuery(connection, sqlString, new Object[] { tableName });
			String keyColumn = "";

			while (rowSet.next()) {
				keyColumn = rowSet.getString("COLUMN_NAME");
				list.add(keyColumn);
			}
			return list;
		} catch (SQLException ex) {
			throw new CodeGenException("Error reading table information: "
					+ ex.getMessage(), ex);
		} finally {
			closeResultSet(rowSet);
		}
	}
}
