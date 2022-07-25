package org.secondopinion.common.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DAOResult<T, ID_TYPE> {
	private List<T> success;
	private Map<ID_TYPE, Errored> erroredMap;
	
	public void addSuccess(T t){
		if(Objects.isNull(success)){
			success = new ArrayList<T>();
		}
		success.add(t);
	}
	
	public void addSuccesses(Collection<T> ts){
		if(Objects.isNull(success)){
			success = new ArrayList<T>();
		}
		success.addAll(ts);
	}
	
	public Map<ID_TYPE, Errored> getErroredMap() {
		return erroredMap;
	}
	
	public List<T> getSuccess() {
		return success;
	}
	
	public void addFilure(ID_TYPE id, T t, String message){
		if(Objects.isNull(erroredMap)){
			erroredMap = new HashMap<ID_TYPE, Errored>();
		}
		
		erroredMap.put(id, new Errored(t, message));
	}
	
	public class Errored{
		private T t;
		private String message;
		
		public Errored(T _t, String _message) {
			t = _t;
			message = _message;
		}
		
		public String getMessage() {
			return message;
		}
		
		public T getT() {
			return t;
		}
	}
}
