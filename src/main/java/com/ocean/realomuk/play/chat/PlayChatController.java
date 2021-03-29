package com.ocean.realomuk.play.chat;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ocean.realomuk.common.Const;
import com.ocean.realomuk.model.PlayChatDTO;
import com.ocean.realomuk.model.PlayChatDomain;

@Controller
public class PlayChatController {

	@Autowired
	private PlayChatService service;
	
	@ResponseBody
	@PostMapping("/play/chat/send")					
	public HashMap<String, Object> sendText(@RequestBody PlayChatDTO dto){
		System.out.println("id : " + dto.getUser_id());
		System.out.println("ctnt : " + dto.getCtnt());
		System.out.println("room_code : " + dto.getRoom_code());
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(Const.KEY_RESULT, service.insText(dto));
		return map;
	}
	
	@ResponseBody
	@GetMapping("/play/chat/sel")
	public List<PlayChatDomain> selText(){
		return service.selText();
	}	
	
	
	
	
}
