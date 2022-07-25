package org.secondopinion.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorHelper {

	public static ExecutorService getTPExecutor(int threadPoolSize){
		return Executors.newFixedThreadPool(threadPoolSize);
	}
}
