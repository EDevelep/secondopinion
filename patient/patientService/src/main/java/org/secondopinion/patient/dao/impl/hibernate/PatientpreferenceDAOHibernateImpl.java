package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.regexp.recompile;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.patient.dto.Patientpreference;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class PatientpreferenceDAOHibernateImpl extends BasePatientpreferenceDAOHibernate {

	@Override
	@Transactional
	public void save(Patientpreference patientpreference) throws DataAccessException {
		if (Objects.isNull(patientpreference.getPatientpreferenceId())) {
			patientpreference.setActive('Y');
		}
		super.save(patientpreference);
	}

	@Override
	public List<Patientpreference> findPatientpreferenceBypatientid(Long patientid) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Patientpreference.FIELD_patientid, patientid));
		criterions.add(Restrictions.eq(Patientpreference.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	public List<Patientpreference> findPatientpreferenceForpharmacy(Long pharmacyid) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Patientpreference.FIELD_pharmacyid, pharmacyid));
		criterions.add(Restrictions.eq(Patientpreference.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional
	public Patientpreference getPatientpreferenceForpharmacyfillReq(Long patientId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Patientpreference.FIELD_patientid, patientId));
		criterions.add(Restrictions.eq(Patientpreference.FIELD_active, 'Y'));
		List<Patientpreference> patientpreferences = findByCrieria(criterions);
		if (CollectionUtils.isEmpty(patientpreferences)) {
			return null;
		}
		return patientpreferences.get(0);
	}
}