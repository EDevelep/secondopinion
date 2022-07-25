package org.secondopinioncaretaker.domain; 

import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.caretaker.dto.Appointment;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage; 
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
	public static final String FIELD_appointmentDate = "appointmentDate";
	public static final String FIELD_caretakerId = "caretakerId";
	public static final String FIELD_caretakerName = "caretakerName";
	public static final String FIELD_appointmentStatus = "appointmentStatus";
	public static final String FIELD_description = "description";
	public static final String FIELD_fromTime = "fromTime";
	public static final String FIELD_schedulehoursId = "schedulehoursId";
	public static final String FIELD_toTime = "toTime";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_roomType = "roomType";
	public static final String FIELD_ChatRoomName = "ChatRoomName";
	public static final String FIELD_ChatRoomSID = "ChatRoomSID";
	public static final String FIELD_chatType = "chatType";
	public static final String FIELD_chatURL = "chatURL";
	public static final String FIELD_statusCallbackURL = "statusCallbackURL";
	public static final String FIELD_tokenForChat = "tokenForChat";
	public static final String FIELD_patientName = "patientName";
	public static final String FIELD_ailment = "ailment";
	public static final String FIELD_amountPaid = "amountPaid";
	public static final String FIELD_active = "active";

	private Long appointmentId;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date appointmentDate;
	private String appointmentStatus;
	private String description;
	private Long schedulehoursId;
	@JsonFormat(pattern="HH:mm", shape=Shape.STRING)
	private Date fromTime;
	@JsonFormat(pattern="HH:mm", shape=Shape.STRING)
	private Date toTime;
	private Long userId;
	private String roomType;
	private Long caretakerId;
	private String caretakerName;
	private String chatRoomName;
	private String chatRoomSID;
	private String chatType;
	private String chatURL;
	private String statusCallbackURL;
	private String tokenForChat;
	private String patientName;
	private String ailment;
	private java.math.BigDecimal amountPaid;
	private Character active;

	public void setAppointmentId( Long  _appointmentId){
		this.appointmentId = _appointmentId;
	}
	@Id
 	@Column(name = "appointmentId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAppointmentId(){
		 return this.appointmentId;
	}
	public void setAppointmentDate(Date  _appointmentDate){
		this.appointmentDate = _appointmentDate;
	}
	@JsonFormat(pattern="yyyy-MM-dd", shape=Shape.STRING)
	@NotNull 
	@Temporal(TemporalType.DATE)
	@Column (name="appointmentDate")
	public Date getAppointmentDate(){
		 return this.appointmentDate;
	}
	
	public void setAppointmentStatus( String  _appointmentStatus){
		this.appointmentStatus = _appointmentStatus;
	}
	@Length(max=255)
	@Column (name="appointmentStatus")
	public String getAppointmentStatus(){
		 return this.appointmentStatus;
	}
	public void setDescription( String  _description){
		this.description = _description;
	}
	@Length(max=250)
	@Column (name="description")
	public String getDescription(){
		 return this.description;
	}
	public void setFromTime( Date  _fromTime){
		this.fromTime = _fromTime;
	}
	@JsonFormat(pattern="HH:mm", shape=Shape.STRING)
	@NotNull 
	@Temporal(TemporalType.TIME)
	@Column (name="fromTime")
	public Date getFromTime(){
		 return this.fromTime;
	}
	
	public void setSchedulehoursId( Long  _schedulehoursId){
		this.schedulehoursId = _schedulehoursId;
	}
	@Column (name="schedulehoursId")
	public Long getSchedulehoursId(){
		 return this.schedulehoursId;
	}
	public void setToTime( Date  _toTime){
		this.toTime = _toTime;
	}
	@JsonFormat(pattern="HH:mm", shape=Shape.STRING)
	@NotNull 
	@Column (name="toTime")
	@Temporal(TemporalType.TIME)
	public Date getToTime(){
		 return this.toTime;
	}
	public void setUserId( Long  _userId){
		this.userId = _userId;
	}
	@NotNull 
	@Column (name="userId")
	public Long getUserId(){
		 return this.userId;
	}
	public void setRoomType( String  _roomType){
		this.roomType = _roomType;
	}
	@Length(max=255)
	@Column (name="roomType")
	public String getRoomType(){
		 return this.roomType;
	}
	public void setCaretakerId( Long  _caretakerId){
		this.caretakerId = _caretakerId;
	}
	@Column (name="caretakerId")
	public Long getCaretakerId(){
		 return this.caretakerId;
	}
	public void setCaretakerName( String  _caretakerName){
		this.caretakerName = _caretakerName;
	}
	@Length(max=255)
	@Column (name="caretakerName")
	public String getCaretakerName(){
		 return this.caretakerName;
	}
	public void setChatRoomName( String  _chatRoomName){
		this.chatRoomName = _chatRoomName;
	}
	@Length(max=45)
	@Column (name="chatRoomName")
	public String getChatRoomName(){
		 return this.chatRoomName;
	}
	public void setChatRoomSID( String  _chatRoomSID){
		this.chatRoomSID = _chatRoomSID;
	}
	@Length(max=255)
	@Column (name="chatRoomSID")
	public String getChatRoomSID(){
		 return this.chatRoomSID;
	}
	public void setChatType( String  _chatType){
		this.chatType = _chatType;
	}
	@Length(max=255)
	@Column (name="chatType")
	public String getChatType(){
		 return this.chatType;
	}
	public void setChatURL( String  _chatURL){
		this.chatURL = _chatURL;
	}
	@Length(max=500)
	@Column (name="chatURL")
	public String getChatURL(){
		 return this.chatURL;
	}
	public void setStatusCallbackURL( String  _statusCallbackURL){
		this.statusCallbackURL = _statusCallbackURL;
	}
	@Length(max=255)
	@Column (name="statusCallbackURL")
	public String getStatusCallbackURL(){
		 return this.statusCallbackURL;
	}
	public void setTokenForChat( String  _tokenForChat){
		this.tokenForChat = _tokenForChat;
	}
	@Length(max=65535)
	@Column (name="tokenForChat")
	public String getTokenForChat(){
		 return this.tokenForChat;
	}
	public void setPatientName( String  _patientName){
		this.patientName = _patientName;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="patientName")
	public String getPatientName(){
		 return this.patientName;
	}
	public void setAilment( String  _ailment){
		this.ailment = _ailment;
	}
	@Length(max=225)
	@Column (name="ailment")
	public String getAilment(){
		 return this.ailment;
	}
	public void setAmountPaid( java.math.BigDecimal  _amountPaid){
		this.amountPaid = _amountPaid;
	}
	@NotNull 
	@Column (name="amountPaid")
	public java.math.BigDecimal getAmountPaid(){
		 return this.amountPaid;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseAppointment other = (BaseAppointment)o;
		if(
			ObjectUtil.isEqual(getAppointmentDate(), other.getAppointmentDate()) && 
			ObjectUtil.isEqual(getAppointmentStatus(), other.getAppointmentStatus()) && 
			ObjectUtil.isEqual(getDescription(), other.getDescription()) && 
			ObjectUtil.isEqual(getFromTime(), other.getFromTime()) && 
			ObjectUtil.isEqual(getSchedulehoursId(), other.getSchedulehoursId()) && 
			ObjectUtil.isEqual(getToTime(), other.getToTime()) && 
			ObjectUtil.isEqual(getUserId(), other.getUserId()) && 
			ObjectUtil.isEqual(getRoomType(), other.getRoomType()) && 
			ObjectUtil.isEqual(getCaretakerId(), other.getCaretakerId()) && 
			ObjectUtil.isEqual(getCaretakerName(), other.getCaretakerName()) && 
			ObjectUtil.isEqual(getChatRoomName(), other.getChatRoomName()) && 
			ObjectUtil.isEqual(getChatRoomSID(), other.getChatRoomSID()) && 
			ObjectUtil.isEqual(getChatType(), other.getChatType()) && 
			ObjectUtil.isEqual(getChatURL(), other.getChatURL()) && 
			ObjectUtil.isEqual(getStatusCallbackURL(), other.getStatusCallbackURL()) && 
			ObjectUtil.isEqual(getTokenForChat(), other.getTokenForChat()) && 
			ObjectUtil.isEqual(getPatientName(), other.getPatientName()) && 
			ObjectUtil.isEqual(getAilment(), other.getAilment()) && 
			ObjectUtil.isEqual(getAmountPaid(), other.getAmountPaid()) && 
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
	public int hashCode(){
		int result = 0;
		result = result + (appointmentId!= null ? appointmentId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.appointmentId == null){
			 list.add(new ValidationMessage("Field " + FIELD_appointmentId+  " cannot be null"));
		}

		}
		if(this.userId == null){
			 list.add(new ValidationMessage("Field " + FIELD_userId+  " cannot be null"));
		}

		if((this.appointmentStatus != null) && (this.appointmentStatus.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_appointmentStatus+  " cannot be longer than: " + 255));
		}

		if((this.description != null) && (this.description.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_description+  " cannot be longer than: " + 250));
		}

		
		if(this.appointmentDate == null){
			 list.add(new ValidationMessage("Field " + FIELD_appointmentDate+  " cannot be null"));
		}
		if(this.fromTime == null){
			 list.add(new ValidationMessage("Field " + FIELD_fromTime+  " cannot be null"));
		}
		if(this.toTime == null){
			 list.add(new ValidationMessage("Field " + FIELD_toTime+  " cannot be null"));
		}
		if((this.roomType != null) && (this.roomType.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_roomType+  " cannot be longer than: " + 255));
		}


		if((this.chatRoomName != null) && (this.chatRoomName.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_ChatRoomName+  " cannot be longer than: " + 45));
		}

		if((this.chatRoomSID != null) && (this.chatRoomSID.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_ChatRoomSID+  " cannot be longer than: " + 255));
		}

		if((this.chatType != null) && (this.chatType.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_chatType+  " cannot be longer than: " + 255));
		}

		if((this.chatURL != null) && (this.chatURL.length()>500)){
			 list.add(new ValidationMessage("Field " + FIELD_chatURL+  " cannot be longer than: " + 500));
		}

		if((this.statusCallbackURL != null) && (this.statusCallbackURL.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_statusCallbackURL+  " cannot be longer than: " + 255));
		}

		if((this.tokenForChat != null) && (this.tokenForChat.length()>65535)){
			 list.add(new ValidationMessage("Field " + FIELD_tokenForChat+  " cannot be longer than: " + 65535));
		}

		if( StringUtil.isNullOrEmpty(this.patientName)){
			 list.add(new ValidationMessage("Field " + FIELD_patientName+  " cannot be null"));
		}

		if((this.patientName != null) && (this.patientName.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_patientName+  " cannot be longer than: " + 45));
		}

		if((this.ailment != null) && (this.ailment.length()>225)){
			 list.add(new ValidationMessage("Field " + FIELD_ailment+  " cannot be longer than: " + 225));
		}

		if(this.amountPaid == null){
			 list.add(new ValidationMessage("Field " + FIELD_amountPaid+  " cannot be null"));
		}

	if(this.isFromDB()){
		if((this.createdBy != null) && (this.createdBy.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 255));
		}
	}
	if(this.isFromDB()){
		if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 100));
		}
	}
		if(list.size()>0)setHasValidationErrors(true);

		setValidationMessages(list);
		
	}

	@Override
	public final void setAuditFields() {
		if(!isFromDB()){
			this.createdBy = AppThreadLocal.getUserName();
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("appointmentId = " + appointmentId + "\n");
;
		str.append("appointmentDate = " + appointmentDate + "\n");
		str.append("appointmentStatus = " + appointmentStatus + "\n");
		str.append("description = " + description + "\n");
		str.append("fromTime = " + fromTime + "\n");
		str.append("schedulehoursId = " + schedulehoursId + "\n");
		str.append("toTime = " + toTime + "\n");
		str.append("userId = " + userId + "\n");
		str.append("roomType = " + roomType + "\n");
		str.append("caretakerId = " + caretakerId + "\n");
		str.append("caretakerName = " + caretakerName + "\n");
		str.append("ChatRoomName = " + chatRoomName + "\n");
		str.append("ChatRoomSID = " + chatRoomSID + "\n");
		str.append("chatType = " + chatType + "\n");
		str.append("chatURL = " + chatURL + "\n");
		str.append("statusCallbackURL = " + statusCallbackURL + "\n");
		str.append("tokenForChat = " + tokenForChat + "\n");
		str.append("patientName = " + patientName + "\n");
		str.append("ailment = " + ailment + "\n");
		str.append("amountPaid = " + amountPaid + "\n");
		str.append("active = " + active + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (appointmentId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_appointmentId, getAppointmentId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getAppointmentId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Appointment appointment = new Appointment();
		appointment.setFromDB(false);
		appointment.setAppointmentDate(getAppointmentDate());
		appointment.setAppointmentStatus(getAppointmentStatus());
		appointment.setDescription(getDescription());
		appointment.setFromTime(getFromTime());
		appointment.setSchedulehoursId(getSchedulehoursId());
		appointment.setToTime(getToTime());
		appointment.setUserId(getUserId());
		appointment.setRoomType(getRoomType());
		appointment.setCaretakerId(getCaretakerId());
		appointment.setCaretakerName(getCaretakerName());
		appointment.setChatRoomName(getChatRoomName());
		appointment.setChatRoomSID(getChatRoomSID());
		appointment.setChatType(getChatType());
		appointment.setChatURL(getChatURL());
		appointment.setStatusCallbackURL(getStatusCallbackURL());
		appointment.setTokenForChat(getTokenForChat());
		appointment.setPatientName(getPatientName());
		appointment.setAilment(getAilment());
		appointment.setAmountPaid(getAmountPaid());
		appointment.setActive(getActive());
		appointment.setCreatedDate(getCreatedDate());
		//afterClone(appointment);
		return appointment;
	}
}