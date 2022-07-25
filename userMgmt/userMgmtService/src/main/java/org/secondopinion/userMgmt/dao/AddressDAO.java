package org.secondopinion.userMgmt.dao; 

import org.secondopinion.userMgmt.dto.Address;

import org.secondopinion.dao.IDAO;

public interface AddressDAO extends IDAO<Address,Integer >{

	Address getCompanyAddress(Integer companyId);
}