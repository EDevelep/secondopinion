package org.secondopinion.diagnosticcenter.dto;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.diagnosticcenter.domain.BasePatientpaymentdetails;

@SuppressWarnings({ "serial" })
@Entity
@Table(name = "patientpaymentdetails")
public class Patientpaymentdetails extends BasePatientpaymentdetails {

	private Double fee;
	private String feeType;
	private Long patientInvoiceId;

	
	private Carddetails carddetails;

	@Transient
	public Carddetails getCarddetails() {
		return carddetails;
	}

	public void setCarddetails(Carddetails carddetails) {
		this.carddetails = carddetails;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public Long getPatientInvoiceId() {
		return patientInvoiceId;
	}

	public void setPatientInvoiceId(Long patientInvoiceId) {
		this.patientInvoiceId = patientInvoiceId;
	}
}