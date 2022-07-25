package org.secondopinions.elasticsearch.serviceImpl;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.secondopinions.elasticsearch.model.Doctor;
import org.secondopinions.elasticsearch.model.Specialization;
import org.secondopinions.elasticsearch.repository.ElasticsearchDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {

	@Autowired
	private ElasticsearchDoctorRepository doctorRepository;
	@Autowired
	private SpecializationService specializationService;

	public String saveDoctor(Doctor doctor) {
		Specialization specialization = new Specialization();

		doctor.setId(UUID.randomUUID().toString());
		doctorRepository.save(doctor);
		specialization.setDoctorspecialization(doctor.getDoctorspecialization());
		specializationService.saveSpecialization(specialization);

		return "Doctor Saved.";
	}

	public List<Doctor> search(String keywords) {
		MatchQueryBuilder searchdoctor = QueryBuilders.matchQuery("doctorspecialization", keywords);
		List<Doctor> doctors = this.doctorRepository.search(searchdoctor);
		return doctors;
	}
	
	public List<Doctor> searchDocotor(String keywords) {
		MatchQueryBuilder searchdoctor = QueryBuilders.matchQuery("firstName", keywords);
		List<Doctor> doctors = this.doctorRepository.search(searchdoctor);
		return doctors;
	}

	public void delete(String id) {
		doctorRepository.deleteById(id);

	}
}
