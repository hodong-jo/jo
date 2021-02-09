import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

public class EchoServer {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8080);
		
		Socket socket = null;
		if((socket = ss.accept()) != null) {
			IOUtils.copy(socket.getInputStream(), System.out);
		}
	}

}
