package org.secondopinions.elasticsearch.serviceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.secondopinions.elasticsearch.dto.PatientDTO;
import org.secondopinions.elasticsearch.model.Patient;
import org.secondopinions.elasticsearch.repository.ElasticsearchPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PatientService {
	
	@Autowired
	private ElasticsearchPatientRepository patientRepository;
	
	public Iterable<Patient> listAll() {
		return this.patientRepository.findAll();
	}

	public Patient save(PatientDTO patientDTO) {
		Patient patient= Patient.build(patientDTO);
		return this.patientRepository.save(patient);
	}

	public List<String> search(String keywords) {
		MatchQueryBuilder searchpatient = QueryBuilders.matchQuery("patientname", keywords);
		List<Patient> patient = this.patientRepository.search(searchpatient);
		List<String> list = patient.stream().map(Patient::getPatientname).collect(Collectors.toList());
		return list;
	}
}
