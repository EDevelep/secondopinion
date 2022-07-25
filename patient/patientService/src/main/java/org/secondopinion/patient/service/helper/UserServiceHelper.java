package org.secondopinion.patient.service.helper;

import java.util.HashMap;
import java.util.Map;

import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.service.impl.EMAIL_TEMPLATES;
import org.secondopinion.utils.EmailUtil;
import org.secondopinion.utils.MailProperties;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.EmailUtil.MailContentEnum;

public class UserServiceHelper {

	public static Map<String, String> prepareModelForOTP(Integer otp, String name) {
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
		model.put(MailContentEnum.NAME.name(), name);
		return model;
	}

	public static void sendEmailOTP(final User user, Integer otp, final MailProperties properties) {
		Map<String, String> model = prepareModelForOTP(otp, user.getFullName());
		
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(user, properties), "CureMetric Email Verification",
				EMAIL_TEMPLATES.EMAIL_VERIFICATION.getTemplateName(), model);
	}

	public static MailProperties getMailProperties(User user, MailProperties mailProperties) {
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(user.getEmailId());
		return properties;
	}
	
	public static void sendAccountActivationEmail(final User user, String loginLinkForUI, final MailProperties mailProperties) {
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.LINK.name(), "/" + loginLinkForUI);
		model.put(MailContentEnum.NAME.name(), user.getFullName());

		EmailUtil.sendEmailWithHtmlContent(getMailProperties(user, mailProperties), "CureMetric Account activated ",
				EMAIL_TEMPLATES.MAIL_LOGIN.getTemplateName(), model);
	}

	
	public static void sendEmailVerificationMail(final User user, String verificationId, final MailProperties mailProperties, String emailVerificationLink) {
		// will send email verification link
		if (StringUtil.isNullOrEmpty(user.getUiHostURL())) {
			throw new IllegalArgumentException("uiHostURL field can not be null.");
		}
		String name = user.getFirstName() + " " + user.getLastName();
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.LINK.name(),
				String.format(emailVerificationLink, verificationId, user.getUiHostURL()));
		model.put(MailContentEnum.NAME.name(), name);

		
		String classpathEmailTemplate = "classpath:mail-emailverification.html";
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(user, mailProperties), "CureMetric Email Verification",
				classpathEmailTemplate, model);
	}

	public static void sendPhoneOTP(final User user, Integer otp, final MailProperties properties) {
		Map<String, String> model = prepareModelForOTP(otp, user.getFullName());
		
		EmailUtil.sendEmailWithHtmlContent(getMailProperties(user, properties), "CureMetric Phone Number Verification",
				EMAIL_TEMPLATES.PHONE_VERIFICATION.getTemplateName(), model);
	}


}
