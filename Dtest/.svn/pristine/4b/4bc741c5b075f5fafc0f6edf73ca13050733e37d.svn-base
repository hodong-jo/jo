package test3;

import java.io.FileInputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPTest {
	DataSource ds;
	
	public DBCPTest() throws Exception{
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(ClassLoader.getSystemClassLoader().getResource("test.properties").getFile());
		properties.load(fis);
		
		ds = BasicDataSourceFactory.createDataSource(properties);
		
	}
	
	public DataSource getInstance() {
		return ds;
	}
}
