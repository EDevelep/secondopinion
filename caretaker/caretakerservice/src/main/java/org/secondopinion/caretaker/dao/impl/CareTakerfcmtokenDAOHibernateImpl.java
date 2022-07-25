package org.secondopinion.caretaker.dao.impl;

import java.util.Objects;

import org.secondopinion.caretaker.dto.Caretakerfcmtoken;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CareTakerfcmtokenDAOHibernateImpl extends BaseCareTakerfcmtokenDAOHibernate{
	
	@Override
	@Transactional
	public void save(Caretakerfcmtoken caretaketfcmtoken) {
		
		if(Objects.isNull(caretaketfcmtoken.getCaretakerFCMtokenId())) {
			caretaketfcmtoken.setActive('Y');
		}
		
		super.save(caretaketfcmtoken);
	}
	
	
}