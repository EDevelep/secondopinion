package org.secondopinion.doctor.service;

import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.secondopinion.doctor.DoctorServiceApplicationTests;
import org.secondopinion.doctor.dao.DoctorDAO;
import org.secondopinion.doctor.dto.Certification;
import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.doctor.dto.DoctorAddress;
import org.secondopinion.doctor.dto.DoctorSearchRequest;
import org.secondopinion.doctor.dto.Feedetails;
import org.secondopinion.doctor.dto.Personaldetail;
import org.secondopinion.doctor.dto.Specialization;
import org.secondopinion.doctor.dto.TYPE;
import org.secondopinion.enums.PrimaryContactEnum;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class DoctorServiceImplTest extends DoctorServiceApplicationTests {

  @Autowired
  private IDoctorService doctorService;

  @Autowired
  private IDoctorRegistrationService doctorRegistrationService;

  @Autowired
  private IDoctorLoginService doctorLoginService;

  @Autowired
  private DoctorDAO doctorDAO;

  @Test
  public void updateDoctorDetails() {
    Doctor doctor = new Doctor();
    doctor.setDoctorId(38L);
    doctor.setFirstName("kiran");
    doctor.setEmailId("mamidisrikar93@gmail.com");
    doctor.setCellNumber("7500760303");
    doctor.setGender("male");
    doctor.setPassword("Srikar@1234");

    MultipartFile profilePic = null;
    doctorRegistrationService.updateDoctorDetails(doctor, profilePic);
  }

  @Test
  public void saveDoctor() {
    Doctor doctor = new Doctor();

    doctor.setFirstName("raja1");
    doctor.setLastName("sharma1");
    doctor.setEmailId("test1@qontrack.com");
    doctor.setCellNumber("8766554323");
    doctor.setSpecialization("physician");
    doctor.setPassword("Gani@1994");
    doctor.setGender("Male");
    doctor.setUiHostURL("curmatric.com");
    doctor.setPrimaryContact(PrimaryContactEnum.CELL_NUMBER.name());
    doctor.setType(TYPE.DOCTOR.name());
    Feedetails feedetails = new Feedetails();
    feedetails.setAudit("kale");
    feedetails.setFee(1000.00);


    DoctorAddress doctorAddress = new DoctorAddress();
    doctorAddress.setCity("hyderabad");
    doctorAddress.setCountry("India");
    doctorAddress.setZip("502320");
    doctorAddress.setLatitude(3D);
    doctorAddress.setLongitude(5D);
    // doctor.setDoctorAddresses(Arrays.asList(doctorAddress));

    Personaldetail personaldetail = new Personaldetail();
    personaldetail.setDoctorId(2L);
    // personaldetail.setDOB(new Date());
    personaldetail.setEthinicity("test");
    // doctor.setPersonaldetail(personaldetail);

    Certification certification = new Certification();
    certification.setInstituteName("yeshwanth");
    certification.setDegree("completed");
    // certification.setYearGraduated(2020);

    // Educationdetails ed = new Educationdetails();
    // ed.setDegree("mca");
    // ed.setExperience("10");
    // List<Educationdetails> eds = new ArrayList<>();
    // eds.add(ed);
    //
    // doctor.setEducationDetailses(eds);

    doctorRegistrationService.registerDoctor(doctor);
    // doctorService.deletDoctor(doctor);
    assertNotNull(doctor);
  }

  @Test
  public void testFinddoctorById() {
    Doctor doctor = doctorRegistrationService.getDoctorById(40L);
    assertNotNull(doctor);
  }

  @Test
  public void testGetDoctorFeeDetails() {
    Collection<Feedetails> feedetails = doctorService.getDoctorFeeDetails(1L);
    assertNotNull(feedetails);
  }

  @Test
  public void testGetDoctorSpecialization() {
    Collection<Specialization> specialization = doctorService.getDoctorSpecialization();
    assertNotNull(specialization);
  }

  @Test
  public void testUpdateDoctorDetails() {
    Long doctorId = 2L;
    Doctor doctor = doctorDAO.findById(doctorId);

    Personaldetail personaldetail = new Personaldetail();
    personaldetail.setEthinicity("testsample");
    doctor.setPersonaldetail(personaldetail);

    Certification certification = new Certification();
    certification.setInstituteName("jashwanth");
    certification.setDegree("inprogress");
    List<Certification> eds = new ArrayList<>();
    eds.add(certification);
    doctor.setCertifications(eds);

    doctorRegistrationService.updateDoctorDetails(doctor, null);
  }

  @Test
  public void testGetDoctorById() {
    Doctor doctor = doctorRegistrationService.getDoctorById(2L);
    assertNotNull(doctor);
  }

  // @Test
  // public void testRegisterDoctor() {
  // Doctor doctor = new Doctor();
  // doctor.setFirstName("srikar");
  // doctor.setDoctorId(220L);
  // doctor.setAudit("jhon");
  // doctor.setCellNumber("9876541234");
  // doctor.setSpecialization("physician");
  // doctor.setPassword("sri@123");
  // doctor.setNationality("indian");
  // doctor.setEmailId("doc@gmail.com");
  // doctor.setEmailVerificationLink("Https://");
  // doctorService.registerDoctor(doctor);
  // assertNotNull(doctor);
  // }

  @Test
  public void testEmailVerification() {
    String emailid = "jitendra@qontrack.com";
    Integer emailotp = 522692;
    doctorRegistrationService.emailVerification(emailid, emailotp);
  }

  @Test
  public void testRequestEmailVerificationLink() {
    doctorRegistrationService.requestEmailVerificationLink("srikar@qontrack.com");
  }

  @Test
  public void testForgotPassword() {
    doctorRegistrationService.forgotPassword("abc@gmail.com", "http://");
  }

  @Test
  public void testResetPassword() {
    doctorRegistrationService.resetPassword("support@cure-metric.com", "Itsmegani@1997", 998017,
        "DOCTOR");
  }

  @Test
  public void testVerifyDoctor() {
    // doctorService.verifyDoctor(11L, "abc@123");
  }

  @Test
  public void testApproveDoctor() {
    doctorRegistrationService.approveDoctor(11L);
  }

  @Test
  public void testLogin() {
    Doctor doctor = doctorLoginService.login("ganesh@qontrack.com", "Gani@1994", "DOCTOR");
    assertNotNull(doctor);
  }

  @Test
  public void testDeletDoctor() {

    doctorRegistrationService.deletDoctor(38L);
  }

  @Test
  public void testGetDoctorBySpecilazation() {
    doctorService.getDoctorBySpecilazation(32L);
  }

  @Test
  public void testGetAllDoctors() {
    int pagenumber = 1;
    int maxresult = 1;
    Response<List<Doctor>> docResponse = doctorService.getAllDoctors(pagenumber, maxresult);
    assertNotNull(docResponse);
  }

  @Test
  public void testSaveSpecialization() {
    Specialization specialization = new Specialization();
    specialization.setLastUpdatedBy("system");
    doctorService.saveSpecialization(specialization);
  }

  @Test
  public void testGetSpecailizationById() {
    doctorService.getSpecailizationById(52L);
  }

  @Test
  public void testDeleteSpecialization() {
    doctorService.deleteSpecialization(12L);
  }

  @Test
  public void testGetSpecializationsByDoctorId() {
    Collection<Specialization> specializations = doctorService.getSpecializationsByDoctorId(52L);
    assertNotNull(specializations);
  }

  @Test
  public void testSaveDoctorFeeDetails() {
    Feedetails feedetails = new Feedetails();
    feedetails.setDoctorId(40L);
    feedetails.setFee(40.00);
    doctorService.saveDoctorFeeDetails(feedetails);
    assertNotNull(feedetails);
  }

  @Test
  public void testDeleteDoctorFeeDetails() {
    doctorService.deleteDoctorFeeDetails(81L);
  }

  @Test
  public void testGetFeeDetailsById() {
    doctorService.getFeeDetailsById(1L);
  }

  @Test
  public void testSaveDoctorAddress() {
    DoctorAddress doctorAddress = new DoctorAddress();
    doctorAddress.setAudit("audit");
    doctorAddress.setCity("hyd");
    doctorAddress.setCountry("Ind");
    doctorAddress.setCreatedBy("rakesh");
    doctorAddress.setLastUpdatedBy("sharma");
    doctorService.saveDoctorAddress(doctorAddress);

  }

  @Test
  public void testGetDoctorAddressByDoctorId() {
    doctorService.getDoctorAddressByDoctorId(23L);
  }

  @Test
  public void testdeleteDoctorAssociation() {
    doctorService.deleteDoctorAssociation(106L);
  }

  @Test
  public void testDeleteDoctorAddress() {
    doctorService.deleteDoctorAddress(1L);
  }

  @Test
  public void testGetDoctorAddressById() {
    doctorService.getDoctorAddressById(12L);
  }

  @Test
  public void testVerifyEmailIdExists() {
    doctorRegistrationService.verifyEmailIdExists("abc@gmail.com");
  }

  @Test
  public void testFetchDoctorsByFlagWithPagination() {
    // doctorService.fetchDoctorsByFlagWithPagination(doctorFlagsRequest)
  }

  @Test
  public void testGetMyReports() {
    Map<String, Object> myReportsMap = doctorService.getMyReports(40L);
    assertNotNull(myReportsMap);
  }

  // requestOTPForPhoneVerification
  @Test
  public void saveCertification() {
    Certification certification = new Certification();
    doctorService.saveCertification(certification);

  }

  @Test
  public void requestOTPForPhoneVerification() {
    String phone = "9381930137";
    doctorRegistrationService.requestOTPForPhoneVerification(phone);

  }
  

  @Test
  public void resendOTPForEmail() {
    String phone = "test@gmail.com";
    doctorRegistrationService.resendOTPForEmail(phone);

  }

  @Test
  public void otpVerificationForEmail() {
    String type = "DOCTOR";
    Integer otp = 186755;
    String emailId = "ganesh123@qontrack.com";
    doctorRegistrationService.otpVerificationForEmail(otp, emailId, type);

  }

  @Test
  public void requestOTPForEmailVerification() {
    String emailid = "jitendra@qontrack.com";
    doctorRegistrationService.requestOTPForEmailVerification(emailid);

  }



  @Test
  public void getAllDoctorsBySearchCritieria() {
    DoctorSearchRequest doctorSearchRequest = new DoctorSearchRequest();

    Collection<Doctor> doctor = doctorService.getAllDoctorsBySearchCritieria(doctorSearchRequest);
    assertNotNull(doctor);

  }
}
