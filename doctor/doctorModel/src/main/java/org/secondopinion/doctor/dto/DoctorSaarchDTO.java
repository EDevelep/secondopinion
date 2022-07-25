package org.secondopinion.doctor.dto;


public class DoctorSaarchDTO {
	
	private String firstName;
	private String lastName;
	
	private String doctorspecialization;
	private Long doctorId;
	private Integer totalExperience;;
	private String type;
	private Double averagerating;
	private Double latitude;
	private Double longitude;
	public DoctorSaarchDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDoctorspecialization() {
		return doctorspecialization;
	}
	public void setDoctorspecialization(String doctorspecialization) {
		this.doctorspecialization = doctorspecialization;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public Integer getTotalExperience() {
		return totalExperience;
	}
	public void setTotalExperience(Integer totalExperience) {
		this.totalExperience = totalExperience;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getAveragerating() {
		return averagerating;
	}
	public void setAveragerating(Double averagerating) {
		this.averagerating = averagerating;
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
	public static DoctorSaarchDTO buildDoctorDTOObject(Doctor doctor) {
		DoctorSaarchDTO doctorSaarchDTO=new DoctorSaarchDTO();
		doctorSaarchDTO.setDoctorId(doctor.getDoctorId());
		doctorSaarchDTO.setDoctorspecialization(doctor.getSpecialization());
		doctorSaarchDTO.setFirstName(doctor.getFirstName());
		doctorSaarchDTO.setLastName(doctor.getLastName());
		doctorSaarchDTO.setTotalExperience(doctor.getTotalExperience());
		doctorSaarchDTO.setType(doctor.getType());
		
		return doctorSaarchDTO;
	}
	public static DoctorSaarchDTO buildDoctorDTOObject(DoctorAddress doctorAddress) {
		DoctorSaarchDTO doctorSaarchDTO=new DoctorSaarchDTO();
		doctorSaarchDTO.setDoctorId(doctorAddress.getDoctorId());
		doctorSaarchDTO.setLatitude(doctorAddress.getLatitude());
		doctorSaarchDTO.setLongitude(doctorAddress.getLongitude());
		return doctorSaarchDTO;
	}

}
