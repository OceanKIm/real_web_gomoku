<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Play Ground</title>
	<link rel= "stylesheet" type="text/css" href="/resources/css/playground.css">
</head>
<body>
    <h3>혼자하는 오목 게임</h3>
    <button id='clear'>clear</button>
    <button id='showArr'>showArr</button>
    <div id='frame' value='frame'>
        <img id='omuktable' src="/resources/img/omuktable.jpg"> 
    </div>
    
    <script>
        // 메인 프레임
        var frame = document.querySelector('#frame');

        // 클리어 버튼
        var btn_clear = document.querySelector('#clear');
        btn_clear.onclick = () => {
            var clearArr = document.querySelectorAll('td');
            clearArr.forEach((item)=>{
                item.style.background = '';
            });
        }

        // 임시 arr 버튼
        var showArr = document.querySelector('#showArr');


        var table = document.createElement('table');
        for (let i = 1; i < 20; i++) {
            var tr = document.createElement('tr');
            tr.id = `line_${i}`;
            for (let j = 1; j < 20; j++) {
                var td = document.createElement('td');
                td.onclick = click;
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
                item.target.style.backgroundImage = "url('/resources/img/black_dol.png')";
            } else {
                item.target.style.backgroundImage = "url('/resources/img/white_dol.png')";
            }
            toggle = 1 - toggle;
        }
    </script>
</body>
</html>