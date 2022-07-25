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
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteraddress;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseDiagnosticcenteraddress extends BaseDomainObject<Long> {

	
	
	public static final String FIELD_diagnosticCenterAddressId = "diagnosticCenterAddressId";
	public static final String FIELD_diagnosticcenterId = "diagnosticcenterId";
	public static final String FIELD_address1 = "address1";
	public static final String FIELD_address2 = "address2";
	public static final String FIELD_city = "city";
	public static final String FIELD_country = "country";
	public static final String FIELD_state = "state";
	public static final String FIELD_zip = "zip";
	public static final String FIELD_latitude = "latitude";
	public static final String FIELD_longitude = "longitude";
	public static final String FIELD_active = "active";
	public static final String FIELD_postalLocation = "postalLocation";

	private Long diagnosticCenterAddressId;
	private Long diagnosticcenterId;
	private String address1;
	private String address2;
	private String city;
	private String country;
	private String state;
	private String zip;
	private Double latitude;
	private Double longitude;
	private Character active;
	private String postalLocation;

	
	public void setDiagnosticCenterAddressId( Long  _diagnosticCenterAddressId){
		this.diagnosticCenterAddressId = _diagnosticCenterAddressId;
	}
	@Id
 	@Column(name = "diagnosticCenterAddressId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDiagnosticCenterAddressId(){
		 return this.diagnosticCenterAddressId;
	}
	public void setDiagnosticcenterId( Long  _diagnosticcenterId){
		this.diagnosticcenterId = _diagnosticcenterId;
	}
	@NotNull 
	@Column (name="diagnosticcenterId")
	public Long getDiagnosticcenterId(){
		 return this.diagnosticcenterId;
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
	@Length(max=400)
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
	public void setState( String  _state){
		this.state = _state;
	}
	@Length(max=1000)
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
	public void setActive( Character  _active){
		this.active = _active;
	}
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setLatitude(Double _latitude) {
		this.latitude = _latitude;
	}

	@Column(name = "latitude")
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLongitude(Double _longitude) {
		this.longitude = _longitude;
	}

	@Column(name = "longitude")
	public Double getLongitude() {
		return this.longitude;
	}
		
	@Column(name = "postalLocation")
	public String getPostalLocation() {
		return postalLocation;
	}
	public void setPostalLocation(String postalLocation) {
		this.postalLocation = postalLocation;
	}
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseDiagnosticcenteraddress other = (BaseDiagnosticcenteraddress)o;
		if(
			ObjectUtil.isEqual(getDiagnosticcenterId(), other.getDiagnosticcenterId()) && 
			ObjectUtil.isEqual(getAddress1(), other.getAddress1()) && 
			ObjectUtil.isEqual(getAddress2(), other.getAddress2()) && 
			ObjectUtil.isEqual(getCity(), other.getCity()) && 
			ObjectUtil.isEqual(getCountry(), other.getCountry()) && 
			ObjectUtil.isEqual(getState(), other.getState()) && 
			ObjectUtil.isEqual(getZip(), other.getZip()) && 
			ObjectUtil.isEqual(getLatitude(), other.getLatitude()) &&
			ObjectUtil.isEqual(getLongitude(), other.getLongitude()) &&
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getPostalLocation(), other.getPostalLocation()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (diagnosticCenterAddressId!= null ? diagnosticCenterAddressId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.diagnosticCenterAddressId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticCenterAddressId+  " cannot be null"));
		}

		}
		if(this.diagnosticcenterId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticcenterId+  " cannot be null"));
		}

		if((this.address1 != null) && (this.address1.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_address1+  " cannot be longer than: " + 1000));
		}

		if((this.address2 != null) && (this.address2.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_address2+  " cannot be longer than: " + 1000));
		}

		if((this.city != null) && (this.city.length()>400)){
			 list.add(new ValidationMessage("Field " + FIELD_city+  " cannot be longer than: " + 400));
		}

		if((this.country != null) && (this.country.length()>400)){
			 list.add(new ValidationMessage("Field " + FIELD_country+  " cannot be longer than: " + 400));
		}

		if((this.state != null) && (this.state.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_state+  " cannot be longer than: " + 1000));
		}

		if((this.zip != null) && (this.zip.length()>180)){
			 list.add(new ValidationMessage("Field " + FIELD_zip+  " cannot be longer than: " + 180));
		}
		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null "));
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
		str.append("diagnosticCenterAddressId = " + diagnosticCenterAddressId + "\n");
;
		str.append("diagnosticcenterId = " + diagnosticcenterId + "\n");
		str.append("address1 = " + address1 + "\n");
		str.append("address2 = " + address2 + "\n");
		str.append("city = " + city + "\n");
		str.append("country = " + country + "\n");
		str.append("state = " + state + "\n");
		str.append("zip = " + zip + "\n");
		str.append("longitude = " + longitude + "\n");
		str.append("latitude = " + latitude + "\n");
		str.append("active = " + active + "\n");
		str.append("postalLocation = " + postalLocation + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (diagnosticCenterAddressId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_diagnosticCenterAddressId, getDiagnosticCenterAddressId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getDiagnosticCenterAddressId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Diagnosticcenteraddress diagnosticcenteraddress = new Diagnosticcenteraddress();
		diagnosticcenteraddress.setFromDB(false);
		diagnosticcenteraddress.setDiagnosticcenterId(getDiagnosticcenterId());
		diagnosticcenteraddress.setAddress1(getAddress1());
		diagnosticcenteraddress.setAddress2(getAddress2());
		diagnosticcenteraddress.setCity(getCity());
		diagnosticcenteraddress.setCountry(getCountry());
		diagnosticcenteraddress.setState(getState());
		diagnosticcenteraddress.setLatitude(getLatitude());
		diagnosticcenteraddress.setLongitude(getLongitude());
		diagnosticcenteraddress.setPostalLocation(getPostalLocation());
		diagnosticcenteraddress.setZip(getZip());
		diagnosticcenteraddress.setActive(getActive());
		return diagnosticcenteraddress;
	}
}