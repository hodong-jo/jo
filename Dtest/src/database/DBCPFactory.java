package database;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPFactory {
	private static DBCPFactory datasource;
	DataSource ds;
	
	 private DBCPFactory(File file) throws Exception{
		 FileInputStream fis = new FileInputStream(file);
         Properties properties = new Properties();
         properties.load(fis);
         
         ds = BasicDataSourceFactory.createDataSource(properties);

      }
      
      public static DBCPFactory getInstance(File file) throws Exception{
         if(datasource == null) {
            datasource = new DBCPFactory(file);
            return datasource;
         }else {
            return datasource;
         }
      }
      
      public Connection getConnect() throws SQLException{
         return this.ds.getConnection();
      }
      
}
