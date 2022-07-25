package org.secondopinion.diagnosticcenter.domain; 

import java.util.ArrayList; 
import java.util.List; 
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.secondopinion.diagnosticcenter.dto.Invoice;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage; 
import org.secondopinion.utils.ObjectUtil; 

import javax.persistence.Column; 
import javax.persistence.MappedSuperclass; 
import javax.validation.constraints.NotNull; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Transient;
@MappedSuperclass 
public abstract class BaseInvoice extends BaseDomainObject<Long> {

	
	public static final String FIELD_invoiceId = "invoiceId";
	public static final String FIELD_diagnosticCenterAddressId = "diagnosticCenterAddressId";
	public static final String FIELD_diagnosticCenterName = "diagnosticCenterName";
	public static final String FIELD_diagnosticCenterAppointmentId = "diagnosticCenterAppointmentId";
	public static final String FIELD_appointmentId = "appointmentId";
	public static final String FIELD_paymentDetailsId = "paymentDetailsId";
	public static final String FIELD_amount = "amount";
	public static final String FIELD_invoiceDate = "invoiceDate";
	public static final String FIELD_SGST = "SGST";
	public static final String FIELD_CGST = "CGST";
	public static final String FIELD_active = "active";
	public static final String FIELD_totalInvoiceAmount = "totalInvoiceAmount";
	public static final String FIELD_paidOn = "paidOn";
	public static final String FIELD_invoicestatus = "invoicestatus";
	

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

	public void setInvoiceId( Long  _invoiceId){
		this.invoiceId = _invoiceId;
	}
	@Id
 	@Column(name = "invoiceId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getInvoiceId(){
		 return this.invoiceId;
	}
	public void setDiagnosticCenterAddressId(Long diagnosticCenterAddressId) {
		this.diagnosticCenterAddressId = diagnosticCenterAddressId;
	}
	@NotNull 
	@Column (name="diagnosticCenterAddressId")
	public Long getDiagnosticCenterAddressId() {
		return diagnosticCenterAddressId;
	}
	public void setDiagnosticCenterName(String diagnosticCenterName) {
		this.diagnosticCenterName = diagnosticCenterName;
	}
	@NotNull 
	@Column (name="diagnosticCenterName")
	public String getDiagnosticCenterName() {
		return diagnosticCenterName;
	}
	public void setDiagnosticCenterAppointmentId( Long  _diagnosticCenterAppointmentId){
		this.diagnosticCenterAppointmentId = _diagnosticCenterAppointmentId;
	}
	@NotNull 
	@Column (name="diagnosticCenterAppointmentId")
	public Long getDiagnosticCenterAppointmentId(){
		 return this.diagnosticCenterAppointmentId;
	}
	public void setPaymentDetailsId( Long  _paymentDetailsId){
		this.paymentDetailsId = _paymentDetailsId;
	}
	@Column (name="paymentDetailsId")
	public Long getPaymentDetailsId(){
		 return this.paymentDetailsId;
	}
	public void setAmount( Double  _amount){
		this.amount = _amount;
	}
	@Column (name="amount")
	public Double getAmount(){
		 return this.amount;
	}
	public void setInvoiceDate( Date  _invoiceDate){
		this.invoiceDate = _invoiceDate;
	}
	@Column (name="invoiceDate")
	public Date getInvoiceDate(){
		 return this.invoiceDate;
	}
	public void setSGST( Double  _SGST){
		this.SGST = _SGST;
	}
	@Column (name="SGST")
	public Double getSGST(){
		 return this.SGST;
	}
	public void setCGST( Double  _CGST){
		this.CGST = _CGST;
	}
	@Column (name="CGST")
	public Double getCGST(){
		 return this.CGST;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setTotalInvoiceAmount( Double  _totalInvoiceAmount){
		this.totalInvoiceAmount = _totalInvoiceAmount;
	}
	@Column (name="totalInvoiceAmount")
	public Double getTotalInvoiceAmount(){
		 return this.totalInvoiceAmount;
	}
	public void setPaidOn( Date  _paidOn){
		this.paidOn = _paidOn;
	}
	@Column (name="paidOn")
	public Date getPaidOn(){
		 return this.paidOn;
	}
	public void setInvoicestatus( String  _invoicestatus){
		this.invoicestatus = _invoicestatus;
	}
	@Length(max=45)
	@Column (name="invoicestatus")
	public String getInvoicestatus(){
		 return this.invoicestatus;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseInvoice other = (BaseInvoice)o;
		if(
			ObjectUtil.isEqual(getDiagnosticCenterAppointmentId(), other.getDiagnosticCenterAppointmentId()) && 
			ObjectUtil.isEqual(getPaymentDetailsId(), other.getPaymentDetailsId()) && 
			ObjectUtil.isEqual(getAmount(), other.getAmount()) && 
			ObjectUtil.isEqual(getInvoiceDate(), other.getInvoiceDate()) && 
			ObjectUtil.isEqual(getSGST(), other.getSGST()) && 
			ObjectUtil.isEqual(getCGST(), other.getCGST()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getTotalInvoiceAmount(), other.getTotalInvoiceAmount()) && 
			ObjectUtil.isEqual(getPaidOn(), other.getPaidOn()) && 
			ObjectUtil.isEqual(getInvoicestatus(), other.getInvoicestatus()) && 
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
		if(this.diagnosticCenterAppointmentId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticCenterAppointmentId+  " cannot be null"));
		}
		if((this.invoicestatus != null) && (this.invoicestatus.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_invoicestatus+  " cannot be longer than: " + 45));
		}
		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

	if(this.isFromDB()){
		if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 100));
		}
	}
		if(list.size()>0)setHasValidationErrors(true);

		setValidationMessages(list);
		
	}

	@Override
	public final void setAuditFields() {
		if(!isFromDB()){
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("invoiceId = " + invoiceId + "\n");
;
		str.append("diagnosticCenterAppointmentId = " + diagnosticCenterAppointmentId + "\n");
		str.append("paymentDetailsId = " + paymentDetailsId + "\n");
		str.append("amount = " + amount + "\n");
		str.append("invoiceDate = " + invoiceDate + "\n");
		str.append("SGST = " + SGST + "\n");
		str.append("CGST = " + CGST + "\n");
		str.append("active = " + active + "\n");
		str.append("totalInvoiceAmount = " + totalInvoiceAmount + "\n");
		str.append("paidOn = " + paidOn + "\n");
		str.append("invoicestatus = " + invoicestatus + "\n");
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
		invoice.setDiagnosticCenterAppointmentId(getDiagnosticCenterAppointmentId());
		invoice.setPaymentDetailsId(getPaymentDetailsId());
		invoice.setAmount(getAmount());
		invoice.setInvoiceDate(getInvoiceDate());
		invoice.setSGST(getSGST());
		invoice.setCGST(getCGST());
		invoice.setActive(getActive());
		invoice.setTotalInvoiceAmount(getTotalInvoiceAmount());
		invoice.setPaidOn(getPaidOn());
		invoice.setInvoicestatus(getInvoicestatus());
		//afterClone(invoice);
		return invoice;
	}
}