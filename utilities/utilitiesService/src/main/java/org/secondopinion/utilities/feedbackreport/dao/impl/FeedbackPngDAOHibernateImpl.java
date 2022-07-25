package org.secondopinion.utilities.feedbackreport.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import org.secondopinion.utilities.feedbackreport.dto.FeedbackPng;

@Component
public class FeedbackPngDAOHibernateImpl extends BaseFeedbackPngDAOHibernate{
	
	private static final String deleteFeedbackPngSql = "delete from FeedbackPng where feedbackPngId=:feedbackPngId";
	
	@Override
	@Transactional
	public void delete(FeedbackPng feedbackPng) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("feedbackPngId", feedbackPng.getFeedbackPngId());
		executeQuery(deleteFeedbackPngSql, params);
	}
}