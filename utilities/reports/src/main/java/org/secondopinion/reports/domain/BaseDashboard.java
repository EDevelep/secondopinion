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

import org.hibernate.validator.constraints.Length;
import org.secondopinion.reports.dto.Dashboard;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.reports.dto.CompanyTemplate;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseDashboard extends BaseDomainObject<Integer> {

	public static final String FIELD_dashboardId = "dashboardId";
	public static final String FIELD_companyId = "companyId";
	public static final String FIELD_name = "name";
	public static final String FIELD_defaultDashboard = "defaultDashboard";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Integer dashboardId;
	private Integer companyId;
	private String name;
	private Character defaultDashboard;
	
	public void setDashboardId( Integer  _dashboardId){
		this.dashboardId = _dashboardId;
	}
	@Id
// 	@NotNull
	@Column(name = "dashboardId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getDashboardId(){
		 return this.dashboardId;
	}
	public void setCompanyId( Integer  _companyId){
		this.companyId = _companyId;
	}
	@Column (name="companyId")
	public Integer getCompanyId(){
		 return this.companyId;
	}
	public void setName( String  _name){
		this.name = _name;
	}
	@NotNull 
	@Length(max=150)
	@Column (name="name")
	public String getName(){
		 return this.name;
	}
	public void setDefaultDashboard( Character  _defaultDashboard){
		this.defaultDashboard = _defaultDashboard;
	}
	@NotNull 
	@Column (name="defaultDashboard")
	public Character getDefaultDashboard(){
		 return this.defaultDashboard;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseDashboard other = (BaseDashboard)o;
		if(
			ObjectUtil.isEqual(getCompanyId(), other.getCompanyId()) && 
			ObjectUtil.isEqual(getName(), other.getName()) && 
			ObjectUtil.isEqual(getDefaultDashboard(), other.getDefaultDashboard()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (dashboardId!= null ? dashboardId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.dashboardId == null){
			 list.add(new ValidationMessage("Field " + FIELD_dashboardId+  " cannot be null"));
		}

		}
		if( StringUtil.isNullOrEmpty(this.name)){
			 list.add(new ValidationMessage("Field " + FIELD_name+  " cannot be null"));
		}

		if((this.name != null) && (this.name.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_name+  " cannot be longer than: " + 150));
		}

		if(this.defaultDashboard == null){
			 list.add(new ValidationMessage("Field " + FIELD_defaultDashboard+  " cannot be null"));
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
		str.append("dashboardId = " + dashboardId + "\n");
;
		str.append("companyId = " + companyId + "\n");
		str.append("name = " + name + "\n");
		str.append("defaultDashboard = " + defaultDashboard + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (dashboardId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_dashboardId, getDashboardId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getDashboardId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Dashboard dashboard = new Dashboard();
		dashboard.setFromDB(false);
		dashboard.setCompanyId(getCompanyId());
		dashboard.setName(getName());
		dashboard.setDefaultDashboard(getDefaultDashboard());
		//afterClone(dashboard);
		return dashboard;
	}
}