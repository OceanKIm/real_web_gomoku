'use strict';

var btn_login = document.querySelector('#btn_login')
var btn_guest = document.querySelector('#btn_guest')

var loginCont = document.querySelector('#loginCont')
var joinCont = document.querySelector('#joinCont')

// 로그인 버튼 클릭
btn_login.onclick = login 
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
		<button id="">비밀번호 찾기</button>
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
	btn_join.onclick = function join() {
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
}

// 로그인 ajax
function login_ajax (user_id, user_pw) {
	console.log('login ajax proc')
	console.log('id : ' + user_id.value)
	console.log('pw : ' + user_pw.value)
	
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


// 게스트 플레이 - 미구현
btn_guest.onclick = function () {
	console.log('guest')
}

// 로그아웃 처리 tag onclick
function logout() {
	axios.get('/user/logout')
	.then(function (res) {
	    if (res.data.result === 1) {
			location.href = '/main/home';
		}
	})
}

// 프로필 수정 처리
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

function profile_mod () {
	openCloseModal('block')
}






