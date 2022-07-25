package org.secondopinion.patient.dto;

import java.util.List;

public class UserRelations {
	private List<RelationShipDto> associatedUsers;
	private List<RelationShipDto> reverseAssociatedUsers;

	public List<RelationShipDto> getAssociatedUsers() {
		return associatedUsers;
	}

	public void setAssociatedUsers(List<RelationShipDto> associatedUsers) {
		this.associatedUsers = associatedUsers;
	}

	public List<RelationShipDto> getReverseAssociatedUsers() {
		return reverseAssociatedUsers;
	}

	public void setReverseAssociatedUsers(List<RelationShipDto> reverseAssociatedUsers) {
		this.reverseAssociatedUsers = reverseAssociatedUsers;
	}

}
