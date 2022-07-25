package org.secondopinion.diagnosticcenter.serviceimpl;

import static org.junit.Assert.assertNotNull;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.secondopinion.diagnosticcenter.dto.Appointment;
import org.secondopinion.diagnosticcenter.dto.AppointmentSearchRequest;
import org.secondopinion.diagnosticcenter.dto.UpdateDiagnosticCenterAddressAppointmentStatus;
import org.secondopinion.diagnosticcenter.dto.ViewAppointments;
import org.secondopinion.diagnosticcenter.dto.ViewAppointments.ViewAppointmentEnum;
import org.secondopinion.diagnosticcenter.request.dto.AppointmentRequestDTO;
import org.secondopinion.diagnosticcenter.request.dto.PatientConfirmRequestDTO;
import org.secondopinion.diagnosticcenter.service.DiagnosticCenterAppointmentService;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterUserService;
import org.secondopinion.diagnosticcenter.test.DiagnosticcenterServiceApplicationTest;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class DiagnosticCenterAppointmentServiceTest extends DiagnosticcenterServiceApplicationTest {

	@Autowired
	private DiagnosticCenterAppointmentService diagnosticCenterAppointmentService;

	@Autowired
	private IDiagnosticCenterUserService iDiagnosticCenterUserService;

	@Test
	public void createAppointment() {
		AppointmentRequestDTO appointmentRequest = new AppointmentRequestDTO();
		appointmentRequest.setDiagnosticCenterAddressId(1L);
		appointmentRequest.setDiagnosticCenterUserId(1L);
		appointmentRequest.setReason("New Appointment For User");
		appointmentRequest.setScheduleHourId(6L);
		appointmentRequest.setPatientName("ganesh");
		appointmentRequest.setPatientUserId(4L);
		appointmentRequest.setDiagnosticCenterName("Vijay");
		appointmentRequest.setAppointmentDate(new Date());
		iDiagnosticCenterUserService.createAppointment(appointmentRequest);
	}

	@Test
	public void cancelAppointment() {
		AppointmentRequestDTO appointmentRequest = new AppointmentRequestDTO();
		appointmentRequest.setDiagnosticCenterAddressId(3L);
		appointmentRequest.setAppointmentDate(new Date());
		appointmentRequest.setFromScheduleId(6L);
		appointmentRequest.setPatientUserId(4L);
		iDiagnosticCenterUserService.cancelAppointment(appointmentRequest);
	}

	@Test
	public void confirmAppointment() {
		PatientConfirmRequestDTO patientConfirmRequestDTO = new PatientConfirmRequestDTO();
		patientConfirmRequestDTO.setDiagnosticCenterAddressId(3L);
		patientConfirmRequestDTO.setPatientUserId(4L);
		patientConfirmRequestDTO.setScheduleId(6L);
		iDiagnosticCenterUserService.confirmAppointment(patientConfirmRequestDTO);
	}

	@Test
	public void rescheduleAppointment() {
		AppointmentRequestDTO appointmentRequest = new AppointmentRequestDTO();
		appointmentRequest.setDiagnosticCenterAddressId(3L);
		appointmentRequest.setAppointmentDate(new Date());
		appointmentRequest.setFromScheduleId(6L);
		appointmentRequest.setPatientUserId(4L);
		iDiagnosticCenterUserService.rescheduleAppointment(appointmentRequest);
	}

	@Test
	public void save() {
		Appointment appointment = new Appointment();

		appointment.setPatientAppointmentId(4L);
		appointment.setDescription("test");
		appointment.setPatientId(4L);
		appointment.setAppointmentDate(new java.util.Date());
		appointment.setSchedulehoursId(6L);
		appointment.setToTime(DateUtil.convertLocalTimeToDate(LocalTime.now()));
		appointment.setPatientName("jatin");
		appointment.setReferenceEntityId(3L);
		appointment.setReferenceEntityName("jatin");
		appointment.setUserId(4L);
		appointment.setDiagnosticCenterAddressId(3L);
		appointment.setDiagnosticCenterName("vijay");
		appointment.setFromTime(DateUtil.convertLocalTimeToDate(LocalTime.now()));
		diagnosticCenterAppointmentService.bookAppointemntWithDiagnosticCenter(appointment);
	}

	@Test
	public void deletAppointment() {
		Long appointmentId = 2L;
		diagnosticCenterAppointmentService.deletAppointment(appointmentId);
	}

	@Test
	public void myCancelledAppointmentsTest() {
		ViewAppointments viewAppointments = new ViewAppointments();
		viewAppointments.setPageNum(1);
		viewAppointments.setPatientId(141L);
		ViewAppointmentEnum viewAppointmentEnum = ViewAppointmentEnum.CANCELLED;
		viewAppointments.setViewAppointmentEnum(Arrays.asList(viewAppointmentEnum));
		Map<ViewAppointmentEnum, Response<List<Appointment>>> myCancelledAppointments = diagnosticCenterAppointmentService
				.myAppointments(viewAppointments);
		assertNotNull(myCancelledAppointments);
		assertNotNull(myCancelledAppointments.get(viewAppointmentEnum));
		// assertNotNull(myCancelledAppointments.get(viewAppointmentEnum).entrySet().);
	}

	@Test
	public void myUpcomingAppointmentsTest() {
		ViewAppointments viewAppointments = new ViewAppointments();
		viewAppointments.setPageNum(1);
		viewAppointments.setPatientId(4L);
		ViewAppointmentEnum viewAppointmentEnum = ViewAppointmentEnum.UPCOMING;
		viewAppointments.setViewAppointmentEnum(Arrays.asList(viewAppointmentEnum));
		Map<ViewAppointmentEnum, Response<List<Appointment>>> myUpcomingAppointments = diagnosticCenterAppointmentService
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
		Map<ViewAppointmentEnum, Response<List<Appointment>>> myUpcomingAppointments = diagnosticCenterAppointmentService
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
		Map<ViewAppointmentEnum, Response<List<Appointment>>> myUpcomingAppointments = diagnosticCenterAppointmentService
				.myAppointments(viewAppointments);
		assertNotNull(myUpcomingAppointments);
	}

	@Test
	public void getAllAppointmentBySearchCritieria() {
		AppointmentSearchRequest appointmentSearchRequest = new AppointmentSearchRequest();
		appointmentSearchRequest.setDiagnosticCenterAddressId(24L);
		appointmentSearchRequest.setPatientId(4L);
		appointmentSearchRequest.setPageNum(1);
		Response<List<Appointment>> app = diagnosticCenterAppointmentService
				.getAllAppointmentBySearchCritieria(appointmentSearchRequest);
		assertNotNull(app);
	}

	@Test
	public void updateAppointmentStatusUponPatientRejectsTheRequest() {
		Long entityAppointmentId = 123L;
		diagnosticCenterAppointmentService.updateAppointmentStatusUponPatientRejectsTheRequest(entityAppointmentId,
				AppointmentStatusEnum.PATIENT_CANCELLED.name());
	}

	@Test
	public void updateAppointmentRequestsUponDCChoice() {
		UpdateDiagnosticCenterAddressAppointmentStatus addressAppointmentStatus = new UpdateDiagnosticCenterAddressAppointmentStatus();
		addressAppointmentStatus.setAppointmentId(2L);
		addressAppointmentStatus.setAppointmentStatus(AppointmentStatusEnum.ENTITY_ACCEPTED);
		addressAppointmentStatus.setDiagnosticCenterAddressId(123L);
		addressAppointmentStatus.setReason("testbyjatin");
		diagnosticCenterAppointmentService.updateAppointmentRequestsUponDCChoice(addressAppointmentStatus);
	}
}
