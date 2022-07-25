package org.secondopinion.superadmin.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.superadmin.dto.Rolemenuprivilage;
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
public abstract class BaseRolemenuprivilage extends BaseDomainObject<Integer> {

	public static final String FIELD_rolemenuprivilageId = "rolemenuprivilageId";
	public static final String FIELD_menuId = "menuId";
	public static final String FIELD_roleId = "roleId";
	public static final String FIELD_active = "active";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Integer rolemenuprivilageId;
	private Integer menuId;
	private Integer roleId;
	private String active;
	private String createdBy;
	private Date createdDate;

	public void setRolemenuprivilageId( Integer  _rolemenuprivilageId){
		this.rolemenuprivilageId = _rolemenuprivilageId;
	}
	@Id
 	@Column(name = "rolemenuprivilageId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getRolemenuprivilageId(){
		 return this.rolemenuprivilageId;
	}
	public void setMenuId( Integer  _menuId){
		this.menuId = _menuId;
	}
	@NotNull 
	@Column (name="menuId")
	public Integer getMenuId(){
		 return this.menuId;
	}
	public void setRoleId( Integer  _roleId){
		this.roleId = _roleId;
	}
	@NotNull 
	@Column (name="roleId")
	public Integer getRoleId(){
		 return this.roleId;
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
		BaseRolemenuprivilage other = (BaseRolemenuprivilage)o;
		if(
			ObjectUtil.isEqual(getMenuId(), other.getMenuId()) && 
			ObjectUtil.isEqual(getRoleId(), other.getRoleId()) && 
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
		result = result + (rolemenuprivilageId!= null ? rolemenuprivilageId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.rolemenuprivilageId == null){
			 list.add(new ValidationMessage("Field " + FIELD_rolemenuprivilageId+  " cannot be null"));
		}

		}
		if(this.menuId == null){
			 list.add(new ValidationMessage("Field " + FIELD_menuId+  " cannot be null"));
		}

		if(this.roleId == null){
			 list.add(new ValidationMessage("Field " + FIELD_roleId+  " cannot be null"));
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
		str.append("rolemenuprivilageId = " + rolemenuprivilageId + "\n");
;
		str.append("menuId = " + menuId + "\n");
		str.append("roleId = " + roleId + "\n");
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
		return (rolemenuprivilageId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_rolemenuprivilageId, getRolemenuprivilageId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getRolemenuprivilageId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Rolemenuprivilage rolemenuprivilage = new Rolemenuprivilage();
		rolemenuprivilage.setFromDB(false);
		rolemenuprivilage.setMenuId(getMenuId());
		rolemenuprivilage.setRoleId(getRoleId());
		rolemenuprivilage.setActive(getActive());
		rolemenuprivilage.setCreatedDate(getCreatedDate());
		//afterClone(rolemenuprivilage);
		return rolemenuprivilage;
	}
}