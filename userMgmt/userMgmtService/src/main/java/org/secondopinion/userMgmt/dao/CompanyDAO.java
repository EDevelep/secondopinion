package org.secondopinion.userMgmt.dao; 

import java.util.List;
import java.util.Map;

import org.secondopinion.userMgmt.dto.Address;
import org.secondopinion.userMgmt.dto.Company;
import org.secondopinion.userMgmt.dto.CompanyConfiguration;
import org.secondopinion.userMgmt.dto.CompanyKey;
import org.secondopinion.userMgmt.dto.CompanyTemplate;
import org.secondopinion.userMgmt.dto.UserRequest.CompanyFlag;
import org.secondopinion.dao.IDAO;
import org.secondopinion.common.dto.SortDTO.SortDirection;

public interface CompanyDAO extends IDAO<Company,Integer >{
	Company companyDetails(Integer companyId);
	
	Company companyAndAddressDetails(Integer companyId);
	
	boolean webSiteExists(String website);
	
	boolean emailExists(String emailId);
	
	List<Company> newlyRegisteredCompanies();
	
	void activateRegistration(Integer companyId);

	Company getCompanyForVerification(String verificationId);

	List<Company> findByIds(List<Integer> companyIds);
	
	CompanyTemplate getTemplate(Long companyTemplateId);
	
	List<CompanyTemplate> getTemplateForCompany(Integer companyId);
	
	void deleteTemplate(Long companyTemplateId);

	void saveAddress(Address address);

	void saveCompanyKey(CompanyKey companyKey);
	
	CompanyKey getKeyForCompany(Integer companyId);

	void saveCompanyConfiguration(List<CompanyConfiguration> companyConfigurations);

	Company getByName(String name);

	List<Company> approvedCompanies();

	void approveTheCompany(Integer companyId);

	List<Company> fetchCompaniesByFlagWithPagination(CompanyFlag companyFlag, Map<String, SortDirection> sortMap, Integer pageNumber, Integer pageSize);

	String getCompanyNameByKey(Long companyPK);
}