package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.common.dto.RatingsDTO;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterratings;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterratingsDTO;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.lang.Collections;

@Repository
public class DiagnosticcenterratingsDAOHibernateImpl extends BaseDiagnosticcenterratingsDAOHibernate{

	
	private static final String RATINGS_SQL = "select sum(rating) totalRating, count(*) numberOfRatings from diagnosticcenterratings where diagnosticcenterId = :DIAGNOSTICCENTER_ID";
	
	@Override
	@Transactional(readOnly=true)
	public RatingsDTO getRatingValues(Long diagnosticcenterId) {
		Map<String, Object> params = new HashMap<>();

		params.put("DIAGNOSTICCENTER_ID", diagnosticcenterId);

		List<RatingsDTO> ratings = findBySqlQuery(RATINGS_SQL, params, RatingsDTO.class);
		
		if(Collections.isEmpty(ratings)) {
			return null;
		}

		return ratings.get(0);
	}

	@Override
	@Transactional(readOnly=true)
	public Response<List<Diagnosticcenterratings>> getRatingsByDiagnosticcenterid(Long diagnosticcenterId, Integer pageNum,
			Integer maxResults) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Diagnosticcenterratings.FIELD_diagnosticcenterId, diagnosticcenterId));
		List<Order> orders = Arrays.asList(Order.desc(Diagnosticcenterratings.FIELD_diagnosticcenterratingid));
		return findByCrieria(criterions, orders, pageNum, maxResults);
	}
	
	@Override
	@Transactional
	public void save(Diagnosticcenterratings diagnosticcenterratings) {
		if(Objects.isNull(diagnosticcenterratings.getDiagnosticcenterratingid())) {
			diagnosticcenterratings.setActive('Y');
		}
		super.save(diagnosticcenterratings);
	}
	@Override
	@Transactional(readOnly = true)
	public Response<List<Diagnosticcenterratings>> getDiagnosticcenterratingsSerchCritera(DiagnosticcenterratingsDTO diagnosticcenterratingsDTO) {
		List<Criterion> criterions = new ArrayList<>();

		if (Objects.nonNull( diagnosticcenterratingsDTO.getDiagnosticcenterid())) {
			criterions.add(Restrictions.eq(Diagnosticcenterratings.FIELD_diagnosticcenterId, diagnosticcenterratingsDTO.getDiagnosticcenterid()));
		}

		if (Objects.nonNull(diagnosticcenterratingsDTO.getPatientid())) {
			criterions.add(Restrictions.eq(Diagnosticcenterratings.FIELD_patientid, diagnosticcenterratingsDTO.getPatientid()));
		}

		if (Objects.nonNull( diagnosticcenterratingsDTO.getDiagnosticcenterratingid())) {
			criterions.add(Restrictions.eq(Diagnosticcenterratings.FIELD_diagnosticcenterratingid, diagnosticcenterratingsDTO.getDiagnosticcenterratingid()));
		}

		if (Objects.nonNull(diagnosticcenterratingsDTO.getFeedback())) {
			criterions.add(Restrictions.eq(Diagnosticcenterratings.FIELD_feedback, diagnosticcenterratingsDTO.getFeedback()));
		}
		
		if (Objects.nonNull( diagnosticcenterratingsDTO.getRating())) {
			criterions.add(Restrictions.eq(Diagnosticcenterratings.FIELD_rating, diagnosticcenterratingsDTO.getRating()));
		}
		
			criterions.add(Restrictions.eq(Diagnosticcenterratings.FIELD_active, 'Y'));
		

		return findByCrieria(criterions, null, diagnosticcenterratingsDTO.getPagenumber(), diagnosticcenterratingsDTO.getMaxresult());

	}

}