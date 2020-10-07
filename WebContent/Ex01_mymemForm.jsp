<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
Ex01_mymemForm.jsp<br>
<script type="text/javascript">
	function insertData(){
		//alert("1");
		
		document.myform.action="insert.mem";
		document.myform.submit(); //submit 누른 것처럼 동작하겠다
	}

    
    
</script>    

<h3>회원 가입 폼</h3>

<form action="" method="post" name="myform">

	<table border="1">

		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value="윤아"></td>
		</tr>
		
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="password" value="1234"></td>
		</tr>

		<tr>
			<td colspan="2">
				<a href="#" onClick="insertData()">회원가입</a>
				<!-- <input type="submit" value="회원가입"> -->
			</td>
		</tr>


	</table>
	
</form>









