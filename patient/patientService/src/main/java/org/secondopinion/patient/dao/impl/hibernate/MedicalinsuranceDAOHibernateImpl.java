package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.patient.domain.BaseMedicalinsurance;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.Medicalinsurance;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class MedicalinsuranceDAOHibernateImpl extends BaseMedicalinsuranceDAOHibernate{

	
	@Override
	@Transactional
	public void save(   Medicalinsurance medicalinsurance)  {
		if(Objects.isNull(medicalinsurance.getMedicalInsuranceId())) {
			medicalinsurance.setActive('Y');
			
		}
		super.save(medicalinsurance);
	}
	
	@Override
	@Transactional
	public List<Medicalinsurance> getMedicalInsuranceDtailsForUser(Long UserId) {
		List<Criterion> criterions = new ArrayList<>();
			criterions.add(Restrictions.eq(BaseMedicalinsurance.FIELD_userId, UserId));
			criterions.add(Restrictions.eq(BaseMedicalinsurance.FIELD_active, 'Y'));
	      	return findByCrieria(criterions);
	}

	@Override
	@Transactional
	public Medicalinsurance getMediclInsuranceForUserId(Long userId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseMedicalinsurance.FIELD_userId, userId));
		criterions.add(Restrictions.eq(BaseMedicalinsurance.FIELD_active, 'Y'));
		List<Medicalinsurance>  medicalinsurances=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(medicalinsurances)) return null;
		return medicalinsurances.get(0);
	}

	@Override
	@Transactional
	public Medicalinsurance getMediclInsuranceFromedicalinsuranceId(Long medicalinsuranceId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseMedicalinsurance.FIELD_medicalInsuranceId, medicalinsuranceId));
		criterions.add(Restrictions.eq(BaseMedicalinsurance.FIELD_active, 'Y'));
		List<Medicalinsurance>  medicalinsurances=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(medicalinsurances)) return null;
		return medicalinsurances.get(0);
	}
}