package datasource;

import java.sql.SQLException;
import java.util.Collection;

public interface DBCPDataSourceWrapperMBean {
	int getInitialSize();
	int getLoginTimeout() throws SQLException;
	int getMaxActive();
	int getMaxIdle();
	long getMaxWait();
	int getMinIdle();
	int getNumActive();
	int getNumIdle();
	int getNumTestsPerEvictionRun();
	int getRemoveAbandonedTimeout();
	long getTimeBetweenEvictionRunsMillis();
	int getValidationQueryTimeout();
}
