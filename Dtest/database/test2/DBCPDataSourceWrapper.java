package test2;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DBCPDataSourceWrapper implements DBCPDataSourceWrapperMBean{
	
	BasicDataSource ds;

	public DBCPDataSourceWrapper(DataSource ds) {
		this.ds = (BasicDataSource) ds;
	}
	
	@Override
	public int getInitialSize() {
		return ds.getInitialSize();
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return ds.getLoginTimeout();
	}

	@Override
	public int getMaxActive() {
		return ds.getMaxActive();
	}

	@Override
	public int getMaxIdle() {
		return ds.getMaxIdle();
	}

	@Override
	public long getMaxWait() {
		return ds.getMaxWait();
	}

	@Override
	public int getMinIdle() {
		return ds.getMinIdle();
	}

	@Override
	public int getNumActive() {
		return ds.getNumActive();
	}

	@Override
	public int getNumIdle() {
		return ds.getNumIdle();
	}

	@Override
	public int getNumTestsPerEvictionRun() {
		return ds.getNumTestsPerEvictionRun();
	}

	@Override
	public int getRemoveAbandonedTimeout() {
		return ds.getRemoveAbandonedTimeout();
	}

	@Override
	public long getTimeBetweenEvictionRunsMillis() {
		return ds.getTimeBetweenEvictionRunsMillis();
	}

	@Override
	public int getValidationQueryTimeout() {
		return ds.getValidationQueryTimeout();
	}
	
}
