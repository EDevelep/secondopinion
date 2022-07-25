package org.secondopinion.userMgmt.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.userMgmt.domain.BasePrivileges; 



@SuppressWarnings({ "serial"})
@Entity 
@Table (name="privileges")
public class Privileges extends BasePrivileges{
}