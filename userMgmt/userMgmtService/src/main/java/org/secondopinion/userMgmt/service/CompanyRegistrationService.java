package org.secondopinion.userMgmt.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.secondopinion.userMgmt.dto.Address;
import org.secondopinion.userMgmt.dto.Company;
import org.secondopinion.userMgmt.dto.CompanyTemplate;
import org.secondopinion.userMgmt.dto.User;
import org.secondopinion.userMgmt.dto.UserRequest;
import org.secondopinion.userMgmt.dto.UserRole;
import org.secondopinion.userMgmt.dto.UserRequest.CompanyFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.userMgmt.dao.CompanyDAO;
import org.secondopinion.userMgmt.dao.UserDAO;
import org.secondopinion.userMgmt.util.UserMgmtUtil;
import org.secondopinion.utils.AES;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.StringUtil;

@Service
public class CompanyRegistrationService {
	@Value("${company.secet.key}")
	private String key;
	@Value("${company.secet.iv}")
	private String iv;
	@Value("${company.template.location}")
	private String uploadLocation;
	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UtilComponent utilComponent;
	
	public String registerCompany(Company company){
		
		User isUserExistsWithUserName = getUserDAO().getUser(company.getPrimaryUser().getUserName());
		if(isUserExistsWithUserName != null) {
			throw new DataAccessException("user name already exists. Please provide a new user name");
		}
		
		Company isCompanyExistsWithName = getCompanyDAO().getByName(company.getName());
		if(isCompanyExistsWithName != null) {
			throw new DataAccessException("company name already exists. Please provide a new company name");
		}
		
		company.setActive('N');
		company.getPrimaryUser().setSiteAdmin('N');
		company.setProfileCreatedDate(DateUtil.getDate());
		company.setNewlyRegistered('Y');
		company.setVerificationCompleted('N');
		company.setSetupComplete('N');
		company.setVerifyBy(DateUtil.addAndGetDate(utilComponent.getTimeZone(), 2));
		
		User primaryUser = company.getPrimaryUser();
		if(primaryUser != null && !StringUtil.isNullOrEmpty(primaryUser.getPassword())){
			//String encryptedVal = AES.getEncodedMessage(key, iv, primaryUser.getPassword());
			primaryUser.setPassword(primaryUser.getPassword());
			
			UserRole userRole= new UserRole();
			userRole.setUserId(primaryUser.getUserName());
			userRole.setRoleId(1);
			
			primaryUser.addRole(userRole);
		}
		getCompanyDAO().save(company);
		
		String verifcationId = "VId"+company.getCompanyId()+":"+System.currentTimeMillis();
		company.setVerificationId(verifcationId);
		getCompanyDAO().save(company);
		
		return verifcationId;
	}

	public void activateCompany(Long companyId) {
		companyDAO.activateRegistration(companyId.intValue());
	}

	public void companyEmailVerificationComplete(String verificationId) {
		Company company = companyDAO.getCompanyForVerification(verificationId);
		
		if(Objects.isNull(company )){
			new IllegalArgumentException("Either Verification ID is expired or Invalid, please check.");
		}
		company.setVerificationCompleted('Y');
		companyDAO.save(company);
		
	}

	public Company companyDetails(Long companyId) {
		return companyDAO.companyDetails(companyId.intValue());
	}
	
	
	public void updateCompanyDetails(Long companyId, Company company) {
		companyDAO.save(company);
	}
	
	
	public File getTemplate(Long companyId, Long companyTemplateId){
		CompanyTemplate template = companyDAO.getTemplate(companyTemplateId);
		File file = new File(UserMgmtUtil.getTemplateFolderLocation(uploadLocation, companyId) + template.getDocumentName());
		return file;
	}

	
	public File getLogo(Long companyId){
		Company company = companyDAO.companyDetails(new Integer(companyId.intValue()));
		
		if(!StringUtil.isNullOrEmpty(company.getLogo())){
		File file = new File(UserMgmtUtil.getLogoFolderLocation(uploadLocation, companyId) + company.getLogo());
			return file;
		}else{
			return null;
		}
	}
	
	
	public Company deleteTemplate(Long companyId, Long companyTemplateId) {
		companyDAO.deleteTemplate(companyTemplateId);
		return companyDetails(companyId);
	}

	
	public void updateAddress(Long companyId, Address address) {
		companyDAO.saveAddress(address);
	} 

	public String getUploadLocation() {
		return uploadLocation;
	}

	public void setUploadLocation(String uploadLocation) {
		this.uploadLocation = uploadLocation;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}
	
	public boolean webSiteExists(String website){
		return companyDAO.webSiteExists(website);
	}
	
	public boolean emailExists(String emailId){
		return companyDAO.emailExists(emailId);
	}

	public List<Company> newlyRegisteredcompanies() {
		return companyDAO.newlyRegisteredCompanies();
	}
	
	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public List<Company> approvedCompanies() {
		return companyDAO.approvedCompanies();
	}

	public void approveTheCompany(Integer companyId) {
		 companyDAO.approveTheCompany(companyId);
	}

	public Map<CompanyFlag, List<Company>> fetchCompaniesByFlagWithPagination(UserRequest userRequest) {
		
		Map<CompanyFlag, List<Company>> map = new HashMap<UserRequest.CompanyFlag, List<Company>>();
		Set<CompanyFlag> companyFlags = userRequest.getCompanyFlags();
		if(CollectionUtils.isEmpty(companyFlags )) {
			return map;
		}
		for (CompanyFlag companyFlag : companyFlags) {
			map.put(companyFlag, companyDAO.fetchCompaniesByFlagWithPagination(companyFlag, userRequest.getSortMap(), userRequest.getPageNo(), userRequest.getLimit()));
		}
		return map;
	}

	public String getCompanyNameByKey(Long companyPK) {
		return companyDAO.getCompanyNameByKey(companyPK);
	}
}
