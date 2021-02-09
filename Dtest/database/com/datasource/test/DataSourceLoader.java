package com.datasource.test;

import java.io.File;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import utils.ClassUtil;
import utils.PropertiesUtil;

public class DataSourceLoader {
	
	public static String FACTORY_CLASSNAME = "dataSourceFactoryClassName";
	
	static Logger logger = Logger.getLogger(DataSourceLoader.class);
	static DataSource ds;
	
	public static void loadDataSource(File file) throws Exception{
		try {
			Properties config = PropertiesUtil.loadProperties(file);
			String factoryClassName = PropertiesUtil.getMandatoryProperty(FACTORY_CLASSNAME, config);
			DataSourceFactory factory = (DataSourceFactory)ClassUtil.newInstance(factoryClassName);
			ds = factory.createDataSource(config);
		} catch (Exception e) {
			logger.error("Failed to load datasource:", e);
			throw e;
		}
		
	}
	public static DataSource getDataSource(){
		return ds;
	}

}
