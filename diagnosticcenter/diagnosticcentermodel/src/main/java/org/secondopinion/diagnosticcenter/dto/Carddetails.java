package org.secondopinion.diagnosticcenter.dto; 


import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table; 

import org.secondopinion.diagnosticcenter.domain.BaseCarddetails; 



@SuppressWarnings({ "serial"})
@Entity 
@Table (name="carddetails")
public class Carddetails extends BaseCarddetails{
}