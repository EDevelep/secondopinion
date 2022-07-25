package org.secondopinion.patient.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.patient.domain.BaseAppointment;

@Entity
@Table(name = "appointment")
public class Appointment extends BaseAppointment {
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "HH:mm";
	private String prescriptionUploadDoc;
	private String medicalTestUploadDoc;

	private String orderId;

	@Transient
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Transient
	public String getPrescriptionUploadDoc() {
		return prescriptionUploadDoc;
	}

	public void setPrescriptionUploadDoc(String prescriptionUploadDoc) {
		this.prescriptionUploadDoc = prescriptionUploadDoc;
	}

	@Transient
	public String getMedicalTestUploadDoc() {
		return medicalTestUploadDoc;
	}

	public void setMedicalTestUploadDoc(String medicalTestUploadDoc) {
		this.medicalTestUploadDoc = medicalTestUploadDoc;
	}

	public static Appointment buildAppointment(Long referenceEntityId, Appointment appointment, String appointmentFor) {
		Appointment dbappointment = new Appointment();
		dbappointment.setAppointmentStatus(appointment.getAppointmentStatus());
		dbappointment.setDescription(appointment.getDescription());
		dbappointment.setChatRoomName(null);
		dbappointment.setChatRoomSID(null);
		dbappointment.setChatType(null);
		dbappointment.setChatURL(null);
		dbappointment.setTokenForChat(null);
		dbappointment.setEntityAccepted('Y');
		dbappointment.setActive('Y');
		dbappointment.setReferenceEntityId(referenceEntityId);// diagnosticCenterAddressId
		dbappointment.setReferenceAppointmentId(appointment.getAppointmentId());// diagnosticCenterappotmentId
		dbappointment.setReferenceEntityName(appointment.getReferenceEntityName());// dc name
		dbappointment.setAppointmentFor(appointmentFor);
		dbappointment.setSchedulehoursId(appointment.getSchedulehoursId());
		dbappointment.setAppointmentDate(appointment.getAppointmentDate());
		dbappointment.setFromTime(appointment.getFromTime());
		dbappointment.setToTime(appointment.getToTime());
		dbappointment.setSchedulehoursId(appointment.getSchedulehoursId());
		dbappointment.setUserId(appointment.getUserId());
		dbappointment.setPatientName(appointment.getReferenceEntityName());
		return appointment;

	}

}