package org.secondopinion.diagnosticcenter.dto; 


import java.util.List;

import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.diagnosticcenter.domain.BaseMenu; 



@SuppressWarnings({ "serial"})
@Entity 
@Table (name="menu")
public class Menu extends BaseMenu{

	private List<Submenu> subMenuItems;

	@Transient
	public List<Submenu> getSubMenuItems() {
		return subMenuItems;
	}

	public void setSubMenuItems(List<Submenu> subMenuItems) {
		this.subMenuItems = subMenuItems;
	}
}