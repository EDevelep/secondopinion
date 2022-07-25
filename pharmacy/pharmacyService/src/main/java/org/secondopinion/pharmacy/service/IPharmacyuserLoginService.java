package org.secondopinion.pharmacy.service;

import org.secondopinion.pharmacy.dto.Pharmacyuser;

public interface IPharmacyuserLoginService {

  Pharmacyuser login(String userName, String password);



}
