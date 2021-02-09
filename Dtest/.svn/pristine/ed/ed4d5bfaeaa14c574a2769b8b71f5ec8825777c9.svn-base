package com.concurrent.locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AtomicLockProcess implements LockProcess{
	protected final ReadWriteLock LOCK = new ReentrantReadWriteLock();
	private String data;
	private long readSleep = 10;
	private long writeSleep = 100;
	
	public AtomicLockProcess(long readSleep, long writeSleep) {
		this.readSleep = readSleep;
		this.writeSleep = writeSleep;
	}

	@Override
	public String get() {
		LOCK.readLock().lock();
		try {
			try {
				Thread.sleep(this.readSleep);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this.data;
		}finally {
			LOCK.readLock().unlock();
		}
	}

	@Override
	public void set(String data) {
		LOCK.writeLock().lock();
		
		try {
			try {
				Thread.sleep(this.writeSleep);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.data = data;
		} finally {
			LOCK.writeLock().unlock();
		}
		
	}

}
