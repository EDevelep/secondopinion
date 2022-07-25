package org.secondopinion.userMgmt.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.secondopinion.userMgmt.dto.Company;
import org.secondopinion.userMgmt.dto.User;
import org.secondopinion.userMgmt.dto.UserProfilePic;
import org.secondopinion.userMgmt.dto.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.secondopinion.dao.hibernate.DaoUtil;
import org.secondopinion.userMgmt.dao.CompanyDAO;
import org.secondopinion.userMgmt.dao.RolesDAO;
import org.secondopinion.userMgmt.dao.UserProfilePicDAO;
import org.secondopinion.userMgmt.dao.UserRoleDAO;

@Component
public class UserDAOHibernateImpl extends BaseUserDAOHibernate{

	@Autowired
	private RolesDAO roleDAO;
	@Autowired
	private UserProfilePicDAO userProfilePicDAO;
	@Autowired
	private UserRoleDAO userRolesDAO;
	@Autowired
	private CompanyDAO companyDAO;
	
	@Override
	@Transactional
	public void save(User user){
		super.save(user);
		
		if(user.getUserRoles() != null && user.getUserRoles().size()>0){
			List<UserRole> userRoles =  user.getUserRoles();
			userRolesDAO.save(userRoles);
		}
		UserProfilePic userProfilePic = user.getUserProfilePic();
		if(userProfilePic != null) {
			userProfilePicDAO.save(userProfilePic);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public User getUser(String userId) {
		User user = getActiveUser(userId);
		
		if(user!=null){
			user.setRoles(getRoleDAO().getUserRoles(userId));
			user.setUserProfilePic(userProfilePicDAO.getUserProfilePicByUserId(userId));
		}
		return user;
	}

	@Transactional(readOnly=true)
	private User getActiveUser(String userId){
		Criterion userIdCriteria = Restrictions.eq(User.FIELD_userName, userId);
		Criterion activeCriteria = Restrictions.eq(User.FIELD_active, 'Y');
		
		Criterion andCriterion = Restrictions.and(userIdCriteria, activeCriteria);
		List<User> users = findByCrieria(andCriterion);
		
		if(users!=null && users.size()>0){
			
			User user = users.get(0);
			return user;
		}
		return null;
	}
	
	@Override
	@Transactional(readOnly=true)
	public User getUserAndCompanyDetails(String userId) {
		User user = getActiveUser(userId);
		
		if(user!=null){
			Company company = companyDAO.companyAndAddressDetails(user.getCompanyId());
			user.setCompany(company);
		}
		return user;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<User> getAllUsersInCompany(Integer companyId) {
		Criterion orgIdCriteria = Restrictions.eq(User.FIELD_companyId, companyId);
		return findByCrieria(orgIdCriteria);
	}

//	private static final String updateUserPwd = "update User set password = :password where userName = :userId";

	@Override
	@Transactional
	public void updatePassword(String userId, String password) {
		User user = getUser(userId);
		user.setPassword(password);
		
		save(user);
	}

	@Override
	@Transactional(readOnly=true)
	public User getPrimaryUser(Integer companyId) {
		Criterion userIdCriteria = Restrictions.eq(User.FIELD_companyId, companyId);
		Criterion pwdCriteria = Restrictions.eq(User.FIELD_primaryContact, 'Y');
		
		Criterion andCriterion = Restrictions.and(userIdCriteria, pwdCriteria);
		List<User> users = findByCrieria(andCriterion);
		
		if(users!=null && users.size()>0){
			return users.get(0);
		}
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public boolean userNameExists(String userId) {
		Criterion userIdCriteria = Restrictions.eq(User.FIELD_userName, userId);
		List<User> users = findByCrieria(userIdCriteria);
		
		return (users != null && users.size() >0);
	}

	@Override
	@Transactional(readOnly=true)
	public boolean userEmailExists(String emailId) {
		Criterion userIdCriteria = Restrictions.eq(User.FIELD_emailId, emailId);
		List<User> users = findByCrieria(userIdCriteria);
		
		return (users != null && users.size() >0);
	}
	
	@Override
	@Transactional(readOnly=true)
	public boolean userEmailExists(Integer companyId, String emailId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(User.FIELD_companyId, companyId));
		criteria.add(Restrictions.eq(User.FIELD_emailId, emailId));
		List<User> users = findByCrieria(criteria);
		
		return (users != null && users.size() >0);
	}

	private static final String user_name_sql = "select u.userName, concat(u.firstName, ' ', u.lastName) as name from user u, roles a, userrole b where u.userName = b.userId "
			+ " and a.roleId = b.roleId  and u.companyId = :companyId and a.roleName = :roleName";
	
	@Override
	@Transactional(readOnly=true)
	public List<Object[]> getUserNamesForRoles(Integer companyId, String role) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("companyId", companyId);
		params.put("roleName", role);
		
//		Map<String, Type> scalarMapping = new HashMap<String, Type>();
//		scalarMapping.put("userId", StandardBasicTypes.LONG);
		
//		return  findBySqlQuery(user_name_sql, params, null, scalarMapping);
		return  findBySqlQuery(user_name_sql, params, null);
	}
	
	private static final String user_email_sql = "select emailId as name from user "
			+ " where userName in (:userName)";
	

	@Override
	@Transactional(readOnly=true)
	public List<String> getUserEmailIds(List<String> userIds) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("userName", userIds);
		
		return  findBySqlQuery(user_email_sql, params, null);
	}
	
	public RolesDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RolesDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public UserProfilePicDAO getUserProfilePicDAO() {
		return userProfilePicDAO;
	}

	public void setUserProfilePicDAO(UserProfilePicDAO userProfilePicDAO) {
		this.userProfilePicDAO = userProfilePicDAO;
	}

	public UserRoleDAO getUserRolesDAO() {
		return userRolesDAO;
	}

	public void setUserRolesDAO(UserRoleDAO userRolesDAO) {
		this.userRolesDAO = userRolesDAO;
	}

	@Override
	@Transactional
	public void saveUserRole(UserRole userRole) {
		userRolesDAO.save(userRole);
	}

	@Override
	public void deleteUserRole(UserRole userRole) {
		userRolesDAO.delete(userRole);
	}

	/**
	 * @return the companyDAO
	 */
	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	/**
	 * @param companyDAO the companyDAO to set
	 */
	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		User dbUser = findById(user.getUserId());
		dbUser.setFirstName(user.getFirstName());
		dbUser.setMiddleName(user.getMiddleName());
		dbUser.setLastName(user.getLastName());
		dbUser.setEmailId(user.getEmailId());
		dbUser.setOfficeNumber(user.getOfficeNumber());
		dbUser.setCellNumber(user.getCellNumber());
		dbUser.setUserProfilePic(user.getUserProfilePic());
		save(dbUser);
	}

	private static final String updateLastLoginUserSql = "update User set lastLogin=:loginTime "
			+ " where userId = :userId";
	
	@Override
	@Transactional
	public void updateLastLoginTime(Long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("loginTime", new Date());
		params.put("userId", userId);
		
		executeQuery(updateLastLoginUserSql, params);
		
	}

	@Override
	public User reloadUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public Map<Long, Map<String, String>> getUserNameAndClientNameByUserKeys(Set<Long> userPKs) {
		
		Map<Long, Map<String, String>> userNameAndClientNameByKeyMap = new HashMap<>();
		String inQuery = DaoUtil.generateInQuery(userPKs, User.FIELD_userId, false);
		String usernameInQuery = "select u.id, u."+ User.FIELD_userId +", u."+ User.FIELD_userName +", co." + Company.FIELD_name +" from user u "
				+ " join company co on co." + Company.FIELD_companyId + " = u." + User.FIELD_companyId 
				+ " where "+ inQuery;
		
		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(User.FIELD_userId, StandardBasicTypes.LONG);
		scalarMapping.put(User.FIELD_userName, StandardBasicTypes.STRING);
		scalarMapping.put(Company.FIELD_name, StandardBasicTypes.STRING);
		
		List<Map<String, Object>> objects= findBySqlQueryMapTransformer(usernameInQuery, null, scalarMapping);
		for (Iterator<Map<String, Object>> iterator = objects.iterator(); iterator.hasNext();) {
			Map<String, Object> userNameMap = iterator.next();
			Long userId = (Long) userNameMap.get(User.FIELD_userId);
			if(!userNameAndClientNameByKeyMap.containsKey(userId)) {
				Map<String, String> userNameAndClientNameMap = new HashMap<>();
				userNameAndClientNameMap.put((String)userNameMap.get(User.FIELD_userName), (String)userNameMap.get(Company.FIELD_name));
				userNameAndClientNameByKeyMap.put(userId, userNameAndClientNameMap);
			}
		}
		return userNameAndClientNameByKeyMap ;
	}


}