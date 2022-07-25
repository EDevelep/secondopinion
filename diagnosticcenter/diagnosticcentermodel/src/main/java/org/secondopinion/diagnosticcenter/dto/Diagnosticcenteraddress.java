package org.secondopinion.diagnosticcenter.dto; 


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.diagnosticcenter.domain.BaseDiagnosticcenteraddress; 



@Entity 
@Table (name="diagnosticcenteraddress")
public class Diagnosticcenteraddress extends BaseDiagnosticcenteraddress{
	
	private  List <Menu> menus;
	private List<Package> packages;
	
	@Transient
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	@Transient
	public List<Package> getPackages() {
		return packages;
	}
	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}
}