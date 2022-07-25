package org.secondopinion.doctor.dto; 


import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table;

import org.secondopinion.doctor.domain.BaseStaticdata; 





@SuppressWarnings({ "serial"})
@Entity 
@Table (name="staticdata")
public class Staticdata extends BaseStaticdata{
}