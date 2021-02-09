package datasource;

import java.util.Properties;

import javax.sql.DataSource;

public interface DataSourceFactory {
	DataSource createDataSource(Properties config) throws Exception;
}
