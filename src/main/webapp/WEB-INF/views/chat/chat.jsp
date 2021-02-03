<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>간단한채팅</title>
</head>
<body>
    <div>
        <h3>간단한 채팅 with ajax polling</h3>
    </div>
    <div>
        <h4>(사용자 : ${userInfo})</h4>
    </div>
    <input onclick="start();" type="button" value="채팅시작">
    <input onclick="reset();" type="button" value="리셋">
    <div>
        <input id="text" type="text" name="msg">
		<input onclick="sendMsg(`${userInfo}`);" type="button" value="send">
    </div>
    <div id="chatArea">
        <div>채팅창</div>
        <table id="chatTable">
        	<c:forEach items="${data}" var="item">
        	<tr>
        		<th>${item.i_user} : </th>
        		<th>${item.ctnt} </th>
        	</tr>
        	</c:forEach>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
        var chatArea = document.querySelector('#chatArea');
		var text = document.querySelector("#text");
        var chatTable = document.querySelector("#chatTable");
        
        // 테이블 생성
        function sendMsg(userInfo) { 
            axios.get('/chat/chatProc', {
        		params: {	
        			'i_user':userInfo,
        			'ctnt':text.value		
        		}
        	   })
        	   .then(function (res) {
        	     console.log(res);
                    var tr = document.createElement('tr');
                   	tr.innerHTML = '<th>${userInfo} : </th> <th> ' + res.data.ctnt + '</th>';
					chatTable.appendChild(tr);
	       	   });
        }
        
        function reset() {
            axios.get('/chat/chatReset', {
        	   })
        	   .then(function (res) {
        	     console.log(res);
                 chatTable.innerHTML = '';
	       	   });
        }
        
        function start() {
        	setInterval(ajaxInter, 500);
        }
        
        function ajaxInter(){
        	console.log('start');
            axios.get('/chat/chatPolling', {
     	  	})
     	  	.then(function (res) {
	       	    console.log(res);
	       	 	chatTable.innerHTML = '';
				for (var i = 0; i < Object.keys(res.data).length; i++) {
		       	  	console.log(res.data[i].ctnt);
		       	  	var tr = document.createElement('tr');
                   	tr.innerHTML = '<th>' + res.data[i].i_user + ' : </th> <th> ' + res.data[i].ctnt + '</th>';
					chatTable.appendChild(tr);
				}
       	     });
        }
       </script>
</body>
</html>
















