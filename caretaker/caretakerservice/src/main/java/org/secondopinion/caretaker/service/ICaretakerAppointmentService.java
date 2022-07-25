package org.secondopinion.caretaker.service;

import java.util.List;
import java.util.Map;

import org.secondopinion.caretaker.dto.Appointment;
import org.secondopinion.caretaker.dto.AppointmentSearchRequest;
import org.secondopinion.caretaker.dto.UpdateDoctorAppointmentStatus;
import org.secondopinion.caretaker.dto.ViewAppointments;
import org.secondopinion.caretaker.dto.ViewAppointments.ViewAppointmentEnum;
import org.secondopinion.request.Response;

import com.twilio.rest.video.v1.Room;
import com.twilio.rest.video.v1.Room.RoomStatus;
import com.twilio.rest.video.v1.room.Participant;
import com.twilio.rest.video.v1.room.Participant.Status;

public interface ICaretakerAppointmentService {

	Appointment appointmentDetails(Long appointmentId);

	Response<List<Appointment>> getAppointmentsByCaretakerId(Long caretakerId);

	Appointment saveAppointment(Appointment appointment);
	
	Appointment updateAppointment(Appointment appointment);

	Response<List<Appointment>> getAllAppointmentBySearchCritieria(AppointmentSearchRequest appointmentSearchRequest);


	

	

	Room retrieiveRoomStatusByRoomName(String roomname);

	Room retrieiveRoomStatusByRoomSID(String roomSID);

	Participant retrieveAParticipant(String roomname, String participantname);

	Participant removeAParticipant(String roomname, String participantname);

	List<Participant> participantsBasedOnStatus(String roomname, Status participantstatus);

	Room updateTheRoomStatus(String roomSID, RoomStatus roomstatus);

	Appointment joinroom(Long appointmentId);

	List<Participant> retrieveRoomParticipants(String roomSID);

	void updateAppointmentStatusUponPatientRejectsTheRequest(Long entityAppointmentId, String status);

	Map<ViewAppointmentEnum, Response<List<Appointment>>> myAppointments(ViewAppointments viewAppointments);

	void updateAppointmentRequestsUponCaretakerChoice(UpdateDoctorAppointmentStatus appointment);

	void deletAppointment(Long appointmentId);



}
