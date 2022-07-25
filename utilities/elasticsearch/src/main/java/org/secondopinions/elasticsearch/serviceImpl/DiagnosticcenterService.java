package org.secondopinions.elasticsearch.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.secondopinions.elasticsearch.dto.GeoLocationDTO;
import org.secondopinions.elasticsearch.model.Diagnosticcenter;
import org.secondopinions.elasticsearch.model.DiagnosticcenterTest;
import org.secondopinions.elasticsearch.repository.ElasticsearchDiagnosticcenterRepository;
import org.secondopinions.elasticsearch.repository.ElasticsearchDiagnosticcenterTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

@Service
public class DiagnosticcenterService {

  @Autowired
  public ElasticsearchOperations elasticsearchTemplate;

  @Autowired
  private ElasticsearchDiagnosticcenterTestRepository elasticsearchDiagnosticcentertestRepository;
  @Autowired
  private ElasticsearchDiagnosticcenterRepository diagnosticcenterRepository;

  public Iterable<Diagnosticcenter> listAll() {
    return this.diagnosticcenterRepository.findAll();
  }

  public void save(Diagnosticcenter diagnosticcenter) {
    Diagnosticcenter dbdiagnosticcenter = diagnosticcenterRepository
        .findByDiagnosticcenterId(diagnosticcenter.getDiagnosticcenterId());

    if (Objects.nonNull(dbdiagnosticcenter)) {
      dbdiagnosticcenter.setLatitude(diagnosticcenter.getLatitude());
      dbdiagnosticcenter.setLongitude(diagnosticcenter.getLongitude());
      dbdiagnosticcenter.setGeoPoint(
          new GeoPoint(diagnosticcenter.getLatitude(), diagnosticcenter.getLongitude()));
      diagnosticcenterRepository.save(dbdiagnosticcenter);
    } else {
      Diagnosticcenter diagnosticcenter2 = Diagnosticcenter.build(diagnosticcenter);
      diagnosticcenterRepository.save(diagnosticcenter2);
    }


  }

  public DiagnosticcenterTest save(DiagnosticcenterTest diagnosticcentertest) {
    diagnosticcentertest.setId(UUID.randomUUID().toString());
    return this.elasticsearchDiagnosticcentertestRepository.save(diagnosticcentertest);
  }

  public List<Diagnosticcenter> search(String keywords) {
    MatchQueryBuilder searchByCountries =
        QueryBuilders.matchQuery("diagnosticcentername", keywords);
    List<Diagnosticcenter> diagnosticcenter =
        this.diagnosticcenterRepository.search(searchByCountries);

    return diagnosticcenter;
  }

  public List<DiagnosticcenterTest> searchDiagnosticcenterTest(String keywords) {
    MatchQueryBuilder searchByCountries =
        QueryBuilders.matchQuery("diagnosticcentertestname", keywords);
    List<DiagnosticcenterTest> diagnosticcenter =
        this.elasticsearchDiagnosticcentertestRepository.search(searchByCountries);

    return diagnosticcenter;
  }

  public List<Diagnosticcenter> getLocationMembers(GeoLocationDTO geoLocationDTO) {
    GeoDistanceQueryBuilder geoDistanceQueryBuilder = QueryBuilders.geoDistanceQuery("geoPoint")
        .point(geoLocationDTO.getLatitude(), geoLocationDTO.getLongitude())
        .distance(2, DistanceUnit.KILOMETERS);
    SearchQuery searchQuery =
        new NativeSearchQueryBuilder().withFilter(geoDistanceQueryBuilder).build();
    List<Diagnosticcenter> diagnosticcenters =
        elasticsearchTemplate.queryForList(searchQuery, Diagnosticcenter.class);
    return diagnosticcenters;
  }

  public void deleteByDiagnosticcenterId(Long diagnosticcenterId) {
    diagnosticcenterRepository.deleteByDiagnosticcenterId(diagnosticcenterId);
  }
}
