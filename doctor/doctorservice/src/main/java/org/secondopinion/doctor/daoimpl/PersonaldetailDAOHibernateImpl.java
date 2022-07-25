package org.secondopinion.doctor.daoimpl; 


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.secondopinion.doctor.domain.BasePersonaldetail;
import org.secondopinion.doctor.dto.Personaldetail;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class PersonaldetailDAOHibernateImpl extends BasePersonaldetailDAOHibernate{
	
	@Override
	@Transactional
	public void save(Personaldetail personaldetail) {
		
		if(Objects.isNull(personaldetail.getPersonalDetailId())) {
			personaldetail.setActive('Y');
		}
		
		super.save(personaldetail);
	}
	@Override
	@Transactional(readOnly = true)
	public Personaldetail findPersonaldetailByIdById(Long personaldetailId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BasePersonaldetail.FIELD_personalDetailId,personaldetailId));
		criterions.add(Restrictions.eq(BasePersonaldetail.FIELD_active, 'Y'));
		List<Personaldetail> personaldetails=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(personaldetails)) return null;
		return personaldetails.get(0);
	}

	
	@Override
	@Transactional(readOnly = true)
	public Personaldetail findDoctorPersonaldetailByDoctorId(Long doctorId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BasePersonaldetail.FIELD_doctorId,doctorId));
		criterions.add(Restrictions.eq(BasePersonaldetail.FIELD_active, 'Y'));
		List<Personaldetail> personaldetails=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(personaldetails)) return null;
		return personaldetails.get(0);
	}
}