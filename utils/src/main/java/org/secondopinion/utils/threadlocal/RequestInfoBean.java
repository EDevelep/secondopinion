package org.secondopinion.utils.threadlocal;

import java.util.Date;

/**
 * Created by anil on 19/6/17.
 */
public class RequestInfoBean {
	private Long userId;
	private String userName;
	private Date logInDate;
	private Date requestStartDate;
	private String sessionId;
	private String moduleType;

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
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

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;

	}

	public String getModuleType() {
		return moduleType;
	}

}
