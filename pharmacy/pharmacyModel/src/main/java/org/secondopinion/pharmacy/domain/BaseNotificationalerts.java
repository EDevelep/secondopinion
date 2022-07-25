package org.secondopinion.pharmacy.domain; 

import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 


import javax.persistence.Column; 
import javax.persistence.MappedSuperclass; 
import javax.validation.constraints.NotNull; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.pharmacy.dto.Notificationalerts;
import org.secondopinion.utils.ObjectUtil;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape; 
@MappedSuperclass 
public abstract class BaseNotificationalerts extends BaseDomainObject<Long> {

	public static final String FIELD_notificationalertsId = "notificationalertsId";
	public static final String FIELD_pharmacyaddressId = "pharmacyaddressId";
	public static final String FIELD_objectkey = "objectkey";
	public static final String FIELD_objecttype = "objecttype";
	public static final String FIELD_reason = "reason";
	public static final String FIELD_viewed = "viewed";
	public static final String FIELD_expirydate = "expirydate";
	public static final String FIELD_crearedby = "createdby";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_active = "active";
	

	private Long notificationalertsId;
	private Long pharmacyaddressId;
	private Long objectkey;
	private String objecttype;
	private String reason;
	private Date expirydate;
	private Character viewed;
	private Character active;

	public void setNotificationalertsId( Long  _notificationalertsId){
		this.notificationalertsId = _notificationalertsId;
	}
	
	@Id
 	@Column(name = "notificationalertsId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getNotificationalertsId(){
		 return this.notificationalertsId;
	}
	public void setPharmacyaddressId( Long  pharmacyaddressId){
		this.pharmacyaddressId = pharmacyaddressId;
	}
	@NotNull 
	@Column (name="pharmacyaddressId")
	public Long getPharmacyaddressId(){
		 return this.pharmacyaddressId;
	}
	public void setObjectkey( Long  objectkey){
		this.objectkey = objectkey;
	}
	@NotNull 
	@Column (name="objectkey")
	public Long getObjectkey(){
		 return this.objectkey;
	}
	public void setObjecttype( String  objecttype){
		this.objecttype = objecttype;
	}
	@Length(max=45)
	@Column (name="objecttype")
	public String getObjecttype(){
		 return this.objecttype;
	}
	public void setReason( String  _reason){
		this.reason = _reason;
	}
	
	@Column (name="reason", columnDefinition="text")
	public String getReason(){
		 return this.reason;
	}
	public void setViewed( Character  _viewed){
		this.viewed = _viewed;
	}
	@Column (name="viewed")
	public Character getViewed(){
		 return this.viewed;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="expirydate")
	public Date getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
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
		BaseNotificationalerts other = (BaseNotificationalerts)o;
		if(
			ObjectUtil.isEqual(getPharmacyaddressId(), other.getPharmacyaddressId()) && 
			ObjectUtil.isEqual(getObjectkey(), other.getObjectkey()) && 
			ObjectUtil.isEqual(getObjecttype(), other.getObjecttype()) && 
			ObjectUtil.isEqual(getReason(), other.getReason()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy()) && 
			ObjectUtil.isEqual(getCreatedDate(), other.getCreatedDate()) && 
			ObjectUtil.isEqual(getViewed(), other.getViewed()) ){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (notificationalertsId!= null ? notificationalertsId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.notificationalertsId == null){
			 list.add(new ValidationMessage("Field " + FIELD_notificationalertsId+  " cannot be null"));
		}

		}
		if(this.pharmacyaddressId == null){
			 list.add(new ValidationMessage("Field " + FIELD_pharmacyaddressId+  " cannot be null"));
		}

		if(this.objectkey == null){
			 list.add(new ValidationMessage("Field " + FIELD_objectkey+  " cannot be null"));
		}

		if((this.objecttype != null) && (this.objecttype.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_objecttype+  " cannot be longer than: " + 45));
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
		str.append("notificationalertsId = " + notificationalertsId + "\n");
;
		str.append("pharmacyaddressId = " + pharmacyaddressId + "\n");
		str.append("objectKey = " + objectkey + "\n");
		str.append("objecttype = " + objecttype + "\n");
		str.append("reason = " + reason + "\n");
		str.append("viewed = " + viewed + "\n");
		str.append("creartedby = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("active = " + active + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (notificationalertsId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_notificationalertsId, getNotificationalertsId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getNotificationalertsId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Notificationalerts notificationalerts = new Notificationalerts();
		notificationalerts.setFromDB(false);
		notificationalerts.setPharmacyaddressId(getPharmacyaddressId());
		notificationalerts.setObjectkey(getObjectkey());
		notificationalerts.setObjecttype(getObjecttype());
		notificationalerts.setReason(getReason());
		notificationalerts.setViewed(getViewed());
		notificationalerts.setCreatedBy(getCreatedBy());
		notificationalerts.setCreatedDate(getCreatedDate());
		notificationalerts.setActive(getActive());
		//afterClone(notificationalerts);
		return notificationalerts;
	}
}