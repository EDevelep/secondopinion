package org.secondopinion.patient.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.patient.domain.BaseMedicalinsurance; 




@Entity 
@Table (name="medicalinsurance")
public class Medicalinsurance extends BaseMedicalinsurance{
}