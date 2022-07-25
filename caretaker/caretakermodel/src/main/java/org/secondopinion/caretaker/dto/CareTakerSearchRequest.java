package org.secondopinion.caretaker.dto;

public class CareTakerSearchRequest {

	private String careTakerName;
	private String emailId;
	private String gender;
	private Integer rating;
	private Integer totalExperience;

	// specialization
	private String specializations;

	// address
	private Double latitude;
	private Double longitude;
	private String city;
	private String state;

	// fee details
	private Double minFee;
	private Double maxFee;
	private String feeType;

	// certification name
	private String instituteName;
	private String degree;
	private Integer yearGraduated;

	// personal detail
	private String highestDegree;
	private String ethinicity;
	private String nationality;
	private String preferredlanguages;
	private String nativelanguage;

	public String getCareTakerName() {
		return careTakerName;
	}

	public void setCareTakerName(String careTakerName) {
		this.careTakerName = careTakerName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(Integer totalExperience) {
		this.totalExperience = totalExperience;
	}

	public String getSpecializations() {
		return specializations;
	}

	public void setSpecializations(String specializations) {
		this.specializations = specializations;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getMinFee() {
		return minFee;
	}

	public void setMinFee(Double minFee) {
		this.minFee = minFee;
	}

	public Double getMaxFee() {
		return maxFee;
	}

	public void setMaxFee(Double maxFee) {
		this.maxFee = maxFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Integer getYearGraduated() {
		return yearGraduated;
	}

	public void setYearGraduated(Integer yearGraduated) {
		this.yearGraduated = yearGraduated;
	}

	public String getHighestDegree() {
		return highestDegree;
	}

	public void setHighestDegree(String highestDegree) {
		this.highestDegree = highestDegree;
	}

	public String getEthinicity() {
		return ethinicity;
	}

	public void setEthinicity(String ethinicity) {
		this.ethinicity = ethinicity;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPreferredlanguages() {
		return preferredlanguages;
	}

	public void setPreferredlanguages(String preferredlanguages) {
		this.preferredlanguages = preferredlanguages;
	}

	public String getNativelanguage() {
		return nativelanguage;
	}

	public void setNativelanguage(String nativelanguage) {
		this.nativelanguage = nativelanguage;
	}

}
