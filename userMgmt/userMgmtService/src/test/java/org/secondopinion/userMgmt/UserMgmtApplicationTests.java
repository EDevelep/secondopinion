package org.secondopinion.userMgmt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.secondopinion.userMgmt.UserMgmtApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.secondopinion.configurations.AppProperties;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserMgmtApplication.class})
public class UserMgmtApplicationTests {
	
	@Autowired
	private AppProperties appProperties;
	
	@Test
	public void contextLoads() {
		System.out.println(appProperties);
	}

}
