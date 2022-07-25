package org.secondopinion.caretaker.service;

import static org.junit.Assert.assertNotNull;


import org.junit.Test;

import org.secondopinion.caretaker.CareTakerServiceApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class CareTakerServiceImplTest extends CareTakerServiceApplicationTest {

	@Autowired
	private CareTakerService careTakerService;
	
	@Test
	public void getcaretaker() {
		careTakerService.resendOTPForEmail("jitendra@qontrack.com");
	}
}
