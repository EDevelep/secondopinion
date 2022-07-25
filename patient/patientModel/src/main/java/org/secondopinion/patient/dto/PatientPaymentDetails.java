package org.secondopinion.patient.dto; 



import javax.persistence.Entity; 
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.patient.domain.BasePatientPaymentDetails;

@Entity 
@Table (name="patientpaymentdetails")
public class PatientPaymentDetails extends BasePatientPaymentDetails{
	
	//private Carddetails carddetails;
	private Double fee;
	private String feeType;
	private Long patientInvoiceId;
	private String paymentId;
	@Transient
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	@Transient
	public Long getPatientInvoiceId() {
		return patientInvoiceId;
	}
	public void setPatientInvoiceId(Long patientInvoiceId) {
		this.patientInvoiceId = patientInvoiceId;
	}
	@Transient
	public Double getFee(){
		 return this.fee;
	}
	public void setFee(Double fee){
		  this.fee = fee;
	}
	
	public void setFeeType( String  feeType){
		this.feeType = feeType;
	}
	@Transient
	public String getFeeType(){
		 return this.feeType;
	}

	
	public enum CurrencyTypeEnum {
		INR, USD, EUR;
	}
	
	public enum TransactionStatusEnum {
		SUCCESS, FAILED, REFUNDED,captured, authorized
	}
	
	public enum TransactionTypeEnum {
		NET_BANKING, DEBITCARD, CREDIT_CARD, BHIM, PHONE_PE, G_PAY, AMAZON_PAY, WHATSAPP_PAY, PAYTM_PAY, WALLET
	}
}