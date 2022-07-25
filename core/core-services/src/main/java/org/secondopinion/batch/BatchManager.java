package org.secondopinion.batch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class BatchManager<T> {
	private static final Log LOG = LogFactory.getLog(BatchManager.class);
	private Queue<T> queue;
	private int depth;
	private long maxWaitTime;
	private Object LOCK = new Object();
	
	private IBatchDequeCallBack<T> bdcb;
	
	private BMThread thread;
	private ExecutorService executor =  Executors.newFixedThreadPool(5); //ExecutorHelper.getTPExecutor(2);
	
	public BatchManager(int depth, IBatchDequeCallBack<T> bdcb, long maxWaitTime) {
		this.depth = depth;
		this.queue = new LinkedBlockingQueue<T>(depth*5);
		this.maxWaitTime = maxWaitTime;
		
		this.bdcb = bdcb;
		
		thread = new BMThread();
		thread.start();
		
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				thread.setStopThread(true);
				executor.shutdown();
			}
		});
	}
	
	public void add(T obj){
		synchronized (LOCK) {
			checkDepth();
			queue.add(obj);
		}
	}
	
	private void checkDepth(){
		if (queue.size() > depth) {
			List<T> batch = new ArrayList<T>();
			((LinkedBlockingQueue<T>) queue).drainTo(batch, depth);
			executor.submit(new BMTask(batch));
		}
	}
	
	public void add(Collection<T> obj){
//		TODO: make this method asynch
		for(T t : obj){
			add(t);
		}
	}
	
	private class BMThread extends Thread{
		private volatile boolean stopThread = false;
		
		@Override
		public void run() {
			while(!stopThread){
				try {
					List<T> batch = new ArrayList<T>();
					synchronized (LOCK) {
						if(queue.size() > 0){
							int num = ((LinkedBlockingQueue<T>)queue).drainTo(batch);
							LOG.debug("Number drained: " + num + " batchsize: " + batch.size());
						}
					}
					
					if(batch.size()>0){
						bdcb.processCollection(batch);
					}
					
					Thread.sleep(maxWaitTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void setStopThread(boolean stopThread) {
			this.stopThread = stopThread;
		}
	}
	
	private class BMTask implements Runnable{

		Collection<T> batch;
		
		public BMTask(Collection<T> _batch) {
			this.batch = _batch;
		}
		
		@Override
		public void run() {
			LOG.debug("Number drained/processed: " + batch.size());
			if(batch.size()>0){
				bdcb.processCollection(batch);
			}
			
		}
		
	}
	public IBatchDequeCallBack<T> getBdcb(){
		return bdcb;
	}
}