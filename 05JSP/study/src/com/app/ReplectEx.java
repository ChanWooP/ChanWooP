package com.app;

class Ex{
	public int add(int a, int b) {
		return a+b;
	}
	
	public void print(String title, int reuslt) {
		System.out.println(title+"->"+reuslt);
	}
}

public class ReplectEx {

	public static void main(String[] args) {
		
		String clsName = "com.app.Ex";
		try {
			Class<?> cls = Class.forName(clsName);
			Object ob = cls.newInstance();
			
			Ex e = (Ex)ob;
			
			int s = e.add(10, 5);
			e.print("гу", s);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
