<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="java.sql.*, com.connection.*, com.guestbook.*, java.util.*"%>
<%!
 
%>
<%
    GuestbookDAO gdao = new GuestbookDAO();
 
    request.setCharacterEncoding("UTF-8");
    StringBuilder sb = new StringBuilder();
    String path = request.getContextPath();
    
    //데이터 수신 -> 글번호, 패스워드
    String ssn = request.getParameter("ssn");
    String pw = request.getParameter("pw");
    
    //DAO 클래스의 remove() 
    
    
    String result1 = "";
 
    int removetable = gdao.remove(ssn, pw);
 
    if (removetable == 0) {
        result1 = "fail";
    } else {
        result1 = "success";
    }
    
    //강제 페이지 전환
    response.sendRedirect("guestbook.jsp?result1=" + result1);
%>