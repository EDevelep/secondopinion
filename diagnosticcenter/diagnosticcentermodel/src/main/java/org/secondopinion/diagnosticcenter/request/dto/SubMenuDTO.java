package org.secondopinion.diagnosticcenter.request.dto;

import java.math.BigInteger;
import java.util.Date;

public class SubMenuDTO {

	private BigInteger menuId;
	private BigInteger subMenuId;
	private String serviceName;
	private String serviceType;
	private Double price;
	private String available;
	private String collectiontype;
	private Date availableFrom;

	public BigInteger getMenuId() {
		return menuId;
	}

	public void setMenuId(BigInteger menuId) {
		this.menuId = menuId;
	}

	public BigInteger getSubMenuId() {
		return subMenuId;
	}

	public void setSubMenuId(BigInteger subMenuId) { 
		this.subMenuId = subMenuId;
	}
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getCollectiontype() {
		return collectiontype;
	}

	public void setCollectiontype(String collectiontype) {
		this.collectiontype = collectiontype;
	}

	public Date getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}
}
