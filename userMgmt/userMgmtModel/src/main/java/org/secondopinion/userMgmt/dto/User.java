package org.secondopinion.userMgmt.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.userMgmt.domain.BaseUser;

import org.secondopinion.utils.StringUtil;

@Entity
@Table(name = "user")
public class User extends BaseUser {
	private Company company;
	private List<Roles> roles;
	private List<UserRole> userRoles;
	private UserProfilePic userProfilePic;

	@Transient
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Transient
	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	@Transient
	public void addRole(UserRole userRole) {
		if(userRoles == null){
			userRoles = new ArrayList<UserRole>();
		}
		userRoles.add(userRole);
	}

	@Transient
	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	
	
	/**
	 * @return the userProfilePic
	 */
	@Transient
	public UserProfilePic getUserProfilePic() {
		return userProfilePic;
	}

	/**
	 * @param userProfilePic the userProfilePic to set
	 */
	public void setUserProfilePic(UserProfilePic userProfilePic) {
		this.userProfilePic = userProfilePic;
	}

	@Transient
	public String getFormatedEmail(){
		StringBuilder sb = new StringBuilder();
		sb.append(getFirstName()).append(" ").append(getLastName()).append("<").append(getEmailId()).append(">");
		
		return sb.toString();
	}
	
	@Transient
	public boolean hasRole(String roleName){
		for(Roles role : roles){
			if(StringUtil.equalsIgnoreCase(role.getRoleName(), roleName)){
				return true;
			}
		}
		
		return false;
	}
	
	@Transient
	public boolean hasClientAssoiciationRole(){
		String[] roles = {"ACCOUNT_MNGR","LEAD_RECRUITER","BDM"};
		
		for(String role : roles){
			if(hasRole(role)){
				return true;
			}
		}
		
		return false;
	}
}