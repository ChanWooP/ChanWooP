package com.sp.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*
 * - HandlerInterceptorAdapter 인터페이스
 * 	- 컨트롤러가 요청 전과 후에 알맞는 기능을 수행 할 수 있도록 하기 위한 인터페이스
 *    (서블릿 필터와 유사)
 *	- HandlerInterceptor 인터페이스는 여러개 등록 가능
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter{

	 private final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginCheckInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 클라이언트 요청이 컨트롤러에 도착하기 전에 호출
		// false를 리턴하면 HandlerInterceptor 또는 컨트롤러를 실행하지 않고 종료
		boolean result = true;
		
		try {
			HttpSession session = request.getSession();
			SessionInfo info = (SessionInfo)session.getAttribute("member");
			String cp = request.getContextPath();
			String uri = request.getRequestURI();
			String queryString = request.getQueryString();
			
			if(info!=null)
				return true;
			
			result=false;
			
			//로그인이 안된 경우
			if(isAjaxRequest(request)) {
				response.sendError(403);
			}else {
				if(uri.indexOf(cp)==0)
					uri=uri.substring(request.getContextPath().length());
				if(queryString!=null)
					uri+="?"+queryString;
				
				session.setAttribute("preLoginURI", uri);
				
				response.sendRedirect(cp+"/member/login");
			}
			
		} catch (Exception e) {
			logger.info("pre:"+e.toString());
		}
		
		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러의 요청을 처리한 후 호출. 컨트롤러에서 실행중 예외가 발생하면 실행되지 않음
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 컨트롤러의 요청이 처리된 후 뷰를 통해 클라이언트에게 응답을 전송한뒤에 실행
		// 컨트롤러 처리 중 또는 뷰를 생성하는 과정에서 예외가 발생해도 실행된다.
	}
	
	// AJAX 요청인지 확인하는 메소드
	private boolean isAjaxRequest(HttpServletRequest req) {
		String h = req.getHeader("AJAX"); // 사용자에 의해 작성된 header
		return h!=null&&h.equals("true");
	}

}
