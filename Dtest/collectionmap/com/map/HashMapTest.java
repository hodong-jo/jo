package com.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class HashMapTest {

	public static void main(String[] args) {
		//HashMap을 이용해서 TreeMap생성
		//Key와 Value의 쌍으로 이루어진 데이터집합 , 순서유지X 키중복허용X 값중복허용O
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("가" , 1);
		map.put("나" , 2);
		map.put("라" , 4);
		map.put("다" , 3);
		
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
