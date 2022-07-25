package org.secondopinion.reports.graph.data;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private List<Col> cols = new ArrayList<Col>();
	private List<Row> rows = new ArrayList<Row>();
	
	public List<Col> getCols() {
		return cols;
	}
	public void setCols(List<Col> cols) {
		this.cols = cols;
	}
	public List<Row> getRows() {
		return rows;
	}
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	
	public void addCol(Col col){
		cols.add(col);
	}
	
	public void addRow(Row row){
		rows.add(row);
	}
	public void addCols(List<Col> cols) {
		this.cols.addAll(cols);
	}
}
