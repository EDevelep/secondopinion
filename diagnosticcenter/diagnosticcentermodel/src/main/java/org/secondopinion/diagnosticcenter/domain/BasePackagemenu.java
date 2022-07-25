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
import org.secondopinion.diagnosticcenter.dto.Packagemenu;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BasePackagemenu extends BaseDomainObject<Long> {

	
	
	public static final String FIELD_packageMenuId = "packageMenuId";
	public static final String FIELD_packageId = "packageId";
	public static final String FIELD_menuId = "menuId";
	public static final String FIELD_menuName = "menuName";
	public static final String FIELD_active = "active";

	private Long packageMenuId;
	private Long packageId;
	private Long menuId;
	private String menuName;
	private Character active;

	public void setPackageMenuId( Long  _packageMenuId){
		this.packageMenuId = _packageMenuId;
	}
	@Id
 	@Column(name = "packageMenuId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPackageMenuId(){
		 return this.packageMenuId;
	}
	public void setPackageId( Long  _packageId){
		this.packageId = _packageId;
	}
	@NotNull 
	@Column (name="packageId")
	public Long getPackageId(){
		 return this.packageId;
	}
	public void setMenuId( Long  _menuId){
		this.menuId = _menuId;
	}
	@NotNull 
	@Column (name="menuId")
	public Long getMenuId(){
		 return this.menuId;
	}
	public void setMenuName( String  _menuName){
		this.menuName = _menuName;
	}
	@Length(max=1000)
	@Column (name="menuName")
	public String getMenuName(){
		 return this.menuName;
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
		BasePackagemenu other = (BasePackagemenu)o;
		if(
			ObjectUtil.isEqual(getPackageId(), other.getPackageId()) && 
			ObjectUtil.isEqual(getMenuId(), other.getMenuId()) && 
			ObjectUtil.isEqual(getMenuName(), other.getMenuName()) && 
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
		result = result + (packageMenuId!= null ? packageMenuId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.packageMenuId == null){
			 list.add(new ValidationMessage("Field " + FIELD_packageMenuId+  " cannot be null"));
		}

		}
		if(this.packageId == null){
			 list.add(new ValidationMessage("Field " + FIELD_packageId+  " cannot be null"));
		}

		if(this.menuId == null){
			 list.add(new ValidationMessage("Field " + FIELD_menuId+  " cannot be null"));
		}

		if((this.menuName != null) && (this.menuName.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_menuName+  " cannot be longer than: " + 1000));
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
		str.append("packageMenuId = " + packageMenuId + "\n");
;
		str.append("packageId = " + packageId + "\n");
		str.append("menuId = " + menuId + "\n");
		str.append("menuName = " + menuName + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (packageMenuId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_packageMenuId, getPackageMenuId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getPackageMenuId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Packagemenu packagemenu = new Packagemenu();
		packagemenu.setFromDB(false);
		packagemenu.setPackageId(getPackageId());
		packagemenu.setMenuId(getMenuId());
		packagemenu.setMenuName(getMenuName());
		packagemenu.setActive(getActive());
		//afterClone(packagemenu);
		return packagemenu;
	}
}