package org.secondopinion.patient.service.rest;

import org.json.simple.JSONObject;
import org.secondopinion.configurations.CustomRestTemlpateConfig;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.patient.dto.Appointment;
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
public class CareTakerRestAPIService {


	@Autowired
	private CustomRestTemlpateConfig customRestTemlpateConfig;

	@Value("${caretaker.appointment.saveurl}")
	private String caretakerAppointmentSaveURL;

	@Value("${caretaker.invoice.save}")
	private String createDoctorInvoice;

	public void createCaretakerAppointmentForPatient(Appointment appointment) {
		String jo = customRestTemlpateConfig
				.callRestAPI(appointment, caretakerAppointmentSaveURL, HttpMethod.POST, String.class).getData();
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

	
	public InvoiceDTO createEntityInvoice(Invoice invoice) {
		InvoiceDTO invoiceDTO = InvoiceDTO.buildInvoiceForCareTaker((invoice));
		String jo = customRestTemlpateConfig.callRestAPI(invoiceDTO, createDoctorInvoice, HttpMethod.POST, String.class)
				.getData();

		JSONObject gson = JSONHelper.buidJSONObject(jo);
		invoiceDTO.setEntityInvoiceId((Long) gson.get(Invoice.FIELD_invoiceId));
		return invoiceDTO;
	}
}
