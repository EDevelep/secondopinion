package org.secondopinion.diagnosticcenter.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.secondopinion.configurations.AppProperties;
import org.secondopinion.diagnosticcenter.dao.AppointmentDAO;
import org.secondopinion.diagnosticcenter.dao.ScheduleDAO;
import org.secondopinion.diagnosticcenter.dao.SchedulehoursDAO;
import org.secondopinion.diagnosticcenter.domain.BaseAppointment;
import org.secondopinion.diagnosticcenter.dto.Appointment;
import org.secondopinion.diagnosticcenter.dto.AppointmentSearchRequest;
import org.secondopinion.diagnosticcenter.dto.Schedule;
import org.secondopinion.diagnosticcenter.dto.Schedulehours;
import org.secondopinion.diagnosticcenter.dto.UpdateDiagnosticCenterAddressAppointmentStatus;
import org.secondopinion.diagnosticcenter.dto.ViewAppointments;
import org.secondopinion.diagnosticcenter.dto.ViewAppointments.ViewAppointmentEnum;
import org.secondopinion.diagnosticcenter.service.DiagnosticCenterAppointmentService;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterNotificationalertsService;
import org.secondopinion.diagnosticcenter.service.rest.PatientRestAPIService;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.enums.ScheduleStatusEnum;
import org.secondopinion.fileservice.FileService;
import org.secondopinion.request.Response;
import org.secondopinion.utils.NotificationAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DiagnosticCenterAppointmentServiceImpl implements DiagnosticCenterAppointmentService{

	
	
	@Autowired
	private AppointmentDAO appointmentDAO;
	
	@Autowired
	private SchedulehoursDAO schedulehoursDAO;
	
	@Autowired
	private ScheduleDAO scheduleDAO;
	
	@Autowired
	private IDiagnosticCenterNotificationalertsService notificationalertsService;
	
	@Autowired
	private PatientRestAPIService patientRestAPIService;
	
	@Autowired
	private AppProperties appProperties;

	@Override
	@Transactional
	public Appointment bookAppointemntWithDiagnosticCenter(Appointment appointment) {
		
		appointment.setDiagnosticCenterName(appointment.getReferenceEntityName());
		appointment.setPatientId(appointment.getUserId());
		appointment.setDiagnosticCenterAddressId(appointment.getReferenceEntityId());
		
		Schedulehours schedulehours=schedulehoursDAO.getBySchedulehoursId(appointment.getSchedulehoursId());
		// need to write List schedulehours
		if (Objects.isNull(schedulehours)) {
			throw new IllegalArgumentException("The schedule hours are not exists.");
		}
		if (Objects.isNull(appointment.getAppointmentId())) {
			appointment.setAppointmentStatus(AppointmentStatusEnum.NEW.name());
			appointment.setActive('Y');

			if (ScheduleStatusEnum.AVAILABLE.name().equals(schedulehours.getScheduleStatus())) {
				schedulehours.setScheduleStatus(ScheduleStatusEnum.BLOCKED.name());
				schedulehoursDAO.save(schedulehours);

				appointment.setAppointmentStatus(AppointmentStatusEnum.ENTITY_ACCEPTED.name());
			}
		}
		
		appointmentDAO.save(appointment);
		


		// after appointment is booked first LocalTime booking
		NotificationAlert alert = new NotificationAlert(appointment.getDiagnosticCenterAddressId(),
				appointment.getAppointmentId(), "Appointment Request",
				"you have got a new appointment request from a patient : " + appointment.getPatientName());

		notificationalertsService.sendNotificationalerts(alert);
		return appointment;
		
	}
	
	
	@Override
	@Transactional
	public Response<List<Appointment>> getAllAppointmentBySearchCritieria(AppointmentSearchRequest appointmentDTO) {
		return appointmentDAO.getAllAppointmentBySearchCritieria(appointmentDTO);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map<ViewAppointmentEnum, Response<List<Appointment>>> myAppointments(ViewAppointments viewAppointments) {
		Map<ViewAppointmentEnum, Response<List<Appointment>>> map = new HashMap<>();
		List<ViewAppointmentEnum> viewAppointmentEnums = viewAppointments.getViewAppointmentEnum();
		viewAppointmentEnums.stream().forEach(vae -> {
			if (vae.equals(ViewAppointmentEnum.CURRENT)) {
				map.put(ViewAppointmentEnum.CURRENT,
						appointmentDAO.currentAppointments(viewAppointments.getDiagnosticCenterAddressId(),viewAppointments.getPageNum(),viewAppointments.getMaxResult()));
			}
			if (vae.equals(ViewAppointmentEnum.PREVIOUS)) {
				map.put(ViewAppointmentEnum.PREVIOUS, appointmentDAO
						.previousAppointments(viewAppointments.getDiagnosticCenterAddressId(), viewAppointments.getPatientId(),viewAppointments.getPageNum(),viewAppointments.getMaxResult()));
			}
			if (vae.equals(ViewAppointmentEnum.UPCOMING)) {
				map.put(ViewAppointmentEnum.UPCOMING,
						appointmentDAO.upcomingAppointments(viewAppointments.getDiagnosticCenterAddressId(),viewAppointments.getPageNum(),viewAppointments.getMaxResult()));
			}
			if (vae.equals(ViewAppointmentEnum.CANCELLED)) {
				map.put(ViewAppointmentEnum.CANCELLED,
						appointmentDAO.retrieveCancelledAppoitments(viewAppointments.getDiagnosticCenterAddressId(),viewAppointments.getPageNum(),viewAppointments.getMaxResult()));
			}
			if (vae.equals(ViewAppointmentEnum.RESCHEDULED)) {
				map.put(ViewAppointmentEnum.RESCHEDULED,
						appointmentDAO.retrieveRescheduledAppoitments(viewAppointments.getDiagnosticCenterAddressId(),viewAppointments.getPageNum(),viewAppointments.getMaxResult()));
			}

		});
		return map;
	}
	
	@Override
	@Transactional
	public void updateAppointmentStatusUponPatientRejectsTheRequest(Long entityAppointmentId, String status) {
		Appointment dbappointment = appointmentDAO.findById(entityAppointmentId);
		if (Objects.isNull(dbappointment)) {
			throw new IllegalArgumentException("Appointment  not found");
		}
		dbappointment.setAppointmentStatus(status);
		dbappointment.setActive('Y');
		appointmentDAO.save(dbappointment);
		
		Schedulehours scheduleHours=schedulehoursDAO.getBySchedulehoursId(dbappointment.getSchedulehoursId()); 
		resetTheSchedulehours(scheduleHours);
		
		// after appointment is booked first LocalTime booking
		NotificationAlert alert = new NotificationAlert(dbappointment.getDiagnosticCenterAddressId(),
				dbappointment.getAppointmentId(), "Appointment Cancelled",
				"Mr(s)/Miss)" + dbappointment.getPatientName() + " has cancelled the appointment on "+ dbappointment.getAppointmentDate() + " " + dbappointment.getFromTime() );

		notificationalertsService.sendNotificationalerts(alert);
	}


	@Override
	@Transactional
	public void updateAppointmentRequestsUponDCChoice(UpdateDiagnosticCenterAddressAppointmentStatus addressAppointmentStatus) {
		if (addressAppointmentStatus.getAppointmentStatus().equals(AppointmentStatusEnum.ENTITY_ACCEPTED)) {
			updateAppointmentUponDCAccept(addressAppointmentStatus);
		}
		if (addressAppointmentStatus.getAppointmentStatus().equals(AppointmentStatusEnum.ENTITY_CANCELLED)) {
			updateAppointmentUponDCRejects(addressAppointmentStatus);
		}
		if (addressAppointmentStatus.getAppointmentStatus().equals(AppointmentStatusEnum.ENTITY_RESCHEDULED)) {
			updateAppointmentUponDCReschedule(addressAppointmentStatus);
		}
	}
	

	private void updateAppointmentUponDCAccept(UpdateDiagnosticCenterAddressAppointmentStatus updateDiagnosticCenterAddressAppointmentStatus) {

		Appointment appointment = appointmentDAO.findById(updateDiagnosticCenterAddressAppointmentStatus.getAppointmentId());
		if (Objects.isNull(appointment)) {
			throw new IllegalArgumentException("Appointment not found.");
		}
		appointment.setAppointmentStatus(AppointmentStatusEnum.ENTITY_ACCEPTED.name());

		// update schedulehours table with schedulestatus as Blocked
		Schedulehours schedulehours = schedulehoursDAO.findById(appointment.getSchedulehoursId());
		if (Objects.isNull(schedulehours)) {
			throw new IllegalArgumentException("Schedule hours are not exists.");
		}
		schedulehours.setScheduleStatus(ScheduleStatusEnum.BLOCKED.name());
		schedulehoursDAO.save(schedulehours);

		appointmentDAO.save(appointment);

		patientRestAPIService.updateAppointmentUponDCAppointmentStatus(appointment);

	}

	private void updateAppointmentUponDCRejects(UpdateDiagnosticCenterAddressAppointmentStatus updateDiagnosticCenterAddressAppointmentStatus) {
		Appointment appointment = appointmentDAO.findById(updateDiagnosticCenterAddressAppointmentStatus.getAppointmentId());
		if (Objects.isNull(appointment)) {
			throw new IllegalArgumentException("Appointment not found.");
		}
		appointment.setAppointmentStatus(AppointmentStatusEnum.ENTITY_CANCELLED.name());
		appointmentDAO.save(appointment);
		
		patientRestAPIService.updateAppointmentUponDCAppointmentStatus(appointment);
		
		Schedulehours scheduleHours=schedulehoursDAO.getBySchedulehoursId(appointment.getSchedulehoursId()); 
		resetTheSchedulehours(scheduleHours);

	}

	private void updateAppointmentUponDCReschedule(UpdateDiagnosticCenterAddressAppointmentStatus updateDiagnosticCenterAddressAppointmentStatus) {

		Appointment appointment = appointmentDAO.findById(updateDiagnosticCenterAddressAppointmentStatus.getAppointmentId());
		if (Objects.isNull(appointment)) {
			throw new IllegalArgumentException("Appointment not found.");
		}
		Schedulehours oldScheduleHours = schedulehoursDAO.getBySchedulehoursId(updateDiagnosticCenterAddressAppointmentStatus.getScheduleHoursId());
		resetTheSchedulehours( oldScheduleHours);
		
		appointment.setAppointmentStatus(AppointmentStatusEnum.ENTITY_RESCHEDULED.name());
		// update schedulehours table with schedulestatus as Blocked

		Schedulehours newschedulehours = schedulehoursDAO.getBySchedulehoursId(updateDiagnosticCenterAddressAppointmentStatus.getScheduleHoursId());
		if (Objects.isNull(newschedulehours)) {
			throw new IllegalArgumentException("Schedule hours are not exists.");
		}
		String blockedStatus = ScheduleStatusEnum.BLOCKED.name();
		if (newschedulehours.getScheduleStatus().equals(ScheduleStatusEnum.BLOCKED.name())) {
			throw new IllegalArgumentException("The Schedulehour is already blocked.");
		}
		Schedule schedule = scheduleDAO.getByScheduleId(newschedulehours.getScheduleId());
		if (Objects.isNull(schedule)) {
			throw new IllegalArgumentException("ScheduleDate is not exists.");
		}
		newschedulehours.setScheduleStatus(blockedStatus);
		schedulehoursDAO.save(newschedulehours);

		appointment.setSchedulehoursId(newschedulehours.getScheduleHoursId());
		appointment.setAppointmentDate(schedule.getScheduleDate());
		appointment.setFromTime(newschedulehours.getFromTime());
		appointment.setToTime(newschedulehours.getToTime());
		appointmentDAO.save(appointment);

		patientRestAPIService.updateAppointmentUponDCAppointmentStatus(appointment);
	}
	
	@Override
	@Transactional
	public void deletAppointment(Long appointmentId) {
		Appointment appointment = appointmentDAO.findById(appointmentId);

		if (Objects.isNull(appointment)) {
			throw new IllegalArgumentException("Appointment not found.");
		}

		Schedulehours scheduleHours = schedulehoursDAO.getBySchedulehoursId(appointment.getSchedulehoursId());
		appointment.setActive('N');
		appointmentDAO.save(appointment);

		resetTheSchedulehours(scheduleHours);
	}
	
	private void resetTheSchedulehours(Schedulehours scheduleHours) {
		if(Objects.isNull(scheduleHours)) {
			throw new IllegalArgumentException("scheduleHours  not found");
		}
		if (ScheduleStatusEnum.BLOCKED.name().equals(scheduleHours.getScheduleStatus())) {

			List<Appointment> appointments = appointmentDAO.findByProperty(BaseAppointment.FIELD_schedulehoursId,
					scheduleHours.getScheduleHoursId());
			scheduleHours.setScheduleStatus(ScheduleStatusEnum.AVAILABLE.name());

			if (appointments != null && !appointments.isEmpty()) {
				for (Appointment aptmt : appointments) {
					scheduleHours.setScheduleStatus(ScheduleStatusEnum.AVAILABLE.name());
					if (AppointmentStatusEnum.ENTITY_ACCEPTED.name().equals(aptmt.getAppointmentStatus())) {
						scheduleHours.setScheduleStatus(ScheduleStatusEnum.BLOCKED.name());
					}
				}
			}
			// This was missing!!
			
			schedulehoursDAO.save(scheduleHours);
		}
	}


	@Override
	public void uploadReportsToThePatient(Long appointmentId, MultipartFile medicalreport) {
		Appointment appointment = appointmentDAO.findById(appointmentId);
		if(Objects.isNull(appointment)) {
			throw new IllegalArgumentException("Appointment not found.");
		}
		
		// upload file
		String uploadReference = FileService.uploadFile(
			appProperties.getFc(), appointment.getPatientId(), medicalreport, appointment.getAppointmentId());
		patientRestAPIService.uploadReportsToThePatient(appointment.getAppointmentId(), uploadReference, medicalreport.getOriginalFilename());
	}

}
