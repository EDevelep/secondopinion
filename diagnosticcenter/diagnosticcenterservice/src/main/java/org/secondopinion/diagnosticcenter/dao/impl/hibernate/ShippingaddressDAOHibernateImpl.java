package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.Objects;

import org.secondopinion.diagnosticcenter.dto.Shippingaddress;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ShippingaddressDAOHibernateImpl extends BaseShippingaddressDAOHibernate{
	
	
	@Override
	@Transactional
	public void save(Shippingaddress shippingaddress) {
		if(Objects.isNull(shippingaddress.getShippingaddressId())) {
			shippingaddress.setActive('Y');
		}
		super.save(shippingaddress);
	}
}