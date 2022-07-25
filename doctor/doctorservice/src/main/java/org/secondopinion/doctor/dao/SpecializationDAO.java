package org.secondopinion.doctor.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.Specialization;

public interface SpecializationDAO extends IDAO<Specialization, Long> {

	Specialization findspecializationById(Long specializationId);

	List<Specialization> findSpecializationsByDoctorId(Long doctorId);
}