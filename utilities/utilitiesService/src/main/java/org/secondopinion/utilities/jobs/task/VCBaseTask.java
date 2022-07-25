package org.secondopinion.utilities.jobs.task;

import org.secondopinion.utilities.jobs.dto.FlowInfo;
import org.secondopinion.utilities.jobs.dto.FlowParameter;
import org.secondopinion.utilities.jobs.dto.IFlowParameter;

public abstract class VCBaseTask<INPUT, OUT_PUT> {
	private IFlowParameter taskInput = FlowParameter.FLOW_TASK_INPUT;
	private IFlowParameter taskOutput = FlowParameter.FLOW_TASK_OUTPUT;
	private String taskName;

	private boolean finalTaskInFlow;
	
	public void executeTask(FlowInfo fi) {
		INPUT input = fi.getFlowAttr(taskInput);
		OUT_PUT outPut = execute(input, fi);

		if(finalTaskInFlow){
			fi.removeAll();
		}
		fi.removeAttr(taskInput);
		
		if(taskOutput != null){
			fi.addFlowAttr(taskOutput, outPut);
			fi.addFlowAttr(taskInput, outPut);
		}
		
	}

	public abstract OUT_PUT execute(INPUT input, FlowInfo fi);

	public String getTaskInput() {
		return taskInput.name();
	}

	public void setTaskInput(String taskInput) {
		this.taskInput = FlowParameter.valueOf(taskInput);
	}

	public String getTaskOutput() {
		return taskOutput.name();
	}

	public void setTaskOutput(String taskOutPut) {
		this.taskOutput = FlowParameter.valueOf(taskOutPut);
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public boolean isFinalTaskInFlow() {
		return finalTaskInFlow;
	}

	public void setFinalTaskInFlow(boolean finalTaskInFlow) {
		this.finalTaskInFlow = finalTaskInFlow;
	}	

}
