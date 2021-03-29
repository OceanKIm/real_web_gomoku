package com.ocean.realomuk.play;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ocean.realomuk.model.Pos;
import com.ocean.realomuk.model.RoomDTO;
import com.ocean.realomuk.model.RoomDomain;
import com.ocean.realomuk.model.UserDomain;

@Mapper
public interface PlayMapper {

	int createRoom(RoomDTO dto);
	List<RoomDomain> selRoomList();
	List<UserDomain> selWaitList(RoomDTO dto);
	
	// 흑백 선택 ajax
	RoomDomain selSeletedPlayer(RoomDTO dto);
	int setBlackPlayer(RoomDTO dto);
	int setWhitePlayer(RoomDTO dto);
	int setBlackNull(RoomDTO dto);
	int setWhiteNull(RoomDTO dto);
	
	// is_play 처리
	int setPlay(RoomDTO dto);
	int relPlay(RoomDTO dto);
	UserDomain isPlay(RoomDTO dto);
	
	// pos proc
	int startPos(RoomDTO dto);
	int putPos(Pos pos);
	List<Pos> selPos(RoomDTO dto);
	Pos isPos(Pos pos);
	int delPos(RoomDTO dto);
	int delLastPos(RoomDTO dto);
	
	// finish game proc
	int winProc(RoomDTO dto);
	int	loseProc(RoomDTO dto);
	
	// 기권체크
	int setAbs(RoomDTO dto);
	int relAbs(RoomDTO dto);
	RoomDTO chkAbs(RoomDTO dto);
	
}
