package org.secondopinion.doctor.service;

import java.util.List;
import java.util.Map;

import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.doctor.dto.DoctorFlagsRequest;
import org.secondopinion.doctor.dto.DoctorFlagsRequest.DoctorFlag;
import org.secondopinion.doctor.dto.ProfileCompletedDTO;
import org.springframework.web.multipart.MultipartFile;

public interface IDoctorRegistrationService {

	void registerDoctor(Doctor doctor);
	ProfileCompletedDTO doctorProfileCompleted(Long doctoId);
	void requestEmailVerificationLink(String emailId);
	boolean oldPaswwordVerification(String emailid, String paswword);
	void requestOTPForPhoneVerification(String phonenumber);
	
	void requestOTPForEmailVerification(String emailid);	
	void resendOTPForEmail(String emailid);

	void forgotPassword(String emailId, String resetPwdLink);

	void resetPassword(String emailId, String newPassword, Integer otp, String type);

	void resendOTPForResetPassword(String emailId, String resetPwdLink);

	Doctor getDoctorById(Long doctorId);

	Boolean verifyEmailIdExists(String emailId);
	boolean verifyPhoneNumberExists(String celnumber);
	boolean userNameExists(String doctorName);
	void deletDoctor(Long doctorId);

	void updateDoctorDetails(Doctor doctor, MultipartFile profilePic);

	void emailVerification(String emailid, Integer emailotp);
	String otpVerificationForPhone( Integer emailotp);
	
	String otpVerificationForEmail( Integer emailotp, String emailId,String type );
	
	Boolean approveDoctor(Long doctorId);

	Map<DoctorFlag, List<Doctor>> fetchDoctorsByFlagWithPagination(DoctorFlagsRequest doctorFlagsRequest);

	void changePassword(String emailId, String newPassword);

	void phoneverification(String phonenumber, Integer otp);
	Doctor getDoctorByEmailId(String emailId);

}
