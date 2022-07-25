package org.secondopinion.doctor.dto; 


import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.doctor.domain.BaseRegistration;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.ObjectUtil; 



@Entity 
@Table (name="registration")
public class Registration extends BaseRegistration{

	public boolean hasUserVerified() {
		return ObjectUtil.isEqual(getEmailVerified(), 'Y') && ObjectUtil.isEqual(getPhoneNumberVerified(), 'Y');
	}
	
	public static Registration build(Doctor doctor, String verificationId, Integer otp) {
		
		Registration registration = new Registration();
		registration.setDoctorId(doctor.getDoctorId());
		registration.setEmailId(doctor.getEmailId());
		registration.setRegisteredDate(new Date());
		registration.setNewlyRegistered('Y');
		registration.setActive('N');
		registration.setEmailVerified('N');
		registration.setVerificationId(verificationId);
		registration.setOtp(otp);
		registration.setPhoneNumberVerified('N');
		registration.setDoctorApproved('N');
		registration.setVerificationNeededBy(DateUtil.addAndGetDate( TimeZone.getDefault(), 2));
		
		return registration;
	}
}