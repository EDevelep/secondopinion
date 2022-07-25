package org.secondopnion.patient.service;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.secondopinion.patient.domain.BaseAilments;
import org.secondopinion.patient.dto.Ailments;
import org.secondopinion.patient.service.IAilments;
import org.secondopnion.patient.PatientApplicationTest;

public class AilmentsServiceTest extends PatientApplicationTest {

	private IAilments  iailments;
	
	@Test
	public void saveAilments() {
		Ailments ailments=new Ailments();
		ailments.setUserId(141L);
		ailments.setAilment("test");
	//	ailments.setRecordedDate(LocalDate.now());
		ailments.setAilmentDetails("testByjatin");
		ailments.setAilmentId(132L);
		iailments.saveAilment(ailments);
	}
	@Test
	public void getaliment() {
		Long userID=126L;
	List<Ailments>	 ali=iailments.getaliment(userID);
	assertNotNull(ali);
	}
		
	@Test
	public void testGetAliment() {
		List<Ailments>  aliments = iailments.getaliment(12L);
		assertNotNull(aliments);
		
	}
		
}
