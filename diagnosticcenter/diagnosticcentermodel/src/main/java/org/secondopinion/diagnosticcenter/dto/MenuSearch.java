package org.secondopinion.diagnosticcenter.dto;

public class MenuSearch {


	private String menuName;
	private Character active;
	private Long diagnosticeCenterAddressId;
	private Integer pageNumber;
	private Integer maxresult;
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Character getActive() {
		return active;
	}
	public void setActive(Character active) {
		this.active = active;
	}

	public Long getDiagnosticeCenterAddressId() {
		return diagnosticeCenterAddressId;
	}
	public void setDiagnosticeCenterAddressId(Long diagnosticeCenterAddressId) {
		this.diagnosticeCenterAddressId = diagnosticeCenterAddressId;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getMaxresult() {
		return maxresult;
	}
	public void setMaxresult(Integer maxresult) {
		this.maxresult = maxresult;
	}

}
