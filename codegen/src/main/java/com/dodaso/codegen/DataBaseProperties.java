package com.dodaso.codegen;

import java.sql.Connection;
import java.sql.DriverManager;

import com.dodaso.exception.CodeGenException;

public class DataBaseProperties {
	private final String url;
	private String userName;
	private String password;
	
	private Connection connection = null;
	
	public DataBaseProperties() {
		url = null;	
	}
	
	public DataBaseProperties(String url) {
		this.url = url;	
	}
	
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public Connection getConnection(){
		if(connection == null){
			prepareDataSource();
		}
		
		return connection;
	}

	private void prepareDataSource() {
		 String driverName = "com.mysql.jdbc.Driver";
		 try{
			 String url = this.url;
			 if (url == null) {
				 url = "jdbc:mysql://localhost:3306/bbuy";
			 }
			 Class.forName(driverName);
			 connection = DriverManager.getConnection(url, userName, password);
		 }catch(Exception ex){
			 throw new CodeGenException("Error loading drivers: " + ex.getMessage(), ex);
		 }
	}
}
