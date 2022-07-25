package org.secondopinion.pharmacy.domain; 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.pharmacy.dto.Prescriptionprice;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BasePrescriptionprice extends BaseDomainObject<Long> {

	public static final String FIELD_prescriptionPriceId = "prescriptionPriceId";
	public static final String FIELD_invoiceId = "invoiceId";
	public static final String FIELD_prescriptionFillRequestId = "prescriptionFillRequestId";
	public static final String FIELD_medicalPrescriptionId = "medicalPrescriptionId";
	public static final String FIELD_patientAppointmentId = "patientAppointmentId";
	public static final String FIELD_patientId = "patientId";
	public static final String FIELD_medicine = "medicine";
	public static final String FIELD_type = "type";
	public static final String FIELD_power = "power";
	public static final String FIELD_dosage = "dosage";
	public static final String FIELD_quantity = "quantity";
	public static final String FIELD_unitPrice = "unitPrice";
	public static final String FIELD_cgst = "cgst";
	public static final String FIELD_sgst = "sgst";
	public static final String FIELD_discount = "discount";
	public static final String FIELD_totalPrice = "totalPrice";
	public static final String FIELD_active = "active";
	public static final String FIELD_numberOfDays = "numberOfDays";
	

	private Long prescriptionPriceId;
	private Long invoiceId;
	private Long prescriptionFillRequestId;
	private Long medicalPrescriptionId;
	private Long patientAppointmentId;
	private Long patientId;
	private String medicine;
	private String type;
	private String power;
	private String dosage;
	private Long quantity;
	private Double unitPrice;
	private Double cgst;
	private Double sgst;
	private Double discount;
	private Double totalPrice;
	private Integer numberOfDays;
	private Character active;
	
	public void setPrescriptionPriceId( Long  _prescriptionPriceId){
		this.prescriptionPriceId = _prescriptionPriceId;
	}
	@Id
 	@Column(name = "prescriptionPriceId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPrescriptionPriceId(){
		 return this.prescriptionPriceId;
	}
	public void setInvoiceId( Long  _invoiceId){
		this.invoiceId = _invoiceId;
	}
	@Column (name="invoiceId")
	public Long getInvoiceId(){
		 return this.invoiceId;
	}
	public void setPrescriptionFillRequestId( Long  _prescriptionFillRequestId){
		this.prescriptionFillRequestId = _prescriptionFillRequestId;
	}
	@NotNull 
	@Column (name="prescriptionFillRequestId")
	public Long getPrescriptionFillRequestId(){
		 return this.prescriptionFillRequestId;
	}
	public void setMedicalPrescriptionId( Long  _medicalPrescriptionId){
		this.medicalPrescriptionId = _medicalPrescriptionId;
	}
	@NotNull 
	@Column (name="medicalPrescriptionId")
	public Long getMedicalPrescriptionId(){
		 return this.medicalPrescriptionId;
	}
	public void setPatientAppointmentId( Long  _patientAppointmentId){
		this.patientAppointmentId = _patientAppointmentId;
	}
	@NotNull 
	@Column (name="patientAppointmentId")
	public Long getPatientAppointmentId(){
		 return this.patientAppointmentId;
	}
	public void setPatientId( Long  _patientId){
		this.patientId = _patientId;
	}
	@NotNull 
	@Column (name="patientId")
	public Long getPatientId(){
		 return this.patientId;
	}
	public void setMedicine( String  _medicine){
		this.medicine = _medicine;
	}
	@Length(max=100)
	@Column (name="medicine")
	public String getMedicine(){
		 return this.medicine;
	}
	public void setType( String  _type){
		this.type = _type;
	}
	@Length(max=100)
	@Column (name="type")
	public String getType(){
		 return this.type;
	}
	public void setPower( String  _power){
		this.power = _power;
	}
	@Length(max=45)
	@Column (name="power")
	public String getPower(){
		 return this.power;
	}
	public void setDosage( String  _dosage){
		this.dosage = _dosage;
	}
	@Length(max=45)
	@Column (name="dosage")
	public String getDosage(){
		 return this.dosage;
	}
	public void setTotalPrice( Double  _totalPrice){
		this.totalPrice = _totalPrice;
	}
	@NotNull 
	@Column (name="totalPrice")
	public Double getTotalPrice(){
		 return this.totalPrice;
	}
	
	@NotNull 
	@Column (name="quantity")
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	@NotNull 
	@Column (name="unitPrice")
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}
	@NotNull 
	@Column (name="cgst")
	public Double getCgst() {
		return cgst;
	}
	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}
	@NotNull 
	@Column (name="sgst")
	public Double getSgst() {
		return sgst;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setDiscount( Double  _discount){
		this.discount = _discount;
	}
	@Column (name="discount")
	public Double getDiscount(){
		 return this.discount;
	}
	public void setNumberOfDays( Integer  _numberOfDays){
		this.numberOfDays = _numberOfDays;
	}
	@Column (name="numberOfDays")
	public Integer getNumberOfDays(){
		 return this.numberOfDays;
	}
	
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BasePrescriptionprice other = (BasePrescriptionprice)o;
		if(
			ObjectUtil.isEqual(getInvoiceId(), other.getInvoiceId()) && 
			ObjectUtil.isEqual(getPrescriptionFillRequestId(), other.getPrescriptionFillRequestId()) && 
			ObjectUtil.isEqual(getMedicalPrescriptionId(), other.getMedicalPrescriptionId()) && 
			ObjectUtil.isEqual(getPatientAppointmentId(), other.getPatientAppointmentId()) && 
			ObjectUtil.isEqual(getPatientId(), other.getPatientId()) && 
			ObjectUtil.isEqual(getMedicine(), other.getMedicine()) && 
			ObjectUtil.isEqual(getType(), other.getType()) && 
			ObjectUtil.isEqual(getPower(), other.getPower()) && 
			ObjectUtil.isEqual(getDosage(), other.getDosage()) && 
			ObjectUtil.isEqual(getUnitPrice(), other.getUnitPrice()) && 
			ObjectUtil.isEqual(getCgst(), other.getCgst()) && 
			ObjectUtil.isEqual(getSgst(), other.getSgst()) && 
			ObjectUtil.isEqual(getDiscount(), other.getDiscount()) &&
			ObjectUtil.isEqual(getTotalPrice(), other.getTotalPrice()) && 
			ObjectUtil.isEqual(getNumberOfDays(), other.getNumberOfDays()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (prescriptionPriceId!= null ? prescriptionPriceId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.prescriptionPriceId == null){
			 list.add(new ValidationMessage("Field " + FIELD_prescriptionPriceId+  " cannot be null"));
		}

		}
		if(this.prescriptionFillRequestId == null){
			 list.add(new ValidationMessage("Field " + FIELD_prescriptionFillRequestId+  " cannot be null"));
		}

		if(this.medicalPrescriptionId == null){
			 list.add(new ValidationMessage("Field " + FIELD_medicalPrescriptionId+  " cannot be null"));
		}

		if(this.patientAppointmentId == null){
			 list.add(new ValidationMessage("Field " + FIELD_patientAppointmentId+  " cannot be null"));
		}

		if(this.patientId == null){
			 list.add(new ValidationMessage("Field " + FIELD_patientId+  " cannot be null"));
		}

		if((this.medicine != null) && (this.medicine.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_medicine+  " cannot be longer than: " + 100));
		}

		if((this.type != null) && (this.type.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_type+  " cannot be longer than: " + 100));
		}

		if((this.power != null) && (this.power.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_power+  " cannot be longer than: " + 45));
		}

		if((this.dosage != null) && (this.dosage.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_dosage+  " cannot be longer than: " + 45));
		}
		if(this.unitPrice == null){
			 list.add(new ValidationMessage("Field " + FIELD_unitPrice+  " cannot be null"));
		}
		if(this.cgst == null){
			 list.add(new ValidationMessage("Field " + FIELD_cgst+  " cannot be null"));
		}
		if(this.sgst == null){
			 list.add(new ValidationMessage("Field " + FIELD_sgst+  " cannot be null"));
		}
		if(this.totalPrice == null){
			 list.add(new ValidationMessage("Field " + FIELD_totalPrice+  " cannot be null"));
		}
		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
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
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("prescriptionPriceId = " + prescriptionPriceId + "\n");
;
		str.append("invoiceId = " + invoiceId + "\n");
		str.append("prescriptionFillRequestId = " + prescriptionFillRequestId + "\n");
		str.append("medicalPrescriptionId = " + medicalPrescriptionId + "\n");
		str.append("patientAppointmentId = " + patientAppointmentId + "\n");
		str.append("patientId = " + patientId + "\n");
		str.append("medicine = " + medicine + "\n");
		str.append("type = " + type + "\n");
		str.append("power = " + power + "\n");
		str.append("dosage = " + dosage + "\n");
		str.append("unitPrice = " + unitPrice + "\n");
		str.append("sgst = " + sgst + "\n");
		str.append("cgst = " + cgst + "\n");
		str.append("numberOfDays = " + numberOfDays + "\n");
		str.append("totalPrice = " + totalPrice + "\n");
		str.append("discount = " + discount + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (prescriptionPriceId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_prescriptionPriceId, getPrescriptionPriceId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getPrescriptionPriceId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Prescriptionprice prescriptionprice = new Prescriptionprice();
		prescriptionprice.setFromDB(false);
		prescriptionprice.setInvoiceId(getInvoiceId());
		prescriptionprice.setPrescriptionFillRequestId(getPrescriptionFillRequestId());
		prescriptionprice.setMedicalPrescriptionId(getMedicalPrescriptionId());
		prescriptionprice.setPatientAppointmentId(getPatientAppointmentId());
		prescriptionprice.setPatientId(getPatientId());
		prescriptionprice.setMedicine(getMedicine());
		prescriptionprice.setType(getType());
		prescriptionprice.setPower(getPower());
		prescriptionprice.setDosage(getDosage());
		prescriptionprice.setUnitPrice(getUnitPrice());
		prescriptionprice.setCgst(getCgst());
		prescriptionprice.setSgst(getSgst());
		prescriptionprice.setTotalPrice(getTotalPrice());
		prescriptionprice.setDiscount(getDiscount());
		prescriptionprice.setNumberOfDays(getNumberOfDays());
		prescriptionprice.setActive(getActive());
		//afterClone(prescriptionprice);
		return prescriptionprice;
	}
}