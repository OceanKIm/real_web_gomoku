package com.ocean.realomuk.model;

import org.apache.ibatis.type.Alias;

@Alias("PlayChatEntity")
public class PlayChatEntity {
	private int i_chat;
	private String user_id;
	private String ctnt;
	private String r_dt;
	private String room_code;
	
	public int getI_chat() {
		return i_chat;
	}
	public void setI_chat(int i_chat) {
		this.i_chat = i_chat;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCtnt() {
		return ctnt;
	}
	public void setCtnt(String ctnt) {
		this.ctnt = ctnt;
	}
	public String getR_dt() {
		return r_dt;
	}
	public void setR_dt(String r_dt) {
		this.r_dt = r_dt;
	}
	public String getRoom_code() {
		return room_code;
	}
	public void setRoom_code(String room_code) {
		this.room_code = room_code;
	}
}
