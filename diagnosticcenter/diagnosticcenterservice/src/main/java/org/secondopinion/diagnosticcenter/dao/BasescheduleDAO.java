package org.secondopinion.diagnosticcenter.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Baseschedule;
import org.secondopinion.diagnosticcenter.dto.ScheduleCriteriaDTO;
import org.secondopinion.request.Response;

public interface BasescheduleDAO extends IDAO<Baseschedule,Long >{

	
	Baseschedule findbasseScheduleById(Long basseScheduleId);

	Response<List<Baseschedule>> getDiagnosticCenterAllBasesSchedules(ScheduleCriteriaDTO scheduleCriteriaDTO);
}