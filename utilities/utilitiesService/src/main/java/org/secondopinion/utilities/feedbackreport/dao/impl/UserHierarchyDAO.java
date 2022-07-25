package org.secondopinion.utilities.feedbackreport.dao.impl;
//package org.secondopinion.utilities.feedbackreport.dao;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.transaction.annotation.Transactional;
//
//import org.secondopinion.dao.exception.DataAccessException;
//import org.secondopinion.userMgmt.dto.User;
//
//
//public class UserHierarchyDAO implements UserDAO{
//	private UserIgniteDAO userIgniteDAO;
//	private UserDAOHibernateImpl userHibernateDAO;
//	private CompanyDAO companyDAO; 
//	
//	@Override
//	public Collection<User> findAll() {
//		return null;
//	}
//
//	@Override
//	public User findById(Long userId) {
//		User user = userIgniteDAO.findById(userId);
//		
//		if(user==null){
//			user = findUserFromDB(userId);
//			userIgniteDAO.save(user);
//		}
//		
//		return user;
//	}
//
//	@Transactional(readOnly=true)
//	private User findUserFromDB(Long userId) {
//		User user = userHibernateDAO.findById(userId);
//		return user;
//	}
//
//	@Override
//	@Transactional
//	public void save(User type) {
//		userHibernateDAO.save(type);
//		User user = getUserFromDB(type.getUserName());
//		userIgniteDAO.save(user);
//	}
//
//	@Override
//	@Transactional
//	public void save(Collection<User> types) {
//		userHibernateDAO.save(types);
//		userIgniteDAO.save(types);
//	}
//
//	@Override
//	public User getUser(String userId) {
//		User user = userIgniteDAO.getUser(userId);
//		
//		if(user==null){
//			user = getUserFromDB(userId);
//			userIgniteDAO.save(user);
//		}
//		
//		return user;
//	}
//
//	@Transactional(readOnly=true)
//	private User getUserFromDB(String userId) {
//		return userHibernateDAO.getUser(userId);
//	}
//
//	@Override
//	@Transactional(readOnly=true)
//	public List<User> getAllUsersInCompany(Integer companyId) {
//		return userHibernateDAO.getAllUsersInCompany(companyId);
//	}
//
//	@Override
//	@Transactional(readOnly=true)
//	public User getPrimaryUser(Integer companyId) {
//		return userHibernateDAO.getPrimaryUser(companyId);
//	}
//
//	@Override
//	@Transactional
//	public void updatePassword(String userId, String password) {
//		userHibernateDAO.updatePassword(userId, password);
//		
//		User user = userHibernateDAO.getUser(userId);
//		userIgniteDAO.save(user);
//	}
//
//	@Override
//	public boolean userNameExists(String userId) {
//		return userHibernateDAO.userNameExists(userId);
//	}
//
//	@Override
//	public boolean userEmailExists(String emailId) {
//		return userHibernateDAO.userEmailExists(emailId);
//	}
//	
//	@Override
//	public boolean userEmailExists(Integer companyId, String emailId) {
//		return userHibernateDAO.userEmailExists(companyId, emailId);
//	}
//
//	@Override
//	public List<Object[]> getUserNamesForRoles(Integer companyId, String role) {
//		return userHibernateDAO.getUserNamesForRoles(companyId, role);
//	}
//
//	@Override
//	public void saveUserRole(UserRole userRole) {
//		userHibernateDAO.saveUserRole(userRole);
//		User user = getUserFromDB(userRole.getUserId());
//		
//		userIgniteDAO.save(user);
//	}
//
//	@Override
//	public void delete(User object) throws DataAccessException {
//		userHibernateDAO.delete(object);
//		userIgniteDAO.delete(object);
//	}
//
//	public UserDAOHibernateImpl getUserHibernateDAO() {
//		return userHibernateDAO;
//	}
//
//	public void setUserHibernateDAO(UserDAOHibernateImpl userHibernateDAO) {
//		this.userHibernateDAO = userHibernateDAO;
//	}
//
//	@Override
//	public void deleteUserRole(UserRole userRole) {
//		userHibernateDAO.deleteUserRole(userRole);
//		User user = getUserFromDB(userRole.getUserId());
//		userIgniteDAO.save(user);
//	}
//
//	@Override
//	@Transactional(readOnly=true)
//	public User getUserAndCompanyDetails(String userId) {
//		User user = getUser(userId);
//		
//		Company company = companyDAO.companyAndAddressDetails(user.getCompanyId());
//		user.setCompany(company);
//		
//		return user;
//	}
//
//	@Override
//	public List<String> getUserEmailIds(List<String> userIds) {
//		return userHibernateDAO.getUserEmailIds(userIds);
//	}
//
//	@Override
//	@Transactional
//	public void updateUser(User user) {
//		userHibernateDAO.updateUser(user);
//		User dbDbUser = getUserFromDB(user.getUserName());
//		
//		userIgniteDAO.save(dbDbUser);
//	}
//
//	@Override
//	@Transactional
//	public DAOResult<User, Long> saveWithRetry(Collection<User> types) {
//		DAOResult<User, Long> result =  userHibernateDAO.saveWithRetry(types);
//		
//		userIgniteDAO.save(result.getSuccess());
//		
//		return result;
//	}
//
//	@Override
//	public void updateLastLoginTime(Long userId) {
//		userHibernateDAO.updateLastLoginTime(userId);
//		userIgniteDAO.updateLastLoginTime(userId);
//		
//	}
//
//	@Override
//	@Transactional
//	public void delete(Collection<User> users) throws DataAccessException {
//		userHibernateDAO.delete(users);
//		userIgniteDAO.delete(users);
//	}
//
//	/**
//	 * @return the userIgniteDAO
//	 */
//	public UserIgniteDAO getUserIgniteDAO() {
//		return userIgniteDAO;
//	}
//
//	/**
//	 * @param userIgniteDAO the userIgniteDAO to set
//	 */
//	public void setUserIgniteDAO(UserIgniteDAO userIgniteDAO) {
//		this.userIgniteDAO = userIgniteDAO;
//	}
//
//	/**
//	 * @return the companyDAO
//	 */
//	public CompanyDAO getCompanyDAO() {
//		return companyDAO;
//	}
//
//	/**
//	 * @param companyDAO the companyDAO to set
//	 */
//	public void setCompanyDAO(CompanyDAO companyDAO) {
//		this.companyDAO = companyDAO;
//	}
//
//	@Override
//	public User reloadUser(String userId) {
//		User user = userHibernateDAO.getUser(userId);
//		userIgniteDAO.save(user);
//		return user;
//	}
//}
