package datasource;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import utils.ClassUtil;

public class DBCPTest {

	public DBCPTest(){
		File configFile = new File(ClassUtil.getBaseClassLoader().getResource("datasource.properties").getFile());
		
		try {
			DataSourceLoader.loadDataSource(configFile);
			
			DataSource ds = DataSourceLoader.getDataSource();
			Connection conn = ds.getConnection();
			System.out.println(conn);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
