package com.ocean.realomuk.play.chat;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ocean.realomuk.model.PlayChatDTO;
import com.ocean.realomuk.model.PlayChatDomain;

@Mapper
public interface PlayChatMapper {
	
	int insText(PlayChatDTO dto);
	List<PlayChatDomain> selText();
	
}
