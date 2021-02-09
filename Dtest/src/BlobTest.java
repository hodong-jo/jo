import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import database.DBCPFactory;
import utils.ClassUtil;
import utils.IOUtil;

public class BlobTest {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			
			String sql = "UPDATE test SET picture = ? WHERE id = '1'";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setBytes(1, readFile("D:\\ĸó.PNG"));
			
			pstmt.executeUpdate();
			System.out.println("Stored the file in the BLOB column.");
			
			readPicture();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtil.close(pstmt);
			IOUtil.close(conn);
		}
		
	}
	
	
	private static byte[] readFile(String file) {
		ByteArrayOutputStream bos = null;
		
		try {
			File f = new File(file);
			FileInputStream fis = new FileInputStream(f);
			byte[] buffer = new byte[1024];
			bos = new ByteArrayOutputStream();
			for (int len; (len = fis.read(buffer)) != -1;) {
				bos.write(buffer, 0, len);
			}
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return bos != null ? bos.toByteArray() : null;
	}
	
	
	private static void readPicture() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		FileOutputStream fos = null;
		
		try {
			String sql = "SELECT picture FROM TEST WHERE id='1'";
			
			conn = connect();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			File file = new File("D:\\picture.jpg");
			fos = new FileOutputStream(file);
			
			System.out.println("Writing BLOB to file " + file.getAbsolutePath());
			while (rs.next()) {
				InputStream input = rs.getBinaryStream("picture");
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					fos.write(buffer);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtil.close(fos);
			IOUtil.close(rs);
			IOUtil.close(stmt);
			IOUtil.close(conn);
		}
	}
	
	private static Connection connect() {
		Connection conn = null;
		File file = new File(ClassUtil.getBaseClassLoader().getResource("datasource.properties").getFile());
		
		try {
			conn = DBCPFactory.getInstance(file).getConnect();
			
			System.out.println("connection :"+conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
