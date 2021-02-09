package com.map;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

public class HashtableTest {

	public static void main(String[] args) {
		Hashtable<String,Integer> h = new Hashtable<String, Integer>();
		h.put("a", 1);
		h.put("b", 2);
		h.put("c", 1);
		
		Enumeration<String> en0 = h.keys();
		while(en0.hasMoreElements()) {
			String key = en0.nextElement();
			//System.out.println(key);
		}
		
		Enumeration<Integer> en1 = h.elements();
		while(en1.hasMoreElements()) {
			int value = en1.nextElement();
//			System.out.println(value);
		}
		
		Enumeration/*<String>*/ en3 = h.keys();
		while(en3.hasMoreElements()) {
			String key = en3.nextElement().toString();
//			System.out.println(key + " : " + h.get(key));
		}
		
		Iterator<Integer> iter1 = h.values().iterator();
		while(iter1.hasNext()) {
			Integer temp = iter1.next();
//			System.out.println(temp);
		}
		
		Iterator<String> iter2 = h.keySet().iterator();
		while(iter2.hasNext()) {
			String temp = iter2.next();
//			System.out.println(temp);
//			System.out.println(temp + " : " + h.get(temp));
		}
		
		Iterator iter3 = h.keySet().iterator();
		while(iter3.hasNext()) {
			String temp = (String) iter3.next();
//			System.out.println(temp + " : " + h.get(temp));
		}
		
		Iterator iter4 = h.entrySet().iterator();
		while(iter4.hasNext()) {
			Entry temp = (Entry) iter4.next();
			System.out.println(temp.getKey() + " : " + temp.getValue());
		}

	}

}
