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
import org.secondopinion.userMgmt.dto.Roleprivileges;

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
public abstract class BaseRoleprivileges extends BaseDomainObject<Integer> {

	public static final String FIELD_rolePrivilegeId = "rolePrivilegeId";
	public static final String FIELD_roleId = "roleId";
	public static final String FIELD_privilegeId = "privilegeId";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";

	private Integer rolePrivilegeId;
	private Integer roleId;
	private String privilegeId;
	private Date createdDate;
	private String createdBy;

	public void setRolePrivilegeId( Integer  _rolePrivilegeId){
		this.rolePrivilegeId = _rolePrivilegeId;
	}
	@Id
 	@NotNull
	@Column(name = "rolePrivilegeId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getRolePrivilegeId(){
		 return this.rolePrivilegeId;
	}
	public void setRoleId( Integer  _roleId){
		this.roleId = _roleId;
	}
	@NotNull 
	@Column (name="roleId")
	public Integer getRoleId(){
		 return this.roleId;
	}
	public void setPrivilegeId( String  _privilegeId){
		this.privilegeId = _privilegeId;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="privilegeId")
	public String getPrivilegeId(){
		 return this.privilegeId;
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
	public void setCreatedBy( String  _createdBy){
		this.createdBy = _createdBy;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="createdBy")
	public String getCreatedBy(){
		 return this.createdBy;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseRoleprivileges other = (BaseRoleprivileges)o;
		if(
			ObjectUtil.isEqual(getRoleId(), other.getRoleId()) && 
			ObjectUtil.isEqual(getPrivilegeId(), other.getPrivilegeId()) && 
			ObjectUtil.isEqual(getCreatedDate(), other.getCreatedDate()) && 
			ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (rolePrivilegeId!= null ? rolePrivilegeId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.rolePrivilegeId == null){
			 list.add(new ValidationMessage("Field " + FIELD_rolePrivilegeId+  " cannot be null"));
		}

		}
		if(this.roleId == null){
			 list.add(new ValidationMessage("Field " + FIELD_roleId+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.privilegeId)){
			 list.add(new ValidationMessage("Field " + FIELD_privilegeId+  " cannot be null"));
		}

		if((this.privilegeId != null) && (this.privilegeId.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_privilegeId+  " cannot be longer than: " + 45));
		}

		if(this.createdDate == null){
			 list.add(new ValidationMessage("Field " + FIELD_createdDate+  " cannot be null"));
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
		if(this.isFromDB()){
			if(this.lastUpdatedTs == null){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedTs+  " cannot be null"));
			}
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
		if(list.size()>0)setHasValidationErrors(true);

		setValidationMessages(list);
		
	}

	@Override
	public final void setAuditFields() {
		if(!isFromDB()){
			this.createdBy = AppThreadLocal.getUserName();
		}
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
		this.lastUpdatedBy = AppThreadLocal.getUserName();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("rolePrivilegeId = " + rolePrivilegeId + "\n");
;
		str.append("roleId = " + roleId + "\n");
		str.append("privilegeId = " + privilegeId + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (rolePrivilegeId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_rolePrivilegeId, getRolePrivilegeId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getRolePrivilegeId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Roleprivileges roleprivileges = new Roleprivileges();
		roleprivileges.setFromDB(false);
		roleprivileges.setRoleId(getRoleId());
		roleprivileges.setPrivilegeId(getPrivilegeId());
		roleprivileges.setCreatedDate(getCreatedDate());
		//afterClone(roleprivileges);
		return roleprivileges;
	}
}