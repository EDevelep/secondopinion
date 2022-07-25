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
import org.secondopinion.userMgmt.dto.CompanyConfiguration;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseCompanyConfiguration extends BaseDomainObject<Integer> {

	public static final String FIELD_companyConfigurationId = "companyConfigurationId";
	public static final String FIELD_companyId = "companyId";
	public static final String FIELD_configProperty = "configProperty";
	public static final String FIELD_propertyValue = "propertyValue";
	public static final String FIELD_propertyType = "propertyType";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Integer companyConfigurationId;
	private Integer companyId;
	private String configProperty;
	private String propertyValue;
	private String propertyType;

	public void setCompanyConfigurationId( Integer  _companyConfigurationId){
		this.companyConfigurationId = _companyConfigurationId;
	}
	@Id
// 	@NotNull
	@Column(name = "companyConfigurationId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCompanyConfigurationId(){
		 return this.companyConfigurationId;
	}
	public void setCompanyId( Integer  _companyId){
		this.companyId = _companyId;
	}
	@NotNull 
	@Column (name="companyId")
	public Integer getCompanyId(){
		 return this.companyId;
	}
	public void setConfigProperty( String  _configProperty){
		this.configProperty = _configProperty;
	}
	@NotNull 
	@Length(max=100)
	@Column (name="configProperty")
	public String getConfigProperty(){
		 return this.configProperty;
	}
	public void setPropertyValue( String  _propertyValue){
		this.propertyValue = _propertyValue;
	}
	@NotNull 
	@Length(max=100)
	@Column (name="propertyValue")
	public String getPropertyValue(){
		 return this.propertyValue;
	}
	public void setPropertyType( String  _propertyType){
		this.propertyType = _propertyType;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="propertyType")
	public String getPropertyType(){
		 return this.propertyType;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseCompanyConfiguration other = (BaseCompanyConfiguration)o;
		if(
			ObjectUtil.isEqual(getCompanyId(), other.getCompanyId()) && 
			ObjectUtil.isEqual(getConfigProperty(), other.getConfigProperty()) && 
			ObjectUtil.isEqual(getPropertyValue(), other.getPropertyValue()) && 
			ObjectUtil.isEqual(getPropertyType(), other.getPropertyType()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (companyConfigurationId!= null ? companyConfigurationId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.companyConfigurationId == null){
			 list.add(new ValidationMessage("Field " + FIELD_companyConfigurationId+  " cannot be null"));
		}

		}
		if(this.companyId == null){
			 list.add(new ValidationMessage("Field " + FIELD_companyId+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.configProperty)){
			 list.add(new ValidationMessage("Field " + FIELD_configProperty+  " cannot be null"));
		}

		if((this.configProperty != null) && (this.configProperty.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_configProperty+  " cannot be longer than: " + 100));
		}

		if( StringUtil.isNullOrEmpty(this.propertyValue)){
			 list.add(new ValidationMessage("Field " + FIELD_propertyValue+  " cannot be null"));
		}

		if((this.propertyValue != null) && (this.propertyValue.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_propertyValue+  " cannot be longer than: " + 100));
		}

		if( StringUtil.isNullOrEmpty(this.propertyType)){
			 list.add(new ValidationMessage("Field " + FIELD_propertyType+  " cannot be null"));
		}

		if((this.propertyType != null) && (this.propertyType.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_propertyType+  " cannot be longer than: " + 45));
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
		str.append("companyConfigurationId = " + companyConfigurationId + "\n");
;
		str.append("companyId = " + companyId + "\n");
		str.append("configProperty = " + configProperty + "\n");
		str.append("propertyValue = " + propertyValue + "\n");
		str.append("propertyType = " + propertyType + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (companyConfigurationId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_companyConfigurationId, getCompanyConfigurationId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getCompanyConfigurationId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		CompanyConfiguration companyConfiguration = new CompanyConfiguration();
		companyConfiguration.setFromDB(false);
		companyConfiguration.setCompanyId(getCompanyId());
		companyConfiguration.setConfigProperty(getConfigProperty());
		companyConfiguration.setPropertyValue(getPropertyValue());
		companyConfiguration.setPropertyType(getPropertyType());
		//afterClone(companyConfiguration);
		return companyConfiguration;
	}
}