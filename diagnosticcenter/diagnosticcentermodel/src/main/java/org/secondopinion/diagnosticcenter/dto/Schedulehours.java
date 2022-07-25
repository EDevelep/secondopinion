package org.secondopinion.diagnosticcenter.dto; 


import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table; 

import org.secondopinion.diagnosticcenter.domain.BaseSchedulehours; 



@SuppressWarnings({ "serial"})
@Entity 
@Table (name="schedulehours")
public class Schedulehours extends BaseSchedulehours{
}