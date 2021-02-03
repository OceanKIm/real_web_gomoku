<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<header>
	<div id="mainLogoContainer">
		<a href="/main/home">
			<img id='mainlogo' src="/res/img/mainlogo.png"> 
		</a>
		<div>
			현재 접속자수 : 0
		</div>
		<c:if test="${loginUser != null}">
			<div>id : ${loginUser.user_id} |</div>
			<div><a href="">로그아웃</a></div>
		</c:if>
	</div>
	<ul>
		<li>
			<a href="/main/home">HOME</a>
		</li>
		<li>
			<a href="/play/playHome">플레이</a>
		</li>
		<li>
			<a href="">자유게시판</a>
		</li>
	</ul>
</header>