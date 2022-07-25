
package org.secondopnion.patient.service;

import static org.junit.Assert.assertNotNull;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.junit.Test;
import org.secondopinion.enums.AppointmentForEnum;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.patient.dto.AppointmentBookingDTO;
import org.secondopinion.patient.dto.AppointmentDTO;
import org.secondopinion.patient.dto.AppointmentSearchRequest;
import org.secondopinion.patient.dto.Carddetails;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.PatientPaymentDetails;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.PatientPaymentDetails.TransactionTypeEnum;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.dto.UserDTO;
import org.secondopinion.patient.dto.ViewAppointments;
import org.secondopinion.patient.dto.ViewAppointments.ViewAppointmentEnum;
import org.secondopinion.patient.service.IPatientAppointmentService;
import org.secondopinion.patient.service.impl.PatientAppointmentServiceImpl;
import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
import org.secondopnion.patient.PatientApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientAppointmentServiceImplTest extends PatientApplicationTest {

	@Autowired
	private IPatientAppointmentService appointmentPatientService;

	@Autowired
	private PatientAppointmentServiceImpl patientAppointmentServiceImpl;

	@Test
	public void testRequestTobookAppointmentWithDoctor() {
		Appointment appointment = new Appointment();
		Long patientId = 32L;
		Long doctorId = 2L;
		Long feeDetailsId = 12L;
		Double amountPaid = 700d;
		Long scheduleHoursId = 60L;

		appointment.setAmountPaid(amountPaid);
		appointment.setAilment("heart");
		appointment.setEntityAccepted('Y');
		appointment.setAppointmentStatus(AppointmentStatusEnum.NEW.name());
		appointment.setAppointmentDate(DateUtil.getCurrentDateOnly(TimeZone.getDefault()));
		appointment.setAppointmentFor(AppointmentForEnum.DOCTOR.name());
		appointment.setChatType("VIDEO_CHAT");
		appointment.setDescription("heart");
		appointment.setReferenceEntityId(doctorId);
		appointment.setReferenceEntityName(" jatin kumar sharma ");
		appointment.setReferenceAppointmentId(doctorId);
		appointment.setPatientName("sharma jatin");
		appointment.setSchedulehoursId(scheduleHoursId);
		appointment.setFromTime(DateUtil.convertLocalTimeToDate(LocalTime.now()));
		appointment.setToTime(DateUtil.convertLocalTimeToDate(LocalTime.now()));
		appointment.setUserId(patientId);
		appointment.setStatusCallbackURL("http://localhost:4200");
		// appointment.setGetWayType("RAZOR_PAY");

		Carddetails cardDetails = new Carddetails();
		cardDetails.setCardbankname("hdfc");
		cardDetails.setCardnumber("4242424242424242");
		cardDetails.setCardtype("DEBIT_CARD");
		cardDetails.setCvv("123");
		cardDetails.setExpmonth(11L);
		cardDetails.setExpyear(2029L);
		cardDetails.setSaveCardDetails(true);
		cardDetails.setUserId(patientId);
		cardDetails.setUsername("ramanujaajay");

		PatientPaymentDetails patientPaymentDetails = new PatientPaymentDetails();
		patientPaymentDetails.setAmountPaid(amountPaid);
		patientPaymentDetails.setCurrencyType("INR");
		patientPaymentDetails.setDescription("heart");
		patientPaymentDetails.setFee(amountPaid);
		patientPaymentDetails.setFeeType("Consultation");
		patientPaymentDetails.setFeedetailsId(feeDetailsId);
		patientPaymentDetails.setPatientId(patientId);
		patientPaymentDetails.setPaymentThrough("hdfc");
		patientPaymentDetails.setStripeCustomerEmail("vishnu@qontrack.com");
		patientPaymentDetails.setStripeCustomerName("vishnu");
		patientPaymentDetails.setTransactionType(TransactionTypeEnum.DEBITCARD.name());
		//patientPaymentDetails.setCarddetails(cardDetails);
		ForUser forUser = new ForUser();
		forUser.setUserId(1L);
		forUser.setForUserId(3L);
		// appointment.setPatientPaymentDetails(patientPaymentDetails);
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setAppointment(appointment);
		// patientAppointmentServiceImpl.chargeThePatientForAppointmentBooking(appointment);
		assertNotNull(appointment);
	}

	@Test
	public void testUpdateAppointmentUponDoctorAcceptedTheRequest() {
		String timings = "09:00-09:30, 12:00-12:30 ,17:00-17:30";
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(new Date());
		appointment.setAppointmentFor("Mahesh");
		appointment.setFromTime(DateUtil.getDate());
		appointment.setToTime(DateUtil.getDate());
		appointment.setDescription("morning LocalTime");
		appointment.setUserId(4L);
		appointment.setSchedulehoursId(4362L); //
		appointment.setReferenceAppointmentId(12L);
		appointment.setChatRoomName("zoom");
		appointment.setAppointmentId(74L);
		appointment.setReferenceEntityName("Suresh Yadhav");
		appointment.setAppointmentStatus(AppointmentStatusEnum.ENTITY_RESCHEDULED.name());
		appointment.setChatURL("http://zoom.com");
		appointmentPatientService.updateAppointmentUponDoctorAppointmentStatusTheRequest(12L, appointment,
				AppointmentForEnum.DOCTOR.name());
	}

	@Test
	public void testUpdateAppointmentUponDoctorRejectedTheRequest() {
		String timings = "09:00-09:30, 12:00-12:30 ,17:00-17:30";
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(new Date());
		appointment.setCreatedDate(new Date());
		appointment.setCreatedBy("vishnu");
		appointment.setLastUpdatedTs(new Date());
		appointment.setAppointmentFor("Mahesh");
		appointment.setDescription("morning LocalTime");
		appointment.setUserId(7L);
		appointment.setReferenceAppointmentId(74L);
		appointment.setChatRoomName("zoom");
		appointment.setChatURL("http://zoom.com");
		appointmentPatientService.updateAppointmentUponDoctorAppointmentStatusTheRequest(12L, appointment,
				AppointmentForEnum.DOCTOR.name());
	}


	@Test
	public void buildInvoicePatientForAppointmentBooking() {

		ForUser forUser = new ForUser();
		forUser.setForUserId(141L);
		forUser.setUserId(141L);
	AppointmentBookingDTO appointmentBookingDTO=new AppointmentBookingDTO();
	  appointmentBookingDTO.setAmountPaid(500.00);
	  appointmentBookingDTO.setAppointmentFor("DOCTOR");
	  appointmentBookingDTO.setAppointmentId(1L);
	  appointmentBookingDTO.setPaymentId("pay_JoSKi8DjrQ4qVl");
		appointmentPatientService.buildInvoicePatientForAppointmentBooking(appointmentBookingDTO);
	}

	@Test
	public void testUpdateAppointmentUponDoctorRejectedTheRequest1() {

		ForUser forUser = new ForUser();
		forUser.setForUserId(141L);
		forUser.setUserId(141L);
		AppointmentSearchRequest appointmentSearchRequest = new AppointmentSearchRequest();
		appointmentSearchRequest.setFromDate(DateUtil.getMinDateTimeOfDate(DateUtil.getDate(TimeZone.getDefault())));
		appointmentSearchRequest.setToDate(DateUtil.getMaxDateTimeOfDate(DateUtil.getDate(TimeZone.getDefault())));
		appointmentSearchRequest.setAppointmentFor("DOCTOR");
		appointmentSearchRequest.setReferenceEntityName("Jatin");
		appointmentPatientService.getAllAppointmentBySearchCritieria(appointmentSearchRequest, forUser, null);
	}

	@Test
	public void myCancelledAppointmentsTest() {
		ViewAppointments viewAppointments = new ViewAppointments();
		viewAppointments.setPageNum(1);
		viewAppointments.setPatientId(141L);
		ViewAppointmentEnum viewAppointmentEnum = ViewAppointmentEnum.CANCELLED;
		viewAppointments.setViewAppointmentEnum(Arrays.asList(viewAppointmentEnum));
		Map<ViewAppointmentEnum, Response<List<Appointment>>> myCancelledAppointments = appointmentPatientService
				.myAppointments(viewAppointments);
		assertNotNull(myCancelledAppointments);
		assertNotNull(myCancelledAppointments.get(viewAppointmentEnum)); //
		assertNotNull(myCancelledAppointments.get(viewAppointmentEnum));
	}

	@Test
	public void myNotAttendAppointmentsTest() {
		ViewAppointments viewAppointments = new ViewAppointments();
		viewAppointments.setPageNum(1);
		viewAppointments.setPatientId(141L);
		ViewAppointmentEnum viewAppointmentEnum = ViewAppointmentEnum.NO_SHOW;
		viewAppointments.setViewAppointmentEnum(Arrays.asList(viewAppointmentEnum));
		Map<ViewAppointmentEnum, Response<List<Appointment>>> myCancelledAppointments = appointmentPatientService
				.myAppointments(viewAppointments);
		assertNotNull(myCancelledAppointments);
		assertNotNull(myCancelledAppointments.get(viewAppointmentEnum)); //
		assertNotNull(myCancelledAppointments.get(viewAppointmentEnum));
	}

	@Test
	public void myUpcomingAppointmentsTest() {
		ViewAppointments viewAppointments = new ViewAppointments();
		viewAppointments.setPageNum(1);
		viewAppointments.setPatientId(141L);
		ViewAppointmentEnum viewAppointmentEnum = ViewAppointmentEnum.UPCOMING;
		viewAppointments.setViewAppointmentEnum(Arrays.asList(viewAppointmentEnum));
		Map<ViewAppointmentEnum, Response<List<Appointment>>> myUpcomingAppointments = appointmentPatientService
				.myAppointments(viewAppointments);
		assertNotNull(myUpcomingAppointments);
	}

	@Test
	public void myCurrentAppointmentsTest() {
		ViewAppointments viewAppointments = new ViewAppointments();
		viewAppointments.setPageNum(1);
		viewAppointments.setPatientId(141L);
		ViewAppointmentEnum viewAppointmentEnum = ViewAppointmentEnum.CURRENT;
		viewAppointments.setViewAppointmentEnum(Arrays.asList(viewAppointmentEnum));
		Map<ViewAppointmentEnum, Response<List<Appointment>>> myUpcomingAppointments = appointmentPatientService
				.myAppointments(viewAppointments);
		assertNotNull(myUpcomingAppointments);
	}

	@Test
	public void myPreviousAppointmentsTest() {
		ViewAppointments viewAppointments = new ViewAppointments();
		viewAppointments.setPageNum(1);
		viewAppointments.setPatientId(173L);
		ViewAppointmentEnum viewAppointmentEnum = ViewAppointmentEnum.PREVIOUS;
		viewAppointments.setViewAppointmentEnum(Arrays.asList(viewAppointmentEnum));
		Map<ViewAppointmentEnum, Response<List<Appointment>>> myUpcomingAppointments = appointmentPatientService
				.myAppointments(viewAppointments);
		assertNotNull(myUpcomingAppointments);
	}

	@Test
	public void patientRejectedTheRequestTest() {
		appointmentPatientService.patientRejectedTheRequest(66L);
	}

	@Test
	public void getAllAppointmentBySearchCritieria() {
		AppointmentSearchRequest appointmentSearchRequest = new AppointmentSearchRequest();
		appointmentSearchRequest.setReferenceEntityName("GMahesh");
		appointmentSearchRequest.setAppointmentFor("DOCTOR");
		ForUser forUser = new ForUser();
		forUser.setUserId(141L);
		forUser.setForUserId(141L);
		RELATIONSHIP_TYPE relationship_TYPE = null;
		appointmentPatientService.getAllAppointmentBySearchCritieria(appointmentSearchRequest, forUser,
				relationship_TYPE);
	}

	@Test
	public void testRequestTobookAppointmentWithDiagnostic() {
		Appointment appointment = new Appointment();
		Long patientId = 173L;
		Long diagnosticCenterAddressId = 24L;
		double amountPaid = 200d;
		Long schedulehoursid = 32856L;

		appointment.setAmountPaid(amountPaid);
		appointment.setAilment("heart");
		appointment.setAppointmentDate(DateUtil.getCurrentDateOnly(TimeZone.getDefault()));
		appointment.setAppointmentFor(AppointmentForEnum.DIAGNOSTIC_CENTER.name());
		appointment.setDescription("heart scanning");
		appointment.setReferenceEntityId(diagnosticCenterAddressId);
		appointment.setReferenceEntityName("Ajay Diagnostic ");
		appointment.setPatientName("jatinsharma");
		appointment.setSchedulehoursId(schedulehoursid);
		appointment.setFromTime(DateUtil.convertLocalTimeToDate(LocalTime.now()));
		appointment.setToTime(DateUtil.convertLocalTimeToDate(LocalTime.now()));
		appointment.setUserId(patientId);
		appointment.setStatusCallbackURL("http://localhost:4200");

		Carddetails cardDetails = new Carddetails();
		cardDetails.setCardbankname("hdfc");
		cardDetails.setCardnumber("4000056655665556");
		cardDetails.setCardtype("DEBIT_CARD");
		cardDetails.setCvv("123");
		cardDetails.setExpmonth(11L);
		cardDetails.setExpyear(2024L);
		cardDetails.setSaveCardDetails(true);
		cardDetails.setUserId(patientId);
		cardDetails.setUsername("ramanujaajay");

		PatientPaymentDetails patientPaymentDetails = new PatientPaymentDetails();
		patientPaymentDetails.setAmountPaid(amountPaid);
		patientPaymentDetails.setCurrencyType("INR");
		patientPaymentDetails.setDescription("heart");
		patientPaymentDetails.setFee(amountPaid);
		patientPaymentDetails.setFeeType("Consultation");

		patientPaymentDetails.setPatientId(patientId);
		patientPaymentDetails.setPaymentThrough("hdfc");
		patientPaymentDetails.setStripeCustomerEmail("jitendra@qontrack.com");
		patientPaymentDetails.setStripeCustomerName("jit");
		patientPaymentDetails.setTransactionType(TransactionTypeEnum.DEBITCARD.name());
	//	patientPaymentDetails.setCarddetails(cardDetails);
		ForUser forUser = new ForUser();
		forUser.setUserId(1L);
		forUser.setForUserId(3L);
		// appointment.setPatientPaymentDetails(patientPaymentDetails);
		RELATIONSHIP_TYPE relationship_TYPE = null;
		AppointmentDTO appointmentdto = new AppointmentDTO();
		appointmentdto.setAppointment(appointment);
		appointmentPatientService.requestTobookAppointmentWithDoctor(appointmentdto, relationship_TYPE);
		assertNotNull(appointment);
	}

	// getTotelPateint

	@Test
	public void totelAppointmentsTest() {
		Long refrenceEntityId = 11L;
		List<UserDTO> user = appointmentPatientService.getTotelPateint(refrenceEntityId);
		assertNotNull(user);
	}

}
