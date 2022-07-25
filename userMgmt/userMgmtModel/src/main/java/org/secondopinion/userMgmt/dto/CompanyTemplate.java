package org.secondopinion.userMgmt.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.userMgmt.domain.BaseCompanyTemplate; 


@Entity 
@Table (name="companytemplate")
public class CompanyTemplate extends BaseCompanyTemplate{
	public enum TEMPLATE{
		REQUIREMENT_VENDOR_PUBLISH, REQUIREMENT_RESOURCE_PUBLISH, INTERVIEW, BENCH_LIST; 
	}
	
	public enum DOC_TEMPLATE{
		MSA("MSA From: "), PO("PO From: "), MSA_PO("MSA & PO From:"), INVOICE("Invoice from: "); 
		private final String emailSubject;
		
		private DOC_TEMPLATE(String _emailSubject) {
			this.emailSubject = _emailSubject;
		}
		
		public String getEmailSubject() {
			return emailSubject;
		}
	}
} 