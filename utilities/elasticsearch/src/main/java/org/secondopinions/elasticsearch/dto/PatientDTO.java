package org.secondopinions.elasticsearch.dto;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class PatientDTO {
  private String userName;
  private String lastName;
  private Double latitude;
  private Double longitude;
  private Long userId;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public static SearchRequest builsearchRequest(String patientname) {
    SearchRequest searchRequest = new SearchRequest();
    searchRequest.indices("patient");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("patientname", patientname);
    matchQueryBuilder.fuzziness(Fuzziness.AUTO);
    searchRequest.source(searchSourceBuilder);
    searchSourceBuilder.query(matchQueryBuilder);
    return searchRequest;
  }
}
