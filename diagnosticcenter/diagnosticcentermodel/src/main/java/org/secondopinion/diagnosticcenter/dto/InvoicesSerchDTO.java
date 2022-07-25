package org.secondopinion.diagnosticcenter.dto;

import java.util.Date;

public class InvoicesSerchDTO {

	private Long invoiceId;
	private Long diagnosticCenterAddressId;
	private String diagnosticCenterName;
	private Long diagnosticCenterAppointmentId;
	private Long paymentDetailsId;
	private Double amount;
	private Date invoiceDate;
	private Double SGST;
	private Double CGST;
	private Character active;
	private Double totalInvoiceAmount;
	private Date paidOn;
	private String invoicestatus;
	private Integer pageNum;
	private Integer maxResults;
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Long getDiagnosticCenterAddressId() {
		return diagnosticCenterAddressId;
	}
	public void setDiagnosticCenterAddressId(Long diagnosticCenterAddressId) {
		this.diagnosticCenterAddressId = diagnosticCenterAddressId;
	}
	public String getDiagnosticCenterName() {
		return diagnosticCenterName;
	}
	public void setDiagnosticCenterName(String diagnosticCenterName) {
		this.diagnosticCenterName = diagnosticCenterName;
	}
	public Long getDiagnosticCenterAppointmentId() {
		return diagnosticCenterAppointmentId;
	}
	public void setDiagnosticCenterAppointmentId(Long diagnosticCenterAppointmentId) {
		this.diagnosticCenterAppointmentId = diagnosticCenterAppointmentId;
	}
	public Long getPaymentDetailsId() {
		return paymentDetailsId;
	}
	public void setPaymentDetailsId(Long paymentDetailsId) {
		this.paymentDetailsId = paymentDetailsId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Double getSGST() {
		return SGST;
	}
	public void setSGST(Double sGST) {
		SGST = sGST;
	}
	public Double getCGST() {
		return CGST;
	}
	public void setCGST(Double cGST) {
		CGST = cGST;
	}
	public Character getActive() {
		return active;
	}
	public void setActive(Character active) {
		this.active = active;
	}
	public Double getTotalInvoiceAmount() {
		return totalInvoiceAmount;
	}
	public void setTotalInvoiceAmount(Double totalInvoiceAmount) {
		this.totalInvoiceAmount = totalInvoiceAmount;
	}
	public Date getPaidOn() {
		return paidOn;
	}
	public void setPaidOn(Date paidOn) {
		this.paidOn = paidOn;
	}
	public String getInvoicestatus() {
		return invoicestatus;
	}
	public void setInvoicestatus(String invoicestatus) {
		this.invoicestatus = invoicestatus;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
}
