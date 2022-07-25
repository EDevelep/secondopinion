package org.secondopinion.reports.graph.singlerow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.secondopinion.reports.graph.GraphUtil;
import org.secondopinion.reports.graph.data.Col;
import org.secondopinion.reports.graph.data.Row;
import org.secondopinion.reports.graph.data.Table;
import org.secondopinion.reports.util.DataType;
import org.secondopinion.reports.util.DataType.GraphDataType;


public class SingleRowGraphDataBuilder implements ISingleRowGraphDataBuilder{

	@Override
	public Table buildGraph(Map<String, Object> data) {
		boolean firstRow = true;
		Table table = new Table();
		for(String key : data.keySet()){
			if(firstRow){
				table.addCols(buildColumns(key, data.get(key)));
				firstRow=false;
//				continue;
			}
			Row row = new Row();
			
			row.addCell(GraphUtil.getCellValue(key, GraphDataType.STRING));
			Object obj =  data.get(key);
			DataType dataType = DataType.getType(obj.getClass());
			row.addCell(GraphUtil.getCellValue(obj, dataType.getGoogleGraphType()));
			
			table.addRow(row);
		}
		return table;
	}

	private List<Col> buildColumns(String col, Object obj) {
		List<Col> cols = new ArrayList<Col>(2);
		
		cols.add(GraphDataType.STRING.getColName(col));
		cols.add(GraphDataType.NUMBER.getColName("Count"));
		
		return cols;
	}

}
