package org.secondopinion.diagnosticcenter.serviceimpl;

import org.junit.Test;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterratings;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterratingsDTO;
import org.secondopinion.diagnosticcenter.service.IRatingsDiagnosticcenter;
import org.secondopinion.diagnosticcenter.test.DiagnosticcenterServiceApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class DiagnosticcenterratingTest extends DiagnosticcenterServiceApplicationTest {
	
	@Autowired
	private IRatingsDiagnosticcenter iRatingsDiagnosticcenter;
	
	@Test
	public void saveDiagnosticcenterRating() {
		Diagnosticcenterratings diagnosticcenterratings=new Diagnosticcenterratings();
		diagnosticcenterratings.setPatientid(20L);
		diagnosticcenterratings.setRating(1.0d);
		diagnosticcenterratings.setFeedback("test feedback");
		diagnosticcenterratings.setDiagnosticcenterId(24L);
		diagnosticcenterratings.setActive('Y');
		iRatingsDiagnosticcenter.saveRatings(diagnosticcenterratings);
	}
	
	@Test
	public void getRatingsBydiagnosticcenterSerchCritera() {
		DiagnosticcenterratingsDTO diagnosticcenterratingsDTO=new DiagnosticcenterratingsDTO();
		diagnosticcenterratingsDTO.setPatientid(22L);
		diagnosticcenterratingsDTO.setMaxresult(1);
		diagnosticcenterratingsDTO.setPagenumber(1);
		iRatingsDiagnosticcenter.getRatingsBydiagnosticcenterSerchCritera(diagnosticcenterratingsDTO);
	}
	
	

}

