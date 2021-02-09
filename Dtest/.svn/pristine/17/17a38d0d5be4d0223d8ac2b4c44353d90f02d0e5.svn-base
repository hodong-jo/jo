import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class ThreadMonitor implements ThreadMonitorMBean{


	@Override
	public int getThreadCounter() {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		return threadMXBean.getThreadCount();
	}
	
	@Override
	public long getTotalThreadCounter() {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		return threadMXBean.getTotalStartedThreadCount();
	}

	@Override
	public void a() {
		System.out.println("Test");
		
	}
	







}
