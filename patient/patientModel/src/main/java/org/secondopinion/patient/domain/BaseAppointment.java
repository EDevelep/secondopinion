package org.secondopinion.patient.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.ObjectUtil;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length;

@MappedSuperclass
public abstract class BaseAppointment extends BaseDomainObject<Long> {

	public static final String FIELD_appointmentId = "appointmentId";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_appointmentFor = "appointmentFor";
	public static final String FIELD_referenceAppointmentId = "referenceAppointmentId";
	public static final String FIELD_appointmentDate = "appointmentDate";
	public static final String FIELD_toTime = "toTime";
	public static final String FIELD_fromTime = "fromTime";
	public static final String FIELD_description = "description";
	public static final String FIELD_schedulehoursId = "schedulehoursId";
	public static final String FIELD_roomType = "roomType";
	public static final String FIELD_referenceEntityId = "referenceEntityId";
	public static final String FIELD_entityAccepted = "entityAccepted";
	public static final String FIELD_referenceEntityName = "referenceEntityName";
	public static final String FIELD_prescriptionId = "prescriptionId";
	public static final String FIELD_chatRoomName = "chatRoomName";
	public static final String FIELD_chatRoomSID = "chatRoomSID";
	public static final String FIELD_chatType = "chatType";
	public static final String FIELD_chatURL = "chatURL";
	public static final String FIELD_statusCallbackURL = "statusCallbackURL";
	public static final String FIELD_tokenForChat = "tokenForChat";
	public static final String FIELD_patientName = "patientName";
	public static final String FIELD_appointmentStatus = "appointmentStatus";
	public static final String FIELD_ailment = "ailment";
	public static final String FIELD_active = "active";
	public static final String FIELD_amountPaid = "amountPaid";
	public static final String FIELD_referenceEntitySpecialization = "referenceEntitySpecialization";
	public static final String FIELD_amountPaidStatus = "amountPaidStatus";
	public static final String FIELD_followup = "followup";
	public static final String FIELD_followupDate = "followupDate";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";

	private Long appointmentId;
	private Long userId;
	private String appointmentFor;
	private Long referenceAppointmentId;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date appointmentDate;
	@JsonFormat(pattern = "HH:mm", shape = Shape.STRING)
	private Date toTime;
	@JsonFormat(pattern = "HH:mm", shape = Shape.STRING)
	private Date fromTime;
	private String description;
	private Long schedulehoursId;
	private String roomType;
	private Long referenceEntityId;// dc addres id
	private Character entityAccepted;
	private String referenceEntityName;// dc name
	private Long prescriptionId;
	private String chatRoomName;
	private String chatRoomSID;
	private String chatType;
	private String chatURL;
	private String statusCallbackURL;
	private String tokenForChat;
	private String patientName;
	private String appointmentStatus;
	private String ailment;
	private Character active;
	private Double amountPaid;
	private String amountPaidStatus;
	private String referenceEntitySpecialization;
	private Character followup;
	private Date followupDate;

	public void setAppointmentId(Long _appointmentId) {
		this.appointmentId = _appointmentId;
	}

	@Id
	@Column(name = "appointmentId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAppointmentId() {
		return this.appointmentId;
	}

	public void setUserId(Long _userId) {
		this.userId = _userId;
	}

	@NotNull
	@Column(name = "userId")
	public Long getUserId() {
		return this.userId;
	}

	public void setAppointmentFor(String _appointmentFor) {
		this.appointmentFor = _appointmentFor;
	}

	@Length(max = 45)
	@Column(name = "appointmentFor")
	public String getAppointmentFor() {
		return this.appointmentFor;
	}

	public void setReferenceAppointmentId(Long _referenceAppointmentId) {
		this.referenceAppointmentId = _referenceAppointmentId;
	}

	@Column(name = "referenceAppointmentId")
	public Long getReferenceAppointmentId() {
		return this.referenceAppointmentId;
	}

	public void setAppointmentDate(Date _appointmentDate) {
		this.appointmentDate = _appointmentDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "appointmentDate")
	public Date getAppointmentDate() {
		return this.appointmentDate;
	}

	public void setToTime(Date _toTime) {
		this.toTime = _toTime;
	}

	@JsonFormat(pattern = "HH:mm", shape = Shape.STRING)
	@NotNull
	@Column(name = "toTime")
	@Temporal(TemporalType.TIME)
	public Date getToTime() {
		return this.toTime;
	}

	public void setFromTime(Date _fromTime) {
		this.fromTime = _fromTime;
	}

	@JsonFormat(pattern = "HH:mm", shape = Shape.STRING)
	@NotNull
	@Column(name = "fromTime")
	@Temporal(TemporalType.TIME)
	public Date getFromTime() {
		return this.fromTime;
	}

	public void setDescription(String _description) {
		this.description = _description;
	}

	@Length(max = 250)
	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setSchedulehoursId(Long _schedulehoursId) {
		this.schedulehoursId = _schedulehoursId;
	}

	@Column(name = "schedulehoursId")
	public Long getSchedulehoursId() {
		return this.schedulehoursId;
	}

	public void setRoomType(String _roomType) {
		this.roomType = _roomType;
	}

	@Length(max = 255)
	@Column(name = "roomType")
	public String getRoomType() {
		return this.roomType;
	}

	public void setReferenceEntityId(Long _referenceEntityId) {
		this.referenceEntityId = _referenceEntityId;
	}

	@Column(name = "referenceEntityId")
	public Long getReferenceEntityId() {
		return this.referenceEntityId;
	}

	public void setEntityAccepted(Character _entityAccepted) {
		this.entityAccepted = _entityAccepted;
	}

	@Column(name = "entityAccepted")
	public Character getEntityAccepted() {
		return this.entityAccepted;
	}

	public void setReferenceEntityName(String _referenceEntityName) {
		this.referenceEntityName = _referenceEntityName;
	}

	@Length(max = 255)
	@Column(name = "referenceEntityName")
	public String getReferenceEntityName() {
		return this.referenceEntityName;
	}

	@NotNull
	@Column(name = "prescriptionId")
	public Long getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(Long prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	@Column(name = "amountPaidStatus")
	public String getAmountPaidStatus() {
		return amountPaidStatus;
	}

	public void setAmountPaidStatus(String amountPaidStatus) {
		this.amountPaidStatus = amountPaidStatus;
	}

	public void setChatRoomName(String _chatRoomName) {
		this.chatRoomName = _chatRoomName;
	}

	@Length(max = 45)
	@Column(name = "chatRoomName")
	public String getChatRoomName() {
		return this.chatRoomName;
	}

	public void setChatRoomSID(String _chatRoomSID) {
		this.chatRoomSID = _chatRoomSID;
	}

	@Length(max = 255)
	@Column(name = "chatRoomSID")
	public String getChatRoomSID() {
		return this.chatRoomSID;
	}

	public void setChatType(String _chatType) {
		this.chatType = _chatType;
	}

	@Length(max = 255)
	@Column(name = "chatType")
	public String getChatType() {
		return this.chatType;
	}

	public void setChatURL(String _chatURL) {
		this.chatURL = _chatURL;
	}

	@Length(max = 500)
	@Column(name = "chatURL")
	public String getChatURL() {
		return this.chatURL;
	}

	public void setStatusCallbackURL(String _statusCallbackURL) {
		this.statusCallbackURL = _statusCallbackURL;
	}

	@Length(max = 255)
	@Column(name = "statusCallbackURL")
	public String getStatusCallbackURL() {
		return this.statusCallbackURL;
	}

	public void setTokenForChat(String _tokenForChat) {
		this.tokenForChat = _tokenForChat;
	}

	@Length(max = 21845)
	@Column(name = "tokenForChat")
	public String getTokenForChat() {
		return this.tokenForChat;
	}

	public void setPatientName(String _patientName) {
		this.patientName = _patientName;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "patientName")
	public String getPatientName() {
		return this.patientName;
	}

	public void setAppointmentStatus(String _appointmentStatus) {
		this.appointmentStatus = _appointmentStatus;
	}

	@Length(max = 255)
	@Column(name = "appointmentStatus")
	public String getAppointmentStatus() {
		return this.appointmentStatus;
	}

	public void setAilment(String _ailment) {
		this.ailment = _ailment;
	}

	@Length(max = 250)
	@Column(name = "ailment")
	public String getAilment() {
		return this.ailment;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	public void setAmountPaid(Double _amountPaid) {
		this.amountPaid = _amountPaid;
	}

	@NotNull
	@Column(name = "amountPaid")
	public Double getAmountPaid() {
		return this.amountPaid;
	}

	@Column(name = "referenceEntitySpecialization")
	public String getReferenceEntitySpecialization() {
		return referenceEntitySpecialization;
	}

	public void setReferenceEntitySpecialization(String referenceEntitySpecialization) {
		this.referenceEntitySpecialization = referenceEntitySpecialization;
	}
	@Column(name = "followup")
	public Character getFollowup() {
		return followup;
	}

	public void setFollowup(Character followup) {
		this.followup = followup;
	}
	@Column(name = "followupDate")
	public Date getFollowupDate() {
		return followupDate;
	}

	public void setFollowupDate(Date followupDate) {
		this.followupDate = followupDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseAppointment other = (BaseAppointment) o;
		if (ObjectUtil.isEqual(getUserId(), other.getUserId())
				&& ObjectUtil.isEqual(getAppointmentFor(), other.getAppointmentFor())
				&& ObjectUtil.isEqual(getReferenceAppointmentId(), other.getReferenceAppointmentId())
				&& ObjectUtil.isEqual(getAppointmentDate(), other.getAppointmentDate())
				&& ObjectUtil.isEqual(getToTime(), other.getToTime())
				&& ObjectUtil.isEqual(getFromTime(), other.getFromTime())
				&& ObjectUtil.isEqual(getDescription(), other.getDescription())
				&& ObjectUtil.isEqual(getSchedulehoursId(), other.getSchedulehoursId())
				&& ObjectUtil.isEqual(getRoomType(), other.getRoomType())
				&& ObjectUtil.isEqual(getReferenceEntityId(), other.getReferenceEntityId())
				&& ObjectUtil.isEqual(getEntityAccepted(), other.getEntityAccepted())
				&& ObjectUtil.isEqual(getReferenceEntityName(), other.getReferenceEntityName())
				&& ObjectUtil.isEqual(getPrescriptionId(), other.getPrescriptionId())
				&& ObjectUtil.isEqual(getChatRoomName(), other.getChatRoomName())
				&& ObjectUtil.isEqual(getChatRoomSID(), other.getChatRoomSID())
				&& ObjectUtil.isEqual(getChatType(), other.getChatType())
				&& ObjectUtil.isEqual(getChatURL(), other.getChatURL())
				&& ObjectUtil.isEqual(getStatusCallbackURL(), other.getStatusCallbackURL())
				&& ObjectUtil.isEqual(getTokenForChat(), other.getTokenForChat())
				&& ObjectUtil.isEqual(getPatientName(), other.getPatientName())
				&& ObjectUtil.isEqual(getAppointmentStatus(), other.getAppointmentStatus())
				&& ObjectUtil.isEqual(getAilment(), other.getAilment())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getAmountPaid(), other.getAmountPaid())
				&& ObjectUtil.isEqual(getReferenceEntitySpecialization(), other.getReferenceEntitySpecialization())
				&& ObjectUtil.isEqual(getAmountPaidStatus(), other.getAmountPaidStatus())
				&& ObjectUtil.isEqual(getFollowup(), other.getFollowup())
				&& ObjectUtil.isEqual(getFollowupDate(), other.getFollowupDate())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (appointmentId != null ? appointmentId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.appointmentId == null) {
				list.add(new ValidationMessage("Field " + FIELD_appointmentId + " cannot be null"));
			}

		}
		if (this.userId == null) {
			list.add(new ValidationMessage("Field " + FIELD_userId + " cannot be null"));
		}
		if (StringUtil.isNullOrEmpty(this.appointmentFor)) {
			list.add(new ValidationMessage("Field " + FIELD_appointmentFor + " cannot be null "));
		}
		if ((this.appointmentFor != null) && (this.appointmentFor.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_appointmentFor + " cannot be longer than: " + 45));
		}
		if (this.appointmentDate == null) {
			list.add(new ValidationMessage("Field " + FIELD_appointmentDate + " cannot be null"));
		}
		if (this.fromTime == null) {
			list.add(new ValidationMessage("Field " + FIELD_fromTime + " cannot be null"));
		}
		if (this.toTime == null) {
			list.add(new ValidationMessage("Field " + FIELD_toTime + " cannot be null"));
		}
		if ((this.description != null) && (this.description.length() > 250)) {
			list.add(new ValidationMessage("Field " + FIELD_description + " cannot be longer than: " + 250));
		}

		if ((this.roomType != null) && (this.roomType.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_roomType + " cannot be longer than: " + 255));
		}

		if ((this.referenceEntityName != null) && (this.referenceEntityName.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_referenceEntityName + " cannot be longer than: " + 255));
		}
		if (this.prescriptionId == null) {
			list.add(new ValidationMessage("Field " + prescriptionId + " cannot be null"));
		}

		if ((this.chatRoomName != null) && (this.chatRoomName.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_chatRoomName + " cannot be longer than: " + 45));
		}

		if ((this.chatRoomSID != null) && (this.chatRoomSID.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_chatRoomSID + " cannot be longer than: " + 255));
		}

		if ((this.chatType != null) && (this.chatType.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_chatType + " cannot be longer than: " + 255));
		}

		if ((this.chatURL != null) && (this.chatURL.length() > 500)) {
			list.add(new ValidationMessage("Field " + FIELD_chatURL + " cannot be longer than: " + 500));
		}

		if ((this.statusCallbackURL != null) && (this.statusCallbackURL.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_statusCallbackURL + " cannot be longer than: " + 255));
		}

		if ((this.tokenForChat != null) && (this.tokenForChat.length() > 21845)) {
			list.add(new ValidationMessage("Field " + FIELD_tokenForChat + " cannot be longer than: " + 21845));
		}

		if (StringUtil.isNullOrEmpty(this.patientName)) {
			list.add(new ValidationMessage("Field " + FIELD_patientName + " cannot be null"));
		}

		if ((this.patientName != null) && (this.patientName.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_patientName + " cannot be longer than: " + 45));
		}

		if ((this.appointmentStatus != null) && (this.appointmentStatus.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_appointmentStatus + " cannot be longer than: " + 255));
		}

		if ((this.ailment != null) && (this.ailment.length() > 250)) {
			list.add(new ValidationMessage("Field " + FIELD_ailment + " cannot be longer than: " + 250));
		}

		if (this.amountPaid == null) {
			list.add(new ValidationMessage("Field " + FIELD_amountPaid + " cannot be null"));
		}

		if (this.isFromDB()) {
			if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 255)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 255));
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
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
		this.lastUpdatedBy = AppThreadLocal.getUserName();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("appointmentId = " + appointmentId + "\n");
		;
		str.append("userId = " + userId + "\n");
		str.append("appointmentFor = " + appointmentFor + "\n");
		str.append("referenceAppointmentId = " + referenceAppointmentId + "\n");
		str.append("appointmentDate = " + appointmentDate + "\n");
		str.append("toTime = " + toTime + "\n");
		str.append("fromTime = " + fromTime + "\n");
		str.append("description = " + description + "\n");
		str.append("schedulehoursId = " + schedulehoursId + "\n");
		str.append("roomType = " + roomType + "\n");
		str.append("referenceEntityId = " + referenceEntityId + "\n");
		str.append("entityAccepted = " + entityAccepted + "\n");
		str.append("referenceEntityName = " + referenceEntityName + "\n");
		str.append("medicalPrescriptionId = " + prescriptionId + "\n");
		str.append("chatRoomName = " + chatRoomName + "\n");
		str.append("chatRoomSID = " + chatRoomSID + "\n");
		str.append("chatType = " + chatType + "\n");
		str.append("chatURL = " + chatURL + "\n");
		str.append("statusCallbackURL = " + statusCallbackURL + "\n");
		str.append("tokenForChat = " + tokenForChat + "\n");
		str.append("patientName = " + patientName + "\n");
		str.append("appointmentStatus = " + appointmentStatus + "\n");
		str.append("followup = " + followup + "\n");
		str.append("followupDate = " + followupDate + "\n");
		str.append("ailment = " + ailment + "\n");
		str.append("active = " + active + "\n");
		str.append("amountPaid = " + amountPaid + "\n");
		str.append("referenceEntitySpecialization = " + referenceEntitySpecialization + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (appointmentId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_appointmentId, getAppointmentId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getAppointmentId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Appointment appointment = new Appointment();
		appointment.setFromDB(false);
		appointment.setUserId(getUserId());
		appointment.setAppointmentFor(getAppointmentFor());
		appointment.setReferenceAppointmentId(getReferenceAppointmentId());
		appointment.setAppointmentDate(getAppointmentDate());
		appointment.setToTime(getToTime());
		appointment.setFromTime(getFromTime());
		appointment.setDescription(getDescription());
		appointment.setSchedulehoursId(getSchedulehoursId());
		appointment.setRoomType(getRoomType());
		appointment.setReferenceEntityId(getReferenceEntityId());
		appointment.setEntityAccepted(getEntityAccepted());
		appointment.setReferenceEntityName(getReferenceEntityName());
		appointment.setPrescriptionId(getPrescriptionId());
		appointment.setChatRoomName(getChatRoomName());
		appointment.setChatRoomSID(getChatRoomSID());
		appointment.setChatType(getChatType());
		appointment.setChatURL(getChatURL());
		appointment.setStatusCallbackURL(getStatusCallbackURL());
		appointment.setTokenForChat(getTokenForChat());
		appointment.setPatientName(getPatientName());
		appointment.setAppointmentStatus(getAppointmentStatus());
		appointment.setAilment(getAilment());
		appointment.setActive(getActive());
		appointment.setReferenceEntitySpecialization(getReferenceEntitySpecialization());
		appointment.setAmountPaid(getAmountPaid());
		appointment.setAmountPaidStatus(getAmountPaidStatus());
		// afterClone(appointment);
		return appointment;
	}
}