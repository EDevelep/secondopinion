package org.secondopinion.doctor.dto; 


import javax.persistence.Entity; 
import javax.persistence.Table; 

import org.secondopinion.doctor.domain.BaseCertification; 



@Entity 
@Table (name="certification")
public class Certification extends BaseCertification{
}