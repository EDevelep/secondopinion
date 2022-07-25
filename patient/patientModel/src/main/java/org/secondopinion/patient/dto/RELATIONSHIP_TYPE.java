package org.secondopinion.patient.dto;

public enum RELATIONSHIP_TYPE {
	DOCTOR, DIAGNOSTIC_CENTER, HOSPITAL, PARENT, CHILD, RELATIVE, SELF, PATIENT, FRIEND, OTHER, NUTRITIONIST, CARETAKER,
	INPROGRESS, SIBLING;

	public RELATIONSHIP_TYPE reverseRelation() {

		switch (this) {
		case DOCTOR:

			return PATIENT;

		case DIAGNOSTIC_CENTER:

			return PATIENT;

		case HOSPITAL:

			return PATIENT;

		case PARENT:

			return CHILD;

		case CHILD:

			return PARENT;

		case RELATIVE:

			return RELATIVE;

		case SELF:

			return SELF;

		case PATIENT:

			return SELF;

		case FRIEND:

			return FRIEND;

		case OTHER:

			return OTHER;
		case NUTRITIONIST:

			return PATIENT;

		case CARETAKER:

			return PATIENT;
			
			case SIBLING:
			
			return SIBLING;
			
		default:
			throw new IllegalArgumentException("Invalid relationship type");
		}
	}

	public static Object[] getPatientRelations() {

		return new Object[] { PARENT.name(), CHILD.name(), RELATIVE.name(), SELF.name(), PATIENT.name(), FRIEND.name(),
				OTHER.name() };
	}
}
