package org.secondopinion.diagnosticcenter.service;

import java.util.List;

import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenteruserUpdateDTO;
import org.secondopinion.diagnosticcenter.request.dto.AppointmentRequestDTO;
import org.secondopinion.diagnosticcenter.request.dto.PatientConfirmRequestDTO;

public interface IDiagnosticCenterUserService {

	void updateUserRole(DiagnosticcenteruserUpdateDTO diagnosticCenteruser);

	public Diagnosticcenteruser login(String userName, String password);

	public void createUser(Diagnosticcenteruser user, List<String> roleNames, boolean createFromDC);

	public void updateUser(Diagnosticcenteruser user);

	public void createAppointment(AppointmentRequestDTO appointmentRequest);

	public void cancelAppointment(AppointmentRequestDTO appointmentRequest);

	public void rescheduleAppointment(AppointmentRequestDTO appointmentRequest);

	public void confirmAppointment(PatientConfirmRequestDTO appointmentRequest);
}
