package org.secondopinion.pharmacy.dto;

public class PharmacySearchRequest {
	  private Long pharmacyId;
	    private String pharmacyName;
	    private String licenseNumber;
		private Long addressId;
		private Integer pagenumber;
		private Integer maxresult;
	    public Integer getPagenumber() {
			return pagenumber;
		}
		public void setPagenumber(Integer pagenumber) {
			this.pagenumber = pagenumber;
		}
		public Integer getMaxresult() {
			return maxresult;
		}
		public void setMaxresult(Integer maxresult) {
			this.maxresult = maxresult;
		}
		public Long getPharmacyId() {
			return pharmacyId;
		}
		public void setPharmacyId(Long pharmacyId) {
			this.pharmacyId = pharmacyId;
		}
		public String getPharmacyName() {
			return pharmacyName;
		}
		public void setPharmacyName(String pharmacyName) {
			this.pharmacyName = pharmacyName;
		}
		public String getLicenseNumber() {
			return licenseNumber;
		}
		public void setLicenseNumber(String licenseNumber) {
			this.licenseNumber = licenseNumber;
		}
		public Long getAddressId() {
			return addressId;
		}
		public void setAddressId(Long addressId) {
			this.addressId = addressId;
		}
	
}
