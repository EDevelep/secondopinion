package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.patient.domain.BaseRelationship;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.RelationShipDto;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class RelationshipDAOHibernateImpl extends BaseRelationshipDAOHibernate {

	private static final String USER_RELATION_DETAILS = "select a.userId, a.relationUserId,a.relationshipId,a.relationship, b.firstName, b.lastName, b.middleName, b.userName,b.emailId, a.verified, a.verifiedOn, a.approved, a.approvedOn, a.active,b.cellNumber,b.addedBy , a.addedbyUser "
			+ "from relationship a, user b where a.userId = :USER_ID   and relationship in ('RELATIVE',  'PARENT', 'CHILD', 'FRIEND','INPROGRESS','SIBLING') and b.userId = a.relationUserId and addedbyUser = 'Y';";
	
	private static final String REVERSE_USER_RELATION_DETAILS = "select a.userId, a.relationUserId,a.relationshipId, a.addedbyUser, a.relationship, b.firstName, b.lastName, b.middleName, b.userName,b.emailId, a.verified, a.verifiedOn, a.approved, a.approvedOn, a.active, b.cellNumber,b.addedBy  \r\n"
			+ "from relationship a, user b where a.relationUserId = :USER_ID   and relationship in ('RELATIVE',  'PARENT', 'CHILD', 'FRIEND','INPROGRESS','SIBLING') and b.userId = a.userId and addedbyUser = 'Y';";
			

	@Override
	@Transactional
	public void save(Relationship relationship) throws DataAccessException {
		if (Objects.isNull(relationship.getRelationshipId())) {
			super.save(relationship);
		}

	}

	@Override
	public List<Long> getNewRelationshipsUserIds(Long userId) {

		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseRelationship.FIELD_userId, userId));
		criteria.add(Restrictions.or(Restrictions.eq(BaseRelationship.FIELD_approved, 'N'),
				Restrictions.eq(BaseRelationship.FIELD_approved, 'N')));

		List<Relationship> relations = findByCrieria(criteria);

		List<Long> ids = new ArrayList<>();

		if (relations != null && relations.isEmpty()) {
			relations.stream().forEach(n -> ids.add(n.getRelationUserId()));
		}

		return ids;
	}

	@Override
	public HashMap<Long, String> getRelationshipsUserIds(Long userid) {
		List<Criterion> criteria = new ArrayList<>();

		criteria.add(Restrictions.eq(BaseRelationship.FIELD_userId, userid));
		criteria.add(Restrictions.ne(BaseRelationship.FIELD_relationship, RELATIONSHIP_TYPE.HOSPITAL.name()));
		criteria.add(Restrictions.ne(BaseRelationship.FIELD_relationship, RELATIONSHIP_TYPE.DIAGNOSTIC_CENTER.name()));
		criteria.add(Restrictions.ne(BaseRelationship.FIELD_relationship, RELATIONSHIP_TYPE.SELF.name()));
		criteria.add(Restrictions.ne(BaseRelationship.FIELD_relationship, RELATIONSHIP_TYPE.DOCTOR.name()));
		List<Relationship> relations = findByCrieria(criteria);

		HashMap<Long, String> hs = new HashMap<Long, String>();

		if (relations != null) {
			relations.stream().forEach(n -> hs.put(n.getRelationUserId(), n.getRelationship()));
		}

		return hs;
	}


	@Override
	@Transactional(readOnly = true)
	public Relationship checkRelationshipExists(Long userId, Long relationUserId,RELATIONSHIP_TYPE relationship_TYPE) {
		
		if(relationship_TYPE == RELATIONSHIP_TYPE.PATIENT) {
			return checkRelationshipExists(userId, relationUserId);
		}

		
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseRelationship.FIELD_userId, userId));
		criteria.add(Restrictions.eq(BaseRelationship.FIELD_relationUserId, relationUserId));
		criteria.add(Restrictions.eq(BaseRelationship.FIELD_relationship, relationship_TYPE.toString()));
		List<Relationship> relations = findByCrieria(criteria);

		if (CollectionUtils.isEmpty(relations)) {
			return null;
		}

		return relations.get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public Relationship checkRelationshipExists(Long userId, Long reationUserId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseRelationship.FIELD_userId, userId));
		criteria.add(Restrictions.eq(BaseRelationship.FIELD_relationUserId, reationUserId));
		criteria.add(Restrictions.in(BaseRelationship.FIELD_relationship, RELATIONSHIP_TYPE.getPatientRelations()));
		List<Relationship> relations = findByCrieria(criteria);

		if (CollectionUtils.isEmpty(relations)) {
			return null;
		}

		return relations.get(0);
	}

	//TODO: REMOVE THIS METHOD! WRONG implementation
	@Override
	public Response<List<Relationship>> getRelationships(Long userid, Long assocteuserid, Integer pageNum,
			Integer maxResults) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseRelationship.FIELD_userId, userid));
		criteria.add(Restrictions.ne(BaseRelationship.FIELD_relationship, RELATIONSHIP_TYPE.HOSPITAL.name()));
		criteria.add(Restrictions.ne(BaseRelationship.FIELD_relationship, RELATIONSHIP_TYPE.DIAGNOSTIC_CENTER.name()));
		criteria.add(Restrictions.ne(BaseRelationship.FIELD_relationship, RELATIONSHIP_TYPE.SELF.name()));
		criteria.add(Restrictions.ne(BaseRelationship.FIELD_relationship, RELATIONSHIP_TYPE.DOCTOR.name()));
		return findByCrieria(criteria, null, pageNum, maxResults);

	}

	@Transactional
	public List<RelationShipDto> allAssociateUser(Long userId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("USER_ID", userId);
		List<RelationShipDto> relationship = findBySqlQuery(USER_RELATION_DETAILS, params, RelationShipDto.class);

		return relationship;

	}
	
	@Transactional
	public List<RelationShipDto> allReverseAssociateUsers(Long userId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("USER_ID", userId);
		List<RelationShipDto> relationship = findBySqlQuery(REVERSE_USER_RELATION_DETAILS, params, RelationShipDto.class);

		return relationship;

	}
}