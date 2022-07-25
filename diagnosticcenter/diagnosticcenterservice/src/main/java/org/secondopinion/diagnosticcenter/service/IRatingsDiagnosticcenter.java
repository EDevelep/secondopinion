package org.secondopinion.diagnosticcenter.service;

import java.util.List;

import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterratings;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterratingsDTO;

import org.secondopinion.request.Response;



public interface IRatingsDiagnosticcenter {
	
	void saveRatings(Diagnosticcenterratings diagnosticcenterratings);
	void updateRatings(Diagnosticcenterratings diagnosticcenterratings);
	Response<List<Diagnosticcenterratings>> getRatingsBydiagnosticcenterSerchCritera(DiagnosticcenterratingsDTO diagnosticcenterratingsDTO);

}
