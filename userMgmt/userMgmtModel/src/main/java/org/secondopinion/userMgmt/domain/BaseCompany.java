package org.secondopinion.userMgmt.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

//import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.hibernate.validator.constraints.Length;
import org.secondopinion.userMgmt.dto.Company;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseCompany extends BaseDomainObject<Integer> {

	public static final String FIELD_companyId = "companyId";
	public static final String FIELD_name = "name";
	public static final String FIELD_emailId = "emailId";
	public static final String FIELD_alternateEmailForMailForwarding = "alternateEmailForMailForwarding";
	public static final String FIELD_type = "type";
	public static final String FIELD_phoneNum = "phoneNum";
	public static final String FIELD_faxNum = "faxNum";
	public static final String FIELD_websiteURL = "websiteURL";
	public static final String FIELD_logoImageUrl = "logoImageUrl";
	public static final String FIELD_logo = "logo";
	public static final String FIELD_disclaimer = "disclaimer";
	public static final String FIELD_newlyRegistered = "newlyRegistered";
	public static final String FIELD_approved = "approved";
	public static final String FIELD_active = "active";
	public static final String FIELD_verificationId = "verificationId";
	public static final String FIELD_verifyBy = "verifyBy";
	public static final String FIELD_verificationCompleted = "verificationCompleted";
	public static final String FIELD_profileCreatedDate = "profileCreatedDate";
	public static final String FIELD_setupComplete = "setupComplete";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

//	@QuerySqlField(index=true)
	private Integer companyId;
	private String name;
//	@QuerySqlField(index=true)
	private String emailId;
	private String alternateEmailForMailForwarding;
	private String type;
	private String phoneNum;
	private String faxNum;
	private String websiteURL;
	private String logoImageUrl;
	private String logo;
	private String disclaimer;
	private Character newlyRegistered;
	private Character approved;
	private Character active;
	private String verificationId;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date verifyBy;
	private Character verificationCompleted;
	private Date profileCreatedDate;
	private Character setupComplete;

	public void setCompanyId( Integer  _companyId){
		this.companyId = _companyId;
	}
	@Id
	@Column(name = "companyId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCompanyId(){
		 return this.companyId;
	}
	public void setName( String  _name){
		this.name = _name;
	}
	@NotNull 
	@Length(max=250)
	@Column (name="name")
	public String getName(){
		 return this.name;
	}
	public void setEmailId( String  _emailId){
		this.emailId = _emailId;
	}
	@NotNull 
	@Length(max=100)
	@Column (name="emailId")
	public String getEmailId(){
		 return this.emailId;
	}
	public void setAlternateEmailForMailForwarding( String  _alternateEmailForMailForwarding){
		this.alternateEmailForMailForwarding = _alternateEmailForMailForwarding;
	}
	@Length(max=250)
	@Column (name="alternateEmailForMailForwarding")
	public String getAlternateEmailForMailForwarding(){
		 return this.alternateEmailForMailForwarding;
	}
	public void setType( String  _type){
		this.type = _type;
	}
	@NotNull 
	@Length(max=50)
	@Column (name="type")
	public String getType(){
		 return this.type;
	}
	public void setPhoneNum( String  _phoneNum){
		this.phoneNum = _phoneNum;
	}
	@Length(max=45)
	@Column (name="phoneNum")
	public String getPhoneNum(){
		 return this.phoneNum;
	}
	public void setFaxNum( String  _faxNum){
		this.faxNum = _faxNum;
	}
	@Length(max=45)
	@Column (name="faxNum")
	public String getFaxNum(){
		 return this.faxNum;
	}
	public void setWebsiteURL( String  _websiteURL){
		this.websiteURL = _websiteURL;
	}
	@NotNull 
	@Length(max=250)
	@Column (name="websiteURL")
	public String getWebsiteURL(){
		 return this.websiteURL;
	}
	public void setLogoImageUrl( String  _logoImageUrl){
		this.logoImageUrl = _logoImageUrl;
	}
	@Length(max=250)
	@Column (name="logoImageUrl")
	public String getLogoImageUrl(){
		 return this.logoImageUrl;
	}
	public void setLogo( String  _logo){
		this.logo = _logo;
	}
	@Length(max=1024)
	@Column (name="logo")
	public String getLogo(){
		 return this.logo;
	}
	public void setDisclaimer( String  _disclaimer){
		this.disclaimer = _disclaimer;
	}
	@Length(max=2000)
	@Column (name="disclaimer")
	public String getDisclaimer(){
		 return this.disclaimer;
	}
	public void setNewlyRegistered( Character  _newlyRegistered){
		this.newlyRegistered = _newlyRegistered;
	}
	@NotNull 
	@Column (name="newlyRegistered")
	public Character getNewlyRegistered(){
		 return this.newlyRegistered;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setVerificationId( String  _verificationId){
		this.verificationId = _verificationId;
	}
	@Length(max=125)
	@Column (name="verificationId")
	public String getVerificationId(){
		 return this.verificationId;
	}
	public void setVerifyBy( Date  _verifyBy){
		this.verifyBy = _verifyBy;
	}
	@NotNull 
	@Column (name="verifyBy")
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	public Date getVerifyBy(){
		 return this.verifyBy;
	}
	public void setVerificationCompleted( Character  _verificationCompleted){
		this.verificationCompleted = _verificationCompleted;
	}
	@NotNull 
	@Column (name="verificationCompleted")
	public Character getVerificationCompleted(){
		 return this.verificationCompleted;
	}
	public void setProfileCreatedDate( Date  _profileCreatedDate){
		this.profileCreatedDate = _profileCreatedDate;
	}
	@Column (name="profileCreatedDate")
	public Date getProfileCreatedDate(){
		 return this.profileCreatedDate;
	}
	public void setSetupComplete( Character  _setupComplete){
		this.setupComplete = _setupComplete;
	}
	@NotNull 
	@Column (name="setupComplete")
	public Character getSetupComplete(){
		 return this.setupComplete;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseCompany other = (BaseCompany)o;
		if(
			ObjectUtil.isEqual(getName(), other.getName()) && 
			ObjectUtil.isEqual(getEmailId(), other.getEmailId()) && 
			ObjectUtil.isEqual(getAlternateEmailForMailForwarding(), other.getAlternateEmailForMailForwarding()) && 
			ObjectUtil.isEqual(getType(), other.getType()) && 
			ObjectUtil.isEqual(getPhoneNum(), other.getPhoneNum()) && 
			ObjectUtil.isEqual(getFaxNum(), other.getFaxNum()) && 
			ObjectUtil.isEqual(getWebsiteURL(), other.getWebsiteURL()) && 
			ObjectUtil.isEqual(getLogoImageUrl(), other.getLogoImageUrl()) && 
			ObjectUtil.isEqual(getLogo(), other.getLogo()) && 
			ObjectUtil.isEqual(getDisclaimer(), other.getDisclaimer()) && 
			ObjectUtil.isEqual(getNewlyRegistered(), other.getNewlyRegistered()) && 
			ObjectUtil.isEqual(getApproved(), other.getApproved()) &&
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getVerificationId(), other.getVerificationId()) && 
			ObjectUtil.isEqual(getVerifyBy(), other.getVerifyBy()) && 
			ObjectUtil.isEqual(getVerificationCompleted(), other.getVerificationCompleted()) && 
			ObjectUtil.isEqual(getProfileCreatedDate(), other.getProfileCreatedDate()) && 
			ObjectUtil.isEqual(getSetupComplete(), other.getSetupComplete()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (companyId!= null ? companyId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.companyId == null){
			 list.add(new ValidationMessage("Field " + FIELD_companyId+  " cannot be null"));
		}

		}
		if( StringUtil.isNullOrEmpty(this.name)){
			 list.add(new ValidationMessage("Field " + FIELD_name+  " cannot be null"));
		}

		if((this.name != null) && (this.name.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_name+  " cannot be longer than: " + 250));
		}

		if( StringUtil.isNullOrEmpty(this.emailId)){
			 list.add(new ValidationMessage("Field " + FIELD_emailId+  " cannot be null"));
		}

		if((this.emailId != null) && (this.emailId.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_emailId+  " cannot be longer than: " + 100));
		}

		if((this.alternateEmailForMailForwarding != null) && (this.alternateEmailForMailForwarding.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_alternateEmailForMailForwarding+  " cannot be longer than: " + 250));
		}

		if( StringUtil.isNullOrEmpty(this.type)){
			 list.add(new ValidationMessage("Field " + FIELD_type+  " cannot be null"));
		}

		if((this.type != null) && (this.type.length()>50)){
			 list.add(new ValidationMessage("Field " + FIELD_type+  " cannot be longer than: " + 50));
		}

		if((this.phoneNum != null) && (this.phoneNum.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_phoneNum+  " cannot be longer than: " + 45));
		}

		if((this.faxNum != null) && (this.faxNum.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_faxNum+  " cannot be longer than: " + 45));
		}

		if( StringUtil.isNullOrEmpty(this.websiteURL)){
			 list.add(new ValidationMessage("Field " + FIELD_websiteURL+  " cannot be null"));
		}

		if((this.websiteURL != null) && (this.websiteURL.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_websiteURL+  " cannot be longer than: " + 250));
		}

		if((this.logoImageUrl != null) && (this.logoImageUrl.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_logoImageUrl+  " cannot be longer than: " + 250));
		}

		if((this.logo != null) && (this.logo.length()>1024)){
			 list.add(new ValidationMessage("Field " + FIELD_logo+  " cannot be longer than: " + 1024));
		}

		if((this.disclaimer != null) && (this.disclaimer.length()>2000)){
			 list.add(new ValidationMessage("Field " + FIELD_disclaimer+  " cannot be longer than: " + 2000));
		}

		if(this.newlyRegistered == null){
			 list.add(new ValidationMessage("Field " + FIELD_newlyRegistered+  " cannot be null"));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if((this.verificationId != null) && (this.verificationId.length()>125)){
			 list.add(new ValidationMessage("Field " + FIELD_verificationId+  " cannot be longer than: " + 125));
		}

		if(this.verifyBy == null){
			 list.add(new ValidationMessage("Field " + FIELD_verifyBy+  " cannot be null"));
		}

		if(this.verificationCompleted == null){
			 list.add(new ValidationMessage("Field " + FIELD_verificationCompleted+  " cannot be null"));
		}

		if(this.setupComplete == null){
			 list.add(new ValidationMessage("Field " + FIELD_setupComplete+  " cannot be null"));
		}

		if(this.isFromDB()){
			if( StringUtil.isNullOrEmpty(this.lastUpdatedBy)){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be null"));
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
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseCompany [companyId=" + companyId + ", name=" + name + ", emailId=" + emailId
				+ ", alternateEmailForMailForwarding=" + alternateEmailForMailForwarding + ", type=" + type
				+ ", phoneNum=" + phoneNum + ", faxNum=" + faxNum + ", websiteURL=" + websiteURL + ", logoImageUrl="
				+ logoImageUrl + ", logo=" + logo + ", disclaimer=" + disclaimer + ", newlyRegistered="
				+ newlyRegistered + ", approved=" + approved + ", active=" + active + ", verificationId="
				+ verificationId + ", verifyBy=" + verifyBy + ", verificationCompleted=" + verificationCompleted
				+ ", profileCreatedDate=" + profileCreatedDate + ", setupComplete=" + setupComplete + "]";
	}
	@Transient
	@Override
	public final boolean isKeyNull(){
		return (companyId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_companyId, getCompanyId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getCompanyId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Company company = new Company();
		company.setFromDB(false);
		company.setName(getName());
		company.setEmailId(getEmailId());
		company.setAlternateEmailForMailForwarding(getAlternateEmailForMailForwarding());
		company.setType(getType());
		company.setPhoneNum(getPhoneNum());
		company.setFaxNum(getFaxNum());
		company.setWebsiteURL(getWebsiteURL());
		company.setLogoImageUrl(getLogoImageUrl());
		company.setLogo(getLogo());
		company.setDisclaimer(getDisclaimer());
		company.setNewlyRegistered(getNewlyRegistered());
		company.setActive(getActive());
		company.setVerificationId(getVerificationId());
		company.setVerifyBy(getVerifyBy());
		company.setVerificationCompleted(getVerificationCompleted());
		company.setProfileCreatedDate(getProfileCreatedDate());
		company.setSetupComplete(getSetupComplete());
		company.setApproved(getApproved());
		//afterClone(company);
		return company;
	}
	
	@Column (name="approved")
	public Character getApproved() {
		return approved;
	}
	public void setApproved(Character approved) {
		this.approved = approved;
	}
}