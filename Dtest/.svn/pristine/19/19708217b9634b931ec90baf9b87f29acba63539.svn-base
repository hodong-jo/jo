package com.concurrent.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceTest {

	
	//CompletionService 를 이용하면 스레드풀에서 작업처리가 완료된 것만 통보받을수 있다.
	
	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
		
		System.out.println("처리 요청");
		
		for(int i = 0; i < 3; i++) {
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
						int sum = 0;
						
						for(int i = 1; i <= 10; i++) {
							sum += i;
//							System.out.println("test");
						}
					String threadName = Thread.currentThread().getName();
					System.out.println(threadName);
					return sum;
				}
				
			});
		}
		
		System.out.println("처리 완료된 작업 확인");
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				while (true) {
					
					try {
						Future<Integer> future = completionService.take();
						int value = future.get();
						System.out.println("처리결과: " + value);
						String threadName = Thread.currentThread().getName();
						System.out.println(threadName);
					} catch (Exception e) {
						break;
					}
				}
				
			}
			
		});
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		executorService.shutdown();

	}

}
