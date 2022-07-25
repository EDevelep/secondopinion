package org.secondopinion.reports.dto; 

import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.reports.domain.BaseReportParameter;
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
		
		public Object getParamValue(Report report, TimeZone timeZone){
			switch (this) {
				case PARAM_COMPANY_ID:
					return 0L;//new Long(UserContextMngr.getCompanyId());
				case PARAM_CURRENT_DATE:
					return DateUtil.getYesterday(timeZone);
				case PARAM_DATE_YESTERDAY:
					return DateUtil.get2PreviousDay(timeZone);
				case PARAM_DATE_MINUS_2:
					return DateUtil.addAndGetDate(timeZone, -3);
				case PARAM_DATE_WEEK_PAST:
					return DateUtil.getWeekPlusPreviousDay(timeZone);
				case PARAM_DATE_MONTH_PAST:
					return DateUtil.get30PlusPreviousDay(timeZone);
				case PARAM_DATE_NINETY_DAY_PAST:
					return DateUtil.get90PlusPreviousDay(timeZone);
				case PARAM_START_OF_MONTH:
					return DateUtil.getStartOfMonth(timeZone);
				case PARAM_USER_ID:
					return 0L;//UserContextMngr.getUser();
				default:
					throw new IllegalArgumentException("Invalid parameter");
			}
		}
	};
}