package org.secondopinion.utilities.jobs.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.utilities.jobs.dto.Lookupstatic;



public interface LookupstaticDAO extends IDAO<Lookupstatic,Long >{

	List<Lookupstatic> getDiagnosticcenterTest(String type, String module);
}