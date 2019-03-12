package com.test070;

public interface Sample {
	public static int a = 10;
	//모든 메소드 -> 추상 메소드로만 구성 -> abstract 키워드 사용
	//추상 메소드는 구현부({})가 없다
	//메소드 시그니처만 존재한다
	public abstract void method();
	abstract void method2();

}
