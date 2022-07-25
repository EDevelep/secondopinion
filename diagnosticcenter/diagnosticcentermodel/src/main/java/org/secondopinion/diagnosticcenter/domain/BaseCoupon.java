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
import org.secondopinion.diagnosticcenter.dto.Coupon;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseCoupon extends BaseDomainObject<Long> {

	
	public static final String FIELD_couponId = "couponId";
	public static final String FIELD_subMenuId = "subMenuId";
	public static final String FIELD_code = "code";
	public static final String FIELD_couponTitle = "couponTitle";
	public static final String FIELD_description = "description";
	public static final String FIELD_diagnosticcenterId = "diagnosticcenterId";
	public static final String FIELD_dicountPercentage = "dicountPercentage";
	public static final String FIELD_discountAmount = "discountAmount";
	public static final String FIELD_expiresOn = "expiresOn";
	public static final String FIELD_validFrom = "validFrom";

	private Long couponId;
	private Long subMenuId;
	private String code;
	private String couponTitle;
	private String description;
	private Long diagnosticcenterId;
	private Double dicountPercentage;
	private Double discountAmount;
	private Date expiresOn;
	private Date validFrom;

	public void setCouponId( Long  _couponId){
		this.couponId = _couponId;
	}
	@Id
 	@Column(name = "couponId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCouponId(){
		 return this.couponId;
	}
	public void setSubMenuId( Long  _subMenuId){
		this.subMenuId = _subMenuId;
	}
	@Column (name="subMenuId")
	public Long getSubMenuId(){
		 return this.subMenuId;
	}
	public void setCode( String  _code){
		this.code = _code;
	}
	@Length(max=40)
	@Column (name="code")
	public String getCode(){
		 return this.code;
	}
	public void setCouponTitle( String  _couponTitle){
		this.couponTitle = _couponTitle;
	}
	@NotNull 
	@Length(max=600)
	@Column (name="couponTitle")
	public String getCouponTitle(){
		 return this.couponTitle;
	}
	public void setDescription( String  _description){
		this.description = _description;
	}
	@Length(max=2000)
	@Column (name="description")
	public String getDescription(){
		 return this.description;
	}
	public void setDiagnosticcenterId( Long  _diagnosticcenterId){
		this.diagnosticcenterId = _diagnosticcenterId;
	}
	@NotNull 
	@Column (name="diagnosticcenterId")
	public Long getDiagnosticcenterId(){
		 return this.diagnosticcenterId;
	}
	public void setDicountPercentage( Double  _dicountPercentage){
		this.dicountPercentage = _dicountPercentage;
	}
	@Column (name="dicountPercentage")
	public Double getDicountPercentage(){
		 return this.dicountPercentage;
	}
	public void setDiscountAmount( Double  _discountAmount){
		this.discountAmount = _discountAmount;
	}
	@Column (name="discountAmount")
	public Double getDiscountAmount(){
		 return this.discountAmount;
	}
	public void setExpiresOn( Date  _expiresOn){
		this.expiresOn = _expiresOn;
	}
	@Column (name="expiresOn")
	public Date getExpiresOn(){
		 return this.expiresOn;
	}
	public void setValidFrom( Date  _validFrom){
		this.validFrom = _validFrom;
	}
	@Column (name="validFrom")
	public Date getValidFrom(){
		 return this.validFrom;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseCoupon other = (BaseCoupon)o;
		if(
			ObjectUtil.isEqual(getSubMenuId(), other.getSubMenuId()) && 
			ObjectUtil.isEqual(getCode(), other.getCode()) && 
			ObjectUtil.isEqual(getCouponTitle(), other.getCouponTitle()) && 
			ObjectUtil.isEqual(getDescription(), other.getDescription()) && 
			ObjectUtil.isEqual(getDiagnosticcenterId(), other.getDiagnosticcenterId()) && 
			ObjectUtil.isEqual(getDicountPercentage(), other.getDicountPercentage()) && 
			ObjectUtil.isEqual(getDiscountAmount(), other.getDiscountAmount()) && 
			ObjectUtil.isEqual(getExpiresOn(), other.getExpiresOn()) && 
			ObjectUtil.isEqual(getValidFrom(), other.getValidFrom()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (couponId!= null ? couponId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.couponId == null){
			 list.add(new ValidationMessage("Field " + FIELD_couponId+  " cannot be null"));
		}

		}
		if((this.code != null) && (this.code.length()>40)){
			 list.add(new ValidationMessage("Field " + FIELD_code+  " cannot be longer than: " + 40));
		}

		if( StringUtil.isNullOrEmpty(this.couponTitle)){
			 list.add(new ValidationMessage("Field " + FIELD_couponTitle+  " cannot be null"));
		}

		if((this.couponTitle != null) && (this.couponTitle.length()>600)){
			 list.add(new ValidationMessage("Field " + FIELD_couponTitle+  " cannot be longer than: " + 600));
		}

		if((this.description != null) && (this.description.length()>2000)){
			 list.add(new ValidationMessage("Field " + FIELD_description+  " cannot be longer than: " + 2000));
		}

		if(this.diagnosticcenterId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticcenterId+  " cannot be null"));
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
		str.append("couponId = " + couponId + "\n");
;
		str.append("subMenuId = " + subMenuId + "\n");
		str.append("code = " + code + "\n");
		str.append("couponTitle = " + couponTitle + "\n");
		str.append("description = " + description + "\n");
		str.append("diagnosticcenterId = " + diagnosticcenterId + "\n");
		str.append("dicountPercentage = " + dicountPercentage + "\n");
		str.append("discountAmount = " + discountAmount + "\n");
		str.append("expiresOn = " + expiresOn + "\n");
		str.append("validFrom = " + validFrom + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (couponId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_couponId, getCouponId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getCouponId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Coupon coupon = new Coupon();
		coupon.setFromDB(false);
		coupon.setSubMenuId(getSubMenuId());
		coupon.setCode(getCode());
		coupon.setCouponTitle(getCouponTitle());
		coupon.setDescription(getDescription());
		coupon.setDiagnosticcenterId(getDiagnosticcenterId());
		coupon.setDicountPercentage(getDicountPercentage());
		coupon.setDiscountAmount(getDiscountAmount());
		coupon.setExpiresOn(getExpiresOn());
		coupon.setValidFrom(getValidFrom());
		//afterClone(coupon);
		return coupon;
	}
}