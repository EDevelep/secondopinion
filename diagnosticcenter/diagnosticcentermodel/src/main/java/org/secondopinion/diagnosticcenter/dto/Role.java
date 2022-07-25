package org.secondopinion.diagnosticcenter.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.diagnosticcenter.domain.BaseRole; 



@Entity 
@Table (name="role")
public class Role extends BaseRole{
	
	public enum RoleEnum {
		ADMIN, SYSTEM_ADMIN, TECHNICIAN, CASHIER, OTHER,FINANCE, COLLECTIONAGENT, ASSOCIATEDUSER
	}
}