package com.concurrent.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTest {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(3); //코어스레드수 3
		ExecutorService executorService2 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executorService2);
		
//		for(int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					//스레드에게 시킬 작업 내용
					ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)executorService;
					
					int poolSize = threadPoolExecutor.getPoolSize();//스레드풀사이즈
					String threadName = Thread.currentThread().getName();//스레드풀에있는 해당 스레드 이름
					
					System.out.println("총 스레드 개수 : " + poolSize + ", 작업 스레드 이름 : "+ threadName);
					
					int Value = Integer.parseInt("예외");
				}
			};
			
			Callable<String> task = new Callable<String>() {

				@Override
				public String call() throws Exception {
					
					String threadName = Thread.currentThread().getName();//스레드풀에있는 해당 스레드 이름
					
					return threadName;
				}
				
			};
			
			class Task2 implements Runnable{//두 개 이상의 스레드 작업을 취합할 목적
				Result result;
				
				Task2(Result result){
					this.result = result;
				}
				
				@Override
				public void run() {
					int sum = 0;
					
					for(int i = 0; i <=10; i++) {
						sum += i;
					}
					
					result.addValue(sum);
				}
			};
			Result result2 = new Result();
			Runnable task1 = new Task2(result2);
			Runnable task2 = new Task2(result2);
			Future<Result> future1 = executorService2.submit(task1, result2);
			Future<Result> future2 = executorService2.submit(task2, result2);
			
			String s;
//			completionService.submit(task, s);
			
//			executorService.execute(runnable); //스레드풀에 작업 처리 요청
//			executorService.submit(runnable); //submit을 사용하면 예외가 발생되도 해당 스레드를 죽이지않고 실행한다
//			executorService.submit(task);//Callable 테스트 
			
			Future<String> futer = executorService.submit(task);
			
			try {
//				futer.get();
//				String result = futer.get();
//				System.out.println(result);
//				System.out.println(executorService.submit(task).get());
				result2 = future1.get();
				result2 = future2.get();
				System.out.println("결과 : " + result2.accumValue);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
//		}
		
		executorService.shutdown();
		executorService2.shutdown();
		
	}

}

class Result {
	int accumValue;
	synchronized void addValue(int value) {
		accumValue += value;
	}
}