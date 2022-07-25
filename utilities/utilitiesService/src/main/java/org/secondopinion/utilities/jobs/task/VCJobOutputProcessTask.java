package org.secondopinion.utilities.jobs.task;

import java.util.List;

import org.secondopinion.utilities.jobs.dto.FlowOutput;


public interface VCJobOutputProcessTask {
	void process(List<FlowOutput> jobOutput);
}
