package org.secondopinion.diagnosticcenter.dto;

public class DiagnosticcenterSearchRequest {
	
	


    private Long diagnosticcenterId;
    private String diagnosticCenterName;
	private Double latitude;
	private Double longitude;
	

	public String getDiagnosticCenterName() {
		return diagnosticCenterName;
	}

	public void setDiagnosticCenterName(String diagnosticCenterName) {
		this.diagnosticCenterName = diagnosticCenterName;
	}

	public Long getDiagnosticcenterId() {
		return diagnosticcenterId;
	}

	public void setDiagnosticcenterId(Long diagnosticcenterId) {
		this.diagnosticcenterId = diagnosticcenterId;
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
