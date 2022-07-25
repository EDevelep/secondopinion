package org.secondopinion.diagnosticcenter.service;

import java.util.Collection;
import java.util.List;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser;
import org.secondopinion.diagnosticcenter.dto.Role;
import org.secondopinion.diagnosticcenter.dto.UpdatePasswordRequest;
import org.secondopinion.request.Response;
import org.springframework.web.multipart.MultipartFile;

public interface IDiagnosticcenteruserRegistrationService {

  void addDiagnosticcenteruser(Diagnosticcenteruser diagnosticcenteruser, boolean creteFromDC,
      List<String> roleNames);

  Diagnosticcenteruser getByDiagnosticcenterIdAndEmailId(Long diagnosticcenterId, String emailId);

  void resetPasswordByDiagnosticcenter(UpdatePasswordRequest updatePasswordRequest);

  void resetPassword(UpdatePasswordRequest updatePasswordRequest);

  void resetPasswordForDiagnosticcenteruser(UpdatePasswordRequest updatePasswordRequest);

  void verifyemail(Long diagnosticcenterId, String emailId);

  void activateOrDeactivateUser(Long diagnosticcenterUserId, boolean deactivateUser);

  Response<List<Diagnosticcenteruser>> getAssociatedUsers(Long diagnosticcenterId, Integer pageNum,
      Integer maxResults);

  Diagnosticcenteruser getPrimaryDetails(Long diagnosticcenteruserid);

  void updatePrimaryDetails(Diagnosticcenteruser diagnosticcenteruser);

  Diagnosticcenteruser getDiagnosticcenteruserByVerificationId(String verificationId,
      String passwordTypeEnum);

  Collection<Role> getAllRoles();

  void uploadProfilePic(Long diagnosticcenterId, MultipartFile profilePic);

  void forgotPassword(String emailId, String uiHostURL);

  Diagnosticcenteruser addprimaryDiagnosticcenteruser(Diagnosticcenteruser diagnosticcenteruser);

	void uploadReport(Long diagnosticcenterId, MultipartFile report);
}
