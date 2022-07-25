package org.secondopinion.patient.dto;

import java.math.BigDecimal;

public class RazerPayPaymentGatewayDTO {
	private String cutomerEmail;
	private String customerName;
	private String description;
	private Double amountPaid;
	private String currencyType;
	private String cardnumber;
	private Long expmonth;
	private Long expyear;
	private String cvv;
	private String paymentReferenceId;
	private BigDecimal refundAmount; 
	public RazerPayPaymentGatewayDTO(String cutomerEmail, String customerName, String description, Double amountPaid,
			String currencyType, String cardnumber, Long expmonth, Long expyear, String cvv, String paymentReferenceId,
			BigDecimal refundAmount, String refundReason, String type, String secretKey) {
		super();
		this.cutomerEmail = cutomerEmail;
		this.customerName = customerName;
		this.description = description;
		this.amountPaid = amountPaid;
		this.currencyType = currencyType;
		this.cardnumber = cardnumber;
		this.expmonth = expmonth;
		this.expyear = expyear;
		this.cvv = cvv;
		this.paymentReferenceId = paymentReferenceId;
		this.refundAmount = refundAmount;
		this.refundReason = refundReason;
		this.type = type;
		this.secretKey = secretKey;
	}
	public String getCutomerEmail() {
		return cutomerEmail;
	}
	public void setCutomerEmail(String cutomerEmail) {
		this.cutomerEmail = cutomerEmail;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public Long getExpmonth() {
		return expmonth;
	}
	public void setExpmonth(Long expmonth) {
		this.expmonth = expmonth;
	}
	public Long getExpyear() {
		return expyear;
	}
	public void setExpyear(Long expyear) {
		this.expyear = expyear;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getPaymentReferenceId() {
		return paymentReferenceId;
	}
	public void setPaymentReferenceId(String paymentReferenceId) {
		this.paymentReferenceId = paymentReferenceId;
	}
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	private String refundReason;
	private String type;
	private String secretKey;
}
