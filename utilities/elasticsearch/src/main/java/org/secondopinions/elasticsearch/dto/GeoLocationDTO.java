package org.secondopinions.elasticsearch.dto;

public class GeoLocationDTO {
  private Double latitude;
 private Double longitude;
 private Double distance;
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
public Double getDistance() {
	return distance;
}
public void setDistance(Double distance) {
	this.distance = distance;
}
}
