package netty;
import java.io.File;
import java.net.InetSocketAddress;

import datasource.DBCPTest;
import datasource.DataSourceLoader;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import utils.ClassUtil;

public class ChatServer {
	private final int port;
	
	public ChatServer(int port) {
		this.port = port;
		
		File configFile = new File(ClassUtil.getBaseClassLoader().getResource("datasource.properties").getFile());
		try {
			DataSourceLoader.loadDataSource(configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) throws Exception {
		int port = Integer.parseInt("8888");
		new ChatServer(port).start();
	}
	
	public void start() throws Exception{
		SelfSignedCertificate ssc = new SelfSignedCertificate();
		SslContext sslContext = SslContextBuilder.forServer(ssc.certificate(),ssc.privateKey()).build();
		
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
					 .channel(NioServerSocketChannel.class)// NIO 전송 채널을 이용하도록 설정
//					 .localAddress(new InetSocketAddress(port))
					 .handler(new LoggingHandler(LogLevel.INFO))// 서버 소켓채널에서 발생한 이벤트로그 출력
//					 .option(ChannelOption.SO_BACKLOG, 100)// 연결요청의 큐길이
					 .childHandler(new ChatServerInitializer(sslContext));
			
			bootstrap.bind(port).sync().channel().closeFuture().sync();
//			ChannelFuture channelFuture = bootstrap.bind(port).sync();// 서버를 비동기식으로 바인딩,sync()는 바인딩이 완료되기를 대기
//			channelFuture.channel().closeFuture().sync();// 채널의 CloseFuture를 얻고 완료될 떄까지 현재 스레드를 블로킹
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
