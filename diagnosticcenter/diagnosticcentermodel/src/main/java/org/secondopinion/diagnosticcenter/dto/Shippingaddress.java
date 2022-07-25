package org.secondopinion.diagnosticcenter.dto; 


import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table;

import org.secondopinion.diagnosticcenter.domain.BaseShippingaddress; 





@SuppressWarnings({ "serial"})
@Entity 
@Table (name="shippingaddress")
public class Shippingaddress extends BaseShippingaddress{
}