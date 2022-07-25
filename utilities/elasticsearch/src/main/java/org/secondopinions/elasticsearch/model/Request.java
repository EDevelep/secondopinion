package org.secondopinions.elasticsearch.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import com.fasterxml.jackson.annotation.JsonFormat;

@Setting(settingPath = "es-config/elastic-analyzer.json")
@Document(indexName = "request", type = "request", shards = 2)
public class Request {
	@Id
	private String id;
	@Field(type = FieldType.Date)
	private Date requestStartTime;
	@Field(type = FieldType.Date)
	private Date requestEndTime;
	private String excutionUrl;
	private String requestUrl;
	private String moduleType;
	private Long userId;
	private String ipAddress;
	private String userName;
	private Long totalTimeTaken;
	public Request() {

	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getRequestStartTime() {
		return requestStartTime;
	}

	public void setRequestStartTime(Date requestStartTime) {
		this.requestStartTime = requestStartTime;
	}

	public Date getRequestEndTime() {
		return requestEndTime;
	}

	public void setRequestEndTime(Date requestEndTime) {
		this.requestEndTime = requestEndTime;
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

}
