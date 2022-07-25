package org.secondopinion.doctor.daoimpl; 


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.doctor.domain.BaseAppointmentnotes;
import org.secondopinion.doctor.dto.Appointmentnotes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class AppointmentnotesDAOHibernateImpl extends BaseAppointmentnotesDAOHibernate{
	
	@Override
	@Transactional(readOnly=true)
	public List<Appointmentnotes> previousAppointmentsnotes(List<Long> appointmentIds) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.in(BaseAppointmentnotes.FIELD_appointmentId,appointmentIds));
		criterions.add(Restrictions.eq(BaseAppointmentnotes.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
	
	@Override
	@Transactional
	public void save(Appointmentnotes appointmentnotes) {
		if(Objects.isNull(appointmentnotes.getAppointmentNotesId())) {
			appointmentnotes.setActive('Y');
		}
		super.save(appointmentnotes);
	}

	@Override
	@Transactional(readOnly=true)
	public Appointmentnotes findappointmentnotesDetailsById(Long appointmentNotesId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseAppointmentnotes.FIELD_appointmentNotesId,appointmentNotesId));
		criterions.add(Restrictions.eq(BaseAppointmentnotes.FIELD_active, 'Y'));
		List<Appointmentnotes> appointmentnotes=findByCrieria(criterions);
		return appointmentnotes.get(0);
	}
	
}