package org.secondopinion.doctor.domain; 

import java.util.ArrayList; 
import java.util.List; 
import java.util.Date;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.doctor.dto.Baseschedule;
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
public abstract class BaseBaseschedule extends BaseDomainObject<Long> {

	public static final String FIELD_basseScheduleId = "basseScheduleId";
	public static final String FIELD_doctorId = "doctorId";
	public static final String FIELD_active = "active";
	public static final String FIELD_numberofmonths = "numberofmonths";
	public static final String FIELD_minSlot = "minSlot";
	public static final String FIELD_monday = "monday";
	public static final String FIELD_tuesday = "tuesday";
	public static final String FIELD_wednesday = "wednesday";
	public static final String FIELD_thursday = "thursday";
	public static final String FIELD_friday = "friday";
	public static final String FIELD_saturday = "saturday";
	public static final String FIELD_sunday = "sunday";
	public static final String FIELD_associateEntityType = "associateEntityType";
	public static final String FIELD_associateEntityId = "associateEntityId";
	public static final String FIELD_associateEntityName = "associateEntityName";
	
	private Long basseScheduleId;
	private Long doctorId;
	private Character active;
	private Integer numberofmonths;
	private Long minSlot;
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String saturday;
	private String sunday;
	private Long associateEntityId;
	private String associateEntityType;
	private String associateEntityName;

	public void setBasseScheduleId( Long  _basseScheduleId){
		this.basseScheduleId = _basseScheduleId;
	}
	@Id
 	@Column(name = "basseScheduleId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getBasseScheduleId(){
		 return this.basseScheduleId;
	}
	public void setDoctorId( Long  _doctorId){
		this.doctorId = _doctorId;
	}
	@NotNull 
	@Column (name="doctorId")
	public Long getDoctorId(){
		 return this.doctorId;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setNumberofmonths( Integer  _numberofmonths){
		this.numberofmonths = _numberofmonths;
	}
	@NotNull 
	@Column (name="numberofmonths")
	public Integer getNumberofmonths(){
		 return this.numberofmonths;
	}
	public void setMinSlot( Long  _minSlot){
		this.minSlot = _minSlot;
	}
	@NotNull 
	@Column (name="minSlot")
	public Long getMinSlot(){
		 return this.minSlot;
	}
	public void setMonday( String  _monday){
		this.monday = _monday;
	}
	@Length(max=250)
	@Column (name="monday")
	public String getMonday(){
		 return this.monday;
	}
	public void setTuesday( String  _tuesday){
		this.tuesday = _tuesday;
	}
	@Length(max=250)
	@Column (name="tuesday")
	public String getTuesday(){
		 return this.tuesday;
	}
	public void setWednesday( String  _wednesday){
		this.wednesday = _wednesday;
	}
	@Length(max=250)
	@Column (name="wednesday")
	public String getWednesday(){
		 return this.wednesday;
	}
	public void setThursday( String  _thursday){
		this.thursday = _thursday;
	}
	@Length(max=250)
	@Column (name="thursday")
	public String getThursday(){
		 return this.thursday;
	}
	public void setFriday( String  _friday){
		this.friday = _friday;
	}
	@Length(max=250)
	@Column (name="friday")
	public String getFriday(){
		 return this.friday;
	}
	public void setSaturday( String  _saturday){
		this.saturday = _saturday;
	}
	@Length(max=250)
	@Column (name="saturday")
	public String getSaturday(){
		 return this.saturday;
	}
	public void setSunday( String  _sunday){
		this.sunday = _sunday;
	}
	@Length(max=250)
	@Column (name="sunday")
	public String getSunday(){
		 return this.sunday;
	}
	@Column(name = "associateEntityId")
	public Long getAssociateEntityId() {
		return associateEntityId;
	}

	public void setAssociateEntityId(Long associateEntityId) {
		this.associateEntityId = associateEntityId;
	}

	@Column(name = "associateEntityType")
	public String getAssociateEntityType() {
		return associateEntityType;
	}

	public void setAssociateEntityType(String associateEntityType) {
		this.associateEntityType = associateEntityType;
	}
	@Column(name = "associateEntityName")
	public String getAssociateEntityName() {
		return associateEntityName;
	}
	public void setAssociateEntityName(String associateEntityName) {
		this.associateEntityName = associateEntityName;
	}
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseBaseschedule other = (BaseBaseschedule)o;
		if(
			ObjectUtil.isEqual(getDoctorId(), other.getDoctorId()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getNumberofmonths(), other.getNumberofmonths()) && 
			ObjectUtil.isEqual(getMinSlot(), other.getMinSlot()) && 
			ObjectUtil.isEqual(getMonday(), other.getMonday()) && 
			ObjectUtil.isEqual(getTuesday(), other.getTuesday()) && 
			ObjectUtil.isEqual(getWednesday(), other.getWednesday()) && 
			ObjectUtil.isEqual(getThursday(), other.getThursday()) && 
			ObjectUtil.isEqual(getFriday(), other.getFriday()) && 
			ObjectUtil.isEqual(getSaturday(), other.getSaturday()) && 
			ObjectUtil.isEqual(getSunday(), other.getSunday()) && 
			 ObjectUtil.isEqual(getAssociateEntityId(), other.getAssociateEntityId())&&
			 ObjectUtil.isEqual(getAssociateEntityType(), other.getAssociateEntityType()) &&
			 ObjectUtil.isEqual(getAssociateEntityName(), other.getAssociateEntityName()) &&
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
		result = result + (basseScheduleId!= null ? basseScheduleId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.basseScheduleId == null){
			 list.add(new ValidationMessage("Field " + FIELD_basseScheduleId+  " cannot be null"));
		}

		}
		if(this.doctorId == null){
			 list.add(new ValidationMessage("Field " + FIELD_doctorId+  " cannot be null"));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if(this.numberofmonths == null){
			 list.add(new ValidationMessage("Field " + FIELD_numberofmonths+  " cannot be null"));
		}

		if(this.minSlot == null){
			 list.add(new ValidationMessage("Field " + FIELD_minSlot+  " cannot be null"));
		}

		if((this.monday != null) && (this.monday.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_monday+  " cannot be longer than: " + 250));
		}

		if((this.tuesday != null) && (this.tuesday.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_tuesday+  " cannot be longer than: " + 250));
		}

		if((this.wednesday != null) && (this.wednesday.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_wednesday+  " cannot be longer than: " + 250));
		}

		if((this.thursday != null) && (this.thursday.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_thursday+  " cannot be longer than: " + 250));
		}

		if((this.friday != null) && (this.friday.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_friday+  " cannot be longer than: " + 250));
		}

		if((this.saturday != null) && (this.saturday.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_saturday+  " cannot be longer than: " + 250));
		}

		if((this.sunday != null) && (this.sunday.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_sunday+  " cannot be longer than: " + 250));
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
		str.append("basseScheduleId = " + basseScheduleId + "\n");
;
		str.append("doctorId = " + doctorId + "\n");
		str.append("active = " + active + "\n");
		str.append("numberofmonths = " + numberofmonths + "\n");
		str.append("minSlot = " + minSlot + "\n");
		str.append("monday = " + monday + "\n");
		str.append("tuesday = " + tuesday + "\n");
		str.append("wednesday = " + wednesday + "\n");
		str.append("thursday = " + thursday + "\n");
		str.append("friday = " + friday + "\n");
		str.append("saturday = " + saturday + "\n");
		str.append("sunday = " + sunday + "\n");
		str.append("associateEntityType = " + associateEntityType + "\n");
		str.append("associateEntityId = " + associateEntityId + "\n");
		str.append("associateEntityName = " + associateEntityName + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (basseScheduleId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_basseScheduleId, getBasseScheduleId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getBasseScheduleId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Baseschedule baseschedule = new Baseschedule();
		baseschedule.setFromDB(false);
		baseschedule.setDoctorId(getDoctorId());
		baseschedule.setActive(getActive());
		baseschedule.setNumberofmonths(getNumberofmonths());
		baseschedule.setMinSlot(getMinSlot());
		baseschedule.setMonday(getMonday());
		baseschedule.setTuesday(getTuesday());
		baseschedule.setWednesday(getWednesday());
		baseschedule.setThursday(getThursday());
		baseschedule.setFriday(getFriday());
		baseschedule.setSaturday(getSaturday());
		baseschedule.setSunday(getSunday());
		baseschedule.setAssociateEntityId(getAssociateEntityId());
		baseschedule.setAssociateEntityType(getAssociateEntityType());
		baseschedule.setAssociateEntityName(getAssociateEntityName());
		baseschedule.setCreatedDate(getCreatedDate());
		//afterClone(baseschedule);
		return baseschedule;
	}
}