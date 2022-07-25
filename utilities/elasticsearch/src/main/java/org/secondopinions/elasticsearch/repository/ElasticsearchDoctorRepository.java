package org.secondopinions.elasticsearch.repository;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.secondopinions.elasticsearch.model.Doctor;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ElasticsearchDoctorRepository extends ElasticsearchRepository<Doctor, String> {
	 List<Doctor> search(QueryBuilder query);



	Doctor findBydoctorId(Long doctorId);
}
