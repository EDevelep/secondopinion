package org.secondopinion.reports.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.reports.dto.CompanyTemplate;
import org.secondopinion.reports.dto.CompanyTemplate.DOC_TEMPLATE;

public interface CompanyTemplateDAO extends IDAO<CompanyTemplate,Long >{
	public List<CompanyTemplate> getTemplatesForCompany(Integer companyId);

	public CompanyTemplate getDocTemplateForCompany(Integer companyId, DOC_TEMPLATE template);
}