package org.secondopinions.elasticsearch.repository;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.secondopinions.elasticsearch.model.Patient;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ElasticsearchPatientRepository extends ElasticsearchRepository<Patient, String>{
	List<Patient> search(QueryBuilder query);
}
