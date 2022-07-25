/*
 * user info object to maintain user object in the cache
 */
package org.securityservice.jwt;

import java.util.List;

public class UserInfo {
	 private String jwtToken;
     private Long userId;
     private String userName;
     private String moduleType;
     private List<String> roles;
     
	public UserInfo(String userName, String jwtToken, String moduleType, Long userId, List<String> roles) {
		this.userId = userId;
		this.userName = userName;
		this.jwtToken = jwtToken;
		this.moduleType = moduleType;
		this.roles = roles;
	}

	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
}
