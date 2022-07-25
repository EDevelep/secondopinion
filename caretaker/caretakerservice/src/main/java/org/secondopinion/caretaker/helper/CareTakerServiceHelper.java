package org.secondopinion.caretaker.helper;

import java.util.HashMap;
import java.util.Map;

import org.secondopinion.caretaker.dto.Caretaker;
import org.secondopinion.utils.EmailUtil;
import org.secondopinion.utils.MailProperties;
import org.secondopinion.utils.EmailUtil.MailContentEnum;

public class CareTakerServiceHelper {

	public static Map<String, String> prepareModelForOTP(Integer otp, String name) {
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.OTP.name(), String.valueOf(otp));
		model.put(MailContentEnum.NAME.name(), name);
		return model;
	}

	public static void sendPhoneOTP(final Caretaker caretaker, Integer otp, final MailProperties properties) {
		Map<String, String> model = prepareModelForOTP(otp, caretaker.getFullName());

		EmailUtil.sendEmailWithHtmlContent(getMailProperties(caretaker, properties),
				"CureMetric Phone Number Verification", EMAIL_TEMPLATES.PHONE_VERIFICATION.getTemplateName(), model);
	}

	public static void sendEmailOTP(final Caretaker caretaker, Integer otp, final MailProperties properties) {
		Map<String, String> model = prepareModelForOTP(otp, caretaker.getFullName());

		EmailUtil.sendEmailWithHtmlContent(getMailProperties(caretaker, properties),
				"CureMetric Phone Number Verification", EMAIL_TEMPLATES.EMAIL_VERIFICATION.getTemplateName(), model);
	}

	public static MailProperties getMailProperties(Caretaker caretaker, MailProperties mailProperties) {
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(caretaker.getEmailId());
		return properties;
	}

	public static void sendAccountActivationEmail(Caretaker caretaker, String loginLinkForUI,
			MailProperties mailProperties) {
		Map<String, String> model = new HashMap<>();
		model.put(MailContentEnum.LINK.name(), "/" + loginLinkForUI);
		model.put(MailContentEnum.NAME.name(), caretaker.getFullName());

		EmailUtil.sendEmailWithHtmlContent(getMailProperties(caretaker, mailProperties),
				"CureMetric Account activated ", EMAIL_TEMPLATES.MAIL_LOGIN.getTemplateName(), model);

	}
}
