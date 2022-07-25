package org.secondopinion.patient.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.VitalSearch;
import org.secondopinion.patient.dto.Vitals;

public interface VitalsDAO extends IDAO<Vitals,Long >{

	List<Vitals> search(VitalSearch vitalSearch);
	 List<Vitals> getUserVitalsByuserId(Long userId);
	List<Vitals> getUserVitalsByDay(Long userId, Date date);
	void save(Long userId, Date vitalsRecordDate, List<Vitals> vitals);
	List<Map<String, Object>> executeGraphQuery(Long userId, String vitalName, Date recordedDate);
	List<Vitals> getUserVitalsByuserId(List<Long> userIds);
	List<Vitals> getVitalsByvitalsId(List<Long> collect);
}