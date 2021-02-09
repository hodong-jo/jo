/**
 *  내용        : 
 *  수정이력    
 * 
 *    수정일         수정자   버전     수정내용
 *    ---------     -------- ------  -----------------------------
 *    2009. 03. 03   tghan    1.0    최초 작성
 *
 */

package utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author tghan
 * @since 2009. 03. 03
 * @version 1.0
 * @see 
 */
public class ConcurrentUtil {
	
	public static ExecutorService newCachedThreadPool(){
		return Executors.newCachedThreadPool();
	}
	 
	public static ExecutorService newFixedThreadPool(int nThreads){
		return Executors.newFixedThreadPool(nThreads);
	}
	
	public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory){
		return Executors.newFixedThreadPool(nThreads, threadFactory);
	}
	
	public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
		return Executors.newScheduledThreadPool(corePoolSize);
	}
	
	public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, ThreadFactory threadFactory) {
		return Executors.newScheduledThreadPool(corePoolSize, threadFactory);
	}
	
	public static ExecutorService newSingleThreadExecutor(){
		return Executors.newSingleThreadExecutor();
	}
	
	public static ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory){
		return Executors.newSingleThreadExecutor(threadFactory);
	}
	
	public static ScheduledExecutorService newSingleThreadScheduledExecutor(){
		return Executors.newSingleThreadScheduledExecutor();
	}
	
	public static ScheduledExecutorService newSingleThreadScheduledExecutor(ThreadFactory threadFactory){
		return Executors.newSingleThreadScheduledExecutor(threadFactory);
	}
	
	public static void shutdown(ExecutorService service, long waitTimeout){
		if(service == null) return;
		service.shutdown();
		if(waitTimeout > 0){
			try{
				if(!service.awaitTermination(waitTimeout, TimeUnit.MILLISECONDS)){
					throw new RuntimeException("ExecutorService shutdown wait timeout [" + waitTimeout + " milliseconds] : " 
							+ service.getClass().getName());
				}
			}catch(InterruptedException ie){}
		}
	}
	
	public static void shutdownNow(ExecutorService service, long waitTimeout){
		if(service == null) return;
		service.shutdownNow();
		if(waitTimeout > 0){
			try{
				if(!service.awaitTermination(waitTimeout, TimeUnit.MILLISECONDS)){
					throw new RuntimeException("ExecutorService shutdownNow wait timeout [" + waitTimeout + " milliseconds] : " 
							+ service.getClass().getName());
				}
			}catch(InterruptedException ie){}
		}
	}
}
