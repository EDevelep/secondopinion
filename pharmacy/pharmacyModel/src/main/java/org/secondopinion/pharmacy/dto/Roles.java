package org.secondopinion.pharmacy.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.pharmacy.domain.BaseRoles; 



@Entity 
@Table (name="roles")
public class Roles extends BaseRoles{
	
	public enum PharmacyRolesEnum {
		ADMIN, SYSTEM_ADMIN, MANAGER, CASHIER, WORKER, OTHER
	}
}