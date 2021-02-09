package datasource;

import java.lang.management.ManagementFactory;
import java.util.Properties;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPDataSourceFactory implements DataSourceFactory{

	@Override
	public DataSource createDataSource(Properties config) throws Exception {
		DBCPDataSourceWrapper ds = new DBCPDataSourceWrapper((BasicDataSource)BasicDataSourceFactory.createDataSource(config));
		ObjectName name = ObjectName.getInstance("DataSource:type=DBCPDataSource");
		
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		server.registerMBean(ds, name);
		return ds;
	}

}
