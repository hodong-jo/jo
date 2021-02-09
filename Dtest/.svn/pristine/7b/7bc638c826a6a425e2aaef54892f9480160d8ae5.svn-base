package socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import utils.IOUtil;

public class MultiThreadSocketServer {
	static Logger logger = Logger.getLogger(SocketServer.class);
	static AtomicInteger SEQ = new AtomicInteger();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 9000;
		ServerSocket ss = null;
		InputStream in = null;
		OutputStream out = null;
		ExecutorService executor = Executors.newFixedThreadPool(3);
		try{
			InetSocketAddress endpoint = new InetSocketAddress(port);
			ss = new ServerSocket();
			ss.bind(endpoint);
			logger.info("Socket server listen : " + port);
			
			while(true){
				Socket socket = ss.accept();
				int seq = SEQ.incrementAndGet();
				
				try{
					in = socket.getInputStream();
					out = socket.getOutputStream();
					
					executor.execute(new WorkThread(socket, in, out));
					
				}catch(Exception ex){
					logger.error("[#ERROR][" + seq + "]", ex);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			executor.shutdown();
			if( in != null) try { in.close(); } catch (Exception e) { e.printStackTrace(); }
			if( out != null) try { out.close(); } catch (Exception e) { e.printStackTrace(); }
			IOUtil.close(ss);
		}
	}
}


