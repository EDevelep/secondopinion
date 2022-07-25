package org.secondopinion.doctor.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.doctor.domain.BasePatientRequest;
import org.secondopinion.doctor.dto.PatientRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class PatientRequestDAOHibernateImpl extends BasePatientRequestDAOHibernate{

	@Override
	@Transactional(readOnly=true)
	public PatientRequest getByDoctorIdAndPatientId(Long doctorId, Long patientId) {
		
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BasePatientRequest.FIELD_doctorId, doctorId));
		criteria.add(Restrictions.eq(BasePatientRequest.FIELD_patientId, patientId));
		criteria.add(Restrictions.eq(BasePatientRequest.FIELD_active, 'Y'));

		List<PatientRequest> doctorpatientassociations =  findByCrieria(criteria);
		if(CollectionUtils.isEmpty(doctorpatientassociations)) {
			return null;
		}
		return doctorpatientassociations.get(0);
	}
	
	@Override
	@Transactional
	public void save(PatientRequest patientRequest) {
		
		if(Objects.isNull(patientRequest.getPatientRequestId())) {
			patientRequest.setActive('Y');
		}
		
		super.save(patientRequest);
	}

	@Override
	@Transactional(readOnly=true)
	public List<PatientRequest> getByDoctorIdAndRequestAcceptedFlag(Long doctorId, Character requestAccetedFlag) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BasePatientRequest.FIELD_doctorId, doctorId));
		criteria.add(Restrictions.eq(BasePatientRequest.FIELD_requestAccepted, requestAccetedFlag));
		criteria.add(Restrictions.eq(BasePatientRequest.FIELD_active, 'Y'));

		return findByCrieria(criteria);
	}

	@Override
	@Transactional(readOnly=true)
	public List<PatientRequest> getByDoctorIdAndNewRequestFlag(Long doctorId, Character newRequestFlag) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BasePatientRequest.FIELD_doctorId, doctorId));
		criteria.add(Restrictions.eq(BasePatientRequest.FIELD_newRequest, newRequestFlag));
		criteria.add(Restrictions.eq(BasePatientRequest.FIELD_active, 'Y'));

		return findByCrieria(criteria);
	}

	@Override
	@Transactional(readOnly=true)
	public List<PatientRequest> findPatientRequestsForDoctorId(Long doctorId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BasePatientRequest.FIELD_doctorId, doctorId));
		criteria.add(Restrictions.eq(BasePatientRequest.FIELD_active, 'Y'));
		return findByCrieria(criteria);
	}

}