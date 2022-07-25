package org.secondopinion.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MailProperties {
	private String smtpPort;
	private boolean auth;
	private boolean starttlsEnable;
	private String smtpUser;
	private String password;
	private String smtpHost;
	private String smtpFromAddress;
	private String fromAddress;
	private boolean override;
	private String smtpPassword;
	private String host;
	
	private List<String> attachments = new ArrayList<String>();

	private List<String> toRecipients = new ArrayList<String>();
	private List<String> ccedRecipients = new ArrayList<String>();
	private List<String> bccedRecipients = new ArrayList<String>();

	public MailProperties(String smtpPort) {
		this.smtpPort = smtpPort;
		this.auth = true;
//		this.starttlsEnable = false;
		this.starttlsEnable = true;
		this.override = true;
	}
	
	public MailProperties(String smtpPort, boolean auth, boolean starttlsEnable, boolean override) {
		this.smtpPort = smtpPort;
		this.auth = auth;
		this.starttlsEnable = starttlsEnable;
		this.override = override;
	}
	
	public MailProperties(String port, String smtpUser, String password, String host, String fromAdress) {
		this(port) ;
		this.smtpUser =  smtpUser;
		this.password = password;
		this.smtpHost = host;
		this.fromAddress = fromAdress;
	}

	public List<String> getAttachments() {
		return attachments;
	}
	
	public boolean hasAttachements(){
		return attachments.size() > 0;
	}
	
	public void addToRecipient(String toRecipient) {
		toRecipients.add(toRecipient);
	}

	public void addCcedRecipient(String ccRecipient) {
		ccedRecipients.add(ccRecipient);
	}

	public void addBccedRecipient(String bccRecipient) {
		bccedRecipients.add(bccRecipient);
	}
	
	public void addBccedRecipients(List<String> recepients) {
		bccedRecipients.addAll(recepients);
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public boolean isStarttlsEnable() {
		return starttlsEnable;
	}

	public void setStarttlsEnable(boolean starttlsEnable) {
		this.starttlsEnable = starttlsEnable;
	}

	public List<String> getToRecipients() {
		return toRecipients;
	}

	public void setToRecipients(List<String> toRecipients) {
		this.toRecipients = toRecipients;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public List<String> getCcedRecipients() {
		return ccedRecipients;
	}

	public void setCcedRecipients(List<String> ccedRecipients) {
		this.ccedRecipients = ccedRecipients;
	}

	public List<String> getBccedRecipients() {
		return bccedRecipients;
	}

	public void setBccedRecipients(List<String> bccedRecipients) {
		this.bccedRecipients = bccedRecipients;
	}

	public String getSmtpUser() {
		return smtpUser;
	}

	public void setSmtpUser(String smtpUser) {
		this.smtpUser = smtpUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addAttachment(String file){
		this.attachments.add(file);
	}
	
    public boolean isOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    public Properties getEmailServerPoperties() {
		Properties mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", smtpPort);
		mailServerProperties.put("mail.smtp.auth", auth);
		mailServerProperties.put("mail.smtp.starttls.enable", starttlsEnable);
		mailServerProperties.put("mail.smtp.user", smtpUser);
		mailServerProperties.put("mail.smtp.password", password);
		mailServerProperties.put("mail.smtp.ssl.trust", smtpHost);
		
		
		if(!StringUtil.isNullOrEmpty(smtpFromAddress)){
			mailServerProperties.put("mail.smtp.from", smtpFromAddress);
		}
        if(!StringUtil.isNullOrEmpty(smtpPassword)){
            mailServerProperties.put("mail.smtp.bounceBack.password", smtpPassword);
        }
        if(!StringUtil.isNullOrEmpty(host)){
            mailServerProperties.put("mail.smtp.pop.host", host);
        }
		return mailServerProperties;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	
	@Override
	public MailProperties clone() {
		MailProperties mp = new MailProperties(smtpPort);
		
		mp.auth = auth;
		mp.starttlsEnable = starttlsEnable;
		mp.smtpUser = smtpUser;
		mp.password = password;
		mp.smtpHost = smtpHost;
		mp.fromAddress = fromAddress;
		mp.setSmtpFromAddress(smtpFromAddress);
		mp.setOverride(false);
		
		return mp;
	}

	/**
	 * @return the smtpFromAddress
	 */
	public String getSmtpFromAddress() {
		return smtpFromAddress;
	}

	/**
	 * @param smtpFromAddress the smtpFromAddress to set
	 */
	public void setSmtpFromAddress(String smtpFromAddress) {
		this.smtpFromAddress = smtpFromAddress;
	}

	public String getSmtpPassword() {
		return smtpPassword;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}	
	
	
}