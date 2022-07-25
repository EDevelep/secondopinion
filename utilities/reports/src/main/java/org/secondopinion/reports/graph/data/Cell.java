package org.secondopinion.reports.graph.data;

public class Cell<T> {
	
	private T v;

	public Cell(T v) {
		this.v = v;
	}
	
	public T getV() {
		return v;
	}

	public void setV(T v) {
		this.v = v;
	}

	@Override
	public String toString() {
		return "Cell [v=" + v + "]";
	}
	
	
}
