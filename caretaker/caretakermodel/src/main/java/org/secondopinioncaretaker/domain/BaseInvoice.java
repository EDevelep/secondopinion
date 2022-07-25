package org.secondopinioncaretaker.domain; 

import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.caretaker.dto.Invoice;
import org.secondopinion.domain.BaseDomainObject;
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
import org.hibernate.validator.constraints.Length; 
@MappedSuperclass 
public abstract class BaseInvoice extends BaseDomainObject<Long> {

	public static final String FIELD_invoiceId = "invoiceId";
	public static final String FIELD_patientid = "patientid";
	public static final String FIELD_caretekerId = "caretekerId";
	public static final String FIELD_caretakername = "caretakername";
	public static final String FIELD_patientname = "patientname";
	public static final String FIELD_caretakerappointmentid = "caretakerappointmentid";
	public static final String FIELD_patientappointmentid = "patientappointmentid";
	public static final String FIELD_cardnumber = "cardnumber";
	public static final String FIELD_transactiontype = "transactiontype";
	public static final String FIELD_invoicestatus = "invoicestatus";
	public static final String FIELD_amount = "amount";
	public static final String FIELD_payByDate = "payByDate";
	public static final String FIELD_paid = "paid";
	public static final String FIELD_paidOn = "paidOn";
	public static final String FIELD_active = "active";
	public static final String FIELD_paymentReferenceId = "paymentReferenceId";
	

	private Long invoiceId;
	private Long patientid;
	private Long caretekerId;
	private String caretakername;
	private String patientname;
	private Long caretakerappointmentid;
	private Long patientappointmentid;
	private String cardnumber;
	private String transactiontype;
	private String invoicestatus;
	private Double amount;
	private Date payByDate;
	private Character paid;
	private Date paidOn;
	private Character active;
	private String paymentReferenceId;

	
	public void setActive( Character  _active){
		this.active = _active;
	}
	@Column (name="active")
	public Character getActive(){
		return this.active;
	}

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
	public void setCaretekerId( Long  _caretekerId){
		this.caretekerId = _caretekerId;
	}
	@Column (name="caretekerId")
	public Long getCaretekerId(){
		 return this.caretekerId;
	}
	public void setCaretakername( String  _caretakername){
		this.caretakername = _caretakername;
	}
	@Length(max=45)
	@Column (name="caretakername")
	public String getCaretakername(){
		 return this.caretakername;
	}
	public void setPatientname( String  _patientname){
		this.patientname = _patientname;
	}
	@Length(max=45)
	@Column (name="patientname")
	public String getPatientname(){
		 return this.patientname;
	}
	public void setCaretakerappointmentid( Long  _caretakerappointmentid){
		this.caretakerappointmentid = _caretakerappointmentid;
	}
	@Column (name="caretakerappointmentid")
	public Long getCaretakerappointmentid(){
		 return this.caretakerappointmentid;
	}
	public void setPatientappointmentid( Long  _patientappointmentid){
		this.patientappointmentid = _patientappointmentid;
	}
	@Column (name="patientappointmentid")
	public Long getPatientappointmentid(){
		 return this.patientappointmentid;
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
	public void setInvoicestatus( String  _invoicestatus){
		this.invoicestatus = _invoicestatus;
	}
	@Length(max=45)
	@Column (name="invoicestatus")
	public String getInvoicestatus(){
		 return this.invoicestatus;
	}
	public void setAmount( Double  _amount){
		this.amount = _amount;
	}
	@NotNull 
	@Column (name="amount")
	public Double getAmount(){
		 return this.amount;
	}
	public void setPayByDate( Date  _payByDate){
		this.payByDate = _payByDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="payByDate")
	public Date getPayByDate(){
		 return this.payByDate;
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
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="paidOn")
	public Date getPaidOn(){
		 return this.paidOn;
	}
	/*public void setActive( Character  _active){
		this.active = _active;
	}
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}*/
	public void setPaymentReferenceId( String  _paymentReferenceId){
		this.paymentReferenceId = _paymentReferenceId;
	}
	@Length(max=150)
	@Column (name="paymentReferenceId")
	public String getPaymentReferenceId(){
		 return this.paymentReferenceId;
	}
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseInvoice other = (BaseInvoice)o;
		if(
			ObjectUtil.isEqual(getPatientid(), other.getPatientid()) && 
			ObjectUtil.isEqual(getCaretekerId(), other.getCaretekerId()) && 
			ObjectUtil.isEqual(getCaretakername(), other.getCaretakername()) && 
			ObjectUtil.isEqual(getPatientname(), other.getPatientname()) && 
			ObjectUtil.isEqual(getCaretakerappointmentid(), other.getCaretakerappointmentid()) && 
			ObjectUtil.isEqual(getPatientappointmentid(), other.getPatientappointmentid()) && 
			ObjectUtil.isEqual(getCardnumber(), other.getCardnumber()) && 
			ObjectUtil.isEqual(getTransactiontype(), other.getTransactiontype()) && 
			ObjectUtil.isEqual(getInvoicestatus(), other.getInvoicestatus()) && 
			ObjectUtil.isEqual(getAmount(), other.getAmount()) && 
			ObjectUtil.isEqual(getPayByDate(), other.getPayByDate()) && 
			ObjectUtil.isEqual(getPaid(), other.getPaid()) && 
			ObjectUtil.isEqual(getPaidOn(), other.getPaidOn()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) &&
			ObjectUtil.isEqual(getPaymentReferenceId(), other.getPaymentReferenceId()) && 
			ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy()) && 
			ObjectUtil.isEqual(getCreatedDate(), other.getCreatedDate()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs()) 
		     ){
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

		if((this.caretakername != null) && (this.caretakername.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_caretakername+  " cannot be longer than: " + 45));
		}

		if((this.patientname != null) && (this.patientname.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_patientname+  " cannot be longer than: " + 45));
		}

		if((this.cardnumber != null) && (this.cardnumber.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_cardnumber+  " cannot be longer than: " + 255));
		}

		if((this.transactiontype != null) && (this.transactiontype.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_transactiontype+  " cannot be longer than: " + 45));
		}

		if((this.invoicestatus != null) && (this.invoicestatus.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_invoicestatus+  " cannot be longer than: " + 45));
		}

		if(this.amount == null){
			 list.add(new ValidationMessage("Field " + FIELD_amount+  " cannot be null"));
		}

		if((this.paymentReferenceId != null) && (this.paymentReferenceId.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_paymentReferenceId+  " cannot be longer than: " + 150));
		}

	if(this.isFromDB()){
		if((this.createdBy != null) && (this.createdBy.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 45));
		}
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
		if(!isFromDB()){
			this.createdBy = AppThreadLocal.getUserName();
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
		str.append("caretekerId = " + caretekerId + "\n");
		str.append("caretakername = " + caretakername + "\n");
		str.append("patientname = " + patientname + "\n");
		str.append("caretakerappointmentid = " + caretakerappointmentid + "\n");
		str.append("patientappointmentid = " + patientappointmentid + "\n");
		str.append("cardnumber = " + cardnumber + "\n");
		str.append("transactiontype = " + transactiontype + "\n");
		str.append("invoicestatus = " + invoicestatus + "\n");
		str.append("amount = " + amount + "\n");
		str.append("payByDate = " + payByDate + "\n");
		str.append("paid = " + paid + "\n");
		str.append("paidOn = " + paidOn + "\n");
		str.append("active = " + active + "\n");
		str.append("paymentReferenceId = " + paymentReferenceId + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
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
		invoice.setCaretekerId(getCaretekerId());
		invoice.setCaretakername(getCaretakername());
		invoice.setPatientname(getPatientname());
		invoice.setCaretakerappointmentid(getCaretakerappointmentid());
		invoice.setPatientappointmentid(getPatientappointmentid());
		invoice.setCardnumber(getCardnumber());
		invoice.setTransactiontype(getTransactiontype());
		invoice.setInvoicestatus(getInvoicestatus());
		invoice.setAmount(getAmount());
		invoice.setPayByDate(getPayByDate());
		invoice.setPaid(getPaid());
		invoice.setPaidOn(getPaidOn());
        invoice.setActive(getActive());
		invoice.setPaymentReferenceId(getPaymentReferenceId());
		invoice.setCreatedDate(getCreatedDate());
		//afterClone(invoice);
		return invoice;
	}
}