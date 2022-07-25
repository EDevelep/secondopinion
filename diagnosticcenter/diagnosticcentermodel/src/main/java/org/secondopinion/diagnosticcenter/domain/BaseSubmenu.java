package org.secondopinion.diagnosticcenter.domain; 

import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.diagnosticcenter.dto.Submenu;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage; 
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
public abstract class BaseSubmenu extends BaseDomainObject<Long> {

	public static final String FIELD_subMenuId = "subMenuId";
	public static final String FIELD_menuId = "menuId";
	public static final String FIELD_diagnosticCenterAddressId = "diagnosticCenterAddressId";
	public static final String FIELD_serviceName = "serviceName";
	public static final String FIELD_serviceType = "serviceType";
	public static final String FIELD_price = "price";
	public static final String FIELD_available = "available";
	public static final String FIELD_collectiontype = "collectiontype";
	public static final String FIELD_active = "active";
	public static final String FIELD_availableFrom = "availableFrom";

	private Long subMenuId;
	private Long menuId;
	private String serviceName;
	private String serviceType;
	private Long diagnosticCenterAddressId;
	private Double price;
	private String available;
	private Character active;
	private String collectiontype;
	private Date availableFrom;
	
	public void setSubMenuId( Long  _subMenuId){
		this.subMenuId = _subMenuId;
	}
	@Id
 	@Column(name = "subMenuId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getSubMenuId(){
		 return this.subMenuId;
	}
	public void setMenuId( Long  _menuId){
		this.menuId = _menuId;
	}
	@NotNull 
	@Column (name="menuId")
	public Long getMenuId(){
		 return this.menuId;
	}
	public void setServiceName( String  _serviceName){
		this.serviceName = _serviceName;
	}
	@NotNull 
	@Length(max=1000)
	@Column (name="serviceName")
	public String getServiceName(){
		 return this.serviceName;
	}
	public void setServiceType( String  _serviceType){
		this.serviceType = _serviceType;
	}
	@Length(max=180)
	@Column (name="serviceType")
	public String getServiceType(){
		 return this.serviceType;
	}
	public void setPrice( Double  _price){
		this.price = _price;
	}
	@Column (name="price")
	public Double getPrice(){
		 return this.price;
	}
	public void setAvailable( String  _available){
		this.available = _available;
	}
	@Length(max=4)
	@Column (name="available")
	public String getAvailable(){
		 return this.available;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setAvailableFrom( Date  _availableFrom){
		this.availableFrom = _availableFrom;
	}
	@Column (name="availableFrom")
	public Date getAvailableFrom(){
		 return this.availableFrom;
	}
	public void setDiagnosticCenterAddressId( Long  _diagnosticCenterAddressId){
		this.diagnosticCenterAddressId = _diagnosticCenterAddressId;
	}
	@Column (name="collectiontype")
	public String getCollectiontype() {
		return collectiontype;
	}
	public void setCollectiontype(String collectiontype) {
		this.collectiontype = collectiontype;
	}
	@NotNull 
	@Column (name="diagnosticCenterAddressId")
	public Long getDiagnosticCenterAddressId(){
		 return this.diagnosticCenterAddressId;
	}
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseSubmenu other = (BaseSubmenu)o;
		if(
			ObjectUtil.isEqual(getMenuId(), other.getMenuId()) && 
			ObjectUtil.isEqual(getServiceName(), other.getServiceName()) && 
			ObjectUtil.isEqual(getDiagnosticCenterAddressId(), other.getDiagnosticCenterAddressId()) && 
			ObjectUtil.isEqual(getServiceType(), other.getServiceType()) && 
			ObjectUtil.isEqual(getPrice(), other.getPrice()) && 
			ObjectUtil.isEqual(getAvailable(), other.getAvailable()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getAvailableFrom(), other.getAvailableFrom()) && 
			ObjectUtil.isEqual(getCollectiontype(), other.getCollectiontype()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (subMenuId!= null ? subMenuId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.subMenuId == null){
			 list.add(new ValidationMessage("Field " + FIELD_subMenuId+  " cannot be null"));
		}

		}
		if(this.menuId == null){
			 list.add(new ValidationMessage("Field " + FIELD_menuId+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.serviceName)){
			 list.add(new ValidationMessage("Field " + FIELD_serviceName+  " cannot be null"));
		}

		if((this.serviceName != null) && (this.serviceName.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_serviceName+  " cannot be longer than: " + 1000));
		}

		if((this.serviceType != null) && (this.serviceType.length()>180)){
			 list.add(new ValidationMessage("Field " + FIELD_serviceType+  " cannot be longer than: " + 180));
		}
		if(this.diagnosticCenterAddressId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticCenterAddressId+  " cannot be null"));
		}
		
		if((this.available != null) && (this.available.length()>4)){
			 list.add(new ValidationMessage("Field " + FIELD_available+  " cannot be longer than: " + 4));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
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
		str.append("subMenuId = " + subMenuId + "\n");
;
		str.append("menuId = " + menuId + "\n");
		str.append("serviceName = " + serviceName + "\n");
		str.append("diagnosticCenterAddressId = " + diagnosticCenterAddressId + "\n");
		str.append("serviceType = " + serviceType + "\n");
		str.append("price = " + price + "\n");
		str.append("available = " + available + "\n");
		str.append("active = " + active + "\n");
		str.append("availableFrom = " + availableFrom + "\n");
		str.append("collectiontype = " + collectiontype + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (subMenuId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_subMenuId, getSubMenuId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getSubMenuId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Submenu submenu = new Submenu();
		submenu.setFromDB(false);
		submenu.setMenuId(getMenuId());
		submenu.setServiceName(getServiceName());
		submenu.setServiceType(getServiceType());
		submenu.setPrice(getPrice());
		submenu.setDiagnosticCenterAddressId(getDiagnosticCenterAddressId());
		submenu.setAvailable(getAvailable());
		submenu.setActive(getActive());
		submenu.setCollectiontype(getCollectiontype());
		submenu.setAvailableFrom(getAvailableFrom());
		//afterClone(submenu);
		return submenu;
	}
}