package org.secondopinion.reports.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.reports.domain.BaseReportRoleAssociation; 



@SuppressWarnings({ "serial"})
@Entity 
@Table (name="reportroleassociation")
public class ReportRoleAssociation extends BaseReportRoleAssociation{
}