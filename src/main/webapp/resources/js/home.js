'use strict';

var btn_login = document.querySelector('#btn_login')
var btn_guest = document.querySelector('#btn_guest')

var loginCont = document.querySelector('#loginCont')
var joinCont = document.querySelector('#joinCont')

// 로그인 버튼 클릭
btn_login.onclick = login 
// 로그인 proc
function login () {
	console.log('login!')
	joinCont.innerHTML = ''
	loginCont.innerHTML = `
	<div>
		<div> 아이디와 비밀번호를 입력 해 주세요</div>
		<div><input type="text" id="user_id" value="" placeholder="아이디 입력"></div>
		<div><input type="password" id="user_pw" placeholder="비밀번호 입력"></div>
		<div><button id="btn_login_done">로그인</button></div>
	</div>
	<div>
		<button id="btn_join">회원가입 하기</button>
	</div>
	<div>
		<button id="btn_findIdPw">아이디/비밀번호 찾기</button>
	</div>
	`
	var user_id =  document.querySelector('#user_id')
	var user_pw =  document.querySelector('#user_pw')

	// 로그인완료 버튼 클릭
	var btn_login_done = document.querySelector('#btn_login_done')
	btn_login_done.onclick = function () {
		login_ajax(user_id, user_pw)
	}
	
	// 회원가입버튼 버튼 클릭
	var btn_join = document.querySelector('#btn_join')	
	btn_join.onclick = join
	
	// 아이디 비밀번호 찾기 버튼클릭
	var btn_findIdPw = document.querySelector('#btn_findIdPw')	
	btn_findIdPw.onclick = findIdPw
}

// 회원가입 Proc
function join() {
console.log('btn_join clicked')
	loginCont.innerHTML = ''
	joinCont.innerHTML = `
		<div> 당신의 회원가입을 환영합니다</div>
		<div><input type="text" id="user_id" placeholder="아이디 입력"></div>
		<div><input type="password" id="user_pw" placeholder="비밀번호 입력"></div>
		<div><input type="password" id="user_pw_chk" placeholder="비밀번호 확인"></div>
		<div><input type="text" id="nm" placeholder="이름 입력"></div>
		<div>
			Gender :
			<label>남성<input type="radio" name="gender" value="0" checked></label>
			<label>여성<input type="radio" name="gender" value="1"></label>
		</div>
		<div><input type="text" id="email" placeholder="이메일 입력"></div>
		<div> 이메일은 요구되지 않으며, 오직 비밀번호 복구에만 사용됩니다.(스팸 없음)</div>
		<div><button id="btn_join_done">회원가입 완료</button></div>
	`
	// 회원가입완료 버튼 클릭
	var btn_join_done = document.querySelector('#btn_join_done')
	var user_id =  document.querySelector('#user_id')
	var user_pw =  document.querySelector('#user_pw')
	var user_pw_chk =  document.querySelector('#user_pw_chk')	
	var nm =  document.querySelector('#nm')
	var gender =  document.getElementsByName('gender')
	var email =  document.querySelector('#email')							
	
	btn_join_done.onclick = function () {
		join_ajax(user_id, user_pw, user_pw_chk ,nm, gender, email)
	}
}

// 게스트 플레이 - 미구현
btn_guest.onclick = function () {
	console.log('guest')
}


// profile 수정 모달 proc
function profile_mod () {
	openCloseModal('block')
	// 한번 더 참조 시켜야함?
	var modalCont = document.querySelector('#modalCont')
	modalCont.innerHTML = 'profile 수정하기'
}

// 아이디 비밀번호 찾기  모달 proc
function findIdPw() {
	openCloseModal('block')
	modalCont.innerHTML = `
	    <div id="findIdPwCont">
	        <div id="modal_msg">
	        </div>
	        <div>
	            <input type="text" id="find_email" placeholder="가입된 이메일">
			</div>	   
			<div>      
  				<button id="send_email">보내기</button>
	        </div>
	        <div id="modal_exp">
			            ※가입된 아이디가 있을 경우, 입력하신 이메일로 아이디를 안내해 드립니다.<br>
			            ※만약 이메일이 오지 않는다면, 스팸 편지함응로 이동하지 않았는지 확인해 주세요<br>
			            ※이메일 서비스 제공자 사정에 의해 즉시 도착하지 않을 수 있으니,
			            최대 30분 정도 기다리신 후 다시 시도해주세요.
	        </div>
	    </div>
	`
	var find_email = document.querySelector('#find_email')
	var send_email = document.querySelector('#send_email')
	var modal_msg = document.querySelector('#modal_msg')
	send_email.onclick = function () {
		console.log('email : ' + find_email.value)
		axios({
				method: 'post',
				url: '/user/findId',
				data: {
					'e_mail':find_email.value
				}
			})
		.then(function (res) {
			console.log(res);
			if (res.data.result === 1) {
				modal_msg.innerHTML = '해당 메일로 아이디 정보를 보냈습니다.'
			} else if (res.data.result === 0) {
				modal_msg.innerHTML = '해당 이메일로 가입된 아이디가 없습니다.'
			} else if (res.data.result === -1) {
				modal_msg.innerHTML = '메일 전송 오류.'
			} 
		})		
	};
	
}

//================================= ajax ======================================
// 로그인 ajax
function login_ajax (user_id, user_pw) {
	console.log('login ajax proc')
	// required 요구
	if(user_id.value === '' || user_pw.value === '') {
		alert('아이디 또는 비밀번호를 입력 해 주세요.');
		return;	
	}
	
	axios({
		method: 'post',
		url: '/user/login',
		data: {
			user_id: user_id.value,
			user_pw: user_pw.value
		}
	})
	.then(function (res) {
		console.log(res);
		if (res.data.result === 1) {
			// 그냥 채널로 이동 시키자.
			location.href = '/main/home';
		} else if (res.data.result === 2) {
			alert('비밀번호를 다시 확인 해 주세요.');
		} else if (res.data.result === 3) {
			alert('존재하지 않는 아이디 입니다.');
		} 
	})
}

// 회원가입 ajax
function join_ajax (user_id, user_pw, user_pw_chk , nm, gender, email) {
	console.log('join ajax proc')
	// 젠더 확인
	var genValue;
	gender.forEach((node) => {
	    if(node.checked)  {
			console.log('gender : ' + node.value)
			genValue = node.value;
	    }
	}) 
	
	// id 확인 정규식
	var reUserId = /^[A-Za-z0-9+]*$/;
	if(!reUserId.test(user_id.value)) {
		alert('아이디를 확인 해 주세요.');
		user_id.focus();
		return;
	}
	
	// 비밀번호 체크
	if(user_pw.value !== user_pw_chk.value) {
		alert('비밀번호를 확인 해 주세요');
		user_pw.focus();
		return;
	}
	
	// 이름 확인 정규식
	var eleNm = nm;
	var reNm = /^[가-힣]*$/;
	if(!reNm.test(nm.value)) {
		alert('이름을 확인 해 주세요.');
		nm.focus();
		return;
	}
	
	// required 요구
	if(user_id.value === '' || user_pw.value === '' || nm.value === '') {
		alert('양식을 확인 해 주세요');
		return;
	}
	
	axios({
		method: 'post',
		url: '/user/join',
		data: {
			user_id: user_id.value,
			user_pw: user_pw.value,
			nm: nm.value,
			gender: genValue,
			e_mail: email.value
		}
	})
	.then(function (res) {
		console.log(res);
		if (res.data.result === 1) {
			login()	// 회원가입 성공 시 다시 로그인 이동.
			alert('회원가입이 완료 되었습니다!');
		}
	})
}

// 로그아웃 처리 tag onclick ajax 
function logout() {
	console.log('logout ajax proc')
	axios.get('/user/logout')
	.then(function (res) {
	    if (res.data.result === 1) {
			location.href = '/main/home';
		}
	})
}


// ============================================================ 기타
// 모달창 열기 닫기
function openCloseModal(state) {
	var modalWrapElem = document.querySelector('.modal_wrap')
	var blackBgElem = document.querySelector('.black_bg')	
	modalWrapElem.style.display = state
	blackBgElem.style.display = state
}
function modalClose() {
	openCloseModal('none')
}
// 모달창 Cont참조  id : modalCont
var modalCont = document.querySelector('#modalCont')





