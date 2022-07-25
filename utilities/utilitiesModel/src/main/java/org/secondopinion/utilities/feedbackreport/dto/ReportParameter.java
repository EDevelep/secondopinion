package org.secondopinion.utilities.feedbackreport.dto; 

import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.utilities.feedbackreport.domain.BaseReportParameter;
import org.secondopinion.utils.DateUtil; 

@Entity 
@Table (name="reportparameter")
public class ReportParameter extends BaseReportParameter{
	public enum PARAMETER {PARAM_COMPANY_ID("companyId"),
		PARAM_CURRENT_DATE ("date"),
		PARAM_DATE_YESTERDAY("date"),
		PARAM_DATE_MINUS_2("date"),
		PARAM_DATE_WEEK_PAST("date"),
		PARAM_DATE_MONTH_PAST("date"),
		PARAM_DATE_NINETY_DAY_PAST("date"),
		PARAM_START_OF_MONTH("date"),
		PARAM_USER_ID ("userId");
		
		private final String paramName;
	
		private PARAMETER(String _paramName) {
			this.paramName = _paramName;
		}
		
		public String getParamName() {
			return paramName;
		}
		
		public Object getParamValue(Report report){
			switch (this) {
				case PARAM_COMPANY_ID:
					return report.getCompanyId();
				case PARAM_CURRENT_DATE:
					return DateUtil.getYesterday( TimeZone.getTimeZone("UTC"));
				case PARAM_DATE_YESTERDAY:
					return DateUtil.get2PreviousDay( TimeZone.getTimeZone("UTC"));
				case PARAM_DATE_MINUS_2:
					return DateUtil.addAndGetDate( TimeZone.getTimeZone("UTC"), -3);
				case PARAM_DATE_WEEK_PAST:
					return DateUtil.getWeekPlusPreviousDay( TimeZone.getTimeZone("UTC"));
				case PARAM_DATE_MONTH_PAST:
					return DateUtil.get30PlusPreviousDay( TimeZone.getTimeZone("UTC"));
				case PARAM_DATE_NINETY_DAY_PAST:
					return DateUtil.get90PlusPreviousDay( TimeZone.getTimeZone("UTC"));
				case PARAM_START_OF_MONTH:
					return DateUtil.getStartOfMonth( TimeZone.getTimeZone("UTC"));
				case PARAM_USER_ID:
					return report.getLastUpdatedBy();
				default:
					throw new IllegalArgumentException("Invalid parameter");
			}
		}
	};
}