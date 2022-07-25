package org.secondopinion.pharmacy.dto;

public class InvoicesSerchDTO {
	
	private Long invoiceId;
	private Long patientid;
	private String patientname;
	private Long doctorid;
	private String doctorname;
	private String specialization;
	private Long pharmacyaddressId;
	private String pharmacyname;
	private Long prescriptionFillRequestId;
	private Double discount;
	private Double total;
	private String invoicestatus;
	private Integer pageNum;
	private Integer maxResults;
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Long getPatientid() {
		return patientid;
	}
	public void setPatientid(Long patientid) {
		this.patientid = patientid;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
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
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public Long getPharmacyaddressId() {
		return pharmacyaddressId;
	}
	public void setPharmacyaddressId(Long pharmacyaddressId) {
		this.pharmacyaddressId = pharmacyaddressId;
	}
	public String getPharmacyname() {
		return pharmacyname;
	}
	public void setPharmacyname(String pharmacyname) {
		this.pharmacyname = pharmacyname;
	}
	public Long getPrescriptionFillRequestId() {
		return prescriptionFillRequestId;
	}
	public void setPrescriptionFillRequestId(Long prescriptionFillRequestId) {
		this.prescriptionFillRequestId = prescriptionFillRequestId;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
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
