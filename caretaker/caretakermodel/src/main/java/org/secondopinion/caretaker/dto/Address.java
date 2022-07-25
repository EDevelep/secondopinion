package org.secondopinion.caretaker.dto; 


import javax.persistence.Entity; 

import javax.persistence.Table;

import org.secondopinioncaretaker.domain.BaseAddress; 





@SuppressWarnings({ "serial"})
@Entity 
@Table (name="address")
public class Address extends BaseAddress{
}