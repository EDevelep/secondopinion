package org.secondopnion.patient.service;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;
import org.secondopinion.patient.dto.Familyhistory;
import org.secondopinion.patient.dto.MedicalHistoryDto;
import org.secondopinion.patient.dto.Personalsymptoms;
import org.secondopinion.patient.dto.Questionairre;
import org.secondopinion.patient.dto.Socialhistory;
import org.secondopinion.patient.dto.Surgerydetails;
import org.secondopinion.patient.service.IMedicalHistory;
import org.secondopinion.utils.DateUtil;
import org.secondopnion.patient.PatientApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class MedicalHistoryServiceTest extends PatientApplicationTest {

	@Autowired
	private IMedicalHistory medicalHistory;

	@Test
	public void saveMedicalHistory() {
		MedicalHistoryDto medicalHistoryDto = new MedicalHistoryDto();
		Surgerydetails suregerydetails = new Surgerydetails();
		suregerydetails.setDate(DateUtil.getDate());
		suregerydetails.setUserId(141L);
		suregerydetails.setName("byherat");
		suregerydetails.setSurgerlookupid(23L);
		Familyhistory familyhistory=new Familyhistory();
		familyhistory.setAilment("test");
		familyhistory.setRelationship("brother");
		familyhistory.setUserId(141l);
		Socialhistory socialhistory=new Socialhistory();
		socialhistory.setAbortions("test");
		socialhistory.setUserId(2L);
		socialhistory.setAllergies("test1");
		socialhistory.setDeliveries(1);
		socialhistory.setMaritalStatus("yes");
		Personalsymptoms personalsymptoms=new Personalsymptoms();
		personalsymptoms.setUserId(2L);
		personalsymptoms.setSymptomsdesc("test");
		Questionairre questionairre=new Questionairre();
		questionairre.setAlcohol("yes");
		questionairre.setDrugs("no");
		questionairre.setUserId(2L);//socialhistory
		medicalHistoryDto.setSocialhistory(Arrays.asList(socialhistory));
		medicalHistoryDto.setFamilyhistory(Arrays.asList(familyhistory));
		medicalHistoryDto.setQuestionairre(Arrays.asList(questionairre));
		medicalHistoryDto.setSuregerydetails(Arrays.asList(suregerydetails));
		medicalHistoryDto.setPersonalsymptoms(Arrays.asList(personalsymptoms));
		medicalHistory.saveMedicalHistory(medicalHistoryDto);
	}

	@Test
	public void getAllMedicalhistoryDetail() {
		Long userId = 141L;
		MedicalHistoryDto medicalHistoryDto= medicalHistory.getAllMedicalhistoryDetail(userId);
		assertNotNull(medicalHistoryDto);
	}

}
