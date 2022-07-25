package org.secondopinion.caretaker.dao;

import java.util.List;

import org.secondopinion.caretaker.dto.Baseschedule;
import org.secondopinion.dao.IDAO;

import org.secondopinion.request.Response;

public interface BasescheduleDAO extends IDAO<Baseschedule,Long >{

	Response<List<Baseschedule>> getDoctorAllBasesSchedules(Long caretakerId, Integer pageNum, Integer maxResults);

	Baseschedule findbasseScheduleById(Long basseScheduleId);
}