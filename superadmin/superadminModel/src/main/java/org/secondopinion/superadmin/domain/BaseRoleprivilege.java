package org.secondopinion.superadmin.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 

import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.superadmin.dto.Roleprivilege;
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
public abstract class BaseRoleprivilege extends BaseDomainObject<Integer> {

	public static final String FIELD_roleprivilegeId = "roleprivilegeId";
	public static final String FIELD_roleId = "roleId";
	public static final String FIELD_privilegeId = "privilegeId";
	public static final String FIELD_serviceName = "serviceName";
	public static final String FIELD_moduleName = "moduleName";
	public static final String FIELD_active = "active";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Integer roleprivilegeId;
	private Integer roleId;
	private Integer privilegeId;
	private String serviceName;
	private String moduleName;
	private String active;
	private String createdBy;
	private Date createdDate;

	public void setRoleprivilegeId( Integer  _roleprivilegeId){
		this.roleprivilegeId = _roleprivilegeId;
	}
	@Id
 	@Column(name = "roleprivilegeId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getRoleprivilegeId(){
		 return this.roleprivilegeId;
	}
	public void setRoleId( Integer  _roleId){
		this.roleId = _roleId;
	}
	@NotNull 
	@Column (name="roleId")
	public Integer getRoleId(){
		 return this.roleId;
	}
	public void setPrivilegeId( Integer  _privilegeId){
		this.privilegeId = _privilegeId;
	}
	@NotNull 
	@Column (name="privilegeId")
	public Integer getPrivilegeId(){
		 return this.privilegeId;
	}
	public void setServiceName( String  _serviceName){
		this.serviceName = _serviceName;
	}
	@NotNull 
	@Length(max=145)
	@Column (name="serviceName")
	public String getServiceName(){
		 return this.serviceName;
	}
	public void setModuleName( String  _moduleName){
		this.moduleName = _moduleName;
	}
	@NotNull 
	@Length(max=145)
	@Column (name="moduleName")
	public String getModuleName(){
		 return this.moduleName;
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
	@Length(max=45)
	@Column (name="createdBy")
	public String getCreatedBy(){
		 return this.createdBy;
	}
	public void setCreatedDate( Date  _createdDate){
		this.createdDate = _createdDate;
	}
	@Column (name="createdDate")
	public Date getCreatedDate(){
		 return this.createdDate;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseRoleprivilege other = (BaseRoleprivilege)o;
		if(
			ObjectUtil.isEqual(getRoleId(), other.getRoleId()) && 
			ObjectUtil.isEqual(getPrivilegeId(), other.getPrivilegeId()) && 
			ObjectUtil.isEqual(getServiceName(), other.getServiceName()) && 
			ObjectUtil.isEqual(getModuleName(), other.getModuleName()) && 
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
		result = result + (roleprivilegeId!= null ? roleprivilegeId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.roleprivilegeId == null){
			 list.add(new ValidationMessage("Field " + FIELD_roleprivilegeId+  " cannot be null"));
		}

		}
		if(this.roleId == null){
			 list.add(new ValidationMessage("Field " + FIELD_roleId+  " cannot be null"));
		}

		if(this.privilegeId == null){
			 list.add(new ValidationMessage("Field " + FIELD_privilegeId+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.serviceName)){
			 list.add(new ValidationMessage("Field " + FIELD_serviceName+  " cannot be null"));
		}

		if((this.serviceName != null) && (this.serviceName.length()>145)){
			 list.add(new ValidationMessage("Field " + FIELD_serviceName+  " cannot be longer than: " + 145));
		}

		if( StringUtil.isNullOrEmpty(this.moduleName)){
			 list.add(new ValidationMessage("Field " + FIELD_moduleName+  " cannot be null"));
		}

		if((this.moduleName != null) && (this.moduleName.length()>145)){
			 list.add(new ValidationMessage("Field " + FIELD_moduleName+  " cannot be longer than: " + 145));
		}

		if( StringUtil.isNullOrEmpty(this.active)){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if((this.active != null) && (this.active.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be longer than: " + 45));
		}

	if(this.isFromDB()){
		if((this.createdBy != null) && (this.createdBy.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 45));
		}
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
			this.createdBy = AppThreadLocal.getUserName();
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("roleprivilegeId = " + roleprivilegeId + "\n");
;
		str.append("roleId = " + roleId + "\n");
		str.append("privilegeId = " + privilegeId + "\n");
		str.append("serviceName = " + serviceName + "\n");
		str.append("moduleName = " + moduleName + "\n");
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
		return (roleprivilegeId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_roleprivilegeId, getRoleprivilegeId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getRoleprivilegeId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Roleprivilege roleprivilege = new Roleprivilege();
		roleprivilege.setFromDB(false);
		roleprivilege.setRoleId(getRoleId());
		roleprivilege.setPrivilegeId(getPrivilegeId());
		roleprivilege.setServiceName(getServiceName());
		roleprivilege.setModuleName(getModuleName());
		roleprivilege.setActive(getActive());
		roleprivilege.setCreatedDate(getCreatedDate());
		//afterClone(roleprivilege);
		return roleprivilege;
	}
}