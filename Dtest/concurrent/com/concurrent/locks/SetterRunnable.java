package com.concurrent.locks;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

public class SetterRunnable implements Runnable {
	static final AtomicInteger INDEX = new AtomicInteger();
	final Logger logger = Logger.getLogger("lockTestLogger");

	LockProcess process;
	final AtomicBoolean loop = new AtomicBoolean(true);
	long setterSleep = 2000;

	public SetterRunnable(LockProcess process, long setterSleep) {
		this.process = process;
		this.setterSleep = setterSleep;
	}

	@Override
	public void run() {
		this.logger.warn("[" + this.getClass().getSimpleName() + "] started");

		while (this.loop.get()) {
			long start = System.currentTimeMillis();
			String data = "TEST DATA" + String.valueOf(INDEX.incrementAndGet());

			this.logger.debug("[" + this.getClass().getSimpleName() + "]set data[" + INDEX.get() + "]:started");
			this.process.set(data);

			this.logger.info("[" + this.getClass().getSimpleName() + "]Set data[" + INDEX.get() + "]:[" + data + "]");

			try {
				Thread.sleep(this.setterSleep);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.logger.debug("[" + this.getClass().getSimpleName() + "]Set data[" + INDEX.get() + "]:ended["
					+ (System.currentTimeMillis() - start) + " ms.]");

		}

		this.logger.warn("[" + this.getClass().getSimpleName() + "] finished");

	}

	public void shutdown() {
		this.loop.set(false);
	}

}
