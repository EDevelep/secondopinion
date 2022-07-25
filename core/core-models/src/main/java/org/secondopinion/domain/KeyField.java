package org.secondopinion.domain;

public class KeyField {
	
	private String fieldName;
	private Object value;
	
	public KeyField(String _fieldName, Object _value) {
		this.fieldName = _fieldName;
		this.value = _value;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getValue() {
		return value;
	}

}
