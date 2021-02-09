/**
 *  내용        : 
 *  수정이력    
 * 
 *    수정일         수정자   버전     수정내용
 *    ---------     -------- ------  -----------------------------
 *    2019. 11. 18.   tghan    1.0    최초 작성
 *
 */

package com.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author tghan
 * @since 2019. 11. 18.
 * @version 1.0
 * @see 
 */
public class OnceExcTest {

	public static void main(String[] args) {
		try {
			
			ScheduledExecutorService timer = Executors.newScheduledThreadPool(10);
			
			
			Runnable task1 = new TestTimerTask1("Task1");
			timer.schedule(task1, 5000, TimeUnit.MILLISECONDS);
			
			
			Runnable task2 = new TestTimerTask2("Task2");
			timer.schedule(task2, 5000, TimeUnit.MILLISECONDS);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
