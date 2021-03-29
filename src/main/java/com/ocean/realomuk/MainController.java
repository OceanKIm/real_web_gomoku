package com.ocean.realomuk;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ocean.realomuk.common.Const;
import com.ocean.realomuk.common.SecurityUtils;
import com.ocean.realomuk.model.GuestDTO;
import com.ocean.realomuk.model.RoomDTO;
import com.ocean.realomuk.model.UserEntity;
import com.ocean.realomuk.user.UserService;

@Controller
public class MainController {

	// room_code 삽입을 위한 service
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String main() {
		return null;
	}
	
	@GetMapping("/main/home")
	public void home() {
		if (!Const.startServer) {
			System.out.println("----------------- ### start server ### -----------------");
			Const.startServer = true;
		}
	}

	@GetMapping("/play/playHome")
	public void playHome(Model model, HttpSession hs) {
		try {
			String room_code;
			if (hs.getAttribute("guest") != null) {
				room_code = ((GuestDTO)hs.getAttribute("guest")).getRoom_code();
			} else {
				room_code = userService.selRoomCode(SecurityUtils.getLoginUser(hs)).getRoom_code();
			}
			System.out.println("has room_code : " + room_code);
			// 이미 룸코드를 가지고 있다면 해당 게임룸으로 재입장.
			model.addAttribute("hasRoomCode", room_code);
		} catch (Exception e) {
			System.out.println(" -- 비 로그인 사용자 접근  --");
			return;
		}
	}

	@GetMapping("/ground")
	public void playGround(Model model, HttpSession hs, RoomDTO dto) {
		System.out.println("go to ground");
		UserEntity loginUser = SecurityUtils.getLoginUser(hs);
		dto.setI_user(SecurityUtils.getLoginUserPk(hs));
		if (loginUser != null) {
			//유저 룸코드 삽입.
			userService.insRoomCode(dto);
			
			// 세션 유저 room_code 정보 갱신.
			loginUser.setRoom_code(dto.getRoom_code());
			hs.setAttribute("loginUser", loginUser);
		}
		GuestDTO guest = SecurityUtils.getGuestUser(hs);
		if (guest != null) {
			// 게스트 룸코드 삽입.
			if (guest.getRoom_code() == null) {
				guest.setRoom_code(dto.getRoom_code());	
			}
		}
		//model.addAttribute("room", dto);
	}
}






























