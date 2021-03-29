package com.ocean.realomuk.model;

import org.apache.ibatis.type.Alias;

@Alias("RoomEntity")
public class RoomEntity {
	private int i_room;
	private String room_code;
	private String black_player;
	private String White_player;
	private String r_dt;
	private String room_host;
	private int is_abs;
	
	public int getI_room() {
		return i_room;
	}
	public void setI_room(int i_room) {
		this.i_room = i_room;
	}
	public String getRoom_code() {
		return room_code;
	}
	public void setRoom_code(String room_code) {
		this.room_code = room_code;
	}
	public String getBlack_player() {
		return black_player;
	}
	public void setBlack_player(String black_player) {
		this.black_player = black_player;
	}
	public String getWhite_player() {
		return White_player;
	}
	public void setWhite_player(String white_player) {
		White_player = white_player;
	}
	public String getR_dt() {
		return r_dt;
	}
	public void setR_dt(String r_dt) {
		this.r_dt = r_dt;
	}
	public int getIs_abs() {
		return is_abs;
	}
	public void setIs_abs(int is_abs) {
		this.is_abs = is_abs;
	}
	public String getRoom_host() {
		return room_host;
	}
	public void setRoom_host(String room_host) {
		this.room_host = room_host;
	}
}
