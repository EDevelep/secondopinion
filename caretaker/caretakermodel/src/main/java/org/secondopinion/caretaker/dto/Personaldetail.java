package org.secondopinion.caretaker.dto; 


import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table;

import org.secondopinioncaretaker.domain.BasePersonaldetail;






@SuppressWarnings({ "serial"})
@Entity 
@Table (name="personaldetail")
public class Personaldetail extends BasePersonaldetail{
}