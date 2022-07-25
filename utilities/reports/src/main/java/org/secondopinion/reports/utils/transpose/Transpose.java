package org.secondopinion.reports.utils.transpose;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.secondopinion.reports.utils.rollup.CompositeKey;
import org.secondopinion.utils.StringUtil;

public class Transpose {
	
	public static List<Object[]> transposeWithTotals(List<Object[]> list, int transposeColId, String[] keyCols, int[] keyColumns, int valueColumn) {
		return transpose(list, transposeColId, keyCols, keyColumns, true, valueColumn) ;
	}
	
	public static List<Object[]> transpose(List<Object[]> list, int transposeColId, String[] keyCols, int[] keyColumns, int valueColumn) {
		return transpose(list, transposeColId, keyCols, keyColumns, false, valueColumn) ;
	}
	
	public static List<Object[]> transpose(List<Object[]> list, int transposeColId,String[] keyCols, int[] keyColumns, boolean calcTotals, int valueColumn) {
		
		TransposedValues transposedValues = new TransposedValues(calcTotals,keyCols, keyColumns);
		transposedValues.getTransposedColumns(list, transposeColId, keyColumns.length);
		
		for(Object[] objs: list){
			transposedValues.transpose(objs, transposeColId, valueColumn);
		}
		
		return  transposedValues.getTransposedValues();
	}
	
	static Object sum(Object val1, Object val2){
		Double sumVal = (val1 != null) ? new Double(val1.toString()) : null;
		
		if(sumVal == null){
			sumVal  = (val2 != null) ? new Double(val2.toString()) : null;
		}else if(val2 != null){
			sumVal += new Double(val2.toString());
		}
		
		return sumVal;
	}
	
	static CompositeKey getKey(Object[] objs, int[] keyIds){
		List<String> list = new ArrayList<String>();
		boolean hasNonNullKey = false;
		for(int i : keyIds){
			String key = String.valueOf(objs[i]);
			if(!StringUtil.isNullOrEmpty(key)){
					hasNonNullKey = true;
			}
			list.add(key);
		}
		
		if(!hasNonNullKey){
			list.set(0, "Total");
		}
		
		return new CompositeKey(list.toArray(new String[0])); 
	}
	
	private static class TransposedValues{
		Map<String, Integer> transposeCols = new LinkedHashMap<String, Integer>() ;
		Object[] transposedTotals = null;
		String[] keyCols;
		Map<CompositeKey, Object[]> map = new LinkedHashMap<CompositeKey, Object[]>();
		boolean calcTotals;
		int[] keyColumns;
		
		public TransposedValues(boolean _calcTotals, String[] _keyCols, int[] _keyColumns) {
			this.calcTotals = _calcTotals;
			this.keyColumns = _keyColumns;
			this.keyCols = _keyCols;
		}
		
		void getTransposedColumns(List<Object[]> list, int transposeColId, int numberOfKeyColumns){
			Set<String> columns = new LinkedHashSet<String>();
			for(int i = 0; i< numberOfKeyColumns; i++){
				columns.add( StringUtil.initCap(keyCols[i]));
			}
			
			for(Object[] object: list){
				String col = String.valueOf(object[transposeColId]);
				if(col != null && !"Total".equalsIgnoreCase(col)){
					columns.add(col);
				}
			}
			int i =0;
			for(String col : columns){
				transposeCols.put(col, ++i);
			}
			
			if(calcTotals){
				transposeCols.put("Total", ++i);
				transposedTotals = new Object[transposeCols.size()];
			}
		}
		
		void transpose(Object[] objs, int transposeColId, int valueColumn){
			CompositeKey key = getKey(objs, keyColumns);
			
			Object[] vals = new Object[transposeCols.size()];
			if(map.containsKey(key)){
				vals = map.get(key);
			}else{
				Object[] keyVal = key.getKey();
				for(int i=0; i<keyVal.length; i++){
					vals[i] = keyVal[i];
				}
			}
			
			String transposedCol =  String.valueOf(objs[transposeColId]);
			if(transposedCol != null){
				Object colValue = sum(vals[this.transposeCols.get(transposedCol)-1], objs[valueColumn]);
				vals[this.transposeCols.get(transposedCol)-1] = colValue;
				
				if(calcTotals){
					vals[vals.length -1] = sum(objs[valueColumn], vals[vals.length -1]);
					transposedTotals[this.transposeCols.get(transposedCol)-1] = sum(objs[valueColumn], transposedTotals[this.transposeCols.get(transposedCol)-1]);
					transposedTotals[vals.length -1] = sum(objs[valueColumn], transposedTotals[vals.length -1]);
				}
			}
			
			map.put(key, vals);
		}
		
		List<Object[]> getTransposedValues(){
			List<Object[]> vals = new ArrayList<Object[]>();
			
			vals.add(transposeCols.keySet().toArray());
			vals.addAll(map.values());
			
			if(calcTotals){
				vals.add(transposedTotals);
			}
			return vals;
		}
	}
	
}
