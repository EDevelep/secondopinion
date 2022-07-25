package org.secondopinion.reports.dto; 


import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.secondopinion.reports.domain.BaseDashboardRuleAssociation; 



@SuppressWarnings({ "serial"})
@Entity 
@Table (name="dashboardruleassociation")
public class DashboardRuleAssociation extends BaseDashboardRuleAssociation{
}