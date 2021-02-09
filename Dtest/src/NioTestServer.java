import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioTestServer {

	public static void main(String[] args) {
		try {
			RandomAccessFile aFile = new RandomAccessFile("D:/test/test.txt", "rw");
			RandomAccessFile aFile2 = new RandomAccessFile("D:/test/test2.txt", "rw");
			FileChannel inChannel = aFile.getChannel();
			FileChannel inChannel2 = aFile2.getChannel();
			ByteBuffer buf1 = ByteBuffer.allocate(48);
			ByteBuffer buf2 = ByteBuffer.allocate(128);
			ByteBuffer[] buffers = {buf1,buf2};
			
			
//			long count = inChannel.size();
			
//			System.out.println(count);
//			System.out.println();
//			inChannel2.transferFrom(inChannel, 0, count);
			
			
//			long count2 = inChannel.size();
//			System.out.println(count2);
			
//			buf.flip();
			int bytesRead = inChannel.read(buf1);
			while (bytesRead != -1) {
				System.out.println("\nRead " + bytesRead);
				buf1.flip();
				
				while(buf1.hasRemaining()) {
					System.out.print((char) buf1.get());
				}
				buf1.clear();
//				System.out.println("bytesdRead = " + bytesRead);
				bytesRead = inChannel.read(buf1);
//				System.out.println("bytesdRead = " + bytesRead);
			}
			aFile.close();
			buf1.mark();
//			System.out.println(buf.position());
			char x = buf1.getChar();
			int y = buf1.getInt();
			byte z = buf1.get();
//			System.out.println(buf.position());
//			buf.reset();
			
//			System.out.println(buf.get());
			
			ServerSocketChannel channel = ServerSocketChannel.open();
			channel.socket().bind(new InetSocketAddress(8080));
			
			while(true) {
				SocketChannel sc = channel.accept();
				System.out.println("connect");
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
