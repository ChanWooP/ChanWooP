package com.test074;

public class Sample3 {
	
	//HAS-A 관계
	//강한결합 vs 약한결합
	//인터페이스를 이용한 약한 결합 상태를 권장
	private Sample1 sample;
	
	//강한결합
	public Sample3(Sample1 sample) {
		this.sample = sample;
	}
	
	public void method() {
		this.sample.method();
	}

}
