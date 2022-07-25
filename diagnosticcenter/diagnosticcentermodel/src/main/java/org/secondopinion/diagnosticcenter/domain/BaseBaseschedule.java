package org.secondopinion.diagnosticcenter.domain; 

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
import org.secondopinion.diagnosticcenter.dto.Baseschedule;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseBaseschedule extends BaseDomainObject<Long> {

	
	public static final String FIELD_basseScheduleId = "basseScheduleId";
	public static final String FIELD_diagnosticCenterAddressId = "diagnosticCenterAddressId";
	public static final String FIELD_scheduleFor = "scheduleFor";
	public static final String FIELD_diagnosticcenterUserId = "diagnosticcenterUserId";
	public static final String FIELD_numberofmonths = "numberofmonths";
	public static final String FIELD_minSlot = "minSlot";
	public static final String FIELD_menuId = "menuId";
	public static final String FIELD_subMenuId = "subMenuId";
	public static final String FIELD_packageId = "packageId";
	public static final String FIELD_monday = "monday";
	public static final String FIELD_tuesday = "tuesday";
	public static final String FIELD_wednesday = "wednesday";
	public static final String FIELD_thursday = "thursday";
	public static final String FIELD_friday = "friday";
	public static final String FIELD_saturday = "saturday";
	public static final String FIELD_sunday = "sunday";
	public static final String FIELD_active = "active";
	
	private Long basseScheduleId;
	private Long diagnosticCenterAddressId;
	private String scheduleFor;//it should be enum values 4 user, submenu, package
	private Long diagnosticcenterUserId;
	private Integer numberofmonths;
	private Long minSlot;
	private Long menuId;//it can be remove
	private Long subMenuId;
	private Long packageId;
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String saturday;
	private String sunday;
	private Character active;
	
	public void setBasseScheduleId( Long  _basseScheduleId){
		this.basseScheduleId = _basseScheduleId;
	}
	@Id
 	@Column(name = "basseScheduleId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getBasseScheduleId(){
		 return this.basseScheduleId;
	}
	public void setDiagnosticCenterAddressId( Long  _diagnosticCenterAddressId){
		this.diagnosticCenterAddressId = _diagnosticCenterAddressId;
	}
	@NotNull 
	@Column (name="diagnosticCenterAddressId")
	public Long getDiagnosticCenterAddressId(){
		 return this.diagnosticCenterAddressId;
	}
	public void setScheduleFor( String  _scheduleFor){
		this.scheduleFor = _scheduleFor;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="scheduleFor")
	public String getScheduleFor(){
		 return this.scheduleFor;
	}
	public void setDiagnosticcenterUserId( Long  _diagnosticcenterUserId){
		this.diagnosticcenterUserId = _diagnosticcenterUserId;
	}
	@Column (name="diagnosticcenterUserId")
	public Long getDiagnosticcenterUserId(){
		 return this.diagnosticcenterUserId;
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
	
	public void setMenuId( Long  _menuId){
		this.menuId = _menuId;
	}
	@Column (name="menuId")
	public Long getMenuId(){
		 return this.menuId;
	}
	public void setSubMenuId( Long  _subMenuId){
		this.subMenuId = _subMenuId;
	}
	@Column (name="subMenuId")
	public Long getSubMenuId(){
		 return this.subMenuId;
	}
	public void setPackageId( Long  _packageId){
		this.packageId = _packageId;
	}
	@Column (name="packageId")
	public Long getPackageId(){
		 return this.packageId;
	}
	
	public void setMonday( String  _monday){
		this.monday = _monday;
	}
	@Length(max=1000)
	@Column (name="monday")
	public String getMonday(){
		 return this.monday;
	}
	public void setTuesday( String  _tuesday){
		this.tuesday = _tuesday;
	}
	@Length(max=1000)
	@Column (name="tuesday")
	public String getTuesday(){
		 return this.tuesday;
	}
	public void setWednesday( String  _wednesday){
		this.wednesday = _wednesday;
	}
	@Length(max=1000)
	@Column (name="wednesday")
	public String getWednesday(){
		 return this.wednesday;
	}
	public void setThursday( String  _thursday){
		this.thursday = _thursday;
	}
	@Length(max=1000)
	@Column (name="thursday")
	public String getThursday(){
		 return this.thursday;
	}
	public void setFriday( String  _friday){
		this.friday = _friday;
	}
	@Length(max=1000)
	@Column (name="friday")
	public String getFriday(){
		 return this.friday;
	}
	public void setSaturday( String  _saturday){
		this.saturday = _saturday;
	}
	@Length(max=1000)
	@Column (name="saturday")
	public String getSaturday(){
		 return this.saturday;
	}
	public void setSunday( String  _sunday){
		this.sunday = _sunday;
	}
	@Length(max=1000)
	@Column (name="sunday")
	public String getSunday(){
		 return this.sunday;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
		
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseBaseschedule other = (BaseBaseschedule)o;
		if(
			ObjectUtil.isEqual(getDiagnosticCenterAddressId(), other.getDiagnosticCenterAddressId()) && 
			ObjectUtil.isEqual(getScheduleFor(), other.getScheduleFor()) && 
			ObjectUtil.isEqual(getDiagnosticcenterUserId(), other.getDiagnosticcenterUserId()) && 
			ObjectUtil.isEqual(getNumberofmonths(), other.getNumberofmonths()) && 
			ObjectUtil.isEqual(getMinSlot(), other.getMinSlot()) && 
			ObjectUtil.isEqual(getMenuId(), other.getMenuId()) && 
			ObjectUtil.isEqual(getSubMenuId(), other.getSubMenuId()) && 
			ObjectUtil.isEqual(getPackageId(), other.getPackageId()) && 
			ObjectUtil.isEqual(getMonday(), other.getMonday()) && 
			ObjectUtil.isEqual(getTuesday(), other.getTuesday()) && 
			ObjectUtil.isEqual(getWednesday(), other.getWednesday()) && 
			ObjectUtil.isEqual(getThursday(), other.getThursday()) && 
			ObjectUtil.isEqual(getFriday(), other.getFriday()) && 
			ObjectUtil.isEqual(getSaturday(), other.getSaturday()) && 
			ObjectUtil.isEqual(getSunday(), other.getSunday()) && 
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
		if(this.diagnosticCenterAddressId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticCenterAddressId+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.scheduleFor)){
			 list.add(new ValidationMessage("Field " + FIELD_scheduleFor+  " cannot be null"));
		}

		if((this.scheduleFor != null) && (this.scheduleFor.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_scheduleFor+  " cannot be longer than: " + 45));
		}
		if(this.numberofmonths == null){
			 list.add(new ValidationMessage("Field " + FIELD_numberofmonths+  " cannot be null"));
		}

		if(this.minSlot == null){
			 list.add(new ValidationMessage("Field " + FIELD_minSlot+  " cannot be null"));
		}
		

		if((this.monday != null) && (this.monday.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_monday+  " cannot be longer than: " + 1000));
		}

		if((this.tuesday != null) && (this.tuesday.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_tuesday+  " cannot be longer than: " + 1000));
		}

		if((this.wednesday != null) && (this.wednesday.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_wednesday+  " cannot be longer than: " + 1000));
		}

		if((this.thursday != null) && (this.thursday.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_thursday+  " cannot be longer than: " + 1000));
		}

		if((this.friday != null) && (this.friday.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_friday+  " cannot be longer than: " + 1000));
		}

		if((this.saturday != null) && (this.saturday.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_saturday+  " cannot be longer than: " + 1000));
		}

		if((this.sunday != null) && (this.sunday.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_sunday+  " cannot be longer than: " + 1000));
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
		str.append("basseScheduleId = " + basseScheduleId + "\n");
;
		str.append("diagnosticCenterAddressId = " + diagnosticCenterAddressId + "\n");
		str.append("scheduleFor = " + scheduleFor + "\n");
		str.append("diagnosticcenterUserId = " + diagnosticcenterUserId + "\n");
		str.append("numberofmonths = " + numberofmonths + "\n");
		str.append("minSlot = " + minSlot + "\n");
		str.append("menuId = " + menuId + "\n");
		str.append("subMenuId = " + subMenuId + "\n");
		str.append("packageId = " + packageId + "\n");
		str.append("monday = " + monday + "\n");
		str.append("tuesday = " + tuesday + "\n");
		str.append("wednesday = " + wednesday + "\n");
		str.append("thursday = " + thursday + "\n");
		str.append("friday = " + friday + "\n");
		str.append("saturday = " + saturday + "\n");
		str.append("sunday = " + sunday + "\n");
		str.append("active = " + active + "\n");
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
		baseschedule.setDiagnosticCenterAddressId(getDiagnosticCenterAddressId());
		baseschedule.setScheduleFor(getScheduleFor());
		baseschedule.setDiagnosticcenterUserId(getDiagnosticcenterUserId());
		baseschedule.setNumberofmonths(getNumberofmonths());
		baseschedule.setMinSlot(getMinSlot());
		baseschedule.setMenuId(getMenuId());
		baseschedule.setSubMenuId(getSubMenuId());
		baseschedule.setPackageId(getPackageId());
		baseschedule.setMonday(getMonday());
		baseschedule.setTuesday(getTuesday());
		baseschedule.setWednesday(getWednesday());
		baseschedule.setThursday(getThursday());
		baseschedule.setFriday(getFriday());
		baseschedule.setSaturday(getSaturday());
		baseschedule.setSunday(getSunday());
		baseschedule.setActive(getActive());
		//afterClone(baseschedule);
		return baseschedule;
	}
}