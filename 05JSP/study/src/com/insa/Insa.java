package com.insa;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Insa {
	private String name;
	private String birth;
	private String phone;
	private int basic;
	private int pay;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getBasic() {
		return basic;
	}

	public void setBasic(int basic) {
		this.basic = basic;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public String result() {
		String result = "";

		result += String.format("<table border='1'>");
		result += String.format("<tr><td>이름</td><td>%s</td></tr>", this.name);
		result += String.format("<tr><td>생년월일</td><td>%s</td></tr>", this.birth);
		result += String.format("<tr><td>띠</td><td>%s", birthYears());
		result += String.format("<tr><td>나이</td><td>%s", age());
		result += String.format("<tr><td>전화번호</td><td>%s", this.phone);
		result += String.format("<tr><td>기본급</td><td>₩%,d", this.basic);
		result += String.format("<tr><td>수당</td><td>₩%,d", this.pay);
		result += String.format("<tr><td>세금</td><td>₩%,d", tax());
		result += String.format("<tr><td>실급여</td><td>₩%,d", this.basic + this.pay - tax());
		result += String.format("</table>");

		return result;
	}

	private String birthYears() {
		List<String> yearList = Arrays.asList("쥐", "소", "호랑이", "토끼", "용", "뱀", "말", "양", "원숭이", "닭", "개", "돼지");
		int birthYear = Integer.parseInt(this.birth.substring(0, 4));

		return yearList.get((birthYear + 8) % 12);
	}

	private int age() {
		int birthYear = Integer.parseInt(this.birth.substring(0, 4));
		int birthMonth = Integer.parseInt(this.birth.substring(5, 7));
		int birthDay = Integer.parseInt(this.birth.substring(8, 10));

		Calendar current = Calendar.getInstance();
		int currentYear = current.get(Calendar.YEAR);
		int currentMonth = current.get(Calendar.MONTH) + 1;
		int currentDay = current.get(Calendar.DAY_OF_MONTH);

		int age = currentYear - birthYear;

		if (birthMonth * 100 + birthDay > currentMonth * 100 + currentDay)
			age--;

		return age;

	}

	private int tax() {
		double tax = 0;

		if ((this.basic + this.pay) >= 3000000) {
			tax = (this.basic + this.pay) * 0.03;
		} else if ((this.basic + this.pay) >= 2000000) {
			tax = (this.basic + this.pay) * 0.02;
		}

		return (int) tax;
	}
}
