package netty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ChatClient {
	private final String host;
	private final int port;
	
	public ChatClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public static void main(String[] args) throws Exception {
		String host = "localhost";
		int port = Integer.parseInt("8888");
		new ChatClient(host, port).start();
	}
	
	public void start() throws Exception{
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group)
					 .channel(NioSocketChannel.class)
//					 .remoteAddress(new InetSocketAddress(host,port))//서버의 InetSocketAddress를 설정
					 .handler(new ChatClidentInitializer());
			ChannelFuture channelFuture = null;
//			ChannelFuture channelFuture = bootstrap.connect().sync();
//			channelFuture.channel().closeFuture().sync();
			
			Channel channel = bootstrap.connect(host, port).sync().channel();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			for (;;) {
				String line = in.readLine();
				if(line == null) {
					System.out.println("linenull");
					break;
				}
				channelFuture = channel.writeAndFlush(line + "\n");
				
				if ("bye".equals(line.toLowerCase())){
					System.out.println("clinet bye");
					channel.closeFuture().sync();
					break;
				}
			}
			if(channelFuture != null) {
				channelFuture.sync();
			}
			System.out.println("test");
		} finally {
//			group.shutdownGracefully().sync();
			group.shutdownGracefully();
		}
	}
	
}
