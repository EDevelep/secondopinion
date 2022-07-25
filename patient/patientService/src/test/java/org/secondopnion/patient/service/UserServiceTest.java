
package org.secondopnion.patient.service;

import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import org.junit.Test;
import org.secondopinion.patient.dto.Address;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.Medicalinsurance;
import org.secondopinion.patient.dto.Personaldetail;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.dto.UserRelations;
import org.secondopinion.patient.dto.UserResponseDTO;
import org.secondopinion.patient.dto.VitalSearch;
import org.secondopinion.patient.dto.Vitals;
import org.secondopinion.patient.service.IUserRegistrationService;
import org.secondopinion.patient.service.VitalsReportService;
import org.secondopinion.patient.service.impl.CountryCodeService;
import org.secondopinion.patient.service.impl.UserService;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.NotificationAlert;
import org.secondopnion.patient.PatientApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class UserServiceTest extends PatientApplicationTest {

	@Autowired
	private UserService userService;

	@Autowired
	private VitalsReportService vitalsReportService;

	@Autowired
	private CountryCodeService countryCodeService;
	@Autowired
	private IUserRegistrationService userRegistrationService;

	@Autowired
	private org.secondopinion.patient.service.INotificationalertsService notificationalertsService;

	@Test
	public void getCountrycode() {
		String postalCode = "203201";
		countryCodeService.getCountrycode(postalCode);

	}

	@Test
	public void getUser() {
		String email = "jatin@cure-metric.com";
		String password = "Gani@1994";
		UserResponseDTO user = userService.getUser(email, password);
		assertNotNull(user);

	}

	/*
	 * @Test public void getPersonaldetail() { Long userId = 173L; RELATIONSHIP_TYPE
	 * relationship_TYPE=RELATIONSHIP_TYPE.valueOf(""); Personaldetail
	 * personaldetail = userService.getPersonaldetail(userId,relationship_TYPE);
	 * assertNotNull(personaldetail); }
	 */

	/*
	 * @Test public void updateUser() { Personaldetail personaldetail = new
	 * Personaldetail(); // personaldetail.setEthinicity("NOIDA");
	 * personaldetail.setEyeColor("BLACK"); personaldetail.setEyeColor("BLUE");
	 * personaldetail.setHeight("5.7"); personaldetail.setHighestDegree("MBBS");
	 * personaldetail.setLastUpdatedBy("SDMIN");
	 * personaldetail.setNationality("INDIA");
	 * personaldetail.setSpecialization("MBDDSSS");
	 * personaldetail.setPersonalDetailId(15L); Address address = new Address();
	 * address.setCity("Noida"); address.setAddressId(10L);
	 * address.setCountry("INDIA"); address.setCreartedby("jatin");
	 * address.setLastUpdatedBy("admin"); address.setLongitudes(9L);
	 * address.setLatitude(3L); address.setState("UP");
	 * address.setStreet1("postvasm"); address.setStreet2("baba nagra"); User user =
	 * new User(); // user.setPersonaldetail(personaldetail);
	 * user.setCellNumber("895877575"); user.setPassword("Jatinkmar@123");
	 * user.setEmailId("javakuma331@gmail.com");
	 * user.setPrimaryContact("768688686"); user.setCellNumber("99999999");
	 * user.setHomeNumber("8888888"); user.setUserName("bojbj");
	 * user.setEmailVerificationLink("JBXLIBXLI"); //
	 * user.setLastLogin(DateUtil.getDate()); user.setFirstName("tgeeb");
	 * user.setLastName("jbk"); user.setOperatedByUser(57L); user.setUserId(170L);
	 * // user.setAddress(Arrays.asList(address)); ForUser foruser = new ForUser();
	 * MultipartFile profilePic = null; userService.updateUser(user, profilePic);
	 * assertNotNull(user); }
	 */

	/*
	 * @Test public void getAddress() { ForUser foruser = new ForUser();
	 * foruser.setUserId(26L); userService.getAddress(foruser);
	 * 
	 * }
	 */

	@Test
	public void deleteAddress() {
		Long addressId = 59L;
		userService.deleteAddress(addressId);

	}

	@Test
	public void addAddress() {
		Long userId = 27L;
		Address address = new Address();
		address.setCity("Delhi");
		address.setCountry("INR");
		address.setLastUpdatedBy("admin");
		address.setState("UPTHEs");
		ForUser forUser = new ForUser();
		userService.addAddress(forUser, address, RELATIONSHIP_TYPE.PATIENT);
		assertNotNull(address);
	}

	@Test
	public void getMedicalInsuranceDtailsForUser() {
		ForUser forUser = new ForUser();
		forUser.setUserId(141L);
		List<Medicalinsurance> medicalinsurances = userService.getMedicalInsuranceDtailsForUser(forUser,
				RELATIONSHIP_TYPE.PATIENT);
		assertNotNull(medicalinsurances);
	}

	@Test
	public void deleteMedicalinsurance() {
		Long medicalinsuranceId = 7L;
		userService.deleteMedicalinsurance(medicalinsuranceId);

	}

	@Test
	public void addVitals() {
		ForUser forUser = new ForUser();
		forUser.setForUserId(92L);
		forUser.setUserId(50L);
		List<Vitals> vitals = new ArrayList<Vitals>();
		Vitals vitals1 = new Vitals();
		vitals1.setVitalname("pulse");
		vitals1.setVitalsId(531L);
		vitals1.setVitalValue("110");
		vitals1.setRecordedDate(DateUtil.getDate());
		vitals1.setMetricType("BPM");
		vitals.add(vitals1);
		userService.addVitals(forUser, vitals, RELATIONSHIP_TYPE.PATIENT);
	}

	@Test
	public void allAssociateUser() {
		Long id = 73L;
		UserRelations relationShipDtos = userService.allAssociateUser(id);
		assertNotNull(relationShipDtos);
	}

	@Test
	public void getNewRelationshipsUserIds() {
		Long id = 8L;

		userService.getNewRelationshipsUserIds(id);
	}

	@Test
	public void getRelationshipsUserIds() {
		Long userid = 4L;
		userService.getRelationshipsUserIds(userid);
	}

	@Test
	public void associateUser() {
		Long userId = 9L;
		Long associateWithUserId = 9L;
		RELATIONSHIP_TYPE relationshipType = RELATIONSHIP_TYPE.CHILD;
		userService.associateUser(userId, associateWithUserId, relationshipType);
	}

	@Test
	public void updateUserPassword() {
		String emailId = "jatin@gamil.com";
		String newPassword = "kumar";
		userService.updateUserPassword(emailId, newPassword);
	}

	@Test
	public void getNewRelationhipRequests() {
		Long userId = 47L;
		userService.getNewRelationhipRequests(userId);
	}

	@Test
	public void getNewUsers() {
		userService.getNewUsers();
	}

	@Test
	public void verifyUser() {
		Long userId = 47L;
		Long userIdToApprove = 4L;
		String verificationId = "cnbkjvbkasjb";
		RELATIONSHIP_TYPE relationshipType = RELATIONSHIP_TYPE.CHILD;
		userService.verifyUser(userId, userIdToApprove, verificationId, relationshipType);
	}

	@Test
	public void updateUserRelations() {
		Long userIdToApprove = 4L;
		Relationship relationship = new Relationship();
		relationship.setAccessToInsurance('Y');
		relationship.setApproved('Y');
		relationship.setUserId(32L);
		userService.updateUserRelations(userIdToApprove, Arrays.asList(relationship));
	}

	@Test
	public void addMedicalInsuranceDetails() {
		ForUser forUser = new ForUser();
		forUser.setForUserId(141L);
		forUser.setUserId(141L);
		Medicalinsurance medicalinsurance = new Medicalinsurance();
		medicalinsurance.setIsPrimaryHolder('Y');
		medicalinsurance.setPolicyNumber("134Test");
		medicalinsurance.setProviderName("sharma");
		medicalinsurance.setPolicyType("tets");
		userService.addMedicalInsuranceDetails(forUser, medicalinsurance, RELATIONSHIP_TYPE.CARETAKER);
	}

	@Test
	public void getUserRelations() {
		ForUser forUser = new ForUser();
		forUser.setForUserId(3L);
		forUser.setUserId(47L);
		userService.getUserRelations(forUser, RELATIONSHIP_TYPE.CARETAKER);
	}

	@Test
	public void searchVitals() {
		VitalSearch vitalSearch = new VitalSearch();

		userService.searchVitals(vitalSearch);
	}

	@Test
	public void getUserVitals() {
		ForUser forUser = new ForUser();
		forUser.setUserId(4L);
		forUser.setForUserId(4L);
		List<Vitals> vitals = userService.getUserVitals(forUser, RELATIONSHIP_TYPE.CARETAKER);
		assertNotNull(vitals);
	}

	@Test
	public void sendNotification() {
		NotificationAlert alert = new NotificationAlert();
		alert.setMessage("welcomd");
		alert.setUserPrimaryKey(41L);
		alert.setObjectName("test");
		alert.setUserPrimaryKey(41L);
		alert.setObjectKey(41L);
		alert.setDate((DateUtil.addAndGetDate(TimeZone.getDefault(), 7)));
		notificationalertsService.sendNotification(alert);

	}

	@Test
	public void getDoctorDashBoardForDoctorIdAndAppointmentFor() {
		userRegistrationService.getDoctorDashBoardForDoctorIdAndAppointmentFor(32L, "DOCTOR");

	}
}
