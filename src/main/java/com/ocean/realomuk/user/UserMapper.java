package com.ocean.realomuk.user;

import org.apache.ibatis.annotations.Mapper;

import com.ocean.realomuk.model.RoomDTO;
import com.ocean.realomuk.model.UserDTO;
import com.ocean.realomuk.model.UserDomain;
import com.ocean.realomuk.model.UserEntity;

@Mapper
public interface UserMapper {
	
	UserDomain selUser(UserDTO dto);
	UserDomain selFindId(UserDTO dto);
	int insUser(UserDTO dto);
	
	// room_code proc
	int insRoomCode(RoomDTO dto);
	int delRoomCode(UserEntity dto);
	UserDomain selRoomCode(UserEntity dto);
	
}
