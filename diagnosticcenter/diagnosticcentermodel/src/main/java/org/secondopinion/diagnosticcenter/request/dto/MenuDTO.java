package org.secondopinion.diagnosticcenter.request.dto;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MenuDTO {

	private BigInteger menuId;
	private String menuName;

	public BigInteger getMenuId() {
		return menuId;
	}

	public void setMenuId(BigInteger menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	private List<SubMenuDTO> subMenuDTOs;

	public List<SubMenuDTO> getSubMenuDTOs() {
		return subMenuDTOs;
	}

	public void setSubMenuDTOs(List<SubMenuDTO> subMenuDTOs) {
		this.subMenuDTOs = subMenuDTOs;
	}
	public void addSubMenu(SubMenuDTO subMenuDTO) {
		if(subMenuDTOs == null) {
			subMenuDTOs = new ArrayList<>();
		}
		
		subMenuDTOs.add(subMenuDTO);
	}
}
