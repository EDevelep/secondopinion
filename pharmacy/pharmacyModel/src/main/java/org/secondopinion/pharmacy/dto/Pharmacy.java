package org.secondopinion.pharmacy.dto;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.pharmacy.domain.BasePharmacy;

@Entity
@Table(name = "pharmacy")
public class Pharmacy extends BasePharmacy {

	
	
	private Pharmacyuser primaryPharmacyUser;
	private Pharmacyaddress primaryPharmacyAddress;
	
	
	private String uiHostURL;
	@Transient
	public String getUiHostURL() {
		return uiHostURL;
	}
	public void setUiHostURL(String uiHostURL) {
		this.uiHostURL = uiHostURL;
	}
	
	
	@Transient
	public Pharmacyuser getPrimaryPharmacyUser() {
		return primaryPharmacyUser;
	}
	public void setPrimaryPharmacyUser(Pharmacyuser primaryPharmacyUser) {
		this.primaryPharmacyUser = primaryPharmacyUser;
	}
	@Transient
	public Pharmacyaddress getPrimaryPharmacyAddress() {
		return primaryPharmacyAddress;
	}
	public void setPrimaryPharmacyAddress(Pharmacyaddress primaryPharmacyAddress) {
		this.primaryPharmacyAddress = primaryPharmacyAddress;
	}
	

}