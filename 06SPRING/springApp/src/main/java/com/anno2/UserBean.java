package com.anno2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserBean {
	@Autowired   // Ÿ������ �������� ����. setter �ʿ� ����
	@Qualifier("userService2")  // ������ Ÿ���� 2�̻��� ��� ���̸����� ����.
	                            // ��, �ܵ����� ��� �Ұ��ϸ� @Autowired�� �Բ� ���
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		System.out.println(s);
	}
}
