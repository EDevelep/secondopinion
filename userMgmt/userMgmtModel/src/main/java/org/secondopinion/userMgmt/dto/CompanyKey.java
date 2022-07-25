package org.secondopinion.userMgmt.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.userMgmt.domain.BaseCompanyKey; 



@SuppressWarnings({ "serial"})
@Entity 
@Table (name="companykey")
public class CompanyKey extends BaseCompanyKey{
}