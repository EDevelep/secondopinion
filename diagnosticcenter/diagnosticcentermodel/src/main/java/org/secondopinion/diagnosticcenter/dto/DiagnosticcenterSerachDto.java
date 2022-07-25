package org.secondopinion.diagnosticcenter.dto;

public class DiagnosticcenterSerachDto {
	private Long diagnosticCenterAddressId;
	private Long diagnosticcenterId;
	private String name;
	public Long getDiagnosticCenterAddressId() {
		return diagnosticCenterAddressId;
	}
	public void setDiagnosticCenterAddressId(Long diagnosticCenterAddressId) {
		this.diagnosticCenterAddressId = diagnosticCenterAddressId;
	}
	public Long getDiagnosticcenterId() {
		return diagnosticcenterId;
	}
	public void setDiagnosticcenterId(Long diagnosticcenterId) {
		this.diagnosticcenterId = diagnosticcenterId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
