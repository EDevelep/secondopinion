package org.secondopinion.patient.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;



public class InvoiceDTO {

	private Long invoiceId;
	private Long patientid;
	private Long doctorid;
	private String doctorname;
	private String specialization;
	private String patientname;
	private Long doctorappointmentid;
	private Long patientappointmentid;
	private Long diagnosticCenterAddressId;
	private String diagnosticCenterName;
	private Long diagnosticCenterAppointmentId;
	private Long pharmacyaddressId;
	private String pharmacyname;
	private Long prescriptionFillRequestId;
	private String cardnumber;
	private String transactiontype;
	private String invoicestatus;
	private Double amount;
	private Double paidamount;
	private Date payByDate;
	private Character paid;
	private Date paidOn;
	private String paymentReferenceId;
	private Long entityInvoiceId;
	private Long paymentDetailsId;
	private Long caretekerId;
	private String caretakername;
	private Long caretakerappointmentid;
	public Long getCaretakerappointmentid() {
		return caretakerappointmentid;
	}
	public void setCaretakerappointmentid(Long caretakerappointmentid) {
		this.caretakerappointmentid = caretakerappointmentid;
	}
	public Long getCaretekerId() {
		return caretekerId;
	}
	public void setCaretekerId(Long caretekerId) {
		this.caretekerId = caretekerId;
	}
	public Long getPaymentDetailsId() {
		return paymentDetailsId;
	}
	public void setPaymentDetailsId(Long paymentDetailsId) {
		this.paymentDetailsId = paymentDetailsId;
	}
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
	
	public String getCaretakername() {
		return caretakername;
	}
	public void setCaretakername(String caretakername) {
		this.caretakername = caretakername;
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
	public Double getPaidamount() {
		return paidamount;
	}
	public void setPaidamount(Double paidamount) {
		this.paidamount = paidamount;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getPayByDate() {
		return payByDate;
	}
	public void setPayByDate(Date payByDate) {
		this.payByDate = payByDate;
	}
	public Character getPaid() {
		return paid;
	}
	public void setPaid(Character paid) {
		this.paid = paid;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getPaidOn() {
		return paidOn;
	}
	public void setPaidOn(Date paidOn) {
		this.paidOn = paidOn;
	}
	public String getPaymentReferenceId() {
		return paymentReferenceId;
	}
	public void setPaymentReferenceId(String paymentReferenceId) {
		this.paymentReferenceId = paymentReferenceId;
	}
	
	public Long getEntityInvoiceId() {
		return entityInvoiceId;
	}

	public void setEntityInvoiceId(Long entityInvoiceId) {
		this.entityInvoiceId = entityInvoiceId;
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
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public static InvoiceDTO build(Invoice invoice) {
		InvoiceDTO invoiceDTO = new InvoiceDTO();
		invoiceDTO.setPatientid(invoice.getPatientid());
		invoiceDTO.setPatientname(invoice.getPatientname());
		invoiceDTO.setPaymentReferenceId(invoice.getPaymentReferenceId());
		invoiceDTO.setInvoicestatus(invoice.getInvoicestatus());
		invoiceDTO.setTransactiontype(invoice.getTransactiontype());
		//invoiceDTO.setCardnumber(invoice.getCardnumber());
		invoiceDTO.setPaid('Y');
		invoiceDTO.setAmount(invoice.getAmount());
		invoiceDTO.setPaidOn(invoice.getPaidOn());
		invoiceDTO.setPayByDate(invoice.getPayByDate());
		return invoiceDTO;
	}
	public static InvoiceDTO buildInvoiceForDoctor(Invoice invoice) {
		InvoiceDTO invoiceDTO = build(invoice);
		
		invoiceDTO.setDoctorid(invoice.getInvoiceentityid());
		invoiceDTO.setDoctorname(invoice.getInvoiceentityname());
		invoiceDTO.setDoctorappointmentid(invoice.getInvoiceentityreferenceid());
		invoiceDTO.setPatientappointmentid(invoice.getInvoicereferenceid());
		
		return invoiceDTO;
	}
	
	
	public static InvoiceDTO buildInvoiceForCareTaker(Invoice invoice) {
		InvoiceDTO invoiceDTO = build(invoice);
		
		invoiceDTO.setCaretekerId(invoice.getInvoiceentityid());
		invoiceDTO.setCaretakername(invoice.getInvoiceentityname());
		invoiceDTO.setCaretakerappointmentid(invoice.getInvoiceentityreferenceid());
		invoiceDTO.setPatientappointmentid(invoice.getInvoicereferenceid());
		
		return invoiceDTO;
	}
	
	public static InvoiceDTO buildInvoiceForPharmacy(Invoice invoice, Medicalprescription medicalprescription) {
		InvoiceDTO invoiceDTO = build(invoice);
		//invoiceDTO.setDoctorid(medicalprescription.getDoctorId());
		//invoiceDTO.setDoctorname(medicalprescription.getDoctorName());
	//	invoiceDTO.setSpecialization(medicalprescription.getDoctorSpecialization());
		invoiceDTO.setInvoiceId(invoice.getEntityInvoiceId()/*pharmacy.invoice.invoiceid*/);
		invoiceDTO.setPharmacyaddressId(invoice.getInvoiceentityid()/*pharmacy.pharamcy id*/);
		invoiceDTO.setPharmacyname(invoice.getInvoiceentityname()/*pharmacy.pharmacyname*/);
		invoiceDTO.setPrescriptionFillRequestId(invoice.getInvoiceentityreferenceid()/*pharmacy.pfr.id*/);
		invoiceDTO.setPaidamount(invoice.getAmount());
		invoiceDTO.setAmount(invoice.getAmount());
		return invoiceDTO;
	}
	public static InvoiceDTO buildInvoiceForDiagnosticCenter(Invoice invoice, Long paymentDetailsId) {
		InvoiceDTO invoiceDTO = build(invoice);
		invoiceDTO.setDiagnosticCenterAddressId(invoice.getInvoiceentityid());
		invoiceDTO.setDiagnosticCenterName(invoice.getInvoiceentityname());
		invoiceDTO.setDiagnosticCenterAppointmentId(invoice.getInvoiceentityreferenceid());
		invoiceDTO.setPatientappointmentid(invoice.getInvoicereferenceid());
		invoiceDTO.setPaymentDetailsId(paymentDetailsId);
		return invoiceDTO;
	}
	public static InvoiceDTO buildInvoiceForPharmacy(Invoice invoice, Prescription prescription) {
		InvoiceDTO invoiceDTO = build(invoice);
		invoiceDTO.setDoctorid(prescription.getDoctorId());
		invoiceDTO.setDoctorname(prescription.getDoctorName());
	//	invoiceDTO.setSpecialization(prescription.getDoctorSpecialization());
		invoiceDTO.setInvoiceId(invoice.getEntityInvoiceId()/*pharmacy.invoice.invoiceid*/);
		invoiceDTO.setPharmacyaddressId(invoice.getInvoiceentityid()/*pharmacy.pharamcy id*/);
		invoiceDTO.setPharmacyname(invoice.getInvoiceentityname()/*pharmacy.pharmacyname*/);
		invoiceDTO.setPrescriptionFillRequestId(invoice.getInvoiceentityreferenceid()/*pharmacy.pfr.id*/);
		invoiceDTO.setPaidamount(invoice.getAmount());
		invoiceDTO.setAmount(invoice.getAmount());
		return invoiceDTO;
	}
	
}
