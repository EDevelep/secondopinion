
package org.secondopinion.utilities.feedbackreport.service;

import java.util.List;
import java.util.Map;

import org.secondopinion.utilities.jobs.dto.LookupType;
import org.secondopinion.utilities.jobs.dto.Lookupstatic;




public interface ILookupstaticService {

	
	List<Lookupstatic> getAllLookupstatics();


	void getAllLookupstaticsMap();
	

	List<Lookupstatic> getDiagnosticcenterTest(String type,String module);


}
