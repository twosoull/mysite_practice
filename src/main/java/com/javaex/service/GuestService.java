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
	
	public int delete(GuestVo guestVo) {
		System.out.println("service delete()");
		System.out.println(guestVo);
		
		return guestDao.delete(guestVo);
	}
}
