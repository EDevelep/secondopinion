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

import org.hibernate.validator.constraints.Length;
import org.secondopinion.diagnosticcenter.dto.Privilege;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BasePrivilege extends BaseDomainObject<Long> {

	
	
	public static final String FIELD_privilegeId = "privilegeId";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";
	public static final String FIELD_active = "active";
	public static final String FIELD_functionName = "functionName";
	public static final String FIELD_module = "module";
	public static final String FIELD_privilegeCode = "privilegeCode";
	public static final String FIELD_privilegeName = "privilegeName";
	public static final String FIELD_privilegeType = "privilegeType";
	public static final String FIELD_serviceName = "serviceName";

	private Long privilegeId;
	private String active;
	private String functionName;
	private String module;
	private String privilegeCode;
	private String privilegeName;
	private String privilegeType;
	private String serviceName;

	public void setPrivilegeId( Long  _privilegeId){
		this.privilegeId = _privilegeId;
	}
	@Id
 	@Column(name = "privilegeId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPrivilegeId(){
		 return this.privilegeId;
	}
	public void setActive( String  _active){
		this.active = _active;
	}
	@Length(max=4)
	@Column (name="active")
	public String getActive(){
		 return this.active;
	}
	public void setFunctionName( String  _functionName){
		this.functionName = _functionName;
	}
	@Length(max=600)
	@Column (name="functionName")
	public String getFunctionName(){
		 return this.functionName;
	}
	public void setModule( String  _module){
		this.module = _module;
	}
	@Length(max=600)
	@Column (name="module")
	public String getModule(){
		 return this.module;
	}
	public void setPrivilegeCode( String  _privilegeCode){
		this.privilegeCode = _privilegeCode;
	}
	@Length(max=600)
	@Column (name="privilegeCode")
	public String getPrivilegeCode(){
		 return this.privilegeCode;
	}
	public void setPrivilegeName( String  _privilegeName){
		this.privilegeName = _privilegeName;
	}
	@Length(max=600)
	@Column (name="privilegeName")
	public String getPrivilegeName(){
		 return this.privilegeName;
	}
	public void setPrivilegeType( String  _privilegeType){
		this.privilegeType = _privilegeType;
	}
	@Length(max=180)
	@Column (name="privilegeType")
	public String getPrivilegeType(){
		 return this.privilegeType;
	}
	public void setServiceName( String  _serviceName){
		this.serviceName = _serviceName;
	}
	@Length(max=600)
	@Column (name="serviceName")
	public String getServiceName(){
		 return this.serviceName;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BasePrivilege other = (BasePrivilege)o;
		if(
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getFunctionName(), other.getFunctionName()) && 
			ObjectUtil.isEqual(getModule(), other.getModule()) && 
			ObjectUtil.isEqual(getPrivilegeCode(), other.getPrivilegeCode()) && 
			ObjectUtil.isEqual(getPrivilegeName(), other.getPrivilegeName()) && 
			ObjectUtil.isEqual(getPrivilegeType(), other.getPrivilegeType()) && 
			ObjectUtil.isEqual(getServiceName(), other.getServiceName())){
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
	if(this.isFromDB()){
		if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 100));
		}
	}
		if((this.active != null) && (this.active.length()>4)){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be longer than: " + 4));
		}

		if((this.functionName != null) && (this.functionName.length()>600)){
			 list.add(new ValidationMessage("Field " + FIELD_functionName+  " cannot be longer than: " + 600));
		}

		if((this.module != null) && (this.module.length()>600)){
			 list.add(new ValidationMessage("Field " + FIELD_module+  " cannot be longer than: " + 600));
		}

		if((this.privilegeCode != null) && (this.privilegeCode.length()>600)){
			 list.add(new ValidationMessage("Field " + FIELD_privilegeCode+  " cannot be longer than: " + 600));
		}

		if((this.privilegeName != null) && (this.privilegeName.length()>600)){
			 list.add(new ValidationMessage("Field " + FIELD_privilegeName+  " cannot be longer than: " + 600));
		}

		if((this.privilegeType != null) && (this.privilegeType.length()>180)){
			 list.add(new ValidationMessage("Field " + FIELD_privilegeType+  " cannot be longer than: " + 180));
		}

		if((this.serviceName != null) && (this.serviceName.length()>600)){
			 list.add(new ValidationMessage("Field " + FIELD_serviceName+  " cannot be longer than: " + 600));
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
		str.append("privilegeId = " + privilegeId + "\n");
;
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		str.append("active = " + active + "\n");
		str.append("functionName = " + functionName + "\n");
		str.append("module = " + module + "\n");
		str.append("privilegeCode = " + privilegeCode + "\n");
		str.append("privilegeName = " + privilegeName + "\n");
		str.append("privilegeType = " + privilegeType + "\n");
		str.append("serviceName = " + serviceName + "\n");
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
	public Long getId(){
		return getPrivilegeId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Privilege privilege = new Privilege();
		privilege.setFromDB(false);
		privilege.setActive(getActive());
		privilege.setFunctionName(getFunctionName());
		privilege.setModule(getModule());
		privilege.setPrivilegeCode(getPrivilegeCode());
		privilege.setPrivilegeName(getPrivilegeName());
		privilege.setPrivilegeType(getPrivilegeType());
		privilege.setServiceName(getServiceName());
		//afterClone(privilege);
		return privilege;
	}
}