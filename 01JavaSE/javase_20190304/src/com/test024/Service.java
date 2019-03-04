package com.test024;

import java.util.Scanner;

public class Service {
	int count = 3;
	int idx = 0;
	Score[] s = new Score[count];
	
	public void menu01(Scanner sc) {
		if(count == idx) {
			System.out.println("더이상 입력불가능");
		}else {
			System.out.print("학번>");
			String stuNum = sc.nextLine();
			System.out.print("이름>");
			String name = sc.nextLine();
			System.out.print("과목1>");
			int sub1 = sc.nextInt();
			sc.nextLine();
			System.out.print("과목2>");
			int sub2 = sc.nextInt();
			sc.nextLine();
			System.out.print("과목3>");
			int sub3 = sc.nextInt();
			sc.nextLine();
			
			Score s1 = new Score();
			s1.setStuNum(stuNum);
			s1.setName(name);
			s1.setSub1(sub1);
			s1.setSub2(sub2);
			s1.setSub3(sub3);
			
			s[idx++] = s1;
		}
	}

	public void menu02(Scanner sc) {
		System.out.println("-------------------------------------");
		System.out.println("학번/이름/과목1/과목2/과목3/총점");
		for(int b=0; b<idx; ++b) {
			Score s_ = s[b];
			System.out.printf("%s / %s/ %d/ %d / %d / %d%n"
					, s_.getStuNum(), s_.getName(), s_.getSub1(), s_.getSub2(), s_.getSub3()
					, s_.getSub1()+s_.getSub2()+s_.getSub3());
		}
	}

}
