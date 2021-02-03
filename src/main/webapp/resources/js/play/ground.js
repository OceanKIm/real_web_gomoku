// 메인 프레임
var frame = document.querySelector('#frame');

// 바둑판 구조 잡기
var table = document.createElement('table');
for (let i = 1; i <= 15; i++) {
	var tr = document.createElement('tr');
	tr.id = `line_${i}`;
	for (let j = 1; j <= 15; j++) {
		var td = document.createElement('td');
		td.onclick = click;
		td.innerHTML = ''
		td.id = `${i}_${j}`;
		tr.append(td);
	}
	table.append(tr);
}
frame.append(table);

// 클릭 리스너 // black : 0, white : 1
var toggle = 0;
function click(item) {
	// 기본 두기 
	if (toggle) {
		item.target.style.backgroundImage = "url('/res/img/black_dol.png')";
	} else {
		item.target.style.backgroundImage = "url('/res/img/white_dol.png')";
	}
	toggle = 1 - toggle;
}


// -- lastCont
var btn_watingRoom = document.querySelector('#btn_watingRoom')
var btn_chattingRoom = document.querySelector('#btn_chattingRoom')

var watingRoom = document.querySelector('#watingRoom')
var chattingRoom = document.querySelector('#chattingRoom')

btn_watingRoom.onclick = function () {
	console.log('select waiting room')
	watingRoom.style.display = 'block'
	chattingRoom.style.display = 'none'
}
btn_chattingRoom.onclick = function () {
	console.log('select chatting room')	
	watingRoom.style.display = 'none'
	chattingRoom.style.display = 'block'
}










