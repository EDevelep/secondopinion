package org.secondopinion.diagnosticcenter.dto;

public class MenuSearchDTO {

	private String menuName;
	private Long diagnosticCenterAddressId;


	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Long getDiagnosticCenterAddressId() {
		return diagnosticCenterAddressId;
	}

	public void setDiagnosticCenterAddressId(Long diagnosticCenterAddressId) {
		this.diagnosticCenterAddressId = diagnosticCenterAddressId;
	}

}
