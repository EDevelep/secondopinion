package org.secondopinion.doctor.service;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.junit.Test;
import org.secondopinion.doctor.DoctorServiceApplicationTests;
import org.secondopinion.doctor.dto.Appointment;
import org.secondopinion.doctor.dto.AppointmentSearchRequest;
import org.secondopinion.doctor.dto.Appointmentnotes;
import org.secondopinion.doctor.dto.UpdateDoctorAppointmentStatus;
import org.secondopinion.doctor.dto.ViewAppointments;
import org.secondopinion.doctor.dto.ViewAppointments.ViewAppointmentEnum;
import org.secondopinion.enums.AppointmentForEnum;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class DoctorAppointmentServiceImplTest extends DoctorServiceApplicationTests{

	@Autowired
	private IDoctorAppointmentService appointmentService;
	
	
	
	@Test
	public void saveAppointment() {

		Appointment appointment = new Appointment();
		Long patientId = 32L;
		Long doctorId = 1L;
		Long feeDetailsId = 1L;
		BigDecimal amountPaid =new BigDecimal(500);
		Long scheduleHoursId = 169L;
		
		//appointment.setAmountPaid(amountPaid);
		appointment.setAilment("heart");
		//appointment.setEntityAccepted('Y');
		//appointment.setAppointmentStatus(AppointmentStatusEnum.NEW.name());
		appointment.setAppointmentDate(DateUtil.getCurrentDateOnly( TimeZone.getDefault()));
		//appointment.setAppointmentFor(AppointmentForEnum.DOCTOR.name());
		appointment.setChatType("VIDEO_CHAT");
		appointment.setAmountPaid(amountPaid);
		appointment.setDescription("heart");
		appointment.setReferenceEntityId(doctorId);
		appointment.setReferenceEntityName("kumar jatin sharma ");
		//appointment.setReferenceAppointmentId(doctorId);
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
		appointment.setDoctorId(4L);
		appointment.setReason("test by jatin reschedule");
		appointment.setScheduleHoursId(142L);
		appointmentService.updateAppointmentRequestsUponDoctorChoice(appointment);
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
		appointment.setDoctorId(220L);//doctorId
		appointment.setAudit("shekar");
		appointment.setDescription("somesh");
		appointment.setLastUpdatedBy("jacke");
		appointment.setUserId(1L);
		appointment.validate(true);
		appointmentService.updateAppointment(appointment);
		assertNotNull(appointment);
	}
	
	@Test
	public void testSaveAppointmentnotes() {
		Appointmentnotes appointmentnotes = new Appointmentnotes();
		appointmentnotes.setAppointmentId("6");
		appointmentnotes.setNotes("sample");
		appointmentService.saveAppointmentnotes(appointmentnotes);
		assertNotNull(appointmentnotes);
	}
	
	@Test
	public void testAppointmentnotesDetails() {
		Appointmentnotes appointmentnotesdata = appointmentService.appointmentnotesDetails(1L);
		assertNotNull(appointmentnotesdata);
	}
	
	@Test
	public void testGetAppointmentNotesByAppointmentId() {
		List<Appointmentnotes> appointmentnotesdata = appointmentService.getAppointmentNotesByAppointmentId(1L);
		assertNotNull(appointmentnotesdata);
	}
	
	@Test
	public void testPreviousAppointmentsnotes() {
		List<Appointmentnotes>  appointmentnotesdata = appointmentService.previousAppointmentsnotes(38L, 3L);
		assertNotNull(appointmentnotesdata);
	}
	
	@Test
	public void testGetAllAppointmentBySearchCritieria() {
		AppointmentSearchRequest appointmentSearchRequest = new AppointmentSearchRequest();
		appointmentSearchRequest.setPatientName("ajay");
		appointmentSearchRequest.setDoctorId(40L);
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
		List<Appointment> appointment = appointmentService.getAppointmentsByDoctorId(101L).getData();
		assertNotNull(appointment);
	}
	
	
	@Test
	public void myCurrentAppointmentsTest() {
		ViewAppointments viewAppointments = new ViewAppointments();
		viewAppointments.setDoctorId(40L);
		viewAppointments.setPageNum(1);
		viewAppointments.setMaxResult(1);
		viewAppointments.setViewAppointmentEnum(Arrays.asList(ViewAppointmentEnum.PREVIOUS));
		Map<ViewAppointmentEnum, Response<List<Appointment>>> currentAppointments =  appointmentService.myAppointments(viewAppointments);
		assertNotNull(currentAppointments);
	}
	
}
