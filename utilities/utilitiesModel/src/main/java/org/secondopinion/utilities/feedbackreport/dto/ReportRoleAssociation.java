package org.secondopinion.utilities.feedbackreport.dto; 


import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table;

import org.secondopinion.utilities.feedbackreport.domain.BaseReportRoleAssociation; 




@Entity 
@Table (name="REPORTROLEASSOCIATION")
@SequenceGenerator(name="reportRoleAssociationSeq", sequenceName="REPORTROLEASSOCIATION_SEQ")
public class ReportRoleAssociation extends BaseReportRoleAssociation{
}