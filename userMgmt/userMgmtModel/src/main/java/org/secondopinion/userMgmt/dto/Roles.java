package org.secondopinion.userMgmt.dto; 


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.userMgmt.domain.BaseRoles; 



@SuppressWarnings({ "serial"})
@Entity 
@Table (name="roles")
public class Roles extends BaseRoles{
	private List<Privileges> privileges;

	/**
	 * @return the privileges
	 */
	@Transient
	public List<Privileges> getPrivileges() {
		return privileges;
	}

	/**
	 * @param privileges the privileges to set
	 */
	public void setPrivileges(List<Privileges> privileges) {
		this.privileges = privileges;
	}
}