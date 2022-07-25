package org.secondopinion.pharmacy.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.secondopinion.pharmacy.dao.PharmacyDAO;
import org.secondopinion.pharmacy.dao.PharmacyuserDAO;
import org.secondopinion.pharmacy.dao.RegistrationDAO;
import org.secondopinion.pharmacy.dao.RolesDAO;
import org.secondopinion.pharmacy.dao.UserroleDAO;
import org.secondopinion.pharmacy.dto.Pharmacy;
import org.secondopinion.pharmacy.dto.Pharmacyuser;
import org.secondopinion.pharmacy.dto.Registration;
import org.secondopinion.pharmacy.dto.Userrole;
import org.secondopinion.pharmacy.service.IPharmacyuserLoginService;
import org.secondopinion.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class PharmacyuserLoginServiceImpl implements IPharmacyuserLoginService {

  @Autowired
  private PharmacyuserDAO pharmacyuserDAO;

  @Autowired
  private RegistrationDAO registrationDAO;
  @Autowired
  private RolesDAO rolesDAO;

  @Autowired
  private PharmacyDAO pharmacyDAO;

  @Autowired
  private UserroleDAO userRoleDAO;

  @Override
  public Pharmacyuser login(String userName, String password) {

    Pharmacyuser pharmacyuser =
        pharmacyuserDAO.findPharmacyByUserNameAndEmail(userName);

    if (Objects.isNull(pharmacyuser)) {
      throw new IllegalArgumentException(
          "Invalid UserID or Password..");
    }
    Pharmacy pharmacy = pharmacyDAO.findOneByProperty(Pharmacy.FIELD_pharmacyaddressId,
        pharmacyuser.getPharmacyaddressId());

    if (Objects.isNull(pharmacy)) {
      throw new IllegalArgumentException(
          "You have not registered with us with the email address that you have specified.");
    }
    Registration registration =
        registrationDAO.findOneByProperty(Registration.FIELD_pharmacyId, pharmacy.getPharmacyId());

    if (Objects.isNull(registration) || Objects.isNull(registration.getActive())
        || registration.getActive() == 'N' || pharmacy.getActive() == 'N') {
      throw new IllegalArgumentException(
          "Your Email Id Is  Unverified.");
    }

    String hashedPasswordFromDb = pharmacyuser.getPassword();
    if (!UserHelper.checkpw(password, hashedPasswordFromDb)) {
      updateRetryCountIfLoginFailed(pharmacyuser.getPharmacyUserId(), pharmacyuser.getRetry());
      throw new IllegalArgumentException("Invalid UserID or Password.");
    }

    List<Userrole> userRoles =
        userRoleDAO.getByRoleIdAndUserId(null, pharmacyuser.getPharmacyUserId());
    if (CollectionUtils.isEmpty(userRoles)) {
      throw new IllegalArgumentException("Roles are not exists");
    }
    List<Integer> roleIds =
        userRoles.stream().map(ur -> ur.getRoleId()).collect(Collectors.toList());

    pharmacyuser.setRoles(rolesDAO.getByUserroles(roleIds));
    updateLastLoginTime(pharmacyuser.getPharmacyUserId());
    pharmacyuser.setRoleId(roleIds.get(0));
    return pharmacyuser;
  }



  private void updateLastLoginTime(Long pharmacyUserId) {
    pharmacyuserDAO.updateLastLoginTime(pharmacyUserId);
  }

  private void updateRetryCountIfLoginFailed(Long pharmacyUserId, Integer retry) {
    pharmacyuserDAO.updateRetryCountIfLoginFailed(pharmacyUserId, retry);

  }
}
