package org.secondopinion.utilities.jobs.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.utilities.jobs.dto.JobDef;

public interface JobDefDAO extends IDAO<JobDef,Integer >{
	JobDef getJobDefinition(String beanid);
	
	List<JobDef> getAllJobDefs();
	
}