package org.secondopinion.diagnosticcenter.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.diagnosticcenter.domain.BaseCoupon; 



@Entity 
@Table (name="coupon")
public class Coupon extends BaseCoupon{
}