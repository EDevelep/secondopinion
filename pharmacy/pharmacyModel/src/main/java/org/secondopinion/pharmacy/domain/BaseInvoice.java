package org.secondopinion.pharmacy.domain; 

import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.pharmacy.dto.Invoice;
import org.secondopinion.utils.ObjectUtil; 

import javax.persistence.Column; 
import javax.persistence.MappedSuperclass; 
import javax.validation.constraints.NotNull; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length; 
@MappedSuperclass 
public abstract class BaseInvoice extends BaseDomainObject<Long> {

	public static final String FIELD_invoiceId = "invoiceId";
	public static final String FIELD_patientid = "patientid";
	public static final String FIELD_patientname = "patientname";
	public static final String FIELD_doctorid = "doctorid";
	public static final String FIELD_doctorname = "doctorname";
	public static final String FIELD_specialization = "specialization";
	public static final String FIELD_pharmacyaddressId = "pharmacyaddressId";
	public static final String FIELD_pharmacyname = "pharmacyname";
	public static final String FIELD_prescriptionFillRequestId = "prescriptionFillRequestId";
	public static final String FIELD_discount = "discount";
	public static final String FIELD_total = "total";
	public static final String FIELD_invoicestatus = "invoicestatus";
	public static final String FIELD_paidamount = "paidamount";
	public static final String FIELD_cardnumber = "cardnumber";
	public static final String FIELD_transactiontype = "transactiontype";
	public static final String FIELD_paid = "paid";
	public static final String FIELD_paidOn = "paidOn";
	public static final String FIELD_paymentReferenceId = "paymentReferenceId";
	public static final String FIELD_expectedDate = "expectedDate";
	public static final String FIELD_shippedBy = "shippedBy";
	public static final String FIELD_mobileNumber = "mobileNumber";
	public static final String FIELD_active = "active";
	public static final String FIELD_trackingId = "trackingId";
	public static final String FIELD_name = "name";
	public static final String FIELD_medicalPrescriptionId= "medicalPrescriptionId";
	

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
	private Double paidamount;
	private String cardnumber;
	private String transactiontype;
	private Character paid;
	private Date paidOn;
	private String paymentReferenceId;
	private Date expectedDate;
	private String shippedBy;
	private String mobileNumber;
	private Character active;
	private String trackingId;
	private String name;
	private Long medicalPrescriptionId;
	
	public void setInvoiceId( Long  _invoiceId){
		this.invoiceId = _invoiceId;
	}
	@Id
 	@Column(name = "invoiceId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getInvoiceId(){
		 return this.invoiceId;
	}
	public void setPatientid( Long  _patientid){
		this.patientid = _patientid;
	}
	@NotNull 
	@Column (name="patientid")
	public Long getPatientid(){
		 return this.patientid;
	}
	public void setPatientname( String  _patientname){
		this.patientname = _patientname;
	}
	@Length(max=45)
	@Column (name="patientname")
	public String getPatientname(){
		 return this.patientname;
	}
	public void setDoctorid( Long  _doctorid){
		this.doctorid = _doctorid;
	}
	@Column (name="doctorid")
	public Long getDoctorid(){
		 return this.doctorid;
	}
	public void setDoctorname( String  _doctorname){
		this.doctorname = _doctorname;
	}
	@Length(max=45)
	@Column (name="doctorname")
	public String getDoctorname(){
		 return this.doctorname;
	}
	public void setSpecialization( String  _specialization){
		this.specialization = _specialization;
	}
	@Length(max=150)
	@Column (name="specialization")
	public String getSpecialization(){
		 return this.specialization;
	}
	public void setPharmacyaddressId( Long  _pharmacyaddressId){
		this.pharmacyaddressId = _pharmacyaddressId;
	}
	@Column (name="pharmacyaddressId")
	public Long getPharmacyaddressId(){
		 return this.pharmacyaddressId;
	}
	public void setPharmacyname( String  _pharmacyname){
		this.pharmacyname = _pharmacyname;
	}
	@Length(max=45)
	@Column (name="pharmacyname")
	public String getPharmacyname(){
		 return this.pharmacyname;
	}
	public void setPrescriptionFillRequestId( Long  _prescriptionFillRequestId){
		this.prescriptionFillRequestId = _prescriptionFillRequestId;
	}
	@Column (name="prescriptionFillRequestId")
	public Long getPrescriptionFillRequestId(){
		 return this.prescriptionFillRequestId;
	}
	public void setDiscount( Double  _discount){
		this.discount = _discount;
	}
	@Column (name="discount")
	public Double getDiscount(){
		 return this.discount;
	}
	public void setTotal( Double  _total){
		this.total = _total;
	}
	@Column (name="total")
	public Double getTotal(){
		 return this.total;
	}
	public void setInvoicestatus( String  _invoicestatus){
		this.invoicestatus = _invoicestatus;
	}
	@Length(max=45)
	@Column (name="invoicestatus")
	public String getInvoicestatus(){
		 return this.invoicestatus;
	}
	public void setPaidamount( Double  _paidamount){
		this.paidamount = _paidamount;
	}
	@Column (name="paidamount")
	public Double getPaidamount(){
		 return this.paidamount;
	}
	public void setCardnumber( String  _cardnumber){
		this.cardnumber = _cardnumber;
	}
	@Length(max=255)
	@Column (name="cardnumber")
	public String getCardnumber(){
		 return this.cardnumber;
	}
	public void setTransactiontype( String  _transactiontype){
		this.transactiontype = _transactiontype;
	}
	@Length(max=45)
	@Column (name="transactiontype")
	public String getTransactiontype(){
		 return this.transactiontype;
	}
	public void setPaid( Character  _paid){
		this.paid = _paid;
	}
	@Column (name="paid")
	public Character getPaid(){
		 return this.paid;
	}
	public void setPaidOn( Date  _paidOn){
		this.paidOn = _paidOn;
	}
	@Column (name="paidOn")
	public Date getPaidOn(){
		 return this.paidOn;
	}
	public void setPaymentReferenceId( String  _paymentReferenceId){
		this.paymentReferenceId = _paymentReferenceId;
	}
	@Length(max=150)
	@Column (name="paymentReferenceId")
	public String getPaymentReferenceId(){
		 return this.paymentReferenceId;
	}
	public void setExpectedDate( Date  _expectedDate){
		this.expectedDate = _expectedDate;
	}
	@Column (name="expectedDate")
	public Date getExpectedDate(){
		 return this.expectedDate;
	}
	public void setShippedBy( String  _shippedBy){
		this.shippedBy = _shippedBy;
	}
	@Length(max=445)
	@Column (name="shippedBy")
	public String getShippedBy(){
		 return this.shippedBy;
	}
	public void setMobileNumber( String  _mobileNumber){
		this.mobileNumber = _mobileNumber;
	}
	@Length(max=45)
	@Column (name="mobileNumber")
	public String getMobileNumber(){
		 return this.mobileNumber;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setTrackingId(String _trackingId){
		this.trackingId = _trackingId;
	}
	@Column (name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column (name="trackingId")
	public String getTrackingId(){
		 return this.trackingId;
	}
	@Column (name="medicalPrescriptionId")
	public Long getMedicalPrescriptionId() {
		return medicalPrescriptionId;
	}
	public void setMedicalPrescriptionId(Long medicalPrescriptionId) {
		this.medicalPrescriptionId = medicalPrescriptionId;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseInvoice other = (BaseInvoice)o;
		if(
			ObjectUtil.isEqual(getPatientid(), other.getPatientid()) && 
			ObjectUtil.isEqual(getPatientname(), other.getPatientname()) && 
			ObjectUtil.isEqual(getDoctorid(), other.getDoctorid()) && 
			ObjectUtil.isEqual(getDoctorname(), other.getDoctorname()) && 
			ObjectUtil.isEqual(getSpecialization(), other.getSpecialization()) && 
			ObjectUtil.isEqual(getPharmacyaddressId(), other.getPharmacyaddressId()) && 
			ObjectUtil.isEqual(getPharmacyname(), other.getPharmacyname()) && 
			ObjectUtil.isEqual(getPrescriptionFillRequestId(), other.getPrescriptionFillRequestId()) && 
			ObjectUtil.isEqual(getDiscount(), other.getDiscount()) && 
			ObjectUtil.isEqual(getTotal(), other.getTotal()) && 
			ObjectUtil.isEqual(getInvoicestatus(), other.getInvoicestatus()) && 
			ObjectUtil.isEqual(getPaidamount(), other.getPaidamount()) && 
			ObjectUtil.isEqual(getCardnumber(), other.getCardnumber()) && 
			ObjectUtil.isEqual(getTransactiontype(), other.getTransactiontype()) && 
			ObjectUtil.isEqual(getPaid(), other.getPaid()) && 
			ObjectUtil.isEqual(getPaidOn(), other.getPaidOn()) && 
			ObjectUtil.isEqual(getPaymentReferenceId(), other.getPaymentReferenceId()) && 
			ObjectUtil.isEqual(getExpectedDate(), other.getExpectedDate()) && 
			ObjectUtil.isEqual(getShippedBy(), other.getShippedBy()) && 
			ObjectUtil.isEqual(getMobileNumber(), other.getMobileNumber()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getTrackingId(), other.getTrackingId()) && 
			ObjectUtil.isEqual(getName(), other.getName()) && 
			ObjectUtil.isEqual(getMedicalPrescriptionId(), other.getMedicalPrescriptionId())&&
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (invoiceId!= null ? invoiceId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.invoiceId == null){
			 list.add(new ValidationMessage("Field " + FIELD_invoiceId+  " cannot be null"));
		}

		}
		if(this.patientid == null){
			 list.add(new ValidationMessage("Field " + FIELD_patientid+  " cannot be null"));
		}

		if((this.patientname != null) && (this.patientname.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_patientname+  " cannot be longer than: " + 45));
		}

		if((this.doctorname != null) && (this.doctorname.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_doctorname+  " cannot be longer than: " + 45));
		}

		if((this.specialization != null) && (this.specialization.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_specialization+  " cannot be longer than: " + 150));
		}

		if((this.pharmacyname != null) && (this.pharmacyname.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_pharmacyname+  " cannot be longer than: " + 45));
		}

		if((this.invoicestatus != null) && (this.invoicestatus.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_invoicestatus+  " cannot be longer than: " + 45));
		}

		if((this.cardnumber != null) && (this.cardnumber.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_cardnumber+  " cannot be longer than: " + 255));
		}

		if((this.transactiontype != null) && (this.transactiontype.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_transactiontype+  " cannot be longer than: " + 45));
		}

		if((this.paymentReferenceId != null) && (this.paymentReferenceId.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_paymentReferenceId+  " cannot be longer than: " + 150));
		}

		if((this.shippedBy != null) && (this.shippedBy.length()>445)){
			 list.add(new ValidationMessage("Field " + FIELD_shippedBy+  " cannot be longer than: " + 445));
		}

		if((this.mobileNumber != null) && (this.mobileNumber.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_mobileNumber+  " cannot be longer than: " + 45));
		}

	if(this.isFromDB()){
		if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 45));
		}
	}
		if(list.size()>0)setHasValidationErrors(true);

		setValidationMessages(list);
		
	}

	@Override
	public final void setAuditFields() {
		if (!isFromDB()){
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("invoiceId = " + invoiceId + "\n");
;
		str.append("patientid = " + patientid + "\n");
		str.append("patientname = " + patientname + "\n");
		str.append("doctorid = " + doctorid + "\n");
		str.append("doctorname = " + doctorname + "\n");
		str.append("specialization = " + specialization + "\n");
		str.append("pharmacyaddressId = " + pharmacyaddressId + "\n");
		str.append("pharmacyname = " + pharmacyname + "\n");
		str.append("prescriptionFillRequestId = " + prescriptionFillRequestId + "\n");
		str.append("discount = " + discount + "\n");
		str.append("total = " + total + "\n");
		str.append("invoicestatus = " + invoicestatus + "\n");
		str.append("paidamount = " + paidamount + "\n");
		str.append("cardnumber = " + cardnumber + "\n");
		str.append("transactiontype = " + transactiontype + "\n");
		str.append("paid = " + paid + "\n");
		str.append("paidOn = " + paidOn + "\n");
		str.append("paymentReferenceId = " + paymentReferenceId + "\n");
		str.append("expectedDate = " + expectedDate + "\n");
		str.append("shippedBy = " + shippedBy + "\n");
		str.append("mobileNumber = " + mobileNumber + "\n");
		str.append("active = " + active + "\n");
		str.append("trackingId = " + trackingId + "\n");
		str.append("name = " + name + "\n");
		str.append("medicalPrescriptionId = " + medicalPrescriptionId + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (invoiceId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_invoiceId, getInvoiceId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getInvoiceId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Invoice invoice = new Invoice();
		invoice.setFromDB(false);
		invoice.setPatientid(getPatientid());
		invoice.setPatientname(getPatientname());
		invoice.setDoctorid(getDoctorid());
		invoice.setDoctorname(getDoctorname());
		invoice.setSpecialization(getSpecialization());
		invoice.setPharmacyaddressId(getPharmacyaddressId());
		invoice.setPharmacyname(getPharmacyname());
		invoice.setPrescriptionFillRequestId(getPrescriptionFillRequestId());
		invoice.setDiscount(getDiscount());
		invoice.setTotal(getTotal());
		invoice.setInvoicestatus(getInvoicestatus());
		invoice.setPaidamount(getPaidamount());
		invoice.setCardnumber(getCardnumber());
		invoice.setTransactiontype(getTransactiontype());
		invoice.setPaid(getPaid());
		invoice.setPaidOn(getPaidOn());
		invoice.setPaymentReferenceId(getPaymentReferenceId());
		invoice.setExpectedDate(getExpectedDate());
		invoice.setShippedBy(getShippedBy());
		invoice.setMobileNumber(getMobileNumber());
		invoice.setActive(getActive());
		invoice.setTrackingId(getTrackingId());
		invoice.setName(getName());
		invoice.setMedicalPrescriptionId(getMedicalPrescriptionId());
		//afterClone(invoice);
		return invoice;
	}
}