package org.secondopinion.userMgmt.domain; 

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.secondopinion.userMgmt.dto.UserRole;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseUserRole extends BaseDomainObject<Integer> {

	public static final String FIELD_userRoleId = "userRoleId";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_roleId = "roleId";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Integer userRoleId;
	private String userId;
	private Integer roleId;
	
	public void setUserRoleId( Integer  _userRoleId){
		this.userRoleId = _userRoleId;
	}
	@Id
// 	@NotNull
	@Column(name = "userRoleId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserRoleId(){
		 return this.userRoleId;
	}
	public void setUserId( String  _userId){
		this.userId = _userId;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="userId")
	public String getUserId(){
		 return this.userId;
	}
	public void setRoleId( Integer  _roleId){
		this.roleId = _roleId;
	}
	@NotNull 
	@Column (name="roleId")
	public Integer getRoleId(){
		 return this.roleId;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseUserRole other = (BaseUserRole)o;
		if(
			ObjectUtil.isEqual(getUserId(), other.getUserId()) && 
			ObjectUtil.isEqual(getRoleId(), other.getRoleId()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (userRoleId!= null ? userRoleId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.userRoleId == null){
			 list.add(new ValidationMessage("Field " + FIELD_userRoleId+  " cannot be null"));
		}

		}
		if( StringUtil.isNullOrEmpty(this.userId)){
			 list.add(new ValidationMessage("Field " + FIELD_userId+  " cannot be null"));
		}

		if((this.userId != null) && (this.userId.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_userId+  " cannot be longer than: " + 45));
		}

		if(this.roleId == null){
			 list.add(new ValidationMessage("Field " + FIELD_roleId+  " cannot be null"));
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
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("userRoleId = " + userRoleId + "\n");
;
		str.append("userId = " + userId + "\n");
		str.append("roleId = " + roleId + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (userRoleId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_userRoleId, getUserRoleId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getUserRoleId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		UserRole userRole = new UserRole();
		userRole.setFromDB(false);
		userRole.setUserId(getUserId());
		userRole.setRoleId(getRoleId());
		return userRole;
	}
}