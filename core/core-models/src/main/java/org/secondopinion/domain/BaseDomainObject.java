package org.secondopinion.domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.secondopinion.common.dto.BaseSODto;
import org.secondopinion.domain.validation.ValidationMessage;


@MappedSuperclass
@JsonIgnoreProperties(value = { "keyField" })
public abstract class BaseDomainObject<KEY_TYPE> extends BaseSODto {

	private boolean hasValidationErrors = false;
	
	private Collection<ValidationMessage> validationMessages;
	
	@Transient
	@JsonIgnore
	public abstract boolean isKeyNull();
	
	@Transient
	@JsonIgnore
	public abstract KEY_TYPE getId();
	
	@Transient
	@JsonIgnore
	public abstract List<KeyField> getKeyField();
	
	protected void resetValidationMessages() {
		hasValidationErrors = false;
	}
	
	public void setHasValidationErrors(boolean validationErrors) {
		this.hasValidationErrors = validationErrors;
	}
	
	public boolean hasValidationErrors() {
		return hasValidationErrors;
	}

	public void setAuditFields() {
		
	}

	/**
	 * @return the validationMessages
	 */
	@Transient
	public Collection<ValidationMessage> getValidationMessages() {
		return validationMessages;
	}
	/**
	 * @param validationMessages the validationMessages to set
	 */
	public void setValidationMessages(Collection<ValidationMessage> validationMessages) {
		this.validationMessages = validationMessages;
	}
	
//	public void afterClone(OBJECT_TYPE type){
//		
//	}
	
	public void validate(boolean validatePk) {
		
	}
	
}
