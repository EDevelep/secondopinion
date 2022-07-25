package org.secondopinion.patient.service.impl;

import java.util.List;
import java.util.Objects;

import org.secondopinion.patient.dao.FamilyhistoryDAO;
import org.secondopinion.patient.dao.PersonalsymptomsDAO;
import org.secondopinion.patient.dao.QuestionairreDAO;
import org.secondopinion.patient.dao.SocialhistoryDAO;
import org.secondopinion.patient.dao.SurgerydetailsDAO;
import org.secondopinion.patient.dto.Familyhistory;
import org.secondopinion.patient.dto.MedicalHistoryDto;
import org.secondopinion.patient.dto.Personalsymptoms;
import org.secondopinion.patient.dto.Questionairre;
import org.secondopinion.patient.dto.Socialhistory;
import org.secondopinion.patient.dto.Surgerydetails;
import org.secondopinion.patient.service.IMedicalHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicalHistoryServiceImpl implements IMedicalHistory {
	@Autowired
	private QuestionairreDAO questionairreDAO;
	@Autowired
	private SurgerydetailsDAO surgerydetailsDAO;
	@Autowired
	private SocialhistoryDAO socialhistoryDAO;
	@Autowired
	private PersonalsymptomsDAO personalsymptomsDAO;
	@Autowired
	private FamilyhistoryDAO faimFamilyhistoryDAO;

	@Override
	@Transactional
	public void saveMedicalHistory(MedicalHistoryDto medicalHistoryDto) {

		if (Objects.nonNull(medicalHistoryDto.getSuregerydetails())) {
			List<Surgerydetails> surgerydetails = medicalHistoryDto.getSuregerydetails();
			surgerydetails.stream().forEach(n -> n.setActive('Y'));
			surgerydetailsDAO.save(surgerydetails);
		}

		if (Objects.nonNull(medicalHistoryDto.getSocialhistory())) {
			List<Socialhistory> socialhistory = medicalHistoryDto.getSocialhistory();
			socialhistory.stream().forEach(n -> n.setActive('Y'));
			socialhistoryDAO.save(socialhistory);
		}

		if (Objects.nonNull(medicalHistoryDto.getQuestionairre())) {
			List<Questionairre> questionairre = medicalHistoryDto.getQuestionairre();
			questionairre.stream().forEach(n -> n.setActive('Y'));
			questionairreDAO.save(questionairre);
		}
		if (Objects.nonNull(medicalHistoryDto.getFamilyhistory())) {
			List<Familyhistory> familyhistory = medicalHistoryDto.getFamilyhistory();
			familyhistory.stream().forEach(n -> n.setActive('Y'));
			faimFamilyhistoryDAO.save(familyhistory);

		}
		if (Objects.nonNull(medicalHistoryDto.getPersonalsymptoms())) {
			List<Personalsymptoms> personalsymptoms = medicalHistoryDto.getPersonalsymptoms();
			personalsymptoms.stream().forEach(n -> n.setActive('Y'));
			personalsymptomsDAO.save(personalsymptoms);
		}
	}

	public MedicalHistoryDto getAllMedicalhistoryDetail(Long userid) {
		MedicalHistoryDto medicalHistoryDTO = new MedicalHistoryDto();
		medicalHistoryDTO.setSuregerydetails(surgerydetailsDAO.findSurgerydetailsByUserId(userid));
		medicalHistoryDTO.setSocialhistory(socialhistoryDAO.findSocialhistoryByUserId(userid));
		medicalHistoryDTO.setPersonalsymptoms(personalsymptomsDAO.findFamilyhistoryByUserId(userid));
		medicalHistoryDTO.setFamilyhistory(faimFamilyhistoryDAO.findPersonalsymptomsByUserId(userid));
		medicalHistoryDTO.setQuestionairre(questionairreDAO.findQuestionairreByUserId(userid));
		return medicalHistoryDTO;

	}

	@Override
	@Transactional
	public void deleteFamilyHistory(Long familyHistoryId) {
		Familyhistory familyhistory = faimFamilyhistoryDAO.findById(familyHistoryId);
		if (Objects.isNull(familyhistory)) {
			throw new IllegalArgumentException("No Familyhistory Found..");
		}
		familyhistory.setActive('N');
		faimFamilyhistoryDAO.save(familyhistory);
	}

	@Override
	@Transactional
	public void deleteSurgerydetails(Long surgerydetailsId) {
		Surgerydetails surgerydetails = surgerydetailsDAO.findById(surgerydetailsId);
		if (Objects.isNull(surgerydetails)) {
			throw new IllegalArgumentException("No Surgerydetails Found..");
		}
		surgerydetails.setActive('N');
		surgerydetailsDAO.save(surgerydetails);
	}

}
