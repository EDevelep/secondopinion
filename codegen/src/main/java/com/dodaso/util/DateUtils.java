package com.dodaso.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	
	public static String getCurrentDateString(String format){
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}
}
