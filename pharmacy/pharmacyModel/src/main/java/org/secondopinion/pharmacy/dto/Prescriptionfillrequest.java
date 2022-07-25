package org.secondopinion.pharmacy.dto; 


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.pharmacy.domain.BasePrescriptionfillrequest; 



@Entity 
@Table (name="prescriptionfillrequest")
public class Prescriptionfillrequest extends BasePrescriptionfillrequest{

	private List<Prescriptionprice> prescriptionprices;
	private Long invoiceId;
	@Transient
	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	private Invoice invoice;
	
	private Shippingaddress shippingAddress;
	
	private byte[] prescriptionImage;

	
	@Transient
	public byte[] getPrescriptionImage() {
		return prescriptionImage;
	}

	public void setPrescriptionImage(byte[] prescriptionImage) {
		this.prescriptionImage = prescriptionImage;
	}

	@Transient
	public List<Prescriptionprice> getPrescriptionprices() {
		return prescriptionprices;
	}
	public void setPrescriptionprices(List<Prescriptionprice> prescriptionprices) {
		this.prescriptionprices = prescriptionprices;
	}

	@Transient
	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	@Transient
	public Shippingaddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Shippingaddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public static Prescriptionfillrequest buildFillRequest(FillPrescriptionRequestDTO request) {
		Prescriptionfillrequest pfr = new Prescriptionfillrequest();
		pfr.setPatientId(request.getPatientId());
		pfr.setPatientName(request.getPatientName());
		pfr.setPharmacyaddressId(request.getPharmacyaddressId());
		pfr.setDoctorId(request.getDoctorId());
		pfr.setDoctorName(request.getDoctorName());
		pfr.setNewRequest('Y');
		pfr.setRequestSentTime(new Date());
		pfr.setFromModule(request.getFromModule());
		pfr.setPatientAppointmentId(request.getPatientAppointmentId());
		pfr.setDoctorAppointmentId(request.getDoctorAppointmentId());
		
		pfr.setPrescriptionUploaded('N');
		if (request.isPrescriptionUploaded()) {
			pfr.setPrescriptionUploaded('Y');
		} 
		
		pfr.setRefill('N');
		if(request.isRefill()) {
			pfr.setRefill('Y');
		}
		
		return pfr;
	}
}