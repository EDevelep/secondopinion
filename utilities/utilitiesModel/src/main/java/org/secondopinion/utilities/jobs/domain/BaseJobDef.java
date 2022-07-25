package org.secondopinion.utilities.jobs.domain; 

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

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utilities.jobs.dto.JobDef;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseJobDef extends BaseDomainObject<Integer> {

	public static final String FIELD_jobId = "jobId";
	public static final String FIELD_jobName = "jobName";
	public static final String FIELD_jobDescription = "jobDescription";
	public static final String FIELD_jobBeanId = "jobBeanId";
	public static final String FIELD_sendStartEmail = "sendStartEmail";
	public static final String FIELD_sendEndEmail = "sendEndEmail";
	public static final String FIELD_sendFailureEmail = "sendFailureEmail";
	public static final String FIELD_schedule = "schedule";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Integer jobId;
	private String jobName;
	private String jobDescription;
	private String jobBeanId;
	private Boolean sendStartEmail;
	private Boolean sendEndEmail;
	private Boolean sendFailureEmail;
	private String schedule;

	public void setJobId( Integer  _jobId){
		this.jobId = _jobId;
	}
	@Id
// 	@NotNull
	@Column(name = "jobId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getJobId(){
		 return this.jobId;
	}
	public void setJobName( String  _jobName){
		this.jobName = _jobName;
	}
	@Length(max=100)
	@Column (name="jobName")
	public String getJobName(){
		 return this.jobName;
	}
	public void setJobDescription( String  _jobDescription){
		this.jobDescription = _jobDescription;
	}
	@Length(max=250)
	@Column (name="jobDescription")
	public String getJobDescription(){
		 return this.jobDescription;
	}
	public void setJobBeanId( String  _jobBeanId){
		this.jobBeanId = _jobBeanId;
	}
	@NotNull 
	@Length(max=250)
	@Column (name="jobBeanId")
	public String getJobBeanId(){
		 return this.jobBeanId;
	}
	public void setSendStartEmail( Boolean  _sendStartEmail){
		this.sendStartEmail = _sendStartEmail;
	}
	@Column (name="sendStartEmail")
	public Boolean getSendStartEmail(){
		 return this.sendStartEmail;
	}
	public void setSendEndEmail( Boolean  _sendEndEmail){
		this.sendEndEmail = _sendEndEmail;
	}
	@Column (name="sendEndEmail")
	public Boolean getSendEndEmail(){
		 return this.sendEndEmail;
	}
	public void setSendFailureEmail( Boolean  _sendFailureEmail){
		this.sendFailureEmail = _sendFailureEmail;
	}
	@Column (name="sendFailureEmail")
	public Boolean getSendFailureEmail(){
		 return this.sendFailureEmail;
	}
	public void setSchedule( String  _schedule){
		this.schedule = _schedule;
	}
	@Length(max=250)
	@Column (name="schedule")
	public String getSchedule(){
		 return this.schedule;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseJobDef other = (BaseJobDef)o;
		if(
			ObjectUtil.isEqual(getJobName(), other.getJobName()) && 
			ObjectUtil.isEqual(getJobDescription(), other.getJobDescription()) && 
			ObjectUtil.isEqual(getJobBeanId(), other.getJobBeanId()) && 
			ObjectUtil.isEqual(getSendStartEmail(), other.getSendStartEmail()) && 
			ObjectUtil.isEqual(getSendEndEmail(), other.getSendEndEmail()) && 
			ObjectUtil.isEqual(getSendFailureEmail(), other.getSendFailureEmail()) && 
			ObjectUtil.isEqual(getSchedule(), other.getSchedule()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (jobId!= null ? jobId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.jobId == null){
			 list.add(new ValidationMessage("Field " + FIELD_jobId+  " cannot be null"));
		}

		}
		if((this.jobName != null) && (this.jobName.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_jobName+  " cannot be longer than: " + 100));
		}

		if((this.jobDescription != null) && (this.jobDescription.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_jobDescription+  " cannot be longer than: " + 250));
		}

		if( StringUtil.isNullOrEmpty(this.jobBeanId)){
			 list.add(new ValidationMessage("Field " + FIELD_jobBeanId+  " cannot be null"));
		}

		if((this.jobBeanId != null) && (this.jobBeanId.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_jobBeanId+  " cannot be longer than: " + 250));
		}

		if((this.schedule != null) && (this.schedule.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_schedule+  " cannot be longer than: " + 250));
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
		str.append("jobId = " + jobId + "\n");
;
		str.append("jobName = " + jobName + "\n");
		str.append("jobDescription = " + jobDescription + "\n");
		str.append("jobBeanId = " + jobBeanId + "\n");
		str.append("sendStartEmail = " + sendStartEmail + "\n");
		str.append("sendEndEmail = " + sendEndEmail + "\n");
		str.append("sendFailureEmail = " + sendFailureEmail + "\n");
		str.append("schedule = " + schedule + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (jobId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_jobId, getJobId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getJobId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		JobDef jobDef = new JobDef();
		jobDef.setFromDB(false);
		jobDef.setJobName(getJobName());
		jobDef.setJobDescription(getJobDescription());
		jobDef.setJobBeanId(getJobBeanId());
		jobDef.setSendStartEmail(getSendStartEmail());
		jobDef.setSendEndEmail(getSendEndEmail());
		jobDef.setSendFailureEmail(getSendFailureEmail());
		jobDef.setSchedule(getSchedule());
		//afterClone(jobDef);
		return jobDef;
	}
}