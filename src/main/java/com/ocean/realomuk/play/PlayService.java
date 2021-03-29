package com.ocean.realomuk.play;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocean.realomuk.common.PlayUtils;
import com.ocean.realomuk.model.Pos;
import com.ocean.realomuk.model.RoomDTO;
import com.ocean.realomuk.model.RoomDomain;
import com.ocean.realomuk.model.UserDomain;

@Service
public class PlayService {
	
	@Autowired
	private PlayMapper mapper;
	
	public int createRoom(RoomDTO dto) {
		return mapper.createRoom(dto);
	}
	
	public List<RoomDomain> selRoomList() {
		return mapper.selRoomList();
	}

	public List<UserDomain> selWaitList(RoomDTO dto) {
		return mapper.selWaitList(dto);
	}
	
	// 흑백 선택 ajax
	public RoomDomain selSeletedPlayer(RoomDTO dto) {
		return mapper.selSeletedPlayer(dto);
	}
	int setBlackPlayer(RoomDTO dto) {
		return mapper.setBlackPlayer(dto);
	}
	int setWhitePlayer(RoomDTO dto) {
		return mapper.setWhitePlayer(dto);
	}
	int setBlackNull(RoomDTO dto) {
		return mapper.setBlackNull(dto);
	}
	int setWhiteNull(RoomDTO dto) {
		return mapper.setWhiteNull(dto);
	}
	
	// is_play 처리
	int setPlay(RoomDTO dto) {
		return mapper.setPlay(dto);
	}
	int relPlay(RoomDTO dto) {		
		return mapper.relPlay(dto);
	}
	UserDomain isPlay(RoomDTO dto) {
		return mapper.isPlay(dto);
	}
	
	// pos proc
	int startPos(RoomDTO dto) {
		return mapper.startPos(dto);
	}
	int putPos(Pos pos) {		
		return mapper.putPos(pos);
	}
	
	List<Pos> selPos(RoomDTO dto) {
		try { // 처음 data가 0일때 대비
			final int setTime = 5;	// 시간 설정
			final int boardSize = 15;
			int[][] position = new int[boardSize][boardSize];
			List<Pos> list = mapper.selPos(dto);
			Pos newPos = new Pos();
			// 기권 체크
			if (mapper.chkAbs(dto).getIs_abs() == 1) {
				mapper.relAbs(dto);	// 방 기권 해제
				System.out.println("상대방 기권!!!");		
				mapper.winProc(dto); // 승리처리
				newPos.setMsg("chkAbs");
				list.add(newPos);
				return list;
			}
			for (Pos p : list) {
				position[p.getY()][p.getX()] = p.getZ();
			}
			Pos lastPos = list.get(list.size() - 1);
			
			// 시간체크
			//System.out.printf("p_time : %d , n_time : %d\n", lastPos.getP_time(), lastPos.getN_time());
			int diff = lastPos.getN_time() - lastPos.getP_time();
			//System.out.println("diff : " + diff);
			if (diff > setTime) {
				//System.out.println("시간초과!!!");
				newPos.setMsg("overTime");
				list.add(newPos);
				return list;				
			}
			
			String msg = PlayUtils.Rule(lastPos.getX(), lastPos.getY(), lastPos.getZ(), position, boardSize);
			newPos.setMsg(msg);
			newPos.setLeftTime(setTime - diff);
			list.add(newPos); // msg 전달
			return list;
		} catch (Exception e) {
			return mapper.selPos(dto);
		}
	}
	
	Pos isPos(Pos pos) {
		return mapper.isPos(pos);
	}
	
	int delPos(RoomDTO dto) {
		return mapper.delPos(dto);
	}
	
	// finish game proc
	int winProc(RoomDTO dto) {
		return mapper.winProc(dto);
	}
	int	loseProc(RoomDTO dto) {
		return mapper.loseProc(dto);
	}
	
	// 렌주룰 검증 proc - 그지 같지만 그냥 한다.
	int check_renju(RoomDTO dto, int pos_x, int pos_y) {
		try { // 처음 data가 0일때 대비
			final int boardSize = 15;
			int[][] position = new int[boardSize][boardSize];
			List<Pos> list = mapper.selPos(dto);
			list.add(new Pos(pos_x, pos_y, 1, ""));
			for (Pos p : list) {
				position[p.getY()][p.getX()] = p.getZ();
			}
			Pos lastPos = list.get(list.size() - 1);
			List<String> msgs = PlayUtils.RenJuRule(lastPos.getX(), lastPos.getY(), lastPos.getZ(), position, boardSize);

			for (String msg : msgs) {
				System.out.println("msg : " + msg);
			}
			
			// 렌주룰 위반 삭제
			if (msgs.get(0).equals("canNotPut") && msgs.get(1).equals("noWin")) {
				return 0;
			}
			
		} catch (Exception e) {
			return 1;
		}
		return 1;		
	}

	// 기권체크
	int setAbs(RoomDTO dto) {
		return mapper.setAbs(dto);
	}
	int relAbs(RoomDTO dto) {
		return mapper.relAbs(dto);
	}
	RoomDTO chkAbs(RoomDTO dto) {
		return mapper.chkAbs(dto);
	}
}




























