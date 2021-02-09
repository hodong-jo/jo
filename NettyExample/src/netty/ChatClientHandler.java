package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@Sharable
public class ChatClientHandler extends ChannelInboundHandlerAdapter{

//	@Override
//	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		System.out.println("Active");
//		//채팅 활성화 알람을 받으면 메시지를 전송
//		System.out.println(ctx.writeAndFlush(Unpooled.copiedBuffer("Ne", CharsetUtil.UTF_8)));
//	}
	
	@Override
	public void channelRead(ChannelHandlerContext arg0, Object arg1) throws Exception {
		System.out.println((String)arg1);
	}
	
//	@Override
//	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
//		System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
//	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
