package org.secondopinion.diagnosticcenter.dao;

import java.util.List;

import org.secondopinion.common.dto.RatingsDTO;
import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterratings;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterratingsDTO;
import org.secondopinion.request.Response;

public interface DiagnosticcenterratingsDAO extends IDAO<Diagnosticcenterratings,Long >{

	
	RatingsDTO getRatingValues(Long diagnosticcenterid);
 Response<List<Diagnosticcenterratings>> getDiagnosticcenterratingsSerchCritera(DiagnosticcenterratingsDTO diagnosticcenterratingsDTO) ;
	Response<List<Diagnosticcenterratings>> getRatingsByDiagnosticcenterid(Long diagnosticcenterId, Integer pageNum, Integer maxResults);
	
}