package com.sp.member;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.stereotype.Service;

import com.sp.member.SessionInfo;

@Service("member.loginService")
public class LoginServiceImpl implements LoginService{
	
	@Inject // 자바에서 지원. 타입으로 의존관계를 설정
	private Provider<SessionInfo> provider;
	// Singleton Bean에서 Prototype Bean을 참조할때 사용
	// @Scopr("session")은 Singleton Bean을 이용하여 객체를 참조할 수 없음
	// , 세션마다 객체가 만들어지므로. @Autowired로 의존관계 설정 불가
	
	@Override
	public SessionInfo loginMemberInfo() {
		SessionInfo info = null;
		
		try {
			SessionInfo si = provider.get();
			if(si.getUserId()!=null)
				info = si;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public boolean requestLogin(String userId, String userPwd) {
		try {
			if(userId.equals("spring") && userPwd.equals("spring")) {
				SessionInfo info = provider.get();
				info.setUserId("spring");
				info.setUserName("spring");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
