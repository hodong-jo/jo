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

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author tghan
 * @since 2019. 11. 18.
 * @version 1.0
 * @see 
 */
public class RepeatExcTest {

	public static void main(String[] args) {
		try {
			
			Timer timer1 = new Timer();
//			Timer timer2 = new Timer();
			
			TimerTask task1 = new TestTimerTask1("Task1-1");
			TimerTask task2 = new TestTimerTask1("Task1-2");
			
			timer1.schedule(task1, 0, 2000); //EX)TTWWWGGTTWWWTTWWW W=대기5초,T=작업2초,G=GC 동작중 중간에 GG가 들어오면 끝난후 W5초실행
//			timer1.scheduleAtFixedRate(task1, 0, 2000); //지정시간으로 부터 작업이 특정시간마다 실행해야하는 작업일경우 사용
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
