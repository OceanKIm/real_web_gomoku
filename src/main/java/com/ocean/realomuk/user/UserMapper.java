package com.ocean.realomuk.user;

import org.apache.ibatis.annotations.Mapper;

import com.ocean.realomuk.model.UserDTO;
import com.ocean.realomuk.model.UserDomain;

@Mapper
public interface UserMapper {
	
	UserDomain selUser(UserDTO dto);
	UserDomain selFindId(UserDTO dto);
	int insUser(UserDTO dto);

}
