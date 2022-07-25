package org.secondopinion.utilities.feedbackreport.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.secondopinion.utilities.feedbackreport.dao.FeedbackPngDAO;
import org.secondopinion.utilities.feedbackreport.dto.FeedbackLoop;
import org.secondopinion.utilities.feedbackreport.dto.FeedbackPng;
import org.secondopinion.utilities.jobs.dto.FeedbackRequestDTO;

@Repository
public class FeedbackLoopDAOHibernateImpl extends BaseFeedbackLoopDAOHibernate {

	@Autowired
	private FeedbackPngDAO feedbackPngDAO;

	@Override
	@Transactional(readOnly = true)
	public List<FeedbackLoop> findByStatus(String status) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(FeedbackLoop.FIELD_status, status));

		Order feedBackOrder = Order.desc(FeedbackLoop.FIELD_feedbackLoopId);
		return findByCrieria(criterions, feedBackOrder);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FeedbackLoop> findByStatusAndUser(FeedbackRequestDTO feedbackRequestDTO) {

		if (feedbackRequestDTO.isAllToView()) {
			return findAll();
		}

		List<Criterion> criterions = new ArrayList<>();
		buildCriterion(FeedbackLoop.FIELD_clientId, feedbackRequestDTO.getClientId(), criterions);
		buildCriterion(FeedbackLoop.FIELD_userId, feedbackRequestDTO.getUserId(), criterions);
		buildCriterion(FeedbackLoop.FIELD_status, feedbackRequestDTO.getStatus(), criterions);
		buildCriterion(FeedbackLoop.FIELD_patientName, feedbackRequestDTO.getPatientName(), criterions);
		Order feedBackOrder = Order.desc(FeedbackLoop.FIELD_feedbackLoopId);
		return findByCrieria(criterions, feedBackOrder);
	}

	@Override
	public List<FeedbackLoop> findByUserAndCompany(Long companyId, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void save(FeedbackLoop feedbackLoop) {
		super.save(feedbackLoop);

		FeedbackPng feedbackPng = feedbackLoop.getFeedbackPng();
		if (feedbackPng != null) {
			feedbackPng.setFeedbackId(feedbackLoop.getFeedbackLoopId());
			feedbackPngDAO.save(feedbackPng);
		}
	}

	private <T> void buildCriterion(String colmunName, T value, List<Criterion> criterions) {
		if (Objects.isNull(value)) {
			return;
		}
		criterions.add(Restrictions.eq(colmunName, value));
	}
}