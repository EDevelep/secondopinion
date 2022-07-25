package org.secondopinion.caretaker.service;

import java.util.Collection;
import java.util.List;

import org.secondopinion.caretaker.dto.Address;
import org.secondopinion.caretaker.dto.CareTakerSearchRequest;
import org.secondopinion.caretaker.dto.Caretaker;
import org.secondopinion.caretaker.dto.Certification;
import org.secondopinion.caretaker.dto.Feedetails;
import org.secondopinion.caretaker.dto.Personaldetail;
import org.secondopinion.caretaker.dto.ProfileCompletedDTO;

import javassist.tools.framedump;

public interface CareTakerService {
	void registerCareTaker(Caretaker caretaker);

	ProfileCompletedDTO isProfileCompleted(Long caretakerId );

	boolean iscareTakerNameExit(String caretakenName);
	public void emailVerification(String emailid, Integer emailotp);

	Collection<Feedetails> getCareTakerFeeDetails(Long caretakerId);

	String emailOtpVerification(String emailid, Integer emailotp);

	public void phoneVerification(String cellNumber, Integer otp);

	public void resendOTPForEmail(String emailid);

	public Boolean verifyEmailIdExists(String emailId);

	public void resetPassword(String emailId, String newPassword, Integer otp);

	public void resendOTPForPhone(String phonenumber);

	public void saveAddress(Address address);

	public void savePersonaldetail(Personaldetail personaldetail);

	Address getAddress(Long caretakerId);

	Personaldetail getPersonaldetail(Long caretakerId);

	Collection<Address> findCaretakerAddressBycaretakerId(Long caretakerId);

	void updateCaretaker(Long caretakerId, Caretaker caretaker);

	Caretaker getCaretaker(Long caretakerId);

	List<Caretaker> findAllCaretaker(CareTakerSearchRequest careTakerSearchRequest);

	Feedetails getFeeDetailsById(Long feedetailsId);

	void deleteCaretakerFeeDetails(Long feeDetailsId);

	void saveCertification(Certification certification);

	Certification getCertificationById(Long certificationId);

	Collection<Certification> getCaretakerCertifications(Long caretakerId);

	void deleteCaretakerCertication(Long certificationId);

	void saveFeeDetail(Feedetails feedetails);
	
	public String verifyOldPassword(String emailId,String password);

	Caretaker getCaretakerDetails(String emailId);

	Collection<Caretaker> findAllCaretaker();

	
}
