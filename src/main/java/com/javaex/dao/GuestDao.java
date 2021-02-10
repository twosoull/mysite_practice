package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestVo> selectGuestList() {
		System.out.println("Dao selectGuest()");
		
		return sqlSession.selectList("guest.selectGuestList");
	}
	
	public void insert(GuestVo guestVo) {
		System.out.println("Dao insert()");
		System.out.println(guestVo);
		
		sqlSession.insert("guest.insert",guestVo);
		
	}
	
	public void insertGetNo(GuestVo guestVo) {
		System.out.println("Dao insert()");
		System.out.println(guestVo);
		
		sqlSession.insert("guest.insertAjax",guestVo);
		
	}
	public GuestVo selectGuestOne(int no) {
		System.out.println("selectGuestOne");
		
		return sqlSession.selectOne("guest.selectOne",no);
		
	}
	
	public int delete(GuestVo guestVo) {
		System.out.println("Dao delete()");
		System.out.println(guestVo);
		
		return sqlSession.delete("guest.delete",guestVo);
		
	}
}
