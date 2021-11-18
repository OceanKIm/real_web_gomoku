
// 영남대학교 컴퓨터공학과 졸업작품 <리얼오목> 입니다.   
// 웹에서 실시간으로 상대와 오목대전을 펼 칠 수 있습니다.   

★사용기술
-	Java
-	Http, css, Java script
-	spring MVC
-	Jstl
-	Mysql
-	Spring JDBC
-	HikarICP (connection pool) 
-	Mybatis
-	Jdcrypt 
-	ajax (fetch, axios 두개 혼합 사용)
-	Jackson
-	Tile3
-	Java mail 
-	jquery
-	Logging
-	Ajax polling or longPolling 

★기능구현   
  ○ UserController   
     - login : 이미 가입된 회원 인증 및 권한 할당   
     - logout : 로그인 정보 세션 삭제   
     - join : 회원가입, create user table colunm   
     - mod account : 회원 정보 수정, update user table colunm   
     - find id : 아이디 찾기, send mail for find id   
     - find pw : 비밀번호 찾기, send mail for find pw   
     - guest : 게스트 모드

  ○ PlayController   
    - create room : 플레이 룸 생성   
     - sel room list : 실시간으로 플레이 룸 목록 list 보여주기   
     - room out : 방 나가기   
     - sel wait list : 실시간으로 방 안에서 대기실 유저 리스트 보여주기   
     - select black : 흑돌 선택 및 게임 준비   
     - select white : 백돌 선택 및 게임 준비   
     - start game : 게임 시작하기   
     - abs play : 게임 기권   
     - put pos : 해당 좌표에 오목돌 두기   
     - sel pos : 전체 오목판에 놓여진 돌 보여주기   
     - pooling check : 게임 승리 또는 패배 체크   
     - isfinish : 약 0.5 초 간의 pooling 방식으로 게임 진행 체크   
     - chatting : 채팅 기능    

  ○ MailUtils   
     - send mail : 메일 보내기   
     - set mail server : 메일 서버 설정   

  ○ Security Util   
     - isLogin : 로그인 세션 체크   
     - isGuest : 게스트 세션 체크   
     - getLoginUser : 세션의 유저 domain 객체 가져오기   
     - getLoginUserPk : 세션의 유저 PK값 가져오기   
     - getSalt : Salt 값 생성   
     - hashPW : 사용자 pw와 salt 값 해싱 암호화   
     - getRoomCode : 랜덤한 방코드 생성   
     - getGuestID : 랜덤한 게스트 코드 생성   

  ○ PlayUtil   
    - nomal Rule : 일반 오목 알고리즘으로 게임진행체크   
    - RenJu Rule : 렌주룰 오목 알고리즘으로 게임진행체크   

★DataBaseModel   
  // t_user   
    CREATE TABLE `t_user` (   
      `i_user` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,   
      `user_id` VARCHAR(30) NOT NULL COMMENT '유저 아이디' COLLATE 'utf8_general_ci',   
      `user_pw` VARCHAR(150) NOT NULL COMMENT '유저 비밀번호' COLLATE 'utf8_general_ci',   
      `salt` VARCHAR(100) NOT NULL COLLATE 'utf8_general_ci',   
      `nm` VARCHAR(10) NOT NULL COLLATE 'utf8_general_ci',   
      `gender` INT(1) UNSIGNED NULL DEFAULT NULL,   
      `e_mail` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',   
      `profile_img` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',   
      `r_dt` DATETIME NULL DEFAULT current_timestamp(),   
      `rating` INT(10) UNSIGNED NULL DEFAULT '1000',   
      `win_cnt` INT(10) UNSIGNED NULL DEFAULT '0',   
      `lose_cnt` INT(10) UNSIGNED NULL DEFAULT '0',   
      `draw_cnt` INT(10) UNSIGNED NULL DEFAULT '0',   
      `ranking` INT(10) UNSIGNED NULL DEFAULT NULL,   
      `room_code` VARCHAR(10) NULL DEFAULT NULL COLLATE 'utf8_general_ci',   
      `is_login` INT(1) UNSIGNED NULL DEFAULT '0',   
      `is_play` INT(1) UNSIGNED NULL DEFAULT '0',   
      PRIMARY KEY (`i_user`) USING BTREE,   
      UNIQUE INDEX `user_id` (`user_id`) USING BTREE   
    )   
    COLLATE='utf8_general_ci'   
    ENGINE=InnoDB   
    AUTO_INCREMENT=9   
    ;   

  // t_room   
    CREATE TABLE `t_room` (   
      `i_room` INT(10) NOT NULL AUTO_INCREMENT,   
      `room_code` VARCHAR(10) NOT NULL COLLATE 'utf8_general_ci',   
      `black_player` VARCHAR(30) NULL DEFAULT NULL COLLATE 'utf8_general_ci',   
      `white_player` VARCHAR(30) NULL DEFAULT NULL COLLATE 'utf8_general_ci',   
      `r_dt` VARCHAR(30) NULL DEFAULT current_timestamp() COLLATE 'utf8_general_ci',   
      `room_host` VARCHAR(30) NOT NULL COLLATE 'utf8_general_ci',   
      `is_abs` INT(1) NULL DEFAULT NULL,   
      PRIMARY KEY (`i_room`) USING BTREE   
    )   
    COLLATE='utf8_general_ci'   
    ENGINE=InnoDB   
    AUTO_INCREMENT=65   
    ;   

  // t_pos   
    CREATE TABLE `t_pos` (   
      `i_room` INT(10) NOT NULL AUTO_INCREMENT,   
      `x` INT(11) NOT NULL,   
      `y` INT(11) NOT NULL,   
      `z` INT(11) NOT NULL,   
      `room_code` VARCHAR(10) NOT NULL COLLATE 'utf8_general_ci',   
      `p_time` INT(11) NULL DEFAULT NULL,   
      PRIMARY KEY (`i_room`) USING BTREE   
    )   
    COLLATE='utf8_general_ci'   
    ENGINE=InnoDB   
    AUTO_INCREMENT=1585   
    ;   

  // t_play_chat   
    CREATE TABLE `t_play_chat` (   
      `i_chat` INT(11) NOT NULL AUTO_INCREMENT,   
      `user_id` VARCHAR(20) NOT NULL COLLATE 'utf8_general_ci',   
      `ctnt` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8_general_ci',   
      `r_dt` DATETIME NULL DEFAULT current_timestamp(),   
      `room_code` VARCHAR(20) NOT NULL COLLATE 'utf8_general_ci',   
      PRIMARY KEY (`i_chat`) USING BTREE   
    )   
    COLLATE='utf8_general_ci'   
    ENGINE=InnoDB   
    AUTO_INCREMENT=93   
    ;   




