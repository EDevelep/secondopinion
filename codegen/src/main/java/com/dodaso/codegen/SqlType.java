package com.dodaso.codegen;

import com.dodaso.exception.CodeGenException;


public enum SqlType {
	BOOLEAN, BYTE, VARCHAR2, NUMBER, DATE, CHAR, LONG, DOUBLE, TIMESTAMP, FLOAT, OBJECT;

	/*public static SqlType getSqlType(String type) {
		SqlType sqlType = null;
		if ("VARCHAR2".equalsIgnoreCase(type)) {
			sqlType = SqlType.VARCHAR2;
		} else if ("NUMBER".equalsIgnoreCase(type)) {
			sqlType = SqlType.NUMBER;
		} else if ("DATE".equalsIgnoreCase(type)) {
			sqlType = SqlType.DATE;
		} else if ("LONG".equalsIgnoreCase(type)) {
			sqlType = SqlType.LONG;
		} else if ("CHAR".equalsIgnoreCase(type)) {
			sqlType = SqlType.CHAR;
		} else if (type.startsWith("TIMESTAMP")) {
			sqlType = SqlType.DATE;
		} else {
			throw new CodeGenException("Data type needs to be implemented: "
					+ type);
		}

		return sqlType;
	}*/

	public static SqlType getSqlType(FieldObject fieldObject) {
		SqlType returnValue; 
		switch (fieldObject.getSqlDbDataType()) {
		case java.sql.Types.BOOLEAN:
		case java.sql.Types.BIT:
			returnValue = BOOLEAN;
			break;

		case java.sql.Types.TINYINT:
			returnValue = BYTE;
			break;
		case java.sql.Types.SMALLINT:
			returnValue = NUMBER;
			break;

		case java.sql.Types.INTEGER:
			if (fieldObject.isSigned()) {
				returnValue = NUMBER;
			} else {
				returnValue = LONG;
			}
			break;

		case java.sql.Types.BIGINT:
			returnValue = LONG;
			break;
		// Removed from the lower group since mediumIn returns a decimal
		case java.sql.Types.DECIMAL:
		case java.sql.Types.DOUBLE:
		case java.sql.Types.FLOAT:
		case java.sql.Types.REAL:
			returnValue = DOUBLE;
			break;

		case java.sql.Types.NUMERIC:
			returnValue = FLOAT;
			break;

		case java.sql.Types.CHAR:
			returnValue = CHAR;
			break;

		case java.sql.Types.VARCHAR:
		case java.sql.Types.LONGVARCHAR:
			/*
			 * case java.sql.Types.NCHAR: case java.sql.Types.NVARCHAR: case
			 * java.sql.Types.LONGNVARCHAR:
			 */
			returnValue = VARCHAR2;
			break;

		case java.sql.Types.DATE:
		case java.sql.Types.TIME:
		case java.sql.Types.TIMESTAMP:
			returnValue = DATE;
			break;

		case java.sql.Types.BINARY:
		case java.sql.Types.VARBINARY:
		case java.sql.Types.LONGVARBINARY:
			/*
			 * case java.sql.Types.ROWID: case java.sql.Types.NCLOB: case
			 * java.sql.Types.SQLXML:
			 */
		case java.sql.Types.NULL:
		case java.sql.Types.OTHER:
		case java.sql.Types.JAVA_OBJECT:
		case java.sql.Types.DISTINCT:
		case java.sql.Types.STRUCT:
		case java.sql.Types.ARRAY:
		case java.sql.Types.BLOB:
		case java.sql.Types.CLOB:
		case java.sql.Types.REF:
		case java.sql.Types.DATALINK:
			returnValue = OBJECT;
			break;

		default:
			returnValue = OBJECT;
			break;
		}
		
		return returnValue;
	}

	public static int getJavaType(SqlType type) {
		switch (type) {
		case VARCHAR2:
			return java.sql.Types.VARCHAR;
		case CHAR:
			return java.sql.Types.CHAR;
		case NUMBER:
			return java.sql.Types.INTEGER;
		case LONG:
			return java.sql.Types.BIGINT;
		case DATE:
			return java.sql.Types.DATE;
		case TIMESTAMP:
			return java.sql.Types.DATE;
		default:
			throw new CodeGenException("Data type needs to be implemented: " + type);
		}
	}

	public boolean isLong() {
		if (LONG == this) {
			return true;
		} else {
			return false;
		}
	}
}
