package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class TimerFilter implements Filter{
	private FilterConfig fliterConfig;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//request 필터
		long before = System.currentTimeMillis();
		
		chain.doFilter(request, response);
		
		//response 필터
		long end = System.currentTimeMillis();
		
		String uri;
		if(request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest)request;
			uri=req.getRequestURI();
			
			//log 출력
			fliterConfig.getServletContext().log(uri+":"+(end-before)+"ms");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.fliterConfig = filterConfig;
	}
}
