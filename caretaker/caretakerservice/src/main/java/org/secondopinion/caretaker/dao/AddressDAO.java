package org.secondopinion.caretaker.dao;

import java.util.Collection;

import org.secondopinion.caretaker.dto.Address;
import org.secondopinion.dao.IDAO;

public interface AddressDAO extends IDAO<Address,Long >{

	Collection<Address> findCaretakerAddressBycaretakerId(Long caretakerId);
}