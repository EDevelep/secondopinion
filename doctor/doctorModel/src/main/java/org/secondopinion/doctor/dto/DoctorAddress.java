package org.secondopinion.doctor.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.doctor.domain.BaseDoctorAddress; 

@Entity 
@Table (name="address")
public class DoctorAddress extends BaseDoctorAddress{
}