package com.ocean.realomuk.play.chat;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocean.realomuk.model.PlayChatDTO;
import com.ocean.realomuk.model.PlayChatDomain;

@Service
public class PlayChatService {
	
	@Autowired
	private PlayChatMapper mapper;
	
	int insText(PlayChatDTO dto) {
		return mapper.insText(dto);
	}
	
	List<PlayChatDomain> selText() {
		List<PlayChatDomain> list = mapper.selText();
		Collections.reverse(list);	// 역순 정렬
		return list;
	}
	
	
}
