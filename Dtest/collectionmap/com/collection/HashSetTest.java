package com.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class HashSetTest {

	public static void main(String[] args) {
		//HashSet�� �̿��� TreeSet���� 
		//��������X �ߺ����X
		Set<String> set = new HashSet<String>();
		set.add("�β���");
		set.add("ĥ����");
		set.add("������");
		set.add("��ѱ�");
		
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
