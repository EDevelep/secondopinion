package org.secondopinion.diagnosticcenter.domain; 

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

import org.hibernate.validator.constraints.Length;
import org.secondopinion.diagnosticcenter.dto.Useraddress;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseUseraddress extends BaseDomainObject<Long> {

	
	public static final String FIELD_userAddressId = "userAddressId";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";
	public static final String FIELD_address1 = "address1";
	public static final String FIELD_address2 = "address2";
	public static final String FIELD_city = "city";
	public static final String FIELD_country = "country";
	public static final String FIELD_diagnosticcenterUserId = "diagnosticcenterUserId";
	public static final String FIELD_state = "state";
	public static final String FIELD_zip = "zip";
	
	private Long userAddressId;
	private String address1;
	private String address2;
	private String city;
	private String country;
	private Long diagnosticcenterUserId;
	private String state;
	private String zip;
	
	public void setUserAddressId( Long  _userAddressId){
		this.userAddressId = _userAddressId;
	}
	@Id
 	@Column(name = "userAddressId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getUserAddressId(){
		 return this.userAddressId;
	}
	public void setAddress1( String  _address1){
		this.address1 = _address1;
	}
	@Length(max=1000)
	@Column (name="address1")
	public String getAddress1(){
		 return this.address1;
	}
	public void setAddress2( String  _address2){
		this.address2 = _address2;
	}
	@Length(max=1000)
	@Column (name="address2")
	public String getAddress2(){
		 return this.address2;
	}
	public void setCity( String  _city){
		this.city = _city;
	}
	@Length(max=1000)
	@Column (name="city")
	public String getCity(){
		 return this.city;
	}
	public void setCountry( String  _country){
		this.country = _country;
	}
	@Length(max=400)
	@Column (name="country")
	public String getCountry(){
		 return this.country;
	}
	public void setDiagnosticcenterUserId( Long  _diagnosticcenterUserId){
		this.diagnosticcenterUserId = _diagnosticcenterUserId;
	}
	@NotNull 
	@Column (name="diagnosticcenterUserId")
	public Long getDiagnosticcenterUserId(){
		 return this.diagnosticcenterUserId;
	}
	public void setState( String  _state){
		this.state = _state;
	}
	@Length(max=400)
	@Column (name="state")
	public String getState(){
		 return this.state;
	}
	public void setZip( String  _zip){
		this.zip = _zip;
	}
	@Length(max=180)
	@Column (name="zip")
	public String getZip(){
		 return this.zip;
	}
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseUseraddress other = (BaseUseraddress)o;
		if(
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs()) && 
			ObjectUtil.isEqual(getAddress1(), other.getAddress1()) && 
			ObjectUtil.isEqual(getAddress2(), other.getAddress2()) && 
			ObjectUtil.isEqual(getCity(), other.getCity()) && 
			ObjectUtil.isEqual(getCountry(), other.getCountry()) && 
			ObjectUtil.isEqual(getDiagnosticcenterUserId(), other.getDiagnosticcenterUserId()) && 
			ObjectUtil.isEqual(getState(), other.getState()) && 
			ObjectUtil.isEqual(getZip(), other.getZip())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (userAddressId!= null ? userAddressId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.userAddressId == null){
			 list.add(new ValidationMessage("Field " + FIELD_userAddressId+  " cannot be null"));
		}

		}
	if(this.isFromDB()){
		if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 100));
		}
	}
		if((this.address1 != null) && (this.address1.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_address1+  " cannot be longer than: " + 1000));
		}

		if((this.address2 != null) && (this.address2.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_address2+  " cannot be longer than: " + 1000));
		}

		if((this.city != null) && (this.city.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_city+  " cannot be longer than: " + 1000));
		}

		if((this.country != null) && (this.country.length()>400)){
			 list.add(new ValidationMessage("Field " + FIELD_country+  " cannot be longer than: " + 400));
		}

		if(this.diagnosticcenterUserId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticcenterUserId+  " cannot be null"));
		}

		if((this.state != null) && (this.state.length()>400)){
			 list.add(new ValidationMessage("Field " + FIELD_state+  " cannot be longer than: " + 400));
		}

		if((this.zip != null) && (this.zip.length()>180)){
			 list.add(new ValidationMessage("Field " + FIELD_zip+  " cannot be longer than: " + 180));
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
		str.append("userAddressId = " + userAddressId + "\n");
;
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		str.append("address1 = " + address1 + "\n");
		str.append("address2 = " + address2 + "\n");
		str.append("city = " + city + "\n");
		str.append("country = " + country + "\n");
		str.append("diagnosticcenterUserId = " + diagnosticcenterUserId + "\n");
		str.append("state = " + state + "\n");
		str.append("zip = " + zip + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (userAddressId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_userAddressId, getUserAddressId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getUserAddressId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Useraddress useraddress = new Useraddress();
		useraddress.setFromDB(false);
		useraddress.setAddress1(getAddress1());
		useraddress.setAddress2(getAddress2());
		useraddress.setCity(getCity());
		useraddress.setCountry(getCountry());
		useraddress.setDiagnosticcenterUserId(getDiagnosticcenterUserId());
		useraddress.setState(getState());
		useraddress.setZip(getZip());
		//afterClone(useraddress);
		return useraddress;
	}
}