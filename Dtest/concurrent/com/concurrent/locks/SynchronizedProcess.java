package com.concurrent.locks;

public class SynchronizedProcess implements LockProcess{

	private String data;
	private long readSleep = 10;
	private long writeSleep = 100;
	
	public SynchronizedProcess(long readSleep, long writeSleep) {
		this.readSleep = readSleep;
		this.writeSleep = writeSleep;
	}
	
	@Override
	public synchronized String get() {
		try {
			Thread.sleep(this.readSleep);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.data;
	}

	@Override
	public synchronized void set(String data) {
		try {
			Thread.sleep(this.writeSleep);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.data = data;
	}
	
	

}
