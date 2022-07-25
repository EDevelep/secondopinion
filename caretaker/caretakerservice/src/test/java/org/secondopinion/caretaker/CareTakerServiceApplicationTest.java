package org.secondopinion.caretaker;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.enums.SecondOpinionModuleEnum;
import org.secondopinion.filter.MutableHttpServletRequest;
import org.secondopinion.ssclient.CachedSessionToken;
import org.secondopinion.utils.UserMgmtUtil;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CareTakerServiceApplication.class})
public class CareTakerServiceApplicationTest {

	@Autowired
	private AppProperties appProperties;

	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Before
	public void sessionId() {

		String sessionId;
		try {
			
			sessionId = CachedSessionToken.createSessionId(appProperties.getSsu().getCreateTokenUri(),
					"jitendra@qontrack.com", 
					 SecondOpinionModuleEnum.CARETAKER.name(), 1L, Arrays.asList("CARETAKER"));
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
