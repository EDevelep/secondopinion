package org.secondopinion.patient.dto;

import java.util.List;

public class MedicalHistoryDto {
	
	private List<Surgerydetails> suregerydetails;
	private List<Familyhistory> familyhistory;
	private List<Socialhistory> socialhistory;
	private List<Personalsymptoms> personalsymptoms;
	private List<Questionairre> questionairre;
	public List<Surgerydetails> getSuregerydetails() {
		return suregerydetails;
	}
	public void setSuregerydetails(List<Surgerydetails> suregerydetails) {
		this.suregerydetails = suregerydetails;
	}
	public List<Familyhistory> getFamilyhistory() {
		return familyhistory;
	}
	public void setFamilyhistory(List<Familyhistory> familyhistory) {
		this.familyhistory = familyhistory;
	}
	public List<Socialhistory> getSocialhistory() {
		return socialhistory;
	}
	public void setSocialhistory(List<Socialhistory> socialhistory) {
		this.socialhistory = socialhistory;
	}
	public List<Personalsymptoms> getPersonalsymptoms() {
		return personalsymptoms;
	}
	public void setPersonalsymptoms(List<Personalsymptoms> personalsymptoms) {
		this.personalsymptoms = personalsymptoms;
	}
	public List<Questionairre> getQuestionairre() {
		return questionairre;
	}
	public void setQuestionairre(List<Questionairre> questionairre) {
		this.questionairre = questionairre;
	}
	

}
