package com.concurrent.atomic;

public class SynchronizedCounter implements Counter{
	
	private volatile int count;
	
	@Override
	public synchronized int incrementAngGet() {
		return ++ this.count;
	}

	@Override
	public synchronized int decrementAndGet() {
		return -- this.count;
	}

	@Override
	public synchronized int addAngGet(int delta) {
		this.count = this.count + delta;
		return this.count;
	}

	@Override
	public synchronized int get() {
		return this.count;
	}

}
