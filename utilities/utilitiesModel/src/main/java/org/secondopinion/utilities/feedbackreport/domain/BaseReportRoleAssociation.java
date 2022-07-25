package org.secondopinion.utilities.feedbackreport.domain; 

import java.util.ArrayList; 
import java.util.List; 

import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utilities.feedbackreport.dto.ReportRoleAssociation;
import org.secondopinion.utils.StringUtil; 
import org.secondopinion.utils.ObjectUtil; 

import javax.persistence.Column; 
import javax.persistence.MappedSuperclass; 
import javax.validation.constraints.NotNull; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Transient;
@MappedSuperclass 
public abstract class BaseReportRoleAssociation extends BaseDomainObject<Long> {

	public static final String FIELD_reportRoleAssociationId = "reportRoleAssociationId";
	public static final String FIELD_reportId = "reportId";
	public static final String FIELD_reportRoleAssociationcol = "reportRoleAssociationcol";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long reportRoleAssociationId;
	private Long reportId;
	private Integer reportRoleAssociationcol;

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
	public void setReportId( Long  _reportId){
		this.reportId = _reportId;
	}
	@NotNull 
	@Column (name="reportId")
	public Long getReportId(){
		 return this.reportId;
	}
	public void setReportRoleAssociationcol( Integer  _reportRoleAssociationcol){
		this.reportRoleAssociationcol = _reportRoleAssociationcol;
	}
	@NotNull 
	@Column (name="reportRoleAssociationcol")
	public Integer getReportRoleAssociationcol(){
		 return this.reportRoleAssociationcol;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseReportRoleAssociation other = (BaseReportRoleAssociation)o;
		if(
			ObjectUtil.isEqual(getReportId(), other.getReportId()) && 
			ObjectUtil.isEqual(getReportRoleAssociationcol(), other.getReportRoleAssociationcol()) && 
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
		if(this.reportId == null){
			 list.add(new ValidationMessage("Field " + FIELD_reportId+  " cannot be null"));
		}

		if(this.reportRoleAssociationcol == null){
			 list.add(new ValidationMessage("Field " + FIELD_reportRoleAssociationcol+  " cannot be null"));
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
		str.append("reportRoleAssociationId = " + reportRoleAssociationId + "\n");
;
		str.append("reportId = " + reportId + "\n");
		str.append("reportRoleAssociationcol = " + reportRoleAssociationcol + "\n");
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
		reportRoleAssociation.setReportId(getReportId());
		reportRoleAssociation.setReportRoleAssociationcol(getReportRoleAssociationcol());
		//afterClone(reportRoleAssociation);
		return reportRoleAssociation;
	}
}