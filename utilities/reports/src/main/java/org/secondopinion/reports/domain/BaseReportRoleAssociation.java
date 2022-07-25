package org.secondopinion.reports.domain; 

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
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.reports.dto.CompanyTemplate;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.reports.dto.ReportRoleAssociation;
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseReportRoleAssociation extends BaseDomainObject<Long> {

	public static final String FIELD_reportRoleAssociationId = "reportRoleAssociationId";
	public static final String FIELD_companyId = "companyId";
	public static final String FIELD_reportId = "reportId";
	public static final String FIELD_roleId = "roleId";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long reportRoleAssociationId;
	private Integer companyId;
	private Long reportId;
	private Integer roleId;

	public void setReportRoleAssociationId( Long  _reportRoleAssociationId){
		this.reportRoleAssociationId = _reportRoleAssociationId;
	}
	@Id
// 	@NotNull
	@Column(name = "reportRoleAssociationId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getReportRoleAssociationId(){
		 return this.reportRoleAssociationId;
	}
	public void setCompanyId( Integer  _companyId){
		this.companyId = _companyId;
	}
	@NotNull 
	@Column (name="companyId")
	public Integer getCompanyId(){
		 return this.companyId;
	}
	public void setReportId( Long  _reportId){
		this.reportId = _reportId;
	}
	@NotNull 
	@Column (name="reportId")
	public Long getReportId(){
		 return this.reportId;
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
		BaseReportRoleAssociation other = (BaseReportRoleAssociation)o;
		if(
			ObjectUtil.isEqual(getCompanyId(), other.getCompanyId()) && 
			ObjectUtil.isEqual(getReportId(), other.getReportId()) && 
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
		result = result + (reportRoleAssociationId!= null ? reportRoleAssociationId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.reportRoleAssociationId == null){
			 list.add(new ValidationMessage("Field " + FIELD_reportRoleAssociationId+  " cannot be null"));
		}

		}
		if(this.companyId == null){
			 list.add(new ValidationMessage("Field " + FIELD_companyId+  " cannot be null"));
		}

		if(this.reportId == null){
			 list.add(new ValidationMessage("Field " + FIELD_reportId+  " cannot be null"));
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
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("reportRoleAssociationId = " + reportRoleAssociationId + "\n");
		str.append("companyId = " + companyId + "\n");
		str.append("reportId = " + reportId + "\n");
		str.append("roleId = " + roleId + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (reportRoleAssociationId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_reportRoleAssociationId, getReportRoleAssociationId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getReportRoleAssociationId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		ReportRoleAssociation reportRoleAssociation = new ReportRoleAssociation();
		reportRoleAssociation.setFromDB(false);
		reportRoleAssociation.setCompanyId(getCompanyId());
		reportRoleAssociation.setReportId(getReportId());
		reportRoleAssociation.setRoleId(getRoleId());
		//afterClone(reportRoleAssociation);
		return reportRoleAssociation;
	}
}