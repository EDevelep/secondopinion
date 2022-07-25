package org.secondopinion.diagnosticcenter.dto; 



import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.diagnosticcenter.domain.BaseDiagnosticcenter; 



@Entity 
@Table (name="diagnosticcenter")
public class Diagnosticcenter extends BaseDiagnosticcenter{
	private Diagnosticcenteraddress primaryDataCenterAddress;
	private Diagnosticcenteruser primaryDiagnosticcenteruser;
	
	private Double amount;
	
	private String uiHostURL;
	@Transient
	public String getUiHostURL() {
		return uiHostURL;
	}
	public void setUiHostURL(String uiHostURL) {
		this.uiHostURL = uiHostURL;
	}
	
	@Transient
	public Diagnosticcenteruser getPrimaryDiagnosticcenteruser() {
		return primaryDiagnosticcenteruser;
	}
	public void setPrimaryDiagnosticcenteruser(Diagnosticcenteruser primaryDiagnosticcenteruser) {
		this.primaryDiagnosticcenteruser = primaryDiagnosticcenteruser;
	}
	
	@Transient
	public Diagnosticcenteraddress getPrimaryDataCenterAddress() {
		return primaryDataCenterAddress;
	}
	public void setPrimaryDataCenterAddress(Diagnosticcenteraddress primaryDataCenterAddress) {
		this.primaryDataCenterAddress = primaryDataCenterAddress;
	}
	@Transient
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}