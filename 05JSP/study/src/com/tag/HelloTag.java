package com.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class HelloTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	private int count = 0;

	@Override
	public int doAfterBody() throws JspException {
		if(count<5) {
			count++;
			try {
				pageContext.getOut().print("<br>"+count+" : ");
			} catch (Exception e) {
				throw new JspTagException(e);
			}
			return EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().print("<br></b>end...");
			count = 0;
		} catch (Exception e) {
			throw new JspTagException(e);
		}
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().print("환영합니다<br><b>");
		} catch (Exception e) {
			throw new JspTagException(e);
		}
		return EVAL_BODY_INCLUDE;
	}
	
	
}	
