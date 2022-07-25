package org.secondopinion.userMgmt.dto; 

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.userMgmt.domain.BaseAddress; 

@SuppressWarnings({ "serial"})
@Entity 
@Table (name="address")
public class Address extends BaseAddress{

	@Override
	public String toString() {
		return getAddress1() + ", " + getAddress2() + ", " + getCity() + ", " + getState() + "," + getZip();
	}
	
}