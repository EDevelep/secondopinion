package org.secondopinion.diagnosticcenter.domain; 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.enums.PrimaryContactEnum;
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
@MappedSuperclass 
public abstract class BaseDiagnosticcenteruser extends BaseDomainObject<Long> {

	
	
	public static final String FIELD_diagnosticCenterUserId = "diagnosticCenterUserId";
	public static final String FIELD_diagnosticCenterAddressId = "diagnosticCenterAddressId";
	public static final String FIELD_emailId = "emailId";
	public static final String FIELD_password = "password";
	public static final String FIELD_firstName = "firstName";
	public static final String FIELD_middleName = "middleName";
	public static final String FIELD_lastName = "lastName";
	public static final String FIELD_cellNumber = "cellNumber";
	public static final String FIELD_primaryContact = "primaryContact";
	public static final String FIELD_homeNumber = "homeNumber";
	public static final String FIELD_officeNumber = "officeNumber";
	public static final String FIELD_verificationId = "verificationId";
	public static final String FIELD_active = "active";
	public static final String FIELD_lastLogin = "lastLogin";
	public static final String FIELD_locked = "locked";
	public static final String FIELD_gst_inr = "gstInr";
	public static final String FIELD_otp = "otp";
	public static final String FIELD_emailotp = "emailotp";
	public static final String FIELD_retry = "retry";
	public static final String FIELD_diagnosticName = "diagnosticName";
	public static final String FIELD_licencenumber = "licencenumber";

	private Long diagnosticCenterUserId;
	private Long diagnosticCenterAddressId;
	private String emailId;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String cellNumber;
	private String officeNumber;
	private String homeNumber;
	private String primaryContact;//it should be enum home, cell, office
	private String verificationId;
	private Character active;
	private Date lastLogin;
	private Character locked;
	private String gstInr;
	private Integer retry;
	private String diagnosticName;
	private String licencenumber;
	private Integer otp;
	private Integer emailotp;
	
	public void setDiagnosticCenterUserId( Long  _diagnosticCenterUserId){
		this.diagnosticCenterUserId = _diagnosticCenterUserId;
	}
	
	@Id
 	@Column(name = "diagnosticCenterUserId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDiagnosticCenterUserId(){
		 return this.diagnosticCenterUserId;
	}
	public void setDiagnosticCenterAddressId( Long  _diagnosticCenterAddressId){
		this.diagnosticCenterAddressId = _diagnosticCenterAddressId;
	}
	@NotNull 
	@Column (name="diagnosticCenterAddressId")
	public Long getDiagnosticCenterAddressId(){
		 return this.diagnosticCenterAddressId;
	}
	public void setEmailId( String  _emailId){
		this.emailId = _emailId;
	}
	@Length(max=1000)
	@Column (name="emailId")
	public String getEmailId(){
		 return this.emailId;
	}
	public void setPassword( String  _password){
		this.password = _password;
	}
	@NotNull 
	@Length(max=245)
	@Column (name="password")
	public String getPassword(){
		 return this.password;
	}
	public void setFirstName( String  _firstName){
		this.firstName = _firstName;
	}
	@Length(max=1000)
	@Column (name="firstName")
	public String getFirstName(){
		 return this.firstName;
	}
	public void setMiddleName( String  _middleName){
		this.middleName = _middleName;
	}
	@Length(max=1000)
	@Column (name="middleName")
	public String getMiddleName(){
		 return this.middleName;
	}
	public void setLastName( String  _lastName){
		this.lastName = _lastName;
	}
	@Length(max=1000)
	@Column (name="lastName")
	public String getLastName(){
		 return this.lastName;
	}
	public void setCellNumber( String  _cellNumber){
		this.cellNumber = _cellNumber;
	}
	@Length(max=180)
	@Column (name="cellNumber")
	public String getCellNumber(){
		 return this.cellNumber;
	}
	public void setPrimaryContact( String  _primaryContact){
		this.primaryContact = _primaryContact;
	}
	@Column (name="primaryContact")
	public String getPrimaryContact(){
		 return this.primaryContact;
	}
	public void setHomeNumber( String  _homeNumber){
		this.homeNumber = _homeNumber;
	}
	@Length(max=180)
	@Column (name="homeNumber")
	public String getHomeNumber(){
		 return this.homeNumber;
	}
	public void setOfficeNumber( String  _officeNumber){
		this.officeNumber = _officeNumber;
	}
	@Length(max=180)
	@Column (name="officeNumber")
	public String getOfficeNumber(){
		 return this.officeNumber;
	}
	public void setVerificationId( String  _verificationId){
		this.verificationId = _verificationId;
	}
	@Length(max=255)
	@Column (name="verificationId")
	public String getVerificationId(){
		 return this.verificationId;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setLastLogin( Date  _lastLogin){
		this.lastLogin = _lastLogin;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="lastLogin")
	public Date getLastLogin(){
		 return this.lastLogin;
	}
	public void setLocked( Character  _locked){
		this.locked = _locked;
	}
	@NotNull 
	@Column (name="locked")
	public Character getLocked(){
		 return this.locked;
	}
	public void setGstInr( String  _gstInr){
		this.gstInr = _gstInr;
	}
	@Length(max=45)
	@Column (name="gst_inr")
	public String getGstInr(){
		 return this.gstInr;
	}
	public void setRetry( Integer  _retry){
		this.retry = _retry;
	}
	@Column (name="retry")
	public Integer getRetry(){
		 return this.retry;
	}
	public void setDiagnosticName( String  _diagnosticName){
		this.diagnosticName = _diagnosticName;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="diagnosticName")
	public String getDiagnosticName(){
		 return this.diagnosticName;
	}
	public void setLicencenumber( String  _licencenumber){
		this.licencenumber = _licencenumber;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="licencenumber")
	public String getLicencenumber(){
		 return this.licencenumber;
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

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseDiagnosticcenteruser other = (BaseDiagnosticcenteruser)o;
		if(
			ObjectUtil.isEqual(getDiagnosticCenterAddressId(), other.getDiagnosticCenterAddressId()) && 
			ObjectUtil.isEqual(getEmailId(), other.getEmailId()) && 
			ObjectUtil.isEqual(getPassword(), other.getPassword()) && 
			ObjectUtil.isEqual(getFirstName(), other.getFirstName()) && 
			ObjectUtil.isEqual(getMiddleName(), other.getMiddleName()) && 
			ObjectUtil.isEqual(getLastName(), other.getLastName()) && 
			ObjectUtil.isEqual(getCellNumber(), other.getCellNumber()) && 
			ObjectUtil.isEqual(getPrimaryContact(), other.getPrimaryContact()) && 
			ObjectUtil.isEqual(getHomeNumber(), other.getHomeNumber()) && 
			ObjectUtil.isEqual(getOfficeNumber(), other.getOfficeNumber()) && 
			ObjectUtil.isEqual(getVerificationId(), other.getVerificationId()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getLastLogin(), other.getLastLogin()) && 
			ObjectUtil.isEqual(getLocked(), other.getLocked()) && 
			ObjectUtil.isEqual(getGstInr(), other.getGstInr()) && 
			ObjectUtil.isEqual(getRetry(), other.getRetry()) && 
			ObjectUtil.isEqual(getDiagnosticName(), other.getDiagnosticName()) && 
			ObjectUtil.isEqual(getLicencenumber(), other.getLicencenumber()) && 
			ObjectUtil.isEqual(getOtp(), other.getOtp()) && 
			ObjectUtil.isEqual(getEmailotp(), other.getEmailotp()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (diagnosticCenterUserId!= null ? diagnosticCenterUserId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.diagnosticCenterUserId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticCenterUserId+  " cannot be null"));
		}

		}
		if(this.diagnosticCenterAddressId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticCenterAddressId+  " cannot be null"));
		}

		if((this.emailId != null) && (this.emailId.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_emailId+  " cannot be longer than: " + 1000));
		}

		if( StringUtil.isNullOrEmpty(this.password)){
			 list.add(new ValidationMessage("Field " + FIELD_password+  " cannot be null"));
		}

		if((this.password != null) && (this.password.length()>245)){
			 list.add(new ValidationMessage("Field " + FIELD_password+  " cannot be longer than: " + 245));
		}
		if(StringUtil.isNullOrEmpty(this.firstName)){
			 list.add(new ValidationMessage("Field " + FIELD_firstName+  " cannot be null. "));
		}
		if((this.firstName != null) && (this.firstName.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_firstName+  " cannot be longer than: " + 1000));
		}

		if((this.middleName != null) && (this.middleName.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_middleName+  " cannot be longer than: " + 1000));
		}

		if((this.lastName != null) && (this.lastName.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_lastName+  " cannot be longer than: " + 1000));
		}
		if(StringUtil.isNullOrEmpty(this.cellNumber)){
			 list.add(new ValidationMessage("Field " + FIELD_cellNumber+  " cannot be null"));
		}
		if((this.cellNumber != null) && (this.cellNumber.length()>180)){
			 list.add(new ValidationMessage("Field " + FIELD_cellNumber+  " cannot be longer than: " + 180));
		}

		if((this.primaryContact == null)){
			 list.add(new ValidationMessage("Field " + FIELD_primaryContact+  " cannot be null"));
		}
		List<String> pcEnums = Arrays.stream(PrimaryContactEnum.values()).map(pc -> pc.name()).collect(Collectors.toList());
		if(StringUtil.isNullOrEmpty(this.primaryContact) || !pcEnums.contains(this.primaryContact)) {
			list.add(new ValidationMessage("Field " + FIELD_primaryContact + " either can not be null or it should be in " + pcEnums));
		}
		if(StringUtil.isNullOrEmpty(this.homeNumber)){
			 list.add(new ValidationMessage("Field " + FIELD_homeNumber+  " cannot be null "));
		}
		if((this.homeNumber != null) && (this.homeNumber.length()>180)){
			 list.add(new ValidationMessage("Field " + FIELD_homeNumber+  " cannot be longer than: " + 180));
		}

		if((this.officeNumber != null) && (this.officeNumber.length()>180)){
			 list.add(new ValidationMessage("Field " + FIELD_officeNumber+  " cannot be longer than: " + 180));
		}
		if(StringUtil.isNullOrEmpty(this.verificationId)){
			 list.add(new ValidationMessage("Field " + FIELD_verificationId+  " cannot be null. "));
		}
		if((this.verificationId != null) && (this.verificationId.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_verificationId+  " cannot be longer than: " + 255));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if(this.locked == null){
			 list.add(new ValidationMessage("Field " + FIELD_locked+  " cannot be null"));
		}

		if((this.gstInr != null) && (this.gstInr.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_gst_inr+  " cannot be longer than: " + 45));
		}

		if( StringUtil.isNullOrEmpty(this.diagnosticName)){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticName+  " cannot be null"));
		}

		if((this.diagnosticName != null) && (this.diagnosticName.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticName+  " cannot be longer than: " + 45));
		}

		if( StringUtil.isNullOrEmpty(this.licencenumber)){
			 list.add(new ValidationMessage("Field " + FIELD_licencenumber+  " cannot be null"));
		}

		if((this.licencenumber != null) && (this.licencenumber.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_licencenumber+  " cannot be longer than: " + 45));
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
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("diagnosticCenterUserId = " + diagnosticCenterUserId + "\n");
;
		str.append("diagnosticCenterAddressId = " + diagnosticCenterAddressId + "\n");
		str.append("emailId = " + emailId + "\n");
		str.append("password = " + password + "\n");
		str.append("firstName = " + firstName + "\n");
		str.append("middleName = " + middleName + "\n");
		str.append("lastName = " + lastName + "\n");
		str.append("cellNumber = " + cellNumber + "\n");
		str.append("primaryContact = " + primaryContact + "\n");
		str.append("homeNumber = " + homeNumber + "\n");
		str.append("officeNumber = " + officeNumber + "\n");
		str.append("verificationId = " + verificationId + "\n");
		str.append("active = " + active + "\n");
		str.append("lastLogin = " + lastLogin + "\n");
		str.append("locked = " + locked + "\n");
		str.append("gstInr = " + gstInr + "\n");
		str.append("retry = " + retry + "\n");
		str.append("diagnosticName = " + diagnosticName + "\n");
		str.append("licencenumber = " + licencenumber + "\n");
		str.append("otp = " + otp + "\n");
		str.append("emailotp = " + emailotp + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (diagnosticCenterUserId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_diagnosticCenterUserId, getDiagnosticCenterUserId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getDiagnosticCenterUserId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Diagnosticcenteruser diagnosticcenteruser = new Diagnosticcenteruser();
		diagnosticcenteruser.setFromDB(false);
		diagnosticcenteruser.setDiagnosticCenterAddressId(getDiagnosticCenterAddressId());
		diagnosticcenteruser.setEmailId(getEmailId());
		diagnosticcenteruser.setPassword(getPassword());
		diagnosticcenteruser.setFirstName(getFirstName());
		diagnosticcenteruser.setMiddleName(getMiddleName());
		diagnosticcenteruser.setLastName(getLastName());
		diagnosticcenteruser.setCellNumber(getCellNumber());
		diagnosticcenteruser.setPrimaryContact(getPrimaryContact());
		diagnosticcenteruser.setHomeNumber(getHomeNumber());
		diagnosticcenteruser.setOfficeNumber(getOfficeNumber());
		diagnosticcenteruser.setVerificationId(getVerificationId());
		diagnosticcenteruser.setActive(getActive());
		diagnosticcenteruser.setLastLogin(getLastLogin());
		diagnosticcenteruser.setLocked(getLocked());
		diagnosticcenteruser.setGstInr(getGstInr());
		diagnosticcenteruser.setRetry(getRetry());
		diagnosticcenteruser.setDiagnosticName(getDiagnosticName());
		diagnosticcenteruser.setLicencenumber(getLicencenumber());
		diagnosticcenteruser.setOtp(getOtp());
		diagnosticcenteruser.setEmailotp(getEmailotp());
		//afterClone(diagnosticcenteruser);
		return diagnosticcenteruser;
	}
}