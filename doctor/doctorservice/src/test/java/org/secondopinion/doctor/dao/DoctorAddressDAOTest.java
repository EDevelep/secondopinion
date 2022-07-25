package org.secondopinion.doctor.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.secondopinion.doctor.DoctorServiceApplicationTests;
import org.secondopinion.doctor.dto.DoctorAddress;
import org.springframework.beans.factory.annotation.Autowired;

public class DoctorAddressDAOTest extends DoctorServiceApplicationTests{
	
	@Autowired
	private DoctorAddressDAO doctorAddressDAO;
	
	@Test
	public void testSaveDoctorAddress() {
		DoctorAddress doctorAddress = new DoctorAddress();
    	doctorAddress.setAudit("audit");
    	doctorAddress.setCity("hyd");
    	doctorAddress.setCountry("Ind");
    	doctorAddressDAO.save(doctorAddress);
    	assertNotNull(doctorAddress);
	}
	
	@Test
	public void testGetDoctorAddressByDoctorId() {
		DoctorAddress doctorAddress = new DoctorAddress();
    	doctorAddress.setDoctorId(15L);
    	Long doctorId = doctorAddress.getDoctorId();
    	doctorAddressDAO.findOneByProperty(DoctorAddress.FIELD_doctorId, doctorId);
    }
	
	
	
	
	
	

}
