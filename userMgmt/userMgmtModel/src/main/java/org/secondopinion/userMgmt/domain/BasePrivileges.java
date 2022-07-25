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
import org.secondopinion.userMgmt.dto.Privileges;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape; 
@MappedSuperclass 
public abstract class BasePrivileges extends BaseDomainObject<Integer> {

	public static final String FIELD_privilegeId = "privilegeId";
	public static final String FIELD_privilegeCode = "privilegeCode";
	public static final String FIELD_privilegeName = "privilegeName";
	public static final String FIELD_serviceName = "serviceName";
	public static final String FIELD_functionName = "functionName";
	public static final String FIELD_active = "active";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";

	private Integer privilegeId;
	private String privilegeCode;
	private String privilegeName;
	private String serviceName;
	private String functionName;
	private Character active;
	private Date createdDate;
	private String createdBy;

	public void setPrivilegeId( Integer  _privilegeId){
		this.privilegeId = _privilegeId;
	}
	@Id
// 	@NotNull
	@Column(name = "privilegeId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getPrivilegeId(){
		 return this.privilegeId;
	}
	public void setPrivilegeCode( String  _privilegeCode){
		this.privilegeCode = _privilegeCode;
	}
	@Length(max=45)
	@Column (name="privilegeCode")
	public String getPrivilegeCode(){
		 return this.privilegeCode;
	}
	public void setPrivilegeName( String  _privilegeName){
		this.privilegeName = _privilegeName;
	}
	@NotNull 
	@Length(max=100)
	@Column (name="privilegeName")
	public String getPrivilegeName(){
		 return this.privilegeName;
	}
	public void setServiceName( String  _serviceName){
		this.serviceName = _serviceName;
	}
	@NotNull 
	@Length(max=150)
	@Column (name="serviceName")
	public String getServiceName(){
		 return this.serviceName;
	}
	public void setFunctionName( String  _functionName){
		this.functionName = _functionName;
	}
	@NotNull 
	@Length(max=150)
	@Column (name="functionName")
	public String getFunctionName(){
		 return this.functionName;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
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
		BasePrivileges other = (BasePrivileges)o;
		if(
			ObjectUtil.isEqual(getPrivilegeCode(), other.getPrivilegeCode()) && 
			ObjectUtil.isEqual(getPrivilegeName(), other.getPrivilegeName()) && 
			ObjectUtil.isEqual(getServiceName(), other.getServiceName()) && 
			ObjectUtil.isEqual(getFunctionName(), other.getFunctionName()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
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
		result = result + (privilegeId!= null ? privilegeId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.privilegeId == null){
			 list.add(new ValidationMessage("Field " + FIELD_privilegeId+  " cannot be null"));
		}

		}
		if((this.privilegeCode != null) && (this.privilegeCode.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_privilegeCode+  " cannot be longer than: " + 45));
		}

		if( StringUtil.isNullOrEmpty(this.privilegeName)){
			 list.add(new ValidationMessage("Field " + FIELD_privilegeName+  " cannot be null"));
		}

		if((this.privilegeName != null) && (this.privilegeName.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_privilegeName+  " cannot be longer than: " + 100));
		}

		if( StringUtil.isNullOrEmpty(this.serviceName)){
			 list.add(new ValidationMessage("Field " + FIELD_serviceName+  " cannot be null"));
		}

		if((this.serviceName != null) && (this.serviceName.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_serviceName+  " cannot be longer than: " + 150));
		}

		if( StringUtil.isNullOrEmpty(this.functionName)){
			 list.add(new ValidationMessage("Field " + FIELD_functionName+  " cannot be null"));
		}

		if((this.functionName != null) && (this.functionName.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_functionName+  " cannot be longer than: " + 150));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
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
		str.append("privilegeId = " + privilegeId + "\n");
;
		str.append("privilegeCode = " + privilegeCode + "\n");
		str.append("privilegeName = " + privilegeName + "\n");
		str.append("serviceName = " + serviceName + "\n");
		str.append("functionName = " + functionName + "\n");
		str.append("active = " + active + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (privilegeId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_privilegeId, getPrivilegeId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getPrivilegeId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Privileges privileges = new Privileges();
		privileges.setFromDB(false);
		privileges.setPrivilegeCode(getPrivilegeCode());
		privileges.setPrivilegeName(getPrivilegeName());
		privileges.setServiceName(getServiceName());
		privileges.setFunctionName(getFunctionName());
		privileges.setActive(getActive());
		privileges.setCreatedDate(getCreatedDate());
		//afterClone(privileges);
		return privileges;
	}
}