package org.secondopinion.diagnosticcenter.dto;

import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.secondopinion.diagnosticcenter.domain.BaseRegistration;
import org.secondopinion.utils.DateUtil;

@SuppressWarnings({ "serial" })
@Entity
@Table(name = "registration")
public class Registration extends BaseRegistration {

	public static Registration build(Diagnosticcenter diagnosticcenter, String verificationId, Integer otp) {

		Registration registration = new Registration();
		registration.setDiagnosticcenterId(diagnosticcenter.getDiagnosticcenterId());
		registration.setEmailId(diagnosticcenter.getPrimaryUserEmailId());
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

	public static Registration build(Diagnosticcenteruser diagnosticcenter, String  verificationId, Long diagnosticcenterid) {

		Registration registration = new Registration();
		registration.setDiagnosticcenterId(diagnosticcenterid);
		registration.setEmailId(diagnosticcenter.getEmailId());
		registration.setRegisteredDate(new Date());
		registration.setActive('N');
		registration.setNewlyRegistered('Y');
		registration.setVerificationId(verificationId);
		registration.setVerificationNeededBy(DateUtil.addAndGetDate(TimeZone.getDefault(), 2));
		registration.setEmailVerified('N');
		registration.setPhoneNumberVerified('N');
		return registration;
	}

	public static Registration build(Diagnosticcenteruser diagnosticcenteruser, Long diagnosticCenterUserId,
			Long diagnosticcenterId) {
		
		Registration registration = new Registration();
		registration.setDiagnosticcenterId(diagnosticcenterId);
		registration.setEmailId(diagnosticcenteruser.getEmailId());
		registration.setRegisteredDate(new Date());
		registration.setActive('N');
		registration.setNewlyRegistered('Y');
		registration.setVerificationId(UUID.randomUUID().toString());
		registration.setVerificationNeededBy(DateUtil.addAndGetDate(TimeZone.getDefault(), 2));
		registration.setEmailVerified('N');
		registration.setPhoneNumberVerified('N');
		return registration;
	}
}