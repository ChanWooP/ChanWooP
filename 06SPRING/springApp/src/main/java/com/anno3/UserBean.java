package com.anno3;

import javax.inject.Inject;

/*
 	- @Autowired : ������ ����. Ÿ������ �������� ����
 	- @Inject : �ڹٿ��� ����. Ÿ������ �������� ����. �Ʒ��� dependency �ʿ�
 	  <dependency>
	    <groupId>javax.inject</groupId>
	    <artifactId>javax.inject</artifactId>
	    <version>1</version>
	  </dependency>
 	- @Resource : �ڹٿ��� ����. ���̸����� �������� ����
 	- ������ : ���̸����� �������輳��
 	  @Autowired
	  @Qualifier("���̸�")
*/

public class UserBean {
	@Inject
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		System.out.println(s);
	}
}
