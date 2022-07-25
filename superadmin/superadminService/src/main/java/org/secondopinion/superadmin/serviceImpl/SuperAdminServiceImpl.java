package org.secondopinion.superadmin.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.secondopinion.enums.PrimaryContactEnum;
import org.secondopinion.superadmin.dao.RolesDAO;
import org.secondopinion.superadmin.dao.UserDAO;
import org.secondopinion.superadmin.dao.UserroleDAO;
import org.secondopinion.superadmin.domain.BaseUser;
import org.secondopinion.superadmin.dto.Roles;
import org.secondopinion.superadmin.dto.User;
import org.secondopinion.superadmin.dto.Userrole;
import org.secondopinion.superadmin.service.SuperAdminService;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {

  @Autowired
  private UserDAO userDAO;
  @Autowired
  private RolesDAO rolesDAO;
  @Autowired
  private UserroleDAO userroleDAO;

  @Override
  @Transactional
  public void registerSuperAdmin(User user) {

    if (StringUtil.isNullOrEmpty(user.getCellNumber())) {
      throw new IllegalArgumentException("Cell Number can not be null.");
    }
    List<String> pcEnums = Arrays.stream(PrimaryContactEnum.values()).map(pc -> pc.name())
        .collect(Collectors.toList());
    if (StringUtil.isNullOrEmpty(user.getPrimaryContact())
        || !pcEnums.contains(user.getPrimaryContact())) {
      throw new IllegalArgumentException(
          "Primary contact either can not be null or it should be in " + pcEnums);
    }

    User userByEmail = userDAO.findOneByProperty(BaseUser.FIELD_emailId, user.getEmailId());

    if (Objects.nonNull(userByEmail)) {
      throw new IllegalArgumentException(
          "The email id [" + user.getEmailId() + "] is already registered.");
    }
    UserHelper.passwordValidation(user.getPassword());
    user.setPassword(UserHelper.getHashedPassWord(user.getPassword()));
    if (StringUtil.isNullOrEmpty(user.getCellNumber())) {
      user.setCellNumber(user.getPrimaryContact());
    }

    user.setUserName(user.getEmailId());
    user.setActive('Y');
    userDAO.save(user);
    createAdminUser(user);

  }

  private void createAdminUser(User user) {
    Roles roles = rolesDAO.getRoleByName(Roles.RoleEnum.ADMIN.name());
    if (Objects.isNull(roles)) {
      throw new IllegalArgumentException(Roles.RoleEnum.ADMIN.name() + " not exist in database.");
    }
    user.setRoleId(roles.getRoleId());
    saveUserrole(user);
  }

  @Transactional
  private void saveUserrole(User user) {
    Userrole userrole = new Userrole();
    userrole.setRoleId(user.getRoleId());
    userrole.setUserId(user.getUserId());
    userrole.setRoleId(user.getRoleId());
    userroleDAO.save(userrole);
  }

  @Override
  @Transactional
  public User loginAsSuperAdmin(String emailId, String password) {

    User user = userDAO.findOneByProperty(BaseUser.FIELD_emailId, emailId);

    if (Objects.isNull(user)) {
      throw new IllegalArgumentException("Invalid EmailID.");
    }

    if (user.getLocked() == 'Y') {
      throw new IllegalArgumentException(
          "Your UserID has been locked out due to 3 consecutive incorrect attempts. ");
    }


    if (user.getActive() == 'N') {
      throw new IllegalArgumentException(
          "You have not verified your email address. Click the \"Verify Now\" button provided in the verification email In case you have not received a verification email, please contact us at support@qontrack.com ");
    }

    String dbPassword = user.getPassword();
    if (!UserHelper.checkpw(password, dbPassword)) {
      // updateRetryCountIfLoginFailed(user.getUserId(), user.getRetry());
      throw new IllegalArgumentException("Invalid UserID or Password.");

    }
    List<Userrole> userRoles = userroleDAO.getByRoleIdAndUserId(user.getUserId());
    if (CollectionUtils.isEmpty(userRoles)) {
      throw new IllegalArgumentException("Roles are not exists");
    }
    List<Integer> roleIds =
        userRoles.stream().map(ur -> ur.getRoleId()).collect(Collectors.toList());

    user.setRoles(rolesDAO.getByUserroles(roleIds));
    user.setRoleId(roleIds.get(0));

    return user;
  }

  /*
   * private void updateRetryCountIfLoginFailed(Long userId, Integer retry) {
   * userDAO.updateRetryCountIfLoginFailed(userId, retry);
   * 
   * } }
   */

}
