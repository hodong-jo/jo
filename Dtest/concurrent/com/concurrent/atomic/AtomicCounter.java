package com.concurrent.atomic;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter implements Counter{
	
	private AtomicInteger count = new AtomicInteger();
	@Override
	public int incrementAngGet() {
		return this.count.incrementAndGet();
	}

	@Override
	public int decrementAndGet() {
		return this.count.decrementAndGet();
	}

	@Override
	public int addAngGet(int delta) {
		return this.count.addAndGet(delta);
	}

	@Override
	public int get() {
		return this.count.get();
	}

}
