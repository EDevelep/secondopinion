package org.secondopinion.userMgmt.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.userMgmt.dto.CompanyConfiguration;
import org.secondopinion.userMgmt.dto.CompanyTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CompanyConfigurationDAOHibernateImpl extends BaseCompanyConfigurationDAOHibernate{

	@Override
	@Transactional(readOnly=true)
	public List<CompanyConfiguration> getCompanyConfigurations(Integer companyId) {
		Criterion criteria = Restrictions.eq(CompanyTemplate.FIELD_companyId, companyId);
		
		return findByCrieria(criteria);
	}
}