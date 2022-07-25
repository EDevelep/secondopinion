package org.secondopinions.elasticsearch.serviceImpl;

import java.util.UUID;

import org.secondopinions.elasticsearch.model.BulkRequest;
import org.secondopinions.elasticsearch.model.Request;
import org.secondopinions.elasticsearch.repository.RequestResponseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestResponseServiceImp {
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestResponseServiceImp.class);
	@Autowired
	private RequestResponseRepository requestResponseRepository;

	
	public void saveRequestResponse(Request request) {
		try {
			request.setId(UUID.randomUUID().toString());
			requestResponseRepository.save(request);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

	}

	public void saveRequestResponse(BulkRequest bulkRequest) {
		try {
			bulkRequest.getRequests().stream().forEach(n->n.setId(UUID.randomUUID().toString()));
			requestResponseRepository.saveAll(bulkRequest.getRequests());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

	}

}
