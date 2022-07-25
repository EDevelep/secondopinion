package org.secondopinion.doctor.dto; 


import javax.persistence.Entity; 
import javax.persistence.Table;
import javax.persistence.Transient;


import org.secondopinion.doctor.domain.BasePatientRequest; 


@Entity 
@Table (name="patientrequest")
public class PatientRequest extends BasePatientRequest{
	
	private String patientName;
	
	
	@Transient
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}



	public static PatientRequest build(Appointment appointment) {
		PatientRequest patientRequest = new PatientRequest();
		patientRequest.setDoctorId(appointment.getDoctorId());
		patientRequest.setPatientId(appointment.getUserId());
		patientRequest.setNewRequest('N');
		patientRequest.setRequestAccepted('N');
		patientRequest.setDescription(appointment.getDescription());
		patientRequest.setAlignment(null);
		return patientRequest;
	}
}