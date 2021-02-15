package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.PageAmount;
import com.javaex.vo.UserVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> list() {
		System.out.println("Service : list");
		
		return boardDao.selectBoardList();
	}
	
	//검색 기능 리스트
	public Map<String,Object> list2(String keyword,int crtPage) {
		System.out.println("Service : list2()");
		
		//페이지 글갯수
		int listCnt = 10;
		
		int startRnum = (crtPage-1)*listCnt+1;
		int	endRnum = (startRnum + listCnt) - 1;
		
		List<BoardVo> boardList = boardDao.selectBoardList2(keyword,startRnum,endRnum);
		/////////////////////////////////
		//페이징 계산
		////
		//한화면에 뿌려지는 페이지버튼의 수
		int pageBtnCount = 5;
		
		//한화면에 마지막 버튼의 숫자
		int endPageBtnNo = (int) (Math.ceil(crtPage/(double)pageBtnCount) * pageBtnCount);
		
		//한화면에 첫번재 버튼의 숫자
		int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);
		
		
		int totalCount = boardDao.selectTotalCnt(keyword);
		
		
		
		//
		boolean next; //다음페이지의 여부
		if(endPageBtnNo*listCnt < totalCount){ //125*10 /1234
			next = true;
		}else{
			next = false;
			endPageBtnNo =(int)Math.ceil(totalCount/(double)listCnt);
		}
		
		boolean prev;
		
		if(startPageBtnNo != 1) {
			prev = true;
		}else {
			prev = false;
		}

		
		System.out.println("endPageBtnNo : " +endPageBtnNo);
		System.out.println("startPageBtnNo : " + startPageBtnNo);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("boardList", boardList);
		map.put("endPageBtnNo", endPageBtnNo);
		map.put("startPageBtnNo", startPageBtnNo);
		map.put("next", next);
		map.put("prev", prev);
		
		return map;
	}
	
	/*
	//page의 양을 센다
	public PageAmount pageAmount(int pageNum) {
		System.out.println("Service : pageAmount");
		PageAmount pageAmount = new PageAmount();
		
		int boardAmount = boardDao.boardAmount();
		
		pageAmount.setBoardAmount(boardAmount); //처음 페이지 양구하기
		pageAmount.setPrev(pageNum-1);	//이전페이지
		pageAmount.setNext(pageNum+1);	//이후페이지
		pageAmount.setPageNow(pageNum);  //현재페이지
		System.out.println("page : "+pageAmount.getPage());
		
		return pageAmount;
	}
	*/
	public BoardVo read(int no) {
		System.out.println("Service : read");
		System.out.println(no);
		boardDao.upHit(no);
		return boardDao.selectBoard(no);
	}
	//writeForm.jsp에서 session no값을 name = userNo로 파라미터로 넘겨
	//객체로 받은방식
	public void write(BoardVo boardVo) {
		System.out.println("Service : write");

		boardDao.insertBoard(boardVo);
		
		
	}
	
	//service에서 세션값을 얻어 vo에 담는 방식
	//만약 프로그램을 다른곳에 갈아끼울경우 객체타입이 다를 수 있다고 생각이들어
	//jsp에서 hidden으로 세션값을 주는게 좀더 나은 방식이라 생각이 들기도합니다
	public void write(BoardVo boardVo,HttpSession session) {
		System.out.println("Service : write + session");
		
		//jsp에서 hidden으로 넣는 것보다 여기서 값을 가져오는게 안전하다고
		//판단해서 만들어 봤는데 맞을지 모르겠습니다
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());
		System.out.println(boardVo);
		
		boardDao.insertBoard(boardVo);
	
	}
	public BoardVo modifyForm(int no) {
		System.out.println("Service : modifyForm");
		
		BoardVo boardVo = boardDao.selectBoard(no);
		//필요없는 정보인 userNo를 기본값 0으로 바꿔준다
		//선생님 이렇게 해도될까요??
		boardVo.setUserNo(0);
		
		return boardVo;
	}
	
	public void modify(BoardVo boardVo) {
		System.out.println("Service : modify");
		
		boardDao.updateBoard(boardVo);
		
	}
	
	public void remove(int no) {
		System.out.println("Service : remove");
		
		boardDao.deleteBoard(no);
		
	}
}
