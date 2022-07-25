package org.secondopinion.doctor.dto;

import java.util.Locale;

public class RequestResponseDTO {
	private String start_time;
	private String  EXECUTION_TIME;
	private String  REQUEST_URL;
	private String moduleType;
	private Long userId;
	private String ipaddres;
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEXECUTION_TIME() {
		return EXECUTION_TIME;
	}
	public void setEXECUTION_TIME(String eXECUTION_TIME, Locale locale) {
		EXECUTION_TIME = eXECUTION_TIME;
	}
	public String getREQUEST_URL() {
		return REQUEST_URL;
	}
	public void setREQUEST_URL(String rEQUEST_URL) {
		REQUEST_URL = rEQUEST_URL;
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
	public String getIpaddres() {
		return ipaddres;
	}
	public void setIpaddres(String ipaddres) {
		this.ipaddres = ipaddres;
	}
}
