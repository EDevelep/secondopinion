package org.secondopinion.userMgmt.dao; 

import java.util.List;

import org.secondopinion.userMgmt.dto.CompanyTemplate;
import org.secondopinion.userMgmt.dto.CompanyTemplate.DOC_TEMPLATE;

import org.secondopinion.dao.IDAO;

public interface CompanyTemplateDAO extends IDAO<CompanyTemplate,Long >{
	public List<CompanyTemplate> getTemplatesForCompany(Integer companyId);

	public CompanyTemplate getDocTemplateForCompany(Integer companyId, DOC_TEMPLATE template);
}