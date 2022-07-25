package org.secondopinion.patient.dao.impl.hibernate; 


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.patient.dto.Patientfcmtoken;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class PatientfcmtokenDAOHibernateImpl extends BasePatientfcmtokenDAOHibernate{

	@Override
	@Transactional
	public void save(  Patientfcmtoken patientfcmtoken) throws DataAccessException {
		if(Objects.isNull(patientfcmtoken.getPatientFCMtokenId())) {
			patientfcmtoken.setActive('Y');
		}
		super.save(patientfcmtoken);
	}
	
	
	
	@Override
	@Transactional(readOnly = true)
	public Patientfcmtoken findPatientfcmtokenByPatientid(Long patientid) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Patientfcmtoken.FIELD_patientid, patientid));
		criterions.add(Restrictions.eq(Patientfcmtoken.FIELD_active, 'Y'));
		List<Patientfcmtoken> patientfcmtokens=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(patientfcmtokens)) return null;
		return patientfcmtokens.get(0);
	}
}