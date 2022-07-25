package org.secondopinion.patient.dto; 


import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table;

import org.secondopinion.patient.domain.BasePatientpreference; 





@SuppressWarnings({ "serial"})
@Entity 
@Table (name="patientpreference")
public class Patientpreference extends BasePatientpreference{
}