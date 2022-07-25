package org.secondopinion.request;

import java.util.Set;

public class Request {
	private String userSession;
	private String data;
	private Set<Long> userIds;

	public String getUserSession() {
		return userSession;
	}

	public void setUserSession(String userSession) {
		this.userSession = userSession;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Request [userSession=" + userSession + ", data=" + data + "]";
	}

	public Set<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(Set<Long> userIds) {
		this.userIds = userIds;
	}
	
	
}
