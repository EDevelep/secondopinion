package org.secondopinion.utilities.jobs.dto; 


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.utilities.jobs.domain.BaseJobDef;


@Entity 
@Table (name="jobdef")
public class JobDef extends BaseJobDef{
	private List<JobParams> jobParams;
	private List<JobDependency> dependencies;

	@Transient
	public List<JobParams> getJobParams() {
		return jobParams;
	}

	public void setJobParams(List<JobParams> jobParams) {
		this.jobParams = jobParams;
	}

	@Transient
	public List<JobDependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<JobDependency> dependencies) {
		this.dependencies = dependencies;
	}
	
	@Transient
	public Map<String, String> getJobParamsAsMap(){
		Map<String, String> params = new HashMap<String, String>();
		
		if(getJobParams() != null && getJobParams().size() > 0){
			for(JobParams param : getJobParams()){
				params.put(param.getParamName(), param.getParamValue());
			}
		}
		
		return params;
	}

	public void setJobIdForParams() {
		for(JobParams jobParam : jobParams){
			jobParam.setJobId(getJobId());
		}
	}
	
	public void setJobIdForDepencencies() {
		for(JobDependency dependency : dependencies){
			dependency.setJobId(getJobId());
		}
	}
}