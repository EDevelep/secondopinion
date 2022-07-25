package com.dodaso.codegen;

public class AuditFileldDefinition {
	private static final String CREATED_BY = "createdBy";
	private static final String CREATED_DATE = "createdDate";
	private static final String UPDATED_BY = "updatedBy";
	private static final String UPDATED_DATE = "updatedDate";
	
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return (createdBy == null ? CREATED_BY : createdBy).toUpperCase();
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return (createdDate == null ? CREATED_DATE : createdDate).toUpperCase();
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return (updatedBy == null ? UPDATED_BY : updatedBy).toUpperCase();
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the updatedDate
	 */
	public String getUpdatedDate() {
		return (updatedDate == null ? UPDATED_DATE : updatedDate).toUpperCase();
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	public boolean isVersionField(String fieldName){
		return fieldName.toUpperCase().endsWith(getUpdatedDate());
	}
	
	public boolean isAuditField(String fieldName){
		return fieldName.toUpperCase().endsWith(getUpdatedDate())
			|| fieldName.toUpperCase().endsWith(getUpdatedBy())
			|| fieldName.toUpperCase().endsWith(getCreatedBy())
			|| fieldName.toUpperCase().endsWith(getCreatedDate());
	}
	
	public boolean isAuditFieldForCreate(String fieldName){
		return fieldName.toUpperCase().endsWith(getCreatedBy())
			|| fieldName.toUpperCase().endsWith(getCreatedDate());
	}
}
