package org.secondopinion.patient.dto;



import javax.persistence.Entity;
import javax.persistence.Table;
import org.secondopinion.patient.domain.BaseRoles;



@Entity
@Table(name = "roles")
public class Roles extends BaseRoles {
  public enum RoleEnum {
    ADMIN, SYSTEM_ADMIN, TECHNICIAN, CASHIER, OTHER, FINANCE, COLLECTIONAGENT, ASSOCIATEDUSER
  }

}
