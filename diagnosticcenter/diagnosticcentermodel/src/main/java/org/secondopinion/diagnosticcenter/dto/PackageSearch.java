package org.secondopinion.diagnosticcenter.dto;

public class PackageSearch {
	private Long diagnosticCenterAddressId;
	private String packageName;
	private String description;
	private Double price;
	private Integer pagenumber;
	private Integer maxresult;
	public Long getDiagnosticCenterAddressId() {
		return diagnosticCenterAddressId;
	}
	public void setDiagnosticCenterAddressId(Long diagnosticCenterAddressId) {
		this.diagnosticCenterAddressId = diagnosticCenterAddressId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getPagenumber() {
		return pagenumber;
	}
	public void setPagenumber(Integer pagenumber) {
		this.pagenumber = pagenumber;
	}
	public Integer getMaxresult() {
		return maxresult;
	}
	public void setMaxresult(Integer maxresult) {
		this.maxresult = maxresult;
	}
}
