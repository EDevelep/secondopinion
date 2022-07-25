package org.secondopinion.diagnosticcenter.dto; 


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.diagnosticcenter.domain.BasePackagemenu;




@Entity 
@Table (name="packagemenu")
public class Packagemenu extends BasePackagemenu{

	private List<Packagesubmenu> packageSubMenuItems;

	@Transient
	public List<Packagesubmenu> getPackageSubMenuItems() {
		return packageSubMenuItems;
	}

	public void setPackageSubMenuItems(List<Packagesubmenu> packageSubMenuItems) {
		this.packageSubMenuItems = packageSubMenuItems;
	}
}