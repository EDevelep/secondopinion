package org.secondopinion.utils.threadlocal;

import java.util.Date;

/**
 * Created by anil on 19/6/17.
 */
public class AppThreadLocal {
	private static ThreadLocal<RequestInfoBean> tl = new ThreadLocal<RequestInfoBean>();

	public static void setUser(RequestInfoBean bean) {
		reset();
		tl.set(bean);
	}

	public static String getUserName() {
		return tl.get().getUserName();
	}

	public static Date getRequestStartDate() {
		return tl.get().getRequestStartDate();
	}

	public static void reset() {
		tl.set(null);
	}

	public static String getSessionId() {
		return tl.get().getSessionId();
	}
		
	public static String getModuleType() {
		return tl.get().getModuleType();
	}
	
	public static Long getUserId() {
		return tl.get().getUserId();
	}
}
