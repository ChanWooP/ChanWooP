<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.MultipartFilter"%>
<%@page import="java.net.URLDecoder"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*, java.io.*"%>


<%
	String cp = request.getContextPath();
	request.setCharacterEncoding("UTF-8");
	
	//웹서버의 진짜 경로
	String root = pageContext.getServletContext().getRealPath("/");
	
	//File.separator 운영체제 환경에 따라서 "/", "\\" 등 알아서 지정해줌
	//웹서버의 진짜 경로에 uploads폴더를 만들고 그안에 pds파일을 만든다
	String pathname = root+"uploads"+File.separator+"pds";
	
	System.out.println("root:"+root);
	System.out.println("pathname:"+pathname);
	
	File f = new File(pathname);
	if(! f.exists()) // 폴더가 없으면
		f.mkdirs(); // 모든 폴더를 만들기
		
	//enctype="multipart/form-data" 인 경우는 request.getParameter()로
	//파라메터를 넘겨 받을 수 없다.
	
	String encType = "utf-8"; // 데이터의 enctype;
	int maxFileSize = 5*1024*1024; // 최대 업로드 용량 5MB
	
	//request, 파일저장경로, 업로드최대용량, 파라미터의인코딩
	//동일한 파일명보후여부
	MultipartRequest mreq;
	mreq = new MultipartRequest(request, pathname, maxFileSize, encType
			, new DefaultFileRenamePolicy());
	
	String subject = mreq.getParameter("subject");
	String saveFilename = mreq.getFilesystemName("upload"); // 서버에 저장된 파일 명
	String orginalFilename = mreq.getOriginalFileName("upload"); // 클라이언트가 업로드한 파일 명
	long size = 0;
	if(mreq.getFile("upload") != null)
		size = mreq.getFile("upload").length(); // 파일 크기
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <p>제목 : <%=subject %></p>
 <p>서버에 저장된 파일 명 : <%=saveFilename %></p>
 <p>클라이언트가 업로드한 파일 명 : <%=orginalFilename %></p>
 <p>파일크기 : <%=size %>byte</p>
</body>
</html>