package org.secondopinion.patient.service.helper;

import java.util.TimeZone;


import org.secondopinion.utils.DateUtil;

public enum PARAMETER {
	PARAM_CURRENT_DATE("date"), PARAM_DATE_YESTERDAY("date"), PARAM_DATE_MINUS_2("date"), PARAM_DATE_WEEK_PAST("date"),
	PARAM_DATE_MONTH_PAST("date"), PARAM_DATE_NINETY_DAY_PAST("date"), PARAM_START_OF_MONTH("date"),
	PARAM_USER_ID("userId");

	private final String paramName;

	private PARAMETER(String _paramName) {
		this.paramName = _paramName;
	}

	public String getParamName() {
		return paramName;
	}

	public Object getParamValue(ReportData report, TimeZone timeZone) {
		switch (this) {

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
			return 0L;// UserContextMngr.getUser();
		default:
			throw new IllegalArgumentException("Invalid parameter");
		}
	}
};
