package org.secondopinion.utils;

public class UserMgmtUtil {
	
	public static final String PASSWORD = "password";
	public static final String USER_NAME = "username";
	public static final String AUTHORIZATION = "user-session";
	public static final String MODULENAME = "moduleName";
	public static final String FORUSERID = "forUserId";
	public static final String UMS = "UMS_";
	public static final String UTF_8 = "UTF-8";
	public static final String HEADER = "header";
	public static final String LOGGING_ATTRIBUTE = "logging_attribute";
	public static final String START_TIME = "start_time";
	public static final String REQUEST_URL_MAP = "request_url_map";
	public static final String RESPONSE_BODY = "RESPONSE_BODY";
	public static final String REQUEST_BODY = "REQUEST_BODY";
	public static final String REQUEST_URL = "REQUEST_URL";
	public static final String EXECUTION_TIME = "EXECUTION_TIME";
	public static final String REQUEST_TIMEZONE = "REQUEST_TIMEZONE";
	
	 /**
     * Addresses to Ignore
     */
    public static String[] ignores = new String[]{
            //Interface to filter swagger related requests, otherwise swagger will prompt base-url to be intercepted
            "/swagger-resources",
            "/v2/api-docs",
            "/favicon.ico",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars",
            "/verification/email",
            "/springfox-swagger-ui",
            "/swagger-ui-bundle.js"
    };
	
	public static String getTemplateFolderLocation(String baseLocation, Long companyId) {
		return baseLocation + "/" + companyId + "/templates/";
	}
	
	public static String getLogoFolderLocation(String baseLocation, Long companyId) {
		return baseLocation + "/" + companyId + "/logos/";
	}

	public static String getMSAPOTemplateFolderLocation(String baseLocation, Long companyId, Character defaultTemplate ) {
		String templateLocation = baseLocation + "/msapo/templates";
		if(defaultTemplate != null && defaultTemplate == 'Y'){
			return templateLocation;
		}
		return templateLocation + "/" + companyId ;
	}
	
	public static String getMSAPOFolderLocation(String baseLocation, Long companyId) {
		return baseLocation + "/msapo/" + companyId ;
	}

	public static String getInvoiceFolderLocation(String baseLocation, Long companyId) {
		return baseLocation + "/invoice/" + companyId ;
	}
	public static String getInvoiceTemplateFolderLocation(String baseLocation, Long companyId,
			Character defaultTemplate) {
		String templateLocation = baseLocation + "/invoice/templates";
		if(defaultTemplate != null && defaultTemplate == 'Y'){
			return templateLocation;
		}
		return templateLocation + "/" + companyId ;
	}
}
