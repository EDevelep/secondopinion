package org.secondopinion.patient.dto;

public class CountrycodeDTO {

	private Long id;
	private String country;
	private String postalCode;
	private String postalLocation;
	private String state;
	private Long stateId;
	private String district;
	private Long districtId;
	private String province;
	private String provinceId;
	private Double latitude;
	private Double longitude;

	public CountrycodeDTO(Long id, String country, String postalCode, String postalLocation, String state, Long stateId,
			String district, Long districtId, String province, String provinceId, Double latitude, Double longitude) {
		super();
		this.id = id;
		this.country = country;
		this.postalCode = postalCode;
		this.postalLocation = postalLocation;
		this.state = state;
		this.stateId = stateId;
		this.district = district;
		this.districtId = districtId;
		this.province = province;
		this.provinceId = provinceId;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public CountrycodeDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPostalLocation() {
		return postalLocation;
	}

	public void setPostalLocation(String postalLocation) {
		this.postalLocation = postalLocation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	

}
