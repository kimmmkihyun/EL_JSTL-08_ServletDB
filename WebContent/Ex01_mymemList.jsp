<%@page import="myPkg.MymemBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   

Ex01_mymemList.jsp<br>
목록보기 페이지(레코드수 : ${ fn:length(lists) })

<%
	ArrayList<MymemBean> lists =(ArrayList<MymemBean>)request.getAttribute("lists");
	
	//pageContext.setAttribute("pclists",lists);
%>
 

<table border="1">
	<tr>
		<th>id</th>
		<th>name</th>
		<th>password</th>
		<th>삭제</th>
		<th>수정</th>
	</tr>
	
	<c:forEach var="bean" items="${ lists }">
		<tr>
			<td>${ bean.id }</td>
			<td>${ bean.getName() }</td>
			<td>${ bean["password"] }</td>
			<td><a href="delete.mem?id=${ bean.id }">삭제</a></td>
			<td><a href="updateForm.mem?id=${ bean.id }">수정</a></td>
		</tr>
	</c:forEach>
	
	
	<!-- 
	<c:forEach var="lists" items="<%=lists %>">
		
		<tr>
			<td>${ lists.id }</td>
			<td>${ lists.name }</td>
			<td>${ lists.password }</td>
		</tr>
		
	</c:forEach>	
	-->
	
	<!--
	<c:forEach var="lists" items="${ pclists }"> 
		
		<tr>
			<td>${ lists.id }</td>
			<td>${ lists.name }</td>
			<td>${ lists.password }</td>
		</tr>
		
	</c:forEach>
	
	
	<c:set var="setlists" value="<%=lists %>"/>
	<c:forEach var="lists" items="${ setlists }">
		<tr>
			<td>${ lists.id }</td>
			<td>${ lists.name }</td>
			<td>${ lists.password }</td>
		</tr>
	</c:forEach>	
	-->
	
<br><br>


</table>

<hr>


<table border="1">
	<tr>
		<th>id</th>
		<th>name</th>
		<th>password</th>
		<th>삭제</th>
		<th>수정</th>
	</tr>
	
	<c:forEach var="i" begin="0" end="${ fn:length(lists)-1 }" step="1">
		<tr>	
			<td>${ lists[i].id }</td>
			<td>${ lists[i].getName()}</td>
			<td>${ lists[i]["password"] }</td>	
			<td><a href="delete.mem?id=${ lists[i].id }">삭제</a></td>
			<td><a href="updateForm.mem?id=${ lists[i].id }">수정</a></td>  
		</tr>   
	</c:forEach>	

</table>

<br>

<a href="Ex01_mymemForm.jsp">삽입</a>




<!-- 방의 개수 제한  :  배열(list)       <==>   arraylist(collection) -->

