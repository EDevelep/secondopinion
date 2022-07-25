package org.secondopinions.elasticsearch.model;

import org.secondopinions.elasticsearch.dto.PatientDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Setting(settingPath = "es-config/elastic-analyzer.json")
@Document(indexName = "patient", type = "patient", shards = 2)
public class Patient {


  @Id
  private String id;

  @Field(type = FieldType.Text, analyzer = "autocomplete_index",
      searchAnalyzer = "autocomplete_search")
  private String patientname;
  private String lastName;
  private Double latitude;
  private Double longitude;
  private Long userId;

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

  public String getPatientname() {
    return patientname;
  }

  public void setPatientname(String patientname) {
    this.patientname = patientname;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public static Patient build(PatientDTO patientDTO) {
    // TODO Auto-generated method stub
    return null;
  }



}
