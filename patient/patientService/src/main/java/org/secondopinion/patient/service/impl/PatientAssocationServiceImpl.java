package org.secondopinion.patient.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.secondopinion.patient.dao.RelationshipDAO;
import org.secondopinion.patient.dao.UserDAO;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.PatientRequest;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.patient.dto.StatusType;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.service.IPatientAssociation;
import org.secondopinion.patient.service.rest.DoctorRestAPIService;
import org.secondopinion.utils.EmailUtil;
import org.secondopinion.utils.MailProperties;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.EmailUtil.MailContentEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientAssocationServiceImpl implements IPatientAssociation {

	@Autowired
	private RelationshipDAO relationshipDAO;

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private DoctorRestAPIService doctorRestAPIService;

	@Value("${emailVerificationLink}")
	private String emailVerificationLink;

	@Autowired
	private MailProperties mailProperties;

	@Override
	@Transactional
	public void savePatientRequest(PatientRequest patientRequest, RELATIONSHIP_TYPE relationshiptType) {
		// step1 calling the doctor.patientrequest api

		doctorRestAPIService.createDoctorAssocationForPatient(patientRequest);
		// step 2 creating relationship
		Relationship relationship = relationshipDAO.checkRelationshipExists(patientRequest.getDoctorId(),
				patientRequest.getPatientId(), relationshiptType);
		if (Objects.isNull(relationship)) {
			relationship = Relationship.build(patientRequest.getPatientId(), patientRequest.getDoctorId(),
					RELATIONSHIP_TYPE.DOCTOR);
			relationshipDAO.save(relationship);
		}

	}

	@Override
	@Transactional
	public void updatePatientRequestUponDoctorRejections(Long doctorId, Long patientId,
			RELATIONSHIP_TYPE relationshiptType) {

		// step 1 updating relationship as inactive
		Relationship relationship = relationshipDAO.checkRelationshipExists(doctorId, patientId, relationshiptType);
		if (!Objects.isNull(relationship)) {
			relationship.setApproved('N');
			// relationship.setApprovedOn(null);
			relationship.setAccessToRecords('N');
			relationship.setAccessToPaymentDetails('N');
			relationship.setVerified('N');
			// relationship.setVerifiedOn(null);
			relationship.setAccessToRecords('N');
			relationship.setAccessToPaymentDetails('N');
			relationshipDAO.save(relationship);
		}

	}

	@Override
	public void updateRelationshipUponDoctorAccepts(Long doctorId, Long patientId,
			RELATIONSHIP_TYPE relationship_TYPE) {
		// step 1 updating relationship as inactive
		Relationship relationship = relationshipDAO.checkRelationshipExists(doctorId, patientId, relationship_TYPE);
		if (!Objects.isNull(relationship)) {
			relationship.setApproved('Y');
			// relationship.setApprovedOn(null);
			relationship.setAccessToRecords('Y');
			relationship.setAccessToPaymentDetails('Y');
			relationship.setVerified('Y');
			// relationship.setVerifiedOn(null);
			relationship.setAccessToRecords('Y');
			relationship.setAccessToPaymentDetails('Y');
			relationshipDAO.save(relationship);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Map<Long, String> getUserNameByUserIds(Set<Long> userIds) {
		return userDAO.getUserNameByUserIds(userIds);
	}

	@Override
	@Transactional
	public void deleteAssocateUser(Long userId, Long associateUserId, RELATIONSHIP_TYPE relationshiptType) {

		Relationship relationship = relationshipDAO.checkRelationshipExists(userId, associateUserId, relationshiptType);

		Relationship reverseRelationship = relationshipDAO.checkRelationshipExists(associateUserId, userId,
				relationshiptType);

		relationship.setActive('N');
		relationship.setApproved('N');
		reverseRelationship.setActive('N');
		reverseRelationship.setApproved('N');
		List<Relationship> relationships = new ArrayList<>();

		relationships.add(relationship);
		relationships.add(reverseRelationship);
		relationshipDAO.save(relationships);
	}

	@Override
	@Transactional
	public void updateAssocateUser(ForUser forUser, RELATIONSHIP_TYPE relationship) {

		Relationship forward = relationshipDAO.checkRelationshipExists(forUser.getUserId(), forUser.getForUserId());

		Relationship reverse = relationshipDAO.checkRelationshipExists(forUser.getForUserId(), forUser.getUserId());

		if (Objects.isNull(forward) || Objects.isNull(reverse)) {
			throw new IllegalArgumentException("Relationship either does not exist and an invalid relationship.");
		}

		forward.setRelationship(forUser.getRelationType().name());
		reverse.setRelationship(forUser.getRelationType().reverseRelation().name());

		List<Relationship> relationships = new ArrayList<>();

		relationships.add(forward);
		relationships.add(reverse);
		relationshipDAO.save(relationships);

		if (reverse.getVerified() == null || reverse.getVerified() == 'N') {
			User dbUser = userDAO.findUserByUserId(forUser.getForUserId());
			String name = dbUser.getFirstName() + " " + dbUser.getLastName();
			Map<String, String> model = new HashMap<>();
			/*
			 * model.put(MailContentEnum.NAME.name(), name);
			 * model.put(MailContentEnum.LINK.name(), String.format(emailVerificationLink,
			 * StatusType.VERIFIED.name(), dbUser.getEmailId()));
			 * model.put(MailContentEnum.LINK1.name(), String.format(emailVerificationLink,
			 * StatusType.REJECTED.name(), dbUser.getEmailId()));
			 */

			String classPathEmailTemplate = "classpath:accosateuser-emailverification.html";
			EmailUtil.sendAsscotedEmailWithHtmlContent(getMailProperties(dbUser), "CureMetric User association ",
					classPathEmailTemplate, model);
		}
	}

	@Override
	@Transactional
	public void addManagedUser(User user, ForUser forUser) {
		RELATIONSHIP_TYPE relationshipType = forUser.getRelationType();

		if (relationshipType == RELATIONSHIP_TYPE.DOCTOR || relationshipType == RELATIONSHIP_TYPE.DIAGNOSTIC_CENTER
				|| relationshipType == RELATIONSHIP_TYPE.HOSPITAL || relationshipType == RELATIONSHIP_TYPE.PATIENT
				|| relationshipType == RELATIONSHIP_TYPE.OTHER || relationshipType == RELATIONSHIP_TYPE.SELF
				|| relationshipType == RELATIONSHIP_TYPE.NUTRITIONIST) {
			throw new IllegalArgumentException("Invalid relationship!");
		}

		User dbuser = userDAO.findOneByProperty(User.FIELD_userId, forUser.getForUserId());
		dbuser.setAddedBy(user.getAddedBy());
		userDAO.save(dbuser);
		if (forUser.getUserId() == forUser.getForUserId()) {
			throw new IllegalArgumentException("ForUserId and UserId can not we Same!");
		}

		if (StringUtil.equalsIgnoreCase(forUser.getNewregister(), "Y")) {
			createRelationship(forUser, relationshipType, true, user.getRelationshipName());
		} else {
			createRelationship(forUser, relationshipType, false,user.getRelationshipName());

			User user1 = userDAO.findUserByUserId(forUser.getUserId());
			String username = user1.getFirstName() + " " + user1.getLastName();
			String name = user.getFirstName().toUpperCase() + " " + user.getLastName().toUpperCase();
			Map<String, String> model = new HashMap<>();
			model.put(MailContentEnum.NAME.name(), name);
			model.put(MailContentEnum.PartentUsername.name(), username);
			String classPathEmailTemplate = "classpath:accosateuser-emailverification.html";
			EmailUtil.sendAsscotedEmailWithHtmlContent(getMailProperties(user), "CureMetric User association ",
					classPathEmailTemplate, model);
		}

	}

	public MailProperties getMailProperties(User associateWithUser) {
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(associateWithUser.getEmailId());
		return properties;
	}

	private void createRelationship(ForUser forUser, RELATIONSHIP_TYPE relationshipType, boolean activateRelationship,String relationshipName) {

		Relationship relationship = Relationship.build(forUser.getUserId(), forUser.getForUserId(), relationshipType,
				true);
		relationship.setRelationshipName(relationshipName);
		Relationship relationship2 = Relationship.build(forUser.getForUserId(), forUser.getUserId(),
				relationshipType.reverseRelation(), false);
		relationship2.setRelationshipName(relationshipName);

		if (activateRelationship) {
			relationship.activate();
		}
		List<Relationship> relationships = new ArrayList<>();

		relationships.add(relationship);
		relationships.add(relationship2);

		relationshipDAO.save(relationships);

	}

}
