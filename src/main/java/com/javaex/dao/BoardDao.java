package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public List<BoardVo> selectBoardList(BoardVo boardVo) {
		System.out.println("BoardDao : selectBoardList()");
		List<BoardVo> list = sqlSession.selectList("board.selectList",boardVo);
		System.out.println(boardVo.getFirstNum());
		System.out.println(list);
				
	return list;
	}
	
	//페이지숫자를 얻기위한 글갯수를 가져온다
	
	public int boardAmount() {
		System.out.println("BoardDao : boardAmount()");
		return sqlSession.selectOne("pageAmount");
	}
	
	public BoardVo selectBoard(int no) {
		System.out.println("BoardDao : selectBoard()");
		System.out.println(no);
		
		return sqlSession.selectOne("board.selectBoard",no);
	
	}
	
	public void upHit(int no) {
		System.out.println("BoardDao : upHit()");
		
		sqlSession.update("board.updateHit",no);
	}
	public void insertBoard(BoardVo boardVo) {
		System.out.println("BoardDao : insertBoard()");
		
	
		sqlSession.insert("board.insert",boardVo);
	}
	
	///수정폼 원래 정석적인 방법 sql문에서 필요한 정보만 빼오는 경우
	/*
	public BoardVo selectBoard2(int no) {
		System.out.println("BoardDao : selectBoard()");
		System.out.println(no);
		
		return sqlSession.selectOne("board.selectBoard2",no);
		
		
	}
	*/
	
	public void updateBoard(BoardVo boardVo) {
		System.out.println("BoardDao : updateBoard");
		
		sqlSession.update("board.update",boardVo);
		
	}
	
	public void deleteBoard(int no) {
		System.out.println("BoardDao : deleteBoard");
		System.out.println(no);
		
		sqlSession.delete("board.delete",no);
		
	}
}
