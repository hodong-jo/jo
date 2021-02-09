package com.concurrent.threadpool;

public class WorkerThread implements Runnable{
	
	String command;
	
	public WorkerThread(String s) {
		this.command = s;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"Start" + command);
		processCommand();
		System.out.println(Thread.currentThread().getName()+"End" + command);
	}
	
	private void processCommand() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
