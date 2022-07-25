package org.secondopinion.userMgmt.dao; 

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.secondopinion.userMgmt.dto.User;
import org.secondopinion.userMgmt.dto.UserRole;

import org.secondopinion.dao.IDAO;

public interface UserDAO extends IDAO<User,Long >{
	
	User getUser(String userId);
	
	User getUserAndCompanyDetails(String userId);
	
	List<User> getAllUsersInCompany(Integer companyId);
	
	User getPrimaryUser(Integer companyId);
	
	void updatePassword(String userId, String password);
	
	boolean userNameExists(String userId);
	
	boolean userEmailExists(String emailId);
	
	public boolean userEmailExists(Integer companyId, String emailId);
	
	List<Object[]> getUserNamesForRoles(Integer companyId, String role);
	
	void saveUserRole(UserRole userRole);

	void deleteUserRole(UserRole userRole);

	List<String> getUserEmailIds(List<String> userIds);

	void updateUser(User user);

	void updateLastLoginTime(Long userId);

	User reloadUser(String userId);

	//String getUserNameByKey(Long userPK);

	Map<Long, Map<String, String>> getUserNameAndClientNameByUserKeys(Set<Long> userPKs);
}