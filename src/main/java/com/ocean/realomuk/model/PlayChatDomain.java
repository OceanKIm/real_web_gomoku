package com.ocean.realomuk.model;

import org.apache.ibatis.type.Alias;

@Alias("PlayChatDomain")
public class PlayChatDomain extends PlayChatEntity {
	private int rating;

	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
}
