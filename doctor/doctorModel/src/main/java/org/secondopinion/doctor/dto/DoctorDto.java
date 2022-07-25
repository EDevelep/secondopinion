package org.secondopinion.doctor.dto;

public class DoctorDto {

	private Feedetails feedetails;
	private Appointment appointment;

	public Feedetails getFeedetails() {
		return feedetails;
	}

	public void setFeedetails(Feedetails feedetails) {
		this.feedetails = feedetails;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public static String getFieldDoctorid() {
		return FIELD_doctorId;
	}

	public static String getFieldFirstname() {
		return FIELD_firstName;
	}

	public static String getFieldLastname() {
		return FIELD_lastName;
	}

	public static String getFieldMiddlename() {
		return FIELD_middleName;
	}

	public static String getFieldHighestdegree() {
		return FIELD_highestDegree;
	}

	public static String getFieldSpecialization() {
		return FIELD_specialization;
	}

	public static String getFieldUserid() {
		return FIELD_userId;
	}

	public static String getFieldPassword() {
		return FIELD_password;
	}

	public static String getFieldLastlogin() {
		return FIELD_lastLogin;
	}

	public static String getFieldEmergencycontactnumber() {
		return FIELD_emergencyContactNumber;
	}

	public static String getFieldCellnumber() {
		return FIELD_cellNumber;
	}

	public static String getFieldNationality() {
		return FIELD_nationality;
	}

	public static String getFieldEthinicity() {
		return FIELD_ethinicity;
	}

	public static String getFieldCreatedby() {
		return FIELD_createdBy;
	}

	public static String getFieldCreatedate() {
		return FIELD_createDate;
	}

	public static String getFieldLastupdatedby() {
		return FIELD_lastUpdatedBy;
	}

	public static String getFieldlastUpdatedTs() {
		return FIELD_lastUpdatedTs;
	}

	public static final String FIELD_doctorId = "doctorId";
	public static final String FIELD_firstName = "firstName";
	public static final String FIELD_lastName = "lastName";
	public static final String FIELD_middleName = "middleName";
	public static final String FIELD_highestDegree = "highestDegree";
	public static final String FIELD_specialization = "specialization";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_password = "password";
	public static final String FIELD_lastLogin = "lastLogin";
	public static final String FIELD_emergencyContactNumber = "emergencyContactNumber";
	public static final String FIELD_cellNumber = "cellNumber";
	public static final String FIELD_nationality = "nationality";
	public static final String FIELD_ethinicity = "ethinicity";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createDate = "createDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

}
