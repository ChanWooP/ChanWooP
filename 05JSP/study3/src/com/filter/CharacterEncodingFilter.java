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
		// request ����
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) request;
			if (req.getMethod().equalsIgnoreCase("POST")) {
				req.setCharacterEncoding(charset);
			}
		}

		// ���� ���� �Ǵ� ������ �������̸� ����(JSP) ����
		chain.doFilter(request, response);

		// response ����

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		charset = filterConfig.getInitParameter("charset");

		if (charset == null || charset.length() == 0)
			charset = "UTF-8";
	}

}
