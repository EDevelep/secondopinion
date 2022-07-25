package org.secondopinion.doctor.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.secondopinion.doctor.dto.Appointment;
import org.secondopinion.doctor.dto.AppointmentSearchRequest;
import org.secondopinion.doctor.dto.Appointmentnotes;
import org.secondopinion.doctor.dto.DoctorDashBoardDTO;
import org.secondopinion.doctor.dto.UpdateDoctorAppointmentStatus;
import org.secondopinion.doctor.dto.ViewAppointments;
import org.secondopinion.doctor.dto.ViewAppointments.ViewAppointmentEnum;
import org.secondopinion.request.Response;
import com.twilio.rest.video.v1.Room;
import com.twilio.rest.video.v1.Room.RoomStatus;
import com.twilio.rest.video.v1.room.Participant;
import com.twilio.rest.video.v1.room.Participant.Status;

public interface IDoctorAppointmentService {

  Appointment appointmentDetails(Long appointmentId);

  Response<List<Appointment>> getAppointmentsByDoctorId(Long doctorId);

  Appointment saveAppointment(Appointment appointment);

  Appointment updateAppointment(Appointment appointment);

  Response<List<Appointment>> getAllAppointmentBySearchCritieria(
      AppointmentSearchRequest appointmentSearchRequest);

  Appointmentnotes appointmentnotesDetails(Long appointmentNotesId);

  List<Appointmentnotes> getAppointmentNotesByAppointmentId(Long appointmentId);

  Appointmentnotes saveAppointmentnotes(Appointmentnotes appointmentnotes);

  void deleteAppointmentnotes(Long appointmentNotesId);

  List<Appointmentnotes> previousAppointmentsnotes(Long doctorId, Long patientId);

  Room retrieiveRoomStatusByRoomName(String roomname);

  Room retrieiveRoomStatusByRoomSID(String roomSID);

  Participant retrieveAParticipant(String roomname, String participantname);

  Participant removeAParticipant(String roomname, String participantname);

  List<Participant> participantsBasedOnStatus(String roomname, Status participantstatus);

  Room updateTheRoomStatus(String roomSID, RoomStatus roomstatus);

  Appointment joinroom(Long appointmentId);

  List<Participant> retrieveRoomParticipants(String roomSID);

  void updateAppointmentStatusUponPatientRejectsTheRequest(Long entityAppointmentId, String status);

  Map<ViewAppointmentEnum, Response<List<Appointment>>> myAppointments(
      ViewAppointments viewAppointments);

  void updateAppointmentRequestsUponDoctorChoice(UpdateDoctorAppointmentStatus appointment);

  void deletAppointment(Long appointmentId);

  void updateAppointmentStatusUponPatientReschudleTheRequest(Long entityAppointmentId,
      String status, Long schedulehoursId);

Collection<DoctorDashBoardDTO> appointmentDetailsForDoctor(Long doctorId, String appointmentFor);



}
