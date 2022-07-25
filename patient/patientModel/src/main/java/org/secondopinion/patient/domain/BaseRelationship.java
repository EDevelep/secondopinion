package org.secondopinion.patient.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Relationship;

@MappedSuperclass
public abstract class BaseRelationship extends BaseDomainObject<Long> {

	public static final String FIELD_relationshipId = "relationshipId";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_relationUserId = "relationUserId";
	public static final String FIELD_relationship = "relationship";
	public static final String FIELD_verificationId = "verificationId";
	public static final String FIELD_verified = "verified";
	public static final String FIELD_verifiedOn = "verifiedOn";
	public static final String FIELD_approved = "approved";
	public static final String FIELD_approvedOn = "approvedOn";
	public static final String FIELD_accessToRecords = "accessToRecords";
	public static final String FIELD_accessToPaymentDetails = "accessToPaymentDetails";
	public static final String FIELD_accessToPrescription = "accessToPrescription";
	public static final String FIELD_accessToInsurance = "accessToInsurance";
	public static final String FIELD_accessToPersonalDetails = "accessToPersonalDetails";
	
	public static final String FIELD_accessToVitals = "accessToVitals";
	public static final String FIELD_accessToAppoitment = "accessToAppoitment";
	public static final String FIELD_accessToProfile = "accessToProfile";
	public static final String FIELD_accessToNotifaction = "accessToNotifaction";
	public static final String FIELD_active = "active";
	public static final String FIELD_addedbyUser="addedbyUser";
	public static final String FIELD_relationshipExit="relationshipExit";
	public static final String FIELD_relationshipName="relationshipName";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long relationshipId;
	private Long userId;
	private Long relationUserId;
	private String relationship;
	private String verificationId;
	private Character verified;
	private Date verifiedOn;
	private Character approved;
	private Date approvedOn;
	private Character accessToRecords;
	private Character accessToPaymentDetails;
	private Character accessToPrescription;
	private Character accessToInsurance;
	private Character accessToPersonalDetails;
	private Character active;
	private Character relationshipExit;
	private Character addedbyUser;
	private String relationshipName;
	private Character accessToVitals;
	private Character accessToAppoitment;
	private Character accessToProfile;
	private Character accessToNotifaction;
	
	public void setRelationshipId(Long _relationshipId) {
		this.relationshipId = _relationshipId;
	}

	@Id
	@Column(name = "relationshipId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getRelationshipId() {
		return this.relationshipId;
	}

	public void setUserId(Long _userId) {
		this.userId = _userId;
	}

	@NotNull
	@Column(name = "userId")
	public Long getUserId() {
		return this.userId;
	}

	public void setRelationUserId(Long _relationUserId) {
		this.relationUserId = _relationUserId;
	}

	@NotNull
	@Column(name = "relationUserId")
	public Long getRelationUserId() {
		return this.relationUserId;
	}

	public void setRelationship(String _relationship) {
		this.relationship = _relationship;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "relationship")
	public String getRelationship() {
		return this.relationship;
	}

	public void setVerificationId(String _verificationId) {
		this.verificationId = _verificationId;
	}

	@Length(max = 200)
	@Column(name = "verificationId")
	public String getVerificationId() {
		return this.verificationId;
	}

	public void setVerified(Character _verified) {
		this.verified = _verified;
	}

	@Column(name = "verified")
	public Character getVerified() {
		return this.verified;
	}

	public void setVerifiedOn(Date _verifiedOn) {
		this.verifiedOn = _verifiedOn;
	}

	@Column(name = "verifiedOn")
	public Date getVerifiedOn() {
		return this.verifiedOn;
	}

	public void setApproved(Character _approved) {
		this.approved = _approved;
	}

	@Column(name = "approved")
	public Character getApproved() {
		return this.approved;
	}

	public void setApprovedOn(Date _approvedOn) {
		this.approvedOn = _approvedOn;
	}

	@Column(name = "approvedOn")
	public Date getApprovedOn() {
		return this.approvedOn;
	}

	public void setAccessToRecords(Character _accessToRecords) {
		this.accessToRecords = _accessToRecords;
	}

	@NotNull
	@Column(name = "accessToRecords")
	public Character getAccessToRecords() {
		return this.accessToRecords;
	}

	public void setAccessToPaymentDetails(Character _accessToPaymentDetails) {
		this.accessToPaymentDetails = _accessToPaymentDetails;
	}
	@Column(name = "addedbyUser")
	public Character getAddedbyUser() {
		return addedbyUser;
	}

	public void setAddedbyUser(Character addedbyUser) {
		this.addedbyUser = addedbyUser;
	}

	@NotNull
	@Column(name = "accessToPaymentDetails")
	public Character getAccessToPaymentDetails() {
		return this.accessToPaymentDetails;
	}

	public void setAccessToPrescription(Character _accessToPrescription) {
		this.accessToPrescription = _accessToPrescription;
	}

	@NotNull
	@Column(name = "accessToPrescription")
	public Character getAccessToPrescription() {
		return this.accessToPrescription;
	}

	public void setAccessToInsurance(Character _accessToInsurance) {
		this.accessToInsurance = _accessToInsurance;
	}

	@NotNull
	@Column(name = "accessToInsurance")
	public Character getAccessToInsurance() {
		return this.accessToInsurance;
	}

	public void setAccessToPersonalDetails(Character _accessToPersonalDetails) {
		this.accessToPersonalDetails = _accessToPersonalDetails;
	}

	@NotNull
	@Column(name = "accessToPersonalDetails")
	public Character getAccessToPersonalDetails() {
		return this.accessToPersonalDetails;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}



	public Character getRelationshipExit() {
		return relationshipExit;
	}
	@Column(name = "relationshipExit")
	public void setRelationshipExit(Character relationshipExit) {
		this.relationshipExit = relationshipExit;
	}
	
	@Column(name = "relationshipName")
	public String getRelationshipName() {
		return relationshipName;
	}

	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}
	@Column(name = "accessToVitals")
	public Character getAccessToVitals() {
		return accessToVitals;
	}

	public void setAccessToVitals(Character accessToVitals) {
		this.accessToVitals = accessToVitals;
	}
	@Column(name = "accessToAppoitment")
	public Character getAccessToAppoitment() {
		return accessToAppoitment;
	}

	public void setAccessToAppoitment(Character accessToAppoitment) {
		this.accessToAppoitment = accessToAppoitment;
	}
	@Column(name = "accessToProfile")
	public Character getAccessToProfile() {
		return accessToProfile;
	}

	public void setAccessToProfile(Character accessToProfile) {
		this.accessToProfile = accessToProfile;
	}
	@Column(name = "accessToNotifaction")
	public Character getAccessToNotifaction() {
		return accessToNotifaction;
	}

	public void setAccessToNotifaction(Character accessToNotifaction) {
		this.accessToNotifaction = accessToNotifaction;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseRelationship other = (BaseRelationship) o;
		if (ObjectUtil.isEqual(getUserId(), other.getUserId())
				&& ObjectUtil.isEqual(getRelationUserId(), other.getRelationUserId())
				&& ObjectUtil.isEqual(getRelationship(), other.getRelationship())
				&& ObjectUtil.isEqual(getVerificationId(), other.getVerificationId())
				&& ObjectUtil.isEqual(getVerified(), other.getVerified())
				&& ObjectUtil.isEqual(getVerifiedOn(), other.getVerifiedOn())
				&& ObjectUtil.isEqual(getApproved(), other.getApproved())
				&& ObjectUtil.isEqual(getApprovedOn(), other.getApprovedOn())
				&& ObjectUtil.isEqual(getAccessToRecords(), other.getAccessToRecords())
				&& ObjectUtil.isEqual(getAccessToPaymentDetails(), other.getAccessToPaymentDetails())
				&& ObjectUtil.isEqual(getAccessToPrescription(), other.getAccessToPrescription())
				&& ObjectUtil.isEqual(getAccessToInsurance(), other.getAccessToInsurance())
				&& ObjectUtil.isEqual(getAccessToPersonalDetails(), other.getAccessToPersonalDetails())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy())
				&& ObjectUtil.isEqual(getAddedbyUser(), other.getAddedbyUser())
				&& ObjectUtil.isEqual(getRelationshipExit(), other.getRelationshipExit())
				&& ObjectUtil.isEqual(getRelationshipName(), other.getRelationshipName())
				&& ObjectUtil.isEqual(getAccessToAppoitment(), other.getAccessToAppoitment())
				&& ObjectUtil.isEqual(getAccessToProfile(), other.getAccessToProfile())
				&& ObjectUtil.isEqual(getAccessToNotifaction(), other.getAccessToNotifaction())
				&& ObjectUtil.isEqual(getCreatedDate(), other.getCreatedDate())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (relationshipId != null ? relationshipId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.relationshipId == null) {
				list.add(new ValidationMessage("Field " + FIELD_relationshipId + " cannot be null"));
			}

		}
		if (this.userId == null) {
			list.add(new ValidationMessage("Field " + FIELD_userId + " cannot be null"));
		}

		if (this.relationUserId == null) {
			list.add(new ValidationMessage("Field " + FIELD_relationUserId + " cannot be null"));
		}

		if (StringUtil.isNullOrEmpty(this.relationship)) {
			list.add(new ValidationMessage("Field " + FIELD_relationship + " cannot be null"));
		}

		if ((this.relationship != null) && (this.relationship.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_relationship + " cannot be longer than: " + 45));
		}

		if ((this.verificationId != null) && (this.verificationId.length() > 200)) {
			list.add(new ValidationMessage("Field " + FIELD_verificationId + " cannot be longer than: " + 200));
		}

		if (this.accessToRecords == null) {
			list.add(new ValidationMessage("Field " + FIELD_accessToRecords + " cannot be null"));
		}

		if (this.accessToPaymentDetails == null) {
			list.add(new ValidationMessage("Field " + FIELD_accessToPaymentDetails + " cannot be null"));
		}

		if (this.accessToPrescription == null) {
			list.add(new ValidationMessage("Field " + FIELD_accessToPrescription + " cannot be null"));
		}

		if (this.accessToInsurance == null) {
			list.add(new ValidationMessage("Field " + FIELD_accessToInsurance + " cannot be null"));
		}

		if (this.accessToPersonalDetails == null) {
			list.add(new ValidationMessage("Field " + FIELD_accessToPersonalDetails + " cannot be null"));
		}

		if ((this.createdBy != null) && (this.createdBy.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_createdBy + " cannot be longer than: " + 45));
		}

		if (this.isFromDB()) {
			if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 45)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 45));
			}
		}
		if (list.size() > 0)
			setHasValidationErrors(true);

		setValidationMessages(list);

	}

	@Override
	public final void setAuditFields() {
		if (!isFromDB()) {
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("relationshipId = " + relationshipId + "\n");
		;
		str.append("userId = " + userId + "\n");
		str.append("relationUserId = " + relationUserId + "\n");
		str.append("relationship = " + relationship + "\n");
		str.append("verificationId = " + verificationId + "\n");
		str.append("verified = " + verified + "\n");
		str.append("verifiedOn = " + verifiedOn + "\n");
		str.append("approved = " + approved + "\n");
		str.append("approvedOn = " + approvedOn + "\n");
		str.append("accessToRecords = " + accessToRecords + "\n");
		str.append("accessToPaymentDetails = " + accessToPaymentDetails + "\n");
		str.append("accessToPrescription = " + accessToPrescription + "\n");
		str.append("accessToInsurance = " + accessToInsurance + "\n");
		str.append("accessToAppoitment = " + accessToAppoitment + "\n");
		str.append("accessToProfile = " + accessToProfile + "\n");
		str.append("accessToVitals = " + accessToVitals + "\n");
		str.append("accessToNotifaction = " + accessToNotifaction + "\n");
		str.append("accessToPersonalDetails = " + accessToPersonalDetails + "\n");
		str.append("active = " + active + "\n");
		str.append("relationshipExit = " + relationshipExit + "\n");
		str.append("relationshipName = " + relationshipName + "\n");
		str.append("creartedby = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (relationshipId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_relationshipId, getRelationshipId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getRelationshipId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Relationship relationship = new Relationship();
		relationship.setFromDB(false);
		relationship.setUserId(getUserId());
		relationship.setRelationUserId(getRelationUserId());
		relationship.setRelationship(getRelationship());
		relationship.setVerificationId(getVerificationId());
		relationship.setVerified(getVerified());
		relationship.setVerifiedOn(getVerifiedOn());
		relationship.setApproved(getApproved());
		relationship.setApprovedOn(getApprovedOn());
		relationship.setAccessToRecords(getAccessToRecords());
		relationship.setAccessToPaymentDetails(getAccessToPaymentDetails());
		relationship.setAccessToPrescription(getAccessToPrescription());
		relationship.setAccessToInsurance(getAccessToInsurance());
		relationship.setAddedbyUser(getAddedbyUser());
		relationship.setRelationshipExit(getRelationshipExit());
		relationship.setAccessToPersonalDetails(getAccessToPersonalDetails());
		relationship.setActive(getActive());
		relationship.setRelationshipName(getRelationshipName());
		relationship.setAccessToVitals(getAccessToVitals());
		relationship.setAccessToAppoitment(getAccessToAppoitment());
		relationship.setAccessToNotifaction(getAccessToNotifaction());
		relationship.setAccessToProfile(getAccessToProfile());
		relationship.setCreatedBy(getCreatedBy());
		relationship.setCreatedDate(getCreatedDate());
		// afterClone(relationship);
		return relationship;
	}
}