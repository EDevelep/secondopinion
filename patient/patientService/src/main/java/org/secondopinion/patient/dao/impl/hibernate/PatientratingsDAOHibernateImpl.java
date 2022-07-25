package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.patient.domain.BasePatientratings;
import org.secondopinion.patient.dto.Patientratings;
import org.secondopinion.patient.dto.PatientratingsSearchCriteria;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PatientratingsDAOHibernateImpl extends BasePatientratingsDAOHibernate{


	@Override
	@Transactional
	public void save(  Patientratings patientratings) throws DataAccessException {
		if(Objects.isNull(patientratings.getPatientratingid())) {
			patientratings.setActive('Y');
		}
		super.save(patientratings);
	}
	
	
	@Override
	public Response<List<Patientratings>> getRatingsByCriteria(PatientratingsSearchCriteria ratings) {
		List<Criterion> criterions = new ArrayList<>();
		if(Objects.nonNull(ratings.getPatientratingid())) {
			criterions.add(Restrictions.eq(BasePatientratings.FIELD_patientratingid, ratings.getPatientratingid()));
		}
		if(Objects.nonNull(ratings.getPatientid())) {
			criterions.add(Restrictions.eq(BasePatientratings.FIELD_patientid, ratings.getPatientid()));
		}
		
		if(Objects.nonNull(ratings.getRatingForEnum())) {
			criterions.add(Restrictions.eq(BasePatientratings.FIELD_ratingFor, ratings.getRatingForEnum().name()));
		}
		if(Objects.nonNull(ratings.getReferenceId())) {
			criterions.add(Restrictions.eq(BasePatientratings.FIELD_referenceId, ratings.getReferenceId()));
		}
		if(Objects.nonNull(ratings.getRating())) {
			criterions.add(Restrictions.eq(BasePatientratings.FIELD_rating, ratings.getRating()));
		}
		if(!StringUtils.isEmpty(ratings.getFeedback())) {
			criterions.add(Restrictions.ilike(BasePatientratings.FIELD_feedback, ratings.getFeedback(), MatchMode.ANYWHERE));
		}
		criterions.add(Restrictions.eq(BasePatientratings.FIELD_active, 'Y'));
		return findByCrieria(criterions, null, ratings.getPageNum(), ratings.getMaxResults());
	}


	@Override
	@Transactional
	public List<Patientratings> getRatingsBydoctorIds(List<Long> doctorIds) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.in(BasePatientratings.FIELD_doctorId, doctorIds));
		criterions.add(Restrictions.eq(BasePatientratings.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
}