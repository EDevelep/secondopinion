package org.secondopinion.userMgmt.util;

import org.apache.commons.lang3.StringUtils;

public class UserMgmtUtil {
	
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

	public static boolean isStringEmpty(String userName) {
		return StringUtils.isEmpty(userName );
	}
}
