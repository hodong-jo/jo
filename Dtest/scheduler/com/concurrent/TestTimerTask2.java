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

import java.util.UUID;

import org.apache.log4j.Logger;

/**
 * @author tghan
 * @since 2019. 11. 18.
 * @version 1.0
 * @see 
 */
public class TestTimerTask2 implements Runnable {

	Logger logger = Logger.getLogger(getClass());
	
	String name;
	
	public TestTimerTask2(String name) {
		super();
		this.name = name;
	}

	
	@Override
	public void run() {
		
		String uuid = UUID.randomUUID().toString();
		
		logger.info("[" + name + "][" + uuid + "][" + Thread.currentThread().getName() + "] " + this.getClass().getName() + " started.");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("[" + name + "][" + uuid + "][" + Thread.currentThread().getName() + "] " + this.getClass().getName() + " finished.");
		
	}

}
