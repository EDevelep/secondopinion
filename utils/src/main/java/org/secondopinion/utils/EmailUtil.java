package org.secondopinion.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.CollectionUtils;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * 
 * @author SreeLakshmi on 31/05/2017
 *
 */
public class EmailUtil {
//PartentUsername
	public enum MailContentEnum {
		USERNAME("${username}"), NAME("${name}"), LINK("${link}"), LINK1("${link1}"), OTP("${otp}"),
		APPOINTMENTID("${appointmentId}"), SCHUDLEDATE("${schudleDate}"), AMOUNT("${amount}"),
		REFUNDEDAMOUNT("${refundedamount}"), CANCELLE_NAME("${canclleName}"),USERID("${userId}"),PartentUsername("${PartentUsername}");

		private String replaceKey;

		private MailContentEnum(String replaceKey) {
			this.replaceKey = replaceKey;
		}

		public String getReplaceKey() {
			return replaceKey;
		}
	}

	private static final String CHARSET = "utf-8";
	private static final String SUBTYPE = "html";

	@Autowired
	ResourceLoader resourceLoader;

	public static Properties buildProperties(MailProperties mailProps) {
		Properties props = System.getProperties();
		props.put("mail.smtp.ssl.trust", mailProps.getSmtpHost());
		props.put("mail.smtp.port", mailProps.getSmtpPort());
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.user", mailProps.getSmtpUser());
		props.put("mail.smtp.password", mailProps.getPassword());
		props.put("mail.smtp.auth", "true");

		return props;
	}

	public static void sendEmailWithHtmlContentFroRescheduleAppointment(MailProperties mailProps, String subject,
			String classPathHtmlTemplate, Map<String, String> model) {
		Properties props = buildProperties(mailProps);
		Session session = Session.getDefaultInstance(props);

		String content = "";
		if (classPathHtmlTemplate != null) {
			content = getHtmlTemplateAsString(classPathHtmlTemplate);
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.NAME.name()))) {
			content = content.replace(MailContentEnum.NAME.getReplaceKey(), model.get(MailContentEnum.NAME.name()));
		}

		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.SCHUDLEDATE.name()))) {
			content = content.replace(MailContentEnum.SCHUDLEDATE.getReplaceKey(),
					model.get(MailContentEnum.SCHUDLEDATE.name()));
		}

		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.APPOINTMENTID.name()))) {
			content = content.replace(MailContentEnum.APPOINTMENTID.getReplaceKey(),
					model.get(MailContentEnum.APPOINTMENTID.name()));
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.AMOUNT.name()))) {
			content = content.replace(MailContentEnum.AMOUNT.getReplaceKey(), model.get(MailContentEnum.AMOUNT.name()));
		}

		MimeMessage msg = buildMimeMsg(mailProps.getFromAddress(), mailProps.getToRecipients(), session, subject);
		sendMail(mailProps, content, session, msg);
	}

	public static void sendEmailWithHtmlContentFroCancellAppointment(MailProperties mailProps, String subject,
			String classPathHtmlTemplate, Map<String, String> model) {
		Properties props = buildProperties(mailProps);
		Session session = Session.getDefaultInstance(props);

		String content = "";
		if (classPathHtmlTemplate != null) {
			content = getHtmlTemplateAsString(classPathHtmlTemplate);
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.NAME.name()))) {
			content = content.replace(MailContentEnum.NAME.getReplaceKey(), model.get(MailContentEnum.NAME.name()));
		}

		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.SCHUDLEDATE.name()))) {
			content = content.replace(MailContentEnum.SCHUDLEDATE.getReplaceKey(),
					model.get(MailContentEnum.SCHUDLEDATE.name()));
		}

		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.APPOINTMENTID.name()))) {
			content = content.replace(MailContentEnum.APPOINTMENTID.getReplaceKey(),
					model.get(MailContentEnum.APPOINTMENTID.name()));
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.AMOUNT.name()))) {
			content = content.replace(MailContentEnum.AMOUNT.getReplaceKey(), model.get(MailContentEnum.AMOUNT.name()));
		}

		MimeMessage msg = buildMimeMsg(mailProps.getFromAddress(), mailProps.getToRecipients(), session, subject);
		sendMail(mailProps, content, session, msg);
	}

	public static void sendEmailWithHtmlContentFroAppointment(MailProperties mailProps, String subject,
			String classPathHtmlTemplate, Map<String, String> model) {
		Properties props = buildProperties(mailProps);
		Session session = Session.getDefaultInstance(props);

		String content = "";
		if (classPathHtmlTemplate != null) {
			content = getHtmlTemplateAsString(classPathHtmlTemplate);
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.NAME.name()))) {
			content = content.replace(MailContentEnum.NAME.getReplaceKey(), model.get(MailContentEnum.NAME.name()));
		}

		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.SCHUDLEDATE.name()))) {
			content = content.replace(MailContentEnum.SCHUDLEDATE.getReplaceKey(),
					model.get(MailContentEnum.SCHUDLEDATE.name()));
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.AMOUNT.name()))) {
			content = content.replace(MailContentEnum.AMOUNT.getReplaceKey(), model.get(MailContentEnum.AMOUNT.name()));
		}

		MimeMessage msg = buildMimeMsg(mailProps.getFromAddress(), mailProps.getToRecipients(), session, subject);
		sendMail(mailProps, content, session, msg);
	}


	public static void sendEmailWithHtmlContentForCancleAppointment(MailProperties mailProps, String subject,
			String classPathHtmlTemplate, Map<String, String> model) {
		Properties props = buildProperties(mailProps);
		Session session = Session.getDefaultInstance(props);

		String content = "";
		if (classPathHtmlTemplate != null) {
			content = getHtmlTemplateAsString(classPathHtmlTemplate);
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.NAME.name()))) {
			content = content.replace(MailContentEnum.NAME.getReplaceKey(), model.get(MailContentEnum.NAME.name()));
		}

		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.USERNAME.name()))) {
			content = content.replace(MailContentEnum.USERNAME.getReplaceKey(), model.get(MailContentEnum.USERNAME.name()));
		}

		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.SCHUDLEDATE.name()))) {
			content = content.replace(MailContentEnum.SCHUDLEDATE.getReplaceKey(),
					model.get(MailContentEnum.SCHUDLEDATE.name()));
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.AMOUNT.name()))) {
			content = content.replace(MailContentEnum.AMOUNT.getReplaceKey(), model.get(MailContentEnum.AMOUNT.name()));
		}

		MimeMessage msg = buildMimeMsg(mailProps.getFromAddress(), mailProps.getToRecipients(), session, subject);
		sendMail(mailProps, content, session, msg);
	}

	
	public static void sendEmailWithHtmlContentForReschudleAppointment(MailProperties mailProps, String subject,
			String classPathHtmlTemplate, Map<String, String> model) {
		Properties props = buildProperties(mailProps);
		Session session = Session.getDefaultInstance(props);

		String content = "";
		if (classPathHtmlTemplate != null) {
			content = getHtmlTemplateAsString(classPathHtmlTemplate);
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.NAME.name()))) {
			content = content.replace(MailContentEnum.NAME.getReplaceKey(), model.get(MailContentEnum.NAME.name()));
		}

		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.SCHUDLEDATE.name()))) {
			content = content.replace(MailContentEnum.SCHUDLEDATE.getReplaceKey(),
					model.get(MailContentEnum.SCHUDLEDATE.name()));
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.USERNAME.name()))) {
			content = content.replace(MailContentEnum.USERNAME.getReplaceKey(), model.get(MailContentEnum.USERNAME.name()));
		}

		MimeMessage msg = buildMimeMsg(mailProps.getFromAddress(), mailProps.getToRecipients(), session, subject);
		sendMail(mailProps, content, session, msg);
	}

	public static String getHtmlTemplateAsString(String template) {
		InputStream dbAsStream = null;
		try {
			ResourceLoader defaultresourceLoader = new DefaultResourceLoader();
			Resource resource = defaultresourceLoader.getResource(template);
			dbAsStream = resource.getInputStream();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new BufferedReader(new InputStreamReader(dbAsStream)).lines().collect(Collectors.joining("\n"));
	}

	public static void sendEmailWithHtmlContent(MailProperties mailProps, String subject, String classPathHtmlTemplate,
			Map<String, String> model) {
		Properties props = buildProperties(mailProps);
		Session session = Session.getDefaultInstance(props);

		String content = "";
		if (classPathHtmlTemplate != null) {
			content = getHtmlTemplateAsString(classPathHtmlTemplate);
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.NAME.name()))) {
			content = content.replace(MailContentEnum.NAME.getReplaceKey(), model.get(MailContentEnum.NAME.name()));
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.OTP.name()))) {
			content = content.replace(MailContentEnum.OTP.getReplaceKey(), model.get(MailContentEnum.OTP.name()));
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.LINK.name()))) {
			content = content.replace(MailContentEnum.LINK.getReplaceKey(), model.get(MailContentEnum.LINK.name()));
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.LINK.name()))) {
			content = content.replace(MailContentEnum.LINK.getReplaceKey(), model.get(MailContentEnum.LINK.name()));
		}
		MimeMessage msg = buildMimeMsg(mailProps.getFromAddress(), mailProps.getToRecipients(), session, subject);
		sendMail(mailProps, content, session, msg);
	}

	public static void sendAsscotedEmailWithHtmlContent(MailProperties mailProps, String subject,
			String classPathHtmlTemplate, Map<String, String> model) {
		Properties props = buildProperties(mailProps);
		Session session = Session.getDefaultInstance(props);

		String content = "";
		if (classPathHtmlTemplate != null) {
			content = getHtmlTemplateAsString(classPathHtmlTemplate);
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.NAME.name()))) {
			content = content.replace(MailContentEnum.NAME.getReplaceKey(), model.get(MailContentEnum.NAME.name()));
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.PartentUsername.name()))) {
			content = content.replace(MailContentEnum.PartentUsername.getReplaceKey(), model.get(MailContentEnum.PartentUsername.name()));
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.OTP.name()))) {
			content = content.replace(MailContentEnum.OTP.getReplaceKey(), model.get(MailContentEnum.OTP.name()));
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.LINK.name()))) {
			content = content.replace(MailContentEnum.LINK.getReplaceKey(), model.get(MailContentEnum.LINK.name()));
		}
		if (!StringUtil.isNullOrEmpty(model.get(MailContentEnum.LINK1.name()))) {
			content = content.replace(MailContentEnum.LINK1.getReplaceKey(), model.get(MailContentEnum.LINK1.name()));
		}
		MimeMessage msg = buildMimeMsg(mailProps.getFromAddress(), mailProps.getToRecipients(), session, subject);
		sendMail(mailProps, content, session, msg);
	}

	public static void sendMail(MailProperties mailProps, String content, Session session, MimeMessage msg) {
		try {
			msg.setText(content, CHARSET, SUBTYPE);

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(content, "text/html");

			Transport transport = session.getTransport();
			transport.connect(mailProps.getSmtpHost(), mailProps.getSmtpUser(), mailProps.getPassword());

			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (MessagingException mse) {
			mse.printStackTrace();
		}
	}

	public static MimeMessage buildMimeMsg(String fromAddress, List<String> reciepents, Session session,
			String subject) {

		MimeMessage msg = null;
		try {
			msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromAddress, false));

			if (CollectionUtils.isEmpty(reciepents)) {
				new IllegalArgumentException("To address can not be empty");
			}
			Address[] toAddrersses = new InternetAddress[reciepents.size()];
			for (int i = 0; i < toAddrersses.length; i++) {
				toAddrersses[i] = new InternetAddress(reciepents.get(i));
			}

			msg.setRecipients(Message.RecipientType.TO, toAddrersses);
			msg.setSubject(subject);

			msg.setSentDate(new Date());

		} catch (MessagingException mse) {
			mse.printStackTrace();
		}
		return msg;
	}

	public static void sendMail(MailProperties mailProps, String body, String subject) {
		Properties props = buildProperties(mailProps);
		Session session = Session.getDefaultInstance(props);
		MimeMessage msg = buildMimeMsg(mailProps.getFromAddress(), mailProps.getToRecipients(), session, subject);

		sendMail(mailProps, body, session, msg);
	}

	public static void sendErrorMessageMail(MailProperties mailProps, String subject, String toRecipients,
			String body) {

		if (StringUtil.isNullOrEmpty(toRecipients)) {
			return;
		}
		mailProps.setToRecipients(StringUtil.trimEmptySpaceAndSplitWithDelimeter(toRecipients));
		sendMail(mailProps, body, subject);

	}
}
