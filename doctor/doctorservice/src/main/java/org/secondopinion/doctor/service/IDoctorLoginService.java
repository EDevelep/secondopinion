package org.secondopinion.doctor.service;

import org.secondopinion.doctor.dto.Doctor;

public interface IDoctorLoginService {

	Doctor login(String userName, String password,String type);



}
