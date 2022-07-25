package org.secondopinion.userMgmt.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.userMgmt.domain.BaseRegistration; 



@SuppressWarnings({ "serial"})
@Entity 
@Table (name="registration")
public class Registration extends BaseRegistration{
}