package org.secondopinion.diagnosticcenter.dto;

public class PackagesubmenuSearchDTO {
	private Long packageMenuId;
	private Long subMenuId;
	private String serviceName;
	private Integer pageNum;
	private Integer maxresult;
	public Long getPackageMenuId() {
		return packageMenuId;
	}
	public void setPackageMenuId(Long packageMenuId) {
		this.packageMenuId = packageMenuId;
	}
	public Long getSubMenuId() {
		return subMenuId;
	}
	public void setSubMenuId(Long subMenuId) {
		this.subMenuId = subMenuId;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getMaxresult() {
		return maxresult;
	}
	public void setMaxresult(Integer maxresult) {
		this.maxresult = maxresult;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}
