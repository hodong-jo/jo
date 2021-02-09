package com.concurrent.atomic;

import org.apache.log4j.Logger;

public class CounterRunnable implements Runnable{
	
	final Logger logger = Logger.getLogger("atomicTestLogger");
	
	private Counter counter;
	private int addValue;
	
	public CounterRunnable(Counter counter, int addValue) {
		this.counter = counter;
		this.addValue = addValue;
	}
	
	@Override
	public void run() {
		int count = 0;
		if(this.addValue == 1) {
			count = this.counter.incrementAngGet();
		}else {
			count = this.counter.addAngGet(addValue);
		}
		this.logger.info("AddedValue: " + this.addValue + ", Count: " + count );
		
	}
	
	

}
