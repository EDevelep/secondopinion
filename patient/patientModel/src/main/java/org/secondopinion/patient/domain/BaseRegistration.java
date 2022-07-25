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
import org.secondopinion.patient.dto.Registration;
import org.secondopinion.utils.ObjectUtil; 

import javax.persistence.Column; 
import javax.persistence.MappedSuperclass; 
import javax.validation.constraints.NotNull; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length; 
@MappedSuperclass 
public abstract class BaseRegistration extends BaseDomainObject<Long> {

	public static final String FIELD_registrationId = "registrationId";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_newlyRegistered = "newlyRegistered";
	public static final String FIELD_registeredDate = "registeredDate";
	public static final String FIELD_userName = "userName";
	public static final String FIELD_verificationId = "verificationId";
	public static final String FIELD_verificationNeededBy = "verificationNeededBy";
	public static final String FIELD_active = "active";
	public static final String FIELD_userVerifiedOn = "userVerifiedOn";
	public static final String FIELD_otp = "otp";
	public static final String FIELD_emailotp = "emailotp";
	public static final String FIELD_otpVerified = "otpVerified";
	public static final String FIELD_otpVerifiedOn = "otpVerifiedOn";
	public static final String FIELD_emailVerified = "emailVerified";
	public static final String FIELD_emailVerifiedOn = "emailVerifiedOn";
	public static final String FIELD_doctorApproved = "doctorApproved";
	public static final String FIELD_doctorApprovedOn = "doctorApprovedOn";
	public static final String FIELD_doctorVerified = "doctorVerified";
	public static final String FIELD_doctorVerifiedOn = "doctorVerifiedOn";
	public static final String FIELD_emailId = "emailId";
	public static final String FIELD_phoneNumberVerified = "phoneNumberVerified";
	public static final String FIELD_phoneNumberVerifiedOn = "phoneNumberVerifiedOn";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long registrationId;
	private Long userId;
	private Character active;
	private Character newlyRegistered;
	private Date registeredDate;
	private String userName;
	private String verificationId;
	private Date verificationNeededBy;
	private Character userVerified;
	private Date userVerifiedOn;
	private Integer otp;

	private Integer emailotp;
	private Character otpVerified;
	private Date otpVerifiedOn;
	private Character emailVerified;
	private Date emailVerifiedOn;
	private Character doctorApproved;
	private Date doctorApprovedOn;
	private Character doctorVerified;
	private Date doctorVerifiedOn;
	private String emailId;
	private Character phoneNumberVerified;
	private Date phoneNumberVerifiedOn;

	
	public void setActive(Character _active) {
		this.active = _active;
	}
	
	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	public void setRegistrationId( Long  _registrationId){
		this.registrationId = _registrationId;
	}
	@Id
 	@Column(name = "registrationId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getRegistrationId(){
		 return this.registrationId;
	}
	public void setUserId( Long  _userId){
		this.userId = _userId;
	}
	@NotNull 
	@Column (name="userId")
	public Long getUserId(){
		 return this.userId;
	}
	public void setNewlyRegistered( Character  _newlyRegistered){
		this.newlyRegistered = _newlyRegistered;
	}
	@NotNull 
	@Column (name="newlyRegistered")
	public Character getNewlyRegistered(){
		 return this.newlyRegistered;
	}
	public void setRegisteredDate( Date  _registeredDate){
		this.registeredDate = _registeredDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="registeredDate")
	public Date getRegisteredDate(){
		 return this.registeredDate;
	}
	public void setUserName( String  _userName){
		this.userName = _userName;
	}
	@Length(max=45)
	@Column (name="userName")
	public String getUserName(){
		 return this.userName;
	}
	public void setVerificationId( String  _verificationId){
		this.verificationId = _verificationId;
	}
	@Length(max=250)
	@Column (name="verificationId")
	public String getVerificationId(){
		 return this.verificationId;
	}
	public void setVerificationNeededBy( Date  _verificationNeededBy){
		this.verificationNeededBy = _verificationNeededBy;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="verificationNeededBy")
	public Date getVerificationNeededBy(){
		 return this.verificationNeededBy;
	}
	public void setUserVerified( Character  _userVerified){
		this.userVerified = _userVerified;
	}
	@Column (name="userVerified")
	public Character getUserVerified(){
		 return this.userVerified;
	}
	public void setUserVerifiedOn( Date  _userVerifiedOn){
		this.userVerifiedOn = _userVerifiedOn;
	}
	//@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="userVerifiedOn")
	public Date getUserVerifiedOn(){
		 return this.userVerifiedOn;
	}
	public void setOtp( Integer  _otp){
		this.otp = _otp;
	}
	@Column (name="otp")
	public Integer getOtp(){
		 return this.otp;
	}
	@Column (name="emailotp")
	public Integer getEmailotp() {
		return emailotp;
	}
	public void setEmailotp(Integer emailotp) {
		this.emailotp = emailotp;
	}

	public void setOtpVerified( Character  _otpVerified){
		this.otpVerified = _otpVerified;
	}
	@Column (name="otpVerified")
	public Character getOtpVerified(){
		 return this.otpVerified;
	}
	public void setOtpVerifiedOn( Date  _otpVerifiedOn){
		this.otpVerifiedOn = _otpVerifiedOn;
	}
	//@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="otpVerifiedOn")
	public Date getOtpVerifiedOn(){
		 return this.otpVerifiedOn;
	}
	public void setEmailVerified( Character  _emailVerified){
		this.emailVerified = _emailVerified;
	}
	@Column (name="emailVerified")
	public Character getEmailVerified(){
		 return this.emailVerified;
	}
	public void setEmailVerifiedOn( Date  _emailVerifiedOn){
		this.emailVerifiedOn = _emailVerifiedOn;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="emailVerifiedOn")
	public Date getEmailVerifiedOn(){
		 return this.emailVerifiedOn;
	}
	public void setDoctorApproved( Character  _doctorApproved){
		this.doctorApproved = _doctorApproved;
	}
	@Column (name="doctorApproved")
	public Character getDoctorApproved(){
		 return this.doctorApproved;
	}
	public void setDoctorApprovedOn( Date  _doctorApprovedOn){
		this.doctorApprovedOn = _doctorApprovedOn;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="doctorApprovedOn")
	public Date getDoctorApprovedOn(){
		 return this.doctorApprovedOn;
	}
	public void setDoctorVerified( Character  _doctorVerified){
		this.doctorVerified = _doctorVerified;
	}
	@Column (name="doctorVerified")
	public Character getDoctorVerified(){
		 return this.doctorVerified;
	}
	public void setDoctorVerifiedOn( Date  _doctorVerifiedOn){
		this.doctorVerifiedOn = _doctorVerifiedOn;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="doctorVerifiedOn")
	public Date getDoctorVerifiedOn(){
		 return this.doctorVerifiedOn;
	}
	public void setEmailId( String  _emailId){
		this.emailId = _emailId;
	}
	@Length(max=45)
	@Column (name="emailId")
	public String getEmailId(){
		 return this.emailId;
	}
	public void setPhoneNumberVerified( Character  _phoneNumberVerified){
		this.phoneNumberVerified = _phoneNumberVerified;
	}
	@Column (name="phoneNumberVerified")
	public Character getPhoneNumberVerified(){
		 return this.phoneNumberVerified;
	}
	public void setPhoneNumberVerifiedOn( Date  _phoneNumberVerifiedOn){
		this.phoneNumberVerifiedOn = _phoneNumberVerifiedOn;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="phoneNumberVerifiedOn")
	public Date getPhoneNumberVerifiedOn(){
		 return this.phoneNumberVerifiedOn;
	}
	

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseRegistration other = (BaseRegistration)o;
		if(
			ObjectUtil.isEqual(getUserId(), other.getUserId()) && 
			ObjectUtil.isEqual(getNewlyRegistered(), other.getNewlyRegistered()) && 
			ObjectUtil.isEqual(getRegisteredDate(), other.getRegisteredDate()) && 
			ObjectUtil.isEqual(getUserName(), other.getUserName()) && 
			ObjectUtil.isEqual(getVerificationId(), other.getVerificationId()) && 
			ObjectUtil.isEqual(getVerificationNeededBy(), other.getVerificationNeededBy()) && 
			ObjectUtil.isEqual(getUserVerifiedOn(), other.getUserVerifiedOn()) && 
			ObjectUtil.isEqual(getOtp(), other.getOtp()) && 
			ObjectUtil.isEqual(getEmailotp(), other.getEmailotp())&&
			ObjectUtil.isEqual(getOtpVerified(), other.getOtpVerified()) && 
			ObjectUtil.isEqual(getOtpVerifiedOn(), other.getOtpVerifiedOn()) && 
			ObjectUtil.isEqual(getEmailVerified(), other.getEmailVerified()) && 
			ObjectUtil.isEqual(getEmailVerifiedOn(), other.getEmailVerifiedOn()) && 
			ObjectUtil.isEqual(getDoctorApproved(), other.getDoctorApproved()) && 
			ObjectUtil.isEqual(getDoctorApprovedOn(), other.getDoctorApprovedOn()) && 
			ObjectUtil.isEqual(getDoctorVerified(), other.getDoctorVerified()) && 
			ObjectUtil.isEqual(getDoctorVerifiedOn(), other.getDoctorVerifiedOn()) && 
			ObjectUtil.isEqual(getEmailId(), other.getEmailId()) && 
			ObjectUtil.isEqual(getPhoneNumberVerified(), other.getPhoneNumberVerified()) && 
			ObjectUtil.isEqual(getPhoneNumberVerifiedOn(), other.getPhoneNumberVerifiedOn()) && 
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
		result = result + (registrationId!= null ? registrationId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.registrationId == null){
			 list.add(new ValidationMessage("Field " + FIELD_registrationId+  " cannot be null"));
		}

		}
		if(this.userId == null){
			 list.add(new ValidationMessage("Field " + FIELD_userId+  " cannot be null"));
		}

		if(this.newlyRegistered == null){
			 list.add(new ValidationMessage("Field " + FIELD_newlyRegistered+  " cannot be null"));
		}

		if((this.userName != null) && (this.userName.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_userName+  " cannot be longer than: " + 45));
		}

		if((this.verificationId != null) && (this.verificationId.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_verificationId+  " cannot be longer than: " + 250));
		}

		if((this.emailId != null) && (this.emailId.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_emailId+  " cannot be longer than: " + 45));
		}

	if(this.isFromDB()){
		if((this.createdBy != null) && (this.createdBy.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 255));
		}
	}
	if(this.isFromDB()){
		if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 255));
		}
	}
		if(this.isFromDB()){
			if(this.lastUpdatedTs == null){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedTs+  " cannot be null"));
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
		str.append("registrationId = " + registrationId + "\n");
;
		str.append("userId = " + userId + "\n");
		str.append("newlyRegistered = " + newlyRegistered + "\n");
		str.append("registeredDate = " + registeredDate + "\n");
		str.append("userName = " + userName + "\n");
		str.append("verificationId = " + verificationId + "\n");
		str.append("verificationNeededBy = " + verificationNeededBy + "\n");
		str.append("active = " + active + "\n");
		str.append("userVerifiedOn = " + userVerifiedOn + "\n");
		str.append("otp = " + otp + "\n");
		str.append("emailotp = " + emailotp + "\n");
		str.append("otpVerified = " + otpVerified + "\n");
		str.append("otpVerifiedOn = " + otpVerifiedOn + "\n");
		str.append("emailVerified = " + emailVerified + "\n");
		str.append("emailVerifiedOn = " + emailVerifiedOn + "\n");
		str.append("doctorApproved = " + doctorApproved + "\n");
		str.append("doctorApprovedOn = " + doctorApprovedOn + "\n");
		str.append("doctorVerified = " + doctorVerified + "\n");
		str.append("doctorVerifiedOn = " + doctorVerifiedOn + "\n");
		str.append("emailId = " + emailId + "\n");
		str.append("phoneNumberVerified = " + phoneNumberVerified + "\n");
		str.append("phoneNumberVerifiedOn = " + phoneNumberVerifiedOn + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (registrationId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_registrationId, getRegistrationId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getRegistrationId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Registration registration = new Registration();
		registration.setFromDB(false);
		registration.setUserId(getUserId());
		registration.setNewlyRegistered(getNewlyRegistered());
		registration.setRegisteredDate(getRegisteredDate());
		registration.setUserName(getUserName());
		registration.setVerificationId(getVerificationId());
		registration.setVerificationNeededBy(getVerificationNeededBy());
		registration.setUserVerified(getUserVerified());
		registration.setUserVerifiedOn(getUserVerifiedOn());
		registration.setOtp(getOtp());
		registration.setEmailotp(getEmailotp());
		registration.setOtpVerified(getOtpVerified());
		registration.setOtpVerifiedOn(getOtpVerifiedOn());
		registration.setEmailVerified(getEmailVerified());
		registration.setEmailVerifiedOn(getEmailVerifiedOn());
		registration.setDoctorApproved(getDoctorApproved());
		registration.setDoctorApprovedOn(getDoctorApprovedOn());
		registration.setDoctorVerified(getDoctorVerified());
		registration.setDoctorVerifiedOn(getDoctorVerifiedOn());
		registration.setEmailId(getEmailId());
		registration.setPhoneNumberVerified(getPhoneNumberVerified());
		registration.setPhoneNumberVerifiedOn(getPhoneNumberVerifiedOn());
		registration.setActive(getActive());
		//afterClone(registration);
		return registration;
	}
}