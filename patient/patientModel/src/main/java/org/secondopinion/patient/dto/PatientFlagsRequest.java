package org.secondopinion.patient.dto;

import java.util.Map;
import java.util.Set;

import org.secondopinion.common.dto.SortDTO.SortDirection;

public class PatientFlagsRequest {

	private Set<PatientFlag> patientFlags;
	private Integer pageNo;
	private Integer limit;
	private Map<String, SortDirection> sortMap;
	
	public enum PatientFlag {
		NEWLY_REGISTERED(Registration.FIELD_newlyRegistered, 'Y'), 
		ACTIVE(Registration.FIELD_active, 'Y'), 
		INACTIVE(Registration.FIELD_active, 'N'), 
		EMAIL_VERIFIED(Registration.FIELD_emailVerified, 'Y'),
		VERIFIED(Registration.FIELD_phoneNumberVerified, 'Y');
		
		private String columnName;
		private Character charYorN;
		
		public String getColumnName() {
			return columnName;
		}
		public Character getCharYorN() {
			return charYorN;
		}
		
		private PatientFlag(String columnName, Character charYorN) {
			this.columnName = columnName;
			this.charYorN = charYorN;
		}
	}
	
	
	public Set<PatientFlag> getPatientFlags() {
		return patientFlags;
	}

	public void setPatientFlags(Set<PatientFlag> patientFlags) {
		this.patientFlags = patientFlags;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Map<String, SortDirection> getSortMap() {
		return sortMap;
	}

	public void setSortMap(Map<String, SortDirection> sortMap) {
		this.sortMap = sortMap;
	}
	
}
