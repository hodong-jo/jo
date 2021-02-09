package netty;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;

import javax.sql.DataSource;

import datasource.DBCPTest;
import datasource.DataSourceLoader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import utils.ClassUtil;

@Sharable // ChannelHandler를 여러 채널 간에 안전하게 공유할 수 있음을 나타내
public class ChatServerHandler extends ChannelInboundHandlerAdapter{
	
	private static DataSource DS = DataSourceLoader.getDataSource();
	private static Connection CONN;
	private static HashMap<String, String> TEST = new HashMap<String, String>();
	private static final ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handlerAdded of Server");
		Channel incoming = ctx.channel();
		for (Channel channel : CHANNEL_GROUP) {
			channel.write(incoming.remoteAddress() + "has joined\n");
		}
		CHANNEL_GROUP.add(incoming);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Statement stmt;
		ResultSet rs;
		
		CONN = DS.getConnection();
		System.out.println(CONN);
		
		stmt = CONN.createStatement();
		rs = stmt.executeQuery("SELECT * FROM test.test");
		
		while(rs.next()) {
			TEST.put(rs.getString("keyword"), rs.getString("value"));
		}
		
		System.out.println("ChannelActive");
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		CONN.close();
		System.out.println("handlerRemoved of Server");
		Channel incoming = ctx.channel();
		for (Channel channel : CHANNEL_GROUP) {
			channel.write(incoming.remoteAddress() + "has left\n");
		}
		CHANNEL_GROUP.remove(incoming);
		
	}
	
//	@Override
//	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//		System.out.println("ChannelInactive");
//	}
	
	// 메시지가 들어올 때마다 호출
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String message = (String)msg;
		System.out.println("Server received: " + message);
		
		Iterator<String> keys = TEST.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			
			if (message.contains(key)) {
				System.out.println("KEY : " + key + " VALUE : " + TEST.get(key));
				break;
			}
		}
//		pstmt = CONN.prepareStatement("SELECT value FROM test.test WHERE KEYWORD=?");
		
		Channel incoming = ctx.channel();
		for (Channel channel : CHANNEL_GROUP) {
			if (channel != incoming) {
				channel.writeAndFlush("[" + incoming.remoteAddress() + "] : " + message + "\n");
			}
		}
		if("bye".equals(message.toLowerCase())){
			ctx.close();
		}
		
//		ctx.write(msg);
	}
	
	// channelRead()의 마지막 호출에서 현재 일괄처리의 마지막 메시지를 처리했음을 핸들러에게 통보
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// 대기중인 메시지를 원격 피어로 플러시하고 채널을 닫음
//		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 예외 스택 추적을 출력
		cause.printStackTrace();
		ctx.close();
	}
	
}
