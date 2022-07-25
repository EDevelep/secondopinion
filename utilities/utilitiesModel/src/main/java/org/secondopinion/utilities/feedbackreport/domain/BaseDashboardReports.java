package org.secondopinion.utilities.feedbackreport.domain; 

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
import org.secondopinion.utilities.feedbackreport.dto.DashboardReports;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseDashboardReports extends BaseDomainObject<Long> {

	public static final String FIELD_dashboardReportsId = "dashboardReportsId";
	public static final String FIELD_dashboardId = "dashboardId";
	public static final String FIELD_reportId = "reportId";
	public static final String FIELD_orderId = "orderId";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long dashboardReportsId;
	private Integer dashboardId;
	private Long reportId;
	private Integer orderId;

	public void setDashboardReportsId( Long  _dashboardReportsId){
		this.dashboardReportsId = _dashboardReportsId;
	}
	@Id
// 	@NotNull
	@Column(name = "dashboardReportsId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDashboardReportsId(){
		 return this.dashboardReportsId;
	}
	public void setDashboardId( Integer  _dashboardId){
		this.dashboardId = _dashboardId;
	}
	@NotNull 
	@Column (name="dashboardId")
	public Integer getDashboardId(){
		 return this.dashboardId;
	}
	public void setReportId( Long  _reportId){
		this.reportId = _reportId;
	}
	@NotNull 
	@Column (name="reportId")
	public Long getReportId(){
		 return this.reportId;
	}
	public void setOrderId( Integer  _orderId){
		this.orderId = _orderId;
	}
	@NotNull 
	@Column (name="orderId")
	public Integer getOrderId(){
		 return this.orderId;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseDashboardReports other = (BaseDashboardReports)o;
		if(
			ObjectUtil.isEqual(getDashboardId(), other.getDashboardId()) && 
			ObjectUtil.isEqual(getReportId(), other.getReportId()) && 
			ObjectUtil.isEqual(getOrderId(), other.getOrderId()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (dashboardReportsId!= null ? dashboardReportsId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.dashboardReportsId == null){
			 list.add(new ValidationMessage("Field " + FIELD_dashboardReportsId+  " cannot be null"));
		}

		}
		if(this.dashboardId == null){
			 list.add(new ValidationMessage("Field " + FIELD_dashboardId+  " cannot be null"));
		}

		if(this.reportId == null){
			 list.add(new ValidationMessage("Field " + FIELD_reportId+  " cannot be null"));
		}

		if(this.orderId == null){
			 list.add(new ValidationMessage("Field " + FIELD_orderId+  " cannot be null"));
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
		str.append("dashboardReportsId = " + dashboardReportsId + "\n");
;
		str.append("dashboardId = " + dashboardId + "\n");
		str.append("reportId = " + reportId + "\n");
		str.append("orderId = " + orderId + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (dashboardReportsId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_dashboardReportsId, getDashboardReportsId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getDashboardReportsId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		DashboardReports dashboardReports = new DashboardReports();
		dashboardReports.setFromDB(false);
		dashboardReports.setDashboardId(getDashboardId());
		dashboardReports.setReportId(getReportId());
		dashboardReports.setOrderId(getOrderId());
		//afterClone(dashboardReports);
		return dashboardReports;
	}
}