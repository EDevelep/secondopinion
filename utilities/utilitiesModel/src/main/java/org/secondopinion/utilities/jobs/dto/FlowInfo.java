package org.secondopinion.utilities.jobs.dto;

import java.util.HashMap;
import java.util.Map;

public class FlowInfo {
	Map<IFlowParameter, Object> flowInformation = new HashMap<IFlowParameter, Object>();
	Map<String, String> jobFlowParams = new HashMap<String, String>();
	FlowOutput flowOutput;
	
	public FlowInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public FlowInfo(Map<String, String> _jobFlowParams) {
		jobFlowParams = _jobFlowParams;
	}
	
	public void  addFlowAttr(IFlowParameter attributeName, Object flowAttribute){
		flowInformation.put(attributeName, flowAttribute);
	}
	
	public String getJobFlowParam(String paramName){
		return jobFlowParams.get(paramName);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T  getFlowAttr(IFlowParameter attributeName){
		return (T)flowInformation.get(attributeName);
	}

	public void removeAttr(IFlowParameter taskInput) {
		flowInformation.remove(taskInput);
	}

	public void removeAll() {
		flowInformation.clear();
	}
	
	public void addToFlowoutput(String name, Object value){
		if(flowOutput == null){
			flowOutput = new FlowOutput();
		}
		
		flowOutput.addFlowAttr(name, value);
			
	}

	public FlowOutput getFlowOutput() {
		return flowOutput;
	}
}
