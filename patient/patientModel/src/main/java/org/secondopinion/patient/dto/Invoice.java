package org.secondopinion.patient.dto;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.enums.InvoiceStatusEnum;
import org.secondopinion.enums.InvoiceTypeEnum;
import org.secondopinion.patient.domain.BaseInvoice;

@Entity
@Table(name = "invoice")
public class Invoice extends BaseInvoice {
	private Long patientId;
	private Long patientAppointmentId;
	private Long medicalPrescriptionId;
	private String medicine;
	private String type;
	private String power;
	private String dosage;
	private Long quantity;
	private Integer morning;
	private Integer afternoon;
	private Integer evening;
	private String instructions;
	private PatientPaymentDetails patientPaymentDetails;
	
	private String amountPaidStatus;

	@Transient
	public String getAmountPaidStatus() {
		return amountPaidStatus;
	}

	public void setAmountPaidStatus(String amountPaidStatus) {
		this.amountPaidStatus = amountPaidStatus;
	}

	@Transient
	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	@Transient
	public Long getPatientAppointmentId() {
		return patientAppointmentId;
	}

	@Transient
	public void setPatientAppointmentId(Long patientAppointmentId) {
		this.patientAppointmentId = patientAppointmentId;
	}

	@Transient
	public Long getMedicalPrescriptionId() {
		return medicalPrescriptionId;
	}

	public void setMedicalPrescriptionId(Long medicalPrescriptionId) {
		this.medicalPrescriptionId = medicalPrescriptionId;
	}

	@Transient
	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	@Transient
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Transient
	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	@Transient
	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	@Transient
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Transient
	public Integer getMorning() {
		return morning;
	}

	public void setMorning(Integer morning) {
		this.morning = morning;
	}

	@Transient
	public Integer getAfternoon() {
		return afternoon;
	}

	public void setAfternoon(Integer afternoon) {
		this.afternoon = afternoon;
	}

	@Transient
	public Integer getEvening() {
		return evening;
	}

	public void setEvening(Integer evening) {
		this.evening = evening;
	}

	@Transient
	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public static Invoice buildInvoiceForAppointment(Appointment appointment,FetchPayment fetchPayment) {
	
		Invoice invoice = new Invoice();
		invoice.setPatientid(appointment.getUserId());
		invoice.setPatientname(appointment.getPatientName());
		invoice.setInvoiceentityname(appointment.getReferenceEntityName());
		invoice.setInvoiceentityid(appointment.getReferenceEntityId());
		invoice.setInvoicetype(appointment.getAppointmentFor());
		invoice.setInvoicereferenceid(appointment.getAppointmentId());
		invoice.setInvoiceentityreferenceid(appointment.getReferenceAppointmentId());
		invoice.setPaymentReferenceId(fetchPayment.getId());
		invoice.setInvoicestatus(InvoiceStatusEnum.PAYMENT_DONE.name());
		invoice.setTransactiontype(fetchPayment.getMethod());
		invoice.setPaid('Y');
		invoice.setActive('Y');
		invoice.setAmount(appointment.getAmountPaid());
		invoice.setPaidOn(fetchPayment.getCreated_at());
		invoice.setPayByDate(appointment.getAppointmentDate());
		invoice.setAmountPaidStatus("PAID");
		return invoice;
	}

	public static Invoice buildNewInvoiceForPrescription(Prescription prescription,
			PatientPaymentDetails patientPaymentDetails) {
		Invoice invoice = new Invoice();
		invoice.setPatientid(prescription.getPatientId());
		invoice.setPatientname(prescription.getPatientName());
		invoice.setInvoiceentityname(prescription.getPharmacyName());
		invoice.setInvoiceentityid(prescription.getPharmacyaddressId());
		invoice.setInvoicetype(InvoiceTypeEnum.PHARMACY.name());
		invoice.setInvoicereferenceid(prescription.getPrescriptionId());
		invoice.setInvoiceentityreferenceid(prescription.getPrescriptionFillRequestId());
		invoice.setPaymentReferenceId(patientPaymentDetails.getTransactionId());
		invoice.setInvoicestatus(prescription.getInvoicestatus());
		invoice.setTransactiontype(patientPaymentDetails.getTransactionType());
	//	invoice.setCardnumber((Objects.isNull(patientPaymentDetails.getCarddetails())) ? null
			//	: patientPaymentDetails.getCarddetails().getCardnumber());
		invoice.setPaid('N');
		invoice.setAmount(0.0d);
		invoice.setPaidOn(new Date());
		invoice.setPayByDate(new Date());
		invoice.setEntityInvoiceId(prescription.getEntityInvoiceId());
		return invoice;
	}

	@Transient
	public PatientPaymentDetails getPatientPaymentDetails() {
		return patientPaymentDetails;
	}

	public void setPatientPaymentDetails(PatientPaymentDetails patientPaymentDetails) {
		this.patientPaymentDetails = patientPaymentDetails;
	}

	public enum ViewInvoiceEnum {
		REQUEST_PAYMENT, PAYMENT_DONE, DELIVERED
	}
}