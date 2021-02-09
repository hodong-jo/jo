package com.reflect;

import com.reflect.vo.ReflectTest;

public class ReflectEx1 {
	public static void main(String[] args) {
		try {
			Class c = Class.forName("com.reflect.vo.ReflectImpl");
            
			ReflectTest h1 = (ReflectTest)c.newInstance();
			ReflectTest h2 = (ReflectTest)(c.getConstructor(new Class[]{String.class}).newInstance(new Object[]{"Test"}));
			ReflectTest h3 = (ReflectTest)(c.getConstructor(new Class[] {int.class}).newInstance(new Object[] {1}));
			
			String g = h1.getString();
			System.out.println(g);
			
			g = h1.getString(".");
			System.out.println(g);
			
			g = h2.getString();
			System.out.println(g);
			
			g = h2.getString(".");
			System.out.println(g);
			
			int x = h3.getInteger(3);
			System.out.println(x);
			
			
		} catch (Throwable e) {
			// TODO: handle exception
			System.err.print(e);
		}
	}
}
