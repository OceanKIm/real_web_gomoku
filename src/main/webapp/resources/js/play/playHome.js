var ajax_polling_time = 1000

var btn_createRoom = document.querySelector('#btn_createRoom')
var data_user = document.querySelector('#data_user')
var data_guest = document.querySelector('#data_guest')

// 방 생성 기본 변수
var id;
var room_code;
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

// 방 생성 버튼 클릭.
btn_createRoom.onclick = function () {
	console.log('create room')
	createRoom_ajax()
}

////// ajax //////
function createRoom_ajax() {
	axios({
		method: 'post',
		url: '/play/createRoom',
		data: {
			room_host:id
		}
	})
	.then(function (res) {
		console.log(res);
		if (res.data.result > 0) {
			enterRoom(res.data.i_room, res.data.room_code)
		} else {
			alert('에러 : 방 생성 실패')
		}
	})	
}

// 룸코드 존재시 바로 대기실행
if (room_code !== '') {
	console.log('go to room..')
	// todo
}


// ajax-polling 방 리스트 뿌려주기
var roomListCont = document.querySelector('#roomListCont')
if (roomListCont) {
	roomList_ajax()
	var roomList_ajax_inter = setInterval(roomList_ajax, ajax_polling_time);
}

function roomList_ajax() {
	fetch('/play/roomList', {
		method: 'GET',
	}).then(res => res.json())
	.then(myJson => {
		console.log('roomList_ajax polling...')
		roomListCont.innerHTML = ''
		roomListObj.proc(myJson)
	})
}


// 테이블 만들어주는 객체
var roomListObj = {
	createRoomTable: function() {
		var tableElem = document.createElement('table')
		tableElem.innerHTML = 
		`<tr>
			<th>방번호</th>
			<th>방코드</th>
			<th>HOST</th>
			<th>생성시간</th>
		</tr>`			
		return tableElem
	},
	
	proc: function(list) {
		if(list.length == 0) {	
			roomListCont.innerHTML = '<h3>생성된 방 없음</h3>'	
			return
		}
		var table = this.createRoomTable()
		for(var i = 0; i < list.length; i++) {
			var recode = this.createRecode(list[i])
			table.append(recode)
		}		
		roomListCont.append(table)
	},
	
	createRecode: function(item) {
		var tr = document.createElement('tr')
		tr.onclick = function () {
			enterRoom(item.i_room, item.room_code)
		}
		tr.innerHTML = `
			<td>#${item.i_room}</td>
			<td>${item.room_code}</td>
			<td>${item.room_host}</td>
			<td>${item.r_dt}</td>`
		return tr
	},	
}

// 방 입장
function enterRoom(i_room, room_code) {
	console.log('enterRoom.. ' + room_code)
	window.open(`/ground?i_room=${i_room}&room_code=${room_code}`, 'newWindow');
}

// 간단한 팝업창 띄우기
function popup(){
	var url = "popup.html";
	var name = "popup test";
    var option = "width = 500, height = 500, top = 100, left = 200, location = no"
    window.open(url, name, option);
}










