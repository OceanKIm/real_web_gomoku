var ajax_polling_time = 1000	// 대기실, 채팅, 레디룸 polling
var game_polling_time = 500	// 게임진행 polling

// 모달창 표시 시간 and 페이지 리로드 타임 (같이 수정하는 것이 좋음)
var modal_time = 1000
var reload_time = 1000

// switch onoff ajax polling
var switch_watingRoom_ajax = true
var switch_chatting_ajax = false
var switch_readRoom_ajax = true

// switch outSelect btn
var switch_outSelect = true

// is game start and finish
var is_game_start = false;
var is_game_finish = false;

// player - now
var black_player
var white_player 
var turn = 2	// 기본 흑 선공

// 메인 프레임
var frame = document.querySelector('#frame');

// 세션 유저 정보.
var data_user = document.querySelector('#data_user')
var data_guest = document.querySelector('#data_guest')
var id;
var room_code;

// select black and white
var readyRoom = document.querySelector('#readyRoom')
var btn_selectBlack = document.querySelector('#selectBlack')
var btn_selectWhite = document.querySelector('#selectWhite')
var is_selected_black = false
var is_selected_white = false

// user data 참조
if (data_user) {
	console.log('set data_user')
	id = data_user.id.value;
	room_code = data_user.room_code.value // 만약 ''이 아닐시 바로 해당 룸코드 대기실 입장
	console.log('user_id : ' + id)
	console.log('room_code : ' + room_code)
}
// guest data 참조
if (data_guest) {
	console.log('set data_guest')
	id = data_guest.id.value;
	room_code = data_guest.room_code.value	
	console.log('guset_id : ' + id)
	console.log('room_code : ' + room_code)
}

// 바둑판 구조 잡기
var table = document.createElement('table');
for (let i = 0; i < 15; i++) {
	var tr = document.createElement('tr');
	tr.id = `line_${i}`;
	for (let j = 0; j < 15; j++) {
		var td = document.createElement('td');
		td.onclick = put_pos_ajax;
		td.innerHTML = ''
		td.id = `${j}_${i}`;
		tr.append(td);
	}
	table.append(tr);
}
frame.append(table);



// 오목판에 돌 뿌려주기 변수
var tdArr = document.querySelectorAll('td');

// 시간 뿌리기
var blackTime = document.querySelector('#blackTime')
var whiteTime = document.querySelector('#whiteTime')

// 오목판에 돌 뿌리는 ajax
function selPos_ajax () {
	//console.log('selPos')
	fetch(`/play/selPos?room_code=${room_code}&user_id=${id}`, {
		method: 'GET',
	}).then(res => res.json())
	.then(list => {
		if (list.length == 0) {
			return // 값이 0 이라면 바로 리턴
		}
		for (var i = 0; i < list.length - 1; i++) {
			if (list[i].z === 1) {
				tdArr[list[i].y * 15 + list[i].x].style.backgroundImage = "url('/res/img/black_dol.png')"
			} else {
				tdArr[list[i].y * 15 + list[i].x].style.backgroundImage = "url('/res/img/white_dol.png')"
			}		
		}
		// turn 구현
		if (list.length > 1) {
			turn = list[list.length - 2].z
		}		
		if (list[list.length - 1].msg === 'canNotPut') {
			// 필요 없음.
			//modalDisplay('렌주룰 : 둘 수 없습니다.', 1)
			// 렌주룰 처리
			//location.reload()
		} else if (list[list.length - 1].msg === 'blackWin') {
			// todo 흑승 proc
			game_finish_proc('흑승 !!!', 'blackWin')
			
		} else if (list[list.length - 1].msg === 'whiteWin') {
			// todo 백승 proc
			game_finish_proc('백승!!!', 'whiteWin')
		} else if (list[list.length - 1].msg === 'chkAbs') {
			// 상대방 기권
			modalDisplay('상대방이 기권하였습니다.', 1)
			pageReload(1)			
		} else if (list[list.length - 1].msg === 'overTime') {
			// 시간 초과
			console.log('time over')
			if (turn === 2) {
				game_finish_proc('시간초과:백승!!!', 'whiteWin')
			} else {
				game_finish_proc('시간초과:흑승 !!!', 'blackWin')
			}	
		}
		console.log('left_time :' + list[list.length - 1].leftTime)
		if (turn === 2) {
			blackTime.innerHTML = list[list.length - 1].leftTime
			blackTime.style.color = 'red'
			whiteTime.style.color = 'white'			
		} else {
			whiteTime.innerHTML = list[list.length - 1].leftTime	
			blackTime.style.color = 'white'
			whiteTime.style.color = 'red'					
		}	
	})	
	
	// select 선수 색깔 바꾸기 
	if (turn === 2) {
		btn_selectBlack.style.background = 'red'
		btn_selectWhite.style.background = 'white'
		
	} else {
		btn_selectBlack.style.background = 'white'
		btn_selectWhite.style.background = 'red'		
	}
	
}


// game finish proc
function game_finish_proc(text, msg) {
	if (!is_game_finish) {
		modalDisplay(text, 1)
		var param = {
			msg:msg,
			user_id: id,
			black_player:black_player,
			white_player:white_player,
			room_code: room_code
		}
			
		fetch('/play/isFinish', {
			method: 'POST',
			headers: {
            	'Content-Type': 'application/json'
			},
			body: JSON.stringify(param)
		}).then(function(res) {
			return res.json()
		}).then(function(data) {
			if (data.result === 1) {
				switch_readRoom_ajax = true
				is_game_start = false
				is_game_finish = true;	
				pageReload(1)
			} 
		})			
	}
}


// 오목돌 두기 이벤트
var toggle = true;
var delay = 500;
function put_pos_ajax(item) {
	if (is_game_start && toggle) {
		
		var param = {
			user_id: id,
			black_player:black_player,
			white_player:white_player,
			room_code: room_code,
			pos_id:item.target.id,
			turn:turn
		}
			
		fetch('/play/putPos', {
			method: 'POST',
			headers: {
            	'Content-Type': 'application/json'
			},
			body: JSON.stringify(param)
		}).then(function(res) {
			return res.json()
		}).then(function(data) {
			if (data.result === 1) {
				item.target.style.backgroundImage = "url('/res/img/black_dol.png')";
			} else if (data.result === 2) {
				item.target.style.backgroundImage = "url('/res/img/white_dol.png')";
			} else if (data.result === 3) {
				modalDisplay('이미 착수 한 위치입니다.', 1)
			} else if (data.result === 4 && black_player === id) {
				modalDisplay('규칙: 렌주룰 위반.', 1)
			} else {
				console.log(data.result)
				console.log('can not put...')
			}
		})		
		toggle = false	
	} else {
		console.log('can not put...')
	}

	// 바둑돌 두기 지연 시키기
	setTimeout(function () {
		toggle = true
	}, delay)		
}

// start_game_bot
var game_start_proc_isStart = false;
var game_bot_inter = setInterval(game_bot, game_polling_time)
function game_bot() {
	
	// 게임 시작 확인
	if (is_game_start && !game_start_proc_isStart) {
		fetch(`/play/isStart?room_code=${room_code}`, {
			method: 'GET',
		}).then(res => res.json())
		.then(myJson => {
			if (myJson.result === 1) {
				modalDisplay('game start', 2)
			}
		})				
	}
	
	if (is_game_start) {
		console.log('game start')		
		selPos_ajax() // 게임 시작 시에만 sel ajax 뿌림
		// 퇴장 불가
		switch_outSelect = false
		game_start_proc_isStart = true
		return
	} else {
		is_game_start = false
		// 퇴장 가능
		switch_outSelect = true 
	}
}


// -- lastCont
var btn_watingRoom = document.querySelector('#btn_watingRoom')
var btn_chattingRoom = document.querySelector('#btn_chattingRoom')

var watingRoom = document.querySelector('#watingRoom')
var chattingRoom = document.querySelector('#chattingRoom')

var chatCont = document.querySelector('#chatCont')
var textVal = document.querySelector('#textVal')
var btn_text = document.querySelector('#btn_text')

// 대기실 버튼
btn_watingRoom.onclick = function () {
	console.log('select waiting room')
	watingRoom.style.display = 'block'
	chattingRoom.style.display = 'none'
	
	// 채팅, 대기실 switch
	switch_watingRoom_ajax = true
	switch_chatting_ajax = false
}

// 채팅 버튼
btn_chattingRoom.onclick = function () {
	console.log('select chatting room')	
	watingRoom.style.display = 'none'
	chattingRoom.style.display = 'block'
	
	// 채팅, 대기실 switch
	switch_watingRoom_ajax = false
	switch_chatting_ajax = true
	var chatting_ajax_inter = setInterval(sel_text_ajax, ajax_polling_time);
}

// 채팅 구현 - 메세지 전송
btn_text.onclick = function() {
	var ctnt = textVal.value
	send_text_ajax(ctnt)
	textVal.value = ''
}

textVal.onkeyup = function () {
	if (window.event.keyCode == 13) {
		var ctnt = textVal.value
		send_text_ajax(ctnt)
		textVal.value = ''
    }
}


function send_text_ajax(ctnt) {
	
	var param = {
		user_id : id,
		ctnt : ctnt,
		room_code:room_code			
	}
	
	fetch('/play/chat/send', {
		method: 'POST',
		headers: {
           	'Content-Type': 'application/json'
		},
		body: JSON.stringify(param)
	}).then(function(res) {
		return res.json()
	}).then(function(data) {
		console.log(data)
	})		
}

// 채팅 text 뿌리기
function sel_text_ajax() {
	if (switch_chatting_ajax) {
		//console.log('chatting ajax...')
		fetch('/play/chat/sel', {
		method: 'GET',
		}).then(res => res.json())
		.then(myJson => {
			sel_text_proc(myJson)
		})	
	}
}


function sel_text_proc(list) {
	chatCont.innerHTML = ''
	for (let i = 0; i < list.length; i++) {
		let div = document.createElement('div')
		div.innerHTML = `${list[i].user_id}(${list[i].rating}): ${list[i].ctnt}`
		if (i == list.length - 1) {
			// 나중에 해결.. 자동으로 스크롤 내리기..
			//div.scrollTop = div.scrollHeight
			//window.scrollTo(0, div.scrollHeight)
		}
		chatCont.append(div)
	}
}




// 대기실 리스트 구현 - 첫 화면에 바로 표시 (처음 바로 polling 되는 것 임)
if (watingRoom) {
	watingRoom_ajax()
	var watingRoom_ajax_inter = setInterval(watingRoom_ajax, ajax_polling_time);
}

function watingRoom_ajax() {
	if(switch_watingRoom_ajax) {
		//console.log('watingRoom ajax...')
		fetch(`/play/waitList?room_code=${room_code}`, {
			method: 'GET',
		}).then(res => res.json())
		.then(myJson => {
			watingRoom.innerHTML = ''
			waitListObj.proc(myJson)
		})			
	} 
}




// 나가기 버튼 구현
var roomOut = document.querySelector('#roomOut')
roomOut.onclick = function() {
	
	// todo : 게임 중에는 나가기 버튼도 무효화
	if (id === black_player || id === white_player) {
		if (is_game_start) {
			modalDisplay('게임 중에는 나갈 수 없습니다.', 1)
			return
		}
	}
	
	console.log('room out')
	fetch('/play/roomOut', {
		method: 'GET',
	}).then(res => res.json())
	.then(myJson => {
		if (myJson.result === 1) {
			self.close();
		}
	})

}

// 대기실리스트뿌리기 객체
var waitListObj = {
	createListTable: function() {
		var tableElem = document.createElement('table')
		tableElem.innerHTML = 
		`<tr>
			<th>아이디</th>
			<th>닉네임</th>
			<th>레이팅</th>
		</tr>`			
		return tableElem
	},
	
	proc: function(list) {
		if(list.length == 0) {	
			watingRoom.innerHTML = '<div>대기자 없음</div>'	
			return
		}
		var table = this.createListTable()
		for(var i = 0; i < list.length; i++) {
			var recode = this.createRecode(list[i])
			table.append(recode)
		}		
		watingRoom.append(table)
	},
	
	createRecode: function(item) {
		var tr = document.createElement('tr')
		tr.innerHTML = `
			<td>${item.user_id}</td>
			<td>${item.nm}</td>
			<td>${item.rating}</td>`
		return tr
	},	
}

if (readyRoom) {
	// ajax pooling
	readRoom_ajax()
	var readRoom_ajax_inter = setInterval(readRoom_ajax, ajax_polling_time); // 게임 시작하면 중지처리 해야함.
}
function readRoom_ajax() {
	if (switch_readRoom_ajax) {
		fetch(`/play/selectedPlayer?room_code=${room_code}`, {
			method: 'GET',
		}).then(res => res.json())
		.then(myJson => {
			console.log('readyRoom_ajax polling...')
			if (myJson.selected_black === null) {
				btn_selectBlack.innerHTML = 'selected'
				is_selected_black = false
			} else {
				if (id === myJson.selected_black) {
					btn_selectBlack.innerHTML = myJson.selected_black 
					+ `  <button onclick="outSelect('black');">x</button>`
				} else {
					btn_selectBlack.innerHTML = myJson.selected_black 
				}
				black_player = myJson.selected_black
				is_selected_black = true
			}
			
			if (myJson.selected_white === null) {
				btn_selectWhite.innerHTML = 'selected'
				is_selected_white = false
			} else {
				if (id === myJson.selected_white) {
					btn_selectWhite.innerHTML = myJson.selected_white
					+ `  <button onclick="outSelect('white');">x</button>`
				} else {
					btn_selectWhite.innerHTML = myJson.selected_white
				}
				white_player = myJson.selected_white
				is_selected_white = true
			}
		})			
	} else {
		//console.log('stop readyRoom_ajax polling')
		if (!is_game_start) {
			switch_readRoom_ajax = true	// 확인 사살
		} else {
			is_game_start = true	// 확인 사살	
		}
	}
	
	
	// 게임 start 처리
	if (is_selected_black && is_selected_white) {

		// game start
		is_game_start = true;
		// stop_readyroom_ajax_polling
		switch_readRoom_ajax = false;
		
	} else {
		is_game_start = false;
	}
	
}




// 선택 해제 처리
function outSelect(select) {
	if (switch_outSelect) {
		var param = {
			user_id: id,
			room_code: room_code
		}

		fetch(`/play/selBackPlay${select}`, {
			method: 'POST',
			headers: {
            	'Content-Type': 'application/json'
			},
			body: JSON.stringify(param)
		}).then(function(res) {
			return res.json()
		}).then(function(data) {
			console.log(data)
		})		
	} else {
		modalDisplay('게임 중.. 퇴장불가',1)
	}
}

// 흑백 선택 버튼 클릭
btn_selectBlack.onclick = function () {
	if (!is_selected_black) {
		select_play_ajax('black')
	}
}
btn_selectWhite.onclick = function () {
	if (!is_selected_white) {
		select_play_ajax('white')		
	}
}
// 흑백 선택 ajax
function select_play_ajax(select) {
		
		var param = {
			user_id: id,
			room_code: room_code
		}
			
		fetch(`/play/selectPlay${select}`, {
			method: 'POST',
			headers: {
            	'Content-Type': 'application/json'
			},
			body: JSON.stringify(param)
		}).then(function(res) {
			return res.json()
		}).then(function(data) {
			if (data.result === 1) {
				if(select === 'black') {
					modalDisplay(`흑돌을 선택하셨습니다(선공)`, 1)
					
				} else {
					modalDisplay(`백돌을 선택하셨습니다(후공)`, 1)
				}
			} else if (data.result === 0) {
				modalDisplay(`대기실 이동 후 변경하세요`, 1)
			} 
		})	
}

// 게임 기권
var btn_absGame = document.querySelector('#absGame')
btn_absGame.onclick = function () {
	if(is_game_start) {
		var param = {
			user_id: id,
			black_player:black_player,
			white_player:white_player,
			room_code: room_code
		}
			
		fetch('/play/absPlay', {
			method: 'POST',
			headers: {
            	'Content-Type': 'application/json'
			},
			body: JSON.stringify(param)
		}).then(function(res) {
			return res.json()
		}).then(function(data) {
			if (data.result === 1) {
				switch_readRoom_ajax = true
				is_game_start = false
				modalDisplay('기권하셨습니다',1)
				pageReload(1)
			} 
		})	
	} else {
		modalDisplay(`기권할 수 없습니다`, 1)
	}
}


// 모달창 열기 닫기
function openCloseModal(state) {
	var modalWrapElem = document.querySelector('.modal_wrap')
	var blackBgElem = document.querySelector('.black_bg')	
	modalWrapElem.style.display = state
	blackBgElem.style.display = state
}
// 모달창 Cont참조  id : modalCont
var modalCont = document.querySelector('#modalCont')
var modalMsg = document.querySelector('#modalMsg')
modalMsg.innerHTML = 'testtest'
// 모달창 내용 적기 및 뿌리는 시간 함수
function modalDisplay (text, time) {
	modalMsg.innerHTML = text
	openCloseModal('block')
	setTimeout(function() {
		openCloseModal('none')
	}, time * modal_time) 
}

// 페이지 리로드
function pageReload(time) {
	setTimeout(function(){
		location.reload() // 페이지 리로드
	}, time * reload_time) 
}


// date-format
Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};














