package org.secondopinions.elasticsearch.repository;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.secondopinions.elasticsearch.model.Medicaltest;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MedicaltestRepository extends ElasticsearchCrudRepository<Medicaltest, String> {
	 List<Medicaltest> search(QueryBuilder query);
}
