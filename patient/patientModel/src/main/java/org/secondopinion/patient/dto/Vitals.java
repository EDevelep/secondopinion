package org.secondopinion.patient.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.patient.domain.BaseVitals; 




@Entity 
@Table (name="vitals")
public class Vitals extends BaseVitals{
}