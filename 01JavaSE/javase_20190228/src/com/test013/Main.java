package com.test013;

public class Main {

	public static void main(String[] args) {
		int count = 1;
		Score[] s = new Score[count];
		java.util.Scanner sc = new java.util.Scanner(System.in);
		
		for(int a=0; a<count; ++a) {
			System.out.print("학번>");
			String stuNum = sc.nextLine();
			System.out.print("과목1>");
			int sub1 = sc.nextInt();
			sc.nextLine();
			System.out.print("과목2>");
			int sub2 = sc.nextInt();
			sc.nextLine();
			System.out.print("과목3>");
			int sub3 = sc.nextInt();
			sc.nextLine();
			System.out.println("입력완료!");
			
			s[a] = new Score();
			s[a].setStuNum(stuNum);
			s[a].setSub1(sub1);
			s[a].setSub2(sub2);
			s[a].setSub3(sub3);
		}
		
		System.out.printf("%s/%s/%s/%s/%s%n","학번","과목1","과목2","과목3","총점");
		for(int a=0; a<count; ++a) {
			int total = s[a].getSub1()+s[a].getSub2()+s[a].getSub3();
			System.out.printf("%s   %3d   %3d   %3d   %3d%n",
					s[a].getStuNum(),s[a].getSub1(),s[a].getSub2(),s[a].getSub3(),total);
		}
	sc.close();
	}
}
