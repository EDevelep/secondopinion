package org.secondopinion.patient.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.secondopinion.patient.dto.Address;
import org.secondopinion.patient.dto.CaretakerResponseDTO;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.Medicalinsurance;
import org.secondopinion.patient.dto.PatientFlagsRequest;
import org.secondopinion.patient.dto.PatientFlagsRequest.PatientFlag;
import org.secondopinion.patient.dto.Patientfcmtoken;
import org.secondopinion.patient.dto.Personaldetail;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.RelationShipDto;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.dto.UserRelations;
import org.secondopinion.patient.dto.UserResponseDTO;
import org.secondopinion.patient.dto.Userreference;
import org.secondopinion.patient.dto.VitalSearch;
import org.secondopinion.patient.dto.Vitals;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {

	UserResponseDTO getUser(String userName, String password);

	void saveUserPrefrence(Userreference userreference);

	List<Userreference> getallUserreference(Long userid, String referencetype);

	User patientRecordForPharmacy(ForUser forUser,RELATIONSHIP_TYPE relationship_TYPE);

	void updateUserPassword(String emailId, String newPassword);

	void updateUser(User user, MultipartFile profilePic, ForUser foruser,RELATIONSHIP_TYPE relationship_TYPE);

	void updateUserPrimarydetails(User user,ForUser foruser,RELATIONSHIP_TYPE relationship_TYPE);

	void addAddress(ForUser forUser, Address address,RELATIONSHIP_TYPE relationship_TYPE);

	List<Address> getAddress(ForUser forUser,RELATIONSHIP_TYPE relationship_TYPE);

	void updatePersonaldetail(ForUser forUser, Personaldetail personaldetail,RELATIONSHIP_TYPE relationship_TYPE);

	void addPersonaldetail(ForUser forUser, Personaldetail personaldetail,RELATIONSHIP_TYPE relationship_TYPE);

	void uploadProfilePic(Long userId, MultipartFile profilePic);

	Personaldetail getPersonaldetail(ForUser foruser,RELATIONSHIP_TYPE relationship_TYPE);

	void deletePersonaldetail(Long personaldetailId);

	void saveFcmTokenForpatient(Patientfcmtoken patientfcmtoken);

	void updtaeFcmTokenForpatient(Long patientId, Patientfcmtoken patientfcmtoken);

	List<Patientfcmtoken> getFcmtokenForpatient(Long patientid);

	void updateAddress(ForUser forUser, Address address,RELATIONSHIP_TYPE relationship_TYPE);

	List<User> getNewUsers();

	void associateUser(Long userId, Long associateWithUserId, RELATIONSHIP_TYPE relationshipType);

	void updateUserRelations(Long userId, List<Relationship> userRelations);

	List<Relationship> getUserRelations(ForUser forUser,RELATIONSHIP_TYPE relationship_TYPE);

	List<Vitals> getUserVitals(ForUser forUser,RELATIONSHIP_TYPE relationship_TYPE);

	List<Vitals> getUserVitalsByDay(ForUser forUser, Date date,RELATIONSHIP_TYPE relationship_TYPE);

	void addVitals(ForUser forUser, List<Vitals> vitals,RELATIONSHIP_TYPE relationship_TYPE);

	List<Vitals> searchVitals(VitalSearch vitalSearch);

	List<Medicalinsurance> getMedicalInsuranceDtailsForUser(ForUser forUser,RELATIONSHIP_TYPE relationship_TYPE);

	void addMedicalInsuranceDetails(ForUser forUser, Medicalinsurance insurance,RELATIONSHIP_TYPE relationship_TYPE);

	void updateMedicalInsuranceDetails(ForUser forUser, Medicalinsurance insurance,RELATIONSHIP_TYPE relationship_TYPE);

	List<Object[]> getNewRelationhipRequests(Long userId);

	void verifyUser(Long userId, Long userIdToApprove, String verificationId, RELATIONSHIP_TYPE relationshipType);

	User findUserById(Long id);

	Map<PatientFlag, List<User>> fetchPatientsByFlagWithPagination(PatientFlagsRequest doctorFlagsRequest);

	void deleteAddress(Long addressId);

	void deleteMedicalinsurance(Long userId);

	void getNewRelationshipsUserIds(Long id);

	User patientSummeryRecord(ForUser forUser,RELATIONSHIP_TYPE relationship_TYPE);

	UserRelations allAssociateUser(Long userIds);

	List<User> getallAssociateUser(Long userId, Integer pageNum, Integer maxResults);

	HashMap<Long, String> getRelationshipsUserIds(Long userid);

	void saveUserPrefrenceDiagnosticcenter(Userreference userreference);

	List<Userreference> getallUserreferenceDiagnosticcenter(Long userid, String referencetype);

	List<Vitals> getUserLatestVitals(ForUser forUser,RELATIONSHIP_TYPE relationship_TYPE);

	void deleteUserRefrence(Long userreferenceId);

	CaretakerResponseDTO patientRecordForCaretaker(Long caretakerId);

	void updateVitals(ForUser forUser, List<Vitals> vitals, RELATIONSHIP_TYPE relationship_TYPE);
}
