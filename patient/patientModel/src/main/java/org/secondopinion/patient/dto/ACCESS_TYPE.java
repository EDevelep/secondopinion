package org.secondopinion.patient.dto;

import org.secondopinion.utils.ObjectUtil;

public enum ACCESS_TYPE {
	MEDICAL_RECORDS, PERSONAL_DETAILS, PRESCRIPTION_DETAILS, VITALS, PAYMENT_DETAILS, INSURANCE_DETAILS,
	APPOINTEMENT_DETAILS;

	public static boolean hasAccess(ACCESS_TYPE accessType, Relationship relationship) {
		switch (accessType) {
		case MEDICAL_RECORDS:
			return ObjectUtil.isEqual('Y', relationship.getAccessToRecords());
		case PERSONAL_DETAILS:
			return ObjectUtil.isEqual('Y', relationship.getAccessToPersonalDetails());
		case PRESCRIPTION_DETAILS:
			return ObjectUtil.isEqual('Y', relationship.getAccessToPrescription());
		case VITALS:
			return ObjectUtil.isEqual('Y', relationship.getAccessToPersonalDetails());
		case PAYMENT_DETAILS:
			return ObjectUtil.isEqual('Y', relationship.getAccessToPaymentDetails());
		case INSURANCE_DETAILS:
			return ObjectUtil.isEqual('Y', relationship.getAccessToInsurance());
		case APPOINTEMENT_DETAILS:
			return ObjectUtil.isEqual('Y', relationship.getAccessToRecords());
		default:
			break;
		}

		return false;
	}
}
