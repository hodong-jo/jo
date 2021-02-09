package test2;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DataSourceTest {

    private static DataSourceTest datasource;
    DataSource ds;

    private DataSourceTest() throws IOException {
    	FileInputStream fis = new FileInputStream(ClassLoader.getSystemClassLoader().getResource("test.properties").getFile());
    	Properties p = new Properties();
    	p.load(fis);
    	MBeanServer server = ManagementFactory.getPlatformMBeanServer();
    	
    	try {
			ds = BasicDataSourceFactory.createDataSource(p);
			DBCPDataSourceWrapper dsw = new DBCPDataSourceWrapper(ds);
			ObjectName name = ObjectName.getInstance("DataSource:type=DBCPDataSource");
			server.registerMBean(dsw, name);
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

    public static DataSourceTest getInstance() throws IOException {
        if (datasource == null) {
            datasource = new DataSourceTest();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }

}
