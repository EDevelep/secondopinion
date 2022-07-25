package org.secondopinions.elasticsearch.dto;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class DoctorDTO {

	private String firstName;
	private String lastName;
	private Long doctorId;
	private Double latitude;
	private Double longitude;

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	private String doctorspecialization;
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getDoctorspecialization() {
		return doctorspecialization;
	}

	public void setDoctorspecialization(String doctorspecialization) {
		this.doctorspecialization = doctorspecialization;
	}

	public static SearchRequest builsearchRequest(String doctorspecialization) {
		SearchRequest searchRequest=new SearchRequest();
		searchRequest.indices("doctor");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("doctorspecialization", doctorspecialization);
		matchQueryBuilder.fuzziness(Fuzziness.AUTO);
		searchRequest.source(searchSourceBuilder);
		searchSourceBuilder.query(matchQueryBuilder);
		return searchRequest;
	}
}
