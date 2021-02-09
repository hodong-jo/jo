package com.datasource.test.test;

import java.io.File;
import java.sql.Connection;

import javax.sql.DataSource;

import com.datasource.test.DataSourceLoader;

import utils.ClassUtil;

public class DBCPTest {

	public static void main(String[] args) {
		
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
