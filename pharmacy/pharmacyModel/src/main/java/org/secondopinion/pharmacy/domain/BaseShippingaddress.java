package org.secondopinion.pharmacy.domain; 

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
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.pharmacy.dto.Shippingaddress;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseShippingaddress extends BaseDomainObject<Long> {

	public static final String FIELD_shippingaddressId = "shippingaddressId";
	public static final String FIELD_patientid = "patientid";
	public static final String FIELD_invoiceId = "invoiceId";
	public static final String FIELD_prescriptionFillRequestId = "prescriptionFillRequestId";
	public static final String FIELD_street1 = "street1";
	public static final String FIELD_street2 = "street2";
	public static final String FIELD_city = "city";
	public static final String FIELD_state = "state";
	public static final String FIELD_country = "country";
	public static final String FIELD_zip = "zip";
	public static final String FIELD_longitudes = "longitudes";
	public static final String FIELD_latitude = "latitude";
	public static final String FIELD_contactName = "contactName";
	public static final String FIELD_contactPhoneNumber = "contactPhoneNumber";
	public static final String FIELD_active = "active";
	
	private Long shippingaddressId;
	private Long patientid;
	private Long invoiceId;
	private Long prescriptionFillRequestId;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String country;
	private String zip;
	private Long longitudes;
	private Long latitude;
	private String contactName;
	private String contactPhoneNumber;
	private Character active;

	public void setShippingaddressId( Long  _shippingaddressId){
		this.shippingaddressId = _shippingaddressId;
	}
	@Id
 	@Column(name = "shippingaddressId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getShippingaddressId(){
		 return this.shippingaddressId;
	}
	public void setPatientid( Long  _patientid){
		this.patientid = _patientid;
	}
	@NotNull 
	@Column (name="patientid")
	public Long getPatientid(){
		 return this.patientid;
	}
	public void setInvoiceId( Long  _invoiceId){
		this.invoiceId = _invoiceId;
	}
	@NotNull 
	@Column (name="invoiceId")
	public Long getInvoiceId(){
		 return this.invoiceId;
	}
	public void setPrescriptionFillRequestId( Long  _prescriptionFillRequestId){
		this.prescriptionFillRequestId = _prescriptionFillRequestId;
	}
	@NotNull 
	@Column (name="prescriptionFillRequestId")
	public Long getPrescriptionFillRequestId(){
		 return this.prescriptionFillRequestId;
	}
	public void setStreet1( String  _street1){
		this.street1 = _street1;
	}
	@Length(max=45)
	@Column (name="street1")
	public String getStreet1(){
		 return this.street1;
	}
	public void setStreet2( String  _street2){
		this.street2 = _street2;
	}
	@Length(max=45)
	@Column (name="street2")
	public String getStreet2(){
		 return this.street2;
	}
	public void setCity( String  _city){
		this.city = _city;
	}
	@Length(max=45)
	@Column (name="city")
	public String getCity(){
		 return this.city;
	}
	public void setState( String  _state){
		this.state = _state;
	}
	@Length(max=45)
	@Column (name="state")
	public String getState(){
		 return this.state;
	}
	public void setCountry( String  _country){
		this.country = _country;
	}
	@Length(max=45)
	@Column (name="country")
	public String getCountry(){
		 return this.country;
	}
	public void setZip( String  _zip){
		this.zip = _zip;
	}
	@Length(max=45)
	@Column (name="zip")
	public String getZip(){
		 return this.zip;
	}
	public void setLongitudes( Long  _longitudes){
		this.longitudes = _longitudes;
	}
	@Column (name="longitudes")
	public Long getLongitudes(){
		 return this.longitudes;
	}
	public void setLatitude( Long  _latitude){
		this.latitude = _latitude;
	}
	@Column (name="latitude")
	public Long getLatitude(){
		 return this.latitude;
	}
	public void setContactName( String  _contactName){
		this.contactName = _contactName;
	}
	@Length(max=45)
	@Column (name="contactName")
	public String getContactName(){
		 return this.contactName;
	}
	public void setContactPhoneNumber( String  _contactPhoneNumber){
		this.contactPhoneNumber = _contactPhoneNumber;
	}
	@Length(max=45)
	@Column (name="contactPhoneNumber")
	public String getContactPhoneNumber(){
		 return this.contactPhoneNumber;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseShippingaddress other = (BaseShippingaddress)o;
		if(
			ObjectUtil.isEqual(getPatientid(), other.getPatientid()) && 
			ObjectUtil.isEqual(getInvoiceId(), other.getInvoiceId()) && 
			ObjectUtil.isEqual(getPrescriptionFillRequestId(), other.getPrescriptionFillRequestId()) && 
			ObjectUtil.isEqual(getStreet1(), other.getStreet1()) && 
			ObjectUtil.isEqual(getStreet2(), other.getStreet2()) && 
			ObjectUtil.isEqual(getCity(), other.getCity()) && 
			ObjectUtil.isEqual(getState(), other.getState()) && 
			ObjectUtil.isEqual(getCountry(), other.getCountry()) && 
			ObjectUtil.isEqual(getZip(), other.getZip()) && 
			ObjectUtil.isEqual(getLongitudes(), other.getLongitudes()) && 
			ObjectUtil.isEqual(getLatitude(), other.getLatitude()) && 
			ObjectUtil.isEqual(getContactName(), other.getContactName()) && 
			ObjectUtil.isEqual(getContactPhoneNumber(), other.getContactPhoneNumber()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (shippingaddressId!= null ? shippingaddressId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.shippingaddressId == null){
			 list.add(new ValidationMessage("Field " + FIELD_shippingaddressId+  " cannot be null"));
		}

		}
		if(this.patientid == null){
			 list.add(new ValidationMessage("Field " + FIELD_patientid+  " cannot be null"));
		}

		if(this.invoiceId == null){
			 list.add(new ValidationMessage("Field " + FIELD_invoiceId+  " cannot be null"));
		}

		if(this.prescriptionFillRequestId == null){
			 list.add(new ValidationMessage("Field " + FIELD_prescriptionFillRequestId+  " cannot be null"));
		}

		if((this.street1 != null) && (this.street1.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_street1+  " cannot be longer than: " + 45));
		}

		if((this.street2 != null) && (this.street2.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_street2+  " cannot be longer than: " + 45));
		}

		if((this.city != null) && (this.city.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_city+  " cannot be longer than: " + 45));
		}

		if((this.state != null) && (this.state.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_state+  " cannot be longer than: " + 45));
		}

		if((this.country != null) && (this.country.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_country+  " cannot be longer than: " + 45));
		}

		if((this.zip != null) && (this.zip.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_zip+  " cannot be longer than: " + 45));
		}

		if((this.contactName != null) && (this.contactName.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_contactName+  " cannot be longer than: " + 45));
		}

		if((this.contactPhoneNumber != null) && (this.contactPhoneNumber.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_contactPhoneNumber+  " cannot be longer than: " + 45));
		}
		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}
	if(this.isFromDB()){
		if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 45));
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
		str.append("shippingaddressId = " + shippingaddressId + "\n");
;
		str.append("patientid = " + patientid + "\n");
		str.append("invoiceId = " + invoiceId + "\n");
		str.append("prescriptionFillRequestId = " + prescriptionFillRequestId + "\n");
		str.append("street1 = " + street1 + "\n");
		str.append("street2 = " + street2 + "\n");
		str.append("city = " + city + "\n");
		str.append("state = " + state + "\n");
		str.append("country = " + country + "\n");
		str.append("zip = " + zip + "\n");
		str.append("longitudes = " + longitudes + "\n");
		str.append("latitude = " + latitude + "\n");
		str.append("contactName = " + contactName + "\n");
		str.append("contactPhoneNumber = " + contactPhoneNumber + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (shippingaddressId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_shippingaddressId, getShippingaddressId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getShippingaddressId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Shippingaddress shippingaddress = new Shippingaddress();
		shippingaddress.setFromDB(false);
		shippingaddress.setPatientid(getPatientid());
		shippingaddress.setInvoiceId(getInvoiceId());
		shippingaddress.setPrescriptionFillRequestId(getPrescriptionFillRequestId());
		shippingaddress.setStreet1(getStreet1());
		shippingaddress.setStreet2(getStreet2());
		shippingaddress.setCity(getCity());
		shippingaddress.setState(getState());
		shippingaddress.setCountry(getCountry());
		shippingaddress.setZip(getZip());
		shippingaddress.setLongitudes(getLongitudes());
		shippingaddress.setLatitude(getLatitude());
		shippingaddress.setContactName(getContactName());
		shippingaddress.setContactPhoneNumber(getContactPhoneNumber());
		shippingaddress.setActive(getActive());
		//afterClone(shippingaddress);
		return shippingaddress;
	}
}