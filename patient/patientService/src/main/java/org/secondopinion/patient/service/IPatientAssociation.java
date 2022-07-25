package org.secondopinion.patient.service;

import java.util.Map;
import java.util.Set;

import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.PatientRequest;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.User;

public interface IPatientAssociation {

	void savePatientRequest(PatientRequest patientRequest,RELATIONSHIP_TYPE relationshiptType);

	void updatePatientRequestUponDoctorRejections(Long doctorId, Long PatientId,RELATIONSHIP_TYPE relationshiptType);

	void updateRelationshipUponDoctorAccepts(Long doctorId, Long patientId,RELATIONSHIP_TYPE relationshiptType);

	Map<Long, String> getUserNameByUserIds(Set<Long> userIds);

	void deleteAssocateUser(Long userId, Long associateUserId,RELATIONSHIP_TYPE relationshiptType);

	void updateAssocateUser(ForUser forUser, RELATIONSHIP_TYPE relationship);

	void addManagedUser(User user, ForUser forUser);

}
