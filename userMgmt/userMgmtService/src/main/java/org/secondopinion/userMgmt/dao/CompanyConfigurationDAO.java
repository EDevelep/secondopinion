package org.secondopinion.userMgmt.dao; 

import java.util.List;

import org.secondopinion.userMgmt.dto.CompanyConfiguration;

import org.secondopinion.dao.IDAO;

public interface CompanyConfigurationDAO extends IDAO<CompanyConfiguration,Integer >{

	List<CompanyConfiguration> getCompanyConfigurations(Integer companyId);
}