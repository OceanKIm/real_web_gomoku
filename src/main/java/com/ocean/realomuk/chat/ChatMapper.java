package com.ocean.realomuk.chat;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ocean.realomuk.model.ChatDTO;
import com.ocean.realomuk.model.ChatDomain;

@Mapper
public interface ChatMapper {
	int insChat(ChatDTO dto);
	List<ChatDomain> selChatList();
	int delChatList();
}
