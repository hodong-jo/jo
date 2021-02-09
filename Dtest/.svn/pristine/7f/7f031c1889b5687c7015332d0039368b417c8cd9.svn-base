package com.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectEx4 {
	
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
			if(number == null) throw new IllegalArgumentException("'" + PROP_PERIOD + "' not assigened");
			
			Object h1 = c.newInstance();
			Object h2 = (c.getConstructor(new Class[] {String.class}).newInstance(new Object[]{user}));
			Object h3 = (c.getConstructor(new Class[] {int.class}).newInstance(new Object[] {number}));
			
			Method m1 = c.getMethod("getString", null);
			Method m2 = c.getMethod("getString", new Class[] {String.class});
			Method m3 = c.getMethod("getInteger", new Class[] {int.class});
			
			String g = (String)m1.invoke(h1, null);
			System.out.println(g);
			
			g = (String)m2.invoke(h1, new Object[] {period});
			System.out.println(g);
			
			g = (String)m1.invoke(h2, null);
			System.out.println(g);
			
			g = (String)m2.invoke(h2, new Object[] {period});
			System.out.println(g);
			
			int x = (int)m3.invoke(h3, new Object[] {3});
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
