package com.ocean.realomuk.common;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.ocean.realomuk.model.GuestDTO;
import com.ocean.realomuk.model.UserEntity;


public class SecurityUtils {
	
	// =============================== 로그인 정보 관련
	//false: 로그아웃 상태, true: 로그인 상태  
	public static boolean isLogin(HttpSession hs) {
		return getLoginUser(hs) != null;
	}
	public static boolean isGuest(HttpSession hs) {
		return (GuestDTO)hs.getAttribute("guest") != null;
	}
	
	public static UserEntity getLoginUser(HttpSession hs) {
		return (UserEntity)hs.getAttribute("loginUser");
	}
	public static GuestDTO getGuestUser(HttpSession hs) {
		return (GuestDTO)hs.getAttribute("guest");
	}
	
	
	public static int getLoginUserPk(HttpSession hs) {
		UserEntity loginUser = getLoginUser(hs);
		return loginUser == null ? 0 : loginUser.getI_user();
	}

// 폐기	
//	public static String hasRoomCode(HttpSession hs) {
//		UserEntity loginUser = getLoginUser(hs);
//		if (loginUser == null) {
//			GuestDTO guest = (GuestDTO)hs.getAttribute("guest");
//			if (guest != null) {
//				return guest.getRoom_code(); 
//			}
//			return null;  
//		}
//		return loginUser.getRoom_code();
//	}
	
	
	// =============================== 보안 코드 관련
	public static String gensalt() {
		return BCrypt.gensalt();
	}

	public static String hashPassword(String pw, String salt) {
		return BCrypt.hashpw(pw, salt);
	}
	
	public static String getRoomCode(int length) {
		String code = "";
		// 55개 문자 
		char[] ch = {'0','1','2','3','4','5','6','7','8','9',
				'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','w','x','y','z',
				'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','W','X','Y','Z'};
		for (int i = 0; i < length; i++) {
			code += ch[(int)(Math.random() * 55)];
		}
		return code;
	}
	
	public static String getGuestId() {
		String id = "";
		char[] ch = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','w','x','y','z'};
		char[] num = {'0','1','2','3','4','5','6','7','8','9'};
		for (int i = 0; i < 5; i++) {
			id += ch[(int)(Math.random() * 23)];
		}
		for (int i = 0; i < 3; i++) {
			id += num[(int)(Math.random() * 10)];
		}
		return id;
	}

}






















