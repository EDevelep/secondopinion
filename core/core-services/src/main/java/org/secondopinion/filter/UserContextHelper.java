package org.secondopinion.filter;

import java.util.Date;

import org.codehaus.jettison.json.JSONObject;
import org.secondopinion.enums.SecondOpinionModuleEnum;
import org.secondopinion.ssclient.CachedSessionToken;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.utils.threadlocal.RequestInfoBean;

public class UserContextHelper {
	
	public static void setUserContext(String sessionId, String uri) {
		try {
			String userInfo = CachedSessionToken.getUserInfoBySessionId(uri, sessionId);

			JSONObject jsonObject = new JSONObject(userInfo);

			RequestInfoBean requestInfoBean = new RequestInfoBean();
			requestInfoBean.setRequestStartDate(new Date());
			requestInfoBean.setUserId(jsonObject.getLong("userId"));
			requestInfoBean.setUserName(jsonObject.getString("userName"));
			requestInfoBean.setSessionId(sessionId);
			requestInfoBean.setModuleType(jsonObject.getString("moduleType"));

			AppThreadLocal.reset();
			AppThreadLocal.setUser(requestInfoBean);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	public static void  setUserContextForUnRegisterAPI() {
		try {

			RequestInfoBean requestInfoBean = new RequestInfoBean();
			requestInfoBean.setRequestStartDate(new Date());
			requestInfoBean.setUserId(0L);
			requestInfoBean.setUserName("SYSTEM");

			AppThreadLocal.reset();
			AppThreadLocal.setUser(requestInfoBean);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}
	
	public static void updatetUserContextForUnRegisterAPI(String userName, String moduleType, Long userId) {
		try {

			RequestInfoBean requestInfoBean = new RequestInfoBean();
			requestInfoBean.setRequestStartDate(AppThreadLocal.getRequestStartDate());
			requestInfoBean.setUserId(userId);
			requestInfoBean.setUserName(userName);
			requestInfoBean.setModuleType(moduleType);

			AppThreadLocal.reset();
			AppThreadLocal.setUser(requestInfoBean);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}
}
