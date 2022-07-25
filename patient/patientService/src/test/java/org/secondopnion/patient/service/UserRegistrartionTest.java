package org.secondopnion.patient.service;

import java.util.Iterator;

import org.junit.Test;
import org.secondopinion.enums.PrimaryContactEnum;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.service.impl.UserRegistrationService;
import org.secondopinion.patient.service.impl.UserService;
import org.secondopinion.utils.DateUtil;
import org.secondopnion.patient.PatientApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class UserRegistrartionTest extends PatientApplicationTest {

  @Autowired
  private UserRegistrationService userRegistrationService;
  @Autowired
  private UserService userService;

  @Test
  public void saveUser() {
    User user = new User();

    user.setCellNumber("8784545487");
    user.setPassword("Baloney12@");
    user.setHomeNumber("985983488");
    user.setFirstName("kumarg");
    user.setEmailId("dhanurijavas@gmail.com");
    user.setUserName("rahul");
    user.setUiHostURL("zoom.com");
    user.setPrimaryContact(PrimaryContactEnum.CELL_NUMBER.name());
    user.setLastLogin(DateUtil.getDate());
    user.setOperatedByUser(57L);
    user.setLastName("sharma");
    user.setDependent('Y');
    user.setUiHostURL("zoom.com");
    user.setDependent('Y');
    MultipartFile multipart = null;
    userRegistrationService.registerUser(user, multipart);
  }


  @Test
  public void updateUser() {
    User user = new User();

    user.setCellNumber("8784545487");
   // user.setPassword("Baloney12@");
    user.setHomeNumber("985983488");
    user.setFirstName("kumarg");
  //  user.setEmailId("dhanurijavas@gmail.com");
   // user.setUserName("rahul");
  //  user.setUiHostURL("zoom.com");
    //user.setPrimaryContact(PrimaryContactEnum.CELL_NUMBER.name());
    user.setLastLogin(DateUtil.getDate());
    user.setOperatedByUser(57L);
    user.setLastName("sharma");
    user.setDependent('Y');
    user.setUiHostURL("zoom.com");
    user.setDependent('Y');
    MultipartFile multipart = null;
    ForUser forUser=new ForUser();
    forUser.setUserId(2L);
    forUser.setForUserId(2L);
    forUser.setRelationType(RELATIONSHIP_TYPE.PATIENT);
    userService.updateUser(user, multipart, forUser, null);;
  }
  // @Test
  public void activateRegistration() {
    Long userIdToActivate = 166L;
    userRegistrationService.activateRegistration(userIdToActivate);
  }


  // @Test
  public void requestOTPForPhoneVerification() {
    String phonenumber = "9381930137";
    String actionName = "Resend otp";
    userRegistrationService.requestOTPForPhoneVerification(phonenumber,"");
  }

  @Test
  public void emailVerification() {
    Integer otp = 196254;
    String emailid = "sachinsharma@gmail.com";
    userRegistrationService.emailVerification(emailid, otp,"sachin");
  }

  @Test
  public void forgotPassword() {
    String emailId = "gemsofhyd@gmail.com";
    String resetPwdLink = "zoom.com";
    userRegistrationService.forgotPassword(emailId, resetPwdLink,"");
  }

  // @Test
  public void requestEmailVerificationLink() {
    String emailId = "naga.ajay@qontrack.com";
    userRegistrationService.requestEmailVerificationLink(emailId);
  }

  // @Test
  public void userNameExists() {
    String userName = "jatin";
    userRegistrationService.userNameExists(userName);
  }

  // @Test
  public void userEmailRegistered() {
    String userEmail = "java@gmail.com";
    userRegistrationService.userEmailRegistered(userEmail);
  }


  @Test
  public void phoneverification() {
    String phonenumber = "9381306079";
    Integer otp = 905744;
    Long foruserId=73L;
    userRegistrationService.phoneVerification(phonenumber, otp,"radha",foruserId);
  }

  @Test
  public void resetPassword() {
    String emailId = "java@gmail.com";
    String newPassword = "jatin";
    userRegistrationService.resetPassword(emailId, newPassword, 123456,"");
  }

}
