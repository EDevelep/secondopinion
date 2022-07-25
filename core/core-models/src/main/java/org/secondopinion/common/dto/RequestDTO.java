
package org.secondopinion.common.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class RequestDTO {

	private Date requestStartTime;

	private Date requestEndTime;
	private String excutionUrl;
	private String requestUrl;
	private String moduleType;
	private Long userId;
	private String ipAddress;
	private String userName;
	private Long totalTimeTaken;
	private String header;

	public Date getRequestStartTime() {
		return requestStartTime;
	}

	public void setRequestStartTime(Date requestStartTime) {
		this.requestStartTime = requestStartTime;
	}

	public String getExcutionUrl() {
		return excutionUrl;
	}

	public void setExcutionUrl(String excutionUrl) {
		this.excutionUrl = excutionUrl;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getTotalTimeTaken() {
		return totalTimeTaken;
	}

	public void setTotalTimeTaken(Long totalTimeTaken) {
		this.totalTimeTaken = totalTimeTaken;
	}

	public Date getRequestEndTime() {
		return requestEndTime;
	}

	public void setRequestEndTime(Date requestEndTime) {
		this.requestEndTime = requestEndTime;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

}
