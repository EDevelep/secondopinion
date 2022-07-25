package org.secondopinions.elasticsearch.repository;


import org.secondopinions.elasticsearch.model.Request;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RequestResponseRepository extends ElasticsearchRepository<Request, String> {

}
