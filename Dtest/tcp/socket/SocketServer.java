package socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import utils.IOUtil;

public class SocketServer {
	static Logger logger = Logger.getLogger(SocketServer.class);
	static AtomicInteger SEQ = new AtomicInteger();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 9000;
		ServerSocket ss = null;
		try{
			InetSocketAddress endpoint = new InetSocketAddress("192.168.0.69",port);
			ss = new ServerSocket();
			ss.bind(endpoint);
			logger.info("Socket server listen : " + port);
			
			while(true){
				Socket socket = ss.accept();
				InputStream in = null;
				OutputStream out = null;
				BufferedReader bfr = null;
				PrintWriter pw = null;
				StringBuffer sb = new StringBuffer();
				int seq = SEQ.incrementAndGet();
				try{
					in = socket.getInputStream();
					out = socket.getOutputStream();
					
					String line;
					bfr = new BufferedReader(new InputStreamReader(in));
					pw = new PrintWriter(out);
					
					while((line = bfr.readLine()) != null) {
						logger.info("Recv[" + seq + "] : [" + line + "]");
						pw.println(line);
						sb.append(line);
						if(line.equals("end")) {
							break;
						}
						Thread.sleep(1000);
					}
					logger.info("Send[" + seq + "] : [" + sb.toString() + "]");
					pw.flush();
					
					
				}catch(Exception ex){
					logger.error("[#ERROR][" + seq + "]", ex);
				}finally{
					if( pw != null) try { pw.close(); } catch (Exception e) { e.printStackTrace(); }
					IOUtil.close(bfr);
					IOUtil.close(socket);
				}
			
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			
			IOUtil.close(ss);
		}
	}

}
