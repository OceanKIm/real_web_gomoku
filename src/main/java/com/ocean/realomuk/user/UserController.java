package com.ocean.realomuk.user;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ocean.realomuk.common.Const;
import com.ocean.realomuk.model.UserDTO;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@ResponseBody
	@PostMapping("/login")
	public HashMap<String, Integer>loginProc(HttpSession hs,@RequestBody UserDTO dto) {
		// 1: 로그인 성공, 2: 비밀번호 틀림, 3: 아이디 없음.
		int result = service.selUser(hs, dto);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put(Const.KEY_RESULT, result);
		return map;
	}
	
	
	@ResponseBody
	@PostMapping("/join")
	public HashMap<String, Integer>joinProc(@RequestBody UserDTO dto){
		int result = service.insUser(dto);		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put(Const.KEY_RESULT, result);
		return map;
	}
	
	@ResponseBody
	@GetMapping("/logout")
	public HashMap<String, Integer>logoutProc(HttpSession hs){
		hs.invalidate();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put(Const.KEY_RESULT, 1);
		return map;
	}
	
	// ---------------- 아이디 비밀번호 찾기
	@ResponseBody
	@PostMapping("/findId")
	public HashMap<String, Integer>findIdProc(@RequestBody UserDTO dto){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put(Const.KEY_RESULT, service.findId(dto));
		return map;
	}
	
	
	
}
















