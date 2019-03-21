package com.test100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		//자료 입력
		map.put("g","Test");
		map.put("e","Sample");
		map.put("a","Sub");
		map.put("f","Hello");
		
		//자료 출력
		System.out.println(map.get("g"));
		System.out.println(map.get("f"));
		
		//순차적 접근
		Set<String> keys = map.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			String key = it.next();
			System.out.printf("%s %s %n",key,map.get(key));
		}
		
		//자료 삭제
		System.out.println(map.size());
		map.remove("g");
		System.out.println(map.size());
		
		//자료 수정
		System.out.println(map.get("e"));
		map.replace("e", "!@#");
		System.out.println(map.get("e"));
		
		//정렬 출력
		//Map -> keySet -> Set -> List -> 정렬
		Set<String> keys2 = map.keySet();
		List<String> keys3 = new ArrayList(keys2);
		Collections.sort(keys3);
		for(String key : keys3) {
			System.out.printf("%s %s%n",key, map.get(key));
		}
		
	}

}
