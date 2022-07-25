package org.secondopinion.superadmin.dto;



import javax.persistence.Entity;
import javax.persistence.Table;
import org.secondopinion.superadmin.domain.BaseRoles;



@Entity
@Table(name = "roles")
public class Roles extends BaseRoles {
  public enum RoleEnum {
    ADMIN, SYSTEM_ADMIN, TECHNICIAN, CASHIER, OTHER, FINANCE, COLLECTIONAGENT, ASSOCIATEDUSER,SUPERADMIN
  }

}
