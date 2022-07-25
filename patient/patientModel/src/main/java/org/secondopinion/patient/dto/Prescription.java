package org.secondopinion.patient.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.patient.domain.BasePrescription;

@SuppressWarnings({ "serial" })
@Entity
@Table(name = "prescription")
public class Prescription extends BasePrescription {

	private Medicalprescription medicalprescription;
	private Medicaltestprescription medicaltestprescription;
	private List<Medication> medications;
	private List<Medicaltest> medicaltests;
	private char followup;
	private Date followupDate;

	@Transient
	public char getFollowup() {
		return followup;
	}

	public void setFollowup(char followup) {
		this.followup = followup;
	}
	@Transient
	public Date getFollowupDate() {
		return followupDate;
	}

	public void setFollowupDate(Date followupDate) {
		this.followupDate = followupDate;
	}

	@Transient
	public List<Medicaltest> getMedicaltests() {
		return medicaltests;
	}

	public void setMedicaltests(List<Medicaltest> medicaltests) {
		this.medicaltests = medicaltests;
	}

	@Transient
	public List<Medication> getMedications() {
		return medications;
	}

	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}

	private Long medicalPrescriptionId;

	@Transient
	public Long getMedicalPrescriptionId() {
		return medicalPrescriptionId;
	}

	public void setMedicalPrescriptionId(Long medicalPrescriptionId) {
		this.medicalPrescriptionId = medicalPrescriptionId;
	}

	private Long pharmacyaddressId;
	private String pharmacyName;
	private String patientName;
	private String fromModule;
	private String medicalPrescritionFor;
	private byte[] image;
	private boolean refill;
	private boolean prescriptionUploaded;
	private byte[] medicalPrescriptionFile;
	private Address shippingAddress;
	private Long prescriptionFillRequestId;
	private Long entityInvoiceId;
	private String invoicestatus;

	@Transient
	public boolean isRefill() {
		return refill;
	}

	public void setRefill(boolean refill) {
		this.refill = refill;
	}

	@Transient
	public boolean isPrescriptionUploaded() {
		return prescriptionUploaded;
	}

	public void setPrescriptionUploaded(boolean prescriptionUploaded) {
		this.prescriptionUploaded = prescriptionUploaded;
	}

	@Transient
	public byte[] getMedicalPrescriptionFile() {
		return medicalPrescriptionFile;
	}

	public void setMedicalPrescriptionFile(byte[] medicalPrescriptionFile) {
		this.medicalPrescriptionFile = medicalPrescriptionFile;
	}

	@Transient
	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Transient
	public Long getPrescriptionFillRequestId() {
		return prescriptionFillRequestId;
	}

	public void setPrescriptionFillRequestId(Long prescriptionFillRequestId) {
		this.prescriptionFillRequestId = prescriptionFillRequestId;
	}

	@Transient
	public Long getEntityInvoiceId() {
		return entityInvoiceId;
	}

	public void setEntityInvoiceId(Long entityInvoiceId) {
		this.entityInvoiceId = entityInvoiceId;
	}

	@Transient
	public String getInvoicestatus() {
		return invoicestatus;
	}

	public void setInvoicestatus(String invoicestatus) {
		this.invoicestatus = invoicestatus;
	}

	@Transient
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	
	@Transient
	public Long getPharmacyaddressId() {
		return pharmacyaddressId;
	}

	public void setPharmacyaddressId(Long pharmacyaddressId) {
		this.pharmacyaddressId = pharmacyaddressId;
	}

	@Transient
	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	@Transient
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	@Transient
	public String getFromModule() {
		return fromModule;
	}

	public void setFromModule(String fromModule) {
		this.fromModule = fromModule;
	}

	@Transient
	public Medicalprescription getMedicalprescription() {
		return medicalprescription;
	}

	public void setMedicalprescription(Medicalprescription medicalprescription) {
		this.medicalprescription = medicalprescription;
	}

	@Transient
	public Medicaltestprescription getMedicaltestprescription() {
		return medicaltestprescription;
	}

	public void setMedicaltestprescription(Medicaltestprescription medicaltestprescription) {
		this.medicaltestprescription = medicaltestprescription;
	}

	private String appointmentFor;

	@Transient
	public String getAppointmentFor() {
		return appointmentFor;
	}

	public void setAppointmentFor(String appointmentFor) {
		this.appointmentFor = appointmentFor;
	}

	public void update(Appointment appointment) {
		this.setDoctorId(appointment.getReferenceEntityId());
		this.setDoctorName(appointment.getReferenceEntityName());
		this.setDoctorAppointmentId(appointment.getReferenceAppointmentId());
		this.setPatientAppointmentId(appointment.getAppointmentId());
		this.setAppointmentDate(appointment.getAppointmentDate());
		this.setPatientId(appointment.getUserId());

	}

	@Transient
	public String getMedicalPrescritionFor() {
		return medicalPrescritionFor;
	}

	public void setMedicalPrescritionFor(String medicalPrescritionFor) {
		this.medicalPrescritionFor = medicalPrescritionFor;
	}

	public void updateForSelf() {
		this.setDoctorId(-1L);
		this.setDoctorName("SELF");
		this.setDoctorAppointmentId(-1L);
		this.setPatientAppointmentId(-1L);
		this.setAppointmentDate(new Date());
		
		
	}
}