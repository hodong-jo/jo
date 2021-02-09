import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TestTcp {
	
	public static void main(String[] args) {
		InetSocketAddress inetSocketAddress = new InetSocketAddress("192.168.127.131",23);
		Socket client = new Socket();
		OutputStream out = null;
		
		try {
			client.connect(inetSocketAddress);
			
			out = client.getOutputStream();
			
			String str = "TESTESTESTESTESETEST";
			out.write(str.getBytes());
			out.flush();
			System.out.println("º¸³¿");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
