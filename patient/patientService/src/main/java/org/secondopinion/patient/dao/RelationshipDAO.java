package org.secondopinion.patient.dao;

import java.util.HashMap;
import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.RelationShipDto;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.request.Response;

public interface RelationshipDAO extends IDAO<Relationship, Long> {

	List<Long> getNewRelationshipsUserIds(Long userId);

//	Relationship getRelationship(Long userId, Long userIdToApprove,RELATIONSHIP_TYPE relationship_TYPE);

	Relationship checkRelationshipExists(Long reationUserId, Long userId,	RELATIONSHIP_TYPE relationship_TYPE);

	List<RelationShipDto> allAssociateUser(Long userIds);
	
	List<RelationShipDto> allReverseAssociateUsers(Long userIds);

	HashMap<Long, String> getRelationshipsUserIds(Long userid);

	Response<List<Relationship>> getRelationships(Long userid, Long assocteuserid, Integer pageNum, Integer maxResults);

	Relationship checkRelationshipExists(Long reationUserId, Long forUserId);

}