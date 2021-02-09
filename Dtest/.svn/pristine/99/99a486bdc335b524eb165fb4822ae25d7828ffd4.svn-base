/**
 *  내용        : 
 *  수정이력    
 * 
 *    수정일         수정자   버전     수정내용
 *    ---------     -------- ------  -----------------------------
 *    2019. 11. 18.   tghan    1.0    최초 작성
 *
 */

package com.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author tghan
 * @since 2019. 11. 18.
 * @version 1.0
 * @see 
 */
public class OnceExcTest {

	public static void main(String[] args) {
		try {
			
			Timer timer = new Timer();
			
			
			TimerTask task1 = new TestTimerTask1("Task1");
			
			Calendar cal = Calendar.getInstance();
			cal.set(2019, Calendar.NOVEMBER, 18, 11, 13, 30);
			Date time = cal.getTime();
			
			timer.schedule(task1, time);
			
			
			TimerTask task2 = new TestTimerTask2("Task2");
			
			timer.schedule(task2, 10000);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
