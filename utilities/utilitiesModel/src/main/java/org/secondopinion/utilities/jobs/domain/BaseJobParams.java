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
import org.secondopinion.utilities.jobs.dto.JobParams;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseJobParams extends BaseDomainObject<Integer> {

	public static final String FIELD_jobParamsId = "jobParamsId";
	public static final String FIELD_jobId = "jobId";
	public static final String FIELD_paramName = "paramName";
	public static final String FIELD_paramValue = "paramValue";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Integer jobParamsId;
	private Integer jobId;
	private String paramName;
	private String paramValue;

	public void setJobParamsId( Integer  _jobParamsId){
		this.jobParamsId = _jobParamsId;
	}
	@Id
// 	@NotNull
	@Column(name = "jobParamsId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getJobParamsId(){
		 return this.jobParamsId;
	}
	public void setJobId( Integer  _jobId){
		this.jobId = _jobId;
	}
	@NotNull 
	@Column (name="jobId")
	public Integer getJobId(){
		 return this.jobId;
	}
	public void setParamName( String  _paramName){
		this.paramName = _paramName;
	}
	@NotNull 
	@Length(max=100)
	@Column (name="paramName")
	public String getParamName(){
		 return this.paramName;
	}
	public void setParamValue( String  _paramValue){
		this.paramValue = _paramValue;
	}
	@Length(max=100)
	@Column (name="paramValue")
	public String getParamValue(){
		 return this.paramValue;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseJobParams other = (BaseJobParams)o;
		if(
			ObjectUtil.isEqual(getJobId(), other.getJobId()) && 
			ObjectUtil.isEqual(getParamName(), other.getParamName()) && 
			ObjectUtil.isEqual(getParamValue(), other.getParamValue()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (jobParamsId!= null ? jobParamsId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.jobParamsId == null){
			 list.add(new ValidationMessage("Field " + FIELD_jobParamsId+  " cannot be null"));
		}

		}
		if(this.jobId == null){
			 list.add(new ValidationMessage("Field " + FIELD_jobId+  " cannot be null"));
		}

		if( StringUtil.isNullOrEmpty(this.paramName)){
			 list.add(new ValidationMessage("Field " + FIELD_paramName+  " cannot be null"));
		}

		if((this.paramName != null) && (this.paramName.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_paramName+  " cannot be longer than: " + 100));
		}

		if((this.paramValue != null) && (this.paramValue.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_paramValue+  " cannot be longer than: " + 100));
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
		str.append("jobParamsId = " + jobParamsId + "\n");
;
		str.append("jobId = " + jobId + "\n");
		str.append("paramName = " + paramName + "\n");
		str.append("paramValue = " + paramValue + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (jobParamsId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_jobParamsId, getJobParamsId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getJobParamsId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		JobParams jobParams = new JobParams();
		jobParams.setFromDB(false);
		jobParams.setJobId(getJobId());
		jobParams.setParamName(getParamName());
		jobParams.setParamValue(getParamValue());
		//afterClone(jobParams);
		return jobParams;
	}
}