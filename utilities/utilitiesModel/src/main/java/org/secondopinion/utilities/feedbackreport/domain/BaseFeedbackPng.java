package org.secondopinion.utilities.feedbackreport.domain; 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utilities.feedbackreport.dto.FeedbackPng;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseFeedbackPng extends BaseDomainObject<Integer> {

	public static final String FIELD_feedbackPngId = "feedbackPngId";
	public static final String FIELD_feedbackId = "feedbackId";
	public static final String FIELD_screenshot = "screenshot";
	public static final String FIELD_fileName = "fileName";
	public static final String FIELD_contentType = "contentType";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Integer feedbackPngId;
	private Long feedbackId;
	private byte[] screenshot;
	private String fileName;
	private String contentType;
	
	public void setFeedbackPngId( Integer  feedbackPngId){
		this.feedbackPngId = feedbackPngId;
	}
	
	@Id
	@Column(name = "feedbackPngId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getFeedbackPngId(){
		 return this.feedbackPngId;
	}
	
	public void setFeedbackId( Long  feedbackId){
		this.feedbackId = feedbackId;
	}

	@Column (name="feedbackId", nullable=false)
	public Long getFeedbackId(){
		 return this.feedbackId;
	}
	
	public void setScreenshot( byte[] screenshot){
		this.screenshot = screenshot;
	}
	
	@Column (name="screenshot", columnDefinition="MEDIUMBLOB")
	public byte[] getScreenshot(){
		 return this.screenshot;
	}
	/**
	 * @return the fileName
	 */
	@Column (name="fileName")
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the contentType
	 */
	@Column (name="contentType")
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseFeedbackPng other = (BaseFeedbackPng)o;
		if(
			ObjectUtil.isEqual(getFeedbackId(), other.getFeedbackId()) && 
			ObjectUtil.isEqual(getScreenshot(), other.getScreenshot()) && 
			ObjectUtil.isEqual(getFileName(), other.getFileName()) && 
			ObjectUtil.isEqual(getContentType(), other.getContentType()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (feedbackPngId!= null ? feedbackPngId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
			if(this.feedbackPngId == null){
				 list.add(new ValidationMessage("Field " + FIELD_feedbackPngId+  " cannot be null"));
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
	public final void setAuditFields() {
		if(!isFromDB()){
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseFeedbackPng [feedbackPngId=" + feedbackPngId + ", feedbackId=" + feedbackId + ", screenshot="
				+ Arrays.toString(screenshot) + ", fileName=" + fileName + ", contentType=" + contentType + "]";
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (feedbackPngId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_feedbackPngId, getFeedbackPngId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getFeedbackPngId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		FeedbackPng feedbackPng = new FeedbackPng();
		feedbackPng.setFromDB(false);
		feedbackPng.setFeedbackId(getFeedbackId());
		feedbackPng.setScreenshot(getScreenshot());
		return feedbackPng;
	}
}