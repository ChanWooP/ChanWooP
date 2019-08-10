package com.anno5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// id�� �������� ������ ���̵�� Ŭ�����̸����� ù���ڸ� �ҹ��ڷ��� Ŭ�������̴�. �� �Ʒ� Ŭ������
//   ���̵�� userBean �̴�.
@Component
public class UserBean {
	@Autowired
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		System.out.println(s);
	}
}
