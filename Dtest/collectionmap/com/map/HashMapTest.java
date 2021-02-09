package com.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class HashMapTest {

	public static void main(String[] args) {
		//HashMap�� �̿��ؼ� TreeMap����
		//Key�� Value�� ������ �̷���� ���������� , ��������X Ű�ߺ����X ���ߺ����O
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("��" , 1);
		map.put("��" , 2);
		map.put("��" , 4);
		map.put("��" , 3);
		
//		Iterator<String> iter1 = map.keySet().iterator();
//		while(iter1.hasNext()) {
//			String temp = iter1.next();
//			System.out.println(temp + " : " + map.get(temp));
//		}
		
		Map<String,Integer> sortedMap = new TreeMap<String,Integer>();
		sortedMap.putAll(map);
		
		Iterator<String> iter2 = sortedMap.keySet().iterator();
		while(iter2.hasNext()) {
			String temp = iter2.next();
			System.out.println(temp + " : " + sortedMap.get(temp));
		}
		
		
	}

}
