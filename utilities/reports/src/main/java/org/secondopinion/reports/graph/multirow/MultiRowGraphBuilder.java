package org.secondopinion.reports.graph.multirow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.secondopinion.reports.graph.GraphUtil;
import org.secondopinion.reports.graph.data.Col;
import org.secondopinion.reports.graph.data.Row;
import org.secondopinion.reports.graph.data.Table;
import org.secondopinion.reports.util.DataType;
import org.secondopinion.reports.util.DataType.GraphDataType;


public class MultiRowGraphBuilder implements IMultiRowGraphBuilder{

	@Override
	public Table buildGraph(List<Map<String, Object>> data) {
		Table table = new Table();
		
		TypeAndNames typeAndNames = getColumns(data.get(0));
		table.addCols(typeAndNames.cols);
		
		for(Map<String, Object> record : data){
			table.addRow(getTableRow(record, typeAndNames));
		}
		
		return table;
	}
	
	
	private TypeAndNames getColumns(Map<String, Object> map) {
		TypeAndNames typeAndNames = new TypeAndNames(map.size());
		boolean firstCol = true;
		for(String key : map.keySet()){
			DataType dataType = DataType.getType(map.get(key).getClass());
			if(firstCol){
				typeAndNames.add(String.valueOf(key), dataType.getGoogleGraphType());
				firstCol = false;
			}else{
				typeAndNames.add(String.valueOf(map.get(key)), GraphDataType.NUMBER);
			}
		}
		return typeAndNames;
	}


	private Row getTableRow(Map<String, Object> record, TypeAndNames types){
		Row row = new Row();
		
		int colIdx = 0;
		for(String col : record.keySet()){
			GraphDataType graphType = types.getTypeByIdx(colIdx);
			row.addCell(GraphUtil.getCellValue(record.get(col), graphType));
			colIdx++;
		}
		
		return row;
	}
	
	private TypeAndNames getColumnsFromArray(Object[] objects){
		TypeAndNames typeAndNames = new TypeAndNames(objects.length);
		boolean firstCol = true;
		for(Object obj: objects){
			
			DataType dataType = DataType.getType(obj.getClass());
			if(firstCol){
				typeAndNames.add(String.valueOf(obj), dataType.getGoogleGraphType());
				firstCol = false;
			}else{
				typeAndNames.add(String.valueOf(obj), GraphDataType.NUMBER);
			}
		}
		
		return typeAndNames;
	}
	
	private static class TypeAndNames{
		private Map<String, GraphDataType> types = new LinkedHashMap<String, GraphDataType>();
		private List<Col> cols;
		
		TypeAndNames(int size){
			cols = new ArrayList<Col>(size);
		}
		
		GraphDataType getTypeByIdx(int idx){
			int cnt = 0;
			for(String key : types.keySet()){
				if(idx == cnt){
					return types.get(key);
				}
				cnt++;
			}
			
			return null;
		}
		
		void add(String colName, GraphDataType type){
//			System.out.println(colName + " " + type);
			cols.add(type.getColName(colName));
			types.put(colName, type);
		}
		
		public Map<String, GraphDataType> getTypes() {
			return types;
		}
		
	}

	public Table buildGraphFromArray(List<Object[]> data) {
		Table table = new Table();
		
		TypeAndNames typeAndNames = getColumnsFromArray(data.get(0));
		table.addCols(typeAndNames.cols);
		
		data.remove(0);
		
		for( Object[] record : data){
			table.addRow(getTableRowFromArray(record));
		}
		
		return table;
	}

	private Row getTableRowFromArray(Object[] record) {
		Row row = new Row();
		
		for(Object col :record){
			if(col != null){
				GraphDataType graphType = DataType.getType(col.getClass()).getGoogleGraphType();
				row.addCell(GraphUtil.getCellValue(col, graphType));
			}else{
				row.addCell(null);
			}
		}
		
		return row;
	}
}
