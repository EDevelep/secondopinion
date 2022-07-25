package org.secondopinion.pharmacy.dto; 


import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table;

import org.secondopinion.pharmacy.domain.BaseAddress; 




@SuppressWarnings({ "serial"})
@Entity 
@Table (name="address")
public class Address extends BaseAddress{
}