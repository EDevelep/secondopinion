package org.secondopinion.pharmacy.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.pharmacy.dto.Pharmacyuser;
import org.secondopinion.request.Response;

public interface PharmacyuserDAO extends IDAO<Pharmacyuser,Long >{

	Pharmacyuser getByPharmacyAddressIdAndEmailId(Long pharmacyId, String emailId, boolean withinthesamepharmacy);

	Response<List<Pharmacyuser>> getAssociatedUsers(Long pharmacyId, Integer pageNum, Integer maxResults);

	void updateRetryCountIfLoginFailed(Long pharmacyUserId, Integer retry);

	void updateLastLoginTime(Long pharmacyUserId);

	Pharmacyuser findPharmacyByUserNameAndEmail(String userName);

}