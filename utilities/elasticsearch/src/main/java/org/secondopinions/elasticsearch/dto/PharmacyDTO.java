package org.secondopinions.elasticsearch.dto;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class PharmacyDTO {

	
	private String pharmacyname;
	private String fristName;
	private String lastName;
	private Double latitude;
	private Double longitude;
	private Double km;
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPharmacyname() {
		return pharmacyname;
	}
	public void setPharmacyname(String pharmacyname) {
		this.pharmacyname = pharmacyname;
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
	public String getFristName() {
		return fristName;
	}
	public void setFristName(String fristName) {
		this.fristName = fristName;
	}
	public Double getKm() {
		return km;
	}
	public void setKm(Double km) {
		this.km = km;
	}
	public static SearchRequest builsearchRequest(String pharmacyname) {
		SearchRequest searchRequest=new SearchRequest();
		searchRequest.indices("pharmacy");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("pharmacyname", pharmacyname);
		matchQueryBuilder.fuzziness(Fuzziness.AUTO);
		searchRequest.source(searchSourceBuilder);
		searchSourceBuilder.query(matchQueryBuilder);
		return searchRequest;
	}
	
}
