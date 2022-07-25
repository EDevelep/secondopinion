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
import org.secondopinion.diagnosticcenter.dto.Packagesubmenu;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BasePackagesubmenu extends BaseDomainObject<Long> {

	
	
	public static final String FIELD_packageSubMenuId = "packageSubMenuId";
	public static final String FIELD_packageMenuId = "packageMenuId";
	public static final String FIELD_subMenuId = "subMenuId";
	public static final String FIELD_serviceName = "serviceName";
	public static final String FIELD_active = "active";
	
	private Long packageSubMenuId;
	private Long packageMenuId;
	private Long subMenuId;
	private String serviceName;
	private Character active;

	public void setPackageSubMenuId( Long  _packageSubMenuId){
		this.packageSubMenuId = _packageSubMenuId;
	}
	@Id
 	@Column(name = "packageSubMenuId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPackageSubMenuId(){
		 return this.packageSubMenuId;
	}
	public void setPackageMenuId( Long  _packageMenuId){
		this.packageMenuId = _packageMenuId;
	}
	@Column (name="packageMenuId")
	public Long getPackageMenuId(){
		 return this.packageMenuId;
	}
	public void setSubMenuId( Long  _subMenuId){
		this.subMenuId = _subMenuId;
	}
	@Column (name="subMenuId")
	public Long getSubMenuId(){
		 return this.subMenuId;
	}
	public void setServiceName( String  _serviceName){
		this.serviceName = _serviceName;
	}
	@Length(max=1000)
	@Column (name="serviceName")
	public String getServiceName(){
		 return this.serviceName;
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
		BasePackagesubmenu other = (BasePackagesubmenu)o;
		if(
			ObjectUtil.isEqual(getPackageMenuId(), other.getPackageMenuId()) && 
			ObjectUtil.isEqual(getSubMenuId(), other.getSubMenuId()) && 
			ObjectUtil.isEqual(getServiceName(), other.getServiceName()) && 
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
		result = result + (packageSubMenuId!= null ? packageSubMenuId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.packageSubMenuId == null){
			 list.add(new ValidationMessage("Field " + FIELD_packageSubMenuId+  " cannot be null"));
		}

		}
		if((this.serviceName != null) && (this.serviceName.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_serviceName+  " cannot be longer than: " + 1000));
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
		str.append("packageSubMenuId = " + packageSubMenuId + "\n");
;
		str.append("packageMenuId = " + packageMenuId + "\n");
		str.append("subMenuId = " + subMenuId + "\n");
		str.append("serviceName = " + serviceName + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (packageSubMenuId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_packageSubMenuId, getPackageSubMenuId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getPackageSubMenuId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Packagesubmenu packagesubmenu = new Packagesubmenu();
		packagesubmenu.setFromDB(false);
		packagesubmenu.setPackageMenuId(getPackageMenuId());
		packagesubmenu.setSubMenuId(getSubMenuId());
		packagesubmenu.setServiceName(getServiceName());
		packagesubmenu.setActive(getActive());
		//afterClone(packagesubmenu);
		return packagesubmenu;
	}
}