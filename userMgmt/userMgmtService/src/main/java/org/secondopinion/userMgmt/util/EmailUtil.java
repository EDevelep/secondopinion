package org.secondopinion.userMgmt.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.secondopinion.utils.MailProperties;

public class EmailUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmailUtil.class);
	
	public static void sendEmail(MailProperties mailProperties, String subject, String emailBody, boolean sendAsync) {
		if(sendAsync){
			new Thread( new Runnable() {
			   
				@Override
				public void run() {
					sendEmail(mailProperties, subject, emailBody);
				}
			}).start();
		}else{
			sendEmail(mailProperties, subject, emailBody);
		}
	}
	
	public static void sendEmail(MailProperties mailProperties, String subject, String emailBody) {
		Properties mailServerProperties = mailProperties.getEmailServerPoperties();
		Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		
		Message generateMailMessage = new MimeMessage(getMailSession);
        try {

            if (mailProperties.isOverride()) {
            	
                generateMailMessage.setFrom(new InternetAddress(mailProperties.getFromAddress()));
                generateMailMessage.addRecipient(Message.RecipientType.TO,
                        new InternetAddress("partner2hire@gmail.com"));
            } else {
                generateMailMessage.setFrom(new InternetAddress(mailProperties.getFromAddress()));
                for (String emailId : mailProperties.getToRecipients()) {
                    generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
                }
            }
            for (String emailId : mailProperties.getCcedRecipients()) {
                generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(emailId));
            }

            for (String emailId : mailProperties.getBccedRecipients()) {
                generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(emailId));
            }
            generateMailMessage.setSubject(subject);

            Multipart multipart = new MimeMultipart();

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(emailBody, "text/html");
            multipart.addBodyPart(messageBodyPart);

            if (mailProperties.hasAttachements()) {
                for (String attachment : mailProperties.getAttachments()) {
                    MimeBodyPart attachPart = new MimeBodyPart();
                    DataSource dataSource = new FileDataSource(attachment);

                    attachPart.setDataHandler(new DataHandler(dataSource));
                    attachPart.setFileName(dataSource.getName());
                    multipart.addBodyPart(attachPart);
                }
            }

            generateMailMessage.setContent(multipart);
            Transport transport = getMailSession.getTransport("smtp");

            try {
                transport.connect(mailProperties.getSmtpHost(), mailProperties.getSmtpUser(),
                        mailProperties.getPassword());
            } catch (Exception ex) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
                transport.connect(mailProperties.getSmtpHost(), mailProperties.getSmtpUser(),
                        mailProperties.getPassword());
            }

            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
        }catch(Exception ex){
			LOG.error("Error Sending email: ", ex);
			new IllegalArgumentException("Error sending email: " + ex.getMessage(), ex);
		}
	}
}