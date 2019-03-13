package com.test080;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		//------------------선언----------------------------------------
		//저장소 크기를 명시적으로 지정하지 않아도 된다
		//자동 확장
		//List 인터페이스 구현 클래스 ArrayList 클래스(제네릭 포함)
		//제네릭 -  컬렉션에 저장하는 자료형을 제한하는 기능(참조자료형만)
		List<Integer> list = new ArrayList<Integer>();
		
		//자료 저장 -> 특정 자료형만 저장 가능
		//제네릭을 쓰지 않을 경우 여러가지 자료형 저장 가능 단, 추천하지 않음(정렬 등의 문제)
		//Auto Boxing(기본형->참조형), 다형성(참조형->Object)
		list.add(50);     // int    -> Integer(참조형) -> Object
		list.add(12); // double -> Double(참조형) -> Object 
		list.add(20); //           String(참조형) -> Object
		list.add(new Integer(10));
		
		//------------------출력----------------------------------------
		//자료 확인
		System.out.println(list.get(0)); // 10
		System.out.println(list.get(1)); // 12
		System.out.println(list.get(2)); // 20
		System.out.println(list.get(3)); // 50
		
		//순차적 접근을 통한 자료 확인
		//방법1 - 인덱스 제공할 경우
		//적용(List)
		for(int a=0; a<list.size(); ++a) {
			System.out.println(list.get(a));
		}
		
		//방법2 - 인덱스 제공하지 않을 경우
		//적용(List, Set, Map)
		//제네릭을 쓰지 않을 경우 자료가 어떤 자료형인지 모르기 떄문에 모든 클래스의 조상인 Object 클래스 사용
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) {
			Integer o = it.next();
			System.out.println(o);
		}
		
		//방법3 - 인덱스 제공하지 않을 경우
		//적용(List, Set, Map)
		//제네릭을 쓰지 않을 경우 자료가 어떤 자료형인지 모르기 떄문에 모든 클래스의 조상인 Object 클래스 사용
		for(Integer a : list) {
			System.out.println(a);
		}
		
		//------------------삭제----------------------------------------
		//자료 삭제
		//->삭제가 진행된 후 자료 이동 발생
		//->인덱스 변동 발생
		//-> 처음부터가 아닌 끝에서부터 지울수 있도록 하기
		System.out.println(list.get(1)); // 12
		list.remove(0);
		System.out.println(list.get(1)); // 20
		
		//------------------수정----------------------------------------
		//자료 수정
		System.out.println(list.get(1)); // 20
		list.set(1, 50);
		System.out.println(list.get(1)); // 50
		
		//------------------정렬----------------------------------------
		//자료 정렬
		//기본 자료형이라면 정렬 기준 제시 없어도 가능
		//사용자정의 자료형을 정렬하려면 Comparable 또는 Comparator 사용
		System.out.println(list.toString());
		Collections.sort(list); // 기본적으로 Comparable 지원
		Collections.sort(list, new Comparator<Integer>() {
			public int compare(Integer i1, Integer i2) {
				//사용자 정의 정렬
				return 0;
			}
		});
		System.out.println(list.toString());
	}

}
