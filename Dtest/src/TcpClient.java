import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

public class TcpClient {

	public static void main(String[] args) {
		int port = 9005;
		InetSocketAddress endpoint = new InetSocketAddress("localhost", port);
		Socket client = new Socket();
		OutputStream out = null;
		Connection conn = null;
		int connectTimeout = 1000;
		try {
			client.connect(endpoint, connectTimeout);
			
			out = client.getOutputStream();
			
			String s = "Test";
			out.write(s.getBytes());
			out.flush();
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) { try { out.close(); } catch (Exception e) { e.printStackTrace();}}
			if(client != null) { try { client.close(); } catch (Exception e) { e.printStackTrace();}}
		}
	}
}
