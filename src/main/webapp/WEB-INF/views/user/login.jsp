<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="${R}common.js"></script>
	<link rel="stylesheet" href="${R}common.css">
	<style>
		body {
			background-color: #ABD09A;
			text-align: center;
		}
		h1 {
			color: #554C2B;
			font-size: 48px; /*글씨 크기 수정*/
		}
		.title {
			color: #ABD09A;
		}
		form {
			position: absolute; /*절대 배치 -> 이걸 설정하는 순간 body의 text-align: center; 영향을 받지 않음*/
			top: 480px;
			left: 50%;
			transform: translate(-50%, -50%);
		}
		input {
			width: 450px;
			height: 40px;
			border: 2px solid beige;
			background-color: white;
			border-radius: 10px;
		}
		input:hover {
			border: 3px solid #554C2B;
		}
		button {
			margin-left: 10px;
			margin-right: 10px;
			width: 180px;
			height: 45px;
			color: #554C2B;
			background-color: #FFF7DA;
			border: none;
			border-radius: 10px;
			font-size: 13px;
		}
		.btn {
			margin-left: 10px;
			margin-right: 10px;
			width: 180px;
			height: 45px;
			color: #554C2B;
			background-color: #FFF7DA;
			border: none;
			border-radius: 10px;
			font-size: 13px;
		}
		button:hover {
			background-color: #554C2B;
			color: #FFF7DA;
		}
	</style>
</head>
<body>
<div class="container">
	<form:form method="post" modelAttribute="user">
	<img src="<c:url value="/images/wherelogo.png"/>">
	<div class="title"> ${ user.id > 0 ? "수정" : "등록" }</div>
	<form method="post">
		<table>
			<tr>
				<td><input type="text" placeholder="아이디" name="userId" value="${ user.userId }" />
					<br><br></td>
			</tr>
			<tr>
				<td><input type="password" placeholder="비밀번호" name="pwd" value="${ user.pwd }" /></td>
			</tr>
		</table>
		<br><br>
		<form>
			<div>
				<button type="submit" class="btn" >로 그 인</button>
				<button type="button" class="btn" onclick="location.href='join'">회 원 가 입</button>
				<c:if test="${ user.id > 0 }">
					<a href="delete?id=${ user.id }" class="btn" data-confirm-delete>삭제</a>
				</c:if>
			</div>
			</form:form>
</div>
</body>
</html>