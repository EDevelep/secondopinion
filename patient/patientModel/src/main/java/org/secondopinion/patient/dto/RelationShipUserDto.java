package org.secondopinion.patient.dto;

import java.util.Date;

public class RelationShipUserDto {
	private RELATIONSHIP_TYPE relationshipType;
	private String emailid;
	private String name;
	private Long userid;

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserid() {
		return userid;
	}

	public RELATIONSHIP_TYPE getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(RELATIONSHIP_TYPE relationshipType) {
		this.relationshipType = relationshipType;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

}
