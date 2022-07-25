package org.secondopinion.utilities.feedbackreport.dao.impl;
//package org.secondopinion.utilities.feedbackreport.dao;
//
//import java.util.Date;
//import java.util.List;
//
//import org.secondopinion.cache.ignite.IgniteCacheManager;
//import org.secondopinion.dto.User;
//import org.secondopinion.dto.UserRole;
//import org.secondopinion.utilities.feedbackreport.dao.UserDAO;
//
//public class UserIgniteDAO extends AbstractIgniteCacheDAO<Long, User> implements UserDAO {
//
//	public UserIgniteDAO(String className, String cacheName, IgniteCacheManager cacheManager) {
//		super(className, cacheName, cacheManager);
//	}
//
//	/*@Override
//	public User findById(Long key) {
//		return findById(key);
//	}*/
//
//	@Override
//	public User getUser(String userId) {
//		List<User> users = query(User.FIELD_userName + " = ?", userId);
//		
//		if (users !=null && users.size()>0){
//			return users.get(0);
//		}
//		
//		return null;
//	}
//
//	@Override
//	public User getUserAndCompanyDetails(String userId) {
//		return null;
//	}
//
//	@Override
//	public List<User> getAllUsersInCompany(Integer companyId) {
//		return null;
//	}
//
//	@Override
//	public User getPrimaryUser(Integer companyId) {
//		return null;
//	}
//
//	@Override
//	public void updatePassword(String userId, String password) {
//		
//	}
//
//	@Override
//	public boolean userNameExists(String userId) {
//		return false;
//	}
//
//	@Override
//	public boolean userEmailExists(Integer companyId, String emailId) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	
//	@Override
//	public boolean userEmailExists(String emailId) {
//		return false;
//	}
//
//	@Override
//	public List<Object[]> getUserNamesForRoles(Integer companyId, String role) {
//		return null;
//	}
//
//	@Override
//	public void saveUserRole(UserRole userRole) {
//		
//	}
//
//	@Override
//	public void deleteUserRole(UserRole userRole) {
//		
//	}
//
//	@Override
//	public List<String> getUserEmailIds(List<String> userIds) {
//		return null;
//	}
//
//	@Override
//	public void updateUser(User user) {
//		
//	}
//
//	@Override
//	public void updateLastLoginTime(Long userId) {
//		User user = findById(userId);
//		System.out.println("***** USER SHOULD BE NULL*****");
//		if(user != null){
//			user.setLastLogin(new Date());
//			save(user);
//		}
//	}
//	
//	@Override
//	public Class<Long> getKeyClass() {
//		return Long.class;
//	}
//
//	@Override
//	public User reloadUser(String userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
