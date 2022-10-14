<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>WhereEco</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/test.css" />
</head>
<body>
<nav class="menu">
	<h3>회 원 가 입</h3>
</nav>
<div class="tablecenter">
	<form method="post" action="join">
		<table>
			<tr>
				<td><input class="box1" type="text" placeholder="이름" name="name" value="${ userJoinDto.name }" /></td>
			</tr>
			<tr>
				<td><input class="box" type="text" placeholder="아이디"  name="userId" value="${ userJoinDto.userId }" /></td>
			</tr>
			<tr>
				<td><input class="box" type="password" placeholder="비밀번호" name="pwd1" value="${ userJoinDto.pwd1 }" /></td>
			</tr>
			<tr>
				<td><input class="box4" type="password" placeholder="비밀번호 확인" name="pwd2" value="${ userJoinDto.pwd2 }" /></td>
			</tr>
		</table>
		<button type="button" class="btn" onclick="location.href='login'">돌 아 가 기</button>
		<button type="submit" class="btn" >저 장 하 기</button>
		<c:if test="${ not empty message }">
			<div class="message">
					${ message }
			</div>
		</c:if>
	</form>
</div>
</body>
</html>
</div>
</body>
</html>