package com.concurrent.atomic;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import utils.ConcurrentUtil;

public class CounterTest {

	static final Logger logger = Logger.getLogger("atomicTestLogger");

	public static void main(String[] args) {

		boolean synchronizedCounter = false;
		int addValue = 1;

		if (args != null) { // ???
			if (args.length == 1) {
				synchronizedCounter = Boolean.parseBoolean(args[0]);
			} else if (args.length > 1) {
				synchronizedCounter = Boolean.parseBoolean(args[0]);
				addValue = Integer.parseInt(args[1]);
			}
		}

		int concurrentSize = 5;
		int poolSize = 10;

		int loop = 10000;

		// true이면 SynchronizedCounter, false이면 AtomicCounter가 생성됨
		final Counter counter = CounterFactory.createCounter(synchronizedCounter);
		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);

		long start = System.currentTimeMillis();

		try {

			logger.warn("CounterTest started:" + counter.getClass().getSimpleName());

			for (int j = 0; j < loop; j++) {
				for (int i = 0; i < concurrentSize; i++) {
					pool.execute(new CounterRunnable(counter, addValue));
				}
			}
			ConcurrentUtil.shutdown(pool, 60000);

		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			logger.warn("CounterTest ended:" + counter.getClass().getSimpleName() + "[Total:" + (loop*concurrentSize) 
					+ ",Elapsed:" + (System.currentTimeMillis() - start) + " ms.]");
		}

	}

}
