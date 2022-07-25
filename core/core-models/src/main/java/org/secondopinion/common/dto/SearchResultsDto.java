package org.secondopinion.common.dto;

import java.util.List;

public class SearchResultsDto<T> {
	private long totalHits;
	private List<T> results;

	public long getTotalHits() {
		return totalHits;
	}

	public void setTotalHits(long totalHits) {
		this.totalHits = totalHits;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "SearchResultsDto [totalHits=" + totalHits + ", results=" + results + "]";
	}
	
	

}
