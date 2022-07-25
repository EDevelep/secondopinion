package org.secondopinion.userMgmt.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.userMgmt.domain.BaseCompanyConfiguration; 



@SuppressWarnings({ "serial"})
@Entity 
@Table (name="companyconfiguration")
public class CompanyConfiguration extends BaseCompanyConfiguration{
}