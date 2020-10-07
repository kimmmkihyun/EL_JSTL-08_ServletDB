<%@page import="myPkg.MymemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
Ex01_mymemUpdateForm.jsp<br>

<%

	/* MymemBean mb = (MymemBean)request.getAttribute("mb");
	
	System.out.println("UpdateForm id : " + mb.getId());
	System.out.println("UpdateForm name : " + mb.getName());
	System.out.println("UpdateForm password : " + mb.getPassword());  */
%>
<!-- 
만약 이전에 설정해놓은 것을 .getAttribute() 로 가져오지 않을 경우에는 다음과 같은 순서로 알아서 가지고 온다(따라서 굳이 쓰지 않아도 결과값을 출력하는데 문제가 되지 않는다.)
pageContext.getAttribute("mb"); => request.getAttribute("mb"); => session.getAttribute("mb"); => application.getAttribute("mb"); 
-->

<h3>회원 수정 폼</h3>
<form action="update.mem" method="post" name="myform">
	<input type="hidden" name="id" value="${ mb.id }">
	<table border="1">
<%-- 	
		<tr>
			<td>아이디</td>
			<td><input type="text" name="name" value="${ mb.id }"></td>
		</tr>	
--%>
		
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value="${ mb.getName() }"></td>
		</tr>
		
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="password" value="${ mb['password'] }"></td>
		</tr>

		<tr>
			<td colspan="2">
				<!-- <a href="#" onClick="insertData()">회원가입</a> -->
				<input type="submit" value="수정">
			</td>
		</tr>


	</table>
	
</form>

