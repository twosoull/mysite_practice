package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RBoardVo;

@Repository
public class RBoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public List<RBoardVo> selectRboardList() {
		System.out.println("Dao : selectRboardList()");
		
		return sqlSession.selectList("rboard.selectList");
		
	}
	public void insertRBoard(RBoardVo rBoardVo) {
		System.out.println("Dao : RBoard()");
		
		sqlSession.insert("rboard.insertRBoard",rBoardVo);
		
		
	}
	
	public RBoardVo selectRBoard(int no) {
		System.out.println("Dao : selectRBoard");
		
		return sqlSession.selectOne("rboard.selectOne",no);
		
	}
	
	public void insertRBoardReply(RBoardVo rBoardVo) {
		System.out.println("Dao : RBoard()");
		
		sqlSession.insert("rboard.insertRBoardReply",rBoardVo);
	}
	
	public List<RBoardVo> selectGroupNoRBoardList(int no) {
		System.out.println("Dao : selectGroupNoRboardList()");
		
		return sqlSession.selectList("rboard.selectGroupNoRBoardList",no);
		
	}
	
	public void updateOrderNo(RBoardVo rBoardVo) {
		System.out.println("Dao: updateOrderNo()");
		
		sqlSession.update("rboard.updateOrderNo" , rBoardVo);
		
	}
	
}
