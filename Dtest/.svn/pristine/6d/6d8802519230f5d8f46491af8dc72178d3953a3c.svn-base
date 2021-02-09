package com.datasource.test;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import utils.ClassUtil;




public class JDBCTest {

	public static void main(String[] args){
		File file = new File(ClassUtil.getBaseClassLoader().getResource("datasource.properties").getFile());
		
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			
			DataSourceLoader.loadDataSource(file);
			DataSource ds = DataSourceLoader.getDataSource();
			for(int i = 0; i < 15; i++) {
			
			conn = ds.getConnection();
			System.out.println("datasource" + ds + "connection :"+conn);
			
			Thread.sleep(5000);
//			String driverClassName = "oracle.jdbc.driver.OracleDriver";
//			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//			String user = "hodong2";
//			String password = "ghehd2";
			String sql;
			
//			Class.forName(driverClassName); // JDBC 드라이버 로드
			
//			conn = DriverManager.getConnection(url,user,password); // 데이터베이스 연결
			
		
//			System.out.println("Driver:" + driverClassName + ", Connection:" + conn);
			
//			conn.setAutoCommit(false); // 하나의 처리를위해 오토커밋을 false로해주어 전체작업이 수행되어야만 commit이 되게한다
//			sql = "insert into TEST(id,name) values(?,?)";
//			pstmt = conn.prepareStatement(sql); //preparestatment 생성
//			pstmt.setString(1,"ranghyo");
//			pstmt.setString(2, "량효");
//			pstmt.execute();
			
//			String sql = "select id from TEST WHERE id='hodong'";
//			sql = "insert into TEST(id,name) values(?,?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1,"ranghyo");
//			pstmt.setString(2, "량효");
//			pstmt.setString(3, "asd");
//			pstmt.execute();
//			stmt.execute(sql);
			
//			sql = "select id from TEST WHERE id=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "asd");
//			pstmt.executeQuery();
			
			PreparedStatement pstmt2;
			sql = "update TEST set id='as' where id=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setString(1,"ase");
			pstmt2.execute();
			
			PreparedStatement pstmt3;
			sql = "delete from TEST where id='asd'";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.execute();
			
//			ResultSet rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				String id = rs.getString("id");
//				System.out.println(id);
//			}
			conn.commit();
			conn.setAutoCommit(true);
//			conn.close();
			}
					
		} catch (Exception e) {
			if(conn!=null) try{conn.rollback();}catch(SQLException sqle){}
			System.out.println("rollback success");
		}
		
		finally {
//			try {
//				conn.setAutoCommit(true);
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			if(stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace();}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace();}
//			if(conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace();}
			
			
		}
		

	}

}
