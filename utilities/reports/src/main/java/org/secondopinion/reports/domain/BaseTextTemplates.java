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
import org.secondopinion.reports.dto.TextTemplates;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseTextTemplates extends BaseDomainObject<Long> {

	public static final String FIELD_textTemplatesId = "textTemplatesId";
	public static final String FIELD_level = "level";
	public static final String FIELD_referenceId = "referenceId";
	public static final String FIELD_templateName = "templateName";
	public static final String FIELD_templateText = "templateText";
	public static final String FIELD_defaultTemplate = "defaultTemplate";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdTs = "createdTs";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";
	
	private Long textTemplatesId;
	private String level;
	private Long referenceId;
	private String templateName;
	private String templateText;
	private String defaultTemplate;
	private String createdBy;
	private Date createdTs;

	public void setTextTemplatesId( Long  _textTemplatesId){
		this.textTemplatesId = _textTemplatesId;
	}
	@Id
// 	@NotNull
	@Column(name = "textTemplatesId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	public Long getTextTemplatesId(){
		 return this.textTemplatesId;
	}
	public void setLevel( String  _level){
		this.level = _level;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="level")
	public String getLevel(){
		 return this.level;
	}
	public void setReferenceId( Long  _referenceId){
		this.referenceId = _referenceId;
	}
	@NotNull 
	@Column (name="referenceId")
	public Long getReferenceId(){
		 return this.referenceId;
	}
	public void setTemplateName( String  _templateName){
		this.templateName = _templateName;
	}
	@NotNull 
	@Length(max=100)
	@Column (name="templateName")
	public String getTemplateName(){
		 return this.templateName;
	}
	public void setTemplateText( String  _templateText){
		this.templateText = _templateText;
	}
	@NotNull 
	@Length(max=21845)
	@Column (name="templateText")
	public String getTemplateText(){
		 return this.templateText;
	}
	public void setDefaultTemplate( String  _defaultTemplate){
		this.defaultTemplate = _defaultTemplate;
	}
	@NotNull 
	@Length(max=1)
	@Column (name="defaultTemplate")
	public String getDefaultTemplate(){
		 return this.defaultTemplate;
	}
	public void setCreatedBy( String  _createdBy){
		this.createdBy = _createdBy;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="createdBy")
	public String getCreatedBy(){
		 return this.createdBy;
	}
	public void setCreatedTs( Date  _createdTs){
		this.createdTs = _createdTs;
	}
	@NotNull 
	@Column (name="createdTs")
	public Date getCreatedTs(){
		 return this.createdTs;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseTextTemplates other = (BaseTextTemplates)o;
		if(
			ObjectUtil.isEqual(getLevel(), other.getLevel()) && 
			ObjectUtil.isEqual(getReferenceId(), other.getReferenceId()) && 
			ObjectUtil.isEqual(getTemplateName(), other.getTemplateName()) && 
			ObjectUtil.isEqual(getTemplateText(), other.getTemplateText()) && 
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
		result = result + (textTemplatesId!= null ? textTemplatesId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.textTemplatesId == null){
			 list.add(new ValidationMessage("Field " + FIELD_textTemplatesId+  " cannot be null"));
		}

		}
		if( StringUtil.isNullOrEmpty(this.level)){
			 list.add(new ValidationMessage("Field " + FIELD_level+  " cannot be null"));
		}

		if((this.level != null) && (this.level.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_level+  " cannot be longer than: " + 45));
		}

		if(this.referenceId == null){
			 list.add(new ValidationMessage("Field " + FIELD_referenceId+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.templateName)){
			 list.add(new ValidationMessage("Field " + FIELD_templateName+  " cannot be null"));
		}

		if((this.templateName != null) && (this.templateName.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_templateName+  " cannot be longer than: " + 100));
		}

		if( StringUtil.isNullOrEmpty(this.templateText)){
			 list.add(new ValidationMessage("Field " + FIELD_templateText+  " cannot be null"));
		}

		if((this.templateText != null) && (this.templateText.length()>21845)){
			 list.add(new ValidationMessage("Field " + FIELD_templateText+  " cannot be longer than: " + 21845));
		}

		if(this.defaultTemplate == null){
			 list.add(new ValidationMessage("Field " + FIELD_defaultTemplate+  " cannot be null"));
		}

		if(this.isFromDB()){
			if( StringUtil.isNullOrEmpty(this.createdBy)){
				 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be null"));
			}
		}
		if(this.isFromDB()){
			if((this.createdBy != null) && (this.createdBy.length()>45)){
				 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 45));
			}
		}
		if(this.isFromDB()){
			if(this.createdTs == null){
				 list.add(new ValidationMessage("Field " + FIELD_createdTs+  " cannot be null"));
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
		str.append("textTemplatesId = " + textTemplatesId + "\n");
;
		str.append("level = " + level + "\n");
		str.append("referenceId = " + referenceId + "\n");
		str.append("templateName = " + templateName + "\n");
		str.append("templateText = " + templateText + "\n");
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
		return (textTemplatesId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_textTemplatesId, getTextTemplatesId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getTextTemplatesId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		TextTemplates textTemplates = new TextTemplates();
		textTemplates.setFromDB(false);
		textTemplates.setLevel(getLevel());
		textTemplates.setReferenceId(getReferenceId());
		textTemplates.setTemplateName(getTemplateName());
		textTemplates.setTemplateText(getTemplateText());
		textTemplates.setDefaultTemplate(getDefaultTemplate());
		//afterClone(textTemplates);
		return textTemplates;
	}
}