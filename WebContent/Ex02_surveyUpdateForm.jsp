<%@page import="svy.SurveyBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>           
Ex02_surveyUpdateForm.jsp<br>

수정 폼

<%
	SurveyBean sb = (SurveyBean)request.getAttribute("sb"); 
%>
<form action="update.sv" method="post"> 
	 과정 만족도 설문<br>
	 아래 항목을 입력해 주세요.<P>
	<input type="hidden" name="no" value="${ sb.no }">
	 <b>개인 정보 :</b><br>
	이름 　　　　<input type="text" name="name" value="${ sb.name }"><br>
	소속 회사　　<input type="text" name="company" value=${ sb.company }><br>
	이메일 주소　<input type="text" name="email" value="${ sb.email }"><br>
	<p>
	<b>본 교육 과정을 수강하고 과정에 만족도를 선택해 주세요.</b><br>
	<input type="radio" name = "satisfaction" value="매우만족" <c:if test="${ sb.satisfaction == '매우만족' }"> checked </c:if>  >매우 만족 
	<input type="radio" name = "satisfaction" value="만족" <c:if test="${ sb.satisfaction == '만족' }"> checked </c:if> >만족
	<input type="radio" name = "satisfaction" value="보통" <c:if test="${ sb.satisfaction == '보통' }"> checked </c:if> >보통
	<input type="radio" name = "satisfaction" value="불만족" <c:if test="${ sb.satisfaction == '불만족' }"> checked </c:if> >불만족
	<input type="radio" name = "satisfaction" value="매우불만족" <c:if test="${ sb.satisfaction == '매우불만족' }"> checked </c:if> >매우불만족
	<p>
	<b>관심있는 분야는 무엇입니까?</b><br>
	${ sb.part }<br>
	<input type="checkbox" name = "part" value="JAVA" <c:if test="${ fn:contains(sb.part,'JAVA') }"> checked </c:if> >JAVA
	<input type="checkbox" name = "part" value="Spring" <c:if test="${ fn:contains(sb.part,'Spring') }"> checked </c:if> >Spring
	<input type="checkbox" name = "part" value="UML" <c:if test="${ fn:contains(sb.part,'UML') }"> checked </c:if> >UML<br>
	<input type="checkbox" name = "part" value="JDBC" <c:if test="${ fn:contains(sb.part,'JDBC') }"> checked </c:if> >JDBC
	<input type="checkbox" name = "part" value="서블릿" <c:if test="${ fn:contains(sb.part,'서블릿') }"> checked </c:if> >서블릿
	<input type="checkbox" name = "part" value="JSP" <c:if test="${ fn:contains(sb.part,'JSP') }"> checked </c:if> >JSP<br>
	<p>
	정보 발송 방법 <select name="howto">
		<option value="email" <c:if test="${ sb.howto eq 'email'}">selected </c:if>>email
		<option value="우편" <c:if test="${ sb.howto eq '우편'}">selected </c:if>>우편
		</select>
	
	<p>
	<input type="checkbox" name="agree" value="1" <c:if test="${ sb.agree == 1 }"> checked </c:if>  > 정보 발송에 동의합니다.<br>
	<input type="submit" value="전송">
</form>