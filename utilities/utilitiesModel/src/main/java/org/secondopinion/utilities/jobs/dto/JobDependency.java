package org.secondopinion.utilities.jobs.dto; 


import java.util.Map;

import javax.persistence.Entity; 
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.utilities.jobs.domain.BaseJobDependency;




@Entity 
@Table (name="jobdependency")
public class JobDependency extends BaseJobDependency{
	@Override
	@Transient
	public Map<String, Object> getParams() {
		Map<String, Object> params = super.getParams();
		
		return params;
	}
}