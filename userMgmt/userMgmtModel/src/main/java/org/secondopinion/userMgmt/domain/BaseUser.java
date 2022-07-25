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
import org.secondopinion.userMgmt.dto.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseUser extends BaseDomainObject<Long> {

	public static final String FIELD_userId = "userId";
	public static final String FIELD_firstName = "firstName";
	public static final String FIELD_middleName = "middleName";
	public static final String FIELD_lastName = "lastName";
	public static final String FIELD_emailId = "emailId";
	public static final String FIELD_userName = "userName";
	public static final String FIELD_password = "password";
	public static final String FIELD_companyId = "companyId";
	public static final String FIELD_addressId = "addressId";
	public static final String FIELD_officeNumber = "officeNumber";
	public static final String FIELD_cellNumber = "cellNumber";
	public static final String FIELD_primaryContact = "primaryContact";
	public static final String FIELD_siteAdmin = "siteAdmin";
	public static final String FIELD_active = "active";
	public static final String FIELD_lastLogin = "lastLogin";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String emailId;
//	@QuerySqlField(index=true)
	private String userName;
	private String password;
	private Integer companyId;
	private Long addressId;
	private String officeNumber;
	private String cellNumber;
	private Character primaryContact;
	private Character siteAdmin;
	private Character active;
	private Date lastLogin;

	public void setUserId( Long  _userId){
		this.userId = _userId;
	}
	@Id
 	@Column(name = "userId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getUserId(){
		 return this.userId;
	}
	public void setFirstName( String  _firstName){
		this.firstName = _firstName;
	}
	@NotNull 
	@Length(max=100)
	@Column (name="firstName")
	public String getFirstName(){
		 return this.firstName;
	}
	public void setMiddleName( String  _middleName){
		this.middleName = _middleName;
	}
	@Length(max=100)
	@Column (name="middleName")
	public String getMiddleName(){
		 return this.middleName;
	}
	public void setLastName( String  _lastName){
		this.lastName = _lastName;
	}
	@NotNull 
	@Length(max=100)
	@Column (name="lastName")
	public String getLastName(){
		 return this.lastName;
	}
	public void setEmailId( String  _emailId){
		this.emailId = _emailId;
	}
	@NotNull 
	@Length(max=250)
	@Column (name="emailId")
	public String getEmailId(){
		 return this.emailId;
	}
	public void setUserName( String  _userName){
		this.userName = _userName;
	}
	@NotNull 
	@Length(max=250)
	@Column (name="userName")
	public String getUserName(){
		 return this.userName;
	}
	public void setPassword( String  _password){
		this.password = _password;
	}
	
	@Length(max=250)
	@Column (name="password")
	public String getPassword(){
		 return this.password;
	}
	public void setCompanyId( Integer  _companyId){
		this.companyId = _companyId;
	}
	@NotNull 
	@Column (name="companyId")
	public Integer getCompanyId(){
		 return this.companyId;
	}
	public void setAddressId( Long  _addressId){
		this.addressId = _addressId;
	}
	@Column (name="addressId")
	public Long getAddressId(){
		 return this.addressId;
	}
	public void setOfficeNumber( String  _officeNumber){
		this.officeNumber = _officeNumber;
	}
	@Length(max=45)
	@Column (name="officeNumber")
	public String getOfficeNumber(){
		 return this.officeNumber;
	}
	public void setCellNumber( String  _cellNumber){
		this.cellNumber = _cellNumber;
	}
	@Length(max=45)
	@Column (name="cellNumber")
	public String getCellNumber(){
		 return this.cellNumber;
	}
	public void setPrimaryContact( Character  _primaryContact){
		this.primaryContact = _primaryContact;
	}
	@NotNull 
	@Column (name="primaryContact")
	public Character getPrimaryContact(){
		 return this.primaryContact;
	}
	public void setSiteAdmin( Character  _siteAdmin){
		this.siteAdmin = _siteAdmin;
	}
	@NotNull 
	@Column (name="siteAdmin")
	public Character getSiteAdmin(){
		 return this.siteAdmin;
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
	
	@NotNull 
	@Column (name="lastLogin")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getLastLogin(){
		 return this.lastLogin;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseUser other = (BaseUser)o;
		if(
			ObjectUtil.isEqual(getFirstName(), other.getFirstName()) && 
			ObjectUtil.isEqual(getMiddleName(), other.getMiddleName()) && 
			ObjectUtil.isEqual(getLastName(), other.getLastName()) && 
			ObjectUtil.isEqual(getEmailId(), other.getEmailId()) && 
			ObjectUtil.isEqual(getUserName(), other.getUserName()) && 
			ObjectUtil.isEqual(getPassword(), other.getPassword()) && 
			ObjectUtil.isEqual(getCompanyId(), other.getCompanyId()) && 
			ObjectUtil.isEqual(getAddressId(), other.getAddressId()) && 
			ObjectUtil.isEqual(getOfficeNumber(), other.getOfficeNumber()) && 
			ObjectUtil.isEqual(getCellNumber(), other.getCellNumber()) && 
			ObjectUtil.isEqual(getPrimaryContact(), other.getPrimaryContact()) && 
			ObjectUtil.isEqual(getSiteAdmin(), other.getSiteAdmin()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getLastLogin(), other.getLastLogin()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (userId!= null ? userId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.userId == null){
			 list.add(new ValidationMessage("Field " + FIELD_userId+  " cannot be null"));
		}

		}
		if( StringUtil.isNullOrEmpty(this.firstName)){
			 list.add(new ValidationMessage("Field " + FIELD_firstName+  " cannot be null"));
		}

		if((this.firstName != null) && (this.firstName.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_firstName+  " cannot be longer than: " + 100));
		}

		if((this.middleName != null) && (this.middleName.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_middleName+  " cannot be longer than: " + 100));
		}

		if( StringUtil.isNullOrEmpty(this.lastName)){
			 list.add(new ValidationMessage("Field " + FIELD_lastName+  " cannot be null"));
		}

		if((this.lastName != null) && (this.lastName.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_lastName+  " cannot be longer than: " + 100));
		}

		if( StringUtil.isNullOrEmpty(this.emailId)){
			 list.add(new ValidationMessage("Field " + FIELD_emailId+  " cannot be null"));
		}

		if((this.emailId != null) && (this.emailId.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_emailId+  " cannot be longer than: " + 250));
		}

		if( StringUtil.isNullOrEmpty(this.userName)){
			 list.add(new ValidationMessage("Field " + FIELD_userName+  " cannot be null"));
		}

		if((this.userName != null) && (this.userName.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_userName+  " cannot be longer than: " + 250));
		}

		if( StringUtil.isNullOrEmpty(this.password)){
			 list.add(new ValidationMessage("Field " + FIELD_password+  " cannot be null"));
		}

		if((this.password != null) && (this.password.length()>25)){
			 list.add(new ValidationMessage("Field " + FIELD_password+  " cannot be longer than: " + 25));
		}

		if(this.companyId == null){
			 list.add(new ValidationMessage("Field " + FIELD_companyId+  " cannot be null"));
		}

		if((this.officeNumber != null) && (this.officeNumber.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_officeNumber+  " cannot be longer than: " + 45));
		}

		if((this.cellNumber != null) && (this.cellNumber.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_cellNumber+  " cannot be longer than: " + 45));
		}

		if(this.primaryContact == null){
			 list.add(new ValidationMessage("Field " + FIELD_primaryContact+  " cannot be null"));
		}

		if(this.siteAdmin == null){
			 list.add(new ValidationMessage("Field " + FIELD_siteAdmin+  " cannot be null"));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if(this.lastLogin == null){
			 list.add(new ValidationMessage("Field " + FIELD_lastLogin+  " cannot be null"));
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

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("userId = " + userId + "\n");
;
		str.append("firstName = " + firstName + "\n");
		str.append("middleName = " + middleName + "\n");
		str.append("lastName = " + lastName + "\n");
		str.append("emailId = " + emailId + "\n");
		str.append("userName = " + userName + "\n");
		str.append("password = " + password + "\n");
		str.append("companyId = " + companyId + "\n");
		str.append("addressId = " + addressId + "\n");
		str.append("officeNumber = " + officeNumber + "\n");
		str.append("cellNumber = " + cellNumber + "\n");
		str.append("primaryContact = " + primaryContact + "\n");
		str.append("siteAdmin = " + siteAdmin + "\n");
		str.append("active = " + active + "\n");
		str.append("lastLogin = " + lastLogin + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (userId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_userId, getUserId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getUserId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		User user = new User();
		user.setFromDB(false);
		user.setFirstName(getFirstName());
		user.setMiddleName(getMiddleName());
		user.setLastName(getLastName());
		user.setEmailId(getEmailId());
		user.setUserName(getUserName());
		user.setPassword(getPassword());
		user.setCompanyId(getCompanyId());
		user.setAddressId(getAddressId());
		user.setOfficeNumber(getOfficeNumber());
		user.setCellNumber(getCellNumber());
		user.setPrimaryContact(getPrimaryContact());
		user.setSiteAdmin(getSiteAdmin());
		user.setActive(getActive());
		user.setLastLogin(getLastLogin());
		//afterClone(user);
		return user;
	}
}