package org.secondopinion.patient.service.impl;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.payment.gateway.razorpay.RazorPay;
import org.secondopinion.enums.AppointmentForEnum;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.patient.dao.AppointmentDAO;
import org.secondopinion.patient.dao.InvoiceDAO;
import org.secondopinion.patient.dao.RelationshipDAO;
import org.secondopinion.patient.dao.UserDAO;
import org.secondopinion.patient.dto.ACCESS_TYPE;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.patient.dto.AppointmentBookingDTO;
import org.secondopinion.patient.dto.AppointmentDTO;
import org.secondopinion.patient.dto.AppointmentDTOForDiagnosticcenter;
import org.secondopinion.patient.dto.AppointmentSearchRequest;
import org.secondopinion.patient.dto.FetchPayment;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.Invoice;
import org.secondopinion.patient.dto.InvoiceDTO;
import org.secondopinion.patient.dto.PatientPaymentDetails.TransactionStatusEnum;
import org.secondopinion.patient.dto.PaymentGatewayDTO;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.dto.UserDTO;
import org.secondopinion.patient.dto.ViewAppointments;
import org.secondopinion.patient.dto.ViewAppointments.ViewAppointmentEnum;
import org.secondopinion.patient.service.INotificationalertsService;
import org.secondopinion.patient.service.IPatientAppointmentService;
import org.secondopinion.patient.service.IPatientPaymentDetailsService;
import org.secondopinion.patient.service.rest.CareTakerRestAPIService;
import org.secondopinion.patient.service.rest.DiagnosticRestAPIService;
import org.secondopinion.patient.service.rest.DoctorRestAPIService;
import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.NotificationAlert;
import org.secondopinion.utils.VideoChatRoomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.videoroomotpphone.dto.RoomTokenResponse;

import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.video.v1.Room;
import com.twilio.rest.video.v1.room.RoomRecording;

@Service
public class PatientAppointmentServiceImpl implements IPatientAppointmentService {

	@Autowired
	private RelationshipDAO relationshipDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private AppointmentDAO appointmentDAO;
	@Autowired
	private DiagnosticRestAPIService dignosticRestAPIService;

	@Autowired
	private DoctorRestAPIService doctorRestAPIService;
	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private IPatientPaymentDetailsService patientPaymentDetailsService;

	@Autowired
	private INotificationalertsService notificationalertsService;

	@Autowired
	private DoctorRestAPIService doctorRestServiceAPI;

	@Autowired
	private DiagnosticRestAPIService diagnosticRestServiceAPI;
	@Autowired
	private CareTakerRestAPIService careTakerRestAPIService;

	@Autowired
	private InvoiceDAO invoiceDAO;
	@Autowired
	private PatientRatingServiceImpl patientRatingServiceImpl;

	@Autowired
	private RazorPaymentServiceImpl razorPaymentServiceImpl;

	@Override
	@Transactional
	public Appointment requestTobookAppointmentWithDoctor(AppointmentDTO appointmentdto,
			RELATIONSHIP_TYPE relationship_TYPE) {
		Appointment appointment = appointmentdto.getAppointment();
		FetchPayment fetchPayment = null;
		try {

			callRestAPItoBookAppointment(appointment);
			appointment.setCreatedDate(DateUtil.getDate());
			appointment.setActive('Y');
			appointmentDAO.save(appointment);
			Appointment dbapappointment = appointmentDAO.findById(appointment.getAppointmentId());

			fetchPayment = razorPaymentServiceImpl.fetchPaymentsByUsingPaymentId(appointmentdto);
			String status = fetchPayment.getStatus();

			if (TransactionStatusEnum.captured.name().equals(status)) {

				if (appointment.getAppointmentFor().equals(AppointmentForEnum.DOCTOR.name())) {
					Invoice invoice = Invoice.buildInvoiceForAppointment(dbapappointment, fetchPayment);
					InvoiceDTO invoiceDTO = doctorRestAPIService.createEntityInvoice(invoice);
					invoice.setEntityInvoiceId(invoiceDTO.getEntityInvoiceId());
					invoice.setActive('Y');
					invoiceDAO.save(invoice);
				}

				if (appointment.getAppointmentFor().equals(AppointmentForEnum.NUTRITIONIST.name())) {
					Invoice invoice = Invoice.buildInvoiceForAppointment(dbapappointment, fetchPayment);
					InvoiceDTO invoiceDTO = doctorRestAPIService.createEntityInvoice(invoice);
					invoice.setEntityInvoiceId(invoiceDTO.getEntityInvoiceId());
					invoice.setActive('Y');
					invoiceDAO.save(invoice);
				}

				if (appointment.getAppointmentFor().equals(AppointmentForEnum.CARETAKER.name())) {
					Invoice invoice = Invoice.buildInvoiceForAppointment(dbapappointment, fetchPayment);
					InvoiceDTO invoiceDTO = doctorRestAPIService.createEntityInvoice(invoice);
					invoice.setEntityInvoiceId(invoiceDTO.getEntityInvoiceId());
					invoice.setActive('Y');
					invoiceDAO.save(invoice);
				}

			} else if (TransactionStatusEnum.FAILED.name().equals("FAILED")) {
				NotificationAlert alert = new NotificationAlert(dbapappointment.getUserId(),
						dbapappointment.getAppointmentId(), "New Appointment",
						"Your appointment transaction has failed.");
				notificationalertsService.sendNotification(alert);
				throw new IllegalArgumentException("Transaction failed.");
			}
			AppointmentForEnum appointmentForEnum = AppointmentForEnum.valueOf(dbapappointment.getAppointmentFor());

			switch (appointmentForEnum) {
			case DOCTOR:
				NotificationAlert alert = new NotificationAlert(dbapappointment.getUserId(),
						dbapappointment.getAppointmentId(), "New Appointment",
						"Your appointment has been successfully placed With Dr  "
								+ dbapappointment.getReferenceEntityName());
				notificationalertsService.sendNotificationForAppointment(alert);
				break;
			case NUTRITIONIST:
				NotificationAlert alert1 = new NotificationAlert(dbapappointment.getUserId(),
						dbapappointment.getAppointmentId(), "New Appointment",
						"Your appointment has been successfully placed With NUTRITIONIST"
								+ dbapappointment.getReferenceEntityName());
				notificationalertsService.sendNotificationForAppointment(alert1);
				break;
			case CARETAKER:
				NotificationAlert alert2 = new NotificationAlert(dbapappointment.getUserId(),
						dbapappointment.getAppointmentId(), "New Appointment",
						"Your appointment has been successfully placed With NUTRITIONIST"
								+ appointment.getReferenceEntityName());
				notificationalertsService.sendNotificationForAppointment(alert2);
				break;
			default:
				break;
			}

		} catch (Exception e) {
			throw new IllegalArgumentException("  Appointment Field ");

		}
		return appointment;
	}

	@Override
	@Transactional
	public Appointment requestTobookAppointmentWithDiagnosticecnter(AppointmentDTOForDiagnosticcenter appointmentdto,
			RELATIONSHIP_TYPE relationship_TYPE) {

		
		List<Appointment> appointments=appointmentdto.getAppointment();
		
		callRestAPItoBookAppointmentWithdiagnosticcnetr(appointments);
		
		return null;
	}
	
	
	
	
	
	

	@Override
	public String buildInvoicePatientForAppointmentBooking(AppointmentBookingDTO appointment) {
		return null;

	}

	private void callRestAPItoBookAppointment(Appointment appointment) {
		if (Objects.isNull(appointment.getAppointmentId())) {
			RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf(appointment.getAppointmentFor());
			appointment.setCreatedDate(DateUtil.getDate());
			Relationship checkRelationship = relationshipDAO.checkRelationshipExists(appointment.getUserId(),
					appointment.getReferenceEntityId(), relationship_TYPE);
			if (appointment.getAppointmentFor().equals(AppointmentForEnum.DOCTOR.name())) {
				if (Objects.isNull(checkRelationship)) {

					relationshipDAO.save(Relationship.build(appointment.getUserId(), appointment.getReferenceEntityId(),
							RELATIONSHIP_TYPE.DOCTOR));
				}
				User user = userDAO.findOneByProperty(User.FIELD_userId, appointment.getUserId());
				String patientname = user.getFirstName() + " " + user.getLastName();
				appointment.setPatientName(patientname);
				doctorRestAPIService.createDoctorAppointmentForPatient(appointment);

			}

			if (appointment.getAppointmentFor().equals(AppointmentForEnum.NUTRITIONIST.name())) {
				if (Objects.isNull(checkRelationship)) {
					relationshipDAO.save(Relationship.build(appointment.getUserId(), appointment.getReferenceEntityId(),
							RELATIONSHIP_TYPE.NUTRITIONIST));
				}
				User user = userDAO.findOneByProperty(User.FIELD_userId, appointment.getUserId());
				String patientname = user.getFirstName() + " " + user.getLastName();
				appointment.setPatientName(patientname);
				doctorRestAPIService.createDoctorAppointmentForPatient(appointment);

			}

			if (appointment.getAppointmentFor().equals(AppointmentForEnum.CARETAKER.name())) {
				if (Objects.isNull(checkRelationship)) {
					relationshipDAO.save(Relationship.build(appointment.getUserId(), appointment.getReferenceEntityId(),
							RELATIONSHIP_TYPE.CARETAKER));
				}
				User user = userDAO.findOneByProperty(User.FIELD_userId, appointment.getUserId());
				String patientname = user.getFirstName() + " " + user.getLastName();
				appointment.setPatientName(patientname);
				careTakerRestAPIService.createCaretakerAppointmentForPatient(appointment);

			}
			if (appointment.getAppointmentFor().equals(AppointmentForEnum.DIAGNOSTIC_CENTER.name())) {
				if (Objects.isNull(checkRelationship)) {
					relationshipDAO.save(Relationship.build(appointment.getUserId(), appointment.getReferenceEntityId(),
							RELATIONSHIP_TYPE.DIAGNOSTIC_CENTER));
				}
				User user = userDAO.findOneByProperty(User.FIELD_userId, appointment.getUserId());
				String patientname = user.getFirstName() + " " + user.getLastName();
				appointment.setPatientName(patientname);
				dignosticRestAPIService.createDiagnosticCentreAppointmentForPatient(appointment);
			}

		}
	}
	
	
	@SuppressWarnings("unused")
	private void callRestAPItoBookAppointmentWithdiagnosticcnetr(List<Appointment> appointment) {
		
		
	}

	@Override
	@Transactional
	public void updateAppointmentUponDoctorAppointmentStatusTheRequest(Long referenceAppointmentId,
			Appointment appointment, String appointmentFor) {
		Appointment dbappointment = appointmentDAO.findAppointmentByReferenceAppointmentId(referenceAppointmentId,
				appointmentFor);

		String oldappointmentDate = DateUtil.convertDateFormat(dbappointment.getAppointmentDate(),
				Appointment.DATE_FORMAT);
		String oldfromtime = DateUtil.convertDateFormat(dbappointment.getFromTime(), Appointment.TIME_FORMAT);
		String oldtotime = DateUtil.convertDateFormat(dbappointment.getToTime(), Appointment.TIME_FORMAT);

		String olddateAndTime = "  " + oldappointmentDate + " " + oldfromtime + " To  " + oldtotime;
		// here we will get old time and and date
		if (Objects.isNull(dbappointment)) {
			throw new IllegalArgumentException("Appointment does not exists.");
		}
		dbappointment.setAppointmentStatus(appointment.getAppointmentStatus());
		dbappointment.setDescription(appointment.getDescription());
		dbappointment.setChatRoomName(appointment.getChatRoomName());
		dbappointment.setChatRoomSID(appointment.getChatRoomSID());
		dbappointment.setChatType(appointment.getChatType());
		dbappointment.setChatURL(appointment.getChatURL());
		dbappointment.setTokenForChat(null);
		dbappointment.setEntityAccepted('Y');
		dbappointment.setActive('Y');
		dbappointment.setSchedulehoursId(appointment.getSchedulehoursId());
		dbappointment.setAppointmentDate(appointment.getAppointmentDate());
		dbappointment.setFromTime(appointment.getFromTime());
		dbappointment.setToTime(appointment.getToTime());
		dbappointment.setSchedulehoursId(appointment.getSchedulehoursId());

		String appointmentDate = DateUtil.convertDateFormat(appointment.getAppointmentDate(), Appointment.DATE_FORMAT);
		String fromtime = DateUtil.convertDateFormat(appointment.getFromTime(), Appointment.TIME_FORMAT);
		String totime = DateUtil.convertDateFormat(appointment.getToTime(), Appointment.TIME_FORMAT);
		String newdateAndTime = " on " + appointmentDate + " " + fromtime + " To  " + totime;
		AppointmentStatusEnum appointmentStatus = AppointmentStatusEnum.valueOf(appointment.getAppointmentStatus());
		NotificationAlert alert = null;
		switch (appointmentStatus) {
		case ENTITY_ACCEPTED:
			alert = new NotificationAlert(appointment.getUserId(), appointment.getAppointmentId(),
					" Appointment Accepted ", "For" + newdateAndTime);
			notificationalertsService.sendNotification(alert);
			break;
		case ENTITY_CANCELLED:
			alert = new NotificationAlert(appointment.getUserId(), appointment.getAppointmentId(),
					"Appointment Cancelled", "Dated" + newdateAndTime);
			notificationalertsService.sendNotificationForCancleAppointment(alert);
			dbappointment.setEntityAccepted('N');
			break;
		case ENTITY_RESCHEDULED:
			alert = new NotificationAlert(appointment.getUserId(), appointment.getAppointmentId(),
					" Appointment Rescheduled", " from " + olddateAndTime + " to" + newdateAndTime);
			notificationalertsService.sendNotificationForReschudleAppointment(alert);
			break;
		default:
			break;
		}

		appointmentDAO.save(dbappointment);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> getAppointmentFor(Long refrenceEntityId, String appoitmentfor, Integer pageNum) {
		return appointmentDAO.getAppointmentFor(refrenceEntityId, appoitmentfor, pageNum);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> getAllAppointmentBySearchCritieria(
			AppointmentSearchRequest appointmentSearchRequest, ForUser forUser, RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = scheduleService.getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.APPOINTEMENT_DETAILS,
				relationship_TYPE);
		Response<List<Appointment>> appResponse = appointmentDAO
				.getAllAppointmentBySearchCritieria(appointmentSearchRequest, userId);
		return appResponse;
	}

	@Override
	@Transactional
	public Appointment joinroom(Long appointmentId) {

		Appointment appointment = appointmentDAO.findAppointmentIdById(appointmentId);
		if (Objects.isNull(appointment)) {
			throw new IllegalArgumentException("Appointment not found");
		}

		if (StringUtils.isEmpty(appointment.getChatRoomName()) || StringUtils.isEmpty(appointment.getChatRoomSID())) {
			throw new IllegalArgumentException("Please wait for the host to start the meeting.");
		}

		if (StringUtils.isEmpty(appointment.getTokenForChat())) {
			String chatIdentity = appointment.getPatientName() + "-" + appointment.getAppointmentId();// vishnu-16
			RoomTokenResponse roomResponse = VideoChatRoomUtil.createTwilioRoom(
					"Room-" + appointment.getReferenceAppointmentId(), // roomname,
					appointment.getStatusCallbackURL(), chatIdentity, appointment.getChatType());
			buildTwilioDetailsToAppointment(roomResponse, appointment);
			appointmentDAO.save(appointment);
		}

		return appointment;
	}

	private void buildTwilioDetailsToAppointment(RoomTokenResponse twilioRoomResponseDTO, Appointment appointment) {
		if (Objects.isNull(twilioRoomResponseDTO)) {
			return;
		}
		Room room = twilioRoomResponseDTO.getRoom();
		appointment.setChatURL(room.getUrl().toString());
		appointment.setChatRoomName(room.getUniqueName());
		appointment.setStatusCallbackURL(room.getStatusCallback().toString());
		appointment.setChatRoomSID(room.getSid());
		appointment.setTokenForChat(twilioRoomResponseDTO.getTokenForChat());
		appointment.setRoomType(room.getType().name());

	}

	@Override
	@Transactional
	public void patientRejectedTheRequest(Long appointmentId) {
		Appointment appointment = appointmentDAO.findById(appointmentId);
		if (Objects.isNull(appointment)) {
			throw new IllegalArgumentException("Appointment not found.");
		}
		appointment.setAppointmentStatus(AppointmentStatusEnum.PATIENT_CANCELLED.name());
		appointment.setTokenForChat(null);
		if (AppointmentForEnum.DOCTOR.name().equals(appointment.getAppointmentFor())) {
			doctorRestServiceAPI.updateDoctorAppointmentUponPatientRejectsTheAppointment(appointment);
		} else if (AppointmentForEnum.DIAGNOSTIC_CENTER.name().equals(appointment.getAppointmentFor())) {
			diagnosticRestServiceAPI.updateDoctorAppointmentUponPatientRejectsTheAppointment(appointment);
		}

		appointmentDAO.save(appointment);
	}

	@Override

	@Transactional
	public void patinetRescheduleTheRequest(Long appointmentId, Long schudlehourseId) {
		final String TIME_FORMAT = "HH:mm";
		Appointment appointment = appointmentDAO.findById(appointmentId);
		if (Objects.isNull(appointment)) {
			throw new IllegalArgumentException("Appointment not found.");
		}

		appointment.setAppointmentStatus(AppointmentStatusEnum.PATIENT_RESCHEDULED.name());
		appointment.setTokenForChat(null);
		appointmentDAO.save(appointment);
		doctorRestServiceAPI.updateDoctorAppointmentUponPatientRejectsTheAppointment(appointment);

		String appointmentDate = DateUtil.convertDateFormat(appointment.getAppointmentDate(), Appointment.DATE_FORMAT);
		String fromtime = DateUtil.convertDateFormat(appointment.getFromTime(), TIME_FORMAT);
		String totime = DateUtil.convertDateFormat(appointment.getToTime(), TIME_FORMAT);

		String newdateAndTime = "  " + appointmentDate + " " + fromtime + " To  " + totime;

		NotificationAlert alert = new NotificationAlert(appointment.getReferenceEntityId(),
				appointment.getAppointmentId(), " " + "  Appointment has been  Reschedule ", "For" + newdateAndTime);
		notificationalertsService.sendNotificationForAppointment(alert);

	}

	@Override
	public Call voiceChat(Long patientId) throws URISyntaxException {
		User user = userDAO.findById(patientId);
		if (Objects.isNull(user)) {
			throw new IllegalArgumentException("User not found");
		}
		if (StringUtils.isEmpty(user.getCellNumber())) {
			throw new IllegalArgumentException("Cell number can be null");
		}

		return VideoChatRoomUtil.voiceChat(user.getCellNumber());
	}

	@Override
	public List<RoomRecording> fetchRecording(Long patientId, String roomSID) {

		if (StringUtils.isEmpty(roomSID)) {
			throw new IllegalArgumentException("Cell number can not be null");
		}
		List<Appointment> appointments = appointmentDAO.getByPatientAndRoomSID(patientId, roomSID);
		if (CollectionUtils.isEmpty(appointments)) {
			throw new IllegalArgumentException("Please provide valid room SID, and user Id");
		}
		return VideoChatRoomUtil.fetchRecording(roomSID);
	}

	@Override
	@Transactional(readOnly = true)
	public Appointment getAppointmentById(Long appointmentId) {
		if (Objects.isNull(appointmentId)) {
			throw new IllegalArgumentException("AppointmentId can not be null.");
		}
		return appointmentDAO.findAppointmentIdById(appointmentId);
	}

	public Long getAssociateUserIdFromForUser(ForUser forUser, ACCESS_TYPE accessDetails,
			RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = null;
		if (userDAO.hasUserAccessToDetails(forUser.getUserId(), forUser.getForUserId(), accessDetails,
				relationship_TYPE)) {
			userId = forUser.getUserId();
		} else if (forUser.isCallForSelf()) {
			userId = forUser.getForUserId();
		}

		if (Objects.isNull(userId)) {
			throw new IllegalArgumentException("Invalid ForUser");
		}
		return userId;
	}

	@Override
	public Map<ViewAppointmentEnum, Response<List<Appointment>>> myAppointments(ViewAppointments viewAppointments) {
		Map<ViewAppointmentEnum, Response<List<Appointment>>> map = new HashMap<>();
		List<ViewAppointmentEnum> viewAppointmentEnums = viewAppointments.getViewAppointmentEnum();
		List<Long> doctorIds = new ArrayList<>();
		List<Appointment> appointments = new ArrayList<>();

		viewAppointmentEnums.stream().forEach(vae -> {
			switch (vae) {
			case CURRENT:
				map.put(ViewAppointmentEnum.CURRENT, appointmentDAO.currentAppointments(viewAppointments));
				break;

			case NO_SHOW:
				map.put(ViewAppointmentEnum.NO_SHOW, appointmentDAO.notAttendAppointments(viewAppointments));
				break;

			case PREVIOUS:
				map.put(ViewAppointmentEnum.PREVIOUS, appointmentDAO.previousAppointments(viewAppointments));
				break;
			case UPCOMING:
				map.put(ViewAppointmentEnum.UPCOMING, appointmentDAO.getupcomingAppointments(viewAppointments));
				break;
			case CANCELLED:
				map.put(ViewAppointmentEnum.CANCELLED, appointmentDAO.retrieveCancelledAppoitments(viewAppointments));
				break;
			case RESCHEDULED:
				map.put(ViewAppointmentEnum.RESCHEDULED,
						appointmentDAO.retrieveRescheduledAppoitments(viewAppointments));
				break;
			default:
				break;
			}

			/*
			 * if (ObjectUtils.isNotEmpty(map.get(vae).getData())) {
			 * doctorIds.addAll(map.get(vae).getData().stream().map(Appointment::
			 * getReferenceEntityId) .collect(Collectors.toList())); }
			 */

			// appointments.addAll(map.get(vae).getData());

			// List<Patientratings> patientrating =
			// patientRatingServiceImpl.getRatingsBydoctorIds(doctorIds);
			// List<Double> ratting =
			// patientrating.stream().map(Patientratings::getRating).collect(Collectors.toList());
			/// .stream().forEach(n -> n.setRatting(ratting));

		});

		return map;
	}

	@Override
	@Transactional
	public void updateAppointmentbyDiagnosticCenterUser(Long referenceEntityId, Appointment appointment,
			String appointmentFor) {

		Appointment dbappointment = Appointment.buildAppointment(referenceEntityId, appointment, appointmentFor);

		appointmentDAO.save(dbappointment);
		// after book appotment charge the appotment
		// chargeThePatientForAppointmentBooking(appointment);
	}

	@Override
	@Transactional
	public void rescheduleAppointmentForDiagnosticCenterUser(Long referenceAppointmentId, Appointment appointment,
			String appointmentFor) {
		Appointment dbAppointment = appointmentDAO.findAppointmentByReferenceAppointmentId(referenceAppointmentId,
				appointmentFor);
		if (Objects.isNull(dbAppointment)) {
			throw new IllegalArgumentException("Appointment  not found");
		}
		dbAppointment.setFromTime(appointment.getFromTime());
		dbAppointment.setFromTime(appointment.getToTime());
		dbAppointment.setAppointmentStatus(appointment.getAppointmentStatus());
		appointmentDAO.save(dbAppointment);

	}

	@Override
	@Transactional
	public void cancelAppointmentForDiagnosticCenterUser(Long referenceAppointmentId, Appointment appointment,
			String appointmentFor) {
		Appointment dbappointment = appointmentDAO.findAppointmentByReferenceAppointmentId(referenceAppointmentId,
				appointmentFor);
		if (Objects.isNull(dbappointment)) {
			throw new IllegalArgumentException("Appointment  not found");
		}
		dbappointment.setAppointmentStatus(appointment.getAppointmentStatus());
		dbappointment.setActive('N');
		appointmentDAO.save(dbappointment);

	}

	@Override
	@Transactional
	public List<UserDTO> getTotelPateint(Long refrenceEntityId) {
		List<Appointment> appointments = appointmentDAO.findTotelByReferenceAppointmentId(refrenceEntityId);
		if (Objects.isNull(appointments)) {
			throw new IllegalArgumentException("Appointment  not found");
		}
		List<Long> userids = new ArrayList<>();
		appointments.stream().forEach(n -> userids.add(n.getUserId()));
		List<UserDTO> user = userDAO.getUsers(userids);
		if (Objects.isNull(user)) {
			throw new IllegalArgumentException("User  not found");
		}
		return user;
	}

}
