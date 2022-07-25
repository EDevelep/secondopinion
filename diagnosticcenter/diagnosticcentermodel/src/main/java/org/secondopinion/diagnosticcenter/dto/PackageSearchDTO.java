package org.secondopinion.diagnosticcenter.dto;

public class PackageSearchDTO {
	private String packageName;
	private Long DiagnosticCenterAddressId;
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Long getDiagnosticCenterAddressId() {
		return DiagnosticCenterAddressId;
	}
	public void setDiagnosticCenterAddressId(Long DiagnosticCenterAddressId) {
		this.DiagnosticCenterAddressId = DiagnosticCenterAddressId;
	}
	
	
}
