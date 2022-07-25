package org.secondopinion.diagnosticcenter.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.diagnosticcenter.dto.Menu;
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
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseMenu extends BaseDomainObject<Long> {

	
	
	public static final String FIELD_menuId = "menuId";
	public static final String FIELD_diagnosticCenterAddressId = "diagnosticCenterAddressId";
	public static final String FIELD_menuName = "menuName";
	public static final String FIELD_active = "active";

	private Long menuId;
	private Long diagnosticCenterAddressId;
	private String menuName;
	private Character active;

	public void setMenuId( Long  _menuId){
		this.menuId = _menuId;
	}
	@Id
 	@Column(name = "menuId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getMenuId(){
		 return this.menuId;
	}
	public void setDiagnosticCenterAddressId( Long  _diagnosticCenterAddressId){
		this.diagnosticCenterAddressId = _diagnosticCenterAddressId;
	}
	@NotNull 
	@Column (name="diagnosticCenterAddressId")
	public Long getDiagnosticCenterAddressId(){
		 return this.diagnosticCenterAddressId;
	}
	public void setMenuName( String  _menuName){
		this.menuName = _menuName;
	}
	@NotNull 
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
		BaseMenu other = (BaseMenu)o;
		if(
			ObjectUtil.isEqual(getDiagnosticCenterAddressId(), other.getDiagnosticCenterAddressId()) && 
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
		result = result + (menuId!= null ? menuId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.menuId == null){
			 list.add(new ValidationMessage("Field " + FIELD_menuId+  " cannot be null"));
		}

		}
		if(this.diagnosticCenterAddressId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticCenterAddressId+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.menuName)){
			 list.add(new ValidationMessage("Field " + FIELD_menuName+  " cannot be null"));
		}

		if((this.menuName != null) && (this.menuName.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_menuName+  " cannot be longer than: " + 1000));
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
		str.append("menuId = " + menuId + "\n");
;
		str.append("diagnosticCenterAddressId = " + diagnosticCenterAddressId + "\n");
		str.append("menuName = " + menuName + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (menuId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_menuId, getMenuId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getMenuId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Menu menu = new Menu();
		menu.setFromDB(false);
		menu.setDiagnosticCenterAddressId(getDiagnosticCenterAddressId());
		menu.setMenuName(getMenuName());
		menu.setActive(getActive());
		//afterClone(menu);
		return menu;
	}
}