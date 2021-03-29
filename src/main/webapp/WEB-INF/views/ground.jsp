<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Real오목 - playroom</title>
	<!-- css -->
	<link rel="stylesheet" href="/res/css/play/ground.css">
	<!-- js -->
	<script defer src="/res/js/play/ground.js"></script> 
</head>
<body>
	<c:if test="${loginUser != null}">
		<form id="data_user">
			<input type="hidden" name="id" value="${loginUser.user_id}">
			<input type="hidden" name="room_code" value="${loginUser.room_code}">
		</form>
	</c:if>   
	<c:if test="${loginUser == null}">
		<form id="data_guest">
			<input type="hidden" id="id" value="${guest.id}">
			<input type="hidden" id="room_code" value="${guest.room_code}">		
		</form>
	</c:if>
	<div id="groundContainer">
		<div id="frame">
			<img id='omuktable' src="/res/img/playtable.png"> 
		</div>
		<div id="boardCont">
			<div id="firstLine">
				<div id="roomTitle">
				<!-- #${room.i_room}  -->
				<c:if test="${loginUser != null}">
					방코드 : ${loginUser.room_code}
				</c:if>   
				<c:if test="${guest != null}">
					방코드 : ${guest.room_code}
				</c:if>				
				</div>
				<div><button id="roomOut">나가기</button></div>
			</div>
			<div id="readyRoom">
				<div id="blackRoom">
					<div class="tabRoom">#black</div>
					<div><button class="selectbox" id="selectBlack">select</button></div>
					<div class="time" id="blackTime">0</div>
				</div>
				<div id="whiteRoom">
					<div class="tabRoom">#white</div>
					<div><button class="selectbox" id="selectWhite">select</button></div>
					<div class="time" id=whiteTime>0</div>
				</div>
			</div>
			<div id="drawAndCut">
				<div>
					<button id="absGame">기권하기</button>
				</div>
				<div>
					<button>무르기</button>
				</div>
			</div>
			<div id="lastCont">
				<div id="btn_last">
					<button id="btn_watingRoom">대기실</button>
					<button id="btn_chattingRoom">채팅</button>
				</div>
				<div id="watingRoom">
				</div>
				<div id="chattingRoom">
					<div id="chatCont">
					</div>
					<div id="textCont">
						<input type="text" id="textVal">
						<input type="button" id="btn_text" value="입력" >
					</div>
				</div>					
			</div>
		</div>
	</div>
	
	<!-- 게임 진행 모달 창 -->
	<div class="black_bg"></div>
	<div class="modal_wrap">
		<div id="modalCont">
			<div id="modalMsg"></div>
			<div></div>
		</div>
	</div>
</body>
</html>

