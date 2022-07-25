package org.secondopinion.reports.dao.impl.hibernate;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.reports.dto.CompanyTemplate;
import org.secondopinion.reports.dto.CompanyTemplate.DOC_TEMPLATE;
import org.springframework.transaction.annotation.Transactional;

public class CompanyTemplateDAOHibernateImpl extends BaseCompanyTemplateDAOHibernate{

	@Override
	@Transactional(readOnly=true)
	public List<CompanyTemplate> getTemplatesForCompany(Integer companyId) {
		Criterion criteria = Restrictions.eq(CompanyTemplate.FIELD_companyId, companyId);
		return findByCrieria(criteria);
	}

	@Override
	@Transactional(readOnly=true)
	public CompanyTemplate getDocTemplateForCompany(Integer companyId, DOC_TEMPLATE template) {
		Criterion criteria = Restrictions.eq(CompanyTemplate.FIELD_companyId, companyId);
		Criterion templateTypeCriteria = Restrictions.eq(CompanyTemplate.FIELD_templateType, template.name());
		
		Criterion andCriteria = Restrictions.and(criteria, templateTypeCriteria);
		
		List<CompanyTemplate> templates =  findByCrieria(andCriteria);
		
		if(templates != null && templates.size()>0){
			return templates.get(0);
		}
		
		if(companyId!=0){
			//This is to get the default template.
			return getDocTemplateForCompany(0, template);
		}
		
		return null;
	}
}