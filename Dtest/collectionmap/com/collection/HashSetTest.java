package com.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class HashSetTest {

	public static void main(String[] args) {
		//HashSet을 이용한 TreeSet생성 
		//순서유지X 중복허용X
		Set<String> set = new HashSet<String>();
		set.add("두꺼비");
		set.add("칠면조");
		set.add("바위게");
		set.add("비둘기");
		
//		Iterator<String> iter = set.iterator();
//		while(iter.hasNext()) {
//			String temp = iter.next();
//			System.out.println(temp);
//		}
		
//		TreeSet<String> ts = new TreeSet<String>();
//		ts.addAll(set);
//		
//		Iterator<String> iter = ts.iterator();
//		while(iter.hasNext()) {
//			String temp = iter.next();
//			System.out.println(temp);
//		}
//		System.out.println(ts.first());
//		System.out.println(ts.last());

	}

}
