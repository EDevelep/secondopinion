package org.secondopinion.patient.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Invoice;
import org.secondopinion.utils.StringUtil;
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
	public static final String FIELD_invoicetype = "invoicetype";
	public static final String FIELD_invoiceentityid = "invoiceentityid";
	public static final String FIELD_invoiceentityname = "invoiceentityname";
	public static final String FIELD_invoicereferenceid = "invoicereferenceid";
	public static final String FIELD_invoiceentityreferenceid = "invoiceentityreferenceid";
	public static final String FIELD_invoicestatus = "invoicestatus";
	public static final String FIELD_cardnumber = "cardnumber";
	public static final String FIELD_transactiontype = "transactiontype";
	public static final String FIELD_amount = "amount";
	public static final String FIELD_discount = "discount";
	public static final String FIELD_paid = "paid";
	public static final String FIELD_payByDate = "payByDate";
	public static final String FIELD_active = "active";
	public static final String FIELD_paidOn = "paidOn";
	public static final String FIELD_paymentReferenceId = "paymentReferenceId";
	public static final String FIELD_entityInvoiceId = "entityInvoiceId";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";
	public static final String FIELD_creartedBy = "creartedBy";
	public static final String FIELD_createdDate = "createdDate";

	private Long invoiceId;
	private Long patientid;
	private String patientname;
	private String invoicetype;// DOCTOR_APPOINTMENT or DIAGNOSTIC_CENTER_APPOINTMENT OR PHARMACY
	private Long invoicereferenceid;// patient_appointment_id or patient_prescription_id
	private Long invoiceentityid;// doctor_id or pharmacy_id or diagnostic_center_id
	private String invoiceentityname;// doctor_name or pharmacy_name or diagnostic_center_name
	private Long invoiceentityreferenceid;// doctor_appointment_id or diagnostic_appointmentid or
											// pharmacy_prescription_id
	private String invoicestatus;

	private String cardnumber;
	private String transactiontype;
	private Double amount;
	private Double discount;
	private Character paid;
	private Date payByDate;
	private Character active;
	private Date paidOn;
	private String paymentReferenceId;
	private Long entityInvoiceId;

	public void setInvoiceId(Long _invoiceId) {
		this.invoiceId = _invoiceId;
	}

	@Id
	@Column(name = "invoiceId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getInvoiceId() {
		return this.invoiceId;
	}

	public void setPatientid(Long _patientid) {
		this.patientid = _patientid;
	}

	@NotNull
	@Column(name = "patientid")
	public Long getPatientid() {
		return this.patientid;
	}

	public void setPatientname(String _patientname) {
		this.patientname = _patientname;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "patientname")
	public String getPatientname() {
		return this.patientname;
	}

	public void setInvoicetype(String _invoicetype) {
		this.invoicetype = _invoicetype;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "invoicetype")
	public String getInvoicetype() {
		return this.invoicetype;
	}

	public void setInvoiceentityid(Long _invoiceentityid) {
		this.invoiceentityid = _invoiceentityid;
	}

	@NotNull
	@Column(name = "invoiceentityid")
	public Long getInvoiceentityid() {
		return this.invoiceentityid;
	}

	public void setInvoiceentityname(String _invoiceentityname) {
		this.invoiceentityname = _invoiceentityname;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "invoiceentityname")
	public String getInvoiceentityname() {
		return this.invoiceentityname;
	}

	public void setInvoicereferenceid(Long _invoicereferenceid) {
		this.invoicereferenceid = _invoicereferenceid;
	}

	@NotNull
	@Column(name = "invoicereferenceid")
	public Long getInvoicereferenceid() {
		return this.invoicereferenceid;
	}

	public void setInvoiceentityreferenceid(Long _invoiceentityreferenceid) {
		this.invoiceentityreferenceid = _invoiceentityreferenceid;
	}

	@NotNull
	@Column(name = "invoiceentityreferenceid")
	public Long getInvoiceentityreferenceid() {
		return this.invoiceentityreferenceid;
	}

	public void setInvoicestatus(String _invoicestatus) {
		this.invoicestatus = _invoicestatus;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "invoicestatus")
	public String getInvoicestatus() {
		return this.invoicestatus;
	}

	public void setCardnumber(String _cardnumber) {
		this.cardnumber = _cardnumber;
	}

	@Length(max = 255)
	@Column(name = "cardnumber")
	public String getCardnumber() {
		return this.cardnumber;
	}

	public void setTransactiontype(String _transactiontype) {
		this.transactiontype = _transactiontype;
	}

	@Length(max = 45)
	@Column(name = "transactiontype")
	public String getTransactiontype() {
		return this.transactiontype;
	}

	public void setAmount(Double _amount) {
		this.amount = _amount;
	}

	@Column(name = "amount")
	public Double getAmount() {
		return this.amount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Column(name = "discount")
	public Double getDiscount() {
		return discount;
	}

	public void setPaid(Character _paid) {
		this.paid = _paid;
	}

	@Column(name = "paid")
	public Character getPaid() {
		return this.paid;
	}

	public void setPayByDate(Date _payByDate) {
		this.payByDate = _payByDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	@Column(name = "payByDate")
	public Date getPayByDate() {
		return this.payByDate;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@NotNull
	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	public void setPaidOn(Date _paidOn) {
		this.paidOn = _paidOn;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	@Column(name = "paidOn")
	public Date getPaidOn() {
		return this.paidOn;
	}

	public void setPaymentReferenceId(String _paymentReferenceId) {
		this.paymentReferenceId = _paymentReferenceId;
	}

	@Length(max = 45)
	@Column(name = "paymentReferenceId")
	public String getPaymentReferenceId() {
		return this.paymentReferenceId;
	}

	public void setEntityInvoiceId(Long _entityInvoiceId) {
		this.entityInvoiceId = _entityInvoiceId;
	}

	@Column(name = "entityInvoiceId")
	public Long getEntityInvoiceId() {
		return this.entityInvoiceId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseInvoice other = (BaseInvoice) o;
		if (ObjectUtil.isEqual(getPatientid(), other.getPatientid())
				&& ObjectUtil.isEqual(getPatientname(), other.getPatientname())
				&& ObjectUtil.isEqual(getInvoicetype(), other.getInvoicetype())
				&& ObjectUtil.isEqual(getInvoiceentityid(), other.getInvoiceentityid())
				&& ObjectUtil.isEqual(getInvoiceentityname(), other.getInvoiceentityname())
				&& ObjectUtil.isEqual(getInvoicereferenceid(), other.getInvoicereferenceid())
				&& ObjectUtil.isEqual(getInvoiceentityreferenceid(), other.getInvoiceentityreferenceid())
				&& ObjectUtil.isEqual(getInvoicestatus(), other.getInvoicestatus())
				&& ObjectUtil.isEqual(getCardnumber(), other.getCardnumber())
				&& ObjectUtil.isEqual(getTransactiontype(), other.getTransactiontype())
				&& ObjectUtil.isEqual(getAmount(), other.getAmount())
				&& ObjectUtil.isEqual(getDiscount(), other.getDiscount())
				&& ObjectUtil.isEqual(getPaid(), other.getPaid())
				&& ObjectUtil.isEqual(getPayByDate(), other.getPayByDate())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getPaidOn(), other.getPaidOn())
				&& ObjectUtil.isEqual(getPaymentReferenceId(), other.getPaymentReferenceId())
				&& ObjectUtil.isEqual(getEntityInvoiceId(), other.getEntityInvoiceId())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (invoiceId != null ? invoiceId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.invoiceId == null) {
				list.add(new ValidationMessage("Field " + FIELD_invoiceId + " cannot be null"));
			}

		}
		if (this.patientid == null) {
			list.add(new ValidationMessage("Field " + FIELD_patientid + " cannot be null"));
		}

		if (StringUtil.isNullOrEmpty(this.patientname)) {
			list.add(new ValidationMessage("Field " + FIELD_patientname + " cannot be null"));
		}

		if ((this.patientname != null) && (this.patientname.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_patientname + " cannot be longer than: " + 45));
		}
		if (StringUtil.isNullOrEmpty(this.invoicetype)) {
			list.add(new ValidationMessage("Field " + FIELD_invoicetype + " cannot be null"));
		}

		if ((this.invoicetype != null) && (this.invoicetype.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_invoicetype + " cannot be longer than: " + 45));
		}

		if (this.invoiceentityid == null) {
			list.add(new ValidationMessage("Field " + FIELD_invoiceentityid + " cannot be null"));
		}

		if (StringUtil.isNullOrEmpty(this.invoiceentityname)) {
			list.add(new ValidationMessage("Field " + FIELD_invoiceentityname + " cannot be null"));
		}

		if ((this.invoiceentityname != null) && (this.invoiceentityname.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_invoiceentityname + " cannot be longer than: " + 45));
		}

		if (this.invoicereferenceid == null) {
			list.add(new ValidationMessage("Field " + FIELD_invoicereferenceid + " cannot be null"));
		}

		if (this.invoiceentityreferenceid == null) {
			list.add(new ValidationMessage("Field " + FIELD_invoiceentityreferenceid + " cannot be null"));
		}

		if (StringUtil.isNullOrEmpty(this.invoicestatus)) {
			list.add(new ValidationMessage("Field " + FIELD_invoicestatus + " cannot be null"));
		}

		if ((this.invoicestatus != null) && (this.invoicestatus.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_invoicestatus + " cannot be longer than: " + 45));
		}

		if ((this.cardnumber != null) && (this.cardnumber.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_cardnumber + " cannot be longer than: " + 45));
		}

		if ((this.transactiontype != null) && (this.transactiontype.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_transactiontype + " cannot be longer than: " + 45));
		}

		if (this.active == null) {
			list.add(new ValidationMessage("Field " + FIELD_active + " cannot be null"));
		}

		if ((this.paymentReferenceId != null) && (this.paymentReferenceId.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_paymentReferenceId + " cannot be longer than: " + 45));
		}

		if (this.isFromDB()) {
			if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 45)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 45));
			}

		}
		if (list.size() > 0)
			setHasValidationErrors(true);

		setValidationMessages(list);

	}

	@Override
	public final void setAuditFields() {
		if (!isFromDB()) {
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("invoiceId = " + invoiceId + "\n");
		;
		str.append("patientid = " + patientid + "\n");
		str.append("patientname = " + patientname + "\n");
		str.append("invoicetype = " + invoicetype + "\n");
		str.append("invoiceentityid = " + invoiceentityid + "\n");
		str.append("invoiceentityname = " + invoiceentityname + "\n");
		str.append("invoicereferenceid = " + invoicereferenceid + "\n");
		str.append("invoiceentityreferenceid = " + invoiceentityreferenceid + "\n");
		str.append("invoicestatus = " + invoicestatus + "\n");
		str.append("cardnumber = " + cardnumber + "\n");
		str.append("transactiontype = " + transactiontype + "\n");
		str.append("amount = " + amount + "\n");
		str.append("discount = " + discount + "\n");
		str.append("paid = " + paid + "\n");
		str.append("payByDate = " + payByDate + "\n");
		str.append("active = " + active + "\n");
		str.append("paidOn = " + paidOn + "\n");
		str.append("paymentReferenceId = " + paymentReferenceId + "\n");
		str.append("entityInvoiceId = " + entityInvoiceId + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (invoiceId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_invoiceId, getInvoiceId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getInvoiceId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Invoice invoice = new Invoice();
		invoice.setFromDB(false);
		invoice.setPatientid(getPatientid());
		invoice.setPatientname(getPatientname());
		invoice.setInvoicetype(getInvoicetype());
		invoice.setInvoiceentityid(getInvoiceentityid());
		invoice.setInvoiceentityname(getInvoiceentityname());
		invoice.setInvoicereferenceid(getInvoicereferenceid());
		invoice.setInvoiceentityreferenceid(getInvoiceentityreferenceid());
		invoice.setInvoicestatus(getInvoicestatus());
		invoice.setCardnumber(getCardnumber());
		invoice.setTransactiontype(getTransactiontype());
		invoice.setAmount(getAmount());
		invoice.setDiscount(getDiscount());
		invoice.setPaid(getPaid());
		invoice.setPayByDate(getPayByDate());
		invoice.setActive(getActive());
		invoice.setPaidOn(getPaidOn());
		invoice.setPaymentReferenceId(getPaymentReferenceId());
		invoice.setEntityInvoiceId(getEntityInvoiceId());
		// afterClone(invoice);
		return invoice;
	}
}