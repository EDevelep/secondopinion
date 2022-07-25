package org.secondopinion.doctor.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.doctor.dto.Doctorratings;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
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
public abstract class BaseDoctorratings extends BaseDomainObject<Long> {

	public static final String FIELD_doctorratingid = "doctorratingid";
	public static final String FIELD_doctorid = "doctorid";
	public static final String FIELD_patientid = "patientid";
	public static final String FIELD_active = "active";
	public static final String FIELD_appointmentid = "appointmentid";
	public static final String FIELD_rating = "rating";
	public static final String FIELD_feedback = "feedback";
	public static final String FIELD_patientname = "patientname";
	public static final String FIELD_ratingType = "ratingType";

	private Long doctorratingid;
	private Long doctorid;
	private Long patientid;
	private Character active;
	private Long appointmentid;
	private Double rating;
	private String feedback;
	private String patientname;
	private String ratingType;

	public void setDoctorratingid(Long _doctorratingid) {
		this.doctorratingid = _doctorratingid;
	}

	@Id
	@Column(name = "doctorratingid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDoctorratingid() {
		return this.doctorratingid;
	}

	public void setDoctorid(Long _doctorid) {
		this.doctorid = _doctorid;
	}

	@Column(name = "doctorid")
	public Long getDoctorid() {
		return this.doctorid;
	}

	public void setPatientid(Long _patientid) {
		this.patientid = _patientid;
	}

	@Column(name = "patientid")
	public Long getPatientid() {
		return this.patientid;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@NotNull
	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	public void setAppointmentid(Long _appointmentid) {
		this.appointmentid = _appointmentid;
	}

	@Column(name = "appointmentid")
	public Long getAppointmentid() {
		return this.appointmentid;
	}

	public void setRating(Double _rating) {
		this.rating = _rating;
	}

	@Column(name = "rating")
	public Double getRating() {
		return this.rating;
	}

	public void setFeedback(String _feedback) {
		this.feedback = _feedback;
	}

	@Length(max = 500)
	@Column(name = "feedback")
	public String getFeedback() {
		return this.feedback;
	}

	@Column(name = "patientname")
	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	@Column(name = "ratingType")
	public String getRatingType() {
		return ratingType;
	}

	public void setRatingType(String ratingType) {
		this.ratingType = ratingType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseDoctorratings other = (BaseDoctorratings) o;
		if (ObjectUtil.isEqual(getDoctorid(), other.getDoctorid())
				&& ObjectUtil.isEqual(getPatientid(), other.getPatientid())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getAppointmentid(), other.getAppointmentid())
				&& ObjectUtil.isEqual(getRating(), other.getRating())
				&& ObjectUtil.isEqual(getPatientname(), other.getPatientname())
				&& ObjectUtil.isEqual(getRatingType(), other.getRatingType())
				&& ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy())
				&& ObjectUtil.isEqual(getCreatedDate(), other.getCreatedDate())
				&& ObjectUtil.isEqual(getFeedback(), other.getFeedback())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (doctorratingid != null ? doctorratingid.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.doctorratingid == null) {
				list.add(new ValidationMessage("Field " + FIELD_doctorratingid + " cannot be null"));
			}

		}
		if (this.active == null) {
			list.add(new ValidationMessage("Field " + FIELD_active + " cannot be null"));
		}

		if ((this.feedback != null) && (this.feedback.length() > 500)) {
			list.add(new ValidationMessage("Field " + FIELD_feedback + " cannot be longer than: " + 500));
		}
		if ((this.createdBy != null) && (this.createdBy.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_createdBy + " cannot be longer than: " + 255));
		}

		if (this.isFromDB()) {
			if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 100)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 100));
			}
		}
		if (list.size() > 0)
			setHasValidationErrors(true);

		setValidationMessages(list);

	}

	@Override
	public final void setAuditFields() {
		if (!isFromDB()) {
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("doctorratingid = " + doctorratingid + "\n");
		;
		str.append("doctorid = " + doctorid + "\n");
		str.append("patientid = " + patientid + "\n");
		str.append("active = " + active + "\n");
		str.append("appointmentid = " + appointmentid + "\n");
		str.append("rating = " + rating + "\n");
		str.append("feedback = " + feedback + "\n");
		str.append("patientname = " + patientname + "\n");
		str.append("ratingType = " + ratingType + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (doctorratingid == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_doctorratingid, getDoctorratingid()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getDoctorratingid();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Doctorratings doctorratings = new Doctorratings();
		doctorratings.setFromDB(false);
		doctorratings.setDoctorid(getDoctorid());
		doctorratings.setPatientid(getPatientid());
		doctorratings.setActive(getActive());
		doctorratings.setAppointmentid(getAppointmentid());
		doctorratings.setRating(getRating());
		doctorratings.setFeedback(getFeedback());
		doctorratings.setPatientname(getPatientname());
		doctorratings.setRatingType(getRatingType());
		// afterClone(doctorratings);
		return doctorratings;
	}
}