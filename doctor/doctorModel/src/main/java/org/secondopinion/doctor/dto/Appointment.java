package org.secondopinion.doctor.dto;



import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.doctor.domain.BaseAppointment;

@Entity
@Table(name = "appointment")
public class Appointment extends BaseAppointment {

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "HH:mm";
	private Long referenceEntityId;
	private String referenceEntityName;
	
	@Transient
	public Long getReferenceEntityId() {
		return referenceEntityId;
	}
	public void setReferenceEntityId(Long referenceEntityId) {
		this.referenceEntityId = referenceEntityId;
	}
	
	@Transient
	public String getReferenceEntityName() {
		return referenceEntityName;
	}
	public void setReferenceEntityName(String referenceEntityName) {
		this.referenceEntityName = referenceEntityName;
	}
	
	
	
}