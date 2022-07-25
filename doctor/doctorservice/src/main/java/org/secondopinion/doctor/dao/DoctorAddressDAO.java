package org.secondopinion.doctor.dao;

import java.util.Collection;

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.DoctorAddress;

public interface DoctorAddressDAO extends IDAO<DoctorAddress, Long >{

	DoctorAddress findDoctorAddressById(Long addressId);

	Collection<DoctorAddress> findDoctorAddressBydoctorId(Long doctorId);
}