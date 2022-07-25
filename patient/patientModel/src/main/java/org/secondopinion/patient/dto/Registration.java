package org.secondopinion.patient.dto;

import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.patient.domain.BaseRegistration;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.ObjectUtil;

@Entity
@Table(name = "registration")
public class Registration extends BaseRegistration {

	public boolean hasUserVerified() {
		return ObjectUtil.isEqual(getEmailVerified(), 'Y') && ObjectUtil.isEqual(getPhoneNumberVerified(), 'Y');
	}

	public static Registration build(User user, String verificationId, Integer otp) {

		Registration registration = new Registration();
		registration.setEmailId(user.getEmailId());
		registration.setUserId(user.getUserId());
		registration.setUserName(user.getUserName());
		registration.setRegisteredDate(new Date());
		registration.setVerificationId(verificationId);
		registration.setActive('N');
		registration.setNewlyRegistered('Y');
		registration.setOtp(otp);
		registration.setVerificationId(verificationId);
		registration.setVerificationNeededBy(DateUtil.addAndGetDate(TimeZone.getDefault(), 2));
		registration.setEmailVerified('N');
		registration.setPhoneNumberVerified('N');

		return registration;
	}

	public void activateRegistration() {
		setPhoneNumberVerified('Y');
		setPhoneNumberVerifiedOn(DateUtil.getDate());
		setActive('Y');
	}

	public void markEmailVerified() {
		setEmailVerified('Y');
		setEmailVerifiedOn(new Date());
	}

	public void resetEmailVerification(String hashedVerificationId) {
		setVerificationId(hashedVerificationId);
		setEmailVerified('N');
		setEmailVerifiedOn(null);
		
	}

	public void markPhoneVerified() {
		setPhoneNumberVerified('Y');
		setPhoneNumberVerifiedOn(DateUtil.getDate());
		setActive('Y');
	}

	public void markAsActivated() {
		 setNewlyRegistered('N');
		 setActive('Y');
	}
}