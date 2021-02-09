package com.concurrent.locks;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

public class GetterRunnable implements Runnable{
	static final AtomicInteger INDEX = new AtomicInteger();
	final Logger logger = Logger.getLogger("lockTestLogger");
	
	LockProcess process;
	
	public GetterRunnable(LockProcess process) {
		this.process = process;
	}
	
	@Override
	public void run() {
		long start = System.currentTimeMillis();
		
		INDEX.incrementAndGet();
		
		this.logger.debug("[" + this.getClass().getSimpleName() + "]Get data[" + INDEX.get() + "]:started");
		String data = this.process.get();
		
		this.logger.info("[" + this.getClass().getSimpleName() + "]Get data[" + INDEX.get() + "]:[" +  data + "]");
		this.logger.debug("[" + this.getClass().getSimpleName() + "]Get data[" + INDEX.get() + "]:ended[" 
					+ (System.currentTimeMillis() - start) + " ms.]");
		
	}

}
