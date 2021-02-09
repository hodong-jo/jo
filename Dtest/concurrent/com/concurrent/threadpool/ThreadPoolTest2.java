package com.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest2 {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(int i = 0; i < 10; i ++) {
			Runnable worker = new WorkerThread("" + i);
			executor.execute(worker);
		}
		executor.shutdown();

	}

}