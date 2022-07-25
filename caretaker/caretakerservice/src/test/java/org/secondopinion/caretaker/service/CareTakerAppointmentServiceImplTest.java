package org.secondopinion.caretaker.service;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.junit.Test;
import org.secondopinion.caretaker.CareTakerServiceApplicationTest;
import org.secondopinion.caretaker.dto.Appointment;
import org.secondopinion.caretaker.dto.AppointmentSearchRequest;
import org.secondopinion.caretaker.dto.UpdateDoctorAppointmentStatus;
import org.secondopinion.caretaker.dto.ViewAppointments;
import org.secondopinion.caretaker.dto.ViewAppointments.ViewAppointmentEnum;
import org.secondopinion.enums.AppointmentForEnum;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class CareTakerAppointmentServiceImplTest extends CareTakerServiceApplicationTest{

	@Autowired
	private ICaretakerAppointmentService appointmentService;
	
	
	
	@Test
	public void saveAppointment() {

		Appointment appointment = new Appointment();
		Long patientId = 89L;
		Long doctorId = 12L;
		Long feeDetailsId = 12L;
		BigDecimal amountPaid =new BigDecimal(200);
		Long scheduleHoursId = 169L;
		
		appointment.setAmountPaid(amountPaid);
		appointment.setAilment("heart");
		//appointment.setEntityAccepted('Y');
		appointment.setAppointmentStatus(AppointmentStatusEnum.NEW.name());
		appointment.setAppointmentDate(DateUtil.getCurrentDateOnly( TimeZone.getDefault()));
		//appointment.setAppointmentFor(AppointmentForEnum.DOCTOR.name());
		appointment.setChatType("VIDEO_CHAT");
		appointment.setAmountPaid(amountPaid);
		appointment.setDescription("heart");
		appointment.setReferenceEntityId(doctorId);
		appointment.setReferenceEntityName("kumar jatin sharma ");
		//appointment.setReferenceEntityId(doctorId);
		appointment.setPatientName("Jatin  Sharma ");
		appointment.setSchedulehoursId(scheduleHoursId);
		appointment.setFromTime(DateUtil.convertLocalTimeToDate(LocalTime.now()));
		appointment.setToTime(DateUtil.convertLocalTimeToDate(LocalTime.now()));
		appointment.setUserId(patientId);
		appointment.setStatusCallbackURL("http://localhost:4200");
		appointmentService.saveAppointment(appointment);
		
		
	}
	
	
	@Test
	public void updateAppointmentRequestsUponDoctorChoice() {
		UpdateDoctorAppointmentStatus appointment = new UpdateDoctorAppointmentStatus();
		appointment.setAppointmentId(13L);
		appointment.setAppointmentStatus(AppointmentStatusEnum.ENTITY_RESCHEDULED);
		appointment.setCaretakerId(12L);
		appointment.setReason("test by jatin reschedule");
		appointment.setScheduleHoursId(142L);
		appointmentService.updateAppointmentRequestsUponCaretakerChoice(appointment);
		assertNotNull(appointment);
	}
	
	
	@Test
	public void testAppointmentDetails() {
		Appointment appointment = appointmentService.appointmentDetails(6L);
		assertNotNull(appointment);
	}
	
	@Test
	public void testUpdateAppointment() {
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(new Date(0));
		appointment.setCreatedDate(new Date());
		appointment.setCreatedBy("vishnu");
		appointment.setCaretakerId(12L);//doctorId
		appointment.setAudit("shekar");
		appointment.setDescription("somesh");
		appointment.setLastUpdatedBy("jacke");
		appointment.setUserId(1L);
		appointment.validate(true);
		appointmentService.updateAppointment(appointment);
		assertNotNull(appointment);
	}
	
	
	
	
	@Test
	public void testGetAllAppointmentBySearchCritieria() {
		AppointmentSearchRequest appointmentSearchRequest = new AppointmentSearchRequest();
		appointmentSearchRequest.setPatientName("ajay");
		appointmentSearchRequest.setCaretakerId(12L);
		appointmentService.getAllAppointmentBySearchCritieria(appointmentSearchRequest);
		assertNotNull(appointmentSearchRequest);
	}
	
	@Test
	public void testFindById() {
		Appointment appointment = appointmentService.appointmentDetails(6L);
		assertNotNull(appointment);
	}
	
	@Test
	public void testGetAppointmentsByDoctorId() {
		List<Appointment> appointment = appointmentService.getAppointmentsByCaretakerId(101L).getData();
		assertNotNull(appointment);
	}
	
	
	@Test
	public void myCurrentAppointmentsTest() {
		ViewAppointments viewAppointments = new ViewAppointments();
		viewAppointments.setCaretakerId(12L);
		viewAppointments.setPageNum(1);
		viewAppointments.setMaxResult(1);
		viewAppointments.setViewAppointmentEnum(Arrays.asList(ViewAppointmentEnum.PREVIOUS));
		Map<ViewAppointmentEnum, Response<List<Appointment>>> currentAppointments =  appointmentService.myAppointments(viewAppointments);
		assertNotNull(currentAppointments);
	}
	
}
