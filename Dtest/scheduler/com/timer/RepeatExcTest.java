/**
 *  ����        : 
 *  �����̷�    
 * 
 *    ������         ������   ����     ��������
 *    ---------     -------- ------  -----------------------------
 *    2019. 11. 18.   tghan    1.0    ���� �ۼ�
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
			
			timer1.schedule(task1, 0, 2000); //EX)TTWWWGGTTWWWTTWWW W=���5��,T=�۾�2��,G=GC ������ �߰��� GG�� ������ ������ W5�ʽ���
//			timer1.scheduleAtFixedRate(task1, 0, 2000); //�����ð����� ���� �۾��� Ư���ð����� �����ؾ��ϴ� �۾��ϰ�� ���
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
