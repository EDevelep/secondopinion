package org.secondopinion.userMgmt.dto;

import java.util.Map;
import java.util.Set;

import org.secondopinion.common.dto.SortDTO.SortDirection;

public class UserRequest {

	private String userName;
	private String password;
	private Long userId;
	private String emailId;
	private Long companyId;
	private Long companyTemplateId;
	private Address address;
	private Set<CompanyFlag> companyFlags;
	private Integer pageNo;
	private Integer limit;
	private Map<String, SortDirection> sortMap;
	
	
	
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
	 * @return the passowrd
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param passowrd the passowrd to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
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
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	/**
	 * @return the companyTemplateId
	 */
	public Long getCompanyTemplateId() {
		return companyTemplateId;
	}
	/**
	 * @param companyTemplateId the companyTemplateId to set
	 */
	public void setCompanyTemplateId(Long companyTemplateId) {
		this.companyTemplateId = companyTemplateId;
	}
	
	
	/**
	 * @return the companyFlag
	 */
	public Set<CompanyFlag> getCompanyFlags() {
		return companyFlags;
	}
	/**
	 * @param companyFlag the companyFlag to set
	 */
	public void setCompanyFlags(Set<CompanyFlag> companyFlags) {
		this.companyFlags = companyFlags;
	}
	/**
	 * @return the pageNo
	 */
	public Integer getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	/**
	 * @return the sortMap
	 */
	public Map<String, SortDirection> getSortMap() {
		return sortMap;
	}
	/**
	 * @param sortMap the sortMap to set
	 */
	public void setSortMap(Map<String, SortDirection> sortMap) {
		this.sortMap = sortMap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRequest [userName=" + userName + ", password=" + password + ", userId=" + userId + ", emailId="
				+ emailId + ", companyId=" + companyId + ", companyTemplateId=" + companyTemplateId + ", address="
				+ address + ", companyFlags=" + companyFlags + ", pageNo=" + pageNo + ", limit=" + limit + ", sortMap="
				+ sortMap + "]";
	}



	public enum CompanyFlag {
		NEWLY_REGISTERED(Company.FIELD_newlyRegistered, 'Y'), 
		ACTIVE(Company.FIELD_active, 'Y'), 
		INACTIVE(Company.FIELD_active, 'N'), 
		APPROVED(Company.FIELD_approved, 'Y'), 
		VERIFIED(Company.FIELD_verificationCompleted, 'Y');
		
		private String columnName;
		private Character charYorN;
		
		public String getColumnName() {
			return columnName;
		}
		public Character getCharYorN() {
			return charYorN;
		}
		
		private CompanyFlag(String columnName, Character charYorN) {
			this.columnName = columnName;
			this.charYorN = charYorN;
		}
	}
	
	
}
