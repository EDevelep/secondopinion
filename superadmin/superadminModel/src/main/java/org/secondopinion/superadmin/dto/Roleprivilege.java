package org.secondopinion.superadmin.dto; 


import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table;

import org.secondopinion.superadmin.domain.BaseRoleprivilege; 





@SuppressWarnings({ "serial"})
@Entity 
@Table (name="roleprivilege")
public class Roleprivilege extends BaseRoleprivilege{
}