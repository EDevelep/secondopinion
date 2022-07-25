package org.secondopinion.patient.service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.patient.dto.AppointmentBookingDTO;
import org.secondopinion.patient.dto.AppointmentDTO;
import org.secondopinion.patient.dto.AppointmentDTOForDiagnosticcenter;
import org.secondopinion.patient.dto.AppointmentSearchRequest;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.UserDTO;
import org.secondopinion.patient.dto.ViewAppointments;
import org.secondopinion.patient.dto.ViewAppointments.ViewAppointmentEnum;
import org.secondopinion.request.Response;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.video.v1.room.RoomRecording;

public interface IPatientAppointmentService {

  Appointment requestTobookAppointmentWithDoctor(AppointmentDTO appointment	,RELATIONSHIP_TYPE relationship_TYPE);

  Response<List<Appointment>> getAppointmentFor(Long refrenceEntityId, String appoitmentfor,
      Integer pageNum);

  Response<List<Appointment>> getAllAppointmentBySearchCritieria(
      AppointmentSearchRequest appointmentSearchRequest, ForUser forUser,RELATIONSHIP_TYPE relationship_TYPE);

  Appointment joinroom(Long appointmentId);

  Call voiceChat(Long patientId) throws URISyntaxException;

  List<RoomRecording> fetchRecording(Long patientId, String roomSID);

  Appointment getAppointmentById(Long appointmentId);

  void patientRejectedTheRequest(Long appointmentId);

  void patinetRescheduleTheRequest(Long appointmentId, Long schudlehourseId);

  void updateAppointmentUponDoctorAppointmentStatusTheRequest(Long referenceAppointmentId,
      Appointment appointment, String appointmentFor);

  Map<ViewAppointmentEnum, Response<List<Appointment>>> myAppointments(
      ViewAppointments viewAppointments);

  void updateAppointmentbyDiagnosticCenterUser(Long referenceEntityId, Appointment appointment,
      String appointmentFor);

  void cancelAppointmentForDiagnosticCenterUser(Long referenceAppointmentId,
      Appointment appointment, String appointmentFor);

  void rescheduleAppointmentForDiagnosticCenterUser(Long referenceAppointmentId,
      Appointment appointment, String appointmentFor);

  List<UserDTO> getTotelPateint(Long refrenceEntityId);
  public String buildInvoicePatientForAppointmentBooking(AppointmentBookingDTO appointment);

Appointment requestTobookAppointmentWithDiagnosticecnter(AppointmentDTOForDiagnosticcenter appointmentdto,
		RELATIONSHIP_TYPE relationship_TYPE);
}
