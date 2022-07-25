package org.secondopinion.reports.graph.data;

public class Val<T> {
	private T v;

	public Val(T v) {
		this.v = v;
	}
	public T getV() {
		return v;
	}

	public void setV(T v) {
		this.v = v;
	}
}
