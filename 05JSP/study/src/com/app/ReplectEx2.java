package com.app;

import java.lang.reflect.Method;

class Ex2{
	public Integer add(Integer a, Integer b) {
		return a+b;
	}
	
	public int sub(int a, int b) {
		return a-b;
	}
	
	public void write(String title, int s) {
		System.out.println(title+"->"+s);
	}
	
	public void print() {
		System.out.println("print...");
	}
}

public class ReplectEx2 {

	public static void main(String[] args) {
		String className = "com.app.Ex2";
		try {
			Class<?> cls = Class.forName(className);
			Object ob = cls.newInstance(); // ��ü ����
			
			Method m1 = cls.getDeclaredMethod("hap", new Class[] {Integer.class, Integer.class});
			Method m2 = cls.getDeclaredMethod("sub", new Class[] {int.class, int.class});
			Method m3 = cls.getDeclaredMethod("write", new Class[] {String.class, int.class});
			Method m4 = cls.getDeclaredMethod("print");
			
			Object o1 = m1.invoke(ob, 10, 20);
			m3.invoke(ob, "��", o1);
			
			Object o2 = (Integer)m2.invoke(ob, 100, 20);
			m3.invoke(ob, "��", o2);
			
			m4.invoke(ob);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
