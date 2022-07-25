package org.secondopinion.userMgmt.domain; 

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.secondopinion.userMgmt.dto.CompanyKey;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseCompanyKey extends BaseDomainObject<Long> {

	public static final String FIELD_companyKeyId = "companyKeyId";
	public static final String FIELD_companyId = "companyId";
	public static final String FIELD_type = "type";
	public static final String FIELD_keyPart = "keyPart";
	public static final String FIELD_iv = "iv";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long companyKeyId;
	private Integer companyId;
	private String type;
	private String keyPart;
	private String iv;

	public void setCompanyKeyId( Long  _companyKeyId){
		this.companyKeyId = _companyKeyId;
	}
	@Id
// 	@NotNull
	@Column(name = "companyKeyId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCompanyKeyId(){
		 return this.companyKeyId;
	}
	public void setCompanyId( Integer  _companyId){
		this.companyId = _companyId;
	}
	@NotNull 
	@Column (name="companyId")
	public Integer getCompanyId(){
		 return this.companyId;
	}
	public void setType( String  _type){
		this.type = _type;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="type")
	public String getType(){
		 return this.type;
	}
	public void setKeyPart( String  _keyPart){
		this.keyPart = _keyPart;
	}
	@NotNull 
	@Length(max=250)
	@Column (name="keyPart")
	public String getKeyPart(){
		 return this.keyPart;
	}
	public void setIv( String  _iv){
		this.iv = _iv;
	}
	@NotNull 
	@Length(max=250)
	@Column (name="iv")
	public String getIv(){
		 return this.iv;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseCompanyKey other = (BaseCompanyKey)o;
		if(
			ObjectUtil.isEqual(getCompanyId(), other.getCompanyId()) && 
			ObjectUtil.isEqual(getType(), other.getType()) && 
			ObjectUtil.isEqual(getKeyPart(), other.getKeyPart()) && 
			ObjectUtil.isEqual(getIv(), other.getIv()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (companyKeyId!= null ? companyKeyId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.companyKeyId == null){
			 list.add(new ValidationMessage("Field " + FIELD_companyKeyId+  " cannot be null"));
		}

		}
		if(this.companyId == null){
			 list.add(new ValidationMessage("Field " + FIELD_companyId+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.type)){
			 list.add(new ValidationMessage("Field " + FIELD_type+  " cannot be null"));
		}

		if((this.type != null) && (this.type.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_type+  " cannot be longer than: " + 45));
		}

		if( StringUtil.isNullOrEmpty(this.keyPart)){
			 list.add(new ValidationMessage("Field " + FIELD_keyPart+  " cannot be null"));
		}

		if((this.keyPart != null) && (this.keyPart.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_keyPart+  " cannot be longer than: " + 250));
		}

		if( StringUtil.isNullOrEmpty(this.iv)){
			 list.add(new ValidationMessage("Field " + FIELD_iv+  " cannot be null"));
		}

		if((this.iv != null) && (this.iv.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_iv+  " cannot be longer than: " + 250));
		}

		if(this.isFromDB()){
			if( StringUtil.isNullOrEmpty(this.lastUpdatedBy)){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be null"));
			}
		}
		if(this.isFromDB()){
			if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>45)){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 45));
			}
		}
		if(this.isFromDB()){
			if(this.lastUpdatedTs == null){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedTs+  " cannot be null"));
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
		str.append("companyKeyId = " + companyKeyId + "\n");
;
		str.append("companyId = " + companyId + "\n");
		str.append("type = " + type + "\n");
		str.append("keyPart = " + keyPart + "\n");
		str.append("iv = " + iv + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (companyKeyId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_companyKeyId, getCompanyKeyId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getCompanyKeyId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		CompanyKey companyKey = new CompanyKey();
		companyKey.setFromDB(false);
		companyKey.setCompanyId(getCompanyId());
		companyKey.setType(getType());
		companyKey.setKeyPart(getKeyPart());
		companyKey.setIv(getIv());
		//afterClone(companyKey);
		return companyKey;
	}
}