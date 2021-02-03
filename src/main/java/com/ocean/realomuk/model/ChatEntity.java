package com.ocean.realomuk.model;

import org.apache.ibatis.type.Alias;

@Alias("ChatEntity")
public class ChatEntity {
	private int i_chat;
	private String i_user;
	private String ctnt;
	private String r_dt;
	
	
	public int getI_chat() {
		return i_chat;
	}
	public void setI_chat(int i_chat) {
		this.i_chat = i_chat;
	}
	public String getI_user() {
		return i_user;
	}
	public void setI_user(String i_user) {
		this.i_user = i_user;
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
}
