package org.secondopinion.common.dto;

import java.util.HashMap;
import java.util.Map;

public class SortDTO {
	public static Map<String, SortDirection> buildSortMap(String columnName, SortDirection sortDirection) {
		Map<String, SortDirection> sortMap = new HashMap<String, SortDirection>();
		sortMap.put(columnName, sortDirection);
		return sortMap;
	}
	

public enum SortDirection {
	ASC(true), DESC(false);
	private boolean isAsc;
	public boolean getIsAsc() {
		return isAsc;
	}
	private SortDirection(boolean isAsc) {
		this.isAsc = isAsc;
	}
}
}

