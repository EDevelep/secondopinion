package org.secondopinion.patient.domain; 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;  
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.enums.InvoiceTypeEnum;
import org.secondopinion.patient.dto.PatientPaymentDetails;
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
public abstract class BasePatientPaymentDetails extends BaseDomainObject<Long> {

	public static final String FIELD_patientPaymentDetailsId = "patientPaymentDetailsId";
	public static final String FIELD_patientId = "patientId";
	public static final String FIELD_chargeFor = "chargeFor";
	public static final String FIELD_feedetailsId = "feedetailsId";
	public static final String FIELD_appointmentId = "appointmentId";
	public static final String FIELD_prescriptionId = "prescriptionId";
	public static final String FIELD_carddetailsId = "carddetailsId";
	public static final String FIELD_currencyType = "currencyType";
	public static final String FIELD_totalAmount = "totalAmount";
	public static final String FIELD_amountPaid = "amountPaid";
	public static final String FIELD_discountAmount = "discountAmount";
	public static final String FIELD_transactionDate = "transactionDate";
	public static final String FIELD_transactionId = "transactionId";
	public static final String FIELD_transactionType = "transactionType";
	public static final String FIELD_transactionstatus = "transactionstatus";
	public static final String FIELD_description = "description";
	public static final String FIELD_discountType = "discountType";
	public static final String FIELD_errorMessage = "errorMessage";
	public static final String FIELD_paymentThrough = "paymentThrough";
	public static final String FIELD_refundAmount = "refundAmount";
	public static final String FIELD_refundId = "refundId";
	public static final String FIELD_refundTransactionDate = "refundTransactionDate";
	public static final String FIELD_active = "active";
	public static final String FIELD_stripeCustomerEmail = "stripeCustomerEmail";
	public static final String FIELD_stripeCustomerId = "stripeCustomerId";
	public static final String FIELD_stripeCustomerName = "stripeCustomerName";
	public static final String FIELD_createdBy= "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long patientPaymentDetailsId;
	private Long patientId;
	private String chargeFor;
	private Long feedetailsId;
	private Long appointmentId;
	private Long prescriptionId;
	private Long carddetailsId;
	private String currencyType;
	private Double totalAmount;
	private Double amountPaid;
	private Double discountAmount;
	private Date transactionDate;
	private String transactionId;
	private String transactionType;
	private String transactionstatus;
	private String description;
	private String discountType;
	private String errorMessage;
	private String paymentThrough;
	private Double refundAmount;
	private String refundId;
	private Date refundTransactionDate;
	private Character active;
	private String stripeCustomerEmail;
	private String stripeCustomerId;
	private String stripeCustomerName;
	

	public void setPatientPaymentDetailsId( Long  _patientPaymentDetailsId){
		this.patientPaymentDetailsId = _patientPaymentDetailsId;
	}
	@Id
 	@Column(name = "patientPaymentDetailsId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPatientPaymentDetailsId(){
		 return this.patientPaymentDetailsId;
	}
	public void setPatientId( Long  _patientId){
		this.patientId = _patientId;
	}
	@NotNull 
	@Column (name="patientId")
	public Long getPatientId(){
		 return this.patientId;
	}
	public void setChargeFor(String chargeFor) {
		this.chargeFor = chargeFor;
	}

	@NotNull 
	@Column (name="chargeFor")
	public String getChargeFor() {
		return chargeFor;
	}
	public void setFeedetailsId( Long  _feedetailsId){
		this.feedetailsId = _feedetailsId;
	}
	@Column (name="feedetailsId")
	public Long getFeedetailsId(){
		 return this.feedetailsId;
	}
	public void setAppointmentId( Long  _appointmentId){
		this.appointmentId = _appointmentId;
	}
	@Column (name="appointmentId")
	public Long getAppointmentId(){
		 return this.appointmentId;
	}
	public void setPrescriptionId( Long  _prescriptionId){
		this.prescriptionId = _prescriptionId;
	}
	@Column (name="prescriptionId")
	public Long getPrescriptionId(){
		 return this.prescriptionId;
	}
	public void setCarddetailsId( Long  _carddetailsId){
		this.carddetailsId = _carddetailsId;
	}
	@Column (name="carddetailsId")
	public Long getCarddetailsId(){
		 return this.carddetailsId;
	}
	public void setCurrencyType( String  _currencyType){
		this.currencyType = _currencyType;
	}
	@NotNull 
	@Length(max=255)
	@Column (name="currencyType")
	public String getCurrencyType(){
		 return this.currencyType;
	}
	public void setTotalAmount( Double  _totalAmount){
		this.totalAmount = _totalAmount;
	}
	@Column (name="totalAmount")
	public Double getTotalAmount(){
		 return this.totalAmount;
	}
	public void setAmountPaid( Double  _amountPaid){
		this.amountPaid = _amountPaid;
	}
	@NotNull 
	@Column (name="amountPaid")
	public Double getAmountPaid(){
		 return this.amountPaid;
	}
	public void setDiscountAmount( Double  _discountAmount){
		this.discountAmount = _discountAmount;
	}
	@Column (name="discountAmount")
	public Double getDiscountAmount(){
		 return this.discountAmount;
	}
	public void setTransactionDate( Date  _transactionDate){
		this.transactionDate = _transactionDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="transactionDate")
	public Date getTransactionDate(){
		 return this.transactionDate;
	}
	public void setTransactionId( String  _transactionId){
		this.transactionId = _transactionId;
	}
	@Length(max=255)
	@Column (name="transactionId")
	public String getTransactionId(){
		 return this.transactionId;
	}
	public void setTransactionType( String  _transactionType){
		this.transactionType = _transactionType;
	}
	@NotNull 
	@Length(max=255)
	@Column (name="transactionType")
	public String getTransactionType(){
		 return this.transactionType;
	}
	public void setTransactionstatus( String  _transactionstatus){
		this.transactionstatus = _transactionstatus;
	}
	@Length(max=255)
	@Column (name="transactionstatus")
	public String getTransactionstatus(){
		 return this.transactionstatus;
	}
	public void setDescription( String  _description){
		this.description = _description;
	}
	@Length(max=255)
	@Column (name="description")
	public String getDescription(){
		 return this.description;
	}
	public void setDiscountType( String  _discountType){
		this.discountType = _discountType;
	}
	@Length(max=255)
	@Column (name="discountType")
	public String getDiscountType(){
		 return this.discountType;
	}
	public void setErrorMessage( String  _errorMessage){
		this.errorMessage = _errorMessage;
	}
	@Length(max=255)
	@Column (name="errorMessage")
	public String getErrorMessage(){
		 return this.errorMessage;
	}
	public void setPaymentThrough( String  _paymentThrough){
		this.paymentThrough = _paymentThrough;
	}
	@NotNull 
	@Length(max=255)
	@Column (name="paymentThrough")
	public String getPaymentThrough(){
		 return this.paymentThrough;
	}
	public void setRefundAmount( Double  _refundAmount){
		this.refundAmount = _refundAmount;
	}
	@Column (name="refundAmount")
	public Double getRefundAmount(){
		 return this.refundAmount;
	}
	public void setRefundId( String  _refundId){
		this.refundId = _refundId;
	}
	@Length(max=255)
	@Column (name="refundId")
	public String getRefundId(){
		 return this.refundId;
	}
	public void setRefundTransactionDate( Date  _refundTransactionDate){
		this.refundTransactionDate = _refundTransactionDate;
	}
	@Column (name="refundTransactionDate")
	public Date getRefundTransactionDate(){
		 return this.refundTransactionDate;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setStripeCustomerEmail( String  _stripeCustomerEmail){
		this.stripeCustomerEmail = _stripeCustomerEmail;
	}
	@NotNull 
	@Length(max=255)
	@Column (name="stripeCustomerEmail")
	public String getStripeCustomerEmail(){
		 return this.stripeCustomerEmail;
	}
	public void setStripeCustomerId( String  _stripeCustomerId){
		this.stripeCustomerId = _stripeCustomerId;
	}
	@Column (name="stripeCustomerId")
	public String getStripeCustomerId(){
		 return this.stripeCustomerId;
	}
	public void setStripeCustomerName( String  _stripeCustomerName){
		this.stripeCustomerName = _stripeCustomerName;
	}
	@NotNull 
	@Length(max=255)
	@Column (name="stripeCustomerName")
	public String getStripeCustomerName(){
		 return this.stripeCustomerName;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BasePatientPaymentDetails other = (BasePatientPaymentDetails)o;
		if(
			ObjectUtil.isEqual(getPatientId(), other.getPatientId()) &&
			ObjectUtil.isEqual(getChargeFor(), other.getChargeFor()) &&
			ObjectUtil.isEqual(getFeedetailsId(), other.getFeedetailsId()) && 
			ObjectUtil.isEqual(getAppointmentId(), other.getAppointmentId()) && 
			ObjectUtil.isEqual(getPrescriptionId(), other.getPrescriptionId()) && 
			ObjectUtil.isEqual(getCarddetailsId(), other.getCarddetailsId()) && 
			ObjectUtil.isEqual(getCurrencyType(), other.getCurrencyType()) && 
			ObjectUtil.isEqual(getTotalAmount(), other.getTotalAmount()) && 
			ObjectUtil.isEqual(getAmountPaid(), other.getAmountPaid()) && 
			ObjectUtil.isEqual(getDiscountAmount(), other.getDiscountAmount()) && 
			ObjectUtil.isEqual(getTransactionDate(), other.getTransactionDate()) && 
			ObjectUtil.isEqual(getTransactionId(), other.getTransactionId()) && 
			ObjectUtil.isEqual(getTransactionType(), other.getTransactionType()) && 
			ObjectUtil.isEqual(getTransactionstatus(), other.getTransactionstatus()) && 
			ObjectUtil.isEqual(getDescription(), other.getDescription()) && 
			ObjectUtil.isEqual(getDiscountType(), other.getDiscountType()) && 
			ObjectUtil.isEqual(getErrorMessage(), other.getErrorMessage()) && 
			ObjectUtil.isEqual(getPaymentThrough(), other.getPaymentThrough()) && 
			ObjectUtil.isEqual(getRefundAmount(), other.getRefundAmount()) && 
			ObjectUtil.isEqual(getRefundId(), other.getRefundId()) && 
			ObjectUtil.isEqual(getRefundTransactionDate(), other.getRefundTransactionDate()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getStripeCustomerEmail(), other.getStripeCustomerEmail()) && 
			ObjectUtil.isEqual(getStripeCustomerId(), other.getStripeCustomerId()) && 
			//ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy()) && 
			//ObjectUtil.isEqual(getCreatedDate(), other.getCreatedDate()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (patientPaymentDetailsId!= null ? patientPaymentDetailsId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.patientPaymentDetailsId == null){
			 list.add(new ValidationMessage("Field " + FIELD_patientPaymentDetailsId+  " cannot be null"));
		}

		}
		if(this.patientId == null){
			 list.add(new ValidationMessage("Field " + FIELD_patientId+  " cannot be null"));
		}
		if(StringUtil.isNullOrEmpty(this.chargeFor)){
			 list.add(new ValidationMessage("Field " + FIELD_chargeFor+  " cannot be null"));
		}
		if(!StringUtil.isNullOrEmpty(this.chargeFor)){
			List<String> enums = Arrays.stream(InvoiceTypeEnum.values()).map(it -> it.name()).collect(Collectors.toList());
			if(!enums.contains(chargeFor)) {
				list.add(new ValidationMessage("Field " + FIELD_chargeFor+  " should be in " + enums));
			}
		}
		if( StringUtil.isNullOrEmpty(this.currencyType)){
			 list.add(new ValidationMessage("Field " + FIELD_currencyType+  " cannot be null"));
		}

		if((this.currencyType != null) && (this.currencyType.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_currencyType+  " cannot be longer than: " + 255));
		}

		if(this.amountPaid == null){
			 list.add(new ValidationMessage("Field " + FIELD_amountPaid+  " cannot be null"));
		}

		if((this.transactionId != null) && (this.transactionId.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_transactionId+  " cannot be longer than: " + 255));
		}

		if( StringUtil.isNullOrEmpty(this.transactionType)){
			 list.add(new ValidationMessage("Field " + FIELD_transactionType+  " cannot be null"));
		}

		if((this.transactionType != null) && (this.transactionType.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_transactionType+  " cannot be longer than: " + 255));
		}

		if((this.transactionstatus != null) && (this.transactionstatus.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_transactionstatus+  " cannot be longer than: " + 255));
		}

		if((this.description != null) && (this.description.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_description+  " cannot be longer than: " + 255));
		}

		if((this.discountType != null) && (this.discountType.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_discountType+  " cannot be longer than: " + 255));
		}

		if((this.errorMessage != null) && (this.errorMessage.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_errorMessage+  " cannot be longer than: " + 255));
		}

		if( StringUtil.isNullOrEmpty(this.paymentThrough)){
			 list.add(new ValidationMessage("Field " + FIELD_paymentThrough+  " cannot be null"));
		}

		if((this.paymentThrough != null) && (this.paymentThrough.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_paymentThrough+  " cannot be longer than: " + 255));
		}

		if((this.refundId != null) && (this.refundId.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_refundId+  " cannot be longer than: " + 255));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.stripeCustomerEmail)){
			 list.add(new ValidationMessage("Field " + FIELD_stripeCustomerEmail+  " cannot be null"));
		}

		if((this.stripeCustomerEmail != null) && (this.stripeCustomerEmail.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_stripeCustomerEmail+  " cannot be longer than: " + 255));
		}

		if( StringUtil.isNullOrEmpty(this.stripeCustomerName)){
			 list.add(new ValidationMessage("Field " + FIELD_stripeCustomerName+  " cannot be null"));
		}

		if((this.stripeCustomerName != null) && (this.stripeCustomerName.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_stripeCustomerName+  " cannot be longer than: " + 255));
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
		str.append("patientPaymentDetailsId = " + patientPaymentDetailsId + "\n");
;
		str.append("patientId = " + patientId + "\n");
		str.append("feedetailsId = " + feedetailsId + "\n");
		str.append("appointmentId = " + appointmentId + "\n");
		str.append("prescriptionId = " + prescriptionId + "\n");
		str.append("carddetailsId = " + carddetailsId + "\n");
		str.append("currencyType = " + currencyType + "\n");
		str.append("totalAmount = " + totalAmount + "\n");
		str.append("amountPaid = " + amountPaid + "\n");
		str.append("discountAmount = " + discountAmount + "\n");
		str.append("transactionDate = " + transactionDate + "\n");
		str.append("transactionId = " + transactionId + "\n");
		str.append("transactionType = " + transactionType + "\n");
		str.append("transactionstatus = " + transactionstatus + "\n");
		str.append("description = " + description + "\n");
		str.append("discountType = " + discountType + "\n");
		str.append("errorMessage = " + errorMessage + "\n");
		str.append("paymentThrough = " + paymentThrough + "\n");
		str.append("refundAmount = " + refundAmount + "\n");
		str.append("refundId = " + refundId + "\n");
		str.append("refundTransactionDate = " + refundTransactionDate + "\n");
		str.append("active = " + active + "\n");
		str.append("stripeCustomerEmail = " + stripeCustomerEmail + "\n");
		str.append("stripeCustomerId = " + stripeCustomerId + "\n");
		str.append("stripeCustomerName = " + stripeCustomerName + "\n");
		//str.append("createdBy = " + createdBy + "\n");
		//str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (patientPaymentDetailsId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_patientPaymentDetailsId, getPatientPaymentDetailsId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getPatientPaymentDetailsId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		PatientPaymentDetails patientpaymentdetails = new PatientPaymentDetails();
		patientpaymentdetails.setFromDB(false);
		patientpaymentdetails.setPatientId(getPatientId());
		patientpaymentdetails.setFeedetailsId(getFeedetailsId());
		patientpaymentdetails.setAppointmentId(getAppointmentId());
		patientpaymentdetails.setPrescriptionId(getPrescriptionId());
		patientpaymentdetails.setCarddetailsId(getCarddetailsId());
		patientpaymentdetails.setCurrencyType(getCurrencyType());
		patientpaymentdetails.setTotalAmount(getTotalAmount());
		patientpaymentdetails.setAmountPaid(getAmountPaid());
		patientpaymentdetails.setDiscountAmount(getDiscountAmount());
		patientpaymentdetails.setTransactionDate(getTransactionDate());
		patientpaymentdetails.setTransactionId(getTransactionId());
		patientpaymentdetails.setTransactionType(getTransactionType());
		patientpaymentdetails.setTransactionstatus(getTransactionstatus());
		patientpaymentdetails.setDescription(getDescription());
		patientpaymentdetails.setDiscountType(getDiscountType());
		patientpaymentdetails.setErrorMessage(getErrorMessage());
		patientpaymentdetails.setPaymentThrough(getPaymentThrough());
		patientpaymentdetails.setRefundAmount(getRefundAmount());
		patientpaymentdetails.setRefundId(getRefundId());
		patientpaymentdetails.setRefundTransactionDate(getRefundTransactionDate());
		patientpaymentdetails.setActive(getActive());
		patientpaymentdetails.setStripeCustomerEmail(getStripeCustomerEmail());
		patientpaymentdetails.setStripeCustomerId(getStripeCustomerId());
	//	patientpaymentdetails.setCreatedBy(getCreatedBy());
	//	patientpaymentdetails.setCreatedDate(getCreatedDate());
		patientpaymentdetails.setStripeCustomerName(getStripeCustomerName());
		//afterClone(patientpaymentdetails);
		return patientpaymentdetails;
	}
}