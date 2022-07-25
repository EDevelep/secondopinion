package org.secondopinions.elasticsearch.repository;

import java.util.List;
import org.elasticsearch.index.query.QueryBuilder;
import org.secondopinions.elasticsearch.model.Diagnosticcenter;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticsearchDiagnosticcenterRepository
    extends ElasticsearchRepository<Diagnosticcenter, String> {
  List<Diagnosticcenter> search(QueryBuilder query);

  Diagnosticcenter findByDiagnosticcenterId(Long diagnosticcenterId);

  void deleteByDiagnosticcenterId(Long diagnosticcenterId);

}
