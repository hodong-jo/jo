package socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.log4j.Logger;

import utils.IOUtil;

public class SocketClient {
	
	static Logger logger = Logger.getLogger(SocketClient.class);
	
	static void test() {
		InetSocketAddress endpoint = new InetSocketAddress("192.168.0.14", 9000);
		Socket client = new Socket();
		OutputStream out = null;
		InputStream in = null;
		BufferedReader bfr = null;
		int connectTimeout = 1000; //milliseconds
	
		try{
			client.connect(endpoint, connectTimeout);
			logger.info("Connected : [" + endpoint + "]");
			
			out = client.getOutputStream();
			in = client.getInputStream();
			
			String input = "HELLOWORLD\nabc\ntest\nend\n"; 
			byte[] sendData = input.getBytes();
			out.write(sendData);
			out.flush();
			
			logger.info("Send : [" + input + "]");

			bfr = new BufferedReader(new InputStreamReader(in));
			
			String line;
			while((line = bfr.readLine()) != null) {
				logger.info("Recv : [" + line + "]");
				if(line.equals("end"))
					break;
				Thread.sleep(1000);
			}
			
		}catch(Exception ex) {
			logger.error("Failed:", ex);
		}finally{
			IOUtil.close(bfr);
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
		t2.start();
	}

}
