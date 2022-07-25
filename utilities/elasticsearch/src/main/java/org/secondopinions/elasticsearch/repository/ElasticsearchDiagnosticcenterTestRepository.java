package org.secondopinions.elasticsearch.repository;

import java.util.List;
import org.elasticsearch.index.query.QueryBuilder;
import org.secondopinions.elasticsearch.model.DiagnosticcenterTest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticsearchDiagnosticcenterTestRepository
    extends ElasticsearchRepository<DiagnosticcenterTest, String> {
  List<DiagnosticcenterTest> search(QueryBuilder query);


}
