package org.secondopinion.diagnosticcenter.dto;

import java.math.BigInteger;


public class SubmenuDTO {

	private BigInteger menuId;
	private String serviceName;
	private BigInteger diagnosticCenterAddressId;
	private Double price;
	public BigInteger getMenuId() {
		return menuId;
	}
	public void setMenuId(BigInteger menuId) {
		this.menuId = menuId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public BigInteger getDiagnosticCenterAddressId() {
		return diagnosticCenterAddressId;
	}
	public void setDiagnosticCenterAddressId(BigInteger diagnosticCenterAddressId) {
		this.diagnosticCenterAddressId = diagnosticCenterAddressId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	


}
