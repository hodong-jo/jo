package socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import test3.DBCPTest;
import utils.IOUtil;

public class DongServer {
	static Logger logger = Logger.getLogger(SocketServer.class);
	static AtomicInteger SEQ = new AtomicInteger();

	public static void main(String[] args) {
		int port = 9000;
		ServerSocket ss = null;
		Connection conn = null;
		try{
			InetSocketAddress endpoint = new InetSocketAddress(port);
			ss = new ServerSocket();
			ss.bind(endpoint);
			logger.info("Socket server listen : " + port);
			
			while(true){
				Socket socket = ss.accept();
				InputStream in = null;
				OutputStream out = null;
				int seq = SEQ.incrementAndGet();
				try{
					in = socket.getInputStream();
					out = socket.getOutputStream();
					
					byte[] header = new byte[5];
					in.read(header);
					int length = Integer.parseInt(new String(header).substring(0,4));
//					int check = Integer.parseInt(new String(header).substring(4,5));
					
					byte[] recvData = new byte[length];
					in.read(recvData);
					String dongName = new String(recvData);
					
					logger.info("Recv[" + seq + "] : [ Recv success ]");
					
					long start = System.currentTimeMillis();
					dbSelect(conn, dongName, out);
					long end = System.currentTimeMillis();
					
					logger.info((end-start)/1000.0+"√ ");
					logger.info("Send[" + seq + "] : [ Send success ]");
					
				}catch(Exception ex){
					logger.error("[#ERROR][" + seq + "]", ex);
				}finally{
					IOUtil.close(socket);
				}
			
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if( conn != null) try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
			IOUtil.close(ss);
		}
	}
	
	private static void dbSelect(Connection conn, String dongName, OutputStream out) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			DBCPTest dbcpTest = new DBCPTest();
			conn = dbcpTest.getInstance().getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM ZIPCODE WHERE dong LIKE ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, dongName+"%");
			
			String check = "0";
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				StringJoiner sj = new StringJoiner(",");
				sj.add(Integer.toString(rs.getInt(1)));
				sj.add(rs.getString("ZIPCODE"));
				sj.add(rs.getString("SIDO"));
				sj.add(rs.getString("GUGUN"));
				sj.add(rs.getString("DONG"));
				sj.add(rs.getString("BUNJI"));
				
				if(rs.isLast()) {
					check = "1";
				}
				
				byte[] sendData = sj.toString().getBytes();
				int len = sendData.length;
				String lenStr = String.format("%04d", len);
				lenStr += check;
				out.write(lenStr.getBytes());
				out.write(sendData);
				out.flush();
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null)try {rs.close();}catch(SQLException e){e.printStackTrace();};
			if(pstmt != null)try {pstmt.close();}catch(SQLException e){e.printStackTrace();};
		}
		
	}
}
