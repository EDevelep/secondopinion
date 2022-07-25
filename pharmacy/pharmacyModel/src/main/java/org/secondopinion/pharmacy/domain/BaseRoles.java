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
import org.secondopinion.pharmacy.dto.Roles;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseRoles extends BaseDomainObject<Integer> {

	public static final String FIELD_roleId = "roleId";
	public static final String FIELD_roleName = "roleName";
	public static final String FIELD_active = "active";

	private Integer roleId;
	private String roleName;
	private Character active;

	public void setRoleId( Integer  _roleId){
		this.roleId = _roleId;
	}
	@Id
 	@Column(name = "roleId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getRoleId(){
		 return this.roleId;
	}
	public void setRoleName( String  _roleName){
		this.roleName = _roleName;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="roleName")
	public String getRoleName(){
		 return this.roleName;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseRoles other = (BaseRoles)o;
		if(
			ObjectUtil.isEqual(getRoleName(), other.getRoleName()) && 
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
		result = result + (roleId!= null ? roleId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.roleId == null){
			 list.add(new ValidationMessage("Field " + FIELD_roleId+  " cannot be null"));
		}

		}
		if( StringUtil.isNullOrEmpty(this.roleName)){
			 list.add(new ValidationMessage("Field " + FIELD_roleName+  " cannot be null"));
		}

		if((this.roleName != null) && (this.roleName.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_roleName+  " cannot be longer than: " + 45));
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
		str.append("roleId = " + roleId + "\n");
;
		str.append("roleName = " + roleName + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (roleId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_roleId, getRoleId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getRoleId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Roles roles = new Roles();
		roles.setFromDB(false);
		roles.setRoleName(getRoleName());
		roles.setActive(getActive());
		//afterClone(roles);
		return roles;
	}
}