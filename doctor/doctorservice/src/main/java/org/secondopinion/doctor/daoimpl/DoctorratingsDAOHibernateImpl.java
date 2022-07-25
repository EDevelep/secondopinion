package org.secondopinion.doctor.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.common.dto.RatingsDTO;
import org.secondopinion.doctor.domain.BaseDoctorratings;
import org.secondopinion.doctor.domain.DoctorRatingDTO;
import org.secondopinion.doctor.dto.Doctorratings;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DoctorratingsDAOHibernateImpl extends BaseDoctorratingsDAOHibernate {

	private static final String RATINGS_SQL = "select sum(rating) totalRating, count(*) numberOfRatings from doctorratings where doctorid = :DOCTOR_ID";

	public RatingsDTO getRatingValues(Long doctorid) {
		Map<String, Object> params = new HashMap<>();

		params.put("DOCTOR_ID", doctorid);

		List<RatingsDTO> ratings = findBySqlQuery(RATINGS_SQL, params, RatingsDTO.class);

		return ratings.get(0);
	}

	@Override
	@Transactional
	public void save(Doctorratings doctorratings) {

		if (Objects.isNull(doctorratings.getDoctorratingid())) {
			doctorratings.setActive('Y');
		}

		super.save(doctorratings);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Doctorratings>> getDoctorratingSerchCritera(DoctorRatingDTO doctorrating) {
		List<Criterion> criterions = new ArrayList<>();

		if (Objects.nonNull(doctorrating.getAppointmentid())) {
			criterions.add(Restrictions.eq(BaseDoctorratings.FIELD_appointmentid, doctorrating.getAppointmentid()));
		}

		if (Objects.nonNull(doctorrating.getPatientid())) {
			criterions.add(Restrictions.eq(BaseDoctorratings.FIELD_patientid, doctorrating.getPatientid()));
		}

		if (Objects.nonNull(doctorrating.getDoctorid())) {
			criterions.add(Restrictions.eq(BaseDoctorratings.FIELD_doctorid, doctorrating.getDoctorid()));
		}

		if (Objects.nonNull(doctorrating.getFeedback())) {
			criterions.add(Restrictions.eq(BaseDoctorratings.FIELD_feedback, doctorrating.getFeedback()));
		}

		if (Objects.nonNull(doctorrating.getRating())) {
			criterions.add(Restrictions.eq(BaseDoctorratings.FIELD_rating, doctorrating.getRating()));
		}
		if (Objects.nonNull(doctorrating.getActive())) {
			criterions.add(Restrictions.eq(BaseDoctorratings.FIELD_active, 'Y'));
		}

		return findByCrieria(criterions, null, doctorrating.getPagenumber(), doctorrating.getMaxresult());

	}

	@Override
	@Transactional(readOnly = true)
	public List<Doctorratings> findByDoctorratings(Long doctorId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseDoctorratings.FIELD_doctorid, doctorId));
		criterions.add(Restrictions.eq(BaseDoctorratings.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Doctorratings> findByDoctorratings(List<Long> doctorId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.in(BaseDoctorratings.FIELD_doctorid, doctorId));
		criterions.add(Restrictions.eq(BaseDoctorratings.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
}