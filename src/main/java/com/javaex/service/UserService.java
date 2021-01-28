package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	// 회원가입
	public int join(UserVo userVo) {
		System.out.println("userService join()");
		return userDao.insert(userVo);
	}
	
	//로그인
	public UserVo login(UserVo userVo) {
		System.out.println("userService login()");
		return userDao.selectUser(userVo);
		
	}
	
	public UserVo modifyForm(int no) {
		System.out.println("userService modifyForm()");
		return userDao.selectUser2(no);
		
		
	}
	
	public void modify(UserVo userVo) {
		System.out.println("userService modify()");
		
		userDao.update(userVo);
	}
}
