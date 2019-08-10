package com.insa;

import java.text.NumberFormat;
import java.util.Calendar;

public class InsaVO {
	private String name;
	private String birth;
	private String tel;
	private int basic;
	private int sudang;
	
	private int age;
	private String tti;
	private int tax;
	private int pay;
	private String spay;
	
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getBasic() {
		return basic;
	}
	public void setBasic(int basic) {
		this.basic = basic;
	}
	public int getSudang() {
		return sudang;
	}
	public void setSudang(int sudang) {
		this.sudang = sudang;
	}
	
	
	public int getAge() {
		if(birth==null) return -1;
		
		String s=birth.replaceAll("(\\-|\\.|\\/)", "");
		
		int y=Integer.parseInt(s.substring(0, 4));
	    int m=Integer.parseInt(s.substring(4, 6));
	    int d=Integer.parseInt(s.substring(6, 8));
	    
	    Calendar now = Calendar.getInstance ();
	    Calendar cal=Calendar.getInstance();
	    cal.set(Calendar.YEAR, y);
	    cal.set(Calendar.MONTH, m-1);
	    cal.set(Calendar.DATE, d);
	    
	    age = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
	    if ( (cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
	            || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) 
	              && cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH))) {
	        age--;
	    }
	    
		return age;
	}
	public String getTti() {
		if(birth==null) return null;
		
		String []tt={"¿ø¼şÀÌ", "´ß","°³","µÅÁö","Áã","¼Ò","È£¶ûÀÌ","Åä³¢","¿ë","¹ì","¸»","¾ç"};
		int y=Integer.parseInt(birth.substring(0, 4));
		tti = tt[y%12];
		return tti;
	}
	public int getTax() {
	    if(getPay()>=3000000) {
	        tax=(int)(getPay()*0.03);
	    }else if(getPay()>=2000000) {
	        tax=(int)(getPay()*0.02);
	    } 
		return tax;
	}
	
	public int getPay() {
		pay=basic+sudang;
		return pay;
	}
	public String getSpay() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		spay=nf.format(getPay());
		return spay;
	}
}
