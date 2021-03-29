package com.ocean.realomuk.model;

import org.apache.ibatis.type.Alias;

@Alias("Pos")
public class Pos {
	private int i_room;
	private int x;
	private int y;
	private int z;
	private String room_code;
	private int p_time;
	private int n_time;
	private int leftTime;
	private String msg; // Pos DTO 멤버 변수
	
	

	public Pos() {};
	
	public Pos(int x, int y, int z, String room_code) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.room_code = room_code;
	}
	
	public String output() {
		return String.format("%d-%d-%d/", x, y, z);
	}
	
	@Override
	public String toString() {
		return String.format("[%d,%d,%d]", x, y, z);
	}
	
	public int getI_room() {
		return i_room;
	}
	public void setI_room(int i_room) {
		this.i_room = i_room;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public String getRoom_code() {
		return room_code;
	}
	public void setRoom_code(String room_code) {
		this.room_code = room_code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}	
	public int getP_time() {
		return p_time;
	}
	public void setP_time(int p_time) {
		this.p_time = p_time;
	}
	public int getN_time() {
		return n_time;
	}
	public void setN_time(int n_time) {
		this.n_time = n_time;
	}
	public int getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(int leftTime) {
		this.leftTime = leftTime;
	}	
}
