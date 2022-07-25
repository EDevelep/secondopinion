package org.secondopinion.patient.service;

import java.util.Collection;
import java.util.List;

import org.secondopinion.patient.dto.DoctorDashBoardDTO;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.PrivalgeDTO;
import org.secondopinion.patient.dto.ProfileCompltedDTO;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.patient.dto.StatusType;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.dto.UserResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface IUserRegistrationService {
	void registerUser(User user, MultipartFile profilePic);

//DoctorDashBoardDTO
	boolean userNameExists(String userName);

	boolean userEmailRegistered(String userEmail);

	void activateAssociateUserbyRelationUserId(Long relationUserId, StatusType statusType);

	UserResponseDTO getPrimaryDetails(Long userid, ForUser forUser, RELATIONSHIP_TYPE relationship_TYPE);

	ProfileCompltedDTO userProfileCompleted(Long userid);

	Collection<DoctorDashBoardDTO> getDoctorDashBoardForDoctorIdAndAppointmentFor(Long doctorId, String appointmentFor);

	boolean userphoneNumberRegistered(String cellNumber);

	void emailVerification(String emailid, Integer emailotp, String userName);

	boolean oldPaswwordVerification(String emailid, String paswword, String userName);

	void resetPassword(String emailId, String newPassword, Integer otp, String userName);

	void resendOTPForResetPassword(String emailId, String resetPasswordLink, String userName);

	void resendOTPForEmailId(String emailId, String userName);

	void resendEmailForAssocateUser(String emailId, String userName);

	void forgotPassword(String emailId, String resetPwdLink, String userName);

	boolean activateRegistration(Long userIdToActivate);

	void requestEmailVerificationLink(String emailId);

	void phoneVerification(String cellNumber, Integer otp, String userName, Long forUserId);

	String otpVerificationForPhone(Integer otp);

	String otpVerificationForEmail(Integer otp, String emailId, String userName);

	void saveAssocateUserStatus(String emailid, StatusType statusType);

	void requestOTPForPhoneVerification(String phonenumber, String username);

	void requestOTPForEamilVerification(String emailId, String username);

	void deleteUser(Long userId);

	UserResponseDTO getUserByEmail(String userEmail, Long userId, String userName);

	UserResponseDTO getUserDetailsByEmail(String userEmail, String userName);

	List<User> getUserAllUser();

	PrivalgeDTO associateUserPrivalge(Long relationshipId);

	void associateUserPrivalgeUpdate(PrivalgeDTO privalgeDTO);

}
