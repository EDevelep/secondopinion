package org.secondopinion.pharmacy.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.pharmacy.domain.BasePharmacyuser;

@Entity
@Table(name = "pharmacyuser")
public class Pharmacyuser extends BasePharmacyuser {

	private Integer roleId;
	private List<Roles> roles;
	private String uiHostURL;
	
	
	@Transient
	public String getUiHostURL() {
		return uiHostURL;
	}
	public void setUiHostURL(String uiHostURL) {
		this.uiHostURL = uiHostURL;
	}

	@Transient
	public List<Roles> getRoles() {
		return roles;
	}
	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	@Transient
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public enum PasswordTypeEnum {
		CREATE, FORGOT, CHANGE
	}


	
}