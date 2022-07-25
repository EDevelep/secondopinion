package org.secondopinion.patient.dto;

public class StripProperties {

	private static String apikey="sk_test_51GwpTBDwz1IdH1GYJX0ZQLh608pOvwAXRJvMpZtjhiCFa4SGoZnepzYWVDQSiJFsPuVEaCh1ftczBkeJ4ouJUujM00EAkerUX9";

	private static String CUSTOMER_SOURCE = "source";
         private static String MONTH_PLAN="month";
	
	public static String getMONTH_PLAN() {
			return MONTH_PLAN;
		}

		public static void setMONTH_PLAN(String mONTH_PLAN) {
			MONTH_PLAN = mONTH_PLAN;
		}


	private static String CUSTOMER_DESCRIPTION = "description";


	public static String getCUSTOMER_SOURCE() {
		return CUSTOMER_SOURCE;
	}

	public static void setCUSTOMER_SOURCE(String cUSTOMER_SOURCE) {
		CUSTOMER_SOURCE = cUSTOMER_SOURCE;
	}

	public static String getCUSTOMER_DESCRIPTION() {
		return CUSTOMER_DESCRIPTION;
	}

	public static void setCUSTOMER_DESCRIPTION(String cUSTOMER_DESCRIPTION) {
		CUSTOMER_DESCRIPTION = cUSTOMER_DESCRIPTION;
	}

	

	public static String getCUSTOMER_EMAIL() {
		return CUSTOMER_EMAIL;
	}

	public static void setCUSTOMER_EMAIL(String cUSTOMER_EMAIL) {
		CUSTOMER_EMAIL = cUSTOMER_EMAIL;
	}

	
	public static String getSUBSCRIPTION_CUSTOMER() {
		return SUBSCRIPTION_CUSTOMER;
	}

	public static void setSUBSCRIPTION_CUSTOMER(String sUBSCRIPTION_CUSTOMER) {
		SUBSCRIPTION_CUSTOMER = sUBSCRIPTION_CUSTOMER;
	}

	

	public static String getSUBSCRIPTION_ITEMS() {
		return SUBSCRIPTION_ITEMS;
	}

	public static void setSUBSCRIPTION_ITEMS(String sUBSCRIPTION_ITEMS) {
		SUBSCRIPTION_ITEMS = sUBSCRIPTION_ITEMS;
	}

	public static String getSUBSCRIPTION_BILLING() {
		return SUBSCRIPTION_BILLING;
	}

	public static void setSUBSCRIPTION_BILLING(String sUBSCRIPTION_BILLING) {
		SUBSCRIPTION_BILLING = sUBSCRIPTION_BILLING;
	}

	
	private static String CUSTOMER_EMAIL = "email";

	

	private static String SUBSCRIPTION_CUSTOMER = "customer";

	private static String SUBSCRIPTION_ITEMS = "items";

	private static String SUBSCRIPTION_BILLING = "billing";


	public static String getApikey() {
		return apikey;
	}

	public static void setApikey(String apikey) {
		StripProperties.apikey = apikey;
	}

	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	public void setDelinquent(Boolean delinquent) {
		// TODO Auto-generated method stub
		
	}

	public void setEmail(String email) {
		// TODO Auto-generated method stub
		
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
