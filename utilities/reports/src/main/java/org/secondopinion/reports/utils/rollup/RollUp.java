package org.secondopinion.reports.utils.rollup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.secondopinion.reports.utils.transpose.Transpose;

public class RollUp {

	public static void main(String[] args) {
	
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(getObjArray("Cat", "Miami","jan", 18));
		list.add(getObjArray("Cat", "Miami","feb", 18));
		list.add(getObjArray("Cat", "Naples","jan", 9));
		list.add(getObjArray("Cat", "Naples","mar", 9));
		list.add(getObjArray("Dog", "Miami","mar", 12));
		list.add(getObjArray("Dog", "Miami","feb", 12));
		list.add(getObjArray("Dog", "Miami","apr", 12));
		list.add(getObjArray("Dog", "Naples", "feb", 5));
		list.add(getObjArray("Dog", "Naples","mar", 12));
		list.add(getObjArray("Dog", "Tampa","jan", 14));
		list.add(getObjArray("Dog", "Tampa","mar", 14));
		list.add(getObjArray("Turtle", "Naples","jan", 1));
		list.add(getObjArray("Turtle", "Naples","apr", 1));
		list.add(getObjArray("Turtle", "Tampa","jan", 4));
		list.add(getObjArray("Turtle", "Tampa","feb", 4));
		list.add(getObjArray("Turtle", "Tampa","mar", 4));
		
		Collection<Object[]> rollUpList = RollUp.calcRollUp(list, 1);
		
//		list.addAll(rollUpList);
		System.out.println("Rollup Data...");
		for(Object[] obj : rollUpList){
			for(int i=0; i< obj.length; i++){
				System.out.print(obj[i] + " - ");
			}
			
			System.out.println();
		}
		
		System.out.println("\n\nAfter transpose...");
		Collection<Object[]> transposedList = Transpose.transposeWithTotals(list, 2, new String[]{"Animal"}, new int[] {0}, 3);
		
		for(Object[] obj : transposedList){
			for(int i=0; i< obj.length; i++){
				System.out.print(obj[i] + " - ");
			}
			
			System.out.println();
		}
		
		System.out.println("\n\n\n transpose...");
		transposedList = Transpose.transposeWithTotals(list, 2, new String[]{"State"},new int[] {1}, 3);
		
		for(Object[] obj : transposedList){
			for(int i=0; i< obj.length; i++){
				System.out.print(obj[i] + " - ");
			}
			
			System.out.println();
		}
		
		System.out.println("\n\nAfter transpose...");
		transposedList = Transpose.transposeWithTotals(list, 2,new String[]{"Animal", "State"}, new int[] {0, 1}, 3);
		
		for(Object[] obj : transposedList){
			for(int i=0; i< obj.length; i++){
				System.out.print(obj[i] + " - ");
			}
			
			System.out.println();
		}
		
	}
	
	public static Collection<Object[]> calcRollUp(List<Object[]> list, int keyLength){
		Map<CompositeKey, Object[]> map = new LinkedHashMap<CompositeKey, Object[]>();
		
		for(Object[] objs : list){
			Set<CompositeKey> keys = possibleKeys(objs, keyLength);
			addValue(map, keys, keyLength, objs);
			
		}
		
		return map.values();
	}
	
	static Set<CompositeKey>  possibleKeys(Object[] objs, int keyLength){
		Set<CompositeKey> keys = new HashSet<CompositeKey>();
		
//		keys.add(getCompositeKey(1, keyLength+1, keyLength, objs));
		for(int i = 0; i< keyLength; i++){
			keys.add(getCompositeKey(i, keyLength, keyLength, objs));
			for(int j=i+1; j<=keyLength; j++){
				keys.add(getCompositeKey(i, j, keyLength,  objs));
			}
		}
		return keys;
	}
	
	private static CompositeKey getCompositeKey(int startKeyPos, int nextKeyPosFrom, int keyLength, Object[] objs) {
		return  new CompositeKey(startKeyPos, nextKeyPosFrom, keyLength, objs);
	}

	private static void addValue(Map<CompositeKey, Object[]> map, Set<CompositeKey> keys, int keyLength, Object[] obj){
		int length = obj.length;
		for(CompositeKey rollupKey : keys){
			addValueToKey(map, rollupKey, obj);
		}
		
		CompositeKey rollupKey = getKeyForTotals(keyLength);
		Object[] totVals = new Object[length];
		totVals[0] = "Total";
		totVals[length-1] = obj[length-1];
		addValueToKey(map, rollupKey, totVals);
	
	}
	
	private static void addValueToKey(Map<CompositeKey, Object[]> map, CompositeKey rollupKey, Object[] obj){
		int length = obj.length;
		Object[] calVal = getDefaultVal(rollupKey.getKey(), length);
		
		if(map.containsKey(rollupKey)){
			calVal = map.get(rollupKey);
		}
		Double val = new Double(obj[length-1].toString());
		
		if(calVal[length-1] != null){
			calVal[length-1] = (Double)calVal[length-1] + val;
		}else{
			calVal[length-1] = val;
		}
		
		map.put(rollupKey, calVal);
		
	}
	
	private static Object[] getDefaultVal(Object[] key, int length) {
		Object[] calVal = new Object[length];
		for(int i=0; i<key.length; i++){
			calVal[i] = key[i];
		}
		return calVal;
	}

	
	private static Object[] getDefaultVal(int keyLength, int length){
		Object[] calVal = new Object[length];
		
		for(int i=0; i<keyLength; i++){
			if(calVal[i]== null)calVal[i]="Total";
		}
		
		return calVal;
	}
	
	static CompositeKey getKeyForTotals(int length){
		CompositeKey key = new CompositeKey("Total", 0, length);
		
		return key;
	}

	
	private static Object[] getObjArray(Object type, Object store, Object month, Object num){
		Object[] objs = {type, store, month, num};
		return objs;
	}
}
