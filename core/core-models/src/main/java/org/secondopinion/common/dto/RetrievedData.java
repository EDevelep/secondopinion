package org.secondopinion.common.dto;

import java.util.List;
import java.util.Map;

public class RetrievedData<KEY, RECORD> {
	private List<KEY> missingKeys;
	private Map<KEY, RECORD> foundData;
	private List<KEY> erroredKeys;

	public List<KEY> getMissingKeys() {
		return missingKeys;
	}

	public void setMissingKeys(List<KEY> missingKeys) {
		this.missingKeys = missingKeys;
	}

	public Map<KEY, RECORD> getFoundData() {
		return foundData;
	}

	public void setFoundData(Map<KEY, RECORD> foundData) {
		this.foundData = foundData;
	}

	public List<KEY> getErroredKeys() {
		return erroredKeys;
	}

	public void setErroredKeys(List<KEY> erroredKeys) {
		this.erroredKeys = erroredKeys;
	}

}
