package org.secondopinions.elasticsearch.repository;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.secondopinions.elasticsearch.model.Diagnosticcenter;
import org.secondopinions.elasticsearch.model.Specialization;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends ElasticsearchRepository<Specialization, String> {

	Specialization findBydoctorspecialization(String specialization);

	List<Specialization> search(QueryBuilder query);

}
