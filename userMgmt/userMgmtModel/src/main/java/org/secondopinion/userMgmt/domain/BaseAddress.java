package org.secondopinion.userMgmt.domain; 

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.secondopinion.userMgmt.dto.Address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@SuppressWarnings({ "serial"})
@MappedSuperclass 
@JsonIgnoreProperties(value = { "keyField" })
public abstract class BaseAddress extends BaseDomainObject<Integer> {

	public static final String FIELD_addressId = "addressId";
	public static final String FIELD_companyId = "companyId";
	public static final String FIELD_address1 = "address1";
	public static final String FIELD_address2 = "address2";
	public static final String FIELD_city = "city";
	public static final String FIELD_state = "state";
	public static final String FIELD_zip = "zip";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Integer addressId;
	private Integer companyId;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;

	public void setAddressId( Integer  _addressId){
		this.addressId = _addressId;
	}
	@Id
// 	@NotNull
	@Column(name = "addressId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAddressId(){
		 return this.addressId;
	}
	public void setCompanyId( Integer  _companyId){
		this.companyId = _companyId;
	}
	@NotNull 
	@Column (name="companyId")
	public Integer getCompanyId(){
		 return this.companyId;
	}
	public void setAddress1( String  _address1){
		this.address1 = _address1;
	}
	@NotNull 
	@Length(max=200)
	@Column (name="address1")
	public String getAddress1(){
		 return this.address1;
	}
	public void setAddress2( String  _address2){
		this.address2 = _address2;
	}
	
	@Length(max=200)
	@Column (name="address2")
	public String getAddress2(){
		 return this.address2;
	}
	public void setCity( String  _city){
		this.city = _city;
	}
	@NotNull 
	@Length(max=200)
	@Column (name="city")
	public String getCity(){
		 return this.city;
	}
	public void setState( String  _state){
		this.state = _state;
	}
	@NotNull 
	@Length(max=5)
	@Column (name="state")
	public String getState(){
		 return this.state;
	}
	public void setZip( String  _zip){
		this.zip = _zip;
	}
	@NotNull 
	@Length(max=200)
	@Column (name="zip")
	public String getZip(){
		 return this.zip;
	}
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseAddress other = (BaseAddress)o;
		if(
			ObjectUtil.isEqual(getCompanyId(), other.getCompanyId()) && 
			ObjectUtil.isEqual(getAddress1(), other.getAddress1()) && 
			ObjectUtil.isEqual(getAddress2(), other.getAddress2()) && 
			ObjectUtil.isEqual(getCity(), other.getCity()) && 
			ObjectUtil.isEqual(getState(), other.getState()) && 
			ObjectUtil.isEqual(getZip(), other.getZip()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (addressId!= null ? addressId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.addressId == null){
			 list.add(new ValidationMessage("Field " + FIELD_addressId+  " cannot be null"));
		}

		}
		if(this.companyId == null){
			 list.add(new ValidationMessage("Field " + FIELD_companyId+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.address1)){
			 list.add(new ValidationMessage("Field " + FIELD_address1+  " cannot be null"));
		}

		if((this.address1 != null) && (this.address1.length()>200)){
			 list.add(new ValidationMessage("Field " + FIELD_address1+  " cannot be longer than: " + 200));
		}

		if( StringUtil.isNullOrEmpty(this.address2)){
			 list.add(new ValidationMessage("Field " + FIELD_address2+  " cannot be null"));
		}

		if((this.address2 != null) && (this.address2.length()>200)){
			 list.add(new ValidationMessage("Field " + FIELD_address2+  " cannot be longer than: " + 200));
		}

		if( StringUtil.isNullOrEmpty(this.city)){
			 list.add(new ValidationMessage("Field " + FIELD_city+  " cannot be null"));
		}

		if((this.city != null) && (this.city.length()>200)){
			 list.add(new ValidationMessage("Field " + FIELD_city+  " cannot be longer than: " + 200));
		}

		if( StringUtil.isNullOrEmpty(this.state)){
			 list.add(new ValidationMessage("Field " + FIELD_state+  " cannot be null"));
		}

		if((this.state != null) && (this.state.length()>5)){
			 list.add(new ValidationMessage("Field " + FIELD_state+  " cannot be longer than: " + 5));
		}

		if( StringUtil.isNullOrEmpty(this.zip)){
			 list.add(new ValidationMessage("Field " + FIELD_zip+  " cannot be null"));
		}

		if((this.zip != null) && (this.zip.length()>200)){
			 list.add(new ValidationMessage("Field " + FIELD_zip+  " cannot be longer than: " + 200));
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
		str.append("addressId = " + addressId + "\n");
;
		str.append("companyId = " + companyId + "\n");
		str.append("address1 = " + address1 + "\n");
		str.append("address2 = " + address2 + "\n");
		str.append("city = " + city + "\n");
		str.append("state = " + state + "\n");
		str.append("zip = " + zip + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (addressId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_addressId, getAddressId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getAddressId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Address address = new Address();
		address.setFromDB(false);
		address.setCompanyId(getCompanyId());
		address.setAddress1(getAddress1());
		address.setAddress2(getAddress2());
		address.setCity(getCity());
		address.setState(getState());
		address.setZip(getZip());
		//afterClone(address);
		return address;
	}
}