package org.secondopinion.caretaker.serviceimpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.secondopinion.caretaker.dao.AddressDAO;
import org.secondopinion.caretaker.dao.CaretakerDAO;
import org.secondopinion.caretaker.dao.CertificationDAO;
import org.secondopinion.caretaker.dao.FeedetailsDAO;
import org.secondopinion.caretaker.dao.PersonaldetailDAO;
import org.secondopinion.caretaker.dao.RegistrationDAO;
import org.secondopinion.caretaker.dto.Address;
import org.secondopinion.caretaker.dto.CareTakerSearchRequest;
import org.secondopinion.caretaker.dto.Caretaker;
import org.secondopinion.caretaker.dto.Certification;
import org.secondopinion.caretaker.dto.Feedetails;
import org.secondopinion.caretaker.dto.Personaldetail;
import org.secondopinion.caretaker.dto.ProfileCompletedDTO;
import org.secondopinion.caretaker.dto.Registration;
import org.secondopinion.caretaker.helper.CareTakerServiceHelper;
import org.secondopinion.caretaker.service.CareTakerService;
import org.secondopinion.utils.OtpUtil;
import org.secondopinion.utils.SendSms;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.UserHelper;
import org.secondopinion.utils.EmailUtil.MailContentEnum;
import org.secondopinioncaretaker.domain.BaseCaretaker;
import org.secondopinioncaretaker.domain.BaseRegistration;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.EmailUtil;
import org.secondopinion.utils.MailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableList;

@Service
public class CareTakeServiceImpl implements CareTakerService {

	@Autowired
	private CaretakerDAO caretakerDAO;
	@Autowired
	private RegistrationDAO registrationDAO;
	@Autowired
	private AddressDAO addressDAO;
	@Autowired
	private PersonaldetailDAO personaldetailDAO;

	@Autowired
	private FeedetailsDAO feedetailsDAO;
	@Autowired
	private MailProperties mailProperties;
	@Value("${loginLinkForUI}")
	private String loginLinkForUI;
	@Autowired
	private CertificationDAO certificationDAO;

	@Override
	@Transactional
	public void registerCareTaker(Caretaker caretaker) {

		Caretaker.ValidateCareTaker(caretaker);

		UserHelper.passwordValidation(caretaker.getPassword());
		Caretaker caretakerByEmail = caretakerDAO.findOneByProperty(BaseCaretaker.FIELD_emailId,
				caretaker.getEmailId());
		if (Objects.nonNull(caretakerByEmail)) {
			throw new IllegalArgumentException(
					"Caretaker already registered with this email ID. Use another email ID.");
		}
		caretaker.markInActive();
		caretaker.setPassword(UserHelper.getHashedPassWord(caretaker.getPassword()));
		boolean phonenumber = verifyPhoneNumberExists(caretaker.getCellNumber());
		if (phonenumber == true) {
			throw new IllegalArgumentException("Your  phone number already exists.");
		}
		caretakerDAO.save(caretaker);

		String verificationId = getVerificationId();
		String hashedVerificationId = UserHelper.getHashedPassWord(verificationId);
		Registration registration = Registration.build(caretaker, hashedVerificationId);
		registrationDAO.save(registration);
		Integer otp = OtpUtil.otpToPhone(caretaker.getCellNumber(), "Email Verification");
		registration.setEmailotp(otp);
		registrationDAO.save(registration);
		CareTakerServiceHelper.sendEmailOTP(caretaker, otp, mailProperties);

	}

	@Override
	@Transactional
	public void emailVerification(String emailid, Integer emailotp) {
		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_emailotp, emailotp);
		if (Objects.isNull(registration)) {
			throw new IllegalArgumentException("Email verification details not found.");
		}
		Caretaker caretaker = caretakerDAO.findById(registration.getCaretakerId());
		if (Objects.isNull(caretaker)) {
			throw new IllegalArgumentException("Caretaker has not found with the requested email.");
		}
		registration.markEmailVerified();
		String cellNumber = caretaker.getCellNumber();
		if (!registration.getEmailotp().equals(emailotp)) {
			throw new IllegalArgumentException("Invalid otp.");
		}

		Integer otp = OtpUtil.otpToPhone(cellNumber, "Phone Number Verification");
		registration.setOtp(otp);

		registrationDAO.save(registration);
		SendSms.sendSms("Hii Welcome To CureMetric Your Otp is .." + otp, cellNumber);

		CareTakerServiceHelper.sendPhoneOTP(caretaker, otp, mailProperties);
	}

	@Override
	@Transactional
	public void phoneVerification(String cellNumber, Integer otp) {

		Caretaker caretaker = caretakerDAO.findOneByProperty(Caretaker.FIELD_cellNumber, cellNumber);
		if (Objects.isNull(caretaker)) {
			throw new IllegalArgumentException("Caretaker details not found.");
		}
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_caretakerId,
				caretaker.getCaretakerId());
		if (Objects.isNull(registration)) {
			throw new IllegalArgumentException("Registration details not found.");
		}
		if (!registration.getOtp().equals(otp)) {
			throw new IllegalArgumentException("Otp is invalid.");
		}
		registration.markPhoneVerified();
		caretaker.markAsActive();

		registrationDAO.save(registration);
		caretakerDAO.save(caretaker);

		CareTakerServiceHelper.sendAccountActivationEmail(caretaker, loginLinkForUI, mailProperties);

	}

	private boolean verifyPhoneNumberExists(String cellNumber) {
		Caretaker caretaker = caretakerDAO.findOneByProperty(BaseCaretaker.FIELD_cellNumber, cellNumber);
		return (caretaker != null);
	}

	@SuppressWarnings("unused")
	private MailProperties getMailProperties(Caretaker caretaker) {
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(caretaker.getEmailId());
		return properties;
	}

	private String getVerificationId() {
		return UUID.randomUUID().toString();
	}

	@Override
	@Transactional
	public void resendOTPForEmail(String emailid) {

		Caretaker caretaker = caretakerDAO.findOneByProperty(BaseCaretaker.FIELD_emailId, emailid);

		if (Objects.isNull(caretaker)) {
			throw new IllegalArgumentException("Caretaker is not found for given emailId.");
		}

		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_caretakerId,
				caretaker.getCaretakerId());

		if (Objects.isNull(registration)) {
			throw new IllegalArgumentException("Registration details is not found for given emailId.");
		}
		Integer otp = OtpUtil.otpToPhone(caretaker.getCellNumber(), "Email Verification");
		
		registration.setEmailotp(otp);
		registrationDAO.save(registration);
		CareTakerServiceHelper.sendEmailOTP(caretaker, otp, mailProperties);

	}

	@Override
	@Transactional
	public Boolean verifyEmailIdExists(String emailId) {
		Caretaker caretaker = caretakerDAO.findOneByProperty(BaseCaretaker.FIELD_emailId, emailId);
		return (caretaker != null);

	}

	@Override
	@Transactional
	public void resetPassword(String emailId, String newPassword, Integer otp) {
		Caretaker caretaker = caretakerDAO.findOneByProperty(BaseCaretaker.FIELD_emailId, emailId);

		if (Objects.isNull(caretaker)) {
			throw new IllegalArgumentException("Caretaker is not found for given emailId.");
		}
		UserHelper.passwordValidation(newPassword);

		if (Objects.isNull(caretaker)) {
			throw new IllegalArgumentException("Doctor not found");
		}
		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_caretakerId,
				caretaker.getCaretakerId());

		if (!registration.getEmailotp().equals(otp)) {
			throw new IllegalArgumentException("Otp is invalid.");
		}
		caretaker.setPassword(UserHelper.getHashedPassWord(newPassword));

		caretakerDAO.save(caretaker);

	}

	@Override
	@Transactional
	public void resendOTPForPhone(String phonenumber) {
		Caretaker caretaker = caretakerDAO.findOneByProperty(BaseCaretaker.FIELD_cellNumber, phonenumber);

		if (Objects.isNull(caretaker)) {
			throw new IllegalArgumentException("Caretaker is not found for given emailId.");
		}

		Registration registration = registrationDAO.findOneByProperty(BaseRegistration.FIELD_caretakerId,
				caretaker.getCaretakerId());

		if (Objects.isNull(registration)) {
			throw new IllegalArgumentException("Registration details is not found for given emailId.");
		}

		registration.markisInActive();
		Integer otp = OtpUtil.otpToPhone(caretaker.getCellNumber(), "Phone Number Verification");
		registration.setOtp(otp);

		registrationDAO.save(registration);
		SendSms.sendSms("Hii Welcome To CureMetric Your Otp is .." + otp, caretaker.getCellNumber());

		CareTakerServiceHelper.sendPhoneOTP(caretaker, otp, mailProperties);
	}

	@Override
	@Transactional
	public void saveAddress(Address address) {
		address.setActive('Y');
		addressDAO.save(address);
		Caretaker caretaker = caretakerDAO.findOneByProperty(Caretaker.FIELD_caretakerId, address.getCaretakerId());
		caretaker.setIsProfileCompleted('Y');
		caretakerDAO.save(caretaker);
	}

	@Override
	public void savePersonaldetail(Personaldetail personaldetail) {
		personaldetail.setActive('Y');
		personaldetailDAO.save(personaldetail);

	}

	@Override
	@Transactional
	public Address getAddress(Long caretakerId) {
		Address address = addressDAO.findOneByProperty(Address.FIELD_caretakerId, caretakerId);

		if (Objects.isNull(address)) {
			throw new IllegalArgumentException("Address is not found for given caretakerId.");
		}
		return address;
	}

	@Override
	@Transactional
	public Personaldetail getPersonaldetail(Long caretakerId) {
		Personaldetail personaldetail = personaldetailDAO.findOneByProperty(Personaldetail.FIELD_caretakerId,
				caretakerId);
		if (Objects.isNull(personaldetail)) {
			throw new IllegalArgumentException("Personaldetail is not found for given caretakerId.");
		}
		return personaldetail;

	}

	@Override
	@Transactional
	public Collection<Address> findCaretakerAddressBycaretakerId(Long caretakerId) {
		return addressDAO.findCaretakerAddressBycaretakerId(caretakerId);
	}

	@Override
	@Transactional
	public void updateCaretaker(Long caretakerId, Caretaker caretaker) {
		Caretaker dbcaretaker = caretakerDAO.findById(caretakerId);
		if (Objects.isNull(dbcaretaker)) {
			throw new IllegalArgumentException("Caretaker is not found for given caretakerId.");
		}
		
		dbcaretaker=Caretaker.buildUpdateCaretaker(dbcaretaker,caretaker);
		caretakerDAO.save(dbcaretaker);
	}

	@Override
	@Transactional
	public Caretaker getCaretaker(Long caretakerId) {
		return caretakerDAO.findById(caretakerId);
	}

	@Override
	public String emailOtpVerification(String emailid, Integer emailotp) {
		Caretaker caretaker = caretakerDAO.findOneByProperty(Caretaker.FIELD_emailId, emailid);
		if (Objects.isNull(caretaker)) {
			throw new IllegalArgumentException("Caretaker is not found for given emailid.");
		}

		Registration registration = registrationDAO.findOneByProperty(Registration.FIELD_caretakerId,
				caretaker.getCaretakerId());
		if (!registration.getEmailotp().equals(emailotp)) {
			throw new IllegalArgumentException("Otp is Invalid.");
		}
		return "Otp  Verification Success.";
	}

	@Override
	@Transactional
	public List<Caretaker> findAllCaretaker(CareTakerSearchRequest careTakerSearchRequest) {
		return caretakerDAO.findAllcareTakerBySearchRequest(careTakerSearchRequest);
	}

	@Override
	@Transactional
	public ProfileCompletedDTO isProfileCompleted(Long caretakerId) {
		ProfileCompletedDTO profileComplted = new ProfileCompletedDTO();
		Caretaker caretaker = caretakerDAO.findOneByProperty(Caretaker.FIELD_caretakerId, caretakerId);
		if (Objects.isNull(caretaker)) {
			throw new IllegalArgumentException("Caretaker is not found for given  caretakerId .");
		}

		Feedetails feedetails = feedetailsDAO.findOneByProperty(Feedetails.FIELD_caretakerId,
				caretaker.getCaretakerId());
		if (Objects.isNull(feedetails)) {
			profileComplted.setIspersonalDetailCompleted('N');
			profileComplted.setIsAddressCompleted('N');
			profileComplted.setIsFeeDetailCompleted('N');
			return profileComplted;
		}
		Personaldetail personaldetail = personaldetailDAO.findOneByProperty(Personaldetail.FIELD_caretakerId,
				caretaker.getCaretakerId());

		if (Objects.isNull(personaldetail)) {
			profileComplted.setIspersonalDetailCompleted('N');
			profileComplted.setIsAddressCompleted('N');
			profileComplted.setIsFeeDetailCompleted('Y');
			return profileComplted;
		}

		Address address = addressDAO.findOneByProperty(Address.FIELD_caretakerId, caretaker.getCaretakerId());
		if (Objects.isNull(address)) {
			profileComplted.setIspersonalDetailCompleted('Y');
			profileComplted.setIsAddressCompleted('N');
			profileComplted.setIsFeeDetailCompleted('Y');
			return profileComplted;
		}

		profileComplted.setIspersonalDetailCompleted('Y');
		profileComplted.setIsAddressCompleted('Y');
		profileComplted.setIsFeeDetailCompleted('Y');
		return profileComplted;
	}

	@Override
	@Transactional
	public void saveFeeDetail(Feedetails feedetails) {
		feedetailsDAO.save(feedetails);

	}

	@Override
	@Transactional
	public void saveCertification(Certification certification) {
		certification.setActive('Y');
		certificationDAO.save(certification);

	}

	@Override
	@Transactional
	public Collection<Feedetails> getCareTakerFeeDetails(Long caretakerId) {
		return feedetailsDAO.findCaretakerFeeDetailsBycaretakerId(caretakerId);
	}

	@Override
	@Transactional
	public Certification getCertificationById(Long certificationId) {
		return certificationDAO.findCertificationById(certificationId);

	}

	@Override
	@Transactional
	public Collection<Certification> getCaretakerCertifications(Long caretakerId) {
		return certificationDAO.findCaretakerCertificationsByCaretakerId(caretakerId);
	}

	@Override
	public void deleteCaretakerCertication(Long certificationId) {

	}

	@Override
	@Transactional
	public Feedetails getFeeDetailsById(Long caretakerId) {

		return feedetailsDAO.findfeeDetailsById(caretakerId);
	}

	@Override
	public void deleteCaretakerFeeDetails(Long feeDetailsId) {
		feedetailsDAO.findfeeDetailsById(feeDetailsId);
	}

	@Override
	public String verifyOldPassword(String emailId, String password) {
		Caretaker caretaker = caretakerDAO.findOneByProperty(Caretaker.FIELD_emailId, emailId);
		String hashedPasswordFromDb = caretaker.getPassword();
		if (!UserHelper.checkpw(password, hashedPasswordFromDb)) {
			throw new IllegalArgumentException("Invalid Old  Password.");
		}
		return null;
	}

	@Override
	@Transactional
	public boolean iscareTakerNameExit(String caretakenName) {
		Caretaker caretaker = caretakerDAO.findOneByProperty(Caretaker.FIELD_userName, caretakenName);
		if (caretaker == null) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public Caretaker getCaretakerDetails(String emailId) {
		Caretaker caretaker = caretakerDAO.findOneByProperty(BaseCaretaker.FIELD_emailId,
				emailId);
		if (Objects.isNull(caretaker)) {
			throw new IllegalArgumentException(
				"Caretaker Not registered with this email ID");
		}
		return caretaker;
	}

	@Override
	@Transactional
	public Collection<Caretaker> findAllCaretaker() {
	
		return caretakerDAO.findAll();
	}

}
