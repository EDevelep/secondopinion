package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.diagnosticcenter.dto.Baseschedule;
import org.secondopinion.diagnosticcenter.dto.ScheduleCriteriaDTO;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class BasescheduleDAOHibernateImpl extends BaseBasescheduleDAOHibernate{

	@Override
	@Transactional(readOnly=true)
	public Baseschedule findbasseScheduleById(Long basseScheduleId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Baseschedule.FIELD_active, 'Y'));
		criterions.add(Restrictions.eq(Baseschedule.FIELD_basseScheduleId, basseScheduleId));
		List<Baseschedule> baseschedules = findByCrieria(criterions);
		if(CollectionUtils.isEmpty(baseschedules)) {
			return null;
		}
		return baseschedules.get(0);
	}

	@Override
	@Transactional(readOnly=true)
	public Response<List<Baseschedule>> getDiagnosticCenterAllBasesSchedules(ScheduleCriteriaDTO scheduleCriteriaDTO) {
		List<Criterion> criterions = new ArrayList<>();
		//criterions.add(Restrictions.eq(Baseschedule.FIELD_active, 'Y'));
		if(Objects.nonNull(scheduleCriteriaDTO.getBasescheduleId())) {
			criterions.add(Restrictions.eq(Baseschedule.FIELD_basseScheduleId, scheduleCriteriaDTO.getBasescheduleId()));
		}
		if(Objects.nonNull(scheduleCriteriaDTO.getDiagnosticCenterAddressId())) {
			criterions.add(Restrictions.eq(Baseschedule.FIELD_diagnosticCenterAddressId, scheduleCriteriaDTO.getDiagnosticCenterAddressId()));
		}
		if(Objects.nonNull(scheduleCriteriaDTO.getDiagnosticCenteruserId())) {
			criterions.add(Restrictions.eq(Baseschedule.FIELD_diagnosticcenterUserId, scheduleCriteriaDTO.getDiagnosticCenteruserId()));
		}
		if(Objects.nonNull(scheduleCriteriaDTO.getPackageId())) {
			criterions.add(Restrictions.eq(Baseschedule.FIELD_packageId, scheduleCriteriaDTO.getPackageId()));
		}
		if(Objects.nonNull(scheduleCriteriaDTO.getSubmenuId())) {
			criterions.add(Restrictions.eq(Baseschedule.FIELD_subMenuId, scheduleCriteriaDTO.getSubmenuId()));
		}
		return findByCrieria(criterions, null, scheduleCriteriaDTO.getPageNum(), scheduleCriteriaDTO.getMaxResutls());
		
	}
}