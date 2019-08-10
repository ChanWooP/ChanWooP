<%@ tag body-content="empty" language="java" pageEncoding="UTF-8"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="subject" required="true" %>
<%@ attribute name="score" required="true" type="Integer"%>
<%
//4.5, 4.0 ~ 0.0
//과목 : 자바, 점수 : 90, 평점 : 4.0
String s = "과목:"+subject+", ";
String s2 = "점수:"+score+", ";
String s3 = "평점:";
double grade = 0;

if(score>=90)
	grade+=4.5;
else if(score>= 80)
	grade+=4.0;
else if(score>=70)
	grade+=3.5;
else if(score>=60)
	grade+=3.0;
else if(score>=50)
	grade+=2.5;
else if(score>=40)
	grade+=2.0;
else if(score>=30)
	grade+=1.5;
else if(score>=20)
	grade+=1.0;
else if(score>=10)
	grade+=0.5;

s3+=grade;
%>

<%=s%><%=s2%><%=s3%>