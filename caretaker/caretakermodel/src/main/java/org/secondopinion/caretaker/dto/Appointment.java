package org.secondopinion.caretaker.dto;



import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinioncaretaker.domain.BaseAppointment;



@Entity
@Table(name = "appointment")
public class Appointment extends BaseAppointment {

	public static final String DATE_FORMAT = "dd-MMM-yyyy";
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