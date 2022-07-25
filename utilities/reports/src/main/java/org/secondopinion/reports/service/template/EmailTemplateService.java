/*package org.secondopinion.reports.service.template;

import java.util.List;
import java.util.Map;

import org.secondopinion.reports.dao.TextTemplatesDAO;
import org.secondopinion.reports.dto.TextTemplates;
import org.secondopinion.reports.util.FreemarkerUtil;
import org.springframework.transaction.annotation.Transactional;

public class EmailTemplateService {
	public enum  COMPANY_TEMPLATE{HOT_LIST, INTERVIEW_SETUP,
		MISSING_TIMESHEET, REQUIREMENT_PUBLISH, RESOURCE_REQUIREMENT_PUBLISH,
		MSA, PO, MSA_PO, INVOICE, CONTRACT_FOLLOWUP, INVOICE_FOLLOWUP, 
		SUBMISSION_REPORT, COMPANY_REGISTRATION, ASSIGNED_REQUIREMENT,
		};
	
	public enum  CLIENT_TEMPLATE{
		PROFILE_SUBMISSION, PROFILE_BULK_SUBMISSION, PROFILE_SUBMISSION_FOLLOWUP, LEAD_SUBMISSION }
	
	public enum USER_TEMPLATE{
	    USER_LOGIN
	}
	
	private TextTemplatesDAO textTemplatesDAO;

	*//**
	 * @return the textTemplatesDAO
	 *//*
	public TextTemplatesDAO getTextTemplatesDAO() {
		return textTemplatesDAO;
	}

	*//**
	 * @param textTemplatesDAO the textTemplatesDAO to set
	 *//*
	public void setTextTemplatesDAO(TextTemplatesDAO textTemplatesDAO) {
		this.textTemplatesDAO = textTemplatesDAO;
	}
	
	
	public TextTemplates getTemplateForCompany(Integer companyId, COMPANY_TEMPLATE templateName){
		String level = "COMPANY";
		TextTemplates template = textTemplatesDAO.getTemplate(level, new Long(companyId), templateName.name());
		
		if(template == null){
			template = textTemplatesDAO.getDefaultTemplate(level, templateName.name());
		}
		
		return template;
	}
	
	public TextTemplates getTemplateForClient(Long clientId, CLIENT_TEMPLATE templateName){
		String level = "CLIENT";
		
		TextTemplates template = textTemplatesDAO.getTemplate(level, clientId, templateName.name());
		
		if(template == null){
			template = textTemplatesDAO.getDefaultTemplate(level, templateName.name());
		}
		
		return template;
	}
	
	public String applyTemplate(Integer companyId, COMPANY_TEMPLATE templateName, Map<String, Object> params){
		TextTemplates template = getTemplateForCompany(companyId, templateName);
		
//		System.out.println(template.getTemplateText());
		return FreemarkerUtil.applyTemplate(template.getTemplateText(), params);
	}
	
	public String applyTemplate(Long clientId, CLIENT_TEMPLATE templateName, Map<String, Object> params){
		TextTemplates template = getTemplateForClient(clientId, templateName);
		
		return FreemarkerUtil.applyTemplate(template.getTemplateText(), params);
	}

	public TextTemplates getDefaultTemplate(COMPANY_TEMPLATE template) {
		return textTemplatesDAO.getDefaultTemplate("COMPANY", template.name());
	}
	
	public TextTemplates getDefaultTemplate(CLIENT_TEMPLATE template) {
		return textTemplatesDAO.getDefaultTemplate("CLIENT", template.name());
	}
	
	@Transactional
	public void saveTemplate(TextTemplates template){
		textTemplatesDAO.save(template);
	}

	public List<TextTemplates> getAllTemplatesForCompany(Integer companyId) {
		return textTemplatesDAO.getTemplates("COMPANY", new Long(companyId));
	}
	
	public List<TextTemplates> getAllTemplatesForClient(Long clientId) {
		return textTemplatesDAO.getTemplates("CLIENT", clientId);
	}

    public String applyTemplate(USER_TEMPLATE templateName, Map<String, Object> params) {
        String level = "USER";
        TextTemplates template = textTemplatesDAO.getDefaultTemplate(level, templateName.name());
        return FreemarkerUtil.applyTemplate(template.getTemplateText(), params);

    }
}
*/