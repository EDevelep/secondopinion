package org.secondopinion.superadmin.dto; 


import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table;

import org.secondopinion.superadmin.domain.BaseMenu; 





@SuppressWarnings({ "serial"})
@Entity 
@Table (name="menu")
public class Menu extends BaseMenu{
}