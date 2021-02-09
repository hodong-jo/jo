/**
 *  ����        : 
 *  �����̷�    
 * 
 *    ������         ������   ����     ��������
 *    ---------     -------- ------  -----------------------------
 *    2019. 11. 18.   tghan    1.0    ���� �ۼ�
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
			
			timer.scheduleAtFixedRate(task1, 0, 2000, TimeUnit.MILLISECONDS); //�����ð����� ���� �۾��� Ư���ð����� �����ؾ��ϴ� �۾��ϰ�� ���
//			timer.scheduleWithFixedDelay(task1, 0, 2000, TimeUnit.MILLISECONDS); //�۾��̳����������� �����̽ð���ŭ ��ٸ��� ���� �۾�����

		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
