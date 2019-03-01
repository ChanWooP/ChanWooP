package com.test012;

public class Main {

	public static void main(String[] args) {
		Score[] s = new Score[3];
		java.util.Scanner sc = new java.util.Scanner(System.in);
		
		for(int a=0; a<s.length; ++a) {
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
		
		for(int a=0; a<s.length; ++a) {
			System.out.printf("%5s %3d %3d %3d%n",s[a].getStuNum(),s[a].getsub1(),s[a].getsub2(),s[a].getsub3());
		}
	sc.close();
	}
}
