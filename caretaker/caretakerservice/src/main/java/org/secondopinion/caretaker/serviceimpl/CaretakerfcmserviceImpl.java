package org.secondopinion.caretaker.serviceimpl;

import java.util.Objects;

import javax.transaction.Transactional;

import org.secondopinion.caretaker.dao.CareTakerfcmtokenDAO;
import org.secondopinion.caretaker.dto.Caretakerfcmtoken;
import org.secondopinion.caretaker.service.Caretakerfcmservice;
import org.secondopinioncaretaker.domain.BaseCaretakerfcmtoken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class  CaretakerfcmserviceImpl implements  Caretakerfcmservice {
	
	@Autowired
	private CareTakerfcmtokenDAO careTakerfcmtokenDAO;

	@Override
	@Transactional
	public Caretakerfcmtoken saveCaretakerfcmToken(Caretakerfcmtoken doctorfcmtoken) {
		Caretakerfcmtoken dbcaretakerfcmtoken = careTakerfcmtokenDAO.findOneByProperty(BaseCaretakerfcmtoken.FIELD_caretakerId, doctorfcmtoken.getCaretakerId());
		if(Objects.isNull(dbcaretakerfcmtoken)) {
			dbcaretakerfcmtoken = Caretakerfcmtoken.builddoctorfcmtokenObject(doctorfcmtoken, new Caretakerfcmtoken());
		} else {
			dbcaretakerfcmtoken = Caretakerfcmtoken.builddoctorfcmtokenObject(doctorfcmtoken, dbcaretakerfcmtoken);
		}
		dbcaretakerfcmtoken.setActive('Y');
		careTakerfcmtokenDAO.save(dbcaretakerfcmtoken);
		return dbcaretakerfcmtoken;
	}

}
