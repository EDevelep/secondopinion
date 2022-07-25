package org.secondopinion.patient.service.rest;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.secondopinion.configurations.CustomRestTemlpateConfig;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.patient.dto.DoctorRatingDTO;
import org.secondopinion.patient.dto.Invoice;
import org.secondopinion.patient.dto.InvoiceDTO;
import org.secondopinion.patient.dto.PatientRatingsDTO;
import org.secondopinion.patient.dto.PatientRequest;
import org.secondopinion.utils.JSONHelper;
import org.secondopinion.utils.NotificationAlert;
import org.secondopinion.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class DoctorRestAPIService {

	@Value("${doctor.appointment.update}")
	private String updateDoctorAppointment;

	@Autowired
	private CustomRestTemlpateConfig customRestTemlpateConfig;

	@Value("${doctor.rating.saveurl}")
	private String doctorratingSaveURL;

	@Value("${doctor.get.rating}")
	private String doctorgetrating;

	@Value("${doctor.appointment.update.status.bypatient}")
	private String updateDoctorAppointmentByPatient;
	@Value("${doctor.appointment.update.status.bypatient1}")
	private String updateDoctorAppointmentByPatient1;
	@Value("${doctor.patientrequest.saveurl}")
	private String doctorPatientRequestSaveUrl;

	@Value("${doctor.appointment.saveurl}")
	private String doctorAppointmentSaveURL;

	@Value("${notificationalters.from.patient.to.doctor}")
	private String notifiationAlertsTotheDoctor;

	@Value("${doctor.appointment.delete}")
	private String deleteDoctorAppointmentIfPaymentFailed;

	@Value("${doctor.invoice.save}")
	private String createDoctorInvoice;

	@Value("${doctor.invoice.search}")
	private String myAppointmentInvoice;

	public void createDoctorAppointmentForPatient(Appointment appointment) {
		String jo = customRestTemlpateConfig
				.callRestAPI(appointment, doctorAppointmentSaveURL, HttpMethod.POST, String.class).getData();
		if (!StringUtil.isNullOrEmpty(jo)) {
			JSONObject gson = JSONHelper.buidJSONObject(jo);

			Long referenceAppointmentId = (Long) gson.get(Appointment.FIELD_appointmentId);
			appointment.setReferenceAppointmentId(referenceAppointmentId);

			String tokenForChat = (String) gson.get(Appointment.FIELD_tokenForChat);
			appointment.setTokenForChat(tokenForChat);

			String statusCallbackURL = (String) gson.get(Appointment.FIELD_statusCallbackURL);
			appointment.setStatusCallbackURL(statusCallbackURL);

			String videoChatRoomSID = (String) gson.get(Appointment.FIELD_chatRoomSID);
			appointment.setChatRoomSID(videoChatRoomSID);

			String videoChatRoomName = (String) gson.get(Appointment.FIELD_chatRoomName);
			appointment.setChatRoomName(videoChatRoomName);

			String videoURL = (String) gson.get(Appointment.FIELD_chatURL);
			appointment.setChatURL(videoURL);

			String roomType = (String) gson.get(Appointment.FIELD_roomType);
			appointment.setRoomType(roomType);

			appointment.setEntityAccepted('N');

			StringBuilder message = new StringBuilder();
			message.append("Your appointment has been successfully placed and ");
			String appointmentStatus = (String) gson.get(Appointment.FIELD_appointmentStatus);
			if (!appointmentStatus.equals(AppointmentStatusEnum.ENTITY_ACCEPTED.name())) {
				appointmentStatus = AppointmentStatusEnum.ENTITY_PENDING.name();
				message.append(" is pending to accept by " + appointment.getReferenceEntityName());
			} else {
				appointment.setEntityAccepted('Y');
				message.append(" is accepted by " + appointment.getReferenceEntityName());
			}
			message.append(appointment.getReferenceEntityName());
			appointment.setAppointmentStatus(appointmentStatus);

			appointment.setAppointmentId(null);
			appointment.setDescription(message.toString());

		}
	}

	public Long createDoctorAssocationForPatient(PatientRequest patientRequest) {
		String responseData = customRestTemlpateConfig
				.callRestAPI(patientRequest, doctorPatientRequestSaveUrl, HttpMethod.POST, String.class).getData();
		JSONObject jo = JSONHelper.buidJSONObject(responseData);
		return (Long) jo.get("patientRequestId");
	}

	public void notifiationAlertsTotheDoctor(NotificationAlert alertsUtilDTO) {
		customRestTemlpateConfig.callRestAPI(alertsUtilDTO, notifiationAlertsTotheDoctor, HttpMethod.POST, String.class)
				.getData();

	}

	public void updateDoctorAppointmentUponPatientRejectsTheAppointment(Appointment appointment) {

		String url = String.format(updateDoctorAppointmentByPatient, appointment.getReferenceAppointmentId(),
				appointment.getAppointmentStatus(), appointment.getSchedulehoursId());
		customRestTemlpateConfig.callRestAPI(url, HttpMethod.POST, String.class).getData();
	}

	public void updateDoctorAppointmentUponPatientRejectsTheAppointment1(Appointment appointment) {

		String url = String.format(updateDoctorAppointmentByPatient1, appointment.getReferenceAppointmentId(),
				appointment.getAppointmentStatus(), appointment.getSchedulehoursId());
		customRestTemlpateConfig.callRestAPI(url, HttpMethod.POST, String.class).getData();
	}

	public void deleteDoctorAppointmentIfPaymentFailed(Long referenceAppointmentId) {
		String url = String.format(deleteDoctorAppointmentIfPaymentFailed, referenceAppointmentId);
		customRestTemlpateConfig.callRestAPI(url, HttpMethod.DELETE, String.class);
	}

	// myAppointmentInvoice
	public JSONObject myAppointmentInvoice(InvoiceDTO invoiceDTO) {
		String responseData = customRestTemlpateConfig
				.callRestAPI(invoiceDTO, myAppointmentInvoice, HttpMethod.POST, String.class).getData();
		JSONObject jo = JSONHelper.buidJSONObject(responseData);
		return jo;
	}

	public void ratingServiceForDoctor(PatientRatingsDTO patientratingsdto) {
		customRestTemlpateConfig.callRestAPI(patientratingsdto, doctorratingSaveURL, HttpMethod.POST, String.class)
				.getData();

	}

	// delete doctor appointment
	public void deleteDoctorAppointment(Long referenceAppointmentId) {
		String url = String.format(deleteDoctorAppointmentIfPaymentFailed, referenceAppointmentId);
		customRestTemlpateConfig.callRestAPI(url, HttpMethod.DELETE, String.class);
	}

	public void updateDoctorAppointment(Appointment appointment) {
		customRestTemlpateConfig.callRestAPI(appointment, updateDoctorAppointment, HttpMethod.PUT, String.class);
	}

	public InvoiceDTO createEntityInvoice(Invoice invoice) {
		InvoiceDTO invoiceDTO = InvoiceDTO.buildInvoiceForDoctor(invoice);
		String jo = customRestTemlpateConfig.callRestAPI(invoiceDTO, createDoctorInvoice, HttpMethod.POST, String.class)
				.getData();

		JSONObject gson = JSONHelper.buidJSONObject(jo);
		invoiceDTO.setEntityInvoiceId((Long) gson.get(Invoice.FIELD_invoiceId));
		return invoiceDTO;
	}

	public List<DoctorRatingDTO> getDoctorRating(List<Long> doctorId) {
		List<DoctorRatingDTO> doctorRatingDTO = new ArrayList<>();
		String uri = String.format(doctorgetrating, doctorId);
		String responseData = customRestTemlpateConfig.callRestAPI(uri, HttpMethod.POST, String.class).getData();
		JSONObject jo = JSONHelper.buidJSONObject(responseData);
		
		return doctorRatingDTO;
	}
}
