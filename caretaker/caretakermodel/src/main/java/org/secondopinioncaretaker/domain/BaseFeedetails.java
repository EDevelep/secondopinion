package org.secondopinioncaretaker.domain; 

import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.caretaker.dto.Feedetails;
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
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseFeedetails extends BaseDomainObject<Long> {

	public static final String FIELD_feeDetailsId = "feeDetailsId";
	public static final String FIELD_caretakerId = "caretakerId";
	public static final String FIELD_active = "active";
	public static final String FIELD_fee = "fee";
	public static final String FIELD_type = "type";

	private Long feeDetailsId;
	private Long caretakerId;
	private Character active;
	private Double fee;
	private String type;

	public void setFeeDetailsId( Long  _feeDetailsId){
		this.feeDetailsId = _feeDetailsId;
	}
	@Id
 	@Column(name = "feeDetailsId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getFeeDetailsId(){
		 return this.feeDetailsId;
	}
	public void setcaretakerId( Long  _caretakerId){
		this.caretakerId = _caretakerId;
	}
	@NotNull 
	@Column (name="caretakerId")
	public Long getcaretakerId(){
		 return this.caretakerId;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setFee( Double  _fee){
		this.fee = _fee;
	}
	@Column (name="fee")
	public Double getFee(){
		 return this.fee;
	}
	public void setType( String  _type){
		this.type = _type;
	}
	@Length(max=45)
	@Column (name="type")
	public String getType(){
		 return this.type;
	}
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseFeedetails other = (BaseFeedetails)o;
		if(
			ObjectUtil.isEqual(getcaretakerId(), other.getcaretakerId()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getFee(), other.getFee()) && 
			ObjectUtil.isEqual(getType(), other.getType()) && 
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
		result = result + (feeDetailsId!= null ? feeDetailsId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.feeDetailsId == null){
			 list.add(new ValidationMessage("Field " + FIELD_feeDetailsId+  " cannot be null"));
		}

		}
		if(this.caretakerId == null){
			 list.add(new ValidationMessage("Field " + FIELD_caretakerId+  " cannot be null"));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if((this.type != null) && (this.type.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_type+  " cannot be longer than: " + 45));
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
		str.append("feeDetailsId = " + feeDetailsId + "\n");
;
		str.append("caretakerId = " + caretakerId + "\n");
		str.append("active = " + active + "\n");
		str.append("fee = " + fee + "\n");
		str.append("type = " + type + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (feeDetailsId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_feeDetailsId, getFeeDetailsId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getFeeDetailsId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Feedetails feedetails = new Feedetails();
		feedetails.setFromDB(false);
		feedetails.setcaretakerId(getcaretakerId());
		feedetails.setActive(getActive());
		feedetails.setFee(getFee());
		feedetails.setType(getType());
		feedetails.setCreatedDate(getCreatedDate());
		//afterClone(feedetails);
		return feedetails;
	}
}