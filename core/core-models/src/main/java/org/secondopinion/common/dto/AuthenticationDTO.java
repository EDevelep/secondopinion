package org.secondopinion.common.dto;

public class AuthenticationDTO {

	private String userName;
	private String password;
	private String type;
	public final String getUserName() {
		return userName;
	}
	public final void setUserName(String userName) {
		this.userName = userName;
	}
	public final String getPassword() {
		return password;
	}
	public final void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "AuthenticationDTO [userName=" + userName + ", password=" + password+" , userName=" + userName + "]";
	}
	
}
