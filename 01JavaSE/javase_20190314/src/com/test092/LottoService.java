package com.test092;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LottoService {
	
	LottoDAO dao = new LottoDAO();
	//판매소 고유번호는 맴버변수 또는 상수
	public static final String LOTTONUM = "L001";
	
	//자동 번호 서비스
	//매수 입력
	public void menu1(Scanner sc) {
		System.out.print("매수>");
		int count = sc.nextInt();
		sc.nextLine();
		
		System.out.printf("판매소번호 %s%n", LOTTONUM);
		System.out.printf("판매날짜 %s%n", LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
		
		for(int a = 0; a < count; ++a) {
			//로또 번호 자동 생성
			List<Integer> lotto = this.auto();
			
			//저장소에 저장
			this.dao.add(new Lotto(LOTTONUM, lotto));
			
			//발행된 로또 번호 출력
			System.out.println(lotto.toString());
		}
		
	}
	
	private List<Integer> auto() {
		Random r = new Random();
		Set<Integer> set = new HashSet<Integer>();
		while(true) {
			int temp = r.nextInt(45) + 1;
			set.add(temp);
			if(set.size() >= 6) break;
		}
		
		List<Integer> list = new ArrayList<Integer>(set);
		Collections.sort(list);
		return list;
	}
	
	//수동 번호 서비스
	//1~45 인지 확인 절차 필요
	public void menu2(Scanner sc) {
		
		List<Integer> list = new ArrayList<Integer>();
		System.out.print("번호입력>");
		for(int a = 0; a < 6; ++a) {
			int num = sc.nextInt();
			list.add(num);
		}
		this.dao.add(new Lotto(LOTTONUM, list));
		System.out.println(list.toString());

	}
	
	//로또 번호 검색 서비스
	//당첨자(5, 13, 17, 29, 34, 39)
	//당첨번호 3개 숫자일치 = 5등
	public void menu3(Scanner sc) {
		List<Integer> temp = new ArrayList<Integer>();
		System.out.print("당첨번호(n1 n2 n3 n4 n5 n6)>");
		for (int a = 0; a < 6; ++a) {
			int b = sc.nextInt();
			temp.add(b);
		}
		
		System.out.print("당첨확인(1-5)>");
		int m = sc.nextInt();
		sc.nextLine();
		if(m == 5) {
			List<Lotto> list = this.dao.list(3, temp);
			for (Lotto l : list) {
				System.out.println(l.toString());
			}
			System.out.printf("총 %d건%n", list.size());
		}
	}
}
