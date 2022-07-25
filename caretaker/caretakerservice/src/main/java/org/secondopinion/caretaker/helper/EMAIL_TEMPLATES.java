package org.secondopinion.caretaker.helper;

public enum EMAIL_TEMPLATES {
	MAIL_LOGIN("classpath:mail-login.html"), EMAIL_VERIFICATION("classpath:mail-emailverification.html"),
	RESET_PASSWORD("classpath:mail-resetpwd.html"), PHONE_VERIFICATION("classpath:mail-phoneVerification.html"),
	ASSOCIATEUSER_EMAIL_VERIFICATION("classpath:accosateuser-emailverification.html")
	;
	
	private final String templateName;
	
	private EMAIL_TEMPLATES(String templateName) {
		this.templateName = templateName;
	}
	
	public String getTemplateName() {
		return templateName;
	}
}
