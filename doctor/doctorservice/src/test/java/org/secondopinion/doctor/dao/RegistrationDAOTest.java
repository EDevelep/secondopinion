package org.secondopinion.doctor.dao;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import org.junit.Test;
import org.secondopinion.doctor.DoctorServiceApplicationTests;
import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.doctor.dto.Registration;
import org.secondopinion.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationDAOTest extends DoctorServiceApplicationTests{
	
	@Autowired
	private RegistrationDAO registrationDAO;
	
	@Autowired
	private DoctorDAO doctorDAO;
	
	@Test
	public void testActiveRegistration() {
		Doctor doctor = new Doctor();
		doctor.setDoctorId(10L);
		Long doctorIdToActivate = doctor.getDoctorId();
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_doctorId, doctorIdToActivate);
		if (!registration.hasUserVerified()) {
			new IllegalArgumentException("Doctor being activated has not verified his account, cannot be activated");
		}
		
		registration.setNewlyRegistered('N');
		registrationDAO.save(registration);
		assertNotNull(registration);
	}
	
	@Test
	public void testRequestEmailVerificationLink() {
		Registration registration = new Registration();
		registration.setOtp(123);
		Doctor doctor = new Doctor();
		doctor.setEmailId("abc@gmail.com");
		doctor.setDoctorId(12L);
        doctor = doctorDAO.findOneByProperty(Doctor.FIELD_emailId, doctor.getEmailId());
		if(Objects.isNull(doctor)) {
			new IllegalArgumentException("Doctor has not found with the requested email");
		}
		String verificationId = getVerificationId();
		String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
		registration = registrationDAO.findOneByProperty(Registration.FIELD_doctorId, doctor.getDoctorId());
		if(Objects.isNull(registration)) {
			registration = Registration.build(doctor, hashedVerificationId,registration.getOtp());
		}
		registration.setVerificationId(verificationId);
		registration.setEmailVerified('N');
		registration.setEmailVerifiedOn(null);
		registrationDAO.save(registration);
		assertNotNull(registration);
	}
	
	private String getVerificationId() {
		return UUID.randomUUID().toString();
	}
	
	@Test
	public void testVerifyDoctor() {
		Doctor doctor = new Doctor();
		doctor.setDoctorId(11L);
		String verificationId = "def@123";
		String hashedValue = UserHelper.getHashedPassWord(verificationId);
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_doctorId, doctor.getDoctorId());
		if (!UserHelper.checkpw(registration.getVerificationId(), hashedValue)) {
			new IllegalArgumentException("Invalid Verification Id");
		}
		//registration.setDoctorVerified('Y');
		//registration.setDoctorVerifiedOn(new Date());
		registrationDAO.save(registration);
		assertNotNull(registration);
	}
	
	@Test
	public void testApproveDoctor() {
		Doctor doctor = new Doctor();
		doctor.setDoctorId(11L);
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_doctorId, doctor.getDoctorId());
		registration.setDoctorApproved('Y');
		registration.setDoctorApprovedOn(new Date());
		registrationDAO.save(registration);
		assertNotNull(registration);
	}
	
	

}
