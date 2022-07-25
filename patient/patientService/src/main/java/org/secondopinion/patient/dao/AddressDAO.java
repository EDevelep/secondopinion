
package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Address;

public interface AddressDAO extends IDAO<Address, Long> {

	Address getByUserIdAndAddressId(Long patientId, Long shippingAddressId);

	List<Address> getAddresByUserId(Long userId);

	Address getAddresByaddressId(Long addressId);
}
