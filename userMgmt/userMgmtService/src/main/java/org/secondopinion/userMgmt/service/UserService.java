package org.secondopinion.userMgmt.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.secondopinion.userMgmt.dto.KeyValueDTO;
import org.secondopinion.userMgmt.dto.Roles;
import org.secondopinion.userMgmt.dto.User;
import org.secondopinion.userMgmt.dto.UserProfilePic;
import org.secondopinion.userMgmt.dto.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.userMgmt.dao.CompanyDAO;
import org.secondopinion.userMgmt.dao.CompanyKeyDAO;
import org.secondopinion.userMgmt.dao.RolesDAO;
import org.secondopinion.userMgmt.dao.UserDAO;
import org.secondopinion.userMgmt.dao.UserProfilePicDAO;
import org.secondopinion.utils.MailProperties;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.file.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public UserService(CompanyDAO companyDAO, CompanyKeyDAO companyKeyDAO) {
		
		this.companyDAO = companyDAO;
	}
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RolesDAO roleDAO;
	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private MailProperties mailProperties;
	@Autowired
	private UserProfilePicDAO userProfilePicDAO;
	
	@Transactional(readOnly=true)
	public User getUser(String userId, String password) throws IOException{
		User user = getDecryptedUserById(userId, true);
		
		if(user != null){
			if(!StringUtil.equalsIgnoreCase(password, user.getPassword())){
				return null;
			}
			
			if(CollectionUtils.isEmpty(user.getRoles() )){
				user = userDAO.reloadUser(userId);
			}
			user.setPassword("");
		}
		return user;
	}
	
	
	@Transactional(readOnly=true)
	public List<User> getAllUsersInCompany(Integer companyId){
		return userDAO.getAllUsersInCompany(companyId);
	}
	
	public void updatePassword(Long userId, String password){
		User user  = userDAO.findById(userId);
		user.setPassword(password);
		userDAO.save(user);
	}
	
	@Transactional
	public String resetUserPassword(String userId){
		User user = userDAO.getUser(userId);
		
		String newPwd = StringUtil.generateRandomPassword(12);
		
		user.setPassword(newPwd);
		userDAO.save(user);
		
		return newPwd;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	private MailProperties getMailProperties(User user) {
		MailProperties properties = mailProperties.clone();
		properties.setFromAddress("admin@partner2hire.com");
		properties.addToRecipient(user.getEmailId());
		return properties;
	}

	@Transactional
	public void saveUser(User user, MultipartFile profilePic) throws IOException {
		if(Objects.isNull(user.getUserId())) {
			boolean isExistWithCompanyIdAndEmail = userEmailExists(Long.valueOf(user.getCompanyId()), user.getEmailId());
			if(isExistWithCompanyIdAndEmail) {
				throw new DataAccessException("user email already exists for this company. please provide a new email id.");
			}
			
			boolean isUserNameExists = userNameExists(user.getUserName());
			if(isUserNameExists) {
				throw new DataAccessException("user name already exists. please provide a new user name");
			}
		}
		
		user = buildUserProfilePicForSave(user, profilePic);
		if(Objects.isNull(user.getUserId())){
			String password = user.getPassword();
			userDAO.save(user);
			user.setActive('Y');
			
			//TODO: User creation - Need to send an email.
			StringBuilder emailContent = new StringBuilder("<DIV>Hi ")
					.append(user.getFirstName()).append(" ").append(user.getLastName())
					.append(",</BR> <p>Welcome aboard <strong>RME</strong>. Your organization has created an account for you.</p>")
					.append("</BR><p> Please login into the portal at https://rme.partner2hire.com/RMEWeb/RME")
					.append("</BR> Your login credentials are:")
					.append("</BR> UserId: <strong>").append(user.getUserName())
					.append("</strong></BR> Password: <strong>").append(password)
					.append("</strong></p></BR></BR><p> Thanks, </BR>RME Admin.</p></DIV>");
			
		//	EmailUtil.sendEmail(getMailProperties(user), "[RME] Your userid and password info", emailContent.toString());
		}else{
			User dbUser = userDAO.findById(user.getUserId());
			dbUser.setCellNumber(user.getCellNumber());
			dbUser.setEmailId(user.getEmailId());
			dbUser.setOfficeNumber(user.getOfficeNumber());
			dbUser.setFirstName(user.getFirstName());
			dbUser.setLastName(user.getLastName());
			dbUser.setMiddleName(user.getMiddleName());
			dbUser.setActive(user.getActive());
			dbUser.setUserProfilePic(user.getUserProfilePic());
			dbUser.setUserRoles(user.getUserRoles());
			userDAO.save(dbUser);
		}
		
	}
	
	@Transactional(readOnly=true)
	public boolean userNameExists(String userId){
		return userDAO.userNameExists(userId);
	}
	
	@Transactional(readOnly=true)
	public boolean userEmailExists(String emailId){
		return userDAO.userEmailExists(emailId);
	}

	@Transactional(readOnly=true)
	public boolean userEmailExists(Long companyId, String emailId){
		return userDAO.userEmailExists(companyId.intValue(), emailId);
	}
	
	@Transactional(readOnly=true)
	public List<String> getAllUserRoleNames() {
		Collection<Roles> allRoles = roleDAO.findAll();
		
		List<String> roles = new ArrayList<String>();
		for(Roles role : allRoles ){
			roles.add(role.getRoleName());
		}
		
		return roles;
	}
	
	@Transactional(readOnly=true)
	public Collection<Roles> getAllUserRoles() {
		return roleDAO.findAll();
	}
	
	public Integer getResourceRoleId(){
		Roles role = roleDAO.getRoleByName("RESOURCE");
		
		return role.getRoleId();
	}
	
	@Transactional(readOnly=true)
	public Collection<KeyValueDTO> getAllUserRolesForSelection() {
		Collection<Roles> roles =  roleDAO.findAll();
		
		List<KeyValueDTO> selections = new ArrayList<KeyValueDTO>();
		
		for(Roles role : roles){
			if(StringUtil.equalsIgnoreCase(role.getRoleName(),"SITE_ADMIN")){
				continue;
			}
			KeyValueDTO dto = new KeyValueDTO();
			dto.setKey(String.valueOf(role.getRoleId()));
			dto.setValue(role.getRoleName());
			
			selections.add(dto);
		}
		
		return selections;
	}
	
	@Transactional(readOnly=true)
	public List<KeyValueDTO> getUsersByRole(Integer companyId, String role){
		
		List<Object[]> values = userDAO.getUserNamesForRoles(companyId, role);
		
		List<KeyValueDTO> keyValues = new ArrayList<KeyValueDTO>();
		
		for(Object[] objs : values){
			KeyValueDTO dto = new KeyValueDTO();
			dto.setKey((String)objs[0]);
			dto.setValue((String)objs[1]);
			
			keyValues.add(dto);
		}
		
		return keyValues;
	}

	public RolesDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RolesDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Transactional(readOnly=true)
	public User getDecryptedUserById(String userId, boolean isProfilePicRequired) throws IOException {
		User user = userDAO.getUser(userId);
		//getDecryptPipeLine().decryptData(user, user.getCompanyId());
		if(isProfilePicRequired) {
			buildUserProfilePicForFetch(user);
		}
		
		return user;
	}
	

	@Transactional(readOnly=true)
	public User getUserById(String userId) throws IOException {
		User user = userDAO.getUser(userId);
		buildUserProfilePicForFetch(user);
		
		return user;
	}

//	@Transactional
	public void saveUserRole(UserRole userRole) {
		userDAO.saveUserRole(userRole);	
	}

//	@Transactional
	public void deleteUserRole(UserRole userRole) {
		userDAO.deleteUserRole(userRole);
	}

	@Transactional
	public void updateUser(User user, MultipartFile profilePic) throws IOException {
		User user1 = getDecryptedUserById(user.getUserName(), false);
		user.setPassword(user1.getPassword());
		user = buildUserProfilePicForSave(user, profilePic);
		userDAO.updateUser(user);
	}

	@Transactional
	public void updateLastLoginTime(Long userId) {
		userDAO.updateLastLoginTime(userId);
		
	}

	@Transactional
	public void rencryptUserPassword(User user) {
		userDAO.save(user);
	}

	/**
	 * @return the mailProperties
	 */
	public MailProperties getMailProperties() {
		return mailProperties;
	}

	/**
	 * @param mailProperties the mailProperties to set
	 */
	public void setMailProperties(MailProperties mailProperties) {
		this.mailProperties = mailProperties;
	}
	
	//================Profile Pic save
	private User buildUserProfilePicForSave(User user, MultipartFile profilePic) throws IOException {
		UserProfilePic userProfilePic = user.getUserProfilePic();
		if(Objects.isNull(profilePic )) {
			return user;
		} 
		if(user.getUserId() != null) {
			user = getUserById(user.getUserName());
		}
		userProfilePic = user.getUserProfilePic();
		if(Objects.isNull(userProfilePic )) {
			userProfilePic = new UserProfilePic();
		}


		userProfilePic = buildUserProfileicObject(user.getUserName(), profilePic, userProfilePic);
		user.setUserProfilePic(userProfilePic);
		return user;
	}
	
	private User buildUserProfilePicForFetch(User user) throws IOException {
		if(Objects.isNull(user )) {
			return user;
		}
		UserProfilePic userProfilePic = user.getUserProfilePic();
		if(Objects.isNull(userProfilePic )) {
			return user;
		}
		if(Objects.nonNull(userProfilePic.getProfilePic()) ) {
			try {
				userProfilePic.setProfilePic(FileUtil.decompressBytes(userProfilePic.getProfilePic(), 1024));
			} catch(Exception e) {
				logger.error("unable decompress the image.");
			}
		}
		user.setUserProfilePic(userProfilePic);
		return user;
	}

	private UserProfilePic buildUserProfileicObject(String userId, MultipartFile profilePic, UserProfilePic userProfilePic) throws IOException {
		userProfilePic.setFileName(profilePic.getOriginalFilename());
		userProfilePic.setContentType(profilePic.getContentType());
		userProfilePic.setUserId(userId);
		userProfilePic.setProfilePic(FileUtil.compressBytes(profilePic.getBytes(), 1024));
		return userProfilePic;
	}
	
	public void saveProfilePic(String userId, MultipartFile profilePic) throws IOException {
		if(Objects.isNull(profilePic )) {
			return;
		}
		UserProfilePic userProfilePic = userProfilePicDAO.getUserProfilePicByUserId(userId);
		if(Objects.isNull(userProfilePic )){
			userProfilePic = new UserProfilePic();
		}
		buildUserProfileicObject(userId, profilePic, userProfilePic);
		userProfilePicDAO.saveOrUpdateProfilePic(userProfilePic);
	}
	
	public void deleteUserProfilePic(String userId) {
		UserProfilePic userProfilePic = userProfilePicDAO.getUserProfilePicByUserId(userId);
		userProfilePicDAO.delete(userProfilePic);
	}


	public Map<Long, Map<String, String>> getUserNameAndClientNameByUserKeys(Set<Long> userPKs) {
		return userDAO.getUserNameAndClientNameByUserKeys(userPKs);
	}

}