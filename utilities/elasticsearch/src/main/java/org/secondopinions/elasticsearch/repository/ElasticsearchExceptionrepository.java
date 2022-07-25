package org.secondopinions.elasticsearch.repository;

import org.secondopinions.elasticsearch.model.ExceptionIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ElasticsearchExceptionrepository  extends ElasticsearchRepository<ExceptionIndex, String>{

}
