package org.secondopinion.pharmacy.dto; 


import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table;


import org.secondopinion.pharmacy.domain.BaseRegistration;
import org.secondopinion.utils.DateUtil; 





@SuppressWarnings({ "serial"})
@Entity 
@Table (name="registration")
public class Registration extends BaseRegistration{

	public static Registration build(Pharmacy pharmacy, String verificationId, Integer otp) {

		Registration registration = new Registration();
		registration.setPharmacyId((pharmacy.getPharmacyId()));
		registration.setRegisteredDate(new Date());
		registration.setEmailId(pharmacy.getEmailId());
		registration.setVerificationId(verificationId);
		registration.setActive('N');
		registration.setNewlyRegistered('Y');
		registration.setOtp(otp);
		registration.setVerificationId(verificationId);
		registration.setVerificationNeededBy(DateUtil.addAndGetDate( TimeZone.getDefault(), 2));
		registration.setEmailVerified('N');
		registration.setPhoneNumberVerified('N');

		return registration;
	}
	public static Registration build(Pharmacyuser pharmacyuser, String verificationId, Long pharmacyId) {

		Registration registration = new Registration();
		registration.setPharmacyId((pharmacyId));
		//registration.p(pharmacyuser.getPharmacyName());
		registration.setRegisteredDate(new Date());
		registration.setEmailId(pharmacyuser.getEmailId());
		registration.setVerificationId(verificationId);
		registration.setActive('N');
		registration.setNewlyRegistered('Y');
		registration.setVerificationId(verificationId);
		registration.setVerificationNeededBy(DateUtil.addAndGetDate( TimeZone.getDefault(), 2));
		registration.setEmailVerified('N');
		registration.setPhoneNumberVerified('N');

		return registration;
	}
	
	public static Registration buildPharmacyuser(Pharmacyuser pharmacyuser, Long pharmacyuserId, Long pharmacyId) {

		Registration registration = new Registration();
		registration.setPharmacyId((pharmacyId));
		//registration.p(pharmacyuser.getPharmacyName());
		registration.setRegisteredDate(new Date());
		registration.setEmailId(pharmacyuser.getEmailId());
	
		registration.setActive('N');
		registration.setNewlyRegistered('Y');
		registration.setPharmacyUserId(pharmacyuserId);
		registration.setVerificationNeededBy(DateUtil.addAndGetDate( TimeZone.getDefault(), 2));
		registration.setEmailVerified('N');
		registration.setPhoneNumberVerified('N');

		return registration;
	}
}