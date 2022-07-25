package org.secondopinion.doctor.dto; 


import javax.persistence.Entity; 
import javax.persistence.Table; 

import org.secondopinion.doctor.domain.BasePersonaldetail; 


@Entity 
@Table (name="personaldetail")
public class Personaldetail extends BasePersonaldetail{
}