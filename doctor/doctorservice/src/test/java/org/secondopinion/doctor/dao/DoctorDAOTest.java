package org.secondopinion.doctor.dao;

import org.junit.Test;
import org.secondopinion.doctor.DoctorServiceApplicationTests;
import org.secondopinion.doctor.dto.Doctor;
import org.springframework.beans.factory.annotation.Autowired;

public class DoctorDAOTest extends DoctorServiceApplicationTests{
	
	@Autowired 
	private DoctorDAO doctorDao;
	
	@Test
	public void testRegisterDoctor() {
		Doctor doctor = new Doctor();
		//doctor.set
		//Doctor doctorByEmail = doctorDao.findOneByProperty(Doctor.FIELD_emailId, doctor.getEmailId());
	}

}
