package org.secondopinion.utilities.jobs.domain; 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utilities.jobs.dto.JobInfo;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseJobInfo extends BaseDomainObject<Long> {

	public static final String FIELD_jobInfoId = "jobInfoId";
	public static final String FIELD_endTime = "endTime";
	public static final String FIELD_exception = "exception";
	public static final String FIELD_executionDate = "executionDate";
	public static final String FIELD_jobBeanId = "jobBeanId";
	public static final String FIELD_jobId = "jobId";
	public static final String FIELD_jobName = "jobName";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";
	public static final String FIELD_numberFailed = "numberFailed";
	public static final String FIELD_numberProcessed = "numberProcessed";
	public static final String FIELD_numberSuccessfullyProcessed = "numberSuccessfullyProcessed";
	public static final String FIELD_postProcessEndTime = "postProcessEndTime";
	public static final String FIELD_postProcessStartTime = "postProcessStartTime";
	public static final String FIELD_preProcessEndTime = "preProcessEndTime";
	public static final String FIELD_preProcessStartTime = "preProcessStartTime";
	public static final String FIELD_startTime = "startTime";
	public static final String FIELD_status = "status";
	public static final String FIELD_totalTimeTaken = "totalTimeTaken";

	private Long jobInfoId;
	private Date endTime;
	private String exception;
	private Date executionDate;
	private String jobBeanId;
	private Integer jobId;
	private String jobName;
	private Long numberFailed;
	private Long numberProcessed;
	private Long numberSuccessfullyProcessed;
	private Date postProcessEndTime;
	private Date postProcessStartTime;
	private Date preProcessEndTime;
	private Date preProcessStartTime;
	private Date startTime;
	private String status;
	private Integer totalTimeTaken;

	public void setJobInfoId( Long  _jobInfoId){
		this.jobInfoId = _jobInfoId;
	}
	@Id
// 	@NotNull
	@Column(name = "jobInfoId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getJobInfoId(){
		 return this.jobInfoId;
	}
	public void setEndTime( Date  _endTime){
		this.endTime = _endTime;
	}
	@Column (name="endTime")
	public Date getEndTime(){
		 return this.endTime;
	}
	public void setException( String  _exception){
		this.exception = _exception;
	}
	@Length(max=2000)
	@Column (name="exception")
	public String getException(){
		 return this.exception;
	}
	public void setExecutionDate( Date  _executionDate){
		this.executionDate = _executionDate;
	}
	@Column (name="executionDate")
	public Date getExecutionDate(){
		 return this.executionDate;
	}
	public void setJobBeanId( String  _jobBeanId){
		this.jobBeanId = _jobBeanId;
	}
	@Length(max=255)
	@Column (name="jobBeanId")
	public String getJobBeanId(){
		 return this.jobBeanId;
	}
	public void setJobId( Integer  _jobId){
		this.jobId = _jobId;
	}
	@Column (name="jobId")
	public Integer getJobId(){
		 return this.jobId;
	}
	public void setJobName( String  _jobName){
		this.jobName = _jobName;
	}
	@Length(max=255)
	@Column (name="jobName")
	public String getJobName(){
		 return this.jobName;
	}
	public void setNumberFailed( Long  _numberFailed){
		this.numberFailed = _numberFailed;
	}
	@Column (name="numberFailed")
	public Long getNumberFailed(){
		 return this.numberFailed;
	}
	public void setNumberProcessed( Long  _numberProcessed){
		this.numberProcessed = _numberProcessed;
	}
	@Column (name="numberProcessed")
	public Long getNumberProcessed(){
		 return this.numberProcessed;
	}
	public void setNumberSuccessfullyProcessed( Long  _numberSuccessfullyProcessed){
		this.numberSuccessfullyProcessed = _numberSuccessfullyProcessed;
	}
	@Column (name="numberSuccessfullyProcessed")
	public Long getNumberSuccessfullyProcessed(){
		 return this.numberSuccessfullyProcessed;
	}
	public void setPostProcessEndTime( Date  _postProcessEndTime){
		this.postProcessEndTime = _postProcessEndTime;
	}
	@Column (name="postProcessEndTime")
	public Date getPostProcessEndTime(){
		 return this.postProcessEndTime;
	}
	public void setPostProcessStartTime( Date  _postProcessStartTime){
		this.postProcessStartTime = _postProcessStartTime;
	}
	@Column (name="postProcessStartTime")
	public Date getPostProcessStartTime(){
		 return this.postProcessStartTime;
	}
	public void setPreProcessEndTime( Date  _preProcessEndTime){
		this.preProcessEndTime = _preProcessEndTime;
	}
	@Column (name="preProcessEndTime")
	public Date getPreProcessEndTime(){
		 return this.preProcessEndTime;
	}
	public void setPreProcessStartTime( Date  _preProcessStartTime){
		this.preProcessStartTime = _preProcessStartTime;
	}
	@Column (name="preProcessStartTime")
	public Date getPreProcessStartTime(){
		 return this.preProcessStartTime;
	}
	public void setStartTime( Date  _startTime){
		this.startTime = _startTime;
	}
	@Column (name="startTime")
	public Date getStartTime(){
		 return this.startTime;
	}
	public void setStatus( String  _status){
		this.status = _status;
	}
	@Length(max=255)
	@Column (name="status")
	public String getStatus(){
		 return this.status;
	}
	public void setTotalTimeTaken( Integer  _totalTimeTaken){
		this.totalTimeTaken = _totalTimeTaken;
	}
	@Column (name="totalTimeTaken")
	public Integer getTotalTimeTaken(){
		 return this.totalTimeTaken;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseJobInfo other = (BaseJobInfo)o;
		if(
			ObjectUtil.isEqual(getEndTime(), other.getEndTime()) && 
			ObjectUtil.isEqual(getException(), other.getException()) && 
			ObjectUtil.isEqual(getExecutionDate(), other.getExecutionDate()) && 
			ObjectUtil.isEqual(getJobBeanId(), other.getJobBeanId()) && 
			ObjectUtil.isEqual(getJobId(), other.getJobId()) && 
			ObjectUtil.isEqual(getJobName(), other.getJobName()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs()) && 
			ObjectUtil.isEqual(getNumberFailed(), other.getNumberFailed()) && 
			ObjectUtil.isEqual(getNumberProcessed(), other.getNumberProcessed()) && 
			ObjectUtil.isEqual(getNumberSuccessfullyProcessed(), other.getNumberSuccessfullyProcessed()) && 
			ObjectUtil.isEqual(getPostProcessEndTime(), other.getPostProcessEndTime()) && 
			ObjectUtil.isEqual(getPostProcessStartTime(), other.getPostProcessStartTime()) && 
			ObjectUtil.isEqual(getPreProcessEndTime(), other.getPreProcessEndTime()) && 
			ObjectUtil.isEqual(getPreProcessStartTime(), other.getPreProcessStartTime()) && 
			ObjectUtil.isEqual(getStartTime(), other.getStartTime()) && 
			ObjectUtil.isEqual(getStatus(), other.getStatus()) && 
			ObjectUtil.isEqual(getTotalTimeTaken(), other.getTotalTimeTaken())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (jobInfoId!= null ? jobInfoId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.jobInfoId == null){
			 list.add(new ValidationMessage("Field " + FIELD_jobInfoId+  " cannot be null"));
		}

		}
		if((this.exception != null) && (this.exception.length()>2000)){
			 list.add(new ValidationMessage("Field " + FIELD_exception+  " cannot be longer than: " + 2000));
		}

		if((this.jobBeanId != null) && (this.jobBeanId.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_jobBeanId+  " cannot be longer than: " + 255));
		}

		if((this.jobName != null) && (this.jobName.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_jobName+  " cannot be longer than: " + 255));
		}

	if(this.isFromDB()){
		if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 255));
		}
	}
		if((this.status != null) && (this.status.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_status+  " cannot be longer than: " + 255));
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
		str.append("jobInfoId = " + jobInfoId + "\n");
;
		str.append("endTime = " + endTime + "\n");
		str.append("exception = " + exception + "\n");
		str.append("executionDate = " + executionDate + "\n");
		str.append("jobBeanId = " + jobBeanId + "\n");
		str.append("jobId = " + jobId + "\n");
		str.append("jobName = " + jobName + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		str.append("numberFailed = " + numberFailed + "\n");
		str.append("numberProcessed = " + numberProcessed + "\n");
		str.append("numberSuccessfullyProcessed = " + numberSuccessfullyProcessed + "\n");
		str.append("postProcessEndTime = " + postProcessEndTime + "\n");
		str.append("postProcessStartTime = " + postProcessStartTime + "\n");
		str.append("preProcessEndTime = " + preProcessEndTime + "\n");
		str.append("preProcessStartTime = " + preProcessStartTime + "\n");
		str.append("startTime = " + startTime + "\n");
		str.append("status = " + status + "\n");
		str.append("totalTimeTaken = " + totalTimeTaken + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (jobInfoId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_jobInfoId, getJobInfoId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getJobInfoId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		JobInfo jobInfo = new JobInfo();
		jobInfo.setFromDB(false);
		jobInfo.setEndTime(getEndTime());
		jobInfo.setException(getException());
		jobInfo.setExecutionDate(getExecutionDate());
		jobInfo.setJobBeanId(getJobBeanId());
		jobInfo.setJobId(getJobId());
		jobInfo.setJobName(getJobName());
		jobInfo.setNumberFailed(getNumberFailed());
		jobInfo.setNumberProcessed(getNumberProcessed());
		jobInfo.setNumberSuccessfullyProcessed(getNumberSuccessfullyProcessed());
		jobInfo.setPostProcessEndTime(getPostProcessEndTime());
		jobInfo.setPostProcessStartTime(getPostProcessStartTime());
		jobInfo.setPreProcessEndTime(getPreProcessEndTime());
		jobInfo.setPreProcessStartTime(getPreProcessStartTime());
		jobInfo.setStartTime(getStartTime());
		jobInfo.setStatus(getStatus());
		jobInfo.setTotalTimeTaken(getTotalTimeTaken());
		//afterClone(jobInfo);
		return jobInfo;
	}
}