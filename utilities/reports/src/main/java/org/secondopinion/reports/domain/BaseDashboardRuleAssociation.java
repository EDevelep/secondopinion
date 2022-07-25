package org.secondopinion.reports.domain; 

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.reports.dto.CompanyTemplate;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.reports.dto.DashboardRuleAssociation;
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseDashboardRuleAssociation extends BaseDomainObject<Long> {

	public static final String FIELD_dashboardRuleAssociationId = "dashboardRuleAssociationId";
	public static final String FIELD_dashboardId = "dashboardId";
	public static final String FIELD_roleId = "roleId";
	public static final String FIELD_companyId = "companyId";
	public static final String FIELD_isDefault = "isDefault";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long dashboardRuleAssociationId;
	private Integer dashboardId;
	private Integer roleId;
	private Integer companyId;
	private Character isDefault;

	public void setDashboardRuleAssociationId( Long  _dashboardRuleAssociationId){
		this.dashboardRuleAssociationId = _dashboardRuleAssociationId;
	}
	@Id
// 	@NotNull
	@Column(name = "dashboardRuleAssociationId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDashboardRuleAssociationId(){
		 return this.dashboardRuleAssociationId;
	}
	public void setDashboardId( Integer  _dashboardId){
		this.dashboardId = _dashboardId;
	}
	@NotNull 
	@Column (name="dashboardId")
	public Integer getDashboardId(){
		 return this.dashboardId;
	}
	public void setRoleId( Integer  _roleId){
		this.roleId = _roleId;
	}
	@NotNull 
	@Column (name="roleId")
	public Integer getRoleId(){
		 return this.roleId;
	}
	public void setCompanyId( Integer  _companyId){
		this.companyId = _companyId;
	}
	@NotNull 
	@Column (name="companyId")
	public Integer getCompanyId(){
		 return this.companyId;
	}
	public void setIsDefault( Character  _isDefault){
		this.isDefault = _isDefault;
	}
	@NotNull 
	@Column (name="isDefault")
	public Character getIsDefault(){
		 return this.isDefault;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseDashboardRuleAssociation other = (BaseDashboardRuleAssociation)o;
		if(
			ObjectUtil.isEqual(getDashboardId(), other.getDashboardId()) && 
			ObjectUtil.isEqual(getRoleId(), other.getRoleId()) && 
			ObjectUtil.isEqual(getCompanyId(), other.getCompanyId()) && 
			ObjectUtil.isEqual(getIsDefault(), other.getIsDefault()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (dashboardRuleAssociationId!= null ? dashboardRuleAssociationId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.dashboardRuleAssociationId == null){
			 list.add(new ValidationMessage("Field " + FIELD_dashboardRuleAssociationId+  " cannot be null"));
		}

		}
		if(this.dashboardId == null){
			 list.add(new ValidationMessage("Field " + FIELD_dashboardId+  " cannot be null"));
		}

		if(this.roleId == null){
			 list.add(new ValidationMessage("Field " + FIELD_roleId+  " cannot be null"));
		}

		if(this.companyId == null){
			 list.add(new ValidationMessage("Field " + FIELD_companyId+  " cannot be null"));
		}

		if(this.isDefault == null){
			 list.add(new ValidationMessage("Field " + FIELD_isDefault+  " cannot be null"));
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
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("dashboardRuleAssociationId = " + dashboardRuleAssociationId + "\n");
		str.append("dashboardId = " + dashboardId + "\n");
		str.append("roleId = " + roleId + "\n");
		str.append("companyId = " + companyId + "\n");
		str.append("isDefault = " + isDefault + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (dashboardRuleAssociationId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_dashboardRuleAssociationId, getDashboardRuleAssociationId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getDashboardRuleAssociationId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		DashboardRuleAssociation dashboardRuleAssociation = new DashboardRuleAssociation();
		dashboardRuleAssociation.setFromDB(false);
		dashboardRuleAssociation.setDashboardId(getDashboardId());
		dashboardRuleAssociation.setRoleId(getRoleId());
		dashboardRuleAssociation.setCompanyId(getCompanyId());
		dashboardRuleAssociation.setIsDefault(getIsDefault());
		//afterClone(dashboardRuleAssociation);
		return dashboardRuleAssociation;
	}
}