package org.secondopinion.patient.dto; 


import javax.persistence.Entity; 
import javax.persistence.Table;
import org.secondopinion.patient.domain.BasePatientratings; 

 



@Entity 
@Table (name="patientratings")
public class Patientratings extends BasePatientratings{
	
	public enum RatingForEnum {
		APPOINTMENT_BOOKING_PROCESS, DOCTOR_APPOINTMENT, PHARMACY_PRESCRIPTIONS, DIAGNOSTIC_CENTER_APPOINTMENT, 
	}
}