package org.secondopinion.userMgmt.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.userMgmt.dto.CompanyKey;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CompanyKeyDAOHibernateImpl extends BaseCompanyKeyDAOHibernate{

	@Override
	@Transactional(readOnly=true)
	public CompanyKey getKeyForCompany(Integer companyId) {
		Criterion criterion = Restrictions.eq(CompanyKey.FIELD_companyId, companyId);
		
		List<CompanyKey> companyKeys = findByCrieria(criterion);
		
		if(companyKeys != null && companyKeys.size() >0){
			return companyKeys.get(0);
		}
		return null;
	}
}