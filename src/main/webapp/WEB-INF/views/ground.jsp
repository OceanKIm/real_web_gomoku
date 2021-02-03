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
	<div id="groundContainer">
		<div id="frame">
			<img id='omuktable' src="/res/img/playtable.png"> 
		</div>
		<div id="boardCont">
			<div id="firstLine">
				<div id="roomTitle">#1 번방 어서오세요</div>
				<div><button id="roomOut">나가기</button></div>
			</div>
			<div id="readyRoom">
				<div id="blackRoom">
					<div class="tabRoom">#black</div>
					<div><button class="selectbox" id="selectBlack">select</button></div>
					<div class="time">0:00</div>
				</div>
				<div id="whiteRoom">
					<div class="tabRoom">#white</div>
					<div><button class="selectbox" id="selectWhite">select</button></div>
					<div class="time">0:00</div>
				</div>
			</div>
			<div id="drawAndCut">
				<div>
					<button>비김신청</button>
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
					임시 대기
				</div>
				<div id="chattingRoom">
					임시 채팅
				</div>					
			</div>
		</div>
	</div>
</body>
</html>

