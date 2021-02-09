package com.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.reflect.vo.ReflectTest;

public class ReflectEx3 {
	
	static final String CONFIG_PATH = "config.properties";
	static final String PROP_CLASS = "reflectTest.class";
	static final String PROP_USER = "reflectTest.user";
	static final String PROP_PERIOD = "reflectTest.period";
	static final String PROP_NUMBER = "reflectTest.number";
	
	
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		Properties props = new Properties();
		
		try {
			File propFile = new File(ClassLoader.getSystemClassLoader().getResource(CONFIG_PATH).getFile());
			fis = new FileInputStream(propFile);
			
			props.load(fis);
			
			String className = props.getProperty(PROP_CLASS);
			if(className == null || className.length() == 0) throw new IllegalArgumentException("'" + PROP_CLASS + "' not assigened");
			
			Class c = Class.forName(className);
			
			String user = props.getProperty(PROP_USER);
			if(user == null || user.length() == 0) throw new IllegalArgumentException("'" + PROP_USER + "' not assigened");
			
			String period = props.getProperty(PROP_PERIOD);
			if(period == null || period.length() == 0) throw new IllegalArgumentException("'" + PROP_PERIOD + "' not assigened");
			
			Integer number = Integer.parseInt(props.getProperty(PROP_NUMBER));
			if(number == null) throw new IllegalArgumentException("'" + PROP_NUMBER + "' not assigened");
			
			ReflectTest h1 = (ReflectTest)c.newInstance();
			ReflectTest h2 = (ReflectTest)(c.getConstructor(new Class[]{String.class}).newInstance(new Object[]{user}));
			ReflectTest h3 = (ReflectTest)(c.getConstructor(new Class[] {int.class}).newInstance(new Object[] {number}));
			
			String g = h1.getString();
			System.out.println(g);
			
			g = h1.getString(period);
			System.out.println(g);
			
			g = h2.getString();
			System.out.println(g);
			
			g = h2.getString(period);
			System.out.println(g);
			
			int x = h3.getInteger(3);
			System.out.println(x);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
