package org.secondopinions.elasticsearch.serviceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.secondopinions.elasticsearch.model.ExceptionIndex;

import org.secondopinions.elasticsearch.repository.ElasticsearchExceptionrepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExceptionIndexService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionIndexService.class);
	@Autowired
	private ElasticsearchExceptionrepository elasticsearchExceptionrepository;

	public void saveExceptionIndex(ExceptionIndex request) {
		try {
			request.setId(UUID.randomUUID().toString());
			elasticsearchExceptionrepository.save(request);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

	}

	public List<ExceptionIndex> getExceptionIndex() {
		List<ExceptionIndex> result = null;
		try {
			Iterable<ExceptionIndex> itrable = elasticsearchExceptionrepository.findAll();
			result = StreamSupport.stream(itrable.spliterator(), false).collect(Collectors.toList());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;

	}
}
