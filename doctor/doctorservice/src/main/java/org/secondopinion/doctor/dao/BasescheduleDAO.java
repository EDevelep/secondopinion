package org.secondopinion.doctor.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.Baseschedule;
import org.secondopinion.request.Response;

public interface BasescheduleDAO extends IDAO<Baseschedule,Long >{

	Response<List<Baseschedule>> getDoctorAllBasesSchedules(Long doctorId, Integer pageNum, Integer maxResults);

	Baseschedule findbasseScheduleById(Long basseScheduleId);
}