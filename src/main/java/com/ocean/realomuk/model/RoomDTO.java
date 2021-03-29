package com.ocean.realomuk.model;

import org.apache.ibatis.type.Alias;

@Alias("RoomDTO")
public class RoomDTO extends RoomEntity {
	
	// ground에서 room_code 삽입을 위한 i_user
	private int i_user;
	// 흑백 선택을 위한 user id;
	private String user_id;
	// put pos
	private String pos_id;
	// turn
	private int turn;
	// msg
	private String msg;
	
	public int getI_user() {
		return i_user;
	}
	public void setI_user(int i_user) {
		this.i_user = i_user;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPos_id() {
		return pos_id;
	}
	public void setPos_id(String pos_id) {
		this.pos_id = pos_id;
	}	
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
