package org.secondopinion.batch.request;

import java.util.ArrayList;
import java.util.Collection;

import org.secondopinion.batch.IBatchDequeCallBack;
import org.secondopinion.common.dto.BulkRequest;
import org.secondopinion.common.dto.RequestDTO;

import org.secondopinion.filter.interceptors.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestInfoBatchDequeImpl implements IBatchDequeCallBack<RequestDTO> {
	private static final Logger LOGGER = LoggerFactory.getLogger(IBatchDequeCallBack.class);

	@Autowired
	private RequestService requestService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void processCollection(Collection<RequestDTO> batchedData) {

		try {
			BulkRequest bulkRequest = new BulkRequest();
			bulkRequest.setRequests((ArrayList) batchedData);
			requestService.saveElasticSearchRequestListOfObject(bulkRequest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);

		}

	}

}
