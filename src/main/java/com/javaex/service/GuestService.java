package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestService {
	
	@Autowired
	private GuestDao guestDao;
	
	public List<GuestVo> list() {
		System.out.println("service list()");
		
		return guestDao.selectGuestList();
	}
	
	public void add(GuestVo guestVo) {
		System.out.println("service add()");
		
		guestDao.insert(guestVo);
		
	}
	
	//매개변수 vo를 넣어 no값을 얻은후
	//같은 no 값을 함께넣어 insert후
	//no가 얻어진 객체를 이용해 insert했던 vo의 내용을 가져온다
	public GuestVo writeResultVo(GuestVo guestVo) {
		System.out.println("service add()");
		
		//vo를 insert하고 값 얻기
		guestDao.insertGetNo(guestVo);
		
		return guestDao.selectGuestOne(guestVo.getNo());
	}
	
	public int delete(GuestVo guestVo) {
		System.out.println("service delete()");
		System.out.println(guestVo);
		
		return guestDao.delete(guestVo);
	}
}
