package org.secondopinion.reports.utils.rollup;

import java.util.Arrays;

public class CompositeKey {
	private Object[] key;
	
	protected CompositeKey(Object keyVal, int pos, int length){
		key = new Object[length];
		key[pos] = keyVal;
	}
	
	public CompositeKey(Object[] _key){
		this.key = _key;
	}
	
	public CompositeKey(int startKeyPos, int nextKeyPosFrom, int keyLength,
			Object[] objs) {
		key = new Object[keyLength];
		key[startKeyPos] = objs[startKeyPos];
		
		if(nextKeyPosFrom == keyLength-1){
			key[nextKeyPosFrom] = objs[nextKeyPosFrom];
		}
		for(int i=nextKeyPosFrom; i<keyLength-1; i++){
			key[i] = objs[i];
		}
	}

	public Object[] getKey() {
		return key;
	}
			
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(key);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeKey other = (CompositeKey) obj;
		
		if (!Arrays.equals(key, other.key))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Object obj : key){
			sb.append(obj + " - ");
		}
		sb.append("\n");
		return sb.toString();
	}

}
