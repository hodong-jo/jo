package com.concurrent.atomic;

public class CounterFactory {
	
	public static Counter createCounter(boolean synchronizedCounter) {
		if(synchronizedCounter) {
			return new SynchronizedCounter();
		}else {
			return new AtomicCounter();
		}
	}
}
