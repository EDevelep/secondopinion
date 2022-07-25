package org.secondopinion.patient.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Association;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@MappedSuperclass
public abstract class BaseAssociation extends BaseDomainObject<Long> {

	public static final String FIELD_associationId = "associationId";
	public static final String FIELD_doctorId = "doctorId";
	public static final String FIELD_hospitalId = "hospitalId";
	public static final String FIELD_active = "active";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long associationId;
	private Long doctorId;
	private Long hospitalId;
	private Character active;


	public void setAssociationId(Long _associationId) {
		this.associationId = _associationId;
	}

	@Id
	@Column(name = "associationId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAssociationId() {
		return this.associationId;
	}

	public void setDoctorId(Long _doctorId) {
		this.doctorId = _doctorId;
	}

	@NotNull
	@Column(name = "doctorId")
	public Long getDoctorId() {
		return this.doctorId;
	}

	public void setHospitalId(Long _hospitalId) {
		this.hospitalId = _hospitalId;
	}

	@Column(name = "hospitalId")
	public Long getHospitalId() {
		return this.hospitalId;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseAssociation other = (BaseAssociation) o;
		if (ObjectUtil.isEqual(getDoctorId(), other.getDoctorId())
				&& ObjectUtil.isEqual(getHospitalId(), other.getHospitalId())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (associationId != null ? associationId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.associationId == null) {
				list.add(new ValidationMessage("Field " + FIELD_associationId + " cannot be null"));
			}

		}
		if (this.doctorId == null) {
			list.add(new ValidationMessage("Field " + FIELD_doctorId + " cannot be null"));
		}
		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}
		
		if (this.isFromDB()) {
			if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 45)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 45));
			}
		}
		if (list.size() > 0)
			setHasValidationErrors(true);

		setValidationMessages(list);

	}

	@Override
	public final void setAuditFields() {
		if (!isFromDB()) {
			this.createdBy = AppThreadLocal.getUserName();
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("associationId = " + associationId + "\n");
		;
		str.append("doctorId = " + doctorId + "\n");
		str.append("hospitalId = " + hospitalId + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (associationId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_associationId, getAssociationId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getAssociationId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Association association = new Association();
		association.setFromDB(false);
		association.setDoctorId(getDoctorId());
		association.setHospitalId(getHospitalId());
		association.setActive(getActive());
		association.setLastUpdatedTs(getLastUpdatedTs());
		// afterClone(association);
		return association;
	}
}