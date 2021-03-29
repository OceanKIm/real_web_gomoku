package com.ocean.realomuk.play;

import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ocean.realomuk.common.Const;
import com.ocean.realomuk.common.SecurityUtils;
import com.ocean.realomuk.model.GuestDTO;
import com.ocean.realomuk.model.Pos;
import com.ocean.realomuk.model.RoomDTO;
import com.ocean.realomuk.model.RoomDomain;
import com.ocean.realomuk.model.UserDomain;
import com.ocean.realomuk.model.UserEntity;
import com.ocean.realomuk.user.UserService;

@Controller
@RequestMapping("/play")
public class PlayController {

	@Autowired
	private PlayService service;
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@PostMapping("/createRoom")					
	public HashMap<String, Object>createRoom(@RequestBody RoomDTO dto){	
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (dto.getRoom_host().equals("")) {
			System.out.println("방 생성 실패. 에러처리");
			map.put(Const.KEY_RESULT, 0);
			return map;
		}
		
		// room_code 삽입.
		String room_code = SecurityUtils.getRoomCode(6);
		dto.setRoom_code(room_code);
		
		System.out.println("host_id : " + dto.getRoom_host());
		System.out.println("room_code : " + room_code);
		
		map.put(Const.KEY_RESULT, service.createRoom(dto));
		map.put(Const.KEY_ROOM_CODE, room_code);
		map.put(Const.KEY_I_ROOM, dto.getI_room());	
		return map;
	}
	
	// 리스트 뿌려주기
	@ResponseBody
	@GetMapping("/roomList")
	public List<RoomDomain> selRoomList() {
		return service.selRoomList();
	}
	
	@ResponseBody
	@GetMapping("/isStart")
	public HashMap<String, Integer> isStart(RoomDTO dto) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		List<Pos> list = service.selPos(dto);
		if (list.size() == 0) {
			//service.startPos(dto);
			map.put(Const.KEY_RESULT, 1);
		}
		return map;
	}
		
	// 방나가기
	@ResponseBody
	@GetMapping("/roomOut")
	public HashMap<String, Integer> roomOut(HttpSession hs) {
		int result = 1;	// default : 1
		System.out.println("roomOut");
		UserEntity loginUser = SecurityUtils.getLoginUser(hs);
		if (loginUser != null) {
			//유저 룸코드 삭제
			result = userService.delRoomCode(loginUser);
			// is play null
			RoomDTO dto = new RoomDTO();
			dto.setUser_id(loginUser.getUser_id());
			service.relPlay(dto);
		}
		GuestDTO guest = SecurityUtils.getGuestUser(hs);
		if (guest != null) {
			// 게스트 룸코드 삭제.
			guest.setRoom_code(null);
		}		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put(Const.KEY_RESULT, result);
		return map;
	}
	
	// 방 대기자 리스트 뿌려주기
	@ResponseBody
	@GetMapping("/waitList")
	public List<UserDomain> selWaitList(RoomDTO dto) {
		
		return service.selWaitList(dto);
	}	
	
	// 흑백 선택 ajax 처리 with pooling
	@ResponseBody
	@GetMapping("/selectedPlayer")
	public HashMap<String, String> selectedPlayer(RoomDTO dto){
		RoomDomain vo = service.selSeletedPlayer(dto);
		HashMap<String, String> map = new HashMap<String, String>();
		if (vo == null) {
			map.put("selected_black", null);
			map.put("selected_white", null);
			return map;
		}
		map.put("selected_black", vo.getBlack_player());
		map.put("selected_white", vo.getWhite_player());
		return map;
	}
	// 흑선택	
	@ResponseBody
	@PostMapping("/selectPlayblack")					
	public HashMap<String, Object>selectBlack(@RequestBody RoomDTO dto){	
		HashMap<String, Object> map = new HashMap<String, Object>();
		service.delPos(dto); // 여기서 다 삭제.....
		// 선택 플레이어 is_play 처리
		if(service.isPlay(dto).getIs_play() == 1) {
			map.put(Const.KEY_RESULT, 0);
			return map;
		}
		service.setPlay(dto);
		map.put(Const.KEY_RESULT, service.setBlackPlayer(dto));
		return map;
	}
	// 백선택
	@ResponseBody
	@PostMapping("/selectPlaywhite")					
	public HashMap<String, Object>selectWhite(@RequestBody RoomDTO dto){			
		HashMap<String, Object> map = new HashMap<String, Object>();
		service.delPos(dto); // 여기서 다 삭제.....
		// 선택 플레이어 is_play 처리
		if(service.isPlay(dto).getIs_play() == 1) {
			map.put(Const.KEY_RESULT, 0);
			return map;
		}
		service.setPlay(dto);
		map.put(Const.KEY_RESULT, service.setWhitePlayer(dto));		
		return map;
	}
	// 흑해제
	@ResponseBody
	@PostMapping("/selBackPlayblack")					
	public HashMap<String, Object>selBackBlack(@RequestBody RoomDTO dto){	
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 선택 플레이어 is_play 처리
		service.relPlay(dto);
		map.put(Const.KEY_RESULT, service.setBlackNull(dto));
		return map;
	}	
	
	// 백해제
	@ResponseBody
	@PostMapping("/selBackPlaywhite")					
	public HashMap<String, Object>selBackWhite(@RequestBody RoomDTO dto){	
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 선택 플레이어 is_play 처리
		service.relPlay(dto);
		map.put(Const.KEY_RESULT, service.setWhiteNull(dto));
		return map;
	}	
	
	// 기권
	@ResponseBody
	@PostMapping("/absPlay")					
	public HashMap<String, Object>absPlay(@RequestBody RoomDTO dto){	
		HashMap<String, Object> map = new HashMap<String, Object>();
		// delPos
		service.delPos(dto);
		if (dto.getUser_id().equals(dto.getBlack_player())) {
			// 패배처리 proc
			service.loseProc(dto);
			service.relPlay(dto);
			// 기권설정
			service.setAbs(dto);
			map.put(Const.KEY_RESULT, service.setBlackNull(dto));
		} else {
			// :todo 패배처리 proc
			service.loseProc(dto);
			service.relPlay(dto);
			// 기권설정
			service.setAbs(dto);			
			map.put(Const.KEY_RESULT, service.setWhiteNull(dto));
		}	
		return map;
	}		
	
	// 바둑돌 두기 이벤트
	@ResponseBody
	@PostMapping("/putPos")					
	public HashMap<String, Object>putPos(@RequestBody RoomDTO dto){	
		HashMap<String, Object> map = new HashMap<String, Object>();
		StringTokenizer stn = new StringTokenizer(dto.getPos_id(), "_");		
		int pos_x = Integer.parseInt(stn.nextToken());
		int pos_y = Integer.parseInt(stn.nextToken());

		// 중복두기 방지 - more
		if(service.isPos(new Pos(pos_x, pos_y, 0, dto.getRoom_code())) != null) {
			map.put(Const.KEY_RESULT, 3);
			return map;
		}		
		if (dto.getUser_id().equals(dto.getBlack_player())) {
			
			// 렌주룰 검증
			if (service.check_renju(dto, pos_x, pos_y) == 0) {
				System.out.println("렌주룰 걸림!!!!!!!!!!");
				map.put(Const.KEY_RESULT, 4);
				return map;
			}
			
			// 중복두기 방지
			if(dto.getTurn() == 1) {
				map.put(Const.KEY_RESULT, 0);
				return map;
			}
			Pos pos = new Pos(pos_x, pos_y, 1, dto.getRoom_code());
			service.putPos(pos);
			map.put(Const.KEY_RESULT, 1);
			return map;
		} else if (dto.getUser_id().equals(dto.getWhite_player())) {
			// 중복두기 방지
			if(dto.getTurn() == 2) {			
				map.put(Const.KEY_RESULT, 0);
				return map;
			}			
			Pos pos = new Pos(pos_x, pos_y, 2, dto.getRoom_code());		
			service.putPos(pos);
			map.put(Const.KEY_RESULT, 2);
			return map;
		} else {
			// 비 플레이어
			map.put(Const.KEY_RESULT, 0);
			return map;
		}
	}			
	
	// 바둑돌들 화면에 뿌리기. with 승부 체크 and 기권 체크
	@ResponseBody
	@GetMapping("/selPos")
	public List<Pos> selPos(RoomDTO dto) {
		return service.selPos(dto);
	}	
	
	
	// 게임종료및 결과 proc
	@ResponseBody
	@PostMapping("/isFinish")					
	public HashMap<String, Object>isFinish(@RequestBody RoomDTO dto){	

		HashMap<String, Object> map = new HashMap<String, Object>();
		// delPos
		
		if (dto.getUser_id().equals(dto.getBlack_player())) {
			// proc
			if (dto.getMsg().equals("blackWin")) {
				service.winProc(dto);
			} else {
				service.loseProc(dto);
			}
			service.relPlay(dto);
			service.setBlackNull(dto);
			map.put(Const.KEY_RESULT, 1);
		} 
		if (dto.getUser_id().equals(dto.getWhite_player())) {
			// proc
			if (dto.getMsg().equals("whiteWin")) {
				service.winProc(dto);
			} else {
				service.loseProc(dto);
			}	
			service.relPlay(dto);
			service.setWhiteNull(dto);
			map.put(Const.KEY_RESULT, 1);
		}	
		return map;
	}
	
	
}















