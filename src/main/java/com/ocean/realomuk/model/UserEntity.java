package com.ocean.realomuk.model;

import org.apache.ibatis.type.Alias;

@Alias("UserEntity")
public class UserEntity {
	private int i_user;
	private String user_id;
	private String user_pw;
	private String salt;
	private String nm;
	private int gender;
	private String e_mail;
	private String profile_img;
	private String r_dt;
	private int rating;
	private int win_cnt;
	private int lose_cnt;
	private int draw_cnt;
	private int ranking;
	
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
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	public String getR_dt() {
		return r_dt;
	}
	public void setR_dt(String r_dt) {
		this.r_dt = r_dt;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getWin_cnt() {
		return win_cnt;
	}
	public void setWin_cnt(int win_cnt) {
		this.win_cnt = win_cnt;
	}
	public int getLose_cnt() {
		return lose_cnt;
	}
	public void setLose_cnt(int lose_cnt) {
		this.lose_cnt = lose_cnt;
	}
	public int getDraw_cnt() {
		return draw_cnt;
	}
	public void setDraw_cnt(int draw_cnt) {
		this.draw_cnt = draw_cnt;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
}
