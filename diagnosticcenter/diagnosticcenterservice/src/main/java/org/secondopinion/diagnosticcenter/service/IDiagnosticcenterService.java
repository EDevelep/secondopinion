package org.secondopinion.diagnosticcenter.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenter;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterSearchRequest;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteraddress;
import org.secondopinion.diagnosticcenter.dto.Personaldetail;
import org.secondopinion.diagnosticcenter.dto.ProfileCompltedDTO;
import org.secondopinion.request.Response;

public interface IDiagnosticcenterService {


  void registerDiagnosticcenter(Diagnosticcenter diagnosticcenter);

  Collection<Diagnosticcenter> fetchAllDiagnosticcenters();

  List<Diagnosticcenteraddress> getAllDiagnosticcenterBySearchCritieria(
      DiagnosticcenterSearchRequest diagnosticcenterSearchRequest);

  Diagnosticcenter fetchByDiagnosticcenterId(Long DiagnosticcenterId);

  Diagnosticcenter fetchDiagnosticcenterByLicenseNumber(String licenseNumber);

  void emailVerification(String emailid, Integer emailotp);

  void resetPassword(String emailId, String newPassword, Integer otp);

  void resendOTPForResetPassword(String emailId, String resetPasswordLink);

  void forgotPassword(String emailId, String resetPwdLink);

  // boolean activateRegistration(Long userIdToActivate);

  void requestEmailVerificationLink(String emailId);

  void phoneverification(String phone, Integer otp);

  String otpverification(Integer otp, String emailId);

  void requestOTPForPhone(String phonenumber);

  void requestOTPForEmail(String email);

  void resendOTPForEmail(String email);

  Boolean isEmailExistOrNot(String email);

  Boolean isPhoneExistOrNot(String phonenumber);

  Response<List<Diagnosticcenteraddress>> getAddressesOfDiagnosticcenter(Long DiagnosticcenterId,
      Integer pageNum, Integer maxResults);

  void saveAddressesOfDiagnosticcenter(Diagnosticcenteraddress address);

  void updateAddressesOfDiagnosticcenter(Diagnosticcenteraddress address);

  Map<String, Object> getMyReports(Long diagnosticCenterAddressid);

  Diagnosticcenteraddress getDiagnosticcenteraddressById(Long diagnoticCenterAddressId);

  List<Diagnosticcenter> getAllDiagnosticcenterBySearchCritieria(List<String> string);

  ProfileCompltedDTO isProfileCompleted(Long diagnoticCenterId);

  void savePersonaldetail(Personaldetail personaldetail);

  Personaldetail findPersonalDetailBydiagnosticcenterId(Long diagnosticcenterId);

  List<Diagnosticcenter> findDiagnosticcenterByLocation(
      DiagnosticcenterSearchRequest diagnosticcenterSearchRequest);

  void emailVerificationForDiagnosticcenterUser(String emailid, Integer emailotp);

  void phoneverificationForDiagnosticcenterUser(String cellNumber, Integer otp);

  void requestOTPForEmailDiagnosticcenterUser(String email);

List<Diagnosticcenter> getAssoctedDiagnosticCenter(Long diagnosticCenteradminId);

}
