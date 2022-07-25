package org.secondopinion.doctor.service.impl;

import java.util.Objects;

import org.secondopinion.doctor.dao.DoctorDAO;
import org.secondopinion.doctor.dao.RegistrationDAO;
import org.secondopinion.doctor.domain.BaseDoctor;
import org.secondopinion.doctor.domain.BaseRegistration;
import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.doctor.dto.Registration;
import org.secondopinion.doctor.service.IDoctorLoginService;
import org.secondopinion.utils.MailProperties;
import org.secondopinion.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DoctorLoginServiceImpl implements IDoctorLoginService {

	@Autowired
	private DoctorDAO doctorDAO;

	@Autowired
	private RegistrationDAO registrationDAO;

	@Autowired
	private MailProperties mailProperties;

	@Override
	@Transactional
	public Doctor login(String userName, String password,String type) {

		Doctor doctor = doctorDAO.findByDoctorAndNutrations(userName,type);

		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException(
					"Invalid UserID or Password.");
		}
		String supportMailRequest = "Please contact support@qontrack.com for further assistance. ";
		if (doctor.getLocked() == 'Y') {
			throw new IllegalArgumentException(
					"Your User ID has been locked  due to 3 consecutive incorrect attempts. " + supportMailRequest);
		}

		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_doctorId,
				doctor.getDoctorId());
		if (Objects.isNull(registration) || Objects.isNull(registration.getActive()) || registration.getActive() == 'N'
				|| doctor.getActive() == 'N') {
			throw new IllegalArgumentException(
					"Your Email Id Is  Unverified");
		}

		String hashedPasswordFromDb = doctor.getPassword();
		if (!UserHelper.checkpw(password, hashedPasswordFromDb)) {
			updateRetryCountIfLoginFailed(doctor.getDoctorId(), doctor.getRetry());
			throw new IllegalArgumentException("Invalid UserID or Password.");
		}
		updateLastLoginTime(doctor.getDoctorId());
		return doctor;
	}

	private void updateLastLoginTime(Long doctorId) {
		doctorDAO.updateLastLoginTime(doctorId);
	}

	private void updateRetryCountIfLoginFailed(Long doctorId, Integer retry) {
		doctorDAO.updateRetryCountIfLoginFailed(doctorId, retry);
	}

	/**
	 * @return the mailProperties
	 */
	public MailProperties getMailProperties() {
		return mailProperties;
	}

	/**
	 * @param mailProperties the mailProperties to set
	 */
	public void setMailProperties(MailProperties mailProperties) {
		this.mailProperties = mailProperties;
	}

}
