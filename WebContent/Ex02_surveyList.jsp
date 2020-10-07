<%@page import="svy.SurveyBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>       
Ex02_surveyList.jsp<br>

<% 
	ArrayList<SurveyBean> lists = (ArrayList<SurveyBean>)request.getAttribute("lists");

%>
레코드 갯수 : ${ fn:length(lists) } <br>

<table border="1">

	<tr>
		<td>번호</td>
		<td>이름</td>
		<td>회사</td>
		<td>이메일</td>
		<td>만족도</td>
		<td>관심 분야</td>
		<td>정보 발송 방법</td>
		<td>동의 여부</td>
		<td>삭제</td>
		<td>수정</td>
	</tr>
 	
	<c:forEach var="i" begin="0" end="${ fn:length(lists)-1 }" >
	
		<tr>
			<td>${ lists[i].no }</td>
			<td>${ lists[i].name }</td>
			<td>${ lists[i].company }</td>
			<td>${ lists[i].email }</td>
			<td>${ lists[i].satisfaction }</td>
			<td>${ lists[i].part }</td>
			<td>${ lists[i].howto }</td>
			<td>
				<c:if test="${ lists[i].agree == 1 }">
					동의함
				</c:if>
				
				<c:if test="${ !(lists[i].agree == 1) }">
					동의 안함
				</c:if>
			</td>
				
				
			<td><a href="delete.sv?no=${ lists[i].no }">삭제</a></td>
			<td><a href="updateForm.sv?no=${ lists[i].no }">수정</a></td>
				
					
		</tr>	

	</c:forEach>
	
	
</table>
	
	<br>
	<hr>
	<br>
	
<table border="1">

	<tr>
		<td>번호</td>
		<td>이름</td>
		<td>회사</td>
		<td>이메일</td>
		<td>만족도</td>
		<td>관심 분야</td>
		<td>정보 발송 방법</td>
		<td>동의 여부</td>
		<td>삭제</td>
		<td>수정</td>
	</tr>

	<c:forEach var="sb" items="${ lists }">
		<tr>
			<td>${ sb.no }</td>
			<td>${ sb.name }</td>
			<td>${ sb.company }</td>
			<td>${ sb.email }</td>
			<td>${ sb.satisfaction }</td>
			<td>${ sb.part }</td>
			<td>${ sb.howto }</td>
			<td>
				<c:if test="${ sb.agree == 1 }">
					동의함
				</c:if>
				
				<c:if test="${ !(sb.agree == 1) }">
					동의 안함
				</c:if>
			</td>
						
			<td><a href="delete.sv?no=${ sb.no }">삭제</a></td>
			<td><a href="updateForm.sv?no=${ sb.no }">수정</a></td>
				
					
		</tr>	
	</c:forEach>

</table>
<br>
<a href="Ex02_surveyInputForm.jsp">삽입</a>
