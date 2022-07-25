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
import org.secondopinion.pharmacy.dto.Pharmacyfcmtoken;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 

@MappedSuperclass 
public abstract class BasePharmacyfcmtoken extends BaseDomainObject<Long> {

	public static final String FIELD_pharmacyFCMtokenId = "pharmacyFCMtokenId";
	public static final String FIELD_pharmacyaddressId= "pharmacyaddressId";
	public static final String FIELD_pharmacyuserId = "pharmacyuserId";
	public static final String FIELD_browsertoken = "browsertoken";
	public static final String FIELD_androidtoken = "androidtoken";
	public static final String FIELD_iphonetoken = "iphonetoken";
	public static final String FIELD_active = "active";
	
	private Long pharmacyFCMtokenId;
	private Long pharmacyaddressId;
	private Long pharmacyuserId;
	private String browsertoken;
	private String androidtoken;
	private String iphonetoken;
	private Character active;
	
	public void setPharmacyFCMtokenId( Long  _pharmacyFCMtokenId){
		this.pharmacyFCMtokenId = _pharmacyFCMtokenId;
	}
	@Id
 	@Column(name = "pharmacyFCMtokenId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPharmacyFCMtokenId(){
		 return this.pharmacyFCMtokenId;
	}
	public void setPharmacyaddressId( Long  _pharmacyaddressId){
		this.pharmacyaddressId = _pharmacyaddressId;
	}
	@NotNull
	@Column (name="pharmacyaddressId")
	public Long getPharmacyaddressId(){
		 return this.pharmacyaddressId;
	}
	public void setPharmacyuserId( Long  _pharmacyuserId){
		this.pharmacyuserId = _pharmacyuserId;
	}
	@NotNull
	@Column (name="pharmacyuserId")
	public Long getPharmacyuserId(){
		 return this.pharmacyuserId;
	}
	public void setBrowsertoken( String  _browsertoken){
		this.browsertoken = _browsertoken;
	}
	@Length(max=1020)
	@Column (name="browsertoken")
	public String getBrowsertoken(){
		 return this.browsertoken;
	}
	public void setAndroidtoken( String  _androidtoken){
		this.androidtoken = _androidtoken;
	}
	@Length(max=1020)
	@Column (name="androidtoken")
	public String getAndroidtoken(){
		 return this.androidtoken;
	}
	public void setIphonetoken( String  _iphonetoken){
		this.iphonetoken = _iphonetoken;
	}
	@Length(max=1020)
	@Column (name="iphonetoken")
	public String getIphonetoken(){
		 return this.iphonetoken;
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
		BasePharmacyfcmtoken other = (BasePharmacyfcmtoken)o;
		if(
			ObjectUtil.isEqual(getPharmacyaddressId(), other.getPharmacyaddressId()) && 
			ObjectUtil.isEqual(getPharmacyuserId(), other.getPharmacyuserId()) && 
			ObjectUtil.isEqual(getBrowsertoken(), other.getBrowsertoken()) && 
			ObjectUtil.isEqual(getAndroidtoken(), other.getAndroidtoken()) && 
			ObjectUtil.isEqual(getIphonetoken(), other.getIphonetoken()) && 
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
		result = result + (pharmacyFCMtokenId!= null ? pharmacyFCMtokenId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.pharmacyFCMtokenId == null){
			 list.add(new ValidationMessage("Field " + FIELD_pharmacyFCMtokenId+  " cannot be null"));
		}

		}
		if((this.pharmacyaddressId == null)){
			 list.add(new ValidationMessage("Field " + FIELD_pharmacyaddressId+  " cannot be null "));
		}
		if((this.pharmacyuserId == null)){
			 list.add(new ValidationMessage("Field " + FIELD_pharmacyuserId+  " cannot be null "));
		}
		if((this.browsertoken != null) && (this.browsertoken.length()>1020)){
			 list.add(new ValidationMessage("Field " + FIELD_browsertoken+  " cannot be longer than: " + 1020));
		}

		if((this.androidtoken != null) && (this.androidtoken.length()>1020)){
			 list.add(new ValidationMessage("Field " + FIELD_androidtoken+  " cannot be longer than: " + 1020));
		}

		if((this.iphonetoken != null) && (this.iphonetoken.length()>1020)){
			 list.add(new ValidationMessage("Field " + FIELD_iphonetoken+  " cannot be longer than: " + 1020));
		}
		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}
	if(this.isFromDB()){
		if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>1020)){
			 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 1020));
		}
	}
		if(list.size()>0)setHasValidationErrors(true);

		setValidationMessages(list);
		
	}

	@Override
	public final void setAuditFields() {
		this.lastUpdatedBy = AppThreadLocal.getUserName();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("pharmacyFCMtokenId = " + pharmacyFCMtokenId + "\n");
;
		str.append("pharmacyaddressId = " + pharmacyaddressId + "\n");
		str.append("pharmacyuserId = " + pharmacyuserId + "\n");
		str.append("browsertoken = " + browsertoken + "\n");
		str.append("androidtoken = " + androidtoken + "\n");
		str.append("iphonetoken = " + iphonetoken + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (pharmacyFCMtokenId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_pharmacyFCMtokenId, getPharmacyFCMtokenId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getPharmacyFCMtokenId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Pharmacyfcmtoken pharmacyfcmtoken = new Pharmacyfcmtoken();
		pharmacyfcmtoken.setFromDB(false);
		pharmacyfcmtoken.setPharmacyaddressId(getPharmacyaddressId());
		pharmacyfcmtoken.setPharmacyuserId(getPharmacyuserId());
		pharmacyfcmtoken.setBrowsertoken(getBrowsertoken());
		pharmacyfcmtoken.setAndroidtoken(getAndroidtoken());
		pharmacyfcmtoken.setIphonetoken(getIphonetoken());
		pharmacyfcmtoken.setActive(getActive());
		//afterClone(pharmacyfcmtoken);
		return pharmacyfcmtoken;
	}
}