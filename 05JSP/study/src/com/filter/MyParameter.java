package com.filter;

import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyParameter extends HttpServletRequestWrapper{

	public MyParameter(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		if(name.startsWith("pay")) {
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			String s = nf.format(Long.parseLong(super.getParameter(name)));
			return s;
		}
		
		return super.getParameter(name);
	}
	
}
