package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

class WorkThread extends Thread {
	static Logger logger = Logger.getLogger(SocketServer.class);
	static AtomicInteger SEQ = new AtomicInteger();
	
	Socket s;
	InputStream dis;
	OutputStream dos;
	
	public WorkThread(Socket s,InputStream dis, OutputStream dos) {
		this.s = s;
		this.dis = dis;
		this.dos = dos;
	}
	
	public void run() {
		String line;
		BufferedReader bfr = new BufferedReader(new InputStreamReader(dis));
		PrintWriter pw = new PrintWriter(dos);
		StringBuffer sb = new StringBuffer();
		
		try {
			int seq = SEQ.incrementAndGet();
			while((line = bfr.readLine()) != null) {
				logger.info("Recv["+ seq  + "] : [" + line + "]");
				pw.println(line);
				sb.append(line);
				if(line.equals("end")) {
					break;
				}
				Thread.sleep(1000);
				logger.info("Send["+ seq  + "] : [" + sb.toString() + "]");
				pw.flush();
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			this.dis.close();
			this.dos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
