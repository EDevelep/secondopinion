package com.dodaso.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.RowSet;

import com.dodaso.exception.CodeGenException;

public class DbUtil {

	public static ResultSet executeStatement(Statement statement, String sql) {
		try {
			return statement.executeQuery(sql);
		} catch (SQLException e) {
			throw new CodeGenException("Error executing statement."
					+ e.getMessage(), e);
		}
	}

	public static void close(ResultSet rs, Statement st, Connection connection) {
		closeResultSet(rs);
		close(st, connection);
	}

	public static void close(Statement st, Connection connection) {
		closeStatement(st);
		closeConnection(connection);
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			// TODO: Log the exception
			e.printStackTrace();
		}
	}

	public static void closeRowSet(RowSet rs) {
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			// TODO: Log the exception
			e.printStackTrace();
		}
	}
	
	public static void closeStatement(Statement statement) {
		try {
			if(statement != null)
				statement.close();
		} catch (SQLException e) {
			// TODO: Log the exception
			e.printStackTrace();
		}
	}

	public static void closeConnection(Connection connection) {
		try {
			if(connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO: Log the exception
			e.printStackTrace();
		}
	}

	public static PreparedStatement getPreparedStatement(Connection connection, String sqlString) {
		try {
			return connection.prepareStatement(sqlString);
		} catch (SQLException e) {
			throw new CodeGenException("Error preparing statement: "
					+ e.getMessage(), e);
		}
	}
	
	 /**
     * Runs the query using the given dataSource. Returns the result in disconnected RowSet.
     *
     * @param query
     * @return
     */
    public static ResultSet runQuery(Connection connection, String sqlString, Object[] parameters)
    {
        try
        {
//        	OracleCachedRowSet rowSetImpl = new OracleCachedRowSet();
        	PreparedStatement statement = getPreparedStatement(connection, sqlString, parameters);
        	return statement.executeQuery();
//        	rowSetImpl.populate(resultSet);
//        	
//        	return rowSetImpl;
        }
        catch (Exception exception)
        {
            throw new CodeGenException(
                    "Error Processing SQL Query: " + sqlString + ", parameters: " + parameters, exception);
        }
    }

	private static PreparedStatement getPreparedStatement(Connection connection, String sqlString, Object[] parameters){
		try{
			PreparedStatement preparedStatement = getPreparedStatement(connection, sqlString);
			Object object;
			if (parameters != null) {
				for (int i = 0, size = parameters.length; i < size; i++) {
					object = parameters[i];
					// Cast java.util.Date to java.sql.Date, as cannot set
					// parameters using the java.util.Date
					if (object instanceof java.util.Date) {
						object = new Timestamp(((java.util.Date) object).getTime());
					}
					preparedStatement.setObject(i + 1, object);
				}
			}
			return preparedStatement;
		}catch (SQLException e) {
			throw new CodeGenException("Error preparing statement: "
					+ e.getMessage(), e);
		}
	}

}
