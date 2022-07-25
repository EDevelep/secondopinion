package org.secondopinion.utilities.jobs.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.utilities.jobs.dto.JobDependency;

public interface JobDependencyDAO extends IDAO<JobDependency,Integer >{

	List<JobDependency> getJobDependencies(Integer jobId);
}