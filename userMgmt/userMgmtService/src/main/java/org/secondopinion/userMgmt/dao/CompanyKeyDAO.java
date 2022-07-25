package org.secondopinion.userMgmt.dao; 

import org.secondopinion.userMgmt.dto.CompanyKey;

import org.secondopinion.dao.IDAO;

public interface CompanyKeyDAO extends IDAO<CompanyKey,Long >{
	CompanyKey getKeyForCompany(Integer companyId);
}