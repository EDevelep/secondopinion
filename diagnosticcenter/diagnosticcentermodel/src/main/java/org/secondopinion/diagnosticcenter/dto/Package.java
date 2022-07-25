package org.secondopinion.diagnosticcenter.dto; 

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.diagnosticcenter.domain.BasePackage; 

@Entity 
@Table (name="package")
public class Package extends BasePackage{
	
	private List<Packagemenu> menuItems;

	@Transient
	public List<Packagemenu> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<Packagemenu> menuItems) {
		this.menuItems = menuItems;
	}
}