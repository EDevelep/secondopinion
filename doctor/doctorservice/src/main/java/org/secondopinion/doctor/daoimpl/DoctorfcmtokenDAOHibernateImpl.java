package org.secondopinion.doctor.daoimpl;

import java.util.Objects;

import org.secondopinion.doctor.dto.Doctorfcmtoken;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DoctorfcmtokenDAOHibernateImpl extends BaseDoctorfcmtokenDAOHibernate{
	
	@Override
	@Transactional
	public void save(Doctorfcmtoken doctorfcmtoken) {
		
		if(Objects.isNull(doctorfcmtoken.getDoctorFCMtokenId())) {
			doctorfcmtoken.setActive('Y');
		}
		
		super.save(doctorfcmtoken);
	}
	
	
}