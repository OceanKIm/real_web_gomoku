<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<div id="homeContainer">
	<c:if test="${loginUser == null}">
		<h3>웹으로 즐기는 리얼 타임 오목 대전</h3>
		<div class="wrap">
		<c:if test="${guest == null}">
			<div id="btn_login" class="button">로그인</div>
	  		<div id="btn_guest" class="button2">게스트</div>
		</c:if>
		<c:if test="${guest != null}">
			<div>현재 게스트 모드 플레이 중 입니다. (나중에 추가 기능 구현)</div>
		</c:if>
		</div>	
	</c:if>
	<c:if test="${loginUser != null}">
		<div id="profileCont">
			<div class="circular--landscape circular--size200 subCont">
				<c:if test="${loginUser.profile_img == null}">
				<img src="/res/img/basic_profile.jpg">
				</c:if>
			</div>
			<div class="subCont">
				<div>
					아이디 : ${loginUser.user_id}
				</div>
				<div>
					이름 : ${loginUser.nm}
				</div>
				<div>
					레이팅 : ${loginUser.rating}
				</div>
				<div>
					랭킹 : ${loginUser.ranking}
				</div>
				<div>
					전적: ${loginUser.win_cnt}승 ${loginUser.draw_cnt}무  ${loginUser.lose_cnt}패 
				</div>
				<div>
					<button onclick="profile_mod()">프로필 수정</button>
					<button onclick="logout()">로그아웃</button>		
				</div>
			</div>
			<div class="subCont">
				친구 목록 to do
			</div>
		</div>
	</c:if>
	<div id="userCont">
		<div id="loginCont">
		</div>
		<div id="joinCont">
		</div>
	</div>
	<div id="home_ad">
		<div>수익이 없는 광고입니다.</div>
		<img src="/res/img/home_ad.jpg">
	</div>
</div>



<!-- profile 수정 and id pw 찾기 모달창 -->
<div class="black_bg"></div>
<div class="modal_wrap">
	<div class="modal_close" onclick="modalClose()">
			<a href="#">close</a>
	</div>
	<div id="modalCont">
	
	</div>
</div>




