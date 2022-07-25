package org.secondopinion.reports.graph;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.secondopinion.reports.graph.data.Cell;
import org.secondopinion.reports.graph.data.Table;
import org.secondopinion.reports.graph.multirow.MultiRowGraphBuilder;
import org.secondopinion.reports.graph.singlerow.SingleRowGraphDataBuilder;
import org.secondopinion.reports.util.DataType.GraphDataType;
import org.secondopinion.utils.DateUtil;


public class GraphUtil {
	
	public static Table getTableRepresentationSingleRow(List<Map<String, Object>> data){
		SingleRowGraphDataBuilder builder = new SingleRowGraphDataBuilder();
		
		if(data == null || data.size()==0){
			return new Table();
		}
		return builder.buildGraph(data.get(0));
	}
	
	public static Table getTableRepresentationMultiRow(List<Map<String, Object>> data){
		MultiRowGraphBuilder builder = new MultiRowGraphBuilder();
		
		if(data == null || data.size()==0){
			return new Table();
		}
		return builder.buildGraph(data);
	}
	
	public static Cell getCellValue(Object obj, GraphDataType graphType ){
//		System.out.println(obj + " - GraphType: " + graphType);
		switch (graphType) {
		case STRING:
			return new Cell<String>((String)obj);
		case DATE:
		case TIMESTAMP:
			return new Cell<Date>((Date)obj);
		case NUMBER:
			return new Cell<Number>((Number)obj);
		}
		return null;
	}

	public static Table convertToTableMultiRow(List<Object[]> data) {
		MultiRowGraphBuilder builder = new MultiRowGraphBuilder();
		
		if(data == null || data.size()==0){
			return new Table();
		}
		return builder.buildGraphFromArray(data);
	}
}
