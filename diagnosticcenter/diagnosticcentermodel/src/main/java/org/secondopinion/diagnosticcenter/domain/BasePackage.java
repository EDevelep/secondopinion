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
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BasePackage extends BaseDomainObject<Long> {

	
	
	public static final String FIELD_packageId = "packageId";
	public static final String FIELD_diagnosticCenterAddressId = "diagnosticCenterAddressId";
	public static final String FIELD_packageName = "packageName";
	public static final String FIELD_description = "description";
	public static final String FIELD_price = "price";
	public static final String FIELD_active = "active";
	public static final String FIELD_availableFrom = "availableFrom";
	public static final String FIELD_discount= "discount";
	public static final String FIELD_status = "status";

	private Long packageId;
	private Long diagnosticCenterAddressId;
	private String packageName;
	private String description;
	private Double price;
	private Double discount;
	private String status;
	private Date availableFrom;
	private Character active;

	public void setPackageId( Long  _packageId){
		this.packageId = _packageId;
	}
	@Id
 	@Column(name = "packageId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPackageId(){
		 return this.packageId;
	}
	public void setDiagnosticCenterAddressId( Long  _diagnosticCenterAddressId){
		this.diagnosticCenterAddressId = _diagnosticCenterAddressId;
	}
	@NotNull 
	@Column (name="diagnosticCenterAddressId")
	public Long getDiagnosticCenterAddressId(){
		 return this.diagnosticCenterAddressId;
	}
	public void setPackageName( String  _packageName){
		this.packageName = _packageName;
	}
	@NotNull 
	@Length(max=250)
	@Column (name="packageName")
	public String getPackageName(){
		 return this.packageName;
	}
	public void setDescription( String  _description){
		this.description = _description;
	}
	@Length(max=1000)
	@Column (name="description")
	public String getDescription(){
		 return this.description;
	}
	public void setPrice( Double  _price){
		this.price = _price;
	}
	@Column (name="price")
	public Double getPrice(){
		 return this.price;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	@Column (name="discount")
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	@Column (name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column (name="availableFrom")
	public Date getAvailableFrom() {
		return availableFrom;
	}
	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BasePackage other = (BasePackage)o;
		if(
			ObjectUtil.isEqual(getDiagnosticCenterAddressId(), other.getDiagnosticCenterAddressId()) && 
			ObjectUtil.isEqual(getPackageName(), other.getPackageName()) && 
			ObjectUtil.isEqual(getDescription(), other.getDescription()) && 
			ObjectUtil.isEqual(getPrice(), other.getPrice()) && 
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
		result = result + (packageId!= null ? packageId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.packageId == null){
			 list.add(new ValidationMessage("Field " + FIELD_packageId+  " cannot be null"));
		}

		}
		if(this.diagnosticCenterAddressId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticCenterAddressId+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.packageName)){
			 list.add(new ValidationMessage("Field " + FIELD_packageName+  " cannot be null"));
		}

		if((this.packageName != null) && (this.packageName.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_packageName+  " cannot be longer than: " + 250));
		}

		if((this.description != null) && (this.description.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_description+  " cannot be longer than: " + 1000));
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
		str.append("packageId = " + packageId + "\n");
;
		str.append("diagnosticCenterAddressId = " + diagnosticCenterAddressId + "\n");
		str.append("packageName = " + packageName + "\n");
		str.append("description = " + description + "\n");
		str.append("price = " + price + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (packageId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_packageId, getPackageId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getPackageId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		org.secondopinion.diagnosticcenter.dto.Package package1 = new org.secondopinion.diagnosticcenter.dto.Package();
		package1.setFromDB(false);
		package1.setDiagnosticCenterAddressId(getDiagnosticCenterAddressId());
		package1.setPackageName(getPackageName());
		package1.setDescription(getDescription());
		package1.setPrice(getPrice());
		package1.setActive(getActive());
		//afterClone(package);
		return package1;
	}
}