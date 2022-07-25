package org.secondopinion.diagnosticcenter.dto; 


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.diagnosticcenter.domain.BaseDiagnosticcenteruser;



@Entity 
@Table (name="diagnosticcenteruser")
public class Diagnosticcenteruser extends BaseDiagnosticcenteruser{
	private Integer roleId;
	private List<Role> roles;
	private String uiHostURL;
	private Long  diagnosticcenterId;
	
	
	@Transient
	public String getUiHostURL() {
		return uiHostURL;
	}
	public void setUiHostURL(String uiHostURL) {
		this.uiHostURL = uiHostURL;
	}

	@Transient
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
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
	@Transient
	public Long getDiagnosticcenterId() {
		return diagnosticcenterId;
	}
	public void setDiagnosticcenterId(Long diagnosticcenterId) {
		this.diagnosticcenterId = diagnosticcenterId;
	}
	
}