package org.secondopinion.utilities.feedbackreport.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.utilities.feedbackreport.domain.BaseFeedbackLoop;


@Entity 
@Table (name="feedbackloop")
public class FeedbackLoop extends BaseFeedbackLoop{
	
	private FeedbackPng feedbackPng;
	private String userName;
	private String clientName;
	
	@Transient
	public FeedbackPng getFeedbackPng() {
		return feedbackPng;
	}

	public void setFeedbackPng(FeedbackPng feedbackPng) {
		this.feedbackPng = feedbackPng;
	}
	
	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Transient
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public enum STATUS{
		ALL(""), NEW("NEW_FEEDBACK_TEMPLATE"), IN_PROGRESS("PENDING_FEEDBACK_TEMPLATE"), 
		COMPLETED("COMPLETED_FEEDBACK_TEMPLATE");
		
		private final String templateName;
		
		private STATUS(String _templateName){
			this.templateName = _templateName;
		}
		
		public String getTemplateName() {
			return templateName;
		}
	}

	@Transient
	public String getFeedBackTemplateName() {
		return STATUS.valueOf(getStatus()).templateName;
	}

	
	
	
}