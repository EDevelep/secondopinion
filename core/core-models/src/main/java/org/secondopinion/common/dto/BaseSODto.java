package org.secondopinion.common.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@JsonIgnoreProperties(value = { "params" })
public abstract class BaseSODto implements ISODto {
	
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	
	@JsonIgnore
	protected String lastUpdatedBy;
	
	@JsonIgnore
	protected Date lastUpdatedTs;
	
	@JsonIgnore
	private boolean fromDB;

	@JsonIgnore
	protected String createdBy;
	
	@JsonIgnore
	protected Date createdDate;

	
	@JsonIgnore
	public void setLastUpdatedBy(String _lastupdatedby) {
		this.lastUpdatedBy = _lastupdatedby;
	}

	@Length(max = 100)
	@Column(name = "lastUpdatedBy")
	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	@JsonIgnore
	public void setLastUpdatedTs(Date _lastUpdatedTs) {
		this.lastUpdatedTs = _lastUpdatedTs;
	}

	@Column(name = "lastUpdatedTs")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getLastUpdatedTs() {
		return this.lastUpdatedTs;
	}
	
	@JsonIgnore
	public void setAudit(String lastUpdatedBy){
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedTs = new Date();
	}
	
	@Override
	@Transient
	@JsonIgnore
	public Map<String, Object> getParams() {
		 Map<String, Object> params = new HashMap<String, Object>();
		 
		 params.put(FIELD_lastUpdatedBy, getLastUpdatedBy());
		 params.put(FIELD_lastUpdatedTs, getLastUpdatedTs());
		 
		 return params;
	}

	@Transient
	public boolean isFromDB() {
		return fromDB;
	}

	@JsonIgnore
	public void setFromDB(boolean fromDB) {
		this.fromDB = fromDB;
	}

	public void setAuditForCreate(String _createdBy) {
		this.setCreatedBy(_createdBy);	
		this.setCreatedDate(new Date());
	}
	
	@JsonIgnore
	public void setCreatedBy(String _createdBy) {
		this.createdBy = _createdBy;
	}

	@Length(max = 45)
	@Column(name = "createdBy")
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedDate(Date _createdDate) {
		this.createdDate = _createdDate;
	}

	@Column(name = "createdDate")
	public Date getCreatedDate() {
		return this.createdDate;
	}

	
}
