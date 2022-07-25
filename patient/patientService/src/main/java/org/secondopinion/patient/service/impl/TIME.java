package org.secondopinion.patient.service.impl;

import java.util.TimeZone;

import org.secondopinion.utils.DateUtil;

public enum TIME {
	CURRENT_DATE, YESTERDAY, WEEK, THIRTY_DAY, START_OF_MONTH, SIXTY_DAY, NINETY_DAY;
	
	
	
	public Object getDateValue( TimeZone timeZone){
		switch (this) {
			case CURRENT_DATE:
				return DateUtil.getYesterday(timeZone);
			case YESTERDAY:
				return DateUtil.get2PreviousDay(timeZone);
			case WEEK:
				return DateUtil.getWeekPlusPreviousDay(timeZone);
			case THIRTY_DAY:
				return DateUtil.get30PlusPreviousDay(timeZone);
			case NINETY_DAY:
				return DateUtil.get90PlusPreviousDay(timeZone);
			case START_OF_MONTH:
				return DateUtil.getStartOfMonth(timeZone);
			case SIXTY_DAY:
				return DateUtil.get60PlusPreviousDay(timeZone);
			default:
				throw new IllegalArgumentException("Invalid parameter");
		}
	}
}
