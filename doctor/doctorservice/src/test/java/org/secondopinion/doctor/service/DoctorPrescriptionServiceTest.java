package org.secondopinion.doctor.service;


import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.secondopinion.doctor.DoctorServiceApplicationTests;
import org.secondopinion.doctor.dto.Medicalprescription;
import org.springframework.beans.factory.annotation.Autowired;

public class DoctorPrescriptionServiceTest  extends DoctorServiceApplicationTests {

	@Autowired
	private IDoctorPrescriptionService doctorPrescriptionService;

	
}
