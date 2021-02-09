import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.datasource.test.DataSourceLoader;

import utils.ClassUtil;
import utils.IOUtil;
import vo.Test;

public class DbAccess {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		File file = new File(ClassUtil.getBaseClassLoader().getResource("datasource.properties").getFile());
		
		try {
			DataSourceLoader.loadDataSource(file);
			DataSource ds = DataSourceLoader.getDataSource();
			conn = ds.getConnection();
			
			System.out.println("datasource" + ds + "connection :"+conn);
			
			conn.setAutoCommit(false);
			
			System.out.print("Äõ¸®¹®ÀÔ·Â : ");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			
			String sql = bufferedReader.readLine();
			String type = sql.substring(0, sql.indexOf(" "));
			
			stmt = conn.createStatement();
			
			switch (type) {
			case "select":
				rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				for(int i = 1; i <= columnCount; i ++) {
					System.out.print(rsmd.getColumnName(i) + "\t");
				}
				System.out.println();
				while (rs.next()) {
					for(int i = 1; i <= columnCount; i ++) {
						System.out.print(rs.getString(i) + "\t");
					}
					System.out.println();
				}
				break;
			case "insert":
				int count = 0;
				count = stmt.executeUpdate(sql);
				System.out.println("SuccessCount : " + count);
				conn.commit();
				break;
				
			default:
				break;
			}
			
			
		} catch (SQLException e) {
			if(conn!=null) try{conn.rollback(); System.out.println("rollback success");}catch(SQLException sqle){}
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			IOUtil.close(rs);
			IOUtil.close(stmt);
			IOUtil.close(conn);
		}
	}

}
