package org.secondopinion.doctor.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class InvoicesSerchDTO {
	private Long patientid;
	private Long doctorid;
	private String doctorname;
	private String patientname;
	private Long doctorappointmentid;
	private Long patientappointmentid;
	private String cardnumber;
	private String transactiontype;
	private String invoicestatus;
	private Double amount;
	private Date payByDate;
	private String paymentReferenceId;
	private Integer pageNum;
	private Integer maxResults;
	public Long getPatientid() {
		return patientid;
	}
	public void setPatientid(Long patientid) {
		this.patientid = patientid;
	}
	public Long getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(Long doctorid) {
		this.doctorid = doctorid;
	}
	public String getDoctorname() {
		return doctorname;
	}
	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public Long getDoctorappointmentid() {
		return doctorappointmentid;
	}
	public void setDoctorappointmentid(Long doctorappointmentid) {
		this.doctorappointmentid = doctorappointmentid;
	}
	public Long getPatientappointmentid() {
		return patientappointmentid;
	}
	public void setPatientappointmentid(Long patientappointmentid) {
		this.patientappointmentid = patientappointmentid;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getTransactiontype() {
		return transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}
	public String getInvoicestatus() {
		return invoicestatus;
	}
	public void setInvoicestatus(String invoicestatus) {
		this.invoicestatus = invoicestatus;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getPayByDate() {
		return payByDate;
	}
	public void setPayByDate(Date payByDate) {
		this.payByDate = payByDate;
	}
	public String getPaymentReferenceId() {
		return paymentReferenceId;
	}
	public void setPaymentReferenceId(String paymentReferenceId) {
		this.paymentReferenceId = paymentReferenceId;
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
