package org.secondopinion.utilities.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.secondopinion.utilities.jobs.dto.FlowInfo;
import org.secondopinion.utilities.jobs.dto.FlowOutput;
import org.secondopinion.utilities.jobs.dto.FlowParameter;
import org.secondopinion.utilities.jobs.dto.JobInfo;
import org.secondopinion.utilities.jobs.task.VCBaseTask;
import org.secondopinion.utils.ExecutorHelper;
//import org.secondopinion.parser.IPagedDBDataReader;
//import org.secondopinion.parser.jdbc.DBReader;
//import org.secondopinion.task.VCBaseTask;
//import org.secondopinion.utils.ExecutorHelper;
import org.secondopinion.utils.StringUtil;

@Component
public class DBJob<OBJ_TYPE> extends AbstractJob{
	private static Logger Log = LoggerFactory.getLogger(DBJob.class);
	
//	ThreadPoolExecutor;
	private ExecutorService executor ;
	//private IPagedDBDataReader<OBJ_TYPE> dbReader;
	private static final int DEFAULT_POOL_SIZE = 10;
	private int poolSize;
	
	private List<VCBaseTask<?, ?>> tasksList = new ArrayList<VCBaseTask<?,?>>();
	
	@Override
	public void preProcess(JobInfo info) {
		executor = ExecutorHelper.getTPExecutor((poolSize == 0 ? DEFAULT_POOL_SIZE : poolSize));
		super.preProcess(info);
	}
	
	@Override
	public void postProcess(JobInfo info) {
		try{
			if(executor != null){
			//Shutdown executor.
				executor.shutdown();
			}
		}catch(Exception ex){
			Log.error("Error in graceful shutting down of executor:" + ex.getMessage(), ex);
			try{
				executor.shutdownNow();
			}catch(Throwable t){
				Log.error("Error force shutting down of executor:" + ex.getMessage(), ex);
			}
		}
		super.postProcess(info);
	}
	
	
	
	@Override
	public List<FlowOutput> executeJob(JobInfo info, Map<String, String> jobParams) {

		String paramName = jobParams.get("DB_PARAM_NAME");
		String paramValue = jobParams.get("DB_PARAM_VALUE");
		
//		if(!StringUtil.isNullOrEmpty(paramName) && !StringUtil.isNullOrEmpty(paramValue)){
//			dbReader.addParam(paramName, paramValue);
//		}
//				
//		getDbReader().initializeResources();
		
		List<Future<FlowOutput>> futures = new ArrayList<Future<FlowOutput>>();
		int i =0;
//		while(getDbReader().hasNext()){
//			System.out.println("Processing Page: " + i);
//			i++;
//			
//			List<OBJ_TYPE> list = getDbReader().nextPage();
//	
//			futures.add(executor.submit(new TaskExecutor(list, jobParams)));
////			System.out.println(list);
//		}
		
		List<FlowOutput> jobOutput = new ArrayList<>();
		for(Future<FlowOutput> future : futures){ 
			try {
				FlowOutput flowOutput = future.get();
				
				if(flowOutput!=null){
					jobOutput.add(flowOutput);
				}
			} catch (Exception e) {
				Log.error("Error in flow:" + e.getMessage(), e);
			}
		}
		
		return jobOutput;
	}
	
	
//	public IPagedDBDataReader<OBJ_TYPE> getDbReader() {
//		return dbReader;
//	}
//
//	public void setDbReader(IPagedDBDataReader<OBJ_TYPE> dbReader) {
//		this.dbReader = dbReader;
//	}

	public List<VCBaseTask<?, ?>> getTasksList() {
		return tasksList;
	}

	public void setTasksList(List<VCBaseTask<?, ?>> tasksList) {
		this.tasksList = tasksList;
	}

	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}
	
	
	private class TaskExecutor implements Callable<FlowOutput>{
		List<OBJ_TYPE> list;
		Map<String, String> jobParams;
		
		public TaskExecutor(List<OBJ_TYPE> _list, Map<String, String> _jobParams) {
			list = _list;
			jobParams = _jobParams;
		}

		@Override
		public FlowOutput call() throws Exception {
			Object taskInput = list;
			
			FlowInfo flowInfo = new FlowInfo(jobParams);
			String inputName = tasksList.get(0).getTaskInput();
			
			flowInfo.addFlowAttr(FlowParameter.valueOf(inputName), taskInput);
			
			for(VCBaseTask task : getTasksList()){
				task.executeTask(flowInfo);
			}
			
			return flowInfo.getFlowOutput();
		}
		
	}
}
