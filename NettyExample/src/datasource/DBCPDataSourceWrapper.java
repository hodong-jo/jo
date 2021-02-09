package datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DBCPDataSourceWrapper implements DataSource, DBCPDataSourceWrapperMBean{
	
	private BasicDataSource ds;

	public DBCPDataSourceWrapper(BasicDataSource ds) {
		this.ds = ds;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return ds.getLogWriter();
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		ds.setLogWriter(out);
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		ds.setLoginTimeout(seconds);
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return ds.getLoginTimeout();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return ds.getParentLogger();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return ds.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return ds.isWrapperFor(iface);
	}

	@Override
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return ds.getConnection(username, password);
	}

	@Override
	public int getInitialSize() {
		return ds.getInitialSize();
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
