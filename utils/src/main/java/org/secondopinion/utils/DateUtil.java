package org.secondopinion.utils;

import java.time.LocalTime;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import org.apache.commons.lang3.Validate;


public class DateUtil {
	
	public static final String yyyy_MM_ddspcHHclnMMclnSS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "HH:mm:ss";
	public static final String TIME_FORMAT_HH_MM = "HH:mm";
	
	private DateUtil() {
		//nothing
	}
	public static Date getTime(String source) {
		SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT, Locale.ENGLISH);
		try {
			return formatter.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String convertDateFormat(Date date, String toFormat) {
		DateFormat df = new SimpleDateFormat(toFormat);
		return df.format(date);
	}
	
	public static String convertDateFormat(Date scheduleDate) {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(scheduleDate);
	}

	public static Date getDate( TimeZone  timeZone) {
		return getDateFromLocalDateTime(getUTCDatTime( timeZone));
	}
	public static Date addAndGetDate(Date utilDate, TimeZone  timeZone, int addDays) {
		if(Objects.isNull(timeZone)) { timeZone = TimeZone.getDefault(); }
		return getDateFromLocalDateTime(utilDate.toInstant().atZone(timeZone.toZoneId()).plusDays(addDays).toLocalDateTime());
	}
	public static Date addAndGetDate( TimeZone  timeZone, int addDays) {
		return getDateFromLocalDateTime(getUTCDatTime( timeZone).plusDays(addDays));
	}

	public static String getCurrentDateString( TimeZone  timeZone, String dateFormat) {
		return convertDateFormat(getDate( timeZone), dateFormat);
	}

	public static Date getYesterday( TimeZone  timeZone) {
		 return getDateFromLocalDateTime(getUTCDatTime( timeZone).minusDays(1));
	}

	public static Date get2PreviousDay( TimeZone  timeZone) {
		return getDateFromLocalDateTime(getUTCDatTime( timeZone).minusDays(2));
	}

	public static Date getWeekPlusPreviousDay( TimeZone  timeZone) {
		return getDateFromLocalDateTime(getUTCDatTime( timeZone).minusWeeks(1).minusDays(1));
	}

	public static Date get30PlusPreviousDay( TimeZone  timeZone) {
		return getDateFromLocalDateTime(getUTCDatTime( timeZone).minusDays(31));
	}

	public static Date get90PlusPreviousDay( TimeZone  timeZone) {
		return getDateFromLocalDateTime(getUTCDatTime( timeZone).minusDays(90));
	}
	
	public static Date get60PlusPreviousDay( TimeZone  timeZone) {
		return getDateFromLocalDateTime(getUTCDatTime( timeZone).minusDays(60));
	}

	public static Date getStartOfMonth( TimeZone  timeZone) {
		return getDateFromLocalDateTime(getUTCDatTime( timeZone).withDayOfMonth(1));
	}
	
	public static LocalDateTime getUTCDatTime( TimeZone  timeZone) {
		if(Objects.isNull( timeZone)) { 
			timeZone =  TimeZone.getTimeZone(ZoneId.of("Z"));
		}
		return LocalDateTime.now().atZone( timeZone.toZoneId()).toLocalDateTime();
	}
	
	public static Date getDateFromLocalDateTime(LocalDateTime localDateTime) {
		return Timestamp.valueOf(localDateTime);
	}

	public static Date getMinDateTimeOfDate(Date dateToConvert) {
		LocalDateTime ldt = new Timestamp(
			      dateToConvert.getTime()).toLocalDateTime().toLocalDate().atStartOfDay();
		return getDateFromLocalDateTime(ldt);
	}
	
	public static Date getMaxDateTimeOfDate(Date dateToConvert) {
		LocalDateTime ldt = new Timestamp(
			      dateToConvert.getTime()).toLocalDateTime().toLocalDate().atTime(23, 59, 59);
		return getDateFromLocalDateTime(ldt);
	}

	public static String getCurrentDateString( TimeZone  timeZone) {
		return convertDateFormat(getDate( timeZone), DATE_FORMAT);
	}
	
	public static String getCurrentDateTimeString( TimeZone  timeZone) {
		return convertDateFormat(getDate( timeZone), yyyy_MM_ddspcHHclnMMclnSS);
	}
	
	public static LocalDate getCurrentSQLDate( TimeZone  timeZone) {
		return getUTCDatTime( timeZone).toLocalDate();
	}
	
	public static LocalTime getMaxTime() {
		return LocalTime.MAX;
	}
	
	public static LocalTime getMinTime() {
		return LocalTime.MIN;
	}

	public static Date getDate() {
		return getDate( TimeZone.getTimeZone("UTC"));
	}

	public static LocalDate getCurrentLocalDate( TimeZone  timeZone) {
		return getUTCDatTime(  timeZone).toLocalDate();
	}
	public static LocalTime getCurrentLocalTime( TimeZone  timeZone) {
		return getUTCDatTime(  timeZone).toLocalTime();
	}
	public static Date convertLocalDateToUtilDate(LocalDate localDate,  TimeZone  timeZone) {
		if(Objects.isNull(timeZone)) {
			timeZone =  TimeZone.getTimeZone(ZoneId.of("Z"));
		}
		return Date.from(localDate.atStartOfDay( timeZone.toZoneId()).toInstant());
	}
	
	public static Date getCurrentDateOnly( TimeZone  timeZone) {
		if(Objects.isNull(timeZone)) timeZone = TimeZone.getDefault();
		return Date.from(getCurrentLocalDate( timeZone).atStartOfDay( timeZone.toZoneId()).toInstant());
	}
	
	
	public static LocalDate convertUtilDateToLocalDate(Date utilDate) {
		return LocalDate.parse(DateUtil.convertDateFormat(utilDate));
	}
	
	/*public static Date convertLocalTimeToDate(LocalTime localTime) {
		ZoneId zone = ZoneId.systemDefault();
		TimeZone timeZone = TimeZone.getTimeZone(zone);
		Instant instant = localTime.atDate(getCurrentLocalDate(timeZone)).
		        atZone(zone).toInstant();
		return Date.from(instant);
	}*/
	public static Date getOnlyDateFromUtilDate(Date utilDate, TimeZone timeZone) {
		return convertLocalDateToUtilDate(convertUtilDateToLocalDate(utilDate), timeZone);
	}
	
	public static Date convertLocalTimeToDate(LocalTime localTime) {
		return getDateFromLocalDateTime(LocalDate.now().atTime(localTime));
	}
	public static Date addMinutes(final Date date, final int amount) {
        return add(date, Calendar.MINUTE, amount);
    }
	 private static Date add(final Date date, final int calendarField, final int amount) {
	        validateDateNotNull(date);
	        final Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.add(calendarField, amount);
	        return c.getTime();
	    }
	 private static void validateDateNotNull(final Date date) {
	        Validate.notNull(date, "The date must not be null");
	    }
	public static Date addDay(Date date, Integer numberOfdays) {
		 return add(date, Calendar.DATE, numberOfdays);
	}
	public static Date dateTime(Date date, Date time) {
	    return new Date(
	                     date.getYear(), date.getMonth(), date.getDay(), 
	                     time.getHours(), time.getMinutes(), time.getSeconds()
	                   );
	}
	
	public static Date combine(Date date, Date time) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(time);
	    int hour = cal.get(Calendar.HOUR_OF_DAY);
	    int min = cal.get(Calendar.MINUTE);
	    cal.setTime(date);
	    cal.set(Calendar.HOUR_OF_DAY, hour);
	    cal.set(Calendar.MINUTE, min);
	    return cal.getTime();
	}
}
