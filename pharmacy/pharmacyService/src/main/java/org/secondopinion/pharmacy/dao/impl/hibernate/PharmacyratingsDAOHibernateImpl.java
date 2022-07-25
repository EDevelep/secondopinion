package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.common.dto.RatingsDTO;
import org.secondopinion.pharmacy.dto.Pharmacyratings;
import org.secondopinion.pharmacy.dto.PharmacyratingsDTO;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PharmacyratingsDAOHibernateImpl extends BasePharmacyratingsDAOHibernate{

	private static final String RATINGS_SQL = "select sum(rating) totalRating, count(*) numberOfRatings from pharmacyratings where pharmacyid = :PHARMACY_ID";
	
	@Override
	@Transactional(readOnly=true)
	public RatingsDTO getRatingValues(Long pharmacyid) {
		Map<String, Object> params = new HashMap<>();

		params.put("PHARMACY_ID", pharmacyid);

		List<RatingsDTO> ratings = findBySqlQuery(RATINGS_SQL, params, RatingsDTO.class);

		return ratings.get(0);
	}

	@Override
	@Transactional(readOnly=true)
	public Response<List<Pharmacyratings>> getRatingsByPharmacyid(Long pharmacyId, Integer pageNum,
			Integer maxResults) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Pharmacyratings.FIELD_pharmacyaddressId, pharmacyId));
		List<Order> orders = Arrays.asList(Order.desc(Pharmacyratings.FIELD_pharmacyratingid));
		return findByCrieria(criterions, orders, pageNum, maxResults);
	}
	
	@Override
	@Transactional
	public void save(Pharmacyratings pharmacyratings) {
		if(Objects.isNull(pharmacyratings.getPharmacyratingid())) {
			pharmacyratings.setActive('Y');
		}
		super.save(pharmacyratings);
	}
	@Override
	@Transactional(readOnly = true)
	public Response<List<Pharmacyratings>> getPharmacyratingsSerchCritera(PharmacyratingsDTO pharmacyratingsDTO) {
		List<Criterion> criterions = new ArrayList<>();

		if (Objects.nonNull( pharmacyratingsDTO.getPharmacyid())) {
			criterions.add(Restrictions.eq(Pharmacyratings.FIELD_pharmacyaddressId, pharmacyratingsDTO.getPharmacyid()));
		}

		if (Objects.nonNull(pharmacyratingsDTO.getPatientid())) {
			criterions.add(Restrictions.eq(Pharmacyratings.FIELD_patientid, pharmacyratingsDTO.getPatientid()));
		}

		if (Objects.nonNull( pharmacyratingsDTO.getPharmacyratingid())) {
			criterions.add(Restrictions.eq(Pharmacyratings.FIELD_pharmacyratingid, pharmacyratingsDTO.getPharmacyratingid()));
		}

		if (Objects.nonNull(pharmacyratingsDTO.getFeedback())) {
			criterions.add(Restrictions.eq(Pharmacyratings.FIELD_feedback, pharmacyratingsDTO.getFeedback()));
		}
		
		if (Objects.nonNull( pharmacyratingsDTO.getRating())) {
			criterions.add(Restrictions.eq(Pharmacyratings.FIELD_rating, pharmacyratingsDTO.getRating()));
		}
		
			criterions.add(Restrictions.eq(Pharmacyratings.FIELD_active, 'Y'));
		

		return findByCrieria(criterions, null, pharmacyratingsDTO.getPagenumber(), pharmacyratingsDTO.getMaxresult());

	}

}