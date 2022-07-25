package org.secondopinion.pharmacy.service;

import java.util.Collection;
import java.util.List;

import org.secondopinion.pharmacy.dto.Pharmacy;
import org.secondopinion.pharmacy.dto.PharmacyUserUpadteDTO;
import org.secondopinion.pharmacy.dto.Pharmacyuser;
import org.secondopinion.pharmacy.dto.Roles;
import org.secondopinion.pharmacy.dto.UpdatePasswordRequest;
import org.secondopinion.request.Response;

public interface IPharmacyuserRegistrationService {

	Pharmacyuser addPharmacyuser(Pharmacyuser pharmacyuser, boolean createFromPharmacy, List<String> roleNames);

	void updatePharmacyuser(PharmacyUserUpadteDTO pharmacyUserUpadteDTO);

	void resetPasswordByPharmacyUserId(UpdatePasswordRequest updatePasswordRequest);

	void resetPasswordByVerificationId(UpdatePasswordRequest updatePasswordRequest);

	void resetPasswordForPharmacyuser(UpdatePasswordRequest updatePasswordRequest);

	void verifyemail(Long pharmacyId, String emailId);

	void activateOrDeactivateUser(Long pharmacyUserId, boolean deactivateUser);

	Response<List<Pharmacyuser>> getAssociatedUsers(Long pharmacyaddressId, Integer pageNum, Integer maxResults);

	Pharmacyuser getPrimaryDetails(Long pharmacyuserid);

	void updatePrimaryDetails(Pharmacyuser pharmacy);

	Pharmacyuser getPharmacyuserByVerificationId(String verificationId, String passwordTypeEnum);

	Collection<Roles> getAllRoles();

	void forgotPassword(String emailId, String uiHostURL);

	Pharmacyuser getByPharmacyAddressIdAndEmailId(Long pharmacyAddressId, String emailId);

	Pharmacyuser addPrimaryPharmacyuser(Pharmacyuser pharmacyuser);



	void resetPasswordForPharmacyUserNew(UpdatePasswordRequest updatePasswordRequest);

}
