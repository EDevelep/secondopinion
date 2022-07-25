package org.secondopinion.common.dto;

import java.util.List;

public class BulkRequest {

	private List<RequestDTO> requests;

	public List<RequestDTO> getRequests() {
		return requests;
	}

	public void setRequests(List<RequestDTO> requests) {
		this.requests = requests;
	}
}
