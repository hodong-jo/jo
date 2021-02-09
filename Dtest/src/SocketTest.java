import java.net.Socket;

public class SocketTest {
	
	public static void main(String[] args) {
		String ip = "127.0.0.1";
		try {
			Socket socket = new Socket(ip,8888);
			System.out.println("connect");
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
