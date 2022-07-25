package org.secondopinion.utilities.feedbackreport.service.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import org.secondopinion.configurations.CustomRestTemlpateConfig;
import org.secondopinion.request.Request;
import org.secondopinion.request.Response;
import org.secondopinion.utilities.feedbackreport.dao.FeedbackLoopDAO;
import org.secondopinion.utilities.feedbackreport.dao.FeedbackPngDAO;
import org.secondopinion.utilities.feedbackreport.dto.FeedbackLoop;
import org.secondopinion.utilities.feedbackreport.dto.FeedbackPng;
import org.secondopinion.utilities.jobs.dto.FeedbackRequestDTO;
import org.secondopinion.utils.file.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FeedbackLoopService {
	private static final Logger logger = LoggerFactory.getLogger(FeedbackLoopService.class);
	@Value("${username_clientname_endpoint_url}")
	private String username_clientname_endpoint_url;

	@Autowired
	private FeedbackLoopDAO feedbackLoopDAO;

	@Autowired
	private FeedbackPngDAO feedbackPngDAO;

	@Autowired
	private CustomRestTemlpateConfig customRestTemlpateConfig;

	public FeedbackLoopDAO getFeedbackLoopDAO() {
		return feedbackLoopDAO;
	}

	public void setFeedbackLoopDAO(FeedbackLoopDAO feedbackLoopDAO) {
		this.feedbackLoopDAO = feedbackLoopDAO;
	}

	@Transactional
	public void save(FeedbackLoop feedbackLoop, MultipartFile screenshot) throws IOException {
		feedbackLoop = buildFeedbackPngForSave(feedbackLoop, screenshot);
		feedbackLoopDAO.save(feedbackLoop);
	}

	@Transactional(readOnly = true)
	public FeedbackLoop getFeedBackLoopById(Long feedBackId) throws IOException {
		FeedbackLoop feedbackLoop = feedbackLoopDAO.findById(feedBackId);
		return buildFeedbackPngForFetch(feedbackLoop);
	}

	@Transactional(readOnly = true)
	public Collection<FeedbackLoop> getAllFeedBackLoops() {
		return feedbackLoopDAO.findAll();
	}

	@Transactional(readOnly = true)
	public List<FeedbackLoop> getAllFeedBackLoopsByStatusUSerCompany(FeedbackRequestDTO feedbackRequestDTO)
			throws Exception {

		List<FeedbackLoop> feedbackLoopList = feedbackLoopDAO.findByStatusAndUser(feedbackRequestDTO);

		Set<Long> userIds = feedbackLoopList.stream().map(fl -> fl.getUserId()).collect(Collectors.toSet());

		feedbackLoopList.stream().map(fl -> {
			try {
				return buildFeedbackPngForFetch(fl);
			} catch (IOException e) {

			}
			return fl;
		}).collect(Collectors.toList());
		// buildFeedbackWithUserIds(feedbackLoopList, userIds) ;
		return feedbackLoopList;
	}

	private void buildFeedbackWithUserIds(List<FeedbackLoop> feedbackLoopList, Set<Long> userIds) throws Exception {

		Map<String, Map<String, String>> userIdUserNameClientNameMap = getForObject(username_clientname_endpoint_url,
				userIds);
		if (CollectionUtils.isEmpty(userIdUserNameClientNameMap)) {
			return;
		}
		for (FeedbackLoop fl : feedbackLoopList) {
			Map<String, String> usernameClientNameMap = userIdUserNameClientNameMap.get(String.valueOf(fl.getUserId()));
			if (usernameClientNameMap != null) {
				for (Iterator<Entry<String, String>> iterator = usernameClientNameMap.entrySet().iterator(); iterator
						.hasNext();) {
					Entry<String, String> usernameClientNameEntry = iterator.next();
					fl.setClientName(usernameClientNameEntry.getValue());
					fl.setUserName(usernameClientNameEntry.getKey());
				}
			}
		}

	}

	private FeedbackLoop buildFeedbackPngForSave(FeedbackLoop feedbackLoop, MultipartFile screenshot)
			throws IOException {
		FeedbackPng feedbackPng = feedbackLoop.getFeedbackPng();
		if (Objects.isNull(screenshot)) {
			return feedbackLoop;
		}
		if (feedbackLoop.getFeedbackLoopId() != null) {
			feedbackLoop = getFeedBackLoopById(feedbackLoop.getFeedbackLoopId());
		}
		feedbackPng = feedbackLoop.getFeedbackPng();
		if (Objects.isNull(feedbackPng)) {
			feedbackPng = new FeedbackPng();
		}

		feedbackPng = buildFeedbackPngObject(feedbackLoop.getFeedbackLoopId(), screenshot, feedbackPng);
		feedbackLoop.setFeedbackPng(feedbackPng);
		return feedbackLoop;
	}

	private FeedbackLoop buildFeedbackPngForFetch(FeedbackLoop feedbackLoop) throws IOException {
		if (Objects.isNull(feedbackLoop)) {
			return feedbackLoop;
		}
		FeedbackPng feedbackPng = feedbackPngDAO.findOneByProperty(FeedbackPng.FIELD_feedbackId,
				feedbackLoop.getFeedbackLoopId());// feedbackLoop.getFeedbackPng();
		if (Objects.isNull(feedbackPng)) {
			return feedbackLoop;
		}
		if (Objects.nonNull(feedbackPng.getScreenshot())) {
			try {
				feedbackPng.setScreenshot(FileUtil.decompressBytes(feedbackPng.getScreenshot(), 1024));
			} catch (Exception e) {
				logger.error("unable decompress the image.");
			}
		}
		feedbackLoop.setFeedbackPng(feedbackPng);
		return feedbackLoop;
	}

	private FeedbackPng buildFeedbackPngObject(Long feedbackId, MultipartFile screenshot, FeedbackPng feedbackPng)
			throws IOException {
		feedbackPng.setFileName(screenshot.getOriginalFilename());
		feedbackPng.setContentType(screenshot.getContentType());
		feedbackPng.setFeedbackId(feedbackId);
		feedbackPng.setScreenshot(FileUtil.compressBytes(screenshot.getBytes(), 1024));
		return feedbackPng;
	}

	// ====================================================================

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, Map<String, String>> getForObject(String uri, Set<Long> userIds) throws Exception {
		Request request = new Request();
		request.setUserIds(userIds);

		Response response = (Response) customRestTemlpateConfig.callRestAPI(request, uri, HttpMethod.POST,
				Collection.class);

		if (Objects.isNull(response.getData())) {
			return new HashMap<String, Map<String, String>>();
		}
		LinkedHashMap<String, Map<String, String>> linkedHashMap = (LinkedHashMap<String, Map<String, String>>) response
				.getData();
		return linkedHashMap;

	}
}
