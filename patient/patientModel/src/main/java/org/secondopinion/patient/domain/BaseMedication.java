package org.secondopinion.patient.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Medication;
import org.secondopinion.utils.StringUtil; 
import org.secondopinion.utils.ObjectUtil; 

import javax.persistence.Column; 
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length; 
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseMedication extends BaseDomainObject<Long> {

	public static final String FIELD_medicationId = "medicationId";
	public static final String FIELD_patientId = "patientId";
	public static final String FIELD_medicalPrescriptionId = "medicalPrescriptionId";
	public static final String FIELD_numberofrefills = "numberofrefills";
	public static final String FIELD_medicineName = "medicineName";
	public static final String FIELD_type = "type";
	public static final String FIELD_power = "power";
	public static final String FIELD_medicineUsage = "medicineUsage";
	public static final String FIELD_notes = "notes";
	public static final String FIELD_numberOfDays = "numberOfDays";
	public static final String FIELD_quantity = "quantity";
	public static final String FIELD_enddate = "enddate";
	public static final String FIELD_morning = "morning";
	public static final String FIELD_afternoon = "afternoon";
	public static final String FIELD_active = "active";
	public static final String FIELD_evening = "evening";
	public static final String FIELD_instructions = "instructions";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long medicationId;
	private Long patientId;
	private Long medicalPrescriptionId;
	private Long numberofrefills;
	private String medicineName;
	private String type;
	private String power;
	private String medicineUsage;
	private String notes;
	private Integer numberOfDays;
	private Long quantity;
	private Date enddate;
	private Integer morning;
	private Integer afternoon;
	private Character active;
	private Integer evening;
	private String instructions;


	public void setMedicationId( Long  _medicationId){
		this.medicationId = _medicationId;
	}
	@Id
 	@Column(name = "medicationId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getMedicationId(){
		 return this.medicationId;
	}
	public void setPatientId( Long  _patientId){
		this.patientId = _patientId;
	}
	@NotNull 
	@Column (name="patientId")
	public Long getPatientId(){
		 return this.patientId;
	}
	public void setMedicalPrescriptionId( Long  _medicalPrescriptionId){
		this.medicalPrescriptionId = _medicalPrescriptionId;
	}
	@NotNull 
	@Column (name="medicalPrescriptionId")
	public Long getMedicalPrescriptionId(){
		 return this.medicalPrescriptionId;
	}
	public void setNumberofrefills( Long  _numberofrefills){
		this.numberofrefills = _numberofrefills;
	}
	@NotNull 
	@Column (name="numberofrefills")
	public Long getNumberofrefills(){
		 return this.numberofrefills;
	}
	public void setMedicineName( String  _medicineName){
		this.medicineName = _medicineName;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="medicineName")
	public String getMedicineName(){
		 return this.medicineName;
	}
	public void setType( String  _type){
		this.type = _type;
	}
	@Length(max=45)
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
	public void setMedicineUsage( String  _medicineUsage){
		this.medicineUsage = _medicineUsage;
	}
	@Length(max=45)
	@Column (name="medicineUsage")
	public String getMedicineUsage(){
		 return this.medicineUsage;
	}
	public void setNotes( String  _notes){
		this.notes = _notes;
	}
	@Length(max=45)
	@Column (name="notes")
	public String getNotes(){
		 return this.notes;
	}
	public void setNumberOfDays( Integer  _numberOfDays){
		this.numberOfDays = _numberOfDays;
	}
	@Column (name="numberOfDays")
	public Integer getNumberOfDays(){
		 return this.numberOfDays;
	}
	public void setQuantity( Long  _quantity){
		this.quantity = _quantity;
	}
	@Column (name="quantity")
	public Long getQuantity(){
		 return this.quantity;
	}
	public void setEnddate( Date  _enddate){
		this.enddate = _enddate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	@Temporal(TemporalType.DATE)
	@Column (name="enddate")
	public Date getEnddate(){
		 return this.enddate;
	}
	public void setMorning( Integer  _morning){
		this.morning = _morning;
	}
	@Column (name="morning")
	public Integer getMorning(){
		 return this.morning;
	}
	public void setAfternoon( Integer  _afternoon){
		this.afternoon = _afternoon;
	}
	@Column (name="afternoon")
	public Integer getAfternoon(){
		 return this.afternoon;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setEvening( Integer  _evening){
		this.evening = _evening;
	}
	@Column (name="evening")
	public Integer getEvening(){
		 return this.evening;
	}
	public void setInstructions( String  _instructions){
		this.instructions = _instructions;
	}
	@Length(max=500)
	@Column (name="instructions")
	public String getInstructions(){
		 return this.instructions;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseMedication other = (BaseMedication)o;
		if(
			ObjectUtil.isEqual(getPatientId(), other.getPatientId()) && 
			ObjectUtil.isEqual(getMedicalPrescriptionId(), other.getMedicalPrescriptionId()) && 
			ObjectUtil.isEqual(getNumberofrefills(), other.getNumberofrefills()) && 
			ObjectUtil.isEqual(getMedicineName(), other.getMedicineName()) && 
			ObjectUtil.isEqual(getType(), other.getType()) && 
			ObjectUtil.isEqual(getPower(), other.getPower()) && 
			ObjectUtil.isEqual(getMedicineUsage(), other.getMedicineUsage()) && 
			ObjectUtil.isEqual(getNotes(), other.getNotes()) && 
			ObjectUtil.isEqual(getNumberOfDays(), other.getNumberOfDays()) && 
			ObjectUtil.isEqual(getQuantity(), other.getQuantity()) && 
			ObjectUtil.isEqual(getEnddate(), other.getEnddate()) && 
			ObjectUtil.isEqual(getMorning(), other.getMorning()) && 
			ObjectUtil.isEqual(getAfternoon(), other.getAfternoon()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getEvening(), other.getEvening()) && 
			ObjectUtil.isEqual(getInstructions(), other.getInstructions()) && 
			ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy()) && 
			ObjectUtil.isEqual(getCreatedDate(), other.getCreatedDate()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (medicationId!= null ? medicationId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.medicationId == null){
			 list.add(new ValidationMessage("Field " + FIELD_medicationId+  " cannot be null"));
		}

		}
		if(this.patientId == null){
			 list.add(new ValidationMessage("Field " + FIELD_patientId+  " cannot be null"));
		}

		if(this.medicalPrescriptionId == null){
			 list.add(new ValidationMessage("Field " + FIELD_medicalPrescriptionId+  " cannot be null"));
		}

		if(this.numberofrefills == null){
			 list.add(new ValidationMessage("Field " + FIELD_numberofrefills+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.medicineName)){
			 list.add(new ValidationMessage("Field " + FIELD_medicineName+  " cannot be null"));
		}

		if((this.medicineName != null) && (this.medicineName.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_medicineName+  " cannot be longer than: " + 45));
		}

		if((this.type != null) && (this.type.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_type+  " cannot be longer than: " + 45));
		}

		if((this.power != null) && (this.power.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_power+  " cannot be longer than: " + 45));
		}

		if((this.medicineUsage != null) && (this.medicineUsage.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_medicineUsage+  " cannot be longer than: " + 45));
		}

		if((this.notes != null) && (this.notes.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_notes+  " cannot be longer than: " + 45));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if((this.instructions != null) && (this.instructions.length()>500)){
			 list.add(new ValidationMessage("Field " + FIELD_instructions+  " cannot be longer than: " + 500));
		}

	if(this.isFromDB()){
		if((this.createdBy != null) && (this.createdBy.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 45));
		}
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
			this.createdBy = AppThreadLocal.getUserName();
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("medicationId = " + medicationId + "\n");
;
		str.append("patientId = " + patientId + "\n");
		str.append("medicalPrescriptionId = " + medicalPrescriptionId + "\n");
		str.append("numberofrefills = " + numberofrefills + "\n");
		str.append("medicineName = " + medicineName + "\n");
		str.append("type = " + type + "\n");
		str.append("power = " + power + "\n");
		str.append("medicineUsage = " + medicineUsage + "\n");
		str.append("notes = " + notes + "\n");
		str.append("numberOfDays = " + numberOfDays + "\n");
		str.append("quantity = " + quantity + "\n");
		str.append("enddate = " + enddate + "\n");
		str.append("morning = " + morning + "\n");
		str.append("afternoon = " + afternoon + "\n");
		str.append("active = " + active + "\n");
		str.append("evening = " + evening + "\n");
		str.append("instructions = " + instructions + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (medicationId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_medicationId, getMedicationId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getMedicationId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Medication medication = new Medication();
		medication.setFromDB(false);
		medication.setPatientId(getPatientId());
		medication.setMedicalPrescriptionId(getMedicalPrescriptionId());
		medication.setNumberofrefills(getNumberofrefills());
		medication.setMedicineName(getMedicineName());
		medication.setType(getType());
		medication.setPower(getPower());
		medication.setMedicineUsage(getMedicineUsage());
		medication.setNotes(getNotes());
		medication.setNumberOfDays(getNumberOfDays());
		medication.setQuantity(getQuantity());
		medication.setEnddate(getEnddate());
		medication.setMorning(getMorning());
		medication.setAfternoon(getAfternoon());
		medication.setActive(getActive());
		medication.setEvening(getEvening());
		medication.setInstructions(getInstructions());
		medication.setCreatedDate(getCreatedDate());
		//afterClone(medication);
		return medication;
	}
}