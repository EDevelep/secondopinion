package org.secondopinion.superadmin.dao;

import java.util.List;
import org.secondopinion.dao.IDAO;
import org.secondopinion.superadmin.dto.Userrole;


public interface UserroleDAO extends IDAO<Userrole, Long> {

  List<Userrole> getByRoleIdAndUserId(Long userId);
}
