package org.secondopinion.superadmin.dto; 


import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table;

import org.secondopinion.superadmin.domain.BasePrivilege; 





@SuppressWarnings({ "serial"})
@Entity 
@Table (name="privilege")
public class Privilege extends BasePrivilege{
}