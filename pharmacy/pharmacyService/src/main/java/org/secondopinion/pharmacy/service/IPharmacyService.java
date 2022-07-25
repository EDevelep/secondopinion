package org.secondopinion.pharmacy.service;

import java.util.Collection;
import java.util.List;
import org.secondopinion.pharmacy.dto.Personaldetail;
import org.secondopinion.pharmacy.dto.Pharmacy;
import org.secondopinion.pharmacy.dto.PharmacySearchRequest;
import org.secondopinion.pharmacy.dto.Pharmacyaddress;
import org.secondopinion.pharmacy.dto.ProfileCompltedDTO;
import org.secondopinion.request.Response;
import org.springframework.web.multipart.MultipartFile;

public interface IPharmacyService {

	void registerPharmacy(Pharmacy pharmacy);

	Collection<Pharmacy> fetchAllPharmacies();

	Response<List<Pharmacy>> getAllPharmacyBySearchCritieria(PharmacySearchRequest pharmacySearchRequest);

	Pharmacy fetchByPharmacyId(Long pharmacyId);

	Pharmacy fetchPharmacyByLicenseNumber(String licenseNumber);

	Boolean isEmailExistOrNot(String email);

	Boolean isphoneExistOrNot(String phone);

	Response<List<Pharmacyaddress>> getAddressesOfPharmacy(Long pharmacyId, Integer pageNum, Integer maxResults);

	void saveAddressesOfPharmacy(Pharmacyaddress address);

	void emailVerification(String emilid, Integer emailotp);

	void emailVerification(String verificationId);

	String otpVerificationForPhoneNumber(Integer emailotp);

	String otpVerificationForEmail(Integer emailotp, String emailId);

	void updateAddressesOfPharmacy(Pharmacyaddress address);

	void phoneverification(String phonenumber, Integer otp);

	void uploadProfilePic(Long phrmacyId, MultipartFile profilePic);

	void requestOTPForPhone(String phonenumber);

	void requestOTPForEamil(String email);

	void savePersonaldetail(Personaldetail personaldetail);

	Personaldetail getPersonaldetail(Long pharmacyId);

	void resendOTPForEamil(String email);

	boolean oldPaswwordVerification(String emailid, String paswword);

	void requestOTPForEamilForPharmacyUser(String email);

	void emailVerificationForPharmacyUser(String emilid, Integer emailotp);

	void phoneverificationForPharmacyUser(String phonenumber, Integer otp);

	ProfileCompltedDTO isProfileComplted(Long pharmacyId);

	List<Pharmacy> getAssoctedPharmcy(Long pharmacyadminId);

	Boolean isUserExistOrNot(String userName);
}
