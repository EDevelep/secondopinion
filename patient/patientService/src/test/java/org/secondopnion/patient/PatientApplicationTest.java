package org.secondopnion.patient;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.enums.SecondOpinionModuleEnum;
import org.secondopinion.filter.MutableHttpServletRequest;
import org.secondopinion.patient.PatientServiceApplication;
import org.secondopinion.ssclient.CachedSessionToken;
import org.secondopinion.utils.UserMgmtUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.utils.threadlocal.RequestInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PatientServiceApplication.class })
public class PatientApplicationTest {
	@Autowired
	private AppProperties appProperties;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Before
	public void sessionId() {

		String sessionId;
		try {

			sessionId = CachedSessionToken.createSessionId(appProperties.getSsu().getCreateTokenUri(),
					"vishnu@qontrack.com", SecondOpinionModuleEnum.PATIENT.name(), 12L, Arrays.asList("PATIENT"));
			setUserContext(sessionId);
			MutableHttpServletRequest mutableHttpServletRequest = MutableHttpServletRequest
					.INSTANCE(httpServletRequest);
			mutableHttpServletRequest.putHeader(UserMgmtUtil.AUTHORIZATION, sessionId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void setUserContext(String sessionId) {
		try {
			String userInfo = CachedSessionToken.getUserInfoBySessionId(appProperties.getSsu().getUserinfoUri(),
					sessionId);

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

	@Test
	public void contextLoads() {
		System.out.println(appProperties);
	}
}
