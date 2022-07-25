package org.secondopinion.userMgmt.domain; 

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
import org.secondopinion.userMgmt.dto.Roles;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape; 
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseRoles extends BaseDomainObject<Integer> {

	public static final String FIELD_roleId = "roleId";
	public static final String FIELD_roleName = "roleName";
	public static final String FIELD_active = "active";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Integer roleId;
	private String roleName;
	private String active;
	private String createdBy;
	private Date createdDate;

	public void setRoleId( Integer  _roleId){
		this.roleId = _roleId;
	}
	@Id
 	@NotNull
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
	public void setActive( String  _active){
		this.active = _active;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="active")
	public String getActive(){
		 return this.active;
	}
	public void setCreatedBy( String  _createdBy){
		this.createdBy = _createdBy;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="createdBy")
	public String getCreatedBy(){
		 return this.createdBy;
	}
	public void setCreatedDate( Date  _createdDate){
		this.createdDate = _createdDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@NotNull 
	@Column (name="createdDate")
	public Date getCreatedDate(){
		 return this.createdDate;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseRoles other = (BaseRoles)o;
		if(
			ObjectUtil.isEqual(getRoleName(), other.getRoleName()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy()) && 
			ObjectUtil.isEqual(getCreatedDate(), other.getCreatedDate()) && 
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

		if( StringUtil.isNullOrEmpty(this.active)){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if((this.active != null) && (this.active.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be longer than: " + 45));
		}

		if(this.isFromDB()){
			if( StringUtil.isNullOrEmpty(this.createdBy)){
				 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be null"));
			}
		}
		if(this.isFromDB()){
			if((this.createdBy != null) && (this.createdBy.length()>45)){
				 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 45));
			}
		}
		if(this.createdDate == null){
			 list.add(new ValidationMessage("Field " + FIELD_createdDate+  " cannot be null"));
		}

		if(this.isFromDB()){
			if( StringUtil.isNullOrEmpty(this.lastUpdatedBy)){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be null"));
			}
		}
		if(this.isFromDB()){
			if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>45)){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 45));
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
			this.createdBy = AppThreadLocal.getUserName();
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
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
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
		roles.setCreatedDate(getCreatedDate());
		//afterClone(roles);
		return roles;
	}
}