package org.secondopinions.elasticsearch.model;

import java.util.UUID;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.annotations.Setting;



@Setting(settingPath = "es-config/elastic-analyzer.json")
@Document(indexName = "diagnosticcenter", type = "diagnosticcenter", shards = 2)
public class Diagnosticcenter {


  @Id
  private String id;
  @Field(type = FieldType.Text, analyzer = "autocomplete_index",
      searchAnalyzer = "autocomplete_search")
  private String diagnosticcentername;
  private String lastName;
  private Double latitude;
  private Double longitude;
  private Long diagnosticcenterId;
  private Long diagnosticcenteraddresId;
  private Long diagnosticcenrUserId;

  @GeoPointField
  private GeoPoint geoPoint;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public String getDiagnosticcentername() {
    return diagnosticcentername;
  }

  public void setDiagnosticcentername(String diagnosticcentername) {
    this.diagnosticcentername = diagnosticcentername;
  }

  public GeoPoint getGeoPoint() {
    return geoPoint;
  }

  public void setGeoPoint(GeoPoint geoPoint) {
    this.geoPoint = geoPoint;
  }

  public Long getDiagnosticcenterId() {
    return diagnosticcenterId;
  }

  public void setDiagnosticcenterId(Long diagnosticcenterId) {
    this.diagnosticcenterId = diagnosticcenterId;
  }

  public Long getDiagnosticcenteraddresId() {
    return diagnosticcenteraddresId;
  }

  public void setDiagnosticcenteraddresId(Long diagnosticcenteraddresId) {
    this.diagnosticcenteraddresId = diagnosticcenteraddresId;
  }

  public Long getDiagnosticcenrUserId() {
    return diagnosticcenrUserId;
  }

  public void setDiagnosticcenrUserId(Long diagnosticcenrUserId) {
    this.diagnosticcenrUserId = diagnosticcenrUserId;
  }

  public static Diagnosticcenter build(Diagnosticcenter diagnosticcenter) {
    Diagnosticcenter diagnosticcenter1 = new Diagnosticcenter();
    diagnosticcenter.setId(UUID.randomUUID().toString());
    diagnosticcenter1.setDiagnosticcenrUserId(diagnosticcenter.getDiagnosticcenrUserId());
    diagnosticcenter1.setDiagnosticcenteraddresId(diagnosticcenter.getDiagnosticcenteraddresId());
    diagnosticcenter1.setDiagnosticcenterId(diagnosticcenter.getDiagnosticcenterId());
    diagnosticcenter1.setDiagnosticcentername(diagnosticcenter.getDiagnosticcentername());
    diagnosticcenter1.setLastName(diagnosticcenter.getLastName());
    /*
     * diagnosticcenter1 .setGeoPoint(new GeoPoint(diagnosticcenter.getLatitude(),
     * diagnosticcenter.getLongitude()));
     */
    return diagnosticcenter1;
  }
}
