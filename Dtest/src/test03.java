import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.sql.DataSource;

import com.datasource.test.DataSourceLoader;

import utils.ClassUtil;
import utils.IOUtil;

public class test03 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		File file = new File(ClassUtil.getBaseClassLoader().getResource("datasource.properties").getFile());
		
		try {
			DataSourceLoader.loadDataSource(file);
			DataSource ds = DataSourceLoader.getDataSource();
			conn = ds.getConnection();
			
			System.out.println("datasource" + ds + "connection :"+conn);
			
			conn.setAutoCommit(false);
			
			String designName = "1010";
			String sql = "insert into FILE_(ID,LOB_DATA) VALUES(?,?)";
			pstmt = conn.prepareStatement(sql);
			
			String value = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n <m:diagram xmlns:m=\"http://megaengine.megatus.com/diagram\" cached=\"null\" isTransacted=\"No\" lockKey=\"null\" monitoring=\"null\" multithreadLimit=\"unlimited\" name=\""+designName+"\" path=\"com.megatus.megaengine.console.mediate.view.MediateDiagram\" serviceHost=\"${protocol}://${address}\" servicePort=\"${port}\" sid=\"32DECEBF-F2F0-47A2-A848-4B6BCCC5D4D4\"><m:model path=\"com.megatus.megaengine.tool.model.Diagram\"><m:styles><m:style name=\"left\" value=\"0\"></m:style><m:style name=\"top\" value=\"0\"></m:style><m:style name=\"width\" value=\"1759\"></m:style><m:style name=\"height\" value=\"826\"></m:style></m:styles></m:model><m:node name=\"Receive1\" path=\"com.megatus.megaengine.console.mediate.view.event.Receive\" sid=\"1A07F25A-29FF-4261-8853-DBED3FFAF9DC\"><m:model path=\"com.megatus.megaengine.tool.model.Node\"><m:styles><m:style name=\"left\" value=\"487\"></m:style><m:style name=\"top\" value=\"290\"></m:style><m:style name=\"width\" value=\"60.5\"></m:style><m:style name=\"height\" value=\"60.5\"></m:style></m:styles></m:model></m:node></m:diagram>";
			String firUid = file_Insert(pstmt, conn, value);//두번째
			
			value = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n <m:diagram xmlns:m=\"http://megaengine.megatus.com/diagram\" name=\""+designName+"\" path=\"com.megatus.megaengine.console.mediate.view.MediateDiagram\"><m:model path=\"com.megatus.megaengine.tool.model.Diagram\"></m:model></m:diagram>";
					
			String secUid = file_Insert(pstmt, conn, value);//첫번째 디자인정의칸
			
			stmt = conn.createStatement();
			
			String gesignTreeId = "17051aaa-0cc8-4531-8310-bc5a96092aea";
			sql = "select LOB_DATA FROM FILE_ WHERE ID='"+gesignTreeId+"'";
			rs = stmt.executeQuery(sql);
			rs.next();
			String LOB_DATA = rs.getString("LOB_DATA");
			StringBuffer sb = new StringBuffer(LOB_DATA);
			sb.insert(sb.indexOf("</x:directory>"), "<x:diagram action=\"A\" id=\""+firUid+"\" name=\""+designName+".dgm\" permission=\"\" preAction=\"A\" userId=\""+secUid+"\"/>");
			System.out.println(sb.toString());
			
			sql = "update FILE_ set LOB_DATA='"+sb.toString()+"' WHERE ID = '"+gesignTreeId+"'";
			
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			conn.commit();
			
		} catch (SQLException e) {
			if(conn!=null) try{conn.rollback(); System.out.println("rollback success");}catch(SQLException sqle){}
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			IOUtil.close(rs);
			IOUtil.close(stmt);
			IOUtil.close(pstmt);
			IOUtil.close(conn);
		}
	}
	public static String file_Insert(PreparedStatement pstmt, Connection conn, String value) {
		String uid = String.valueOf(UUID.randomUUID());
		
		try {
			pstmt.setString(1, uid);
			pstmt.setString(2, value);
			
			int count = 0;
			count = pstmt.executeUpdate();
			pstmt.clearParameters();
			
			System.out.println("SuccessCount : " + count);
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uid;
	}
}
