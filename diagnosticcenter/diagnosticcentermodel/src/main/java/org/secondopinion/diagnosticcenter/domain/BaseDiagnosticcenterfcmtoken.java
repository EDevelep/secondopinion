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
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterfcmtoken;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseDiagnosticcenterfcmtoken extends BaseDomainObject<Long> {

	
	
	public static final String FIELD_diagnosticcenterfcmtokenId = "diagnosticcenterfcmtokenId";
	public static final String FIELD_diagnosticCenterUserId = "diagnosticCenterUserId";
	public static final String FIELD_diagnosticCenterAddressId = "diagnosticCenterAddressId";
	public static final String FIELD_androidtoken = "androidtoken";
	public static final String FIELD_browsertoken = "browsertoken";
	public static final String FIELD_iphonetoken = "iphonetoken";
	public static final String FIELD_active = "active";
	
	private Long diagnosticcenterfcmtokenId;
	private Long diagnosticCenterUserId;
	private Long diagnosticCenterAddressId;
	private String androidtoken;
	private String browsertoken;
	private String iphonetoken;
	private Character active;

	public void setDiagnosticcenterfcmtokenId( Long  _diagnosticcenterfcmtokenId){
		this.diagnosticcenterfcmtokenId = _diagnosticcenterfcmtokenId;
	}
	@Id
 	@Column(name = "diagnosticcenterfcmtokenId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDiagnosticcenterfcmtokenId(){
		 return this.diagnosticcenterfcmtokenId;
	}
	public void setDiagnosticCenterUserId( Long  _diagnosticCenterUserId){
		this.diagnosticCenterUserId = _diagnosticCenterUserId;
	}
	
	@Column (name="diagnosticCenterUserId")
	public Long getDiagnosticCenterUserId(){
		 return this.diagnosticCenterUserId;
	}
	public void setDiagnosticCenterAddressId( Long  _diagnosticCenterAddressId){
		this.diagnosticCenterAddressId = _diagnosticCenterAddressId;
	}

	@Column (name="diagnosticCenterAddressId")
	public Long getDiagnosticCenterAddressId(){
		 return this.diagnosticCenterAddressId;
	}
	public void setAndroidtoken( String  _androidtoken){
		this.androidtoken = _androidtoken;
	}
	@Length(max=245)
	@Column (name="androidtoken")
	public String getAndroidtoken(){
		 return this.androidtoken;
	}
	public void setBrowsertoken( String  _browsertoken){
		this.browsertoken = _browsertoken;
	}
	@Length(max=245)
	@Column (name="browsertoken")
	public String getBrowsertoken(){
		 return this.browsertoken;
	}
	public void setIphonetoken( String  _iphonetoken){
		this.iphonetoken = _iphonetoken;
	}
	@Length(max=45)
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
		BaseDiagnosticcenterfcmtoken other = (BaseDiagnosticcenterfcmtoken)o;
		if(
			ObjectUtil.isEqual(getDiagnosticCenterUserId(), other.getDiagnosticCenterUserId()) && 
			ObjectUtil.isEqual(getDiagnosticCenterAddressId(), other.getDiagnosticCenterAddressId()) && 
			ObjectUtil.isEqual(getAndroidtoken(), other.getAndroidtoken()) && 
			ObjectUtil.isEqual(getBrowsertoken(), other.getBrowsertoken()) && 
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
		result = result + (diagnosticcenterfcmtokenId!= null ? diagnosticcenterfcmtokenId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.diagnosticcenterfcmtokenId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticcenterfcmtokenId+  " cannot be null"));
		}

		}

		if(this.diagnosticCenterUserId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticCenterUserId+  " cannot be null"));
		}
		
		if(this.diagnosticCenterAddressId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticCenterAddressId+  " cannot be null"));
		}
		
		if((this.androidtoken != null) && (this.androidtoken.length()>245)){
			 list.add(new ValidationMessage("Field " + FIELD_androidtoken+  " cannot be longer than: " + 245));
		}

		if((this.browsertoken != null) && (this.browsertoken.length()>245)){
			 list.add(new ValidationMessage("Field " + FIELD_browsertoken+  " cannot be longer than: " + 245));
		}

		if((this.iphonetoken != null) && (this.iphonetoken.length()>245)){
			 list.add(new ValidationMessage("Field " + FIELD_iphonetoken+  " cannot be longer than: " + 245));
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
		str.append("diagnosticcenterfcmtokenId = " + diagnosticcenterfcmtokenId + "\n");
;
		str.append("diagnosticCenterUserId = " + diagnosticCenterUserId + "\n");
		str.append("diagnosticCenterAddressId = " + diagnosticCenterAddressId + "\n");
		str.append("androidtoken = " + androidtoken + "\n");
		str.append("browsertoken = " + browsertoken + "\n");
		str.append("iphonetoken = " + iphonetoken + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (diagnosticcenterfcmtokenId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_diagnosticcenterfcmtokenId, getDiagnosticcenterfcmtokenId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getDiagnosticcenterfcmtokenId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Diagnosticcenterfcmtoken diagnosticcenterfcmtoken = new Diagnosticcenterfcmtoken();
		diagnosticcenterfcmtoken.setFromDB(false);
		diagnosticcenterfcmtoken.setDiagnosticCenterUserId(getDiagnosticCenterUserId());
		diagnosticcenterfcmtoken.setDiagnosticCenterAddressId(getDiagnosticCenterAddressId());
		diagnosticcenterfcmtoken.setAndroidtoken(getAndroidtoken());
		diagnosticcenterfcmtoken.setBrowsertoken(getBrowsertoken());
		diagnosticcenterfcmtoken.setIphonetoken(getIphonetoken());
		diagnosticcenterfcmtoken.setActive(getActive());
		//afterClone(diagnosticcenterfcmtoken);
		return diagnosticcenterfcmtoken;
	}
}