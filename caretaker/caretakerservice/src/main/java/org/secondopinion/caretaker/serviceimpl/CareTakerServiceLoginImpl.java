package org.secondopinion.caretaker.serviceimpl;

import java.util.Objects;

import org.secondopinion.caretaker.dao.CaretakerDAO;
import org.secondopinion.caretaker.dao.RegistrationDAO;
import org.secondopinion.caretaker.dto.Caretaker;
import org.secondopinion.caretaker.dto.Registration;
import org.secondopinion.caretaker.service.CareTakerLoginService;
import org.secondopinion.utils.UserHelper;
import org.secondopinioncaretaker.domain.BaseCaretaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CareTakerServiceLoginImpl implements CareTakerLoginService {

	@Autowired
	private CaretakerDAO caretakerDAO;
	@Autowired
	private RegistrationDAO registrationDAO;

	@Override
	@Transactional
	public Caretaker login(String userName, String password) {

		Caretaker caretaker = caretakerDAO.findCaretakerBynameAndEmail(userName);
		if (Objects.isNull(caretaker)) {
			throw new IllegalArgumentException(
					"You have not registered with us with the email address that you have specified.");
		}

		Registration registration = registrationDAO.findOneByProperty(BaseCaretaker.FIELD_caretakerId,
				caretaker.getCaretakerId());
		if (Objects.isNull(registration) || Objects.isNull(registration.getActive()) || registration.getActive() == 'N'
				|| caretaker.getActive() == 'N') {
			throw new IllegalArgumentException(
					" Your Email Id Is  Unverified.");
		}

		String hashedPasswordFromDb = caretaker.getPassword();
		if (!UserHelper.checkpw(password, hashedPasswordFromDb)) {
			throw new IllegalArgumentException("Invalid UserID or Password.");
		}

		updateLastLoginTime(caretaker.getCaretakerId());

		return caretaker;
	}

	private void updateLastLoginTime(Long caretakerId) {
		caretakerDAO.updateLastLoginTime(caretakerId);

	}

}
