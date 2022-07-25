package org.secondopinion.pharmacy.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.pharmacy.dto.Roletofunction;
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
public abstract class BaseRoletofunction extends BaseDomainObject<Integer> {

	public static final String FIELD_roletofunctionId = "roletofunctionId";
	public static final String FIELD_roleId = "roleId";
	public static final String FIELD_clientId = "clientId";
	public static final String FIELD_functionName = "functionName";
	public static final String FIELD_active = "active";
	
	private Integer roletofunctionId;
	private Integer roleId;
	private Integer clientId;
	private String functionName;
	private Character active;

	public void setRoletofunctionId( Integer  _roletofunctionId){
		this.roletofunctionId = _roletofunctionId;
	}
	@Id
 	@Column(name = "roletofunctionId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getRoletofunctionId(){
		 return this.roletofunctionId;
	}
	public void setRoleId( Integer  _roleId){
		this.roleId = _roleId;
	}
	@NotNull 
	@Column (name="roleId")
	public Integer getRoleId(){
		 return this.roleId;
	}
	public void setClientId( Integer  _clientId){
		this.clientId = _clientId;
	}
	@NotNull 
	@Column (name="clientId")
	public Integer getClientId(){
		 return this.clientId;
	}
	public void setFunctionName( String  _functionName){
		this.functionName = _functionName;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="functionName")
	public String getFunctionName(){
		 return this.functionName;
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
		BaseRoletofunction other = (BaseRoletofunction)o;
		if(
			ObjectUtil.isEqual(getRoleId(), other.getRoleId()) && 
			ObjectUtil.isEqual(getClientId(), other.getClientId()) && 
			ObjectUtil.isEqual(getFunctionName(), other.getFunctionName()) && 
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
		result = result + (roletofunctionId!= null ? roletofunctionId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.roletofunctionId == null){
			 list.add(new ValidationMessage("Field " + FIELD_roletofunctionId+  " cannot be null"));
		}

		}
		if(this.roleId == null){
			 list.add(new ValidationMessage("Field " + FIELD_roleId+  " cannot be null"));
		}

		if(this.clientId == null){
			 list.add(new ValidationMessage("Field " + FIELD_clientId+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.functionName)){
			 list.add(new ValidationMessage("Field " + FIELD_functionName+  " cannot be null"));
		}

		if((this.functionName != null) && (this.functionName.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_functionName+  " cannot be longer than: " + 45));
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
		str.append("roletofunctionId = " + roletofunctionId + "\n");
;
		str.append("roleId = " + roleId + "\n");
		str.append("clientId = " + clientId + "\n");
		str.append("functionName = " + functionName + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (roletofunctionId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_roletofunctionId, getRoletofunctionId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getRoletofunctionId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Roletofunction roletofunction = new Roletofunction();
		roletofunction.setFromDB(false);
		roletofunction.setRoleId(getRoleId());
		roletofunction.setClientId(getClientId());
		roletofunction.setFunctionName(getFunctionName());
		roletofunction.setActive(getActive());
		//afterClone(roletofunction);
		return roletofunction;
	}
}