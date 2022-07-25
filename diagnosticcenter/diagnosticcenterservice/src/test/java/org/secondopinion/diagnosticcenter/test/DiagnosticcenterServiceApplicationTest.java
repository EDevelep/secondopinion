package org.secondopinion.diagnosticcenter.test;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.diagnosticcenter.DiagnosticcenterServiceApplication;
import org.secondopinion.diagnosticcenter.dto.Role;
import org.secondopinion.enums.SecondOpinionModuleEnum;
import org.secondopinion.filter.MutableHttpServletRequest;

import org.secondopinion.ssclient.CachedSessionToken;
import org.secondopinion.utils.UserMgmtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DiagnosticcenterServiceApplication.class })
public class DiagnosticcenterServiceApplicationTest {

	
	@Autowired
	private AppProperties appProperties;

	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Before
	public void sessionId() {

		String sessionId;
		try {
			
			sessionId = CachedSessionToken.createSessionId(appProperties.getSsu().getCreateTokenUri(), "vishnu@qontrack.com",  
					SecondOpinionModuleEnum.DIAGNOSTIC_CENTER.name(), 12L,  Arrays.asList(Role.RoleEnum.ADMIN.name()));
			MutableHttpServletRequest mutableHttpServletRequest = MutableHttpServletRequest.INSTANCE(httpServletRequest);
			mutableHttpServletRequest.putHeader(UserMgmtUtil.AUTHORIZATION, sessionId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void contextLoads() {
		System.out.println(appProperties);
	}
}
