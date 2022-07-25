package org.secondopinion.patient.dto;

public class UserRequest {
	private ForUser forUser;

	private User user;

	public final ForUser getForUser() {
		return forUser;
	}

	public final void setForUser(ForUser forUser) {
		this.forUser = forUser;
	}

	public final User getUser() {
		return user;
	}

	public final void setUser(User user) {
		this.user = user;
	}

}
