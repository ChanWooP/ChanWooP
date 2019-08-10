package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/*
@WebFilter(
	urlPatterns = "/*",
	initParams = @WebInitParam(name = "charset", value="UTF-8")
)
*/

public class CharacterEncodingFilter implements Filter {

	public String charset;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// request 필터
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) request;
			if (req.getMethod().equalsIgnoreCase("POST")) {
				req.setCharacterEncoding(charset);
			}
		}

		// 다음 필터 또는 필터의 마지막이면 서블릿(JSP) 실행
		chain.doFilter(request, response);

		// response 필터

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		charset = filterConfig.getInitParameter("charset");

		if (charset == null || charset.length() == 0)
			charset = "UTF-8";
	}

}
