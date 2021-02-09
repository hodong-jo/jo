package socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import test3.DBCPTest;
import utils.IOUtil;

public class DbTestServer {
	static Logger logger = Logger.getLogger(SocketServer.class);
	static AtomicInteger SEQ = new AtomicInteger();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 9000;
		ServerSocket ss = null;
		BufferedReader bfr = null;
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
					bfr = new BufferedReader(new InputStreamReader(in));
					
					int result = 0;
					result = dbDelete(bfr,conn);
					logger.info("Recv[" + seq + "] : [" + result +"개의 행이 수정되었습니다." + "]");
					
					long start = System.currentTimeMillis();
					result = dbInsert(bfr,conn);
					long end = System.currentTimeMillis();
					
				    logger.info((end-start)/1000.0+"초");
					logger.info("Recv[" + seq + "] : [" + result +"개의 행이 추가되었습니다." + "]");
					
					String sendDatastr = "success";
					out.write(sendDatastr.getBytes());
					out.flush();
					logger.info("Send[" + seq + "] : [" + sendDatastr + "]");
					
				}catch(Exception ex){
					logger.error("[#ERROR][" + seq + "]", ex);
				}finally{
					if(conn != null)try {conn.close();}catch(SQLException e){e.printStackTrace();};
					IOUtil.close(bfr);
					IOUtil.close(socket);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			IOUtil.close(ss);
		}
	}
	
	private static int dbInsert(BufferedReader bfr, Connection conn) {
		
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			DBCPTest dbcpTest = new DBCPTest();
			conn = dbcpTest.getInstance().getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("INSERT INTO ZIPCODE(seq,zipcode,sido,gugun,dong,bunji) VALUES (?,?,?,?,?,?)");
			
			String line;
			String[] zc;
			
			String header = bfr.readLine();
			
			while((line = bfr.readLine()) != null) {
				if(line.equals("end")) {
					break;
				}
				int rowCount = 6;
				zc = line.split("," , rowCount);
				pstmt.setInt(1, Integer.parseInt(zc[0]));
				for(int i = 2; i <= rowCount; i++) {
					pstmt.setString(i, zc[i-1]);
				}
				count++;
				pstmt.addBatch();
				if( (count % 3000) == 0) {
					pstmt.executeBatch();
					pstmt.clearBatch();
					conn.commit();
				}
				
			}
			pstmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(true); //롤백처리하기
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null)try {pstmt.close();}catch(SQLException e){e.printStackTrace();};
		}
		return count;
		
	}
	
	
	
	private static int dbDelete(BufferedReader bfr, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			DBCPTest dbcpTest = new DBCPTest();
			conn = dbcpTest.getInstance().getConnection();
			String tableName = "ZIPCODE";
			String sql = "DELETE FROM " + tableName;
			pstmt = conn.prepareStatement(sql);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null)try {pstmt.close();}catch(SQLException e){e.printStackTrace();};
		}
		return result;
		
	}
}

