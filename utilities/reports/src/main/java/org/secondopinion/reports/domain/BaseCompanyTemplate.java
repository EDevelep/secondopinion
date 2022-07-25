package org.secondopinion.reports.domain; 

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
import org.secondopinion.reports.dto.CompanyTemplate;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseCompanyTemplate extends BaseDomainObject<Long> {

	public static final String FIELD_companyTemplateId = "companyTemplateId";
	public static final String FIELD_companyId = "companyId";
	public static final String FIELD_templateType = "templateType";
	public static final String FIELD_templateName = "templateName";
	public static final String FIELD_documentName = "documentName";
	public static final String FIELD_documentLocation = "documentLocation";
	public static final String FIELD_defaultTemplate = "defaultTemplate";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdTs = "createdTs";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long companyTemplateId;
	private Integer companyId;
	private String templateType;
	private String templateName;
	private String documentName;
	private String documentLocation;
	private Character defaultTemplate;
	private String createdBy;
	private Date createdTs;

	public void setCompanyTemplateId( Long  _companyTemplateId){
		this.companyTemplateId = _companyTemplateId;
	}
	@Id
// 	@NotNull
	@Column(name = "companyTemplateId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCompanyTemplateId(){
		 return this.companyTemplateId;
	}
	public void setCompanyId( Integer  _companyId){
		this.companyId = _companyId;
	}
	@Column (name="companyId")
	public Integer getCompanyId(){
		 return this.companyId;
	}
	public void setTemplateType( String  _templateType){
		this.templateType = _templateType;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="templateType")
	public String getTemplateType(){
		 return this.templateType;
	}
	public void setTemplateName( String  _templateName){
		this.templateName = _templateName;
	}
	@NotNull 
	@Length(max=150)
	@Column (name="templateName")
	public String getTemplateName(){
		 return this.templateName;
	}
	public void setDocumentName( String  _documentName){
		this.documentName = _documentName;
	}
	@NotNull 
	@Length(max=150)
	@Column (name="documentName")
	public String getDocumentName(){
		 return this.documentName;
	}
	public void setDocumentLocation( String  _documentLocation){
		this.documentLocation = _documentLocation;
	}
	@NotNull 
	@Length(max=250)
	@Column (name="documentLocation")
	public String getDocumentLocation(){
		 return this.documentLocation;
	}
	public void setDefaultTemplate( Character  _defaultTemplate){
		this.defaultTemplate = _defaultTemplate;
	}
	@Column (name="defaultTemplate")
	public Character getDefaultTemplate(){
		 return this.defaultTemplate;
	}
	public void setCreatedBy( String  _createdBy){
		this.createdBy = _createdBy;
	}
	@Length(max=45)
	@Column (name="createdBy")
	public String getCreatedBy(){
		 return this.createdBy;
	}
	public void setCreatedTs( Date  _createdTs){
		this.createdTs = _createdTs;
	}
	@Column (name="createdTs")
	public Date getCreatedTs(){
		 return this.createdTs;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseCompanyTemplate other = (BaseCompanyTemplate)o;
		if(
			ObjectUtil.isEqual(getCompanyId(), other.getCompanyId()) && 
			ObjectUtil.isEqual(getTemplateType(), other.getTemplateType()) && 
			ObjectUtil.isEqual(getTemplateName(), other.getTemplateName()) && 
			ObjectUtil.isEqual(getDocumentName(), other.getDocumentName()) && 
			ObjectUtil.isEqual(getDocumentLocation(), other.getDocumentLocation()) && 
			ObjectUtil.isEqual(getDefaultTemplate(), other.getDefaultTemplate()) && 
			ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy()) && 
			ObjectUtil.isEqual(getCreatedTs(), other.getCreatedTs()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (companyTemplateId!= null ? companyTemplateId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.companyTemplateId == null){
			 list.add(new ValidationMessage("Field " + FIELD_companyTemplateId+  " cannot be null"));
		}

		}
		if( StringUtil.isNullOrEmpty(this.templateType)){
			 list.add(new ValidationMessage("Field " + FIELD_templateType+  " cannot be null"));
		}

		if((this.templateType != null) && (this.templateType.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_templateType+  " cannot be longer than: " + 45));
		}

		if( StringUtil.isNullOrEmpty(this.templateName)){
			 list.add(new ValidationMessage("Field " + FIELD_templateName+  " cannot be null"));
		}

		if((this.templateName != null) && (this.templateName.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_templateName+  " cannot be longer than: " + 150));
		}

		if( StringUtil.isNullOrEmpty(this.documentName)){
			 list.add(new ValidationMessage("Field " + FIELD_documentName+  " cannot be null"));
		}

		if((this.documentName != null) && (this.documentName.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_documentName+  " cannot be longer than: " + 150));
		}

		if( StringUtil.isNullOrEmpty(this.documentLocation)){
			 list.add(new ValidationMessage("Field " + FIELD_documentLocation+  " cannot be null"));
		}

		if((this.documentLocation != null) && (this.documentLocation.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_documentLocation+  " cannot be longer than: " + 250));
		}

	if(this.isFromDB()){
		if((this.createdBy != null) && (this.createdBy.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 45));
		}
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
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("companyTemplateId = " + companyTemplateId + "\n");
;
		str.append("companyId = " + companyId + "\n");
		str.append("templateType = " + templateType + "\n");
		str.append("templateName = " + templateName + "\n");
		str.append("documentName = " + documentName + "\n");
		str.append("documentLocation = " + documentLocation + "\n");
		str.append("defaultTemplate = " + defaultTemplate + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdTs = " + createdTs + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (companyTemplateId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_companyTemplateId, getCompanyTemplateId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getCompanyTemplateId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		CompanyTemplate companyTemplate = new CompanyTemplate();
		companyTemplate.setFromDB(false);
		companyTemplate.setCompanyId(getCompanyId());
		companyTemplate.setTemplateType(getTemplateType());
		companyTemplate.setTemplateName(getTemplateName());
		companyTemplate.setDocumentName(getDocumentName());
		companyTemplate.setDocumentLocation(getDocumentLocation());
		companyTemplate.setDefaultTemplate(getDefaultTemplate());
		//afterClone(companyTemplate);
		return companyTemplate;
	}
}