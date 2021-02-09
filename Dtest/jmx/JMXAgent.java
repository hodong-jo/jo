import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class JMXAgent {

	public static void main(String[] args) throws Exception {
		String host = "localhost";
		int port = 9999;
		String lookupName = "JMXAgent";
		
		VirtualEnvThread t1 = new VirtualEnvThread();
		t1.start();
		TestThread t2 = new TestThread();
		t2.start();
		TestThread2 t3 = new TestThread2();
		t3.start();
		
		
		ThreadMonitor threadMonitor = new ThreadMonitor();
		ObjectName name = new ObjectName("JMXMonitor:type=ThreadMonitor");

		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		server.registerMBean(threadMonitor, name);
		
		LocateRegistry.createRegistry(port);
		
		Map env = new HashMap();
		JMXServiceURL url = new JMXServiceURL (
				"service:jmx:rmi://" + host + ":" + port + "/jndi/rmi://" + host + ":" + port + "/" + lookupName);
		JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, env, server);
		
		System.out.println(url);
		
		
//		HtmlAdaptorServer html_server = new HtmlAdaptorServer();
//		html_server.setPort(port);
		
//		ObjectName html_name = new ObjectName("JMXAgent:type=HttpJMXAgent,port=" + port);
//		server.registerMBean(html_server, html_name);
		
		ObjectName server_name =
				new ObjectName("JMXAgent:type=RmiJMXAgent,port=" + port);
		server.registerMBean(cs, server_name);
		
		cs.start();
		
//		html_server.start(); //HttpJMX
		System.out.println(port);
	}

}

class TestThread extends Thread{
	public void run() {
		System.out.println("TestThread start");
		for(int i = 0; i < 100000; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
class TestThread2 extends Thread{
	public void run() {
		System.out.println("TestThread2 start");
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}