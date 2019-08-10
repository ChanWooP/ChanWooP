package com.sp.member;

import com.sp.member.SessionInfo;

public interface LoginService {
	public SessionInfo loginMemberInfo();
	public boolean requestLogin(String userId, String userPwd);
}
