package org.secondopinion.patient.dto;

import java.util.List;

import org.secondopinion.patient.dto.Invoice.ViewInvoiceEnum;



public class ViewInvoiceEnumRequset {
	
	private Long patientId;
	private List<ViewInvoiceEnum> viewInvoiceEnum;
	private Integer pageNum;
	private Integer maxResult;
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public List<ViewInvoiceEnum> getViewInvoiceEnum() {
		return viewInvoiceEnum;
	}
	public void setViewInvoiceEnum(List<ViewInvoiceEnum> viewInvoiceEnum) {
		this.viewInvoiceEnum = viewInvoiceEnum;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getMaxResult() {
		return maxResult;
	}
	public void setMaxResult(Integer maxResult) {
		this.maxResult = maxResult;
	}
	public enum	ViewInvoiceEnum{
		 REQUEST_PAYMENT, PAYMENT_DONE, DELIVERED
		}
}
