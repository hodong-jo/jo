package test3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCPTestMain {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		try {
			DBCPTest dbcpTest = new DBCPTest();
			conn = dbcpTest.getInstance().getConnection();
			stat = conn.createStatement();
			res = stat.executeQuery("SELECT * FROM TEST");
			while(res.next()) {
				System.out.println(res.getString(1) + " : " + res.getString("name")); //getString 0이아닌 1부터시작 순차적접근만가능
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(res != null)try {res.close();}catch(SQLException e){e.printStackTrace();};
			if(stat != null)try {stat.close();}catch(SQLException e){e.printStackTrace();};
			if(conn != null)try {conn.close();}catch(SQLException e){e.printStackTrace();};
		}
	}

}
