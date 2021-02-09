import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class RmiClient {

	public static void main(String[] args) {
		try{
			
			String host = "localhost";
			int port = 9999;
			String lookupName = "JMXAgent";
			
			Map env = new HashMap();
			JMXServiceURL url = new JMXServiceURL(
					"service:jmx:rmi://" + host + ":" + port + "/jndi/rmi://" + host + ":" + port + "/" + lookupName);
			JMXConnector connector = JMXConnectorFactory.connect(url, env);
			MBeanServerConnection connection = connector.getMBeanServerConnection();
			
			ObjectName name1 = ObjectName.getInstance("JMXMonitor:type=ThreadMonitor");
			
			int memoryInfo = (int) connection.getAttribute(name1, "ThreadCounter");
			
			System.out.println("threadCounter:" + memoryInfo);
			
			connection.invoke(name1, "a", null, null);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
