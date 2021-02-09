package com.reflect;

import java.lang.reflect.Method;

import com.reflect.vo.ReflectTest;

public class ReflectExTest {

	public static void main(String[] args) {
		try {
			Class c = Class.forName("com.reflect.vo.ReflectImpl");
			
			ReflectTest h1 = (ReflectTest) c.newInstance();
			
			Method[] m = c.getDeclaredMethods();
			for(int i = 0; i < m.length; i++) {
				System.out.println(m[i].toString());
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
				
	}

}