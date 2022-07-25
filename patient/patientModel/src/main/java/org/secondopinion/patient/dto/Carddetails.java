package org.secondopinion.patient.dto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.patient.domain.BaseCarddetails;

@Entity
@Table(name = "carddetails")
public class Carddetails extends BaseCarddetails {
	
	private String cvv;
	private boolean saveCardDetails;
	
	public enum CardTypeEnum {
		DEBIT_CARD, CREDIT_CARD
	}
	public enum CardBankNameEnum {
		HDFC, AXIS, YES, AMERICAN_EXPRESS, SBI, 
	}

	@Transient
	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	@Transient
	public boolean isSaveCardDetails() {
		return saveCardDetails;
	}

	public void setSaveCardDetails(boolean saveCardDetails) {
		this.saveCardDetails = saveCardDetails;
	}
	
	
}