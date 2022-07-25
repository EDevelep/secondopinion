package org.secondopinion.patient.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.patient.domain.BaseRelationship;

@Entity
@Table(name = "relationship")
public class Relationship extends BaseRelationship {

	public static Relationship buildSelfRelationship(Long userId,Long forUserId) {
		return build(userId, userId, RELATIONSHIP_TYPE.SELF, true);
	}
	public static Relationship buildAssocateSelfRelationship(Long userId,Long forUserId) {
		return build(userId, forUserId, RELATIONSHIP_TYPE.INPROGRESS, true);
	}

	
	public static Relationship build(Long userId, Long associateUserId, RELATIONSHIP_TYPE relationshipType,
			boolean primaryUser) {
		Relationship relationship = new Relationship();

		boolean isTrue = (RELATIONSHIP_TYPE.DOCTOR.equals(relationshipType)
				|| RELATIONSHIP_TYPE.SELF.equals(relationshipType)
				|| RELATIONSHIP_TYPE.NUTRITIONIST.equals(relationshipType)
				|| RELATIONSHIP_TYPE.CARETAKER.equals(relationshipType)
				|| RELATIONSHIP_TYPE.CARETAKER.equals(relationshipType));
		        

		relationship.setUserId(userId);
		relationship.setRelationUserId(associateUserId);
		relationship.setAccessToRecords(isTrue ? 'Y' : 'N');
		relationship.setAccessToPaymentDetails(isTrue ? 'Y' : 'N');
		relationship.setAccessToRecords(isTrue ? 'Y' : 'N');
		relationship.setAccessToPaymentDetails(isTrue ? 'Y' : 'N');
		relationship.setAccessToPrescription('Y');
		relationship.setAccessToInsurance('Y');
		relationship.setAddedbyUser('N');
		relationship.setAccessToAppoitment(isTrue ? 'Y' : 'N');
		relationship.setAccessToProfile(isTrue ? 'Y' : 'N');
		relationship.setAccessToVitals(isTrue ? 'Y' : 'N');
		relationship.setAccessToNotifaction(isTrue ? 'Y' : 'N');
		relationship.setAccessToPersonalDetails('Y');

		if (primaryUser) {
			relationship.setActive('Y');
			relationship.setApproved('N');
			relationship.setVerified('Y');
			relationship.setAddedbyUser('Y');
			relationship.setVerifiedOn(new Date());
		} else if (isTrue) {
			relationship.setActive('Y');
			relationship.setApproved('N');
			relationship.setVerified('Y');
			relationship.setVerifiedOn(new Date());
			relationship.setApprovedOn(new Date());
		} else {
			relationship.setActive('Y');
			relationship.setApproved('N');
			relationship.setVerified('N');
			relationship.setVerificationId("");
		}
		relationship.setRelationship(relationshipType.name());

		return relationship;
	}

	public void activate() {
		this.setAccessToRecords('Y');
		this.setAccessToPaymentDetails('Y');
		this.setVerified('Y');
		this.setApproved('Y');
		this.setAccessToRecords('Y');
		this.setAccessToPaymentDetails('Y');
		this.setAccessToPrescription('Y');
		this.setAccessToInsurance('Y');
		this.setAccessToPersonalDetails('Y');
		this.setAccessToAppoitment('Y');
		this.setAccessToProfile('Y');
		this.setAccessToVitals('Y');
		this.setAccessToNotifaction('Y');
		this.setApprovedOn(new Date());
		this.setActive('Y');
	}

	public void desactivate() {
		this.setAccessToRecords('N');
		this.setAccessToPaymentDetails('N');
		this.setVerified('N');
		this.setApproved('N');
		this.setAccessToRecords('N');
		this.setAccessToPaymentDetails('N');
		this.setAccessToPrescription('Y');
		this.setAccessToInsurance('Y');
		this.setAccessToPersonalDetails('N');
		this.setApprovedOn(new Date());
		this.setAccessToAppoitment('N');
		this.setAccessToProfile('N');
		this.setAccessToVitals('N');
		this.setAccessToNotifaction('N');
		this.setActive('N');
	}

	public static Relationship build(Long userId, Long associateUserId, RELATIONSHIP_TYPE relationshipType) {

		return build(userId, associateUserId, relationshipType, false);
	}

	public static Relationship buildAssocateUser(Relationship relationship, StatusType statusType) {
		// TODO Auto-generated method stub
		return null;
	}
	

}