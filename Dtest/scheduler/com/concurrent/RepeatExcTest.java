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
public class RepeatExcTest {

	public static void main(String[] args) {
		try {
			
			ScheduledExecutorService timer = Executors.newScheduledThreadPool(10);
			
			Runnable task1 = new TestTimerTask1("Task1-1");
			Runnable task2 = new TestTimerTask1("Task1-2");
			
			timer.scheduleAtFixedRate(task1, 0, 2000, TimeUnit.MILLISECONDS); //지정시간으로 부터 작업이 특정시간마다 실행해야하는 작업일경우 사용
//			timer.scheduleWithFixedDelay(task1, 0, 2000, TimeUnit.MILLISECONDS); //작업이끝난시점부터 딜레이시간만큼 기다린후 이후 작업실행

		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
