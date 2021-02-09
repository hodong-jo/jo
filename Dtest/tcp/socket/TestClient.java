package socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.log4j.Logger;

import utils.IOUtil;

public class TestClient {
	static Logger logger = Logger.getLogger(TestClient.class);
	
	static void test() {
		InetSocketAddress endpoint = new InetSocketAddress("192.168.0.38", 9005);
		Socket client = new Socket();
		OutputStream out = null;
		InputStream in = null;
		int connectTimeout = 1000; //milliseconds
		try{
			client.connect(endpoint, connectTimeout);
			logger.info("Connected : [" + endpoint + "]");
			
			out = client.getOutputStream();
			in = client.getInputStream();
			
			String input = "HELLOW123TEST12345����";	
			byte[] sendData = input.getBytes();
			int len = sendData.length;
			String lenStr = String.format("%04d", len);
			out.write(lenStr.getBytes());
			out.write(sendData);
			out.flush();
			
			logger.info("Send : [" + input + "]");
			
			byte[] recvData = new byte[22];
			in.read(recvData);
			
			String output = new String(recvData);
			logger.info("Recv : [" + output + "]");    

		}catch(Exception ex) {
			logger.error("Failed:", ex);
		}finally{
			IOUtil.close(in);
			IOUtil.close(out);
			IOUtil.close(client);
			
			logger.info("Closed : [" + endpoint + "]");    
		}
	}
	
	public static void main(String[] args){
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				test();
			}
			
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				test();
			}
			
		});
		
		t1.start();
//		t2.start();
	}
}
