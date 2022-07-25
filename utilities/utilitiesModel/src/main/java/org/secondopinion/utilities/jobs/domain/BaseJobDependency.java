package org.secondopinion.utilities.jobs.domain; 

import java.util.ArrayList; 
import java.util.List; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utilities.jobs.dto.JobDependency;
import org.secondopinion.utils.ObjectUtil; 

import javax.persistence.Column; 
import javax.persistence.MappedSuperclass; 
import javax.validation.constraints.NotNull; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Transient;
@MappedSuperclass 
public abstract class BaseJobDependency extends BaseDomainObject<Integer> {

	public static final String FIELD_jobDependencyId = "jobDependencyId";
	public static final String FIELD_jobId = "jobId";
	public static final String FIELD_dependentJobId = "dependentJobId";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Integer jobDependencyId;
	private Integer jobId;
	private Integer dependentJobId;

	public void setJobDependencyId( Integer  _jobDependencyId){
		this.jobDependencyId = _jobDependencyId;
	}
	@Id
// 	@NotNull
	@Column(name = "jobDependencyId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getJobDependencyId(){
		 return this.jobDependencyId;
	}
	public void setJobId( Integer  _jobId){
		this.jobId = _jobId;
	}
	@NotNull 
	@Column (name="jobId")
	public Integer getJobId(){
		 return this.jobId;
	}
	public void setDependentJobId( Integer  _dependentJobId){
		this.dependentJobId = _dependentJobId;
	}
	@NotNull 
	@Column (name="dependentJobId")
	public Integer getDependentJobId(){
		 return this.dependentJobId;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseJobDependency other = (BaseJobDependency)o;
		if(
			ObjectUtil.isEqual(getJobId(), other.getJobId()) && 
			ObjectUtil.isEqual(getDependentJobId(), other.getDependentJobId()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (jobDependencyId!= null ? jobDependencyId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.jobDependencyId == null){
			 list.add(new ValidationMessage("Field " + FIELD_jobDependencyId+  " cannot be null"));
		}

		}
		if(this.jobId == null){
			 list.add(new ValidationMessage("Field " + FIELD_jobId+  " cannot be null"));
		}

		if(this.dependentJobId == null){
			 list.add(new ValidationMessage("Field " + FIELD_dependentJobId+  " cannot be null"));
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
		str.append("jobDependencyId = " + jobDependencyId + "\n");
;
		str.append("jobId = " + jobId + "\n");
		str.append("dependentJobId = " + dependentJobId + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (jobDependencyId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_jobDependencyId, getJobDependencyId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getJobDependencyId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		JobDependency jobDependency = new JobDependency();
		jobDependency.setFromDB(false);
		jobDependency.setJobId(getJobId());
		jobDependency.setDependentJobId(getDependentJobId());
		//afterClone(jobDependency);
		return jobDependency;
	}
}