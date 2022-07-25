package org.secondopinion.reports.graph;

import org.secondopinion.reports.graph.data.Table;

public interface IGraphDataBuilder<T> {
	public Table buildGraph(T data);
}
