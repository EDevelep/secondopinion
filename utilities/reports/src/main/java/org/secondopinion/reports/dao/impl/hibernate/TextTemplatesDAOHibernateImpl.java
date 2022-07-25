package org.secondopinion.reports.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.reports.dto.TextTemplates;
import org.springframework.transaction.annotation.Transactional;

public class TextTemplatesDAOHibernateImpl extends BaseTextTemplatesDAOHibernate{

	@Override
	@Transactional(readOnly=true)
	public TextTemplates getTemplate(String level, Long companyId, String templateName) {
		List<Criterion> criteria = new ArrayList<Criterion>();
		
		criteria.add(Restrictions.eq(TextTemplates.FIELD_referenceId, companyId));
		criteria.add(Restrictions.eq(TextTemplates.FIELD_level, level));
		criteria.add(Restrictions.eq(TextTemplates.FIELD_templateName, templateName));
		
		List<TextTemplates> templates = findByCrieria(criteria);
		
		if(templates != null && templates.size()>0){
			return templates.get(0);
		}
		
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public TextTemplates getDefaultTemplate(String level, String templateName) {
		List<Criterion> criteria = new ArrayList<Criterion>();
		
		criteria.add(Restrictions.eq(TextTemplates.FIELD_templateName, templateName));
		criteria.add(Restrictions.eq(TextTemplates.FIELD_level, level));
		criteria.add(Restrictions.eq(TextTemplates.FIELD_defaultTemplate, "Y"));
		
		List<TextTemplates> templates = findByCrieria(criteria);
		
		if(templates != null && templates.size()>0){
			return templates.get(0);
		}
		
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<TextTemplates> getTemplates(String level, Long refereceId) {
		List<Criterion> criteria = new ArrayList<Criterion>();
		
		criteria.add(Restrictions.eq(TextTemplates.FIELD_level, level));
		criteria.add(Restrictions.eq(TextTemplates.FIELD_referenceId, refereceId));
		
		return findByCrieria(criteria);
	}
}