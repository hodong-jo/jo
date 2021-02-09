package com.concurrent.locks;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import utils.ConcurrentUtil;

public class LockProcessTest {

	static final Logger logger = Logger.getLogger("lockTestLogger");

	public static void main(String[] args) {
		
		boolean synchronizedProcess = false;
		
		if(args != null && args.length > 0) {
			synchronizedProcess = Boolean.parseBoolean(args[0]);
		}	
		
		long readSleep = 10;
		long writeSleep = 10;
		long setterSleep = 2000;
		
		int concurrentSize = 5;
		int poolSize = 10;
		
		int loop = 100;
		
		final LockProcess process = LockProcessFacktory.createLockProcess(synchronizedProcess, readSleep, writeSleep);
		ThreadPoolExecutor pool = (ThreadPoolExecutor)Executors.newFixedThreadPool(poolSize);

		long start = System.currentTimeMillis();
		
		try {
			
			logger.warn("LockProcessTest started: " + process.getClass().getSimpleName());
			
			SetterRunnable setterRunnable = new SetterRunnable(process, setterSleep);
			Thread setterThread = new Thread(setterRunnable);
			setterThread.start();
			
			for(int j = 0; j < loop; j++) {
				for(int i = 0; i < concurrentSize; i++) {
					pool.execute(new GetterRunnable(process));
				}
			}
			ConcurrentUtil.shutdown(pool, 60000);
			
			setterRunnable.shutdown();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			logger.warn("LockProcessTest ended:" + process.getClass().getSimpleName() + "[Total:" + (loop*concurrentSize) 
					+ ",Elapsed:" + (System.currentTimeMillis() - start) + " ms.]");
		}
		
	}

}
