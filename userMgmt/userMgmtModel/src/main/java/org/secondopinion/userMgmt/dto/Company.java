package org.secondopinion.userMgmt.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.userMgmt.domain.BaseCompany;

@Entity
@Table(name = "company")
public class Company extends BaseCompany {
	
	private Registration registration;
	private Address address;
	private User primaryUser;
	private List<CompanyTemplate> template;
	private CompanyKey companyKey;
	private Map<String, CompanyConfiguration> companyConfigurations;

	@Transient
	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	@Transient
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Transient
	public User getPrimaryUser() {
		return primaryUser;
	}

	public void setPrimaryUser(User primaryUser) {
		this.primaryUser = primaryUser;
	}

	/**
	 * @return the template
	 */
	@Transient
	public List<CompanyTemplate> getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(List<CompanyTemplate> template) {
		this.template = template;
	}

	/**
	 * @return the companyKey
	 */ 
	@Transient
	public CompanyKey getCompanyKey() {
		return companyKey;
	}

	/**
	 * @param companyKey the companyKey to set
	 */
	public void setCompanyKey(CompanyKey companyKey) {
		this.companyKey = companyKey;
	}

	@Transient
	public Collection<CompanyConfiguration> getCompanyConfigurations() {
		return companyConfigurations == null || companyConfigurations.isEmpty() ? new ArrayList<>() : companyConfigurations.values();
	}

	public void setCompanyConfigurations(List<CompanyConfiguration> companyConfigurations) {
		this.companyConfigurations = new HashMap<>();
		if(companyConfigurations == null || companyConfigurations.isEmpty()) {
			return;
		}
		for(CompanyConfiguration companyConfiguration : companyConfigurations){
			this.companyConfigurations.put(companyConfiguration.getConfigProperty(), companyConfiguration);
		}
	}

	public void setCompanyIdForConfigurations() {
		if(companyConfigurations != null && companyConfigurations.size()>0){
			for(CompanyConfiguration companyConfiguration : companyConfigurations.values()){
				companyConfiguration.setCompanyId(getCompanyId());
			}
		}
		
	}

	@Transient
	public CompanyConfiguration getCompanyConfiguration(String configName) {
		return companyConfigurations.get(configName);
	}
}