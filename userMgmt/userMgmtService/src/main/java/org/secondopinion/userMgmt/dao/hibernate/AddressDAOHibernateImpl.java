package org.secondopinion.userMgmt.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.userMgmt.dto.Address;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddressDAOHibernateImpl extends BaseAddressDAOHibernate{

	@Override
	@Transactional
	public Address getCompanyAddress(Integer companyId) {
		Criterion companyCriteria = Restrictions.eq(Address.FIELD_companyId, companyId);
	
		List<Address> addresses = findByCrieria(companyCriteria);
		
		if(addresses!=null && addresses.size()>0){
			return addresses.get(0);
		}
		return null;
	}
}