package com.test106;

import java.io.Serializable;

public class Member implements Serializable{
	
	//역직렬화에 대비한 고유번호(번호는 할때마다 다를 수 있음)
	private static final long serialVersionUID = -5949390078161142365L;
	
	private String id, name;
	
	public Member() {
			
	}

	public Member(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
