package org.secondopinion.patient.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.ACCESS_TYPE;
import org.secondopinion.patient.dto.DoctorDashBoardDTO;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.dto.UserDTO;
import org.secondopinion.request.Response;

public interface UserDAO extends IDAO<User,Long >{

	List<Object[]> getUsersForWithRelationship(List<Long> relationships);
	List<UserDTO> getUsers(List<Long> userIds);
	boolean hasUserAccessToDetails(Long userId, Long forUserId, ACCESS_TYPE accessType,RELATIONSHIP_TYPE relationship_TYPE);

	void updateLastLoginTime(Long userId);

	boolean getByIdNotAndUsernameEquals(Long userId, String emailId);

	Map<Long, String> getUserNameByUserIds(Set<Long> userIds);

	String geUsersEmailId(Long userId);

	User findUserByUserId(Long userId);

	void updateRetryCountIfLoginFailed(Long userId, Integer retry);

	Response<List<User>> getAssociatedUsers(Long userId, Integer pageNum, Integer maxResults);
	User findUserByUserNameAndEmailId(String fieldUsername);
	User findUserByUserNameAndEmailIdDependent(String email);
	User emailVerificationUserByUserNameAndEmailId(String emailid, String userName);
	User emailVerificationUserByUserNameAndEmailIds(String emailid, String userName);
	User findUserByUsercellNumber(String cellNumber, String userName);
	User findUserByUsercellNumbers(String cellNumber, String userName);
	User findUserByUserIds(List<Long> userIds);
	Collection<DoctorDashBoardDTO> getDoctorDashBoardForDoctorIdAndAppointmentFor(Long doctorId, String appointmentFor);
}