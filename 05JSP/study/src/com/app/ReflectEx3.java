package com.app;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class ExVO{
	private String name;
	private int age;
	private String tel;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
}

public class ReflectEx3 {
	public static void main(String[] args) {
		String className = "com.app.ExVO";
		
		try {
			Class<?> cls = Class.forName(className);
			Object ob = cls.newInstance();
			
			//Field[] ff = cls.getFields(); // public 필드 목록
			
			// setter 메소드 호출
			Field[] ff = cls.getDeclaredFields(); // 모든 필드 목록(private도)
			for(Field f : ff) {
				String fieldName = f.getName();
				
				String first = fieldName.substring(0, 1).toUpperCase();
				String last = fieldName.substring(1);
				String setter = "set" + first + last;
				
				Method m = cls.getDeclaredMethod(setter, f.getType());
				
				//if(f.getType().getName().equals("java.lang.String")){ // 타입비교
				
				if(f.getName().equals("name")) {
					m.invoke(ob, "홍길동");
				}else if(f.getName().equals("age")) {
					m.invoke(ob, 20);
				}else if(f.getName().equals("tel")) {
					m.invoke(ob, "010-1111-1111");
				}
			}
			
			// getter 메소드 호출
			for(Field f : ff) {
				String fieldName = f.getName();
				
				String first = fieldName.substring(0, 1).toUpperCase();
				String last = fieldName.substring(1);
				String getter = "get" + first + last;
				
				Method m = cls.getDeclaredMethod(getter);
				Object rt = m.invoke(ob);
				
				if(f.getType().getName().equals("java.lang.String")) {
					String s = (String)rt;
					System.out.println(f.getName()+":"+s);
				}else if(f.getType().getName().equals("int")) {
					int s = (int)rt;
					System.out.println(f.getName()+":"+s);
				}
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
