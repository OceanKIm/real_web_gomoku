package com.ocean.realomuk.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocean.realomuk.common.Const;
import com.ocean.realomuk.common.SecurityUtils;
import com.ocean.realomuk.model.UserDTO;
import com.ocean.realomuk.model.UserDomain;

@Service
public class UserService {

	@Autowired
	private UserMapper mapper;
	
	public int selUser(HttpSession hs, UserDTO dto) {
		UserDomain vo = mapper.selUser(dto);
		if (vo == null) {
			return 3;	// 아이디 없음.
		}
		String spw = SecurityUtils.hashPassword(dto.getUser_pw(), vo.getSalt());
		if (!vo.getUser_pw().equals(spw)) {
			return 2;	// 비밀번호 틀림.
		}
		hs.setAttribute(Const.KEY_LOGINUSER, vo);
		return 1;	// 로그인 성공.
	}
	
	
	public int insUser(UserDTO dto) {
		// 암호화 작업.
		String salt = SecurityUtils.gensalt();
		String spw = SecurityUtils.hashPassword(dto.getUser_pw(), salt);
		dto.setSalt(salt);
		dto.setUser_pw(spw);
		return mapper.insUser(dto);
	}
	
}
