package org.secondopinion.patient.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Userreference;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.ObjectUtil;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings({ "serial" })
@MappedSuperclass
public abstract class BaseUserreference extends BaseDomainObject<Long> {

	public static final String FIELD_referenceid = "referenceid";
	public static final String FIELD_referencetype = "referencetype";
	public static final String FIELD_referencename = "referencename";
	public static final String FIELD_userid = "userid";
	public static final String FIELD_doctorid = "doctorid";
	public static final String FIELD_diagnosticcenterId= "diagnosticcenterId";
	public static final String FIELD_pharmacyId= "pharmacyId";
	public static final String FIELD_active = "active";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long referenceid;
	private String referencetype;
	private String referencename;
	private Long userid;
	private Long doctorid;
	private Long pharmacyId;
	private Long diagnosticcenterId;
	private Character active;


	public void setReferenceid(Long _referenceid) {
		this.referenceid = _referenceid;
	}

	@Id
	@Column(name = "referenceid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getReferenceid() {
		return this.referenceid;
	}

	public void setReferencetype(String _referencetype) {
		this.referencetype = _referencetype;
	}

	@Length(max = 45)
	@Column(name = "referencetype")
	public String getReferencetype() {
		return this.referencetype;
	}

	public void setReferencename(String _referencename) {
		this.referencename = _referencename;
	}

	@Length(max = 45)
	@Column(name = "referencename")
	public String getReferencename() {
		return this.referencename;
	}

	public void setUserid(Long _userid) {
		this.userid = _userid;
	}

	@NotNull
	@Column(name = "userid")
	public Long getUserid() {
		return this.userid;
	}

	public void setDoctorid(Long _doctorid) {
		this.doctorid = _doctorid;
	}

	
	@Column(name = "doctorid")
	public Long getDoctorid() {
		return this.doctorid;
	}

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
	@Column(name = "diagnosticcenterId")
	public Long getDiagnosticcenterId() {
		return diagnosticcenterId;
	}

	public void setDiagnosticcenterId(Long diagnosticcenterId) {
		this.diagnosticcenterId = diagnosticcenterId;
	}
	
	@Column(name = "pharmacyId")
	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@NotNull
	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseUserreference other = (BaseUserreference)o;
		if(
			ObjectUtil.isEqual(getReferencetype(), other.getReferencetype()) && 
			ObjectUtil.isEqual(getReferencename(), other.getReferencename()) && 
			ObjectUtil.isEqual(getUserid(), other.getUserid()) && 
			ObjectUtil.isEqual(getDoctorid(), other.getDoctorid()) && 
			ObjectUtil.isEqual(getDiagnosticcenterId(), other.getDiagnosticcenterId()) && 
			ObjectUtil.isEqual(getPharmacyId(), other.getPharmacyId()) && 
			 ObjectUtil.isEqual(getActive(), other.getActive()) &&
			ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy()) && 
			ObjectUtil.isEqual(getCreatedDate(), other.getCreatedDate()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (referenceid != null ? referenceid.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.referenceid == null) {
				list.add(new ValidationMessage("Field " + FIELD_referenceid + " cannot be null"));
			}

		}
		if ((this.referencetype != null) && (this.referencetype.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_referencetype + " cannot be longer than: " + 45));
		}

		if ((this.referencename != null) && (this.referencename.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_referencename + " cannot be longer than: " + 45));
		}

		if (this.userid == null) {
			list.add(new ValidationMessage("Field " + FIELD_userid + " cannot be null"));
		}

		if (this.active == null) {
			list.add(new ValidationMessage("Field " + FIELD_active + " cannot be null"));
		}
		if (this.isFromDB()) {
			if ((this.createdBy != null) && (this.createdBy.length() > 45)) {
				list.add(new ValidationMessage("Field " + FIELD_createdBy + " cannot be longer than: " + 45));
			}
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
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("referenceid = " + referenceid + "\n");
		;
		str.append("referencetype = " + referencetype + "\n");
		str.append("referencename = " + referencename + "\n");
		str.append("userid = " + userid + "\n");
		str.append("doctorid = " + doctorid + "\n");
		str.append("diagnosticcenterId = " + diagnosticcenterId + "\n");
		str.append("pharmacyId = " + pharmacyId + "\n");
		str.append("active = " + active + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (referenceid == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_referenceid, getReferenceid()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getReferenceid();
	}



	@Override
	public Object clone() throws CloneNotSupportedException {
		Userreference userreference = new Userreference();
		userreference.setFromDB(false);
		userreference.setReferencetype(getReferencetype());
		userreference.setReferencename(getReferencename());
		userreference.setUserid(getUserid());
		userreference.setDoctorid(getDoctorid());
		userreference.setPharmacyId(getPharmacyId());
		userreference.setDiagnosticcenterId(getDiagnosticcenterId());
		userreference.setCreatedDate(getCreatedDate());
		userreference.setActive(getActive());
		// afterClone(userreference);
		return userreference;
	}
}