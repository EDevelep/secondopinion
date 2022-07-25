package org.secondopinion.superadmin.service;

import org.secondopinion.superadmin.dto.User;

public interface SuperAdminService {

  void registerSuperAdmin(User user);

  User loginAsSuperAdmin(String emailId, String paswd);
}
