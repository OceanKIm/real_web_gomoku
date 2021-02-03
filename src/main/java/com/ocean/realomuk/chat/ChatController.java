package com.ocean.realomuk.chat;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ocean.realomuk.model.ChatDTO;
import com.ocean.realomuk.model.ChatDomain;


@Controller
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	private ChatMapper mapper;
	
	@GetMapping("/chat")
	public void MainChat(Model model, HttpSession hs, HttpServletRequest request) {
		model.addAttribute("userInfo", request.getRemoteAddr());
		model.addAttribute("data", mapper.selChatList());
	}
	
	@ResponseBody
	@GetMapping("/chatPolling")
	public HashMap<Integer, ChatDomain> chatPolling() {
		// 데이터 가져옴
		List<ChatDomain> list = mapper.selChatList();
		

		HashMap<Integer, ChatDomain> map = new HashMap<Integer, ChatDomain>();
		int index = 0;
		for (ChatDomain vo : list) {
			map.put(index++, vo);
		}
		return map;
	}

	@ResponseBody 
	@GetMapping("chatReset")
	public HashMap<String, String> chatReset() {
		mapper.delChatList();
		HashMap<String, String> map = new HashMap<String, String>();
		return map;
	}
	
	
	@ResponseBody 
	@GetMapping("/chatProc")
	public HashMap<String, String> chatProc(ChatDTO dto) {
		System.out.println("ajax 통신 성공");
		mapper.insChat(dto);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("ctnt", dto.getCtnt());
		return map;
	}
	
}
