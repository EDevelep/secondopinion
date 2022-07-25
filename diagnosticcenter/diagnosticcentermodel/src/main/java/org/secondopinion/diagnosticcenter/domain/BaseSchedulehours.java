package org.secondopinion.diagnosticcenter.domain; 


import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.diagnosticcenter.dto.Schedulehours; 
import org.secondopinion.domain.BaseDomainObject;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage; 
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
@MappedSuperclass 
public abstract class BaseSchedulehours extends BaseDomainObject<Long> {

	
	public static final String FIELD_scheduleHoursId = "scheduleHoursId";
	public static final String FIELD_scheduleId = "scheduleId";
	public static final String FIELD_toTime = "toTime";
	public static final String FIELD_fromTime = "fromTime";
	public static final String FIELD_scheduleStatus = "scheduleStatus";
	public static final String FIELD_active = "active";
	public static final String FIELD_price = "price";
	public static final String FIELD_discount = "discount";

	private Long scheduleHoursId;
	private Long scheduleId;
	@JsonFormat(pattern="HH:mm:ss", shape=Shape.STRING)
	private Date toTime;
	@JsonFormat(pattern="HH:mm:ss", shape=Shape.STRING)
	private Date fromTime;
	private String scheduleStatus;
	private Character active;
	private Double price;
	private Integer discount;
	
	public void setScheduleHoursId( Long  _scheduleHoursId){
		this.scheduleHoursId = _scheduleHoursId;
	}
	@Id
 	@Column(name = "scheduleHoursId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getScheduleHoursId(){
		 return this.scheduleHoursId;
	}
	public void setScheduleId( Long  _scheduleId){
		this.scheduleId = _scheduleId;
	}
	@NotNull 
	@Column (name="scheduleId")
	public Long getScheduleId(){
		 return this.scheduleId;
	}
	public void setToTime( Date  _toTime){
		this.toTime = _toTime;
	}
	@JsonFormat(pattern="HH:mm:ss", shape=Shape.STRING)
	@NotNull 
	@Temporal(TemporalType.TIME)
	@Column (name="toTime")
	public Date getToTime(){
		 return this.toTime;
	}
	public void setFromTime( Date  _fromTime){
		this.fromTime = _fromTime;
	}
	@JsonFormat(pattern="HH:mm:ss", shape=Shape.STRING)
	@NotNull 
	@Temporal(TemporalType.TIME)
	@Column (name="fromTime")
	public Date getFromTime(){
		 return this.fromTime;
	}
	public void setScheduleStatus( String  _scheduleStatus){
		this.scheduleStatus = _scheduleStatus;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="scheduleStatus")
	public String getScheduleStatus(){
		 return this.scheduleStatus;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setPrice( Double  _price){
		this.price = _price;
	}
	@Column (name="price")
	public Double getPrice(){
		 return this.price;
	}
	public void setDiscount( Integer  _discount){
		this.discount = _discount;
	}
	@Column (name="discount")
	public Integer getDiscount(){
		 return this.discount;
	}
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseSchedulehours other = (BaseSchedulehours)o;
		if(
			ObjectUtil.isEqual(getScheduleId(), other.getScheduleId()) && 
			ObjectUtil.isEqual(getToTime(), other.getToTime()) && 
			ObjectUtil.isEqual(getFromTime(), other.getFromTime()) && 
			ObjectUtil.isEqual(getScheduleStatus(), other.getScheduleStatus()) && 
			ObjectUtil.isEqual(getPrice(), other.getPrice()) && 
			ObjectUtil.isEqual(getDiscount(), other.getDiscount()) && 
			ObjectUtil.isEqual(getActive(), other.getActive())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (scheduleHoursId!= null ? scheduleHoursId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.scheduleHoursId == null){
			 list.add(new ValidationMessage("Field " + FIELD_scheduleHoursId+  " cannot be null"));
		}

		}
		if(this.scheduleId == null){
			 list.add(new ValidationMessage("Field " + FIELD_scheduleId+  " cannot be null"));
		}

		if(this.toTime == null){
			 list.add(new ValidationMessage("Field " + FIELD_toTime+  " cannot be null"));
		}

		if(this.fromTime == null){
			 list.add(new ValidationMessage("Field " + FIELD_fromTime+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.scheduleStatus)){
			 list.add(new ValidationMessage("Field " + FIELD_scheduleStatus+  " cannot be null"));
		}

		if((this.scheduleStatus != null) && (this.scheduleStatus.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_scheduleStatus+  " cannot be longer than: " + 45));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if(list.size()>0)setHasValidationErrors(true);

		setValidationMessages(list);
		
	}

	@Override
	public final void setAuditFields() {
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("scheduleHoursId = " + scheduleHoursId + "\n");
		str.append("scheduleId = " + scheduleId + "\n");
		str.append("toTime = " + toTime + "\n");
		str.append("fromTime = " + fromTime + "\n");
		str.append("scheduleStatus = " + scheduleStatus + "\n");
		str.append("price = " + price + "\n");
		str.append("discount = " + discount + "\n");
		str.append("active = " + active + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (scheduleHoursId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_scheduleHoursId, getScheduleHoursId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getScheduleHoursId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Schedulehours schedulehours = new Schedulehours();
		schedulehours.setFromDB(false);
		schedulehours.setScheduleId(getScheduleId());
		schedulehours.setToTime(getToTime());
		schedulehours.setFromTime(getFromTime());
		schedulehours.setScheduleStatus(getScheduleStatus());
		schedulehours.setActive(getActive());
		schedulehours.setPrice(getPrice());
		schedulehours.setDiscount(getDiscount());
		//afterClone(schedulehours);
		return schedulehours;
	}
}