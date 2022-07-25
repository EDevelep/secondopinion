package org.secondopinions.elasticsearch.dto;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class DiagnosticcenterDTO {

	private String diagnosticcentename;
	private String lastName;
	private Double latitude;
	private Double longitude;

	public String getDiagnosticcentename() {
		return diagnosticcentename;
	}

	public void setDiagnosticcentename(String diagnosticcentename) {
		this.diagnosticcentename = diagnosticcentename;
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

	public static SearchRequest builsearchRequest(String diagnosticcentename) {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("diagnosticcenter");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("diagnosticcentename", diagnosticcentename);
		matchQueryBuilder.fuzziness(Fuzziness.AUTO);
		searchRequest.source(searchSourceBuilder);
		searchSourceBuilder.query(matchQueryBuilder);
		return searchRequest;
	}
}
