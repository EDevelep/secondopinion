package org.secondopinion.utilities.jobs.dto;

import java.util.HashMap;
import java.util.Map;


public class FlowOutput {
	private Map<String, Object> flowOutput = new HashMap<String, Object>();
	
	public void  addFlowAttr(String attributeName, Object flowAttribute){
		flowOutput.put(attributeName, flowAttribute);
	}

	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return flowOutput.get(name);
	}
	
}
