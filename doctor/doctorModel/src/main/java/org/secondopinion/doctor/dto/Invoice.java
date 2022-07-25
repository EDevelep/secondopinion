package org.secondopinion.doctor.dto; 



import javax.persistence.Entity; 
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.doctor.domain.BaseInvoice; 



@Entity 
@Table (name="invoice")
public class Invoice extends BaseInvoice{
	private String amountPaidStatus;

	@Transient
	public String getAmountPaidStatus() {
		return amountPaidStatus;
	}

	public void setAmountPaidStatus(String amountPaidStatus) {
		this.amountPaidStatus = amountPaidStatus;
	}


}