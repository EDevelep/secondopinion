package org.secondopinion.diagnosticcenter.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.diagnosticcenter.domain.BaseInvoice; 



@Entity 
@Table (name="invoice")
public class Invoice extends BaseInvoice{
	private Long invoiceentityid;
	
	@Transient
	public Long getInvoiceentityid() {
		return invoiceentityid;
	}

	public void setInvoiceentityid(Long invoiceentityid) {
		this.invoiceentityid = invoiceentityid;
	}

	
}