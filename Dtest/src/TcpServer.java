import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

import utils.IOUtil;

public class TcpServer {

	public static void main(String[] args) {
		int port = 9005;
		ServerSocket ss = null;
		Connection conn = null;
		
		
		try {
			InetSocketAddress endpoint = new InetSocketAddress(port);
			ss = new ServerSocket();
			ss.bind(endpoint);
			
			while(true) {
				Socket socket = ss.accept();
				InputStream in = null;
				OutputStream out = null;
				
				in = socket.getInputStream();
				out = socket.getOutputStream();
				
				byte[] HeaderData = new byte[4];
				byte[] recvData = new byte[4];
				
				in.read(HeaderData);
				
				System.out.println("recvHeader : " + new String(HeaderData));
				
				int dataSize = Integer.parseInt(new String(HeaderData));
				recvData = new byte[dataSize];
				in.read(recvData);
				
				System.out.println("recvData : " + new String(recvData));
				
				String sendData = "0006testtt";
				
				System.out.println("sendData : " + sendData);
				
				out.write(sendData.getBytes());
				out.flush();
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) { try { conn.close(); } catch (Exception e) { e.printStackTrace();}}
			if(ss != null) { try { ss.close(); } catch (Exception e) { e.printStackTrace();}}
		}
		
	}
}
