package org.secondopinion.reports.graph.data;

import java.util.ArrayList;
import java.util.List;

public class Row {
	private List<Cell<?>> c = new ArrayList<Cell<?>>();

	public List<Cell<?>> getC() {
		return c;
	}

	public void setC(List<Cell<?>> c) {
		this.c = c;
	}
	
	public <T> void addCell(Cell<T> cell){
		c.add(cell);
	}
}
