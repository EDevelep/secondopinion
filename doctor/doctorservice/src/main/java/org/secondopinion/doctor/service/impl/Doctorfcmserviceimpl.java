package org.secondopinion.doctor.service.impl;

import java.util.Objects;

import javax.transaction.Transactional;

import org.secondopinion.doctor.dao.DoctorfcmtokenDAO;
import org.secondopinion.doctor.domain.BaseDoctorfcmtoken;
import org.secondopinion.doctor.dto.Doctorfcmtoken;
import org.secondopinion.doctor.service.Doctorfcmservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Doctorfcmserviceimpl implements Doctorfcmservice {
	
	@Autowired
	private DoctorfcmtokenDAO doctorfcmtokenDAO;

	@Override
	@Transactional
	public Doctorfcmtoken saveDoctorfcmToken(Doctorfcmtoken doctorfcmtoken) {
		Doctorfcmtoken dbDoctorfcmtoken = doctorfcmtokenDAO.findOneByProperty(BaseDoctorfcmtoken.FIELD_doctorId, doctorfcmtoken.getDoctorId());
		if(Objects.isNull(dbDoctorfcmtoken)) {
			dbDoctorfcmtoken = Doctorfcmtoken.builddoctorfcmtokenObject(doctorfcmtoken, new Doctorfcmtoken());
		} else {
			dbDoctorfcmtoken = Doctorfcmtoken.builddoctorfcmtokenObject(doctorfcmtoken, dbDoctorfcmtoken);
		}
		dbDoctorfcmtoken.setActive('Y');
		dbDoctorfcmtoken.setDoctorId(doctorfcmtoken.getId());
		doctorfcmtokenDAO.save(dbDoctorfcmtoken);
		return dbDoctorfcmtoken;
	}

}
