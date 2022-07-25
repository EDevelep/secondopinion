package com.dodaso.threadlocal;

import java.util.Date;

public class AppThreadLocal {
	private static ThreadLocal<UserBean> tl = new ThreadLocal<UserBean>();
	
	public static void setUser(UserBean bean) {
		reset();
		tl.set(bean);
	}
	
	public static String getUserName(){
		return tl.get().getUserName();
	}
	
	public static Date getRequestStartDate(){
		return tl.get().getRequestStartDate();
	}
	
	public static void reset(){
		tl.set(null);
	}
}
