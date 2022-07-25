package org.secondopinion.utilities.jobs.dto; 


import java.util.Map;

import javax.persistence.Entity; 
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.utilities.jobs.domain.BaseJobParams;




@Entity 
@Table (name="jobparams")
public class JobParams extends BaseJobParams{
	@Override
	@Transient
	public Map<String, Object> getParams() {
		Map<String, Object> params = super.getParams();
		params.put("jobId", getJobId());
		params.put("paramName", getParamName());
		params.put("paramValue", getParamValue());

		return params;
	}
}