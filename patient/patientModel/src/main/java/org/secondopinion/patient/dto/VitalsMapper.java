package org.secondopinion.patient.dto;

public class VitalsMapper {
	
	private String weight;
	private String oxygenLevel;
	private String pulse;
	private String bloodPressure;
	private String temperature;
	private String height;
	
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getOxygenLevel() {
		return oxygenLevel;
	}
	public void setOxygenLevel(String oxygenLevel) {
		this.oxygenLevel = oxygenLevel;
	}
	public String getPulse() {
		return pulse;
	}
	public void setPulse(String pulse) {
		this.pulse = pulse;
	}
	public String getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	@Override
	public String toString() {
		return "VitalsMapper [weight=" + weight + ", oxygenLevel=" + oxygenLevel + ", pulse=" + pulse
				+ ", bloodPressure=" + bloodPressure + ", temperature=" + temperature + ", height=" + height
				+ ", getHeight()=" + getHeight() + ", getWeight()=" + getWeight() + ", getOxygenLevel()="
				+ getOxygenLevel() + ", getPulse()=" + getPulse() + ", getBloodPressure()=" + getBloodPressure()
				+ ", getTemperature()=" + getTemperature() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
