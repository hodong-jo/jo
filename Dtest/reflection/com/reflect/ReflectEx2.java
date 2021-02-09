package com.reflect;

import java.lang.reflect.Method;

public class ReflectEx2 {
	public static void main(String[] args) {
		
		try {
			
			Class c = Class.forName("com.reflect.vo.ReflectImpl");
			
			Object h1 = c.newInstance();
			Object h2 = (c.getConstructor(new Class[] {String.class}).newInstance(new Object[]{"Test"}));
			Object h3 = (c.getConstructor(new Class[] {int.class}).newInstance(new Object[] {1}));
			
			Method m1 = c.getMethod("getString", null);
			Method m2 = c.getMethod("getString", new Class[] {String.class});
			Method m3 = c.getMethod("getInteger", new Class[] {int.class});
			
			String g = (String)m1.invoke(h1, null);
			System.out.println(g);
			
			g = (String)m2.invoke(h1, new Object[] {"."});
			System.out.println(g);
			
			g = (String)m1.invoke(h2, null);
			System.out.println(g);
			
			g = (String)m2.invoke(h2, new Object[] {"."});
			System.out.println(g);
			
			int x = (int)m3.invoke(h3, new Object[] {3});
			System.out.println(x);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
