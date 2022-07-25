package org.secondopinions.elasticsearch.repository;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.secondopinions.elasticsearch.model.Medicine;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticsearchMedicineRepository extends ElasticsearchRepository<Medicine, String> {
	
	List<Medicine> search(QueryBuilder query);
	List<Medicine> findByName(String name);
    
     
}
