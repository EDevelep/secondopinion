package org.secondopinion.patient.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.patient.domain.BaseAddress; 





@Entity 
@Table (name="address")
public class Address extends BaseAddress{

}