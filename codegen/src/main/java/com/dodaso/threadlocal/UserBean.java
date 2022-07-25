package com.dodaso.threadlocal;

import java.util.Date;

/**
 * @author rswarna
 *
 */
public class UserBean {
	private int userId;
	private String userName;
	private Date logInDate;
	private Date requestStartDate;
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
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
	 * @return the logInDate
	 */
	public Date getLogInDate() {
		return logInDate;
	}
	/**
	 * @param logInDate the logInDate to set
	 */
	public void setLogInDate(Date logInDate) {
		this.logInDate = logInDate;
	}
	/**
	 * @return the requestStartDate
	 */
	public Date getRequestStartDate() {
		return requestStartDate;
	}
	/**
	 * @param requestStartDate the requestStartDate to set
	 */
	public void setRequestStartDate(Date requestStartDate) {
		this.requestStartDate = requestStartDate;
	}
}