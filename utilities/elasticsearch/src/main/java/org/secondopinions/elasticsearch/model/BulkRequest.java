package org.secondopinions.elasticsearch.model;

import java.util.List;

public class BulkRequest {
	private List<Request> requests;

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

}
