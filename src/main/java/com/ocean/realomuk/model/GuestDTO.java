package com.ocean.realomuk.model;

import org.apache.ibatis.type.Alias;

@Alias("GuestDTO")
public class GuestDTO {
	private String id;
	private String room_code;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoom_code() {
		return room_code;
	}
	public void setRoom_code(String room_code) {
		this.room_code = room_code;
	}
}
