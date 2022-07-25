package org.secondopinion.doctor.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.doctor.dao.AppointmentDAO;
import org.secondopinion.doctor.dao.AppointmentnotesDAO;
import org.secondopinion.doctor.dao.ScheduleDAO;
import org.secondopinion.doctor.dao.SchedulehoursDAO;
import org.secondopinion.doctor.domain.BaseAppointment;
import org.secondopinion.doctor.dto.Appointment;
import org.secondopinion.doctor.dto.AppointmentSearchRequest;
import org.secondopinion.doctor.dto.Appointmentnotes;
import org.secondopinion.doctor.dto.DoctorDashBoardDTO;
import org.secondopinion.doctor.dto.Schedule;
import org.secondopinion.doctor.dto.Schedulehours;
import org.secondopinion.doctor.dto.UpdateDoctorAppointmentStatus;
import org.secondopinion.doctor.dto.ViewAppointments;
import org.secondopinion.doctor.dto.ViewAppointments.ViewAppointmentEnum;
import org.secondopinion.doctor.service.IDoctorAppointmentService;
import org.secondopinion.doctor.service.INotificationalertsService;
import org.secondopinion.doctor.service.rest.PatientRestAPIService;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.enums.ScheduleStatusEnum;
import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.NotificationAlert;
import org.secondopinion.utils.VideoChatRoomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.videoroomotpphone.dto.RoomTokenResponse;
import com.twilio.rest.video.v1.Room;
import com.twilio.rest.video.v1.Room.RoomStatus;
import com.twilio.rest.video.v1.room.Participant;
import com.twilio.rest.video.v1.room.Participant.Status;

@Service
public class DoctorAppointmentServiceImpl implements IDoctorAppointmentService {

  @Value("${twilioCallbackURI}")
  private String twilioCallbackURI;

  @Autowired
  private AppointmentDAO appointmentDAO;

  @Autowired
  private UtilComponent utilComponent;

  @Autowired
  private AppointmentnotesDAO appointmentnotesDAO;

  @Autowired
  private SchedulehoursDAO scheduleHoursDAO;
  @Autowired
  private ScheduleDAO scheduleDAO;

  @Autowired
  private PatientRestAPIService patientRestAPIService;

  @Autowired
  private INotificationalertsService notificationalertsService;

  @Override
  public Appointment appointmentDetails(Long appointmentId) {
    return appointmentDAO.findappointmentDetailsById(appointmentId);
  }

  @Override
  public Response<List<Appointment>> getAppointmentsByDoctorId(Long doctorId) {
    return appointmentDAO.getAppointmentsByDoctorId(doctorId);
  }

  @Override
  @Transactional
  public Appointment updateAppointment(Appointment appointment) {
    appointment.validate(true);
    appointment.setActive('Y');
    appointmentDAO.save(appointment);

    return appointment;
  }

  @Override
  @Transactional
  public Appointment saveAppointment(Appointment appointment) {
    appointment.validate(false);
    appointment.setDoctorId(appointment.getReferenceEntityId());
    appointment.setDoctorName(appointment.getReferenceEntityName());
    Schedulehours schedulehours =
        scheduleHoursDAO.getBySchedulehoursId(appointment.getSchedulehoursId());
    if (Objects.isNull(schedulehours)) {
      throw new IllegalArgumentException("No scheduled hours exist.");
    }
    if (Objects.isNull(appointment.getAppointmentId())) {
      appointment.setAppointmentStatus(AppointmentStatusEnum.NEW.name());

      if (ScheduleStatusEnum.AVAILABLE.name().equals(schedulehours.getScheduleStatus())) {
        schedulehours.setScheduleStatus(ScheduleStatusEnum.BLOCKED.name());
        scheduleHoursDAO.save(schedulehours);

        appointment.setAppointmentStatus(AppointmentStatusEnum.ENTITY_ACCEPTED.name());
      }
    }
    appointment.setActive('Y');
    appointment.setAmountPaidStatus("UNPAID");
    appointmentDAO.save(appointment);
    String appointmentDate =
        DateUtil.convertDateFormat(appointment.getAppointmentDate(), Appointment.DATE_FORMAT);
    String fromtime =
        DateUtil.convertDateFormat(appointment.getFromTime(), Appointment.TIME_FORMAT);
    String totime = DateUtil.convertDateFormat(appointment.getToTime(), Appointment.TIME_FORMAT);
    String newdateAndTime = "  " + appointmentDate + " " + fromtime + " To  " + totime;
    NotificationAlert alert =
        new NotificationAlert(appointment.getDoctorId(), appointment.getAppointmentId(),
            " " + " Your appointment has been accepted ", "For" + newdateAndTime);
    notificationalertsService.sendNotificationForAppointment(alert);
    return appointment;
  }

  private void addTwilioDetailsToAppointment(RoomTokenResponse twilioRoomResponseDTO,
      Appointment appointment) {
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
  public Response<List<Appointment>> getAllAppointmentBySearchCritieria(
      AppointmentSearchRequest appointmentSearchRequest) {

    if (Objects.isNull(appointmentSearchRequest.getDoctorId())) {
      throw new IllegalArgumentException("Doctor id can not be null");
    }

    Response<List<Appointment>> appointments = null;
    appointments = appointmentDAO.getAllAppointmentBySearchCritieria(appointmentSearchRequest);
    return appointments;
  }

  @Override
  @Transactional(readOnly = true)
  public Appointmentnotes saveAppointmentnotes(Appointmentnotes appointmentnotes) {
    appointmentnotes.setActive('Y');
    appointmentnotesDAO.save(appointmentnotes);
    return appointmentnotes;
  }

  @Override
  @Transactional(readOnly = true)
  public Appointmentnotes appointmentnotesDetails(Long appointmentNotesId) {
    return appointmentnotesDAO.findappointmentnotesDetailsById(appointmentNotesId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Appointmentnotes> getAppointmentNotesByAppointmentId(Long appointmentId) {
    return appointmentnotesDAO.findByProperty(BaseAppointment.FIELD_appointmentId, appointmentId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Appointmentnotes> previousAppointmentsnotes(Long doctorId, Long patientId) {
    List<Appointmentnotes> appointmentnotesdata = new ArrayList<>();
    List<Appointment> appointments =
        appointmentDAO.previousAppointments(patientId, doctorId).getData();
    if (appointments != null) {
      List<Long> appointmentIds =
          appointments.stream().map(Appointment::getAppointmentId).collect(Collectors.toList());
      appointmentnotesdata = appointmentnotesDAO.previousAppointmentsnotes(appointmentIds);
    }
    return appointmentnotesdata;
  }

  @Override
  @Transactional
  public void deleteAppointmentnotes(Long appointmentNotesId) {
    Appointmentnotes appointmentnotes =
        appointmentnotesDAO.findappointmentnotesDetailsById(appointmentNotesId);

    if (Objects.isNull(appointmentnotes)) {
      throw new IllegalArgumentException("Appointmentnotes not found.");
    }
    appointmentnotes.setActive('N');
    appointmentnotesDAO.save(appointmentnotes);

  }

  @Override
  public Room retrieiveRoomStatusByRoomName(String roomname) {
    return VideoChatRoomUtil.retrieiveRoomStatus(roomname, null);
  }

  @Override
  public Room retrieiveRoomStatusByRoomSID(String roomSID) {
    return VideoChatRoomUtil.retrieiveRoomStatus(null, roomSID);
  }

  @Override
  public Participant retrieveAParticipant(String roomname, String participantname) {
    return VideoChatRoomUtil.retrieveAParticipant(roomname, participantname);
  }

  @Override
  public Participant removeAParticipant(String roomname, String participantname) {
    return VideoChatRoomUtil.removeAParticipant(roomname, participantname);
  }

  @Override
  public List<Participant> participantsBasedOnStatus(String roomname, Status participantstatus) {
    return VideoChatRoomUtil.participantsBasedOnStatus(roomname, participantstatus);
  }

  @Override
  public Room updateTheRoomStatus(String roomSID, RoomStatus roomstatus) {
    return VideoChatRoomUtil.updateTheRoomStatus(roomSID, roomstatus);
  }

  @Override
  @Transactional
  public Appointment joinroom(Long appointmentId) {

    Appointment appointment = appointmentDAO.findappointmentDetailsById(appointmentId);
    if (Objects.isNull(appointment)) {
      throw new IllegalArgumentException("Appointment not found");
    }
    LocalDate currentDay = DateUtil.getCurrentLocalDate(utilComponent.getTimeZone());
    if (!currentDay.equals(DateUtil.convertUtilDateToLocalDate(appointment.getAppointmentDate()))) {
      throw new IllegalArgumentException("Appointment date is not equal to the current date.");
    }

    appointment.setAppointmentStatus(AppointmentStatusEnum.ENTITY_ACCEPTED.name());
    if (StringUtils.isEmpty(appointment.getChatRoomName())
        || StringUtils.isEmpty(appointment.getChatRoomSID())
        || StringUtils.isEmpty(appointment.getTokenForChat())) {

      String chatIdentity =
          appointment.getReferenceEntityName() + "-" + appointment.getAppointmentId();// vishnu-16

      RoomTokenResponse roomResponse =
          VideoChatRoomUtil.createTwilioRoom("Room-" + appointment.getAppointmentId(), // roomname,
              StringUtils.isEmpty(appointment.getStatusCallbackURL()) ? twilioCallbackURI
                  : appointment.getStatusCallbackURL(),
              chatIdentity, appointment.getChatType());

      if (!Objects.isNull(roomResponse)) {
        addTwilioDetailsToAppointment(roomResponse, appointment);
      }

      appointmentDAO.save(appointment);
      patientRestAPIService.updateAppointmentUponDoctorAppointmentStatus(appointment);
    }

    return appointment;
  }

  @Override
  public List<Participant> retrieveRoomParticipants(String roomSID) {
    return VideoChatRoomUtil.retrieveRoomParticipants(roomSID);
  }

  @Override
  @Transactional
  public void updateAppointmentStatusUponPatientRejectsTheRequest(Long entityAppointmentId,
      String status) {
    Appointment dbappointment = appointmentDAO.findappointmentDetailsById(entityAppointmentId);
    if (Objects.isNull(dbappointment)) {
      throw new IllegalArgumentException("Appointment  not found");
    }
    dbappointment.setAppointmentStatus(status);
    dbappointment.setTokenForChat(null);
    dbappointment.setActive('Y');
    appointmentDAO.save(dbappointment);

    Schedulehours scheduleHours =
        scheduleHoursDAO.getBySchedulehoursId(dbappointment.getSchedulehoursId());
    resetTheSchedulehours(scheduleHours);

    // after appointment is booked first LocalTime booking
    NotificationAlert alert = new NotificationAlert(dbappointment.getDoctorId(),
        dbappointment.getAppointmentId(), "Appointment Cancelled",
        dbappointment.getPatientName() + " has cancelled the appointment on "
            + dbappointment.getAppointmentDate() + " " + dbappointment.getFromTime());

    notificationalertsService.sendNotificationForCancleAppointment(alert);
  }


  @Override
  @Transactional
  public void updateAppointmentStatusUponPatientReschudleTheRequest(Long entityAppointmentId,
      String status, Long schedulehoursId) {
    Appointment appointment = appointmentDAO.findappointmentDetailsById(entityAppointmentId);
    if (Objects.isNull(appointment)) {
      throw new IllegalArgumentException("Appointment not found.");
    }
    Schedulehours oldScheduleHours =
        scheduleHoursDAO.getBySchedulehoursId(appointment.getSchedulehoursId());
    resetTheSchedulehours(oldScheduleHours);
    appointment.setAppointmentStatus(AppointmentStatusEnum.ENTITY_RESCHEDULED.name());
    Schedulehours newschedulehours = scheduleHoursDAO.getBySchedulehoursId(schedulehoursId);
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
    scheduleHoursDAO.save(newschedulehours);

    appointment.setSchedulehoursId(newschedulehours.getScheduleHoursId());
    appointment.setAppointmentDate(schedule.getScheduleDate());
    appointment.setFromTime(newschedulehours.getFromTime());
    appointment.setToTime(newschedulehours.getToTime());
    appointment.setChatRoomName(null);
    appointment.setChatRoomSID(null);
    appointment.setChatURL(null);
    appointment.setTokenForChat(null);
    appointmentDAO.save(appointment);
    // after appointment is booked first LocalTime booking
    NotificationAlert alert = new NotificationAlert(appointment.getDoctorId(),
        appointment.getAppointmentId(), "Appointment Reschudle",
        appointment.getPatientName() + " has Reschudle the appointment on "
            + appointment.getAppointmentDate() + " " + appointment.getFromTime());

    notificationalertsService.sendNotificationForCancleAppointment(alert);

  }

  @Override
  @Transactional(readOnly = true)
  public Map<ViewAppointmentEnum, Response<List<Appointment>>> myAppointments(
      ViewAppointments viewAppointments) {
    Map<ViewAppointmentEnum, Response<List<Appointment>>> map = new HashMap<>();
    List<ViewAppointmentEnum> viewAppointmentEnums = viewAppointments.getViewAppointmentEnum();
    viewAppointmentEnums.stream().forEach(vae -> {

      switch (vae) {
        case CURRENT:
          map.put(ViewAppointmentEnum.CURRENT,
              appointmentDAO.currentAppointments(viewAppointments.getDoctorId(),
                  viewAppointments.getPageNum(), viewAppointments.getMaxResult()));
          break;
        case PREVIOUS:
          map.put(ViewAppointmentEnum.PREVIOUS,
              appointmentDAO.previousAppointments(viewAppointments.getDoctorId(),
                  viewAppointments.getPatientId(), viewAppointments.getPageNum(),
                  viewAppointments.getMaxResult()));
          break;
        case UPCOMING:
          map.put(ViewAppointmentEnum.UPCOMING,
              appointmentDAO.upcomingAppointments(viewAppointments.getDoctorId(),
                  viewAppointments.getPageNum(), viewAppointments.getMaxResult()));
          break;
        case CANCELLED:
          map.put(ViewAppointmentEnum.CANCELLED,
              appointmentDAO.retrieveCancelledAppoitments(viewAppointments.getDoctorId(),
                  viewAppointments.getPageNum(), viewAppointments.getMaxResult()));
          break;
        case RESCHEDULED:
          map.put(ViewAppointmentEnum.RESCHEDULED,
              appointmentDAO.retrieveRescheduledAppoitments(viewAppointments.getDoctorId(),
                  viewAppointments.getPageNum(), viewAppointments.getMaxResult()));
          break;

        default:
          break;
      }
    });
    return map;
  }

  @Override
  @Transactional
  public void updateAppointmentRequestsUponDoctorChoice(
      UpdateDoctorAppointmentStatus updateDoctorAppointmentStatus) {
    if (updateDoctorAppointmentStatus.getAppointmentStatus()
        .equals(AppointmentStatusEnum.ENTITY_ACCEPTED)) {
      updateAppointmentUponDoctorAccept(updateDoctorAppointmentStatus);
    }
    if (updateDoctorAppointmentStatus.getAppointmentStatus()
        .equals(AppointmentStatusEnum.ENTITY_CANCELLED)) {
      updateAppointmentUponDoctorRejects(updateDoctorAppointmentStatus);
    }
    if (updateDoctorAppointmentStatus.getAppointmentStatus()
        .equals(AppointmentStatusEnum.ENTITY_RESCHEDULED)) {
      updateAppointmentUponDoctorReschedule(updateDoctorAppointmentStatus);
    }
  }

  private void updateAppointmentUponDoctorAccept(
      UpdateDoctorAppointmentStatus updateDoctorAppointmentStatus) {

    Appointment appointment =
        appointmentDAO.findappointmentDetailsById(updateDoctorAppointmentStatus.getAppointmentId());
    if (Objects.isNull(appointment)) {
      throw new IllegalArgumentException("Appointment not found.");
    }
    appointment.setAppointmentStatus(AppointmentStatusEnum.ENTITY_ACCEPTED.name());

    // update schedulehours table with schedulestatus as Blocked
    Schedulehours schedulehours = scheduleHoursDAO.findById(appointment.getSchedulehoursId());
    if (Objects.isNull(schedulehours)) {
      throw new IllegalArgumentException("Schedule hours are not exists.");
    }
    schedulehours.setScheduleStatus(ScheduleStatusEnum.BLOCKED.name());
    scheduleHoursDAO.save(schedulehours);

    appointment.setChatRoomName(null);
    appointment.setChatRoomSID(null);
    appointment.setChatURL(null);
    appointment.setTokenForChat(null);
    appointmentDAO.save(appointment);

    patientRestAPIService.updateAppointmentUponDoctorAppointmentStatus(appointment);

  }

  private void updateAppointmentUponDoctorRejects(
      UpdateDoctorAppointmentStatus updateDoctorAppointmentStatus) {
    Appointment appointment =
        appointmentDAO.findappointmentDetailsById(updateDoctorAppointmentStatus.getAppointmentId());
    if (Objects.isNull(appointment)) {
      throw new IllegalArgumentException("Appointment not found.");
    }
    appointment.setAppointmentStatus(AppointmentStatusEnum.ENTITY_CANCELLED.name());
    appointment.setChatRoomName(null);
    appointment.setChatRoomSID(null);
    appointment.setChatURL(null);
    appointment.setTokenForChat(null);
    appointmentDAO.save(appointment);

    patientRestAPIService.updateAppointmentUponDoctorAppointmentStatus(appointment);

    Schedulehours scheduleHours =
        scheduleHoursDAO.getBySchedulehoursId(appointment.getSchedulehoursId());

    resetTheSchedulehours(scheduleHours);

    NotificationAlert alert = new NotificationAlert(appointment.getDoctorId(),
        appointment.getAppointmentId(), "Appointment Cancelled",
        appointment.getDoctorName() + " has cancelled the appointment on "
            + appointment.getAppointmentDate() + " " + appointment.getFromTime());

    notificationalertsService.sendNotification(alert);

  }

  private void updateAppointmentUponDoctorReschedule(
      UpdateDoctorAppointmentStatus updateDoctorAppointmentStatus) {
    Appointment appointment =
        appointmentDAO.findappointmentDetailsById(updateDoctorAppointmentStatus.getAppointmentId());
    if (Objects.isNull(appointment)) {
      throw new IllegalArgumentException("Appointment not found.");
    }
    Schedulehours oldScheduleHours =
        scheduleHoursDAO.getBySchedulehoursId(appointment.getSchedulehoursId());
    resetTheSchedulehours(oldScheduleHours);
    appointment.setAppointmentStatus(AppointmentStatusEnum.ENTITY_RESCHEDULED.name());
    Schedulehours newschedulehours =
        scheduleHoursDAO.getBySchedulehoursId(updateDoctorAppointmentStatus.getScheduleHoursId());
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
    scheduleHoursDAO.save(newschedulehours);

    appointment.setSchedulehoursId(newschedulehours.getScheduleHoursId());
    appointment.setAppointmentDate(schedule.getScheduleDate());
    appointment.setFromTime(newschedulehours.getFromTime());
    appointment.setToTime(newschedulehours.getToTime());
    appointment.setChatRoomName(null);
    appointment.setChatRoomSID(null);
    appointment.setChatURL(null);
    appointment.setTokenForChat(null);
    appointmentDAO.save(appointment);

    patientRestAPIService.updateAppointmentUponDoctorAppointmentStatus(appointment);

    String appointmentDate =
        DateUtil.convertDateFormat(appointment.getAppointmentDate(), Appointment.DATE_FORMAT);
    String fromtime =
        DateUtil.convertDateFormat(appointment.getFromTime(), Schedulehours.TIME_FORMAT);
    String totime = DateUtil.convertDateFormat(appointment.getToTime(), Schedulehours.TIME_FORMAT);

    String newdateAndTime = "  " + appointmentDate + " " + fromtime + " To  " + totime;
    // afetr this we need to need to send notofaction the doctor also
    NotificationAlert alert =
        new NotificationAlert(appointment.getDoctorId(), appointment.getAppointmentId(),
            " " + "  Appointment has been  Reschedule ", "For" + newdateAndTime);
    notificationalertsService.sendNotificationForAppointment(alert);
  }

  @Override
  @Transactional
  public void deletAppointment(Long appointmentId) {
    Appointment appointment = appointmentDAO.findappointmentDetailsById(appointmentId);

    if (Objects.isNull(appointment)) {
      throw new IllegalArgumentException("Appointment not found.");
    }

    Schedulehours scheduleHours =
        scheduleHoursDAO.getBySchedulehoursId(appointment.getSchedulehoursId());
    appointment.setActive('N');
    appointmentDAO.save(appointment);

    resetTheSchedulehours(scheduleHours);
  }

  private void resetTheSchedulehours(Schedulehours scheduleHours) {
    if (ScheduleStatusEnum.BLOCKED.name().equals(scheduleHours.getScheduleStatus())) {

      scheduleHours.setScheduleStatus(ScheduleStatusEnum.AVAILABLE.name());
      scheduleHoursDAO.save(scheduleHours);
    }
  }

@Override
@Transactional
public Collection<DoctorDashBoardDTO> appointmentDetailsForDoctor(Long doctorId,String appointmentFor) {
	
	return appointmentDAO.appointmentDetailsForDoctor(doctorId,appointmentFor);
}
}
