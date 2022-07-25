package org.secondopinion.userMgmt.dao.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.secondopinion.userMgmt.dto.Address;
import org.secondopinion.userMgmt.dto.Company;
import org.secondopinion.userMgmt.dto.CompanyConfiguration;
import org.secondopinion.userMgmt.dto.CompanyKey;
import org.secondopinion.userMgmt.dto.CompanyTemplate;
import org.secondopinion.userMgmt.dto.Registration;
import org.secondopinion.userMgmt.dto.User;
import org.secondopinion.userMgmt.dto.UserRequest.CompanyFlag;
import org.secondopinion.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.request.Response;
import org.secondopinion.common.dto.SortDTO.SortDirection;
import org.secondopinion.userMgmt.dao.AddressDAO;
import org.secondopinion.userMgmt.dao.CompanyConfigurationDAO;
import org.secondopinion.userMgmt.dao.CompanyKeyDAO;
import org.secondopinion.userMgmt.dao.CompanyTemplateDAO;
import org.secondopinion.userMgmt.dao.RegistrationDAO;
import org.secondopinion.userMgmt.dao.UserDAO;

@Component
public class CompanyDAOHibernateImpl extends BaseCompanyDAOHibernate{
	@Autowired
	private AddressDAO addressDao;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private RegistrationDAO registrationDao;
	@Autowired
	private CompanyTemplateDAO companyTemplateDao;
	@Autowired
	private CompanyKeyDAO companyKeyDAO;
	@Autowired
	private CompanyConfigurationDAO companyConfigurationDAO; 
	
	@Override
	@Transactional
	public void save(Company company) throws DataAccessException {
		
		super.save(company);
		
		Integer companyId = company.getCompanyId();
		
		if(company.getRegistration() != null){
			Registration registration = company.getRegistration();
			registration.setCompanyId(companyId);
			
			registrationDao.save(registration);
		}
		
		if(company.getAddress() != null){
			Address address = company.getAddress();
			address.setCompanyId(companyId);
			
			addressDao.save(address);
		}
		
		if(company.getPrimaryUser() != null){
			User user = company.getPrimaryUser();
			user.setCompanyId(companyId);
			
			userDao.save(user);
		}
		
		if(company.getTemplate() != null){
			for(CompanyTemplate template : company.getTemplate()){
				template.setCompanyId(companyId);
			}
			
			companyTemplateDao.save(company.getTemplate());
		}
		
		if(company.getCompanyKey() != null){
			company.getCompanyKey().setCompanyId(companyId);
			companyKeyDAO.save(company.getCompanyKey());
		}
		
		if(company.getCompanyConfigurations() != null &&  company.getCompanyConfigurations().size()>0){
			company.setCompanyIdForConfigurations();
			
			companyConfigurationDAO.save(company.getCompanyConfigurations());
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Company getByName(String name) {
		Company company = null;
		Criterion userIdCriteria = Restrictions.eq(Company.FIELD_name, name);
		List<Company> companies = findByCrieria(userIdCriteria);
		if(companies!=null && companies.size()>0){
			company = companies.get(0);
		}
		return company;
	}

	public AddressDAO getAddressDao() {
		return addressDao;
	}

	public void setAddressDao(AddressDAO addressDao) {
		this.addressDao = addressDao;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public RegistrationDAO getRegistrationDao() {
		return registrationDao;
	}

	public void setRegistrationDao(RegistrationDAO registrationDao) {
		this.registrationDao = registrationDao;
	}

	@Override
	@Transactional(readOnly=true)
	public Company companyDetails(Integer companyId) {
		Company company = getCompany(companyId);
		company.setPrimaryUser(userDao.getPrimaryUser(companyId));
//		company.setAddress(addressDao.getCompanyAddress(companyId));
		company.setTemplate(companyTemplateDao.getTemplatesForCompany(companyId));
		return company;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Company companyAndAddressDetails(Integer companyId) {
		Company company = getCompany(companyId);
		return company;
	}

	private Company getCompany(Integer companyId) {
		Company company = findById(companyId);
		company.setAddress(addressDao.getCompanyAddress(companyId));
		company.setCompanyConfigurations(companyConfigurationDAO.getCompanyConfigurations(companyId));
		return company;
	}

	@Override
	@Transactional(readOnly=true)
	public boolean webSiteExists(String website) {
		Criterion webURLCriteria = Restrictions.eq(Company.FIELD_websiteURL, website);
		List<Company> companies = findByCrieria(webURLCriteria);
		
		return (companies != null && companies.size() >0);
	}

	@Override
	@Transactional(readOnly=true)
	public boolean emailExists(String emailId) {
		Criterion emailCriteria = Restrictions.eq(Company.FIELD_emailId, emailId);
		List<Company> companies = findByCrieria(emailCriteria);
		
		return (companies != null && companies.size() >0);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Company> newlyRegisteredCompanies() {
		Criterion newlyRegisteredCriteria = Restrictions.eq(Company.FIELD_newlyRegistered, 'Y');
		Criterion activeCriteria = Restrictions.eq(Company.FIELD_active, 'N');
		
		List<Company> companies = findByCrieria(Restrictions.and(newlyRegisteredCriteria, activeCriteria));
		return buildCompaniesDetails(companies);
		
	}

	private List<Company> buildCompaniesDetails(List<Company> companies) {
		if(CollectionUtils.isEmpty(companies)) {
			return new ArrayList<Company>();
		}
		companies.stream().map(co -> {
			co.setAddress(addressDao.getCompanyAddress(co.getCompanyId()));
			co.setPrimaryUser(userDao.getPrimaryUser(co.getCompanyId()));
			co.setCompanyConfigurations(companyConfigurationDAO.getCompanyConfigurations(co.getCompanyId()));
			return co;
		}).collect(Collectors.toList());
		return companies;
	}

	private static final String activateRegistration = "update Company set active = 'Y', newlyRegistered = 'N' where companyId = :companyId";
	
	@Override
	@Transactional
	public void activateRegistration(Integer companyId) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", companyId);
		
		executeQuery(activateRegistration, params);
	}

	@Override
	@Transactional(readOnly=true)
	public Company getCompanyForVerification(String verificationId) {
		Criterion verificationIdCriteria = Restrictions.eq(Company.FIELD_verificationId, verificationId);
		
		List<Company> companies = findByCrieria(verificationIdCriteria);
		
		if(companies != null){
			return companies.get(0);
		}
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Company> findByIds(List<Integer> companyIds) {
		Criterion emailCriteria = Restrictions.in(Company.FIELD_companyId, companyIds);
		return findByCrieria(emailCriteria);
	}
	
//	private static final String update_company = "update Company set logoImageUrl = :logoImageUrl where companyId = :companyId";
	
//	@Override
//	@Transactional
//	public void update(Company company) {
//		
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("companyId", company.getCompanyId());
//		
//		executeQuery(update_company, params);
//	}
	
	@Override
	@Transactional(readOnly=true)
	public CompanyTemplate getTemplate(Long companyTemplateId) {
		return companyTemplateDao.findById(companyTemplateId);
	}

	@Override
	@Transactional
	public void deleteTemplate(Long companyTemplateId) {
		CompanyTemplate template = new CompanyTemplate();
		template.setCompanyTemplateId(companyTemplateId);
		companyTemplateDao.delete(template);
	}
	
	public CompanyTemplateDAO getCompanyTemplateDao() {
		return companyTemplateDao;
	}

	public void setCompanyTemplateDao(CompanyTemplateDAO companyTemplateDao) {
		this.companyTemplateDao = companyTemplateDao;
	}

	@Override
	@Transactional(readOnly=true)
	public List<CompanyTemplate> getTemplateForCompany(Integer companyId) {
		return companyTemplateDao.getTemplatesForCompany(companyId);
	}

	@Override
	@Transactional
	public void saveAddress(Address address) {
		addressDao.save(address);
	}

	/**
	 * @return the companyKeyDAO
	 */
	public CompanyKeyDAO getCompanyKeyDAO() {
		return companyKeyDAO;
	}

	/**
	 * @param companyKeyDAO the companyKeyDAO to set
	 */
	public void setCompanyKeyDAO(CompanyKeyDAO companyKeyDAO) {
		this.companyKeyDAO = companyKeyDAO;
	}

	@Override
	@Transactional
	public void saveCompanyKey(CompanyKey companyKey) {
		companyKeyDAO.save(companyKey);
	}

	@Override
	@Transactional(readOnly=true)
	public CompanyKey getKeyForCompany(Integer companyId) {
		return companyKeyDAO.getKeyForCompany(companyId);
	}

	public CompanyConfigurationDAO getCompanyConfigurationDAO() {
		return companyConfigurationDAO;
	}

	public void setCompanyConfigurationDAO(CompanyConfigurationDAO companyConfigurationDAO) {
		this.companyConfigurationDAO = companyConfigurationDAO;
	}

	@Override
	public void saveCompanyConfiguration(List<CompanyConfiguration> companyConfigurations) {
		companyConfigurationDAO.save(companyConfigurations);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Company> approvedCompanies() {
		Criterion approvedCriteria = Restrictions.eq(Company.FIELD_approved, 'Y');
		//Criterion activeCriteria = Restrictions.eq(Company.FIELD_active, 'N');
		List<Company> companies = findByCrieria(approvedCriteria);
		return buildCompaniesDetails(companies);
	}
	
	private static final String approveCompany = "update Company set " + Company.FIELD_approved + " = 'Y' where " + Company.FIELD_companyId + " = :companyId";
	
	@Override
	@Transactional
	public void approveTheCompany(Integer companyId) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", companyId);
		
		executeQuery(approveCompany, params);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Company> fetchCompaniesByFlagWithPagination(CompanyFlag companyFlag, Map<String, SortDirection> sortMap, Integer pageNumber, Integer pageSize) {
		Criterion criterion = Restrictions.eq(companyFlag.getColumnName(), companyFlag.getCharYorN()); 
		
		
		List<Company> companies = new ArrayList<>();
		if(Objects.isNull(pageNumber ) || pageNumber == 0 || Objects.isNull(pageSize) || pageSize == 0) {
			companies = findByCrieria(criterion);
		} else {
			List<Order> orders = new ArrayList<>();
			if(sortMap != null) {
				orders = sortMap.entrySet().stream().map(entry -> {
					Order order = null;
					if(entry.getValue().getIsAsc()) {
						order = Order.asc(entry.getKey());
					} else {
						order = Order.desc(entry.getKey());
					}
					return order;
				}).collect(Collectors.toList());
			}
			Response<List<Company>> companyMap = findByCrieria(Arrays.asList(criterion), orders, pageNumber,pageSize) ;
			companies = companyMap.getData();
		}
		
		return buildCompaniesDetails(companies);
	}

	//======================
	private static final String companyNameQuery = "Select id,"+ Company.FIELD_name +" from company where "+ Company.FIELD_companyId +" = :"+Company.FIELD_companyId;
	@Override
	@Transactional(readOnly=true)
	public String getCompanyNameByKey(Long companyPK) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put(Company.FIELD_companyId , companyPK);
		
		List<Map<String, Object>> objects= findBySqlQueryMapTransformer(companyNameQuery, params, buildScalarMapForRoles());
		for (Iterator<Map<String, Object>> iterator = objects.iterator(); iterator.hasNext();) {
			Map<String, Object> companyNameMap = iterator.next();
			return (String) companyNameMap.get(Company.FIELD_name);
		}
		return "";
	}
	
	private Map<String, Type> buildScalarMapForRoles() {
		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(Company.FIELD_name, StandardBasicTypes.STRING);
		
		return scalarMapping;
	}
}