package netty;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.util.CharsetUtil;

public class ChatServerInitializer extends ChannelInitializer<SocketChannel>{

	private final SslContext sslContext;
	
	public ChatServerInitializer(SslContext sslContext){
		this.sslContext = sslContext;
	}
	
	// 비지니스 로직을 처리할 채널핸들러등록
	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
		ChannelPipeline pipeline = socketChannel.pipeline();
        
		pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()));
		pipeline.addLast(new StringDecoder());
		pipeline.addLast(new StringEncoder());
		
//		pipeline.addLast ( "frameDecoder", new LineBasedFrameDecoder (80));
//		pipeline.addLast ( "stringDecoder", new StringDecoder (CharsetUtil.UTF_8));
//
//		pipeline.addLast ( "stringEncoder", new StringEncoder (CharsetUtil.UTF_8));
		
        // Handler
		pipeline.addLast("handler", new ChatServerHandler());
		
	}

}
