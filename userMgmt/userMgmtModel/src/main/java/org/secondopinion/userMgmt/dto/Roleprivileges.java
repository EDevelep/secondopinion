package org.secondopinion.userMgmt.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.userMgmt.domain.BaseRoleprivileges; 



@SuppressWarnings({ "serial"})
@Entity 
@Table (name="roleprivileges")
public class Roleprivileges extends BaseRoleprivileges{
}