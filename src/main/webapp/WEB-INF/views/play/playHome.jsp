<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<c:if test="${hasRoomCode != null}">
	<jsp:forward page="/ground?room_code=${hasRoomCode}"></jsp:forward>
</c:if>
<c:if test="${guest == null && loginUser == null}">
	<jsp:forward page="/main/home"></jsp:forward>
</c:if> 
<c:if test="${loginUser != null}">
	<form id="data_user">
		<input type="hidden" name="id" value="${loginUser.user_id}">
		<input type="hidden" name="room_code" value="${loginUser.room_code}">
	</form>
</c:if>   
<c:if test="${guest != null}">
	<form id="data_guest">
		<input type="hidden" id="id" value="${guest.id}">
		<input type="hidden" id="room_code" value="${guest.room_code}">		
	</form>
</c:if>  
<div id="playHomeContainer">
	<div id="subMenuCont">
		<button id="btn_createRoom">방 만들기</button>
		방리스트는 실시간으로 갱신됩니다.
	</div>
	<div id="roomListCont">
	</div>
</div>

    
