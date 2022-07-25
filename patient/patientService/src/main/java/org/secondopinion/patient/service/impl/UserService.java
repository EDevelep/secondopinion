package org.secondopinion.patient.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.secondopinion.patient.dao.AddressDAO;
import org.secondopinion.patient.dao.AilmentsDAO;
import org.secondopinion.patient.dao.AppointmentDAO;
import org.secondopinion.patient.dao.MedicalinsuranceDAO;
import org.secondopinion.patient.dao.MedicalprescriptionDAO;
import org.secondopinion.patient.dao.MedicalreportsDAO;
import org.secondopinion.patient.dao.MedicationDAO;
import org.secondopinion.patient.dao.PatientfcmtokenDAO;
import org.secondopinion.patient.dao.PersonaldetailDAO;
import org.secondopinion.patient.dao.RegistrationDAO;
import org.secondopinion.patient.dao.RelationshipDAO;
import org.secondopinion.patient.dao.UserDAO;
import org.secondopinion.patient.dao.UserreferenceDAO;
import org.secondopinion.patient.dao.VitalsDAO;
import org.secondopinion.patient.domain.BaseAilments;
import org.secondopinion.patient.domain.BaseAppointment;
import org.secondopinion.patient.domain.BasePersonaldetail;
import org.secondopinion.patient.domain.BaseRegistration;
import org.secondopinion.patient.domain.BaseRelationship;
import org.secondopinion.patient.domain.BaseUser;
import org.secondopinion.patient.dto.ACCESS_TYPE;
import org.secondopinion.patient.dto.Address;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.patient.dto.CaretakerResponseDTO;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.Medicalinsurance;
import org.secondopinion.patient.dto.Medicalprescription;
import org.secondopinion.patient.dto.Medicalreports;
import org.secondopinion.patient.dto.Medication;
import org.secondopinion.patient.dto.PatientFlagsRequest;
import org.secondopinion.patient.dto.PatientFlagsRequest.PatientFlag;
import org.secondopinion.patient.dto.Patientfcmtoken;
import org.secondopinion.patient.dto.Personaldetail;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.Registration;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.dto.UserRelations;
import org.secondopinion.patient.dto.UserResponseDTO;
import org.secondopinion.patient.dto.Userreference;
import org.secondopinion.patient.dto.VitalSearch;
import org.secondopinion.patient.dto.Vitals;
import org.secondopinion.patient.service.IUserService;
import org.secondopinion.patient.service.rest.PatientElasticSerchRestAPIService;
import org.secondopinion.request.Response;
import org.secondopinion.utils.MailProperties;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.UserHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService implements IUserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserreferenceDAO userreferenceDAO;
	@Autowired
	private RegistrationDAO registrationDAO;

	@Autowired
	private RelationshipDAO relationshipDAO;
	@Autowired
	private PersonaldetailDAO personaldetailDAO;

	@Autowired
	private VitalsDAO vitalsDAO;

	@Value("${loginLinkForUI}")
	private String loginLinkForUI;

	/*
	 * @Value("${activeAccosateuserLink}") private String activeAccosateuserLink;
	 */
	@Autowired
	private MedicalinsuranceDAO medicalinsuranceDAO;

	@Autowired
	private AddressDAO addressDAO;

	@Autowired
	private MedicalprescriptionDAO medicalprescriptiondao;

	@Autowired
	private MailProperties mailProperties;

	@Autowired
	private AilmentsDAO ailmentsDAO;

	@Autowired
	private MedicalreportsDAO medicalreportsDAO;

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Autowired
	private PatientfcmtokenDAO patientfcmtokenDAO;
	@Autowired
	private MedicationDAO medicationDAO;

	@Autowired
	private PatientElasticSerchRestAPIService patientElasticSerchRestAPIService;

	@Value("${emailVerificationLink}")
	private String emailVerificationLink;

	private static final String supportMailRequest = "Contact support@qontrack.com for further assistance.";

	@Override
	public void updateUserPassword(String emailId, String newPassword) {
		User user = userDAO.findOneByProperty(BaseUser.FIELD_emailId, emailId);

		if (Objects.isNull(user)) {
			throw new IllegalArgumentException("This EmailID is invalid");
		}

		user.setPassword(UserHelper.getHashedPassWord(newPassword));

		userDAO.save(user);

	}

	@Override
	@Transactional
	public void updateUser(User user, MultipartFile profilePic, ForUser foruser, RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = getAssociateUserIdFromForUser(foruser, ACCESS_TYPE.PERSONAL_DETAILS, relationship_TYPE);
		User dbuser = findUserById(userId);

		if (Objects.isNull(dbuser)) {
			throw new IllegalArgumentException("The User has not been registered");
		}
		// validating username
		boolean isUserNameExistsForOtherUser = userDAO.getByIdNotAndUsernameEquals(user.getUserId(), user.getEmailId());
		if (isUserNameExistsForOtherUser) {
			throw new IllegalArgumentException("The User Name [" + user.getEmailId() + "] is already registered.");
		}

		user.setPassword(dbuser.getPassword());

		dbuser = User.buildForUpdate(dbuser, user);
		saveOrUpdateUser(dbuser, profilePic);

	}

	private void saveOrUpdateUser(User user, MultipartFile profilePic) {

		userDAO.save(user);
		Long userId = user.getUserId();

		Personaldetail pd = user.getPersonaldetail();
		if (Objects.nonNull(pd)) {
			byte[] profilePicBlob = null;
			if (profilePic != null) {
				try {
					profilePicBlob = profilePic.getBytes();
				} catch (IOException e) {
					throw new IllegalArgumentException("Invalid Profile pic.");
				}
			}
			if (Objects.isNull(profilePic) && Objects.isNull(pd)) {
				pd = new Personaldetail();
			}
			pd.setUserId(userId);
			pd.setProfilePic(profilePicBlob);
			pd.setActive('Y');
			personaldetailDAO.save(pd);
		}

	}

	@Override
	public List<User> getNewUsers() {
		return userDAO.findByProperty(BaseUser.FIELD_active, 'N');
	}

	public MailProperties getMailProperties(User associateWithUser) {
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(associateWithUser.getEmailId());
		return properties;
	}

	@Override
	public List<Object[]> getNewRelationhipRequests(Long userId) {
		List<Long> relationships = relationshipDAO.getNewRelationshipsUserIds(userId);

		return CollectionUtils.isEmpty(relationships) ? userDAO.getUsersForWithRelationship(relationships) : null;

	}

	@Override
	@Transactional
	public void verifyUser(Long userId, Long userIdToApprove, String verificationId,
			RELATIONSHIP_TYPE relationshipType) {

		Relationship relationhip = relationshipDAO.checkRelationshipExists(userId, userIdToApprove, relationshipType);

		Relationship relationhipToBeApproved = relationshipDAO.checkRelationshipExists(userIdToApprove, userId,
				relationshipType);

		if (!StringUtil.equalsIgnoreCase(verificationId, relationhip.getVerificationId())) {
			throw new IllegalArgumentException("Error verifying - invalid verification");
		}

		relationhip.setApproved('Y');
		relationhip.setApprovedOn(new Date());

		relationhipToBeApproved.setVerified('Y');
		relationhipToBeApproved.setVerifiedOn(new Date());

		List<Relationship> relationships = new ArrayList<>();
		relationships.add(relationhipToBeApproved);
		relationships.add(relationhip);

		relationshipDAO.save(relationships);
	}

	@Override
	@Transactional(readOnly = true)
	public void updateUserRelations(Long userId, List<Relationship> userRelations) {

		if (CollectionUtils.isEmpty(userRelations)) {
			return;
		}

		userRelations.stream().forEach(n -> n.setUserId(userId));

		relationshipDAO.save(userRelations);
	}

	@Override
	@Transactional
	public UserResponseDTO getUser(String email, String password) {
		User user = null;
		user = userDAO.findUserByUserNameAndEmailId(email);

		if (Objects.isNull(user)) {
			user = userDAO.findUserByUserNameAndEmailIdDependent(email);
		}

		if (Objects.isNull(user)) {
			throw new IllegalArgumentException("Invalid User Id And password ");

		}
		if (user.getLocked() == 'Y') {
			throw new IllegalArgumentException(
					"Your UserID has been locked out due to 3 consecutive incorrect attempts. " + supportMailRequest);
		}

		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_userId, user.getUserId());
		if (Objects.isNull(registration) || Objects.isNull(registration.getActive()) || registration.getActive() == 'N'
				|| user.getActive() == 'N') {
			throw new IllegalArgumentException("Your Email Id Is  Unverified.");
		}

		String dbPassword = user.getPassword();
		if (!UserHelper.checkpw(password, dbPassword)) {
			updateRetryCountIfLoginFailed(user.getUserId(), user.getRetry());
			throw new IllegalArgumentException("Invalid UserID or Password.");

		}

		updateLastLoginTime(user.getUserId());
		UserResponseDTO userResponseDTO = UserResponseDTO.builidUserObject(user);
		return userResponseDTO;
	}

	private void updateRetryCountIfLoginFailed(Long userId, Integer retry) {
		userDAO.updateRetryCountIfLoginFailed(userId, retry);

	}

	@Override
	public List<Relationship> getUserRelations(ForUser forUser, RELATIONSHIP_TYPE relationship_TYPE) {
		return relationshipDAO.findByProperty(BaseRelationship.FIELD_userId, forUser.getUserId());
	}

	@Override
	@Transactional
	public List<Vitals> getUserVitals(ForUser forUser, RELATIONSHIP_TYPE relationship_TYPE) {

		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.VITALS, relationship_TYPE);
		return vitalsDAO.getUserVitalsByuserId(userId);

	}

	@SuppressWarnings("unused")
	@Override
	@Transactional
	public void addVitals(ForUser forUser, List<Vitals> vitals, RELATIONSHIP_TYPE relationship_TYPE) {
		Date recordedDate = new Date();

		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.VITALS, relationship_TYPE);

		vitals.stream().forEach(n -> {
			n.setActive('Y');
			n.setRecordedDate(recordedDate);
		});
		vitalsDAO.save(vitals);
	}

	@SuppressWarnings("unused")
	@Override
	@Transactional
	public void updateVitals(ForUser forUser, List<Vitals> vitals, RELATIONSHIP_TYPE relationship_TYPE) {
		Date recordedDate = new Date();
		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.VITALS, relationship_TYPE);
		List<Long> vitalsId = vitals.stream().map(Vitals::getVitalsId).collect(Collectors.toList());
		if (Objects.isNull(vitalsId)) {
			throw new IllegalArgumentException("VitalsId Can Not We Null For Update");
		}
		List<Vitals> dbList = vitalsDAO.getVitalsByvitalsId(vitalsId);
		if (Objects.isNull(dbList)) {
			throw new IllegalArgumentException("Vitals Can Not We Null For Update");
		}

		vitals.stream().forEach(n -> {
			n.setActive('Y');
			n.setRecordedDate(recordedDate);
		});
		vitalsDAO.save(vitals);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vitals> searchVitals(VitalSearch vitalSearch) {
		return vitalsDAO.search(vitalSearch);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Medicalinsurance> getMedicalInsuranceDtailsForUser(ForUser forUser,
			RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.INSURANCE_DETAILS, relationship_TYPE);
		return medicalinsuranceDAO.getMedicalInsuranceDtailsForUser(userId);
	}

	@Override
	@Transactional
	public void addMedicalInsuranceDetails(ForUser forUser, Medicalinsurance insurance,
			RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.INSURANCE_DETAILS, relationship_TYPE);
		insurance.setUserId(userId);
		insurance.setActive('Y');
		medicalinsuranceDAO.save(insurance);

	}

	@Override
	@Transactional(readOnly = true)
	public void updateMedicalInsuranceDetails(ForUser forUser, Medicalinsurance insurance,
			RELATIONSHIP_TYPE relationship_TYPE) {
		Medicalinsurance medicalinsurance = medicalinsuranceDAO.findById(insurance.getMedicalInsuranceId());
		if (Objects.isNull(medicalinsurance)) {
			medicalinsuranceDAO.save(insurance);
		}
		insurance.setActive('Y');
		medicalinsuranceDAO.save(insurance);
	}

	@Override
	@Transactional
	public void addAddress(ForUser forUser, Address address, RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.PERSONAL_DETAILS, relationship_TYPE);
		address.setUserId(userId);
		address.setActive('Y');
		addressDAO.save(address);
		// patientElasticSerchRestAPIService.patientElasticSerchSave(address);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Address> getAddress(ForUser forUser, RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.PERSONAL_DETAILS, relationship_TYPE);
		return addressDAO.getAddresByUserId(userId);

	}

	@Override
	@Transactional(readOnly = true)
	public void updateAddress(ForUser forUser, Address address, RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.PERSONAL_DETAILS, relationship_TYPE);
		Address dbaddress = addressDAO.getAddresByaddressId(address.getAddressId());

		if (Objects.isNull(dbaddress)) {
			throw new IllegalArgumentException("Object Sholud Not Be Null");
		} else {
			address.setUserId(userId);
			address.setActive('Y');
			addressDAO.save(address);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<Vitals> getUserVitalsByDay(ForUser forUser, Date date, RELATIONSHIP_TYPE relationship_TYPE) {

		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.VITALS, relationship_TYPE);
		return vitalsDAO.getUserVitalsByDay(userId, date);
	}

	@Override
	@Transactional
	public UserRelations allAssociateUser(Long userIds) {
		UserRelations relations = new UserRelations();
		relations.setAssociatedUsers(relationshipDAO.allAssociateUser(userIds));
		relations.setReverseAssociatedUsers(relationshipDAO.allReverseAssociateUsers(userIds));

		return relations;

	}

	@SuppressWarnings("unused")
	private String getPassword() {
		return UUID.randomUUID().toString();
	}

	@Override
	@Transactional
	public void updatePersonaldetail(ForUser forUser, Personaldetail personaldetail,
			RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.PERSONAL_DETAILS, relationship_TYPE);

		Personaldetail dbPersonaldetail = personaldetailDAO
				.findPersonalDetailById(personaldetail.getPersonalDetailId());

		if (Objects.isNull(dbPersonaldetail)) {
			throw new RuntimeException("Object is not Found For given Id");
		}
		personaldetail.setUserId(userId);
		personaldetail.setActive('Y');
		personaldetailDAO.save(personaldetail);
	}

	@Override
	@Transactional
	public void addPersonaldetail(ForUser foruser, Personaldetail personaldetail, RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = getAssociateUserIdFromForUser(foruser, ACCESS_TYPE.PERSONAL_DETAILS, relationship_TYPE);

		personaldetail.setActive('Y');
		personaldetail.setUserId(userId);
		personaldetailDAO.save(personaldetail);

	}

	@Override
	@Transactional
	public Personaldetail getPersonaldetail(ForUser foruser, RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = getAssociateUserIdFromForUser(foruser, ACCESS_TYPE.PERSONAL_DETAILS, relationship_TYPE);
		Personaldetail personaldetail = personaldetailDAO.findPersonaldetailByUserId(userId);

		if (Objects.isNull(personaldetail)) {
			LOGGER.error("Personaldetails of user is not found");
			return null;
		}

		User user = userDAO.findUserByUserId(userId);
		// set the value
		if (Objects.isNull(user)) {
			LOGGER.error("User is not found");
			throw new IllegalArgumentException("User not found.");
		} else {
			personaldetail.setPatientName(user.getFullName());
			personaldetail.setEmailId(user.getEmailId());
			personaldetail.setCellNumber(user.getCellNumber());

		}

		if (Objects.nonNull(personaldetail) && Objects.nonNull(personaldetail.getProfilePic())) {
			try {
				personaldetail.setProfilePic(personaldetail.getProfilePic());
			} catch (Exception e) {
				LOGGER.error("unable decompress the image.");
			}
		}
		return personaldetail;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public RelationshipDAO getRelationshipDAO() {
		return relationshipDAO;
	}

	public void setRelationshipDAO(RelationshipDAO relationshipDAO) {
		this.relationshipDAO = relationshipDAO;
	}

	public VitalsDAO getVitalsDAO() {
		return vitalsDAO;
	}

	public void setVitalsDAO(VitalsDAO vitalsDAO) {
		this.vitalsDAO = vitalsDAO;
	}

	public MailProperties getMailProperties() {
		return mailProperties;
	}

	public void setMailProperties(MailProperties mailProperties) {
		this.mailProperties = mailProperties;
	}

	public MedicalinsuranceDAO getMedicalinsuranceDAO() {
		return medicalinsuranceDAO;
	}

	public void setMedicalinsuranceDAO(MedicalinsuranceDAO medicalinsuranceDAO) {
		this.medicalinsuranceDAO = medicalinsuranceDAO;
	}

	public AddressDAO getAddressDAO() {
		return addressDAO;
	}

	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}

	public void updateLastLoginTime(Long userId) {
		userDAO.updateLastLoginTime(userId);
	}

	@Override
	@Transactional
	public User findUserById(Long id) {
		return userDAO.findUserByUserId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Map<PatientFlag, List<User>> fetchPatientsByFlagWithPagination(PatientFlagsRequest patientFlagsRequest) {
		Map<PatientFlag, List<User>> map = new HashMap<>();
		Set<PatientFlag> patientFlags = patientFlagsRequest.getPatientFlags();
		if (CollectionUtils.isEmpty(patientFlags)) {
			return map;
		}
		for (PatientFlag patientFlag : patientFlags) {
			map.put(patientFlag, registrationDAO.fetchDoctorsByFlagWithPagination(patientFlag,
					patientFlagsRequest.getSortMap(), patientFlagsRequest.getPageNo(), patientFlagsRequest.getLimit()));
		}
		return map;
	}

	@Override
	@Transactional
	public void deleteAddress(Long addressId) {
		Address address = addressDAO.getAddresByaddressId(addressId);
		if (Objects.isNull(address)) {
			throw new IllegalArgumentException("Address not found.");

		}
		address.setActive('N');
		addressDAO.save(address);
	}

	@Override
	@Transactional
	public void deletePersonaldetail(Long personaldetailId) {

		Personaldetail personaldetail = personaldetailDAO.findPersonalDetailById(personaldetailId);
		if (Objects.isNull(personaldetail)) {
			throw new IllegalArgumentException("Personaldetail not found.");

		}
		personaldetail.setActive('Y');
		personaldetailDAO.save(personaldetail);

	}

	@Override
	@Transactional
	public User patientSummeryRecord(ForUser forUser, RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.PERSONAL_DETAILS, relationship_TYPE);
		User user = userDAO.findOneByProperty(BaseUser.FIELD_userId, userId);

		if (Objects.isNull(user)) {
			throw new IllegalArgumentException("User is Not Found Of Given UserID ");
		}

		return buildUserrObjectWithChildObjects(user);
	}

	private User buildUserrObjectWithChildObjects(User user) {

		user.setVitals(vitalsDAO.getUserVitalsByuserId(user.getUserId()));
		user.setMedicalreports(medicalreportsDAO.getmedicalreportsByuserId(user.getUserId()));
		user.setAilments(ailmentsDAO.findByProperty(BaseAilments.FIELD_userId, user.getUserId()));
		user.setAppointment(appointmentDAO.findByProperty(BaseAppointment.FIELD_userId, user.getUserId()));
		user.setPersonaldetail(personaldetailDAO.findPersonaldetailByUserId(user.getUserId()));
		return user;
	}

	@Override
	@Transactional
	public User patientRecordForPharmacy(ForUser forUser, RELATIONSHIP_TYPE relationship_TYPE) {
		User user = userDAO.findUserByUserId(forUser.getUserId());
		return buildUserrObjectWithChildObjectsForPharmacy(user);
	}

	private User buildUserrObjectWithChildObjectsForPharmacy(User user) {
		user.setAddress(addressDAO.getAddresByUserId(user.getUserId()));
		user.setMedicalprescription(
				medicalprescriptiondao.findByProperty(Medicalprescription.FIELD_patientId, user.getUserId()));
		return user;

	}

	public Long getAssociateUserIdFromForUser(ForUser forUser, ACCESS_TYPE accessDetails,
			RELATIONSHIP_TYPE relationship_TYPE) {

		if (relationship_TYPE == RELATIONSHIP_TYPE.PATIENT && forUser.isCallForSelf()) {
			return forUser.getForUserId();
		}

		Long userId = null;

		if (userDAO.hasUserAccessToDetails(forUser.getUserId(), forUser.getForUserId(), accessDetails,
				relationship_TYPE)) {
			userId = forUser.getForUserId();
		}

		if (Objects.isNull(userId)) {
			throw new IllegalArgumentException("Invalid ForUser");
		}
		return userId;
	}

	@Override
	@Transactional
	public void uploadProfilePic(Long userId, MultipartFile profilePic) {
		if (Objects.isNull(profilePic)) {
			throw new IllegalArgumentException("Please upload profile pic");
		}

		User user = userDAO.findById(userId);
		if (Objects.isNull(user)) {
			throw new IllegalArgumentException("User not found");
		}
		Personaldetail personaldetail = personaldetailDAO.findOneByProperty(BasePersonaldetail.FIELD_userId, userId);
		if (Objects.isNull(personaldetail)) {
			personaldetail = new Personaldetail();
			personaldetail.setUserId(userId);
		}

		try {
			byte[] profilePicBlob = profilePic.getBytes();
			personaldetail.setProfilePic(profilePicBlob);
		} catch (IOException e) {
			throw new IllegalArgumentException("Invalid Profile pic.");
		}
		personaldetail.setActive('Y');
		personaldetailDAO.save(personaldetail);
	}

	@Override
	@Transactional(readOnly = true)
	public void saveFcmTokenForpatient(Patientfcmtoken patientfcmtoken) {
		if (Objects.isNull(patientfcmtoken)) {
			throw new IllegalArgumentException("Data is not Found for given object");
		}
		patientfcmtokenDAO.save(patientfcmtoken);
	}

	@Override
	public void updtaeFcmTokenForpatient(Long patientId, Patientfcmtoken patientfcmtoken) {
		Patientfcmtoken dbpatientfcmtoken = patientfcmtokenDAO.findById(patientfcmtoken.getId());

		if (Objects.isNull(dbpatientfcmtoken)) {
			throw new IllegalArgumentException("Data is not found for given Id");
		}
		patientfcmtoken.setPatientid(patientId);
		dbpatientfcmtoken = Patientfcmtoken.biuldForupadtepatientfcmtoken(patientfcmtoken, dbpatientfcmtoken);
		patientfcmtokenDAO.save(dbpatientfcmtoken);
	}

	@Override
	public List<Patientfcmtoken> getFcmtokenForpatient(Long patientid) {
		List<Patientfcmtoken> patientfcmtokens = patientfcmtokenDAO.findByProperty(Patientfcmtoken.FIELD_patientid,
				patientid);
		return patientfcmtokens;
	}

	@Override
	@Transactional
	public void deleteMedicalinsurance(Long medicalinsuranceId) {
		Medicalinsurance medicalinsurance = medicalinsuranceDAO
				.getMediclInsuranceFromedicalinsuranceId(medicalinsuranceId);

		if (Objects.isNull(medicalinsurance)) {
			throw new IllegalArgumentException("Data is not found for given Id");
		}
		medicalinsurance.setActive('N');
		medicalinsuranceDAO.save(medicalinsurance);
	}

	@Override
	@Transactional
	public HashMap<Long, String> getRelationshipsUserIds(Long userid) {
		return relationshipDAO.getRelationshipsUserIds(userid);
	}

	@Override
	@Transactional
	public void getNewRelationshipsUserIds(Long id) {
		relationshipDAO.getNewRelationshipsUserIds(id);

	}

	@Override
	@Transactional
	public List<User> getallAssociateUser(Long userId, Integer pageNum, Integer maxResults) {

		Response<List<User>> response = userDAO.getAssociatedUsers(userId, pageNum, maxResults);
		List<User> user = response.getData();

		if (!CollectionUtils.isEmpty(user)) {
			user.forEach(us -> {
				Response<List<Relationship>> dbrelationship = relationshipDAO.getRelationships(userId, null, pageNum,
						maxResults);
				List<Address> adress = addressDAO.getAddresByUserId(userId);
				List<Vitals> vitals = vitalsDAO.getUserVitalsByuserId(userId);
				List<Relationship> relationships = dbrelationship.getData();
				if (CollectionUtils.isEmpty(relationships)) {
					throw new IllegalArgumentException("Relationship are not exists");
				}

				us.setRelationship(relationships);
				us.setAddress(adress);
				us.setVitals(vitals);
			});

		}
		return user;
	}

	@Override
	@Transactional
	public void saveUserPrefrence(Userreference userreference) {
		userreference.setActive('Y');
		userreferenceDAO.save(userreference);
	}

	@Override
	@Transactional
	public List<Userreference> getallUserreference(Long userid, String referencetype) {
		return userreferenceDAO.getallUserreference(userid, referencetype);

	}

	@Override
	@Transactional
	public void saveUserPrefrenceDiagnosticcenter(Userreference userreference) {
		userreference.setActive('Y');
		userreferenceDAO.save(userreference);
	}

	@Override
	@Transactional
	public List<Userreference> getallUserreferenceDiagnosticcenter(Long userid, String referencetype) {
		return userreferenceDAO.getallUserreferenceDiagnosticcenter(userid, referencetype);

	}

	@Override
	@Transactional
	public void updateUserPrimarydetails(User user, ForUser foruser, RELATIONSHIP_TYPE relationship_TYPE) {

		Long userId = getAssociateUserIdFromForUser(foruser, ACCESS_TYPE.PERSONAL_DETAILS, relationship_TYPE);
		User dbuser = findUserById(userId);

		if (Objects.isNull(dbuser)) {
			throw new IllegalArgumentException("The User has not been registered");
		}
		// validating username
		boolean isUserNameExistsForOtherUser = userDAO.getByIdNotAndUsernameEquals(user.getUserId(), user.getEmailId());
		if (isUserNameExistsForOtherUser) {
			throw new IllegalArgumentException("The User Name [" + user.getEmailId() + "] is already registered.");
		}

		user.setPassword(dbuser.getPassword());

		dbuser = User.buildForUpdate(dbuser, user);
		saveOrUpdateUser(dbuser, null);

	}

	@Override
	public void associateUser(Long userId, Long associateWithUserId, RELATIONSHIP_TYPE relationshipType) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Vitals> getUserLatestVitals(ForUser forUser, RELATIONSHIP_TYPE relationship_TYPE) {
		return vitalsDAO.getUserVitalsByuserId(forUser.getUserId());
	}

	@Override
	@Transactional
	public void deleteUserRefrence(Long userreferenceId) {
		Userreference usrUserreference = userreferenceDAO.findById(userreferenceId);
		usrUserreference.setActive('N');
		userreferenceDAO.save(usrUserreference);

	}

	@Override
	@Transactional
	public CaretakerResponseDTO patientRecordForCaretaker(Long caretakerId) {
		List<Appointment> appointment = appointmentDAO.getAppointmentForCaretaker(caretakerId);
		if (Objects.isNull(appointment)) {
			throw new IllegalArgumentException("Caretaker  appointment  not found");
		}

		List<Long> userIds = appointment.stream().map(Appointment::getUserId).collect(Collectors.toList());

		return buildUserrObjectWithChildObjectsForCareTaker(userIds);

	}

	private CaretakerResponseDTO buildUserrObjectWithChildObjectsForCareTaker(List<Long> userIds) {
		CaretakerResponseDTO caretakerResponseDTO = new CaretakerResponseDTO();
		List<Appointment> curentappointments = appointmentDAO.currentAppointmentsForCareTaker(userIds);
		List<Appointment> upcomeingappointments = appointmentDAO.upcomeingAppointmentsForCareTaker(userIds);
		List<Medication> medications = medicationDAO.findMedicationByUserIds(userIds);
		List<Vitals> vitals = vitalsDAO.getUserVitalsByuserId(userIds);
		List<Medicalreports> medicalreports = medicalreportsDAO.getmedicalreportsByuserIds(userIds);
		// List<Medicalprescription> medicalprescriptions =
		// medicalprescriptiondao.getPrescriptionForCareTaker(userIds);
		caretakerResponseDTO.setVitals(vitals);
		caretakerResponseDTO.setMedications(medications);
		caretakerResponseDTO.setMedicalreports(medicalreports);
		caretakerResponseDTO.setAppointment(curentappointments);
		caretakerResponseDTO.setAppointment(upcomeingappointments);
		// caretakerResponseDTO.setMedicalprescription(medicalprescriptions);
		return caretakerResponseDTO;
	}

}
