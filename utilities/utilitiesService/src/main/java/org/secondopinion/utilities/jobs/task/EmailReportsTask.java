package org.secondopinion.utilities.jobs.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.secondopinion.userMgmt.dto.Company;
import org.springframework.beans.factory.annotation.Autowired;

import org.secondopinion.utilities.jobs.dto.FlowInfo;
import org.secondopinion.utils.EmailUtil;
import org.secondopinion.utils.MailProperties;
import org.secondopinion.utils.StringUtil;

public class EmailReportsTask extends  VCBaseTask<List<EmailReportData>, Void>{

	//private EmailTemplateService emailTemplateService;
	@Autowired
	private MailProperties mailProperties;
	public MailProperties getMailProperties() {
		return mailProperties;
	}

	public void setMailProperties(MailProperties mailProperties) {
		this.mailProperties = mailProperties;
	}
	
	
	
	@Override
	public Void execute(List<EmailReportData> input, FlowInfo fi) {
		
		input.forEach(t -> emailReport(t));
		
		return null;
	}

	private void emailReport(EmailReportData t) {
		Company company = t.getCompany();
		Map<String, Object> params = new HashMap<>();
		params.put("reportsData", t);
		
		String emailString = "";//emailTemplateService.applyTemplate(company.getCompanyId(), COMPANY_TEMPLATE.SUBMISSION_REPORT, params);
		MailProperties props = mailProperties.clone();
		
		if(!StringUtil.isNullOrEmpty(company.getAlternateEmailForMailForwarding()))
			props.addToRecipient(company.getAlternateEmailForMailForwarding());
	
		props.setFromAddress("admin@partner2hire.com");
		props.addToRecipient(company.getEmailId());
		
		String classpathEmailTemplate = null; //"classpath:forgot-password-email-template.html";
//		try {
//			//EmailUtil.INSTANCE.sendEmail(props, "Daily Report", emailString, classpathEmailTemplate);
//		} catch (MessagingException e1) {
//			throw new RuntimeException(e1);
//		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

//	public EmailTemplateService getEmailTemplateService() {
//		return emailTemplateService;
//	}
//
//	public void setEmailTemplateService(EmailTemplateService emailTemplateService) {
//		this.emailTemplateService = emailTemplateService;
//	}
}