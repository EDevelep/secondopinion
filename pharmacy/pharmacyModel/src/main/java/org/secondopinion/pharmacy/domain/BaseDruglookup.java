package org.secondopinion.pharmacy.domain; 

import java.math.BigDecimal;
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 

import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.pharmacy.dto.Druglookup;
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
public abstract class BaseDruglookup extends BaseDomainObject<Long> {

	public static final String FIELD_drugid = "drugid";
	public static final String FIELD_drugname = "drugname";
	public static final String FIELD_pharmacyId = "pharmacyId";
	public static final String FIELD_alternate = "alternate";
	public static final String FIELD_manufacturer = "manufacturer";
	public static final String FIELD_mfgdate = "mfgdate";
	public static final String FIELD_expdate = "expdate";
	public static final String FIELD_price = "price";
	public static final String FIELD_potency = "potency";
	public static final String FIELD_drugtype = "drugtype";
	public static final String FIELD_injectable = "injectable";
	public static final String FIELD_drugform = "drugform";
	public static final String FIELD_active = "active";
	

	private Long drugid;
	private String drugname;
	private String alternate;
	private Long pharmacyId;
	private String manufacturer;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date mfgdate;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date expdate;
	private BigDecimal price;
	private String potency;
	private String drugtype;
	private Character injectable;
	private String drugform;
	private Character active;

	public void setDrugid( Long  _drugid){
		this.drugid = _drugid;
	}
	@Id
 	@Column(name = "drugid") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDrugid(){
		 return this.drugid;
	}
	public void setDrugname( String  _drugname){
		this.drugname = _drugname;
	}
	@Length(max=150)
	@Column (name="drugname")
	public String getDrugname(){
		 return this.drugname;
	}
	public void setAlternate( String  _alternate){
		this.alternate = _alternate;
	}
	@Length(max=150)
	@Column (name="alternate")
	public String getAlternate(){
		 return this.alternate;
	}
	public void setManufacturer( String  _manufacturer){
		this.manufacturer = _manufacturer;
	}
	@Length(max=150)
	@Column (name="manufacturer")
	public String getManufacturer(){
		 return this.manufacturer;
	}
	public void setMfgdate( Date  _mfgdate){
		this.mfgdate = _mfgdate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="mfgdate")
	public Date getMfgdate(){
		 return this.mfgdate;
	}
	public void setExpdate( Date  _expdate){
		this.expdate = _expdate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="expdate")
	public Date getExpdate(){
		 return this.expdate;
	}
	public void setPrice( BigDecimal  _price){
		this.price = _price;
	}
	@NotNull
	@Column(name = "price")
	public BigDecimal getPrice(){
		 return this.price;
	}
	public void setPotency( String  _potency){
		this.potency = _potency;
	}
	@Length(max=45)
	@Column (name="potency")
	public String getPotency(){
		 return this.potency;
	}
	public void setDrugtype( String  _drugtype){
		this.drugtype = _drugtype;
	}
	@Length(max=150)
	@Column (name="drugtype")
	public String getDrugtype(){
		 return this.drugtype;
	}
	public void setInjectable( Character  _injectable){
		this.injectable = _injectable;
	}
	@Column(name = "injectable")
	public Character getInjectable(){
		 return this.injectable;
	}
	public void setDrugform( String  _drugform){
		this.drugform = _drugform;
	}
	@Length(max=255)
	@Column (name="drugform")
	public String getDrugform(){
		 return this.drugform;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	@Column (name="pharmacyId")
	public Long getPharmacyId() {
		return pharmacyId;
	}
	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseDruglookup other = (BaseDruglookup)o;
		if(
			ObjectUtil.isEqual(getDrugname(), other.getDrugname()) && 
			ObjectUtil.isEqual(getAlternate(), other.getAlternate()) && 
			ObjectUtil.isEqual(getManufacturer(), other.getManufacturer()) && 
			ObjectUtil.isEqual(getMfgdate(), other.getMfgdate()) && 
			ObjectUtil.isEqual(getPharmacyId(), other.getPharmacyId()) && 
			ObjectUtil.isEqual(getExpdate(), other.getExpdate()) && 
			ObjectUtil.isEqual(getPrice(), other.getPrice()) && 
			ObjectUtil.isEqual(getPotency(), other.getPotency()) && 
			ObjectUtil.isEqual(getDrugtype(), other.getDrugtype()) && 
			ObjectUtil.isEqual(getInjectable(), other.getInjectable()) && 
			ObjectUtil.isEqual(getDrugform(), other.getDrugform()) && 
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
		result = result + (drugid!= null ? drugid.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.drugid == null){
			 list.add(new ValidationMessage("Field " + FIELD_drugid+  " cannot be null"));
		}

		}
		if((this.drugname != null) && (this.drugname.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_drugname+  " cannot be longer than: " + 150));
		}

		if((this.alternate != null) && (this.alternate.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_alternate+  " cannot be longer than: " + 150));
		}

		if((this.manufacturer != null) && (this.manufacturer.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_manufacturer+  " cannot be longer than: " + 150));
		}

		if((this.potency != null) && (this.potency.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_potency+  " cannot be longer than: " + 45));
		}

		if((this.drugtype != null) && (this.drugtype.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_drugtype+  " cannot be longer than: " + 150));
		}

		if((this.drugform != null) && (this.drugform.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_drugform+  " cannot be longer than: " + 255));
		}
		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
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
		str.append("drugid = " + drugid + "\n");
;
		str.append("drugname = " + drugname + "\n");
		str.append("alternate = " + alternate + "\n");
		str.append("manufacturer = " + manufacturer + "\n");
		str.append("mfgdate = " + mfgdate + "\n");
		str.append("expdate = " + expdate + "\n");
		str.append("price = " + price + "\n");
		str.append("pharmacyId = " + pharmacyId + "\n");
		str.append("potency = " + potency + "\n");
		str.append("drugtype = " + drugtype + "\n");
		str.append("injectable = " + injectable + "\n");
		str.append("drugform = " + drugform + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (drugid == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_drugid, getDrugid()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getDrugid();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Druglookup druglookup = new Druglookup();
		druglookup.setFromDB(false);
		druglookup.setDrugname(getDrugname());
		druglookup.setAlternate(getAlternate());
		druglookup.setManufacturer(getManufacturer());
		druglookup.setMfgdate(getMfgdate());
		druglookup.setExpdate(getExpdate());
		druglookup.setPrice(getPrice());
		druglookup.setPotency(getPotency());
		druglookup.setDrugtype(getDrugtype());
		druglookup.setPharmacyId(getPharmacyId());
		druglookup.setInjectable(getInjectable());
		druglookup.setDrugform(getDrugform());
		druglookup.setActive(getActive());
		//afterClone(druglookup);
		return druglookup;
	}
}