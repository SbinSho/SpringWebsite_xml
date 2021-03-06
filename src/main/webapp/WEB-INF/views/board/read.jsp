<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>이름</th>
			<td>${ memberVO.username }</td>
		</tr>
		<tr>
			<th>아이디</th>
			<td>${ memberVO.userid }</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>${ memberVO.userpwd }</td>
		</tr>
		<tr>
			<th>가입일</th>
			<td>${ memberVO.rdate }</td>
		</tr>
	</table>

</body>
</html>