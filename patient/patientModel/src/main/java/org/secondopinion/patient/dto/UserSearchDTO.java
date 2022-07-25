package org.secondopinion.patient.dto;

public class UserSearchDTO {
  private Long userId;
  private String userName;
  private String firstName;
  private String lastName;
  private String middleName;
  private String city;
  private String state;
  private String country;
  private String zip;
  private Long longitudes;
  private Long latitude;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
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

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public Long getLongitudes() {
    return longitudes;
  }

  public void setLongitudes(Long longitudes) {
    this.longitudes = longitudes;
  }

  public Long getLatitude() {
    return latitude;
  }

  public void setLatitude(Long latitude) {
    this.latitude = latitude;
  }

  public static UserSearchDTO buildUserObject(User user) {
    UserSearchDTO userSearchDTO = new UserSearchDTO();
    userSearchDTO.setUserId(user.getUserId());
    userSearchDTO.setUserName(user.getUserName());
    userSearchDTO.setFirstName(user.getFirstName());
    userSearchDTO.setLastName(user.getLastName());
    return userSearchDTO;
  }

  public static UserSearchDTO buildUserObject(Address address) {
    UserSearchDTO userSearchDTO = new UserSearchDTO();
    userSearchDTO.setUserId(address.getUserId());
    userSearchDTO.setLatitude(address.getLatitude());
    userSearchDTO.setLongitudes(address.getLongitudes());
    return userSearchDTO;
  }

}
