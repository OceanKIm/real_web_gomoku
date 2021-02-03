<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>  
<html>
<head>
	<meta charset="UTF-8">
	<title>${title}</title>
	<script defer src="/res/js/join.js?ver=1"></script>
</head>
<body>
	<div><h3>회원가입</h3></div>
	<div>
		<form id="frm" action="/user/join" method="post" onsubmit="return joinChk();">
			<div><input type="text" name="user_id" placeholder="id" value="${user_id}" required></div>
			<div><input type="password" name="user_pw" placeholder="password" required></div>
			<div><input type="password" name="user_pw_chk" placeholder="password check" required></div>
			<div><input type="text" name="nm" placeholder="Your name" required></div>
			<div>
				Gender :
				<label>Man<input type="radio" name="gender" value="0" checked></label>
				<label>Woman<input type="radio" name="gender" value="1"></label>
			</div>
			<div><input type="text" name="ph" placeholder="Your phone number" required></div>
			<div><input type="submit" value="JOIN"></div>
		</form>
	</div>
	<div>
		<a href="/user/login">go to login</a>
	</div>
	<script>
		<c:if test="${msg != null}">
			alert('${msg}');
		</c:if>
	</script>
</body>
</html>