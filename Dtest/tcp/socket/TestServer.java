package socket;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import utils.IOUtil;


public class TestServer {
	static Logger logger = Logger.getLogger(SocketServer.class);
	static AtomicInteger SEQ = new AtomicInteger();

	public static void main(String[] args) {
		int port = 9000;
		ServerSocket ss = null;
		try{
			InetSocketAddress endpoint = new InetSocketAddress(port);
			ss = new ServerSocket();
			ss.bind(endpoint);
			logger.info("Socket server listen : " + port);
			
			while(true){
				Socket socket = ss.accept();
				InputStream in = null;
				OutputStream out = null;
				ByteArrayOutputStream bout = new ByteArrayOutputStream(); 
				int seq = SEQ.incrementAndGet();
				try{
					in = socket.getInputStream();
					out = socket.getOutputStream();
					
					byte[] header = new byte[4];
					byte[] recvData;
					recvData = new byte[8];
					in.read(header);
					int inputSize = Integer.parseInt(new String(header));
					int recvDataLength = recvData.length;
					while(inputSize > recvDataLength) {
						inputSize = inputSize - in.read(recvData);
						if (inputSize == -1) {
							throw new EOFException("End of stream, Read bytes are insufficient");
						}
						bout.write(recvData);
					}
					recvData = new byte[inputSize];
					in.read(recvData);
					bout.write(recvData);
					logger.info("Recv[" + seq + "] : [" + bout.toString() + "]");
					
					out.write(bout.toByteArray());
					out.flush();
					
					logger.info("Send[" + seq + "] : [" + bout.toString() + "]");
					
					
				}catch(Exception ex){
					logger.error("[#ERROR][" + seq + "]", ex);
				}finally{
					if( bout != null) try { bout.close(); } catch (Exception e) { e.printStackTrace(); }
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