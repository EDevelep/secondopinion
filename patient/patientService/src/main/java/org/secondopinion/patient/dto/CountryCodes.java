package org.secondopinion.patient.dto;

import java.util.List;

public class CountryCodes {
	private String status;
	private List<CountrycodeDTO> result;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<CountrycodeDTO> getResult() {
		return result;
	}
	public void setResult(List<CountrycodeDTO> result) {
		this.result = result;
	}
}