package org.secondopinion.caretaker.dto; 


import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Entity; 
import javax.persistence.Table;


import org.secondopinion.utils.DateUtil;
import org.secondopinioncaretaker.domain.BaseRegistration; 




@SuppressWarnings({ "serial"})
@Entity 
@Table (name="registration")
public class Registration extends BaseRegistration{

	public static Registration build(Caretaker caretaker, String hashedVerificationId) {
		Registration registration = new Registration();
		registration.setCaretakerId(caretaker.getCaretakerId());
		registration.setEmailId(caretaker.getEmailId());
		registration.setCaretakerName(caretaker.getFirstName());
		registration.setRegisteredDate(new Date());
		registration.setVerificationId(hashedVerificationId);
		registration.setActive('N');
		registration.setDoctorApproved('N');
		registration.setDoctorApprovedOn((DateUtil.addAndGetDate(TimeZone.getDefault(), 2)));
		registration.setDoctorVerified('N');
		registration.setDoctorVerifiedOn(DateUtil.addAndGetDate(TimeZone.getDefault(), 2));
		registration.setNewlyRegistered('Y');
		registration.setVerificationId(hashedVerificationId);
		registration.setVerificationNeededBy(DateUtil.addAndGetDate(TimeZone.getDefault(), 2));
		registration.setEmailVerified('N');
		registration.setPhoneNumberVerified('N');
		return registration;
	}

	public void markEmailVerified() {
		setEmailVerified('Y');
		setEmailVerifiedOn(new Date());
		setOtpVerifiedOn(DateUtil.getDate());
		
	}
	public void markEmailNotVerified() {
		setEmailVerified('N');
		setEmailVerifiedOn(new Date());
		setOtpVerifiedOn(DateUtil.getDate());
		
	}

	public void markPhoneVerified() {
		setPhoneNumberVerified('Y');
		setPhoneNumberVerifiedOn(DateUtil.getDate());
		setActive('Y');
		
	}
	public void marisActive() {
		setPhoneNumberVerifiedOn(DateUtil.getDate());
		setActive('Y');
		
	}
	public void markisInActive() {
		setActive('N');
		
	}
}