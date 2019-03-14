package com.test090;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		//선언
		Set<Integer> set = new HashSet<Integer>();
		
		//추가
		set.add(30); // int -> Integer (내부적으로 오토박싱이 일어남)
		set.add(10);
		set.add(new Integer(20)); // 오토박싱이 없으면 이런식으로 선언해야함
		
		//중복불가
		//중복체크를 알아서 해줌(중복이면 false 아니면 true)
		System.out.println(set.add(10)); // false
		
		//저장소에 저장된 자료 갯수
		//중복 데이터는 저장이 되지 않음
		System.out.println(set.size()); // 3
		
		//순차적 접근
		//인덱스를 지정한 특정 번쨰 요소 접근 불가
		//방법1
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()) {
			Integer o = it.next();
			System.out.println(o);
		}
		
		//방법2
		for(Integer a : set) {
			System.out.println(a);
		}
		
		//자료 삭제
		//비교 가능한 경우 삭제 가능
		//자료를 비교하여 삭제 
		//사용자 정의 자료형인 경우 자료 비교 가능한 액션 추가
		System.out.println(set.remove(20)); // true
		System.out.println(set.size()); // 1
		
		//set -> list -> 정렬기능
		List<Integer> list = new ArrayList<Integer>(set);
		System.out.println(list.toString());
		Collections.sort(list);
		System.out.println(list.toString());
	}	

}
