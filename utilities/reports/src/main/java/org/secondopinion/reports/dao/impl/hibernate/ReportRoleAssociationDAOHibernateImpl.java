package org.secondopinion.reports.dao.impl.hibernate; 


import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.secondopinion.reports.dto.ReportRoleAssociation;
import org.springframework.transaction.annotation.Transactional;


public class ReportRoleAssociationDAOHibernateImpl extends BaseReportRoleAssociationDAOHibernate{

	@Override
	@Transactional(readOnly=true)
	public List<Long> getReportsForRoleIds(List<Integer> roleIds, Integer companyId) {
		List<ReportRoleAssociation> companyAssociations = findByCrieria(Restrictions.in(ReportRoleAssociation.FIELD_roleId, roleIds), 
				Restrictions.eq(ReportRoleAssociation.FIELD_companyId, companyId));
	
		List<Long> reportIds = new ArrayList<>();
		companyAssociations.stream().forEach(n -> reportIds.add(n.getReportId()));
		return reportIds;
	}
}
