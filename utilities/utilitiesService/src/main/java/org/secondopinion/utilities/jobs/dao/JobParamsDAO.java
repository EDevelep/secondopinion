package org.secondopinion.utilities.jobs.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.utilities.jobs.dto.JobParams;

public interface JobParamsDAO extends IDAO<JobParams,Integer >{
	
	List<JobParams> getJobParams(Integer jobId);
}